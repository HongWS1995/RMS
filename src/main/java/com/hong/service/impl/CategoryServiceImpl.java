package com.hong.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hong.bean.Category;
import com.hong.dao.CategoryMapper;
import com.hong.service.CategoryService;

/**
 * @author hong
 *
 */
@Transactional
@Service("CategoryService")
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	private CategoryMapper mapper;
	

	@Override
	public Category getCategoryById(int id) {
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public List<Category> getCategoryList(int currentPage,int pageSize,Category Category) {
		return mapper.getCategoryList((currentPage-1)*pageSize,pageSize,Category);
	}


	@Override
	public int saveCategory(Category Category) {
		return mapper.insert(Category);
	}

	@Override
	public int updateByPrimaryKeySelective(Category Category) {
		return mapper.updateByPrimaryKeySelective(Category);
	}


	@Override
	public int deleteByPrimaryKey(Integer id) {
		return mapper.deleteByPrimaryKey(id);
	}

	
	@Override
	public int getCategoryCount(Category categoryform) {
		return mapper.getCategoryCount(categoryform);
	}

	@Override
	public List<Category> getCategoryProduct() {
		return mapper.getAllCategoryProduct();
	}
	
	
}
