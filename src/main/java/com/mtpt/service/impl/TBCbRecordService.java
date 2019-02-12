package com.mtpt.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mtpt.bean.TBCBRecord;
import com.mtpt.bean.page.TotalPage;
import com.mtpt.dao.TBCBRecordMapper;
import com.mtpt.service.ITBCbRecordService;
@Service("tbcbrecordservice")
public class TBCbRecordService implements ITBCbRecordService {

	@Autowired
	TBCBRecordMapper mapper;

	@Override
	public int deleteByPrimaryKey(Integer cdId) {
		// TODO Auto-generated method stub
		return mapper.deleteByPrimaryKey(cdId);
	}

	@Override
	public int insert(TBCBRecord record) {
		// TODO Auto-generated method stub
		return mapper.insert(record);
	}

	@Override
	public int insertSelective(TBCBRecord record) {
		// TODO Auto-generated method stub
		return mapper.insertSelective(record);
	}

	@Override
	public TBCBRecord selectByPrimaryKey(Integer cdId) {
		// TODO Auto-generated method stub
		return mapper.selectByPrimaryKey(cdId);
	}

	@Override
	public int updateByPrimaryKeySelective(TBCBRecord record) {
		// TODO Auto-generated method stub
		return mapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(TBCBRecord record) {
		// TODO Auto-generated method stub
		return mapper.updateByPrimaryKey(record);
	}

	@Override
	public Integer selectCountByPage(TotalPage page) {
		// TODO Auto-generated method stub
		return mapper.selectCountByPage(page);
	}

	@Override
	public int insertByList(List<TBCBRecord> list) {
		// TODO Auto-generated method stub
		return mapper.insertByList(list);
	}

	@Override
	public TBCBRecord selectDataForPhone(String phone) {
		// TODO Auto-generated method stub
		return mapper.selectDataForPhone(phone);
	}
}
