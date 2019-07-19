package com.cafe24.shoppingmall.backend.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cafe24.shoppingmall.backend.repository.ProductDao;
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
	
	@Transactional
	public int deleteProduct(Long deleteNo) {
		// 상품 카테고리 삭제
		deleteCategory(deleteNo);
		// 옵션no 가져와서 세부옵션 먼저 삭제
		List<Long> option_no = productDao.getOptionNoByProductNo(deleteNo);
		
		for(Long no : option_no) {
			//세부옵션 삭제
			productDao.deleteOptionDetail(no);
		}
		//옵션 삭제
		productDao.deleteOption(deleteNo);
		// 상품옵션 삭제
		productDao.deleteProductOption(deleteNo);
		
		// 이미지 삭제
		productDao.deleteImage(deleteNo);
		
		
		// 상품삭제
		int deleteSuccess = productDao.deleteProduct(deleteNo);
		return deleteSuccess;
	}
	
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

	@Transactional
	public boolean updateProduct(ProductVo productVo) {
		Long no = productVo.getNo();
		if (no != null) {
			// 옵션,세부옵션,상품옵션 insert
			updateAboutOption(productVo, no);
			
			// 카테고리 insert
			updateCategory(productVo, no);
			
			// 이미지 insert
			updateImage(productVo, no);
			
		}
		
		
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
			ProductOptionVo productOptionVo = new ProductOptionVo(productVo.getProduct_name(), productVo.getDisplayed(),
					productVo.getSelling(), productVo.getProduct_price(), 1);
			productOptionVo.setUse_stock(productVo.getUse_stock());
			productOptionVo.setNo(no);
			productDao.insertProductOption(productOptionVo);
			
		}
		
		
		
		return true;
	}
	
	
	// 옵션,세부옵션,상품옵션 update
	private boolean updateAboutOption(ProductVo productVo, Long no) {
		if(productVo.getUse_option() == 'Y') {
			for (OptionVo option : productVo.getO_list() ) {
				productDao.updateOption(option);
				for(OptionDetailVo od : option.getOd_list()) {
					productDao.updateOptionDetail(od);
				}
			}
		}
		return true;
	}
	
	
	
	// 카테고리 insert
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
	
	// 카테고리 update
	private boolean updateCategory(ProductVo productVo, Long no) {
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
	
	// 상품카테고리 delete 
	private boolean deleteCategory(Long product_no) {
		productDao.deleteProductCategory(product_no);
		
		return true;
	}
	
	// 이미지 insert
	public boolean insertImage(ProductVo productVo, Long no) {
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
	private boolean updateImage(ProductVo productVo, Long no) {
		if(productVo.getImage_list() != null) {
			for(ImageVo image : productVo.getImage_list()) {
				image.setProduct_no(no);
				productDao.updateImage(image);
			}
		}
		return true;
	}

	
	//상품 리스트 가져오기 
	public List<ProductVo> getList() {
		return productDao.getList();
	}
	
}
