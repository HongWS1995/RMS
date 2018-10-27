package com.hong.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hong.bean.Category;
import com.hong.bean.Ptype;

public interface PtypeMapper {

	
	int deleteByPrimaryKey(Integer id);

	int insert(Ptype record);

	int insertSelective(Ptype record);

	
	Ptype selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(Ptype record);

	int updateByPrimaryKey(Ptype record);

	List<Ptype> getPtypeList(@Param("currentPage") int currentPage,@Param("pageSize")int  pageSize, @Param("ptype")Ptype ptype);

	int getPtypeCount(@Param("ptype")Ptype ptypeform);
}