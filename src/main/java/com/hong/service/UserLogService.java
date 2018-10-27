/**
 * 
 */
package com.hong.service;

import java.util.List;

import com.hong.bean.UserLog;

/**
 * @author hong
 *
 */
public interface UserLogService {
	public UserLog getUserLogById(int id);
	public List<UserLog> getUserLogList(int currentPage,int pageSize,UserLog UserLog);
	public int saveUserLog(UserLog record);
	public int updateByPrimaryKeySelective(UserLog record);
	int deleteByPrimaryKey(Integer id);
}
