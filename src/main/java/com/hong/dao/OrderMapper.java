package com.hong.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.hong.bean.Order;

public interface OrderMapper {

	int deleteByPrimaryKey(Integer id);

	int insert(Order record);

	int insertSelective(Order record);

	Order selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(Order record);

	int updateByPrimaryKey(Order record);
	
	List<Order> getOrderList(@Param("currentPage") int currentPage,@Param("pageSize")int  pageSize, @Param("order")Order order);

	List<Order> getAllOrderDetail();
	
	Order getOrderDetailById(Integer id);
	
	int getOrderCount(@Param("order")Order orderform);
	
	List<Map<String,Object>> getOrderStatistic(Map map);
	
	List<Map<String,Object>> getOrderDetailStatistic(Map map);
}