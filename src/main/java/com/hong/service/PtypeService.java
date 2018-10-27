/**
 * 
 */
package com.hong.service;

import java.util.List;

import com.hong.bean.Ptype;

/**
 * @author hong
 *
 */
public interface PtypeService {
	public Ptype getPtypeById(int id);
	public List<Ptype> getPtypeList(int currentPage,int pageSize,Ptype ptype);
	public int savePtype(Ptype record);
	public int updateByPrimaryKeySelective(Ptype record);
	int deleteByPrimaryKey(Integer id);
	public int getPtypeCount(Ptype Ptypeform);
}
