package com.hong.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hong.bean.Ptype;
import com.hong.dao.PtypeMapper;
import com.hong.service.PtypeService;

/**
 * @author hong
 *
 */
@Transactional
@Service("PtypeService")
public class PtypeServiceImpl implements PtypeService {
	@Autowired
	private PtypeMapper mapper;

	@Override
	public Ptype getPtypeById(int id) {

		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public List<Ptype> getPtypeList(int currentPage, int pageSize, Ptype Ptype) {

		return mapper.getPtypeList((currentPage - 1) * pageSize, pageSize, Ptype);
	}

	@Override
	public int savePtype(Ptype Ptype) {

		return mapper.insert(Ptype);
	}

	@Override
	public int updateByPrimaryKeySelective(Ptype Ptype) {
		return mapper.updateByPrimaryKeySelective(Ptype);
	}

	@Override
	public int deleteByPrimaryKey(Integer id) {
		return mapper.deleteByPrimaryKey(id);
	}

	@Override
	public int getPtypeCount(Ptype Ptypeform) {
		return mapper.getPtypeCount(Ptypeform);
	}

}
