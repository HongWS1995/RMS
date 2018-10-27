package com.hong.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hong.bean.TableR;

public interface TableRMapper {

	int deleteByPrimaryKey(Integer id);

	int insert(TableR record);

	int insertSelective(TableR record);

	TableR selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(TableR record);

	int updateByPrimaryKey(TableR record);
	List<TableR> getTableRList(@Param("currentPage") int currentPage,@Param("pageSize")int  pageSize, @Param("tabler")TableR tabler);

	List<TableR> getAllTableR();
	int getTableRCount(@Param("tabler")TableR tablerform);
}