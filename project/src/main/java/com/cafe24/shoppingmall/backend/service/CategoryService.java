package com.cafe24.shoppingmall.backend.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.shoppingmall.backend.repository.CategoryDao;
import com.cafe24.shoppingmall.backend.vo.CategoryVo;

@Service
public class CategoryService {

	@Autowired
	private CategoryDao categoryDao;
	public List<CategoryVo> getList() {
		
		return categoryDao.getList();
	}
	
	
	public Long addCategory(CategoryVo categoryVo) {
		return categoryDao.insertCategory(categoryVo);
	}
	
	
	public Boolean updateCategory(CategoryVo categoryVo) {
		return 1 == categoryDao.updateCategory(categoryVo);
	}


	public Boolean deleteCategory(Long no) {
		return 1 == categoryDao.deleteCategory(no);
	}

}
