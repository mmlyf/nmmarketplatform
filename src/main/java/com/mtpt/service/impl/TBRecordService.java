package com.mtpt.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mtpt.bean.TBRecord;
import com.mtpt.bean.page.TBRecordPage;
import com.mtpt.dao.TBRecordMapper;
import com.mtpt.service.ITBRecordService;
@Service("tbrecord")
public class TBRecordService implements ITBRecordService{
	@Autowired
	TBRecordMapper mapper;

	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return mapper.deleteByPrimaryKey(id);
	}

	public int insert(com.mtpt.bean.TBRecord record) {
		// TODO Auto-generated method stub
		return mapper.insert(record);
	}

	public int insertSelective(com.mtpt.bean.TBRecord record) {
		// TODO Auto-generated method stub
		return mapper.insertSelective(record);
	}

	public com.mtpt.bean.TBRecord selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return mapper.selectByPrimaryKey(id);
	}

	public int updateByPrimaryKeySelective(com.mtpt.bean.TBRecord record) {
		// TODO Auto-generated method stub
		return mapper.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(com.mtpt.bean.TBRecord record) {
		// TODO Auto-generated method stub
		return mapper.updateByPrimaryKey(record);
	}

	public List<TBRecord> selectByRecordPage(TBRecordPage page) {
		// TODO Auto-generated method stub
		return mapper.selectByRecordPage(page);
	}

	public Integer selectAllCount(TBRecordPage page) {
		// TODO Auto-generated method stub
		return mapper.selectAllCount(page);
	}

	@Override
	public List<TBRecord> selectTaskByAddTime(TBRecordPage page) {
		// TODO Auto-generated method stub
		return mapper.selectTaskByAddTime(page);
	}	
}
