/**
 * 
 */
package com.hong.service;

import java.util.List;

import com.hong.bean.Seat;

/**
 * @author hong
 *
 */
public interface SeatService {
	public Seat getSeatById(int id);
	public List<Seat> getSeatList(int currentPage,int pageSize,Seat seat);
	public int saveSeat(Seat record);
	public int updateByPrimaryKeySelective(Seat record);
	int deleteByPrimaryKey(Integer id);
}
