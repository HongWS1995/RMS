package com.hong.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hong.bean.Order;
import com.hong.bean.Orderdetail;
import com.hong.bean.User;
import com.hong.bean.PageBean;
import com.hong.bean.Product;
import com.hong.service.OrderDetailService;
import com.hong.service.OrderService;
import com.hong.service.ProductService;
import com.hong.utils.DateFormats;

@Controller
@RequestMapping("/order")
public class OrderController {
	public static Logger logger = LoggerFactory.getLogger(OrderController.class);
	@Autowired
	private OrderService orderService;
	@Autowired
	private OrderDetailService orderDetailService;
	@Autowired
	private ProductService productService;

	@ResponseBody
	@RequestMapping("/findOrderById")
	public Order findOrderById(HttpServletRequest request, @RequestParam String id) {
		int uid = Integer.parseInt(request.getParameter("id"));
		Order order = this.orderService.getOrderById(uid);
		return order;
	}

	@SuppressWarnings("rawtypes")
	@ResponseBody
	@RequestMapping("/OrderList")
	public Map getOrderList(int currentPage, int pageSize, Order orderform, HttpSession session,
			HttpServletRequest request){
		User user = (User) session.getAttribute("user");
		int totalRecord = this.orderService.selectCount(orderform);
		PageBean<Order> pageBean = new PageBean<Order>(currentPage, pageSize, totalRecord);
		List<Order> orders = this.orderService.getOrderList(currentPage, pageSize, orderform);
		pageBean.setBeanList(orders);
		Map<String, Object> map = new HashMap<String, Object>();
		if (user != null) {
			map.put("result", "success");
			map.put("pageBean", pageBean);
			return map;
		} else {
			map.put("result", "fail");
			return null;
		}

	}
	
	@ResponseBody
	@RequestMapping("/getOrderDetailById")
	public Order getOrderDetailsList(int id, HttpSession session,
			HttpServletRequest request){
		User user = (User) session.getAttribute("user");
		if(user!=null)
			return this.orderService.getOrderDetailById(id);
		return null;
		

	}
	
	@ResponseBody
	@RequestMapping("/getUserOrderDetailById")
	public Order getUserOrderDetailsList( HttpSession session,HttpServletRequest request){
		int orderId = Integer.parseInt((String)session.getAttribute("orderId"));
		return this.orderService.getOrderDetailById(orderId);
		

	}

	@ResponseBody
	@RequestMapping("/calculate")
	public Map<String, Object> calculateOrder(@RequestParam("order") String order,HttpSession session) {
		Order orderform=null;
		ObjectMapper mapper = new ObjectMapper();
		try {
			orderform  = mapper.readValue(order, Order.class);
		} catch (IOException e){
			e.printStackTrace();
		}
		Map<String, Object> map = new HashMap<String,Object>();
		if(orderform.getTableId()!=null) {
				
				int total=0;
				List<Orderdetail> orderdetails = orderform.getOrderdetails();
				for(Orderdetail o:orderdetails) {
					
					Product p = this.productService.getProductById(o.getProductId());
					total +=p.getPrice()*o.getCount();
				}
				map.put("total", total);
				map.put("result","success");
				return map;
		}else
			return map;
	}
	
	@ResponseBody
	@RequestMapping("/save")
	public Map<String, Object> saveOrder(@RequestParam("order") String order,HttpSession session) {
		//User user = (User)session.getAttribute("user");
		System.out.println(order);
		Order orderform=null;
		ObjectMapper mapper = new ObjectMapper();
		try {
			orderform  = mapper.readValue(order, Order.class);
		} catch (IOException e){
			e.printStackTrace();
		}
		Map<String, Object> map = new HashMap<String,Object>();
		if(orderform.getTableId()!=null) {
			int count_1 = this.orderService.saveOrder(orderform);
			if(count_1>0) {
				int orderId = orderform.getId();
				int total=0;
				List<Orderdetail> orderdetails = orderform.getOrderdetails();
				for(Orderdetail o:orderdetails) {
					o.setOrderId(orderId);
					o.setFlag("0");
					Product p = this.productService.getProductById(o.getProductId());
					int count_insert_detail = this.orderDetailService.saveOrderDetail(o);
					if(count_insert_detail>0)
						total +=p.getPrice()*o.getCount();
					else
						map.put("result", "fail");
				}
				orderform.setTotal(total);
				orderform.setCreatetime(DateFormats.format(new Date()));
				orderform.setStatus("已提交");
				
			}
			int count_2 = this.orderService.updateByPrimaryKeySelective(orderform);
			if(count_1 > 0 && count_2 > 0) {
				map.put("result", "success");
				map.put("orderId", orderform.getId());
				session.setAttribute("orderId",String.valueOf(orderform.getId()));
				session.setMaxInactiveInterval(60*60*2);
			}	
		}	
		else
			map.put("result", "fail");
		return map;
	}
	
	@ResponseBody
	@RequestMapping("/update")
	public Map<String, Object> updateOrder(Order orderform,HttpSession session) {
		User user = (User)session.getAttribute("user");
		Map<String, Object> map = new HashMap<String,Object>();
		if(user!=null) {
			int count_2 = this.orderService.updateByPrimaryKeySelective(orderform);
			if(count_2 > 0)
				map.put("result", "success");
		}	
		else {
			map.put("result", "fail");
		}
			return map;
	}
	
	@ResponseBody
	@RequestMapping("/delete")
	public Map<String, Object> deleteOrder(int id,HttpSession session) {
		User user= (User)session.getAttribute("user");
		Map<String, Object> map = new HashMap<String,Object>();
		if(user!=null) {
			Order order = this.orderService.getOrderById(id);
			int count = this.orderService.deleteByPrimaryKey(id);
			if(count>0)
				map.put("result", "success");
			return map;
		}	
		else
			return map;
	}
	
	@ResponseBody
	@RequestMapping("/getOrderStatistic")
	public Map<String, Object> getOrderStatistic(@RequestParam Map<String,String> paramMap,HttpSession session) {
		User user= (User)session.getAttribute("user");
		Map<String, Object> map = new HashMap<String,Object>();
		if(user!=null) {
			List<Map<String,Object>> rs = this.orderService.getOrderStatistic(paramMap);
			List<String> xAxis = new ArrayList<String>();
			List<Long> series_quantity = new ArrayList<Long>();
			List<BigDecimal> series_amount = new ArrayList<BigDecimal>();
			for (Iterator iterator = rs.iterator(); iterator.hasNext();) {
				Map<String,Object> curMap = (Map<String,Object>) iterator.next();
				String period = (String)curMap.get("period_1");
				Long total = (Long)curMap.get("total");
				BigDecimal amount = (BigDecimal)curMap.get("amount");
				xAxis.add(period);
				series_quantity.add(total);
				series_amount.add(amount);
			}
			if(rs.isEmpty())
				map.put("result", "fail");
			else {
				map.put("result", "success");
				map.put("xAxis", xAxis.toArray());
				map.put("series_quantity", series_quantity.toArray());
				map.put("series_amount", series_amount.toArray());
			}
			return map;
		}	
		else {
			map.put("result", "fail");
			return map;
		}
			
	}
	
	@ResponseBody
	@RequestMapping("/getOrderDetailStatistic")
	public Map<String, Object> getOrderDetailStatistic(@RequestParam Map<String,String> paramMap,HttpSession session) {
		User user= (User)session.getAttribute("user");
		Map<String, Object> map = new HashMap<String,Object>();
		if(user!=null) {
			if(paramMap.get("startTime")!=null&&paramMap.get("endTime")!=null&&!"".equals(paramMap.get("startTime"))&&!"".equals(paramMap.get("endTime"))) {
				paramMap.replace("startTime", paramMap.get("startTime").replaceFirst("T"," "));
				paramMap.replace("endTime", paramMap.get("endTime").replaceFirst("T"," "));
			}
			List<Map<String,Object>> rs = this.orderService.getOrderdetailStatistic(paramMap);
			List<String> yAxis = new ArrayList<String>();
			List<BigDecimal> series_quantity = new ArrayList<BigDecimal>();
			for (Iterator iterator = rs.iterator(); iterator.hasNext();) {
				Map<String,Object> curMap = (Map<String,Object>) iterator.next();
				String period = (String)curMap.get("p_name");
				BigDecimal total = (BigDecimal)curMap.get("p_count");
				yAxis.add(period);
				series_quantity.add(total);
			}
			if(rs.isEmpty())
				map.put("result", "fail");
			else {
				map.put("result", "success");
				map.put("yAxis", yAxis.toArray());
				map.put("series_quantity", series_quantity.toArray());
			}
			return map;
		}	
		else {
			map.put("result", "fail");
			return map;
		}
			
	}
	
}