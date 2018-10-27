package com.hong.dao;

import com.hong.bean.Seat;

public interface SeatMapper {

	int deleteByPrimaryKey(Integer id);

	int insert(Seat record);

	int insertSelective(Seat record);

	Seat selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(Seat record);

	int updateByPrimaryKey(Seat record);
}