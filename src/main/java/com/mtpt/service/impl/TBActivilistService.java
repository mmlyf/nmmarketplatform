package com.mtpt.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mtpt.bean.TBActivilist;
import com.mtpt.bean.page.TBRecordPage;
import com.mtpt.dao.TBActivilistMapper;
import com.mtpt.service.ITBActivilistService;


@Service("activiService")
public class TBActivilistService implements ITBActivilistService{

	@Autowired
	TBActivilistMapper mapper;
	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return mapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(TBActivilist record) {
		// TODO Auto-generated method stub
		return mapper.insert(record);
	}

	@Override
	public int insertSelective(TBActivilist record) {
		// TODO Auto-generated method stub
		return mapper.insertSelective(record);
	}

	@Override
	public TBActivilist selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(TBActivilist record) {
		// TODO Auto-generated method stub
		return mapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(TBActivilist record) {
		// TODO Auto-generated method stub
		return mapper.updateByPrimaryKey(record);
	}

	@Override
	public List<TBActivilist> selectActiByPage(TBRecordPage page) {
		// TODO Auto-generated method stub
		return mapper.selectActiByPage(page);
	}

	@Override
	public Integer selectActiCount(TBRecordPage page) {
		// TODO Auto-generated method stub
		return mapper.selectActiCount(page);
	}
}
