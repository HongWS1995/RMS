/**
 * 
 */
package com.hong.service;

import java.util.List;

import com.hong.bean.TableR;

/**
 * @author hong
 *
 */
public interface TableRService {
	public TableR getTableRById(int id);
	public List<TableR> getTableRList(int currentPage,int pageSize,TableR TableR);
	public int saveTableR(TableR record);
	public int updateByPrimaryKeySelective(TableR record);
	int deleteByPrimaryKey(Integer id);
	public int getTableRCount(TableR tableRform);
	public List<TableR> getAllTableR();
}
