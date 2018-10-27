package com.hong.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hong.bean.Category;

public interface CategoryMapper {

	int deleteByPrimaryKey(Integer id);

	int insert(Category record);

	int insertSelective(Category record);

	Category selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(Category record);

	int updateByPrimaryKey(Category record);

	List<Category> getCategoryList(@Param("currentPage") int currentPage,@Param("pageSize")int  pageSize, @Param("category")Category category);

	List<Category> getAllCategoryProduct();
	int getCategoryCount(@Param("category")Category categoryform);
}