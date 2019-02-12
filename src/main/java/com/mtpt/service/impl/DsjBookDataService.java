package com.mtpt.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mtpt.bean.DsjBookData;
import com.mtpt.bean.page.DsjBookPage;
import com.mtpt.dao.DsjBookDataMapper;
import com.mtpt.service.IDsjBookDataService;

@Service("dsjBookDataService")
public class DsjBookDataService implements IDsjBookDataService{

	@Autowired
	DsjBookDataMapper mapper;
	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return mapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(DsjBookData record) {
		// TODO Auto-generated method stub
		return mapper.insert(record);
	}

	@Override
	public int insertSelective(DsjBookData record) {
		// TODO Auto-generated method stub
		return mapper.insertSelective(record);
	}

	@Override
	public DsjBookData selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(DsjBookData record) {
		// TODO Auto-generated method stub
		return mapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(DsjBookData record) {
		// TODO Auto-generated method stub
		return mapper.updateByPrimaryKey(record);
	}

	@Override
	public List<DsjBookData> selectDsjBookDataByPage(DsjBookPage page) {
		// TODO Auto-generated method stub
		return mapper.selectDsjBookDataByPage(page);
	}

	@Override
	public Integer selectDsjBookCountByPage(DsjBookPage page) {
		// TODO Auto-generated method stub
		return mapper.selectDsjBookCountByPage(page);
	}

}
