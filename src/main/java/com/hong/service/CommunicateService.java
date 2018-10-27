/**
 * 
 */
package com.hong.service;

import java.util.List;

import com.hong.bean.Communicate;

/**
 * @author hong
 *
 */
public interface CommunicateService {
	public Communicate getCommunicateById(int id);
	public List<Communicate> getCommunicateList(int currentPage,int pageSize,Communicate communicate);
	public int saveCommunicate(Communicate record);
	public int updateByPrimaryKeySelective(Communicate record);
	int deleteByPrimaryKey(Integer id);
}
