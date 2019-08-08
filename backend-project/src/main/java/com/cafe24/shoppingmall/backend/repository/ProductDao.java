package com.cafe24.shoppingmall.backend.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.shoppingmall.backend.vo.CategoryVo;
import com.cafe24.shoppingmall.backend.vo.ImageVo;
import com.cafe24.shoppingmall.backend.vo.OptionDetailVo;
import com.cafe24.shoppingmall.backend.vo.OptionVo;
import com.cafe24.shoppingmall.backend.vo.ProductCategoryVo;
import com.cafe24.shoppingmall.backend.vo.ProductOptionVo;
import com.cafe24.shoppingmall.backend.vo.ProductVo;

@Repository
public class ProductDao {

	
	@Autowired
	private SqlSession sqlSession;
	
	
	/* insert */
	
	public Long insertProduct(ProductVo productVo) {
		sqlSession.insert("product_c.insert_product", productVo);
		return productVo.getNo();
	}

	public Long insertOption(OptionVo option) {
		sqlSession.insert("product_c.insert_option", option);
		return option.getNo();
	}

	public Long insertOptionDetail(OptionDetailVo od) {
		sqlSession.insert("product_c.insert_option_detail", od);
		return od.getNo();
	}

	public boolean insertProductCategory(ProductCategoryVo pcVo) {
		return 1 == sqlSession.insert("product_c.insert_product_category",pcVo);
	}

	public boolean insertImage(ImageVo image) {
		return (1 == sqlSession.insert("product_c.insert_image", image));
	}
	
	public Long insertProductOption(ProductOptionVo povo) {
		sqlSession.insert("product_c.insert_product_option", povo);
		return povo.getNo(); 
	}
	
	
	/* update */
	
	public int updateProduct(ProductVo productVo) {
		int count = sqlSession.update("product_ud.update_product", productVo);
		return count; 
	}
	public int updateOption(OptionVo option) {
		int count = sqlSession.update("product_ud.update_option", option);
		return count;
	}

	public int updateOptionDetail(OptionDetailVo od) {
		int count = sqlSession.update("product_ud.update_option_detail", od);
		return count;
	}

	public int updateImage(ImageVo image) {
		int count = sqlSession.update("product_ud.update_image", image);
		return count;
		
	}
	public int updateProductOption(ProductOptionVo povo) {
		return sqlSession.update("product_ud.update_product_option", povo);
	}

	
	
	
	/* delete */
	public int deleteProductCategory(Long product_no) {
		return sqlSession.insert("product_ud.delete_product_category", product_no);
	}

	public int deleteOptionDetailByOptionNo(Long option_no) {
		return sqlSession.delete("product_ud.delete_option_detail", option_no);
		
	}
	public int deleteOptionByProductNo(Long product_no) {
		return sqlSession.delete("product_ud.delete_option", product_no);
	}
	public int deleteOptionByOptionNo(Long product_no) {
		return sqlSession.delete("product_ud.delete_option", product_no);
	}
	public int deleteProductOptionByProductNo(Long product_no) {
		return sqlSession.delete("product_ud.delete_product_option", product_no);
	}
	public int deleteProductOptionByNo(Long po_no) {
		return sqlSession.delete("product_ud.delete_product_option_by_no", po_no);
	}
	public int deleteImageByProductNo(Long product_no) {
		return sqlSession.delete("product_ud.delete_image", product_no);
	}
	public int deleteImageByNo(Long no) {
		return sqlSession.delete("product_ud.delete_image_by_no", no);
	}
	public int deleteProduct(Long product_no) {
		return sqlSession.delete("product_ud.delete_product", product_no);
	}
	
	/*select*/
	
	public List<ProductVo> getList(){
		return sqlSession.selectList("product_r.get_list");
	}

	//상품지울때 상품에 연결되어 있는 옵션번호 리스트 getlist
	public List<Long> getOptionNoByProductNo(Long product_no) {
		return sqlSession.selectList("product_r.get_option_no_list", product_no);
	}
	
	//상품정보 get 
	public ProductVo getProductOne(Long product_no) {
		return sqlSession.selectOne("product_r.get_product_detail_info", product_no);
	}
	
//	//상품옵션 정보 get
//	public List<ProductOptionVo> getProductOption(Long product_no) {
//		return sqlSession.selectList("product_r.get_product_option_list",product_no );
//	}
//
//	public List<OptionVo> getOptionList(Long product_no) {
//		return sqlSession.selectList("product_r.get_option_list",product_no );
//	}
//	
//	public List<OptionDetailVo> getOptionDetailList(Long option_no) {
//		return sqlSession.selectList("product_r.get_option_detail_list", option_no );
//	}
//
//	public List<ImageVo> getImageList(Long product_no) {
//		return sqlSession.selectList("product_r.get_image_list", product_no );
//	}

	public List<CategoryVo> getCategoryList(Long product_no) {
		return sqlSession.selectList("category.get_category_list", product_no );
	}	
	//상위카테고리 하나 가져오기
	public CategoryVo getUpperCateogry(Long upper_no2) {
		return sqlSession.selectOne("category.get_upper_category", upper_no2);
	}

	//재고정보 가져오기
	public ProductVo getStockInfo(Long product_no) {
		return sqlSession.selectOne("product_r.get_product_stock", product_no);
	}

	public ProductOptionVo getProductOption(Long pono) {
		return sqlSession.selectOne("product_r.get_product_option_one", pono);
	}

	public List<ProductVo> getListByCategory(List<Long> c_no_list) {
		Map<String, Object> list = new HashMap<String, Object>();
		list.put("list", c_no_list);
		return sqlSession.selectList("product_r.get_list_by_category", list);
	}

	

	

	

	
}
