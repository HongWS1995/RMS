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

import com.hong.bean.Category;
import com.hong.bean.PageBean;
import com.hong.bean.User;
import com.hong.service.CategoryService;


@Controller
@RequestMapping("/category")
public class CategoryController {
	public static Logger logger = LoggerFactory.getLogger(CategoryController.class);
	@Autowired
	private CategoryService categoryService;
	@ResponseBody
	@RequestMapping("/findCategoryById")
	public Category findCategoryById(HttpServletRequest request,@RequestParam String id){
		int uid = Integer.parseInt(request.getParameter("id"));
		Category Category = this.categoryService.getCategoryById(uid);
		return Category;
	}
	@ResponseBody
	@RequestMapping("/CategoryList")
	public Map<String, Object> getCategoryList(int currentPage,int pageSize,Category Categoryform ,HttpSession session) {
		Map<String, Object> map = new HashMap<String,Object>();
		int totalRecord = this.categoryService.getCategoryCount(Categoryform);
		PageBean<Category> pageBean = new PageBean<>(currentPage, pageSize, totalRecord);
		List<Category> beanList = this.categoryService.getCategoryList(currentPage, pageSize,Categoryform);
		pageBean.setBeanList(beanList);
		map.put("result", "success");
		map.put("pageBean", pageBean);
		return map;
	}
	
	@ResponseBody
	@RequestMapping("/CategoryProductList")
	public Map<String, Object> getCategoryProduct() {
		Map<String, Object> map = new HashMap<String,Object>();
		List<Category> beanList = this.categoryService.getCategoryProduct();
		map.put("result", "success");
		map.put("beanList", beanList);
		return map;
	}
	
	@ResponseBody
	@RequestMapping("/save")
	public Map<String, Object> saveCategory(Category Category,HttpSession session) {
		User user = (User)session.getAttribute("user");
		Map<String, Object> map = new HashMap<String,Object>();
		if(user!=null) {
			int count = this.categoryService.saveCategory(Category);
			if(count>0)
				map.put("result", "success");
			return map;
		}	
		else
			return map;
	}
	
	@ResponseBody
	@RequestMapping("/update")
	public Map<String, Object> updateCategory(Category Category,HttpSession session) {
		User user = (User)session.getAttribute("user");
		Map<String, Object> map = new HashMap<String,Object>();
		if(user!=null) {
			int count = this.categoryService.updateByPrimaryKeySelective(Category);
			if(count>0)
				map.put("result", "success");
			return map;
		}	
		else
			return map;
	}
	
	@ResponseBody
	@RequestMapping("/delete")
	public Map<String, Object> deleteCategory(int id,HttpSession session) {
		User user = (User)session.getAttribute("user");
		Map<String, Object> map = new HashMap<String,Object>();
		if(user!=null) {
			int count = this.categoryService.deleteByPrimaryKey(id);
			if(count>0)
				map.put("result", "success");
			return map;
		}	
		else
			return map;
	}
	
}