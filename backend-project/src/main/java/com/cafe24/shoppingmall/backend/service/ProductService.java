package com.cafe24.shoppingmall.backend.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cafe24.shoppingmall.backend.repository.CategoryDao;
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

	@Autowired
	private CategoryDao categoryDao;

	// 상품 삭제
	@Transactional
	public int deleteProduct(Long deleteNo) {
		// 상품 카테고리 삭제
		deleteCategory(deleteNo);
		// 옵션no 가져와서 세부옵션 먼저 삭제
		List<Long> option_no = productDao.getOptionNoByProductNo(deleteNo);

		for (Long no : option_no) {
			// 세부옵션 삭제
			productDao.deleteOptionDetailByOptionNo(no);
		}
		// 옵션 삭제
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
		System.out.println("productVo :" + productVo.getProduct_name());
		Long no = productDao.insertProduct(productVo);
		System.out.println(no);

		if (no != null) {
			// 옵션,세부옵션,상품옵션 insert
			if (!insertAboutOption(productVo, no))
				return null;

			// 카테고리 insert
			if (!insertCategory(productVo, no))
				return null;

			// 이미지 insert
			if (!insertImage(productVo, no))
				return null;
		}

		return no;
	}

	// 상품 수정
	@Transactional
	public int updateProduct(ProductVo productVo) {
		Long no = productVo.getNo();

		// 상품 update
		if (productVo.getUse_option() == "N") {
			// 옵션,세부옵션,상품옵션 delete
			deleteAboutOption(productVo);
			// 상품 => 상품옵션 일때 insert
			insertSameProductOption(productVo);
		} else {
			// 옵션,세부옵션,상품옵션 update
			updateAboutOption(productVo, no);
		}

		if (productVo.getUse_stock() == "Y") {
			productVo.setStock(0);
			productVo.setSoldout_mark("N");
		}

		// 카테고리 update
		updateAboutCategory(productVo, no);

		// 이미지 update
		updateAboutImage(productVo, no);

		// 상품 update
		int count = productDao.updateProduct(productVo);

		return count;
	}

	// 옵션,세부옵션,상품옵션 insert
	private boolean insertAboutOption(ProductVo productVo, Long no) {
		if ("Y".contentEquals(productVo.getUse_option())) {

			int option_order = 1;
			System.out.println(productVo);
			if (productVo.getO_list() != null) {
				for (OptionVo option : productVo.getO_list()) {

					option.setProduct_no(no);
					option.setOp_order(option_order);
					option.setNo(productDao.insertOption(option));

					if (option.getNo() == null)
						return false;

					int detail_order = 1;
					if (option.getOd_list() != null) {
						for (OptionDetailVo od : option.getOd_list()) {

							od.setOption_no(option.getNo());
							od.setOpd_order(detail_order);
							od.setNo(productDao.insertOptionDetail(od));
							if (od.getNo() == null)
								return false;
							detail_order++;
						}

					}
					option_order++;
				}
				List<ProductOptionVo> po_list =  new ArrayList<ProductOptionVo>();
				int size = productVo.getO_list().size();
				int i = 0;
				int order = 1;
				List<String> poname = new ArrayList<String>();
				List<String> pocode = new ArrayList<String>();
				List<Long> plus_price = new ArrayList<Long>(); 
				po_list = makeProductOpionList(productVo.getO_list(), size, i, po_list, poname, pocode,plus_price, order);
				
				
				productVo.setPo_list(po_list);
			}

			if (productVo.getPo_list() != null ) {
				for (ProductOptionVo po : productVo.getPo_list()) {
					po.setProduct_no(no);
					po.setUse_stock(productVo.getUse_stock());

					productDao.insertProductOption(po);
				}
			}
		} else {
			System.out.println(productVo.getUse_option());
			productVo.setNo(no);
			// 상품 => 상품옵션 일때 insert
			insertSameProductOption(productVo);
		}

		return true;
	}

	private List<ProductOptionVo> makeProductOpionList(List<OptionVo> option_list, int size, int index,
			List<ProductOptionVo> po_list, List<String> poname,List<String> pocode, List<Long> plus_price, int order) {
		List<OptionDetailVo> od_list = option_list.get(index).getOd_list();
		int od_list_size = od_list.size();
		int new_index = index + 1;
		for (int i = 0; i < od_list_size; i++) {
			List<String> parent_poname = new ArrayList<String>();
			List<String> parent_pocode = new ArrayList<String>();
			List<Long> parent_plus_price = new ArrayList<Long>();
			for (String n : poname) {
				parent_poname.add(n);
			}
			for (String c : pocode) {
				parent_pocode.add(c);
			}
			for (Long price : plus_price) {
				parent_plus_price.add(price);
			}
			parent_poname.add(od_list.get(i).getOpd_name());
			parent_pocode.add(od_list.get(i).getNo().toString());
			parent_plus_price.add(od_list.get(i).getPlus_price());
			if (index == (size - 1)) {
				Long sub_plus_price = 0L;
				for (Long price : parent_plus_price) {
					sub_plus_price += price;
				}
				po_list.add(new ProductOptionVo(String.join("/", parent_poname),String.join("/", parent_pocode), sub_plus_price, order++));
			} else if (index < size - 1) {
				makeProductOpionList(option_list, size, new_index, po_list, parent_poname,parent_pocode, parent_plus_price, order);
			}
		}
		return po_list;
	}

	// 옵션 삭제 ( 상품 수정 시 옵션 사용 여부 N일때 )
	private boolean deleteAboutOption(ProductVo productVo) {
		// 세부옵션 delete
		List<OptionVo> option_list = productVo.getO_list();
		for (OptionVo o : option_list) {
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
				productVo.getSelling(), 0L, 1);
		productOptionVo.setUse_stock(productVo.getUse_stock());
		productOptionVo.setStock(productVo.getStock());
		productOptionVo.setProduct_no(productVo.getNo());
		productDao.insertProductOption(productOptionVo);
		return true;
	}

	// 옵션,세부옵션,상품옵션 update
	private boolean updateAboutOption(ProductVo productVo, Long no) {
		// 옵션, 세부옵션 update를 다룸
		for (OptionVo o : productVo.getO_list()) {
			// flag 검사 - insert, delete, update 실행, 비어있으면 아무것도 실행하지 않는다.
			if ("insert" == o.getFlag()) {
				// 옵션 insert
				Long option_no = productDao.insertOption(o);
				for (OptionDetailVo od : o.getOd_list()) {
					// 세부옵션 insert
					od.setOption_no(option_no);
					productDao.insertOptionDetail(od);
				}
			} else if ("update" == o.getFlag()) {
				// 옵션 update
				productDao.updateOption(o);
				for (OptionDetailVo od : o.getOd_list()) {
					// 세부옵션 update
					productDao.updateOptionDetail(od);
				}
			} else if ("delete" == o.getFlag()) {
				// 부모인 option_no 넘김 - 세부옵션 delete
				productDao.deleteOptionDetailByOptionNo(o.getNo());
				// 옵션 delete
				productDao.deleteOptionByOptionNo(o.getNo());
			}
		}
		for (ProductOptionVo povo : productVo.getPo_list()) {
			if ("insert" == povo.getFlag()) {
				productDao.insertProductOption(povo);
			} else if ("update" == povo.getFlag()) {
				productDao.updateProductOption(povo);
			} else if ("delete" == povo.getFlag()) {
				productDao.deleteProductOptionByNo(povo.getNo());
			}

		}

		return true;
	}

	// 상품카테고리 insert
	private boolean insertCategory(ProductVo productVo, Long no) {
		if (productVo.getCategory_list() != null) {
			for (ProductCategoryVo pcVo : productVo.getCategory_list()) {
				pcVo.setProduct_no(no);
				boolean result = productDao.insertProductCategory(pcVo);
				if (!result)
					return false;
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

		if (productVo.getCategory_list() != null) {
			for (ProductCategoryVo pcVo : productVo.getCategory_list()) {
				if ("insert".equals(pcVo.getFlag())) {
					productDao.insertProductCategory(pcVo);
				} else if ("delete".equals(pcVo.getFlag())) {
					productDao.deleteProductCategory(pcVo.getNo());
				}

			}
		}
		return true;
	}

	// 이미지 insert
	private boolean insertImage(ProductVo productVo, Long no) {
		if (productVo.getImage_list() != null) {
			for (ImageVo image : productVo.getImage_list()) {
				image.setProduct_no(no);
				boolean result = productDao.insertImage(image);
				if (!result)
					return false;
			}
		}
		return true;
	}

	// 이미지 update
	private boolean updateAboutImage(ProductVo productVo, Long no) {
		if (productVo.getImage_list() != null) {
			for (ImageVo image : productVo.getImage_list()) {
				if ("insert".equals(image.getFlag())) {
					image.setProduct_no(no);
					productDao.insertImage(image);
				} else if ("update".equals(image.getFlag())) {
					productDao.updateImage(image);
				} else if ("delete".equals(image.getFlag())) {
					productDao.deleteImageByNo(image.getNo());
				}

			}
		}
		return true;
	}

	// 재고정보
	public ProductVo getStockInfo(Long product_no) {
		ProductVo pvo = productDao.getStockInfo(product_no);
		System.out.println(pvo);
		return pvo;
	}

	// 상품 상세 정보
	public ProductVo getProductInfo(Long product_no) {
		Map<String, Object> data = new HashMap<String, Object>();

		ProductVo pvo = productDao.getProductOne(product_no);
		List<CategoryVo> c_list = getCatetoryInfo(product_no);
		if (c_list.size() > 0) {
			pvo.setCategory_list_with_name(c_list);
		}
		return pvo;
	}

	// 카테고리 이름 만들기
	private List<CategoryVo> getCatetoryInfo(Long product_no) {
		List<CategoryVo> c_list = productDao.getCategoryList(product_no);
		for (CategoryVo c : c_list) {
			System.out.println("c");
			if (c.getUpper_no() != null) {
				c.setCategory_name(c.getUpper_category_name() + " > " + c.getCategory_name());
			}

			while (c.getUpper_no2() != null) {
				CategoryVo upper_c = productDao.getUpperCateogry(c.getUpper_no2());
				c.setCategory_name(upper_c.getCategory_name() + " > " + c.getCategory_name());
				c.setUpper_no2(upper_c.getUpper_no2());
			}
		}
		return c_list;

	}

	// 상품 리스트 가져오기
	public List<ProductVo> getList() {
		return productDao.getList();
	}

	public ProductOptionVo getProductOptionInfo(Long pono) {
		return productDao.getProductOption(pono);
	}

	public List<ProductVo> getListByCategory(Long category_no) {
		List<CategoryVo> c_list = categoryDao.getList();
		List<Long> c_no_list = new ArrayList<Long>();
		c_no_list.add(category_no);
		// 하위 no 찾기
		for (CategoryVo c1 : c_list) {
			if (category_no == c1.getUpper_no()) {
				c_no_list.add(c1.getNo());
				for (CategoryVo c2 : c_list) {
					if (c1.getNo() == c2.getUpper_no()) {
						c_no_list.add(c2.getNo());
						for (CategoryVo c3 : c_list) {
							if (c2.getNo() == c3.getUpper_no()) {
								c_no_list.add(c3.getNo());
							}
						}
					}
				}
			}
		}
		return productDao.getListByCategory(c_no_list);
	}

}
