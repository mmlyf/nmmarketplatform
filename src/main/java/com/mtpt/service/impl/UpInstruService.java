package com.mtpt.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mtpt.bean.UpInstru;
import com.mtpt.dao.UpInstruMapper;
import com.mtpt.service.IUpInstruService;

@Service("upInstruService")
public class UpInstruService implements IUpInstruService{

	@Autowired
	UpInstruMapper mapper;
	
	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return mapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(UpInstru record) {
		// TODO Auto-generated method stub
		return mapper.insert(record);
	}

	@Override
	public int insertSelective(UpInstru record) {
		// TODO Auto-generated method stub
		return mapper.insertSelective(record);
	}

	@Override
	public UpInstru selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(UpInstru record) {
		// TODO Auto-generated method stub
		return mapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(UpInstru record) {
		// TODO Auto-generated method stub
		return mapper.updateByPrimaryKey(record);
	}

	@Override
	public List<String> selectAllForInstruct() {
		// TODO Auto-generated method stub
		return mapper.selectAllForInstruct();
	}

}
