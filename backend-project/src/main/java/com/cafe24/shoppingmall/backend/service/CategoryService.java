package com.cafe24.shoppingmall.backend.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cafe24.shoppingmall.backend.repository.CategoryDao;
import com.cafe24.shoppingmall.backend.vo.CategoryVo;

@Service
public class CategoryService {

	@Autowired
	private CategoryDao categoryDao;
	public List<CategoryVo> getList() {
		
		return categoryDao.getList();
	}
	
	@Transactional
	public Long addCategory(CategoryVo categoryVo) {
		Long upper_no = categoryDao.insertCategory(categoryVo);
		List<CategoryVo> sub_category_list = categoryVo.getSub_categories();
		if(sub_category_list != null && !sub_category_list.isEmpty()) {
			insertSubCategories(sub_category_list, upper_no);
		}
		return upper_no;
	}
	
	private int insertSubCategories(List<CategoryVo> sub_category_list, Long upper_no) {
		int count=0;
		for(CategoryVo c : sub_category_list) {
			c.setUpper_no(upper_no);
			Long insert_no = categoryDao.insertCategory(c);
			if(insert_no != null) count++;
			if(c.getSub_categories() != null && !c.getSub_categories().isEmpty()) {
				count += insertSubCategories(c.getSub_categories(), insert_no);
			}
		}
		
		return count;
	}
	
	
	public Boolean updateCategory(CategoryVo categoryVo) {
		categoryDao.updateCategory(categoryVo);
		List<CategoryVo> sub_category_list = categoryVo.getSub_categories();
		if(sub_category_list != null && !sub_category_list.isEmpty()) {
			updateSubCategories(sub_category_list, categoryVo.getNo());
		}
		return true;
	}
	
	private Boolean updateSubCategories(List<CategoryVo> sub_category_list, Long upper_no) {
		for(CategoryVo c : sub_category_list) {
			if("insert".contentEquals(c.getFlag())) {
				c.setUpper_no(upper_no);
				Long insert_no = categoryDao.insertCategory(c);
				c.setNo(insert_no);
			} else if("update".contentEquals(c.getFlag())) {
				categoryDao.updateCategory(c);
			} else if("delete".contentEquals(c.getFlag())) {
				categoryDao.deleteCategory(c.getNo());
			}
			
			if(c.getSub_categories() != null && !c.getSub_categories().isEmpty()) {
				updateSubCategories(c.getSub_categories(), c.getNo());
			}
		}
		
		return true;
	}
	

	public Boolean deleteCategory(Long no) {
		
		return 1 == categoryDao.deleteCategory(no);
	}

}
