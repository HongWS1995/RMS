package com.hong.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.hong.bean.Product;
import com.hong.bean.User;
import com.hong.bean.PageBean;
import com.hong.service.ProductService;

@Controller
@RequestMapping("/product")
public class ProductController {
	public static Logger logger = LoggerFactory.getLogger(ProductController.class);
	@Autowired
	private ProductService productService;

	@ResponseBody
	@RequestMapping("/findProductById")
	public Product findProductById(HttpServletRequest request, @RequestParam String id) {
		int uid = Integer.parseInt(request.getParameter("id"));
		Product product = this.productService.getProductById(uid);
		return product;
	}

	@SuppressWarnings("rawtypes")
	@ResponseBody
	@RequestMapping("/ProductList")
	public Map getProductList(int currentPage, int pageSize, Product productform, HttpSession session) {
		User user = (User) session.getAttribute("user");
		int totalRecord = this.productService.selectCount(productform);
		PageBean<Product> pageBean = new PageBean<Product>(currentPage, pageSize, totalRecord);
		List<Product> products = this.productService.getProductList(currentPage, pageSize, productform);
		pageBean.setBeanList(products);
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
	@RequestMapping("/save")
	public Map<String, Object> saveProduct(Product productform, HttpSession session,
			MultipartHttpServletRequest request) {
		User user = (User) session.getAttribute("user");
		Map<String, Object> map = new HashMap<String, Object>();
		if (user != null) {
			int count_1 = this.productService.saveProduct(productform);
			MultipartFile file = request.getFile("file");
			if (!file.isEmpty()) {
				String contextPath = session.getServletContext().getRealPath("/");
				String imagepath = "image/" + productform.getId() + "."
						+ file.getOriginalFilename().replaceFirst("[\\s\\S]*\\.", "");
				String imageRealPath = contextPath + imagepath;
				String systemFilePath = "C:/Users/hongw/git/RMS/WebContent/"+imagepath;
				File imagePath = new File(imageRealPath);
				File systemFile = new File(systemFilePath);
				InputStream is = null;
				OutputStream os = null;
				OutputStream devos = null;
				try {
					is = file.getInputStream();
					os = new FileOutputStream(imagePath);
					devos = new FileOutputStream(systemFile);
					byte[] buffer = new byte[2048];
					while(is.read(buffer)!=-1) {
						os.write(buffer);
						devos.write(buffer);
					}
				} catch (IllegalStateException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}finally {
					if(is!=null) {
						try {
							is.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					if(os!=null) {
						try {
							os.flush();
							os.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					if(devos!=null) {
						try {
							devos.flush();
							devos.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
				productform.setImagepath(imagepath);
				int count_2 = this.productService.updateByPrimaryKeySelective(productform);
				if (count_1 > 0 && count_2 > 0)
					map.put("result", "success");
			}else {
				if (count_1 > 0)
					map.put("result", "success");
			}
		} else
			map.put("result", "fail");
		return map;
	}

	@ResponseBody
	@RequestMapping("/update")
	public Map<String, Object> updateProduct(Product productform, HttpSession session,
			MultipartHttpServletRequest request) {
		User user = (User) session.getAttribute("user");
		Map<String, Object> map = new HashMap<String, Object>();
		if (user != null) {
			MultipartFile file = request.getFile("file");
			if (!file.isEmpty()) {
				String contextPath = session.getServletContext().getRealPath("/");
				String imagepath = "image/" + productform.getId() + "."
						+ file.getOriginalFilename().replaceFirst("[\\s\\S]*\\.", "");
				String imageRealPath = contextPath + imagepath;
				String systemFilePath = "C:/Users/hongw/git/RMS/WebContent/"+imagepath;
				File imagePath = new File(imageRealPath);
				File systemFile = new File(systemFilePath);
				InputStream is = null;
				OutputStream os = null;
				OutputStream devos = null;
				try {
					is = file.getInputStream();
					os = new FileOutputStream(imagePath);
					devos = new FileOutputStream(systemFile);
					byte[] buffer = new byte[2048];
					while(is.read(buffer)!=-1) {
						os.write(buffer);
						devos.write(buffer);
					}
				} catch (IllegalStateException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}finally {
					if(is!=null) {
						try {
							is.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					if(os!=null) {
						try {
							os.flush();
							os.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					if(devos!=null) {
						try {
							devos.flush();
							devos.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
				productform.setImagepath(imagepath);
			}
			int count_2 = this.productService.updateByPrimaryKeySelective(productform);
			if (count_2 > 0)
				map.put("result", "success");
		} else
			map.put("result", "fail");
		return map;
	}

	@ResponseBody
	@RequestMapping("/delete")
	public Map<String, Object> deleteProduct(int id, HttpSession session) {
		User user = (User) session.getAttribute("user");
		Map<String, Object> map = new HashMap<String, Object>();
		if (user != null) {
			Product product = this.productService.getProductById(id);
			String contextPath = session.getServletContext().getRealPath("/");
			String imageRealPath = contextPath + product.getImagepath();
			File file = new File(imageRealPath);
			if (file.exists() && !file.isDirectory()) {
				file.delete();
			}
			int count = this.productService.deleteByPrimaryKey(id);
			if (count > 0)
				map.put("result", "success");
		} else
			map.put("result", "fail");
		return map;
	}
}