package com.hong.service.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hong.bean.UserLog;
import com.hong.dao.UserLogMapper;
import com.hong.service.UserLogService;

/**
 * @author hong
 *
 */
@Service("UserLogService")
public class UserLogServiceImpl implements UserLogService {
	@Autowired
	private  SqlSessionFactory sqlSessionFactory;
	

	@Override
	public UserLog getUserLogById(int id) {
		SqlSession session =  sqlSessionFactory.openSession();
		UserLogMapper mapper = session.getMapper(UserLogMapper.class);
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public List<UserLog> getUserLogList(int currentPage,int pageSize,UserLog UserLog) {
		SqlSession session =  sqlSessionFactory.openSession();
		UserLogMapper mapper = session.getMapper(UserLogMapper.class);
		return null;
//		return mapper.getUserLogList((currentPage-1)*pageSize,pageSize,UserLog);
	}


	@Override
	public int saveUserLog(UserLog UserLog) {
		SqlSession session =  sqlSessionFactory.openSession();
		UserLogMapper mapper = session.getMapper(UserLogMapper.class);
		return mapper.insert(UserLog);
	}

	@Override
	public int updateByPrimaryKeySelective(UserLog UserLog) {
		SqlSession session =  sqlSessionFactory.openSession();
		UserLogMapper mapper = session.getMapper(UserLogMapper.class);
		return mapper.updateByPrimaryKeySelective(UserLog);
	}


	@Override
	public int deleteByPrimaryKey(Integer id) {
		SqlSession session =  sqlSessionFactory.openSession();
		UserLogMapper mapper = session.getMapper(UserLogMapper.class);
		return mapper.deleteByPrimaryKey(id);
	}
}
