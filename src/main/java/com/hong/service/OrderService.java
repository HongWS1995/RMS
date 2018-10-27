/**
 * 
 */
package com.hong.service;

import java.util.List;
import java.util.Map;

import com.hong.bean.Order;

/**
 * @author hong
 *
 */
public interface OrderService {
	public Order getOrderById(int id);
	public List<Order> getOrderList(int currentPage,int pageSize,Order order);
	public int saveOrder(Order record);
	public int updateByPrimaryKeySelective(Order record);
	int deleteByPrimaryKey(Integer id);
	public int selectCount(Order orderform);
	public Order getOrderDetailById(int id);
	public List<Map<String,Object>> getOrderStatistic(Map map);
	public List<Map<String,Object>> getOrderdetailStatistic(Map map);
}
