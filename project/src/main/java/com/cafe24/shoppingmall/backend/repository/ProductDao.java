package com.cafe24.shoppingmall.backend.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
		sqlSession.insert("product.product_insert", productVo);
		return productVo.getNo();
	}

	public Long insertOption(OptionVo option) {
		sqlSession.insert("product.option_insert", option);
		return option.getNo();
	}

	public Long insertOptionDetail(OptionDetailVo od) {
		sqlSession.insert("product.option_detail_insert", od);
		return od.getNo();
	}

	public boolean insertProductCategory(ProductCategoryVo pcVo) {
		return 1 == sqlSession.insert("product.product_category_insert",pcVo);
	}

	public boolean insertImage(ImageVo image) {
		return (1 == sqlSession.insert("product.image_insert", image));
	}
	
	
	/* update */
	
	public int updateProduct(ProductVo productVo) {
		int count = sqlSession.update("product.product_update", productVo);
		return count; 
	}
	public int updateOption(OptionVo option) {
		int count = sqlSession.update("product.option_update", option);
		return count;
	}

	public int updateOptionDetail(OptionDetailVo od) {
		int count = sqlSession.update("product.option_detail_update", od);
		return count;
	}

	public int updateImage(ImageVo image) {
		int count = sqlSession.update("product.image_update", image);
		return count;
		
	}
	
	public Long insertProductOption(ProductOptionVo povo) {
		sqlSession.insert("product.product_option_insert", povo);
		return povo.getNo(); 
	}
	
	/* delete */
	public int deleteProductCategory(Long product_no) {
		return sqlSession.insert("product.product_category_delete", product_no);
	}

	public int deleteOptionDetail(Long option_no) {
		return sqlSession.delete("product.delete_option_detail", option_no);
		
	}
	public int deleteOption(Long product_no) {
		return sqlSession.delete("product.delete_option", product_no);
	}
	public int deleteProductOption(Long product_no) {
		return sqlSession.delete("product.delete_product_option", product_no);
		
	}
	public int deleteImage(Long product_no) {
		return sqlSession.delete("product.delete_image", product_no);
	}
	public int deleteProduct(Long product_no) {
		return sqlSession.delete("product.delete_product", product_no);
	}
	
	/*select*/
	
	public List<ProductVo> getList(){
		return sqlSession.selectList("product.getlist");
	}

	//상품지울때 상품에 연결되어 있는 옵션번호 리스트
	public List<Long> getOptionNoByProductNo(Long product_no) {
		return sqlSession.selectList("product.get_option_no_list", product_no);
	}

	

	

	

	

	
}
