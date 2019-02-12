package com.mtpt.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mtpt.bean.RepeatOpera;
import com.mtpt.bean.Review;
import com.mtpt.dao.ProduceMapper;
import com.mtpt.service.IProduceService;

import sun.util.logging.resources.logging;
@Service("pservice")
public class ProduceService implements IProduceService{
	
	
	@Autowired
	ProduceMapper mapper;

	public Integer selectModelCount(Review review) {
		// TODO Auto-generated method stub
		System.out.println(review.getCity()+","+review.getDangw()+","+review.getDbname()+","+ 
				","+review.getProduct()+
				","+review.getSecTime());
		return mapper.selectModelCount(review);
	}

	public List<String> selectThreeday(RepeatOpera repeatOpera) {
		// TODO Auto-generated method stub
		return mapper.selectThreeday(repeatOpera);
	}


	@Override
	public Integer selectDxCount(String phonenum) {
		// TODO Auto-generated method stub
		return mapper.selectDxCount(phonenum);
	}

	@Override
	public Integer selectIceCount(String phonenum) {
		// TODO Auto-generated method stub
		return mapper.selectDxCount(phonenum);
	}
	

}
