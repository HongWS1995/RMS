package com.hong.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.hong.bean.User;
import com.hong.dao.UserMapper;
import com.hong.service.UserService;

/**
 * @author hong
 *
 */
@Transactional(timeout = 2000, isolation = Isolation.READ_COMMITTED)
@Service("UserService")
public class UserServiceImpl implements UserService {
	@Autowired
	private UserMapper mapper;

	public User validate(String username, String password) {

		return mapper.getUserByNamePassword(username, password);

	}

	@Override
	public User getUserById(int id) {

		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public List<User> getUserList(int currentPage, int pageSize, User user) {

		return mapper.getUserList((currentPage - 1) * pageSize, pageSize, user);
	}

	@Override
	public int saveUser(User user) {

		return mapper.insert(user);
	}

	@Override
	public int updateByPrimaryKeySelective(User user) {

		return mapper.updateByPrimaryKeySelective(user);
	}

	@Override
	public int deleteByPrimaryKey(Integer id) {

		return mapper.deleteByPrimaryKey(id);
	}

	@Override
	public int selectCount(User user) {

		return mapper.selectCount(user);
	}
}
