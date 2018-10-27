package com.hong.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hong.bean.Order;
import com.hong.bean.Orderdetail;
import com.hong.bean.Orderdetail;

public interface OrderdetailMapper {

	int deleteByPrimaryKey(Integer id);

	int insert(Orderdetail record);

	int insertSelective(Orderdetail record);

	Orderdetail selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(Orderdetail record);

	int updateByPrimaryKey(Orderdetail record);
	
//	List<Orderdetail> getOrderdetailList(@Param("currentPage") int currentPage,@Param("pageSize")int  pageSize, @Param("orderdetail")Orderdetail orderdetail);

	List<Orderdetail> getAllOrderdetail(Order order);
	int getOrderdetailCount(@Param("orderdetail")Orderdetail orderdetailform);
}