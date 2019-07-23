package com.cafe24.shoppingmall.backend.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.shoppingmall.backend.vo.CategoryVo;

@Repository
public class CategoryDao {

	@Autowired
	private SqlSession sqlSession;

	public int deleteCategory(Long no) {
		return sqlSession.delete("category.delete", no);
	}

	public int updateCategory(CategoryVo categoryVo) {
		return sqlSession.update("category.update",categoryVo);
	}

	public Long insertCategory(CategoryVo categoryVo) {
		Boolean result = (1==sqlSession.insert("category.insert", categoryVo));
		if(result) {
			return categoryVo.getNo();
		}else {
			return null;
		}
	}

	public List<CategoryVo> getList() {
		return sqlSession.selectList("category.getlist");
	}
}
