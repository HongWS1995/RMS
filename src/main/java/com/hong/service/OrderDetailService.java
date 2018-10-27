/**
 * 
 */
package com.hong.service;

import java.util.List;

import com.hong.bean.Orderdetail;

/**
 * @author hong
 *
 */
public interface OrderDetailService {
	public Orderdetail getOrderDetailById(int id);
	public List<Orderdetail> getOrderDetailList(int currentPage,int pageSize,Orderdetail orderDetail);
	public int saveOrderDetail(Orderdetail record);
	public int updateByPrimaryKeySelective(Orderdetail record);
	int deleteByPrimaryKey(Integer id);
	public int getOrderdetailCount(Orderdetail orderdetailform);
	public List<Orderdetail> getOrderdetailProduct();
}
