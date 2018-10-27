package com.hong.dao;

import com.hong.bean.UserLog;

public interface UserLogMapper {

	int deleteByPrimaryKey(Integer id);

	int insert(UserLog record);

	int insertSelective(UserLog record);

	UserLog selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(UserLog record);

	int updateByPrimaryKey(UserLog record);
}