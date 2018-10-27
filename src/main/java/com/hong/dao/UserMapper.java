package com.hong.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.hong.bean.User;

public interface UserMapper {

	int deleteByPrimaryKey(Integer id);

	int insert(User record);
	public List<User> getUserList(@Param("currentPage") int currentPage,@Param("pageSize") int pageSize,@Param("user")User user);
	int insertSelective(User record);
	int selectCount(@Param("user") User user);
	User selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(User record);

	int updateByPrimaryKey(User record);

	User getUserByNamePassword(@Param("username")String username, @Param("password")String password);
}