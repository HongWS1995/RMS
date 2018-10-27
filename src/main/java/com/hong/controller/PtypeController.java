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

import com.hong.bean.Ptype;
import com.hong.bean.PageBean;
import com.hong.bean.User;
import com.hong.service.PtypeService;


@Controller
@RequestMapping("/ptype")
public class PtypeController {
	public static Logger logger = LoggerFactory.getLogger(PtypeController.class);
	@Autowired
	private PtypeService ptypeService;
	@ResponseBody
	@RequestMapping("/findPtypeById")
	public Ptype findPtypeById(HttpServletRequest request,@RequestParam String id){
		int uid = Integer.parseInt(request.getParameter("id"));
		Ptype Ptype = this.ptypeService.getPtypeById(uid);
		return Ptype;
	}
	@ResponseBody
	@RequestMapping("/PtypeList")
	public Map<String, Object> getPtypeList(int currentPage,int pageSize,Ptype Ptypeform ,HttpSession session) {
		User user = (User)session.getAttribute("user");
		Map<String, Object> map = new HashMap<String,Object>();
		if(user!=null) {
			int totalRecord = this.ptypeService.getPtypeCount(Ptypeform);
			PageBean<Ptype> pageBean = new PageBean<>(currentPage, pageSize, totalRecord);
			List<Ptype> beanList = this.ptypeService.getPtypeList(currentPage, pageSize,Ptypeform);
			pageBean.setBeanList(beanList);
			map.put("result", "success");
			map.put("pageBean", pageBean);
			return map;
		}	
		else
			return map;
	}
	
	@ResponseBody
	@RequestMapping("/save")
	public Map<String, Object> savePtype(Ptype Ptype,HttpSession session) {
		User user = (User)session.getAttribute("user");
		Map<String, Object> map = new HashMap<String,Object>();
		if(user!=null) {
			int count = this.ptypeService.savePtype(Ptype);
			if(count>0)
				map.put("result", "success");
			return map;
		}	
		else
			return map;
	}
	
	@ResponseBody
	@RequestMapping("/update")
	public Map<String, Object> updatePtype(Ptype Ptype,HttpSession session) {
		User user = (User)session.getAttribute("user");
		Map<String, Object> map = new HashMap<String,Object>();
		if(user!=null) {
			int count = this.ptypeService.updateByPrimaryKeySelective(Ptype);
			if(count>0)
				map.put("result", "success");
			return map;
		}	
		else
			return map;
	}
	
	@ResponseBody
	@RequestMapping("/delete")
	public Map<String, Object> deletePtype(int id,HttpSession session) {
		User user = (User)session.getAttribute("user");
		Map<String, Object> map = new HashMap<String,Object>();
		if(user!=null) {
			int count = this.ptypeService.deleteByPrimaryKey(id);
			if(count>0)
				map.put("result", "success");
			return map;
		}	
		else
			return map;
	}
	
}