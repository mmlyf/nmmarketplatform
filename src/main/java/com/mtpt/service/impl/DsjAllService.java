package com.mtpt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mtpt.bean.DsjAll;
import com.mtpt.dao.DsjAllMapper;
import com.mtpt.service.IDsjAllService;

@Service("dsjallService")
public class DsjAllService implements IDsjAllService{

	@Autowired
	DsjAllMapper mapper;
	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return mapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(DsjAll record) {
		// TODO Auto-generated method stub
		return mapper.insert(record);
	}

	@Override
	public int insertSelective(DsjAll record) {
		// TODO Auto-generated method stub
		return mapper.insertSelective(record);
	}

	@Override
	public DsjAll selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(DsjAll record) {
		// TODO Auto-generated method stub
		return mapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(DsjAll record) {
		// TODO Auto-generated method stub
		return mapper.updateByPrimaryKey(record);
	}

	@Override
	public DsjAll selectDataByPhone(String phone) {
		// TODO Auto-generated method stub
		return mapper.selectDataByPhone(phone);
	}

}
