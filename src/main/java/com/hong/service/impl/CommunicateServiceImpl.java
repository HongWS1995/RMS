package com.hong.service.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hong.bean.Communicate;
import com.hong.dao.CommunicateMapper;
import com.hong.service.CommunicateService;

/**
 * @author hong
 *
 */
@Service("CommunicateService")
public class CommunicateServiceImpl implements CommunicateService {
	@Autowired
	private  SqlSessionFactory sqlSessionFactory;
	

	@Override
	public Communicate getCommunicateById(int id) {
		SqlSession session =  sqlSessionFactory.openSession();
		CommunicateMapper mapper = session.getMapper(CommunicateMapper.class);
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public List<Communicate> getCommunicateList(int currentPage,int pageSize,Communicate Communicate) {
		SqlSession session =  sqlSessionFactory.openSession();
		CommunicateMapper mapper = session.getMapper(CommunicateMapper.class);
		return null;
//		return mapper.getCommunicateList((currentPage-1)*pageSize,pageSize,Communicate);
	}


	@Override
	public int saveCommunicate(Communicate Communicate) {
		SqlSession session =  sqlSessionFactory.openSession();
		CommunicateMapper mapper = session.getMapper(CommunicateMapper.class);
		return mapper.insert(Communicate);
	}

	@Override
	public int updateByPrimaryKeySelective(Communicate Communicate) {
		SqlSession session =  sqlSessionFactory.openSession();
		CommunicateMapper mapper = session.getMapper(CommunicateMapper.class);
		return mapper.updateByPrimaryKeySelective(Communicate);
	}


	@Override
	public int deleteByPrimaryKey(Integer id) {
		SqlSession session =  sqlSessionFactory.openSession();
		CommunicateMapper mapper = session.getMapper(CommunicateMapper.class);
		return mapper.deleteByPrimaryKey(id);
	}
}
