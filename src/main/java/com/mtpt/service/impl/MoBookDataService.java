package com.mtpt.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mtpt.bean.MoBookData;
import com.mtpt.dao.MoBookDataMapper;
import com.mtpt.service.IMoBookDataService;

@Service("mobookDataService")
public class MoBookDataService implements IMoBookDataService{

	@Autowired
	MoBookDataMapper mapper;
	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return mapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(MoBookData record) {
		// TODO Auto-generated method stub
		return mapper.insert(record);
	}

	@Override
	public int insertSelective(MoBookData record) {
		// TODO Auto-generated method stub
		return mapper.insertSelective(record);
	}

	@Override
	public MoBookData selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(MoBookData record) {
		// TODO Auto-generated method stub
		return mapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(MoBookData record) {
		// TODO Auto-generated method stub
		return mapper.updateByPrimaryKey(record);
	}

	@Override
	public MoBookData selectByPhoneNum(String phone) {
		// TODO Auto-generated method stub
		return mapper.selectByPhoneNum(phone);
	}

}
