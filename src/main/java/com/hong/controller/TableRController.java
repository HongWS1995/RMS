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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hong.bean.TableR;
import com.hong.bean.PageBean;
import com.hong.bean.User;
import com.hong.service.TableRService;


@Controller
@RequestMapping("/tabler")
public class TableRController {
	public static Logger logger = LoggerFactory.getLogger(TableRController.class);
	@Autowired
	private TableRService tablerService;
	@ResponseBody
	@RequestMapping("/findTableRById")
	public TableR findTableRById(HttpServletRequest request,@RequestParam String id){
		int uid = Integer.parseInt(request.getParameter("id"));
		TableR TableR = this.tablerService.getTableRById(uid);
		return TableR;
	}
	@ResponseBody
	@RequestMapping("/TableRList")
	public Map<String, Object> getTableRList(int currentPage,int pageSize,TableR TableRform ,HttpSession session) {
		Map<String, Object> map = new HashMap<String,Object>();
		int totalRecord = this.tablerService.getTableRCount(TableRform);
		PageBean<TableR> pageBean = new PageBean<>(currentPage, pageSize, totalRecord);
		List<TableR> beanList = this.tablerService.getTableRList(currentPage, pageSize,TableRform);
		pageBean.setBeanList(beanList);
		map.put("result", "success");
		map.put("pageBean", pageBean);
		return map;
	}
	
	@ResponseBody
	@RequestMapping("/getAllTableR")
	public Map<String, Object> getTableRProduct() {
		Map<String, Object> map = new HashMap<String,Object>();
		List<TableR> beanList = this.tablerService.getAllTableR();
		map.put("result", "success");
		map.put("beanList", beanList);
		return map;
	}
	
	@ResponseBody
	@RequestMapping("/save")
	public Map<String, Object> saveTableR(TableR TableR,HttpSession session) {
		User user = (User)session.getAttribute("user");
		Map<String, Object> map = new HashMap<String,Object>();
		if(user!=null) {
			int count = this.tablerService.saveTableR(TableR);
			if(count>0)
				map.put("result", "success");
			return map;
		}	
		else
			return map;
	}
	
	@ResponseBody
	@RequestMapping("/update")
	public Map<String, Object> updateTableR(TableR TableR,HttpSession session) {
		User user = (User)session.getAttribute("user");
		Map<String, Object> map = new HashMap<String,Object>();
		if(user!=null) {
			int count = this.tablerService.updateByPrimaryKeySelective(TableR);
			if(count>0)
				map.put("result", "success");
			return map;
		}	
		else
			return map;
	}
	
	@ResponseBody
	@RequestMapping("/delete")
	public Map<String, Object> deleteTableR(int id,HttpSession session) {
		User user = (User)session.getAttribute("user");
		Map<String, Object> map = new HashMap<String,Object>();
		if(user!=null) {
			int count = this.tablerService.deleteByPrimaryKey(id);
			if(count>0)
				map.put("result", "success");
			return map;
		}	
		else
			return map;
	}
	
}