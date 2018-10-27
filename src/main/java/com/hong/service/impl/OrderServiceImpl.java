package com.hong.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hong.bean.Order;
import com.hong.dao.OrderMapper;
import com.hong.service.OrderService;

/**
 * @author hong
 *
 */
@Transactional
@Service("OrderService")
public class OrderServiceImpl implements OrderService {
	@Autowired
	private OrderMapper mapper;
	

	@Override
	public Order getOrderById(int id) {
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public List<Order> getOrderList(int currentPage,int pageSize,Order order) {
		return mapper.getOrderList((currentPage-1)*pageSize,pageSize,order);
	}


	@Override
	public int saveOrder(Order order) {
		return mapper.insert(order);
	}

	@Override
	public int updateByPrimaryKeySelective(Order order) {	
		return mapper.updateByPrimaryKeySelective(order);
	}


	@Override
	public int deleteByPrimaryKey(Integer id) {	
		return mapper.deleteByPrimaryKey(id);
	}
	@Override
	public int selectCount(Order orderform) {
		return mapper.getOrderCount(orderform);
	}


	@Override
	public Order getOrderDetailById(int id) {
		return mapper.getOrderDetailById(id);
	}

	@Override
	public List<Map<String, Object>> getOrderStatistic(Map map) {
		return mapper.getOrderStatistic(map);
	}

	
	@Override
	public List<Map<String, Object>> getOrderdetailStatistic(Map map) {
		return mapper.getOrderDetailStatistic(map);
	}
}
