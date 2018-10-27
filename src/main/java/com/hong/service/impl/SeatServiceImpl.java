package com.hong.service.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hong.bean.Seat;
import com.hong.dao.SeatMapper;
import com.hong.service.SeatService;

/**
 * @author hong
 *
 */
@Service("SeatService")
public class SeatServiceImpl implements SeatService {
	@Autowired
	private  SqlSessionFactory sqlSessionFactory;
	

	@Override
	public Seat getSeatById(int id) {
		SqlSession session =  sqlSessionFactory.openSession();
		SeatMapper mapper = session.getMapper(SeatMapper.class);
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public List<Seat> getSeatList(int currentPage,int pageSize,Seat Seat) {
		SqlSession session =  sqlSessionFactory.openSession();
		SeatMapper mapper = session.getMapper(SeatMapper.class);
		return null;
//		return mapper.getSeatList((currentPage-1)*pageSize,pageSize,Seat);
	}


	@Override
	public int saveSeat(Seat Seat) {
		SqlSession session =  sqlSessionFactory.openSession();
		SeatMapper mapper = session.getMapper(SeatMapper.class);
		return mapper.insert(Seat);
	}

	@Override
	public int updateByPrimaryKeySelective(Seat Seat) {
		SqlSession session =  sqlSessionFactory.openSession();
		SeatMapper mapper = session.getMapper(SeatMapper.class);
		return mapper.updateByPrimaryKeySelective(Seat);
	}


	@Override
	public int deleteByPrimaryKey(Integer id) {
		SqlSession session =  sqlSessionFactory.openSession();
		SeatMapper mapper = session.getMapper(SeatMapper.class);
		return mapper.deleteByPrimaryKey(id);
	}
}
