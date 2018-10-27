/**
 * 
 */
package com.hong.service;

import java.util.List;
import com.hong.bean.Product;

/**
 * @author hong
 *
 */
public interface ProductService {
	public Product getProductById(int id);
	public List<Product> getProductList(int currentPage,int pageSize,Product product);
	public int saveProduct(Product record);
	public int updateByPrimaryKeySelective(Product record);
	int deleteByPrimaryKey(Integer id);
	public int selectCount(Product productform);
}
