package com.hong.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hong.bean.TableR;
import com.hong.dao.TableRMapper;
import com.hong.service.TableRService;

@Transactional
@Service("TableRService")
public class TableRServiceImpl implements TableRService {
	@Autowired
	private TableRMapper mapper;

	@Override
	public TableR getTableRById(int id) {

		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public List<TableR> getTableRList(int currentPage, int pageSize, TableR TableR) {

		return mapper.getTableRList((currentPage - 1) * pageSize, pageSize, TableR);
	}

	@Override
	public int saveTableR(TableR TableR) {

		return mapper.insert(TableR);
	}

	@Override
	public int updateByPrimaryKeySelective(TableR TableR) {

		return mapper.updateByPrimaryKeySelective(TableR);
	}

	@Override
	public int deleteByPrimaryKey(Integer id) {

		return mapper.deleteByPrimaryKey(id);
	}

	@Override
	public int getTableRCount(TableR tableRform) {

		return mapper.getTableRCount(tableRform);
	}

	@Override
	public List<TableR> getAllTableR() {

		return mapper.getAllTableR();
	}
}
