package com.hong.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hong.bean.Orderdetail;
import com.hong.bean.PageBean;
import com.hong.bean.User;
import com.hong.service.OrderDetailService;


@Controller
@RequestMapping("/orderdetail")
public class OrderdetailController {
	public static Logger logger = LoggerFactory.getLogger(OrderdetailController.class);
	@Autowired
	private OrderDetailService orderdetailService;
	@ResponseBody
	@RequestMapping("/findOrderdetailById")
	public Orderdetail findOrderdetailById(HttpServletRequest request,@RequestParam String id){
		int uid = Integer.parseInt(request.getParameter("id"));
		Orderdetail Orderdetail = this.orderdetailService.getOrderDetailById(uid);
		return Orderdetail;
	}
	@ResponseBody
	@RequestMapping("/OrderdetailList")
	public Map<String, Object> getOrderdetailList(int currentPage,int pageSize,Orderdetail Orderdetailform ,HttpSession session) {
		Map<String, Object> map = new HashMap<String,Object>();
		int totalRecord = this.orderdetailService.getOrderdetailCount(Orderdetailform);
		PageBean<Orderdetail> pageBean = new PageBean<>(currentPage, pageSize, totalRecord);
		List<Orderdetail> beanList = this.orderdetailService.getOrderDetailList(currentPage, pageSize,Orderdetailform);
		pageBean.setBeanList(beanList);
		map.put("result", "success");
		map.put("pageBean", pageBean);
		return map;
	}
	
	@ResponseBody
	@RequestMapping("/OrderdetailProductList")
	public Map<String, Object> getOrderdetailProduct() {
		Map<String, Object> map = new HashMap<String,Object>();
		List<Orderdetail> beanList = this.orderdetailService.getOrderdetailProduct();
		map.put("result", "success");
		map.put("beanList", beanList);
		return map;
	}
	
	@ResponseBody
	@RequestMapping("/save")
	public Map<String, Object> saveOrderdetail(Orderdetail Orderdetail,HttpSession session) {
		User user = (User)session.getAttribute("user");
		Map<String, Object> map = new HashMap<String,Object>();
		if(user!=null) {
			int count = this.orderdetailService.saveOrderDetail(Orderdetail);
			if(count>0)
				map.put("result", "success");
			return map;
		}	
		else
			return map;
	}
	
	@ResponseBody
	@RequestMapping("/update")
	public Map<String, Object> updateOrderdetail(Orderdetail Orderdetail,HttpSession session) {
		User user = (User)session.getAttribute("user");
		Map<String, Object> map = new HashMap<String,Object>();
		if(user!=null) {
			int count = this.orderdetailService.updateByPrimaryKeySelective(Orderdetail);
			if(count>0)
				map.put("result", "success");
			return map;
		}	
		else
			return map;
	}
	
	@ResponseBody
	@RequestMapping("/updateBatch")
	public Map<String, Object> updateBatchOrderdetail(@RequestBody Orderdetail[] orderdetails,HttpSession session) {
		User user = (User)session.getAttribute("user");
		Map<String, Object> map = new HashMap<String,Object>();
		int count = 0;
		if(user!=null) {
			for(int i = 0;i < orderdetails.length;i++) {
				 count= this.orderdetailService.updateByPrimaryKeySelective(orderdetails[i]);
			}
			
			if(count>0)
				map.put("result", "success");
			else
				map.put("result", "fail");
			return map;
		}	
		else
			return map;
	}
	
	@ResponseBody
	@RequestMapping("/delete")
	public Map<String, Object> deleteOrderdetail(int id,HttpSession session) {
		User user = (User)session.getAttribute("user");
		Map<String, Object> map = new HashMap<String,Object>();
		if(user!=null) {
			int count = this.orderdetailService.deleteByPrimaryKey(id);
			if(count>0)
				map.put("result", "success");
			return map;
		}	
		else
			return map;
	}
	
}