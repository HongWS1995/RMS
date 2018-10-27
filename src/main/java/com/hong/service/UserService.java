/**
 * 
 */
package com.hong.service;

import java.util.List;

import com.hong.bean.User;

/**
 * @author hong
 *
 */
public interface UserService {
	public User getUserById(int id);
	public List<User> getUserList(int currentPage,int pageSize,User user);
	public User validate(String username,String password);
	public int saveUser(User record);
	public int updateByPrimaryKeySelective(User record);
	public int deleteByPrimaryKey(Integer id);
	public int selectCount(User user);
}
