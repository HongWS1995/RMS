package com.hong.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hong.bean.Product;

public interface ProductMapper {

	
	int deleteByPrimaryKey(Integer id);

	
	int insert(Product record);

	
	int insertSelective(Product record);

	
	Product selectByPrimaryKey(Integer id);

	
	int updateByPrimaryKeySelective(Product record);

	
	int updateByPrimaryKey(Product record);

	List<Product> getProductList(@Param("currentPage") int currentPage,@Param("pageSize") int pageSize,@Param("product")Product product);

	int selectCount(@Param("product")Product product);
}