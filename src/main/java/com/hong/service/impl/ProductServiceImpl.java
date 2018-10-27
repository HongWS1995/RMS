package com.hong.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hong.bean.Product;
import com.hong.dao.ProductMapper;
import com.hong.service.ProductService;

@Transactional
@Service("ProductService")
public class ProductServiceImpl implements ProductService {
	@Autowired
	private  ProductMapper mapper;
	

	@Override
	public Product getProductById(int id) {
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public List<Product> getProductList(int currentPage,int pageSize,Product Product) {
		return mapper.getProductList((currentPage-1)*pageSize,pageSize,Product);
	}


	@Override
	public int saveProduct(Product Product) {
		return mapper.insert(Product);
	}

	@Override
	public int updateByPrimaryKeySelective(Product Product) {	
		return mapper.updateByPrimaryKeySelective(Product);
	}


	@Override
	public int deleteByPrimaryKey(Integer id) {
		return mapper.deleteByPrimaryKey(id);
	}

	@Override
	public int selectCount(Product product) {
		return mapper.selectCount(product);
	}
}
