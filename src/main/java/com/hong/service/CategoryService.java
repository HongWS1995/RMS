/**
 * 
 */
package com.hong.service;

import java.util.List;

import com.hong.bean.Category;

/**
 * @author hong
 *
 */
public interface CategoryService {
	public Category getCategoryById(int id);
	public List<Category> getCategoryList(int currentPage,int pageSize,Category Category);
	public int saveCategory(Category record);
	public int updateByPrimaryKeySelective(Category record);
	int deleteByPrimaryKey(Integer id);
	public int getCategoryCount(Category categoryform);
	public List<Category> getCategoryProduct();
}
