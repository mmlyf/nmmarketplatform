package com.mtpt.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mtpt.bean.TBReview;
import com.mtpt.bean.page.TBRecordPage;
import com.mtpt.bean.page.TBReviewPage;
import com.mtpt.dao.TBReviewMapper;
import com.mtpt.service.ITBReviewService;
@Service("reservice")
public class TBReviewService implements ITBReviewService {
	
	@Autowired
	TBReviewMapper mapper;

	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return mapper.deleteByPrimaryKey(id);
	}

	public int insert(TBReview record) {
		// TODO Auto-generated method stub
		return mapper.insert(record);
	}

	public int insertSelective(TBReview record) {
		// TODO Auto-generated method stub
		return mapper.insertSelective(record);
	}

	public TBReview selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return mapper.selectByPrimaryKey(id);
	}

	public int updateByPrimaryKeySelective(TBReview record) {
		// TODO Auto-generated method stub
		return mapper.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(TBReview record) {
		// TODO Auto-generated method stub
		return mapper.updateByPrimaryKey(record);
	}

	public List<TBReview> selectByReviewPage(TBRecordPage page) {
		// TODO Auto-generated method stub
		return mapper.selectByReviewPage(page);
	}

	public Integer selectCountAll(TBRecordPage page) {
		// TODO Auto-generated method stub
		return mapper.selectCountAll(page);
	}

	@Override
	public List<TBReview> selectTaskByAddTime(TBRecordPage page) {
		// TODO Auto-generated method stub
		return mapper.selectTaskByAddTime(page);
	}


}
