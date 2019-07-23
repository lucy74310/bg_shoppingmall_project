package com.cafe24.shoppingmall.backend.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cafe24.shoppingmall.backend.repository.ProductDao;
import com.cafe24.shoppingmall.backend.vo.CategoryVo;
import com.cafe24.shoppingmall.backend.vo.ImageVo;
import com.cafe24.shoppingmall.backend.vo.OptionDetailVo;
import com.cafe24.shoppingmall.backend.vo.OptionVo;
import com.cafe24.shoppingmall.backend.vo.ProductCategoryVo;
import com.cafe24.shoppingmall.backend.vo.ProductOptionVo;
import com.cafe24.shoppingmall.backend.vo.ProductVo;

@Service
public class ProductService {
	
	
	@Autowired
	private ProductDao productDao;
	
	public ProductVo getProductInfo(Long no) {
		return null;
	}

	
	public List<ProductOptionVo> getProductOptionInfo(Long no) {
		List<ProductOptionVo> po_list = new ArrayList<ProductOptionVo>();
		return po_list;
	}
	
	//상품 리스트 가져오기 
	public List<ProductVo> getList() {
		return productDao.getList();
	}
	
	//상품 삭제
	@Transactional
	public int deleteProduct(Long deleteNo) {
		// 상품 카테고리 삭제
		deleteCategory(deleteNo);
		// 옵션no 가져와서 세부옵션 먼저 삭제
		List<Long> option_no = productDao.getOptionNoByProductNo(deleteNo);
		
		for(Long no : option_no) {
			//세부옵션 삭제
			productDao.deleteOptionDetailByOptionNo(no);
		}
		//옵션 삭제
		productDao.deleteOptionByProductNo(deleteNo);
		
		// 상품옵션 삭제
		productDao.deleteProductOptionByProductNo(deleteNo);
		
		// 이미지 삭제
		productDao.deleteImageByProductNo(deleteNo);
		
		// 상품삭제
		int deleteSuccess = productDao.deleteProduct(deleteNo);
		
		return deleteSuccess;
	}
	
	// 상품 등록
	@Transactional
	public Long addProduct(ProductVo productVo) {
		
		// 상품 insert
		Long no = productDao.insertProduct(productVo);
		
		if (no != null) {
			// 옵션,세부옵션,상품옵션 insert
			if( !insertAboutOption(productVo, no) ) return null;
			
			// 카테고리 insert
			if( !insertCategory(productVo, no)) return null;
			
			// 이미지 insert
			if( !insertImage(productVo, no)) return null;
		}
		
		return no;
	}

	// 상품 수정
	@Transactional
	public boolean updateProduct(ProductVo productVo) {
		Long no = productVo.getNo();
		
		// 상품 update
		if(productVo.getUse_option() == 'N') {
			// 옵션,세부옵션,상품옵션 delete
			deleteAboutOption(productVo);
			// 상품 => 상품옵션 일때 insert
			insertSameProductOption(productVo);
		} else {
			// 옵션,세부옵션,상품옵션 update
			updateAboutOption(productVo, no);
		}
		
		if(productVo.getUse_stock() == 'Y') {
			productVo.setStock(0);
			productVo.setSoldout_mark('N');
		}
		
		
		// 카테고리 update
		updateAboutCategory(productVo, no);
					
		// 이미지 update
		updateAboutImage(productVo, no);
		
		// 상품 update
		productDao.updateProduct(productVo);	
		
		
		return true;
	}

	
	// 옵션,세부옵션,상품옵션 insert
	private boolean insertAboutOption(ProductVo productVo, Long no) {
		if(productVo.getUse_option() == 'Y') {
			
			int option_order = 1;
			for (OptionVo option : productVo.getO_list() ) {
				
				option.setProduct_no(no);
				option.setOp_order(option_order);
				option.setNo(productDao.insertOption(option));
					
				if(option.getNo() == null) return false;
				
				int detail_order = 1;
				
				for(OptionDetailVo od : option.getOd_list()) {
					
					od.setOption_no(option.getNo());
					od.setOpd_order(detail_order);
					od.setNo(productDao.insertOptionDetail(od));
					if(od.getNo() == null) return false;
					detail_order++;
				}
				option_order++;
			}
			
			for(ProductOptionVo po : productVo.getPo_list()) {
				po.setProduct_no(no);
				po.setUse_stock(productVo.getUse_stock());
				
				productDao.insertProductOption(po);
			}
		} else {
			productVo.setNo(no);
			// 상품 => 상품옵션 일때 insert
			insertSameProductOption(productVo);
		}
		
		
		
		return true;
	}
	
	// 옵션 삭제 ( 상품 수정 시 옵션 사용 여부 N일때 ) 
	private boolean deleteAboutOption(ProductVo productVo) {
		// 세부옵션 delete
		List<OptionVo> option_list = productVo.getO_list();
		for(OptionVo o : option_list) {
			productDao.deleteOptionDetailByOptionNo(o.getNo());
		}
		// 옵션 delete 
		productDao.deleteOptionByProductNo(productVo.getNo());
		
		// 상품옵션 delete
		productDao.deleteProductOptionByProductNo(productVo.getNo());
		return true;
	}
	
	// 상품 => 상품옵션 일때 insert
	private boolean insertSameProductOption(ProductVo productVo) {
		ProductOptionVo productOptionVo = new ProductOptionVo(productVo.getProduct_name(), productVo.getDisplayed(),
				productVo.getSelling(), productVo.getProduct_price(), 1);
		productOptionVo.setUse_stock(productVo.getUse_stock());
		productOptionVo.setStock(productVo.getStock());
		productOptionVo.setProduct_no(productVo.getNo());
		productDao.insertProductOption(productOptionVo);
		return true;
	}
	
	
	// 옵션,세부옵션,상품옵션 update
	private boolean updateAboutOption(ProductVo productVo, Long no) {
		// 옵션, 세부옵션 update를 다룸		
		for (OptionVo o : productVo.getO_list() ) {
			// flag 검사 - insert, delete, update 실행, 비어있으면 아무것도 실행하지 않는다.
			if("insert" == o.getFlag()) {
				// 옵션 insert
				Long option_no = productDao.insertOption(o);
				for( OptionDetailVo od : o.getOd_list() ) {
					// 세부옵션 insert
					od.setOption_no(option_no);
					productDao.insertOptionDetail(od);
				}
			} else if("update" == o.getFlag()) {
				// 옵션 update
				productDao.updateOption(o);
				for( OptionDetailVo od : o.getOd_list() ) {
					// 세부옵션 update
					productDao.updateOptionDetail(od);
				}
			} else if("delete" == o.getFlag()) {
				//부모인 option_no 넘김 - 세부옵션 delete
				productDao.deleteOptionDetailByOptionNo(o.getNo());
				// 옵션 delete
				productDao.deleteOptionByOptionNo(o.getNo());
			}
		}
		for(ProductOptionVo povo : productVo.getPo_list()) {
			if("insert" == povo.getFlag()) {
				productDao.insertProductOption(povo);
			} else if("update" == povo.getFlag()) {
				productDao.updateProductOption(povo);
			} else if("delete" == povo.getFlag()) {
				productDao.deleteProductOptionByNo(povo.getNo());
			}
				
		}
		
		
		
		return true;
	}
	
	
	
	// 상품카테고리 insert
	private boolean insertCategory(ProductVo productVo, Long no) {
		if(productVo.getCategory_list() != null) {
			for(ProductCategoryVo pcVo : productVo.getCategory_list()) {
				pcVo.setProduct_no(no);
				boolean result = productDao.insertProductCategory(pcVo);
				if(!result) return false;
			}
		}
		return true;
	}
	
	// 상품카테고리 delete 
	private boolean deleteCategory(Long product_no) {
		productDao.deleteProductCategory(product_no);
		return true;
	}
	
	// 카테고리 update
	private boolean updateAboutCategory(ProductVo productVo, Long no) {
		// 기존에 product_no = no로 갖고 있는 로우 삭제
		// 인서트 
		
		if(productVo.getCategory_list() != null) {
			for(ProductCategoryVo pcVo : productVo.getCategory_list()) {
				if("insert".contentEquals(pcVo.getFlag())) {
					productDao.insertProductCategory(pcVo);
				}else if("delete".contentEquals(pcVo.getFlag())) {
					productDao.deleteProductCategory(pcVo.getNo());
				}
				
			}
		}
		return true;
	}
	
	
	
	// 이미지 insert
	private boolean insertImage(ProductVo productVo, Long no) {
		if(productVo.getImage_list() != null) {
			for(ImageVo image : productVo.getImage_list()) {
				image.setProduct_no(no);
				boolean result = productDao.insertImage(image);
				if(!result) return false;
			}
		}
		return true;
	}
	

	// 이미지 update
	private boolean updateAboutImage(ProductVo productVo, Long no) {
		if(productVo.getImage_list() != null) {
			for(ImageVo image : productVo.getImage_list()) {
				if("insert".contentEquals(image.getFlag())) {
					image.setProduct_no(no);
					productDao.insertImage(image);
				} else if("update".contentEquals(image.getFlag())) {
					productDao.updateImage(image);
				} else if("delete".contentEquals(image.getFlag())) {
					productDao.deleteImageByNo(image.getNo());
				}
				
			}
		}
		return true;
	}


	public List<OptionVo> getOptionList(Long no) {
		// TODO Auto-generated method stub
		return null;
	}


	public List<ImageVo> getImageList(Long no) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public List<CategoryVo> getCatetoryInfo(Long no) {
		
		return null;
	}
	
	
	
}
