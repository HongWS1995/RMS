package com.hong.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hong.bean.Orderdetail;
import com.hong.dao.OrderdetailMapper;
import com.hong.service.OrderDetailService;

/**
 * @author hong
 *
 */
@Transactional
@Service("OrderdetailService")
public class OrderDetaliServiceImpl implements OrderDetailService {
	@Autowired
	private OrderdetailMapper mapper;
	

	@Override
	public Orderdetail getOrderDetailById(int id) {
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public List<Orderdetail> getOrderDetailList(int currentPage,int pageSize,Orderdetail Orderdetail) {
		
		
		return null;
//		return mapper.getOrderdetailList((currentPage-1)*pageSize,pageSize,Orderdetail);
	}


	@Override
	public int saveOrderDetail(Orderdetail Orderdetail) {
		
		
		return mapper.insert(Orderdetail);
	}

	@Override
	public int updateByPrimaryKeySelective(Orderdetail Orderdetail) {
		
		
		return mapper.updateByPrimaryKeySelective(Orderdetail);
	}


	@Override
	public int deleteByPrimaryKey(Integer id) {
		
		
		return mapper.deleteByPrimaryKey(id);
	}

	
	@Override
	public int getOrderdetailCount(Orderdetail orderdetailform) {
		return 0;
	}

	
	@Override
	public List<Orderdetail> getOrderdetailProduct() {
		
		return null;
	}
}
