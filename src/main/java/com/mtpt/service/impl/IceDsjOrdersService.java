package com.mtpt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mtpt.bean.IceDsjOrders;
import com.mtpt.bean.page.TotalPage;
import com.mtpt.dao.IceDsjOrdersMapper;
import com.mtpt.service.IIceDsjOrdersService;
@Service("icedsjorderService")
public class IceDsjOrdersService implements IIceDsjOrdersService{
	
	@Autowired
	IceDsjOrdersMapper mapper;

	@Override
	public int deleteByPrimaryKey(Integer dxId) {
		// TODO Auto-generated method stub
		return mapper.deleteByPrimaryKey(dxId);
	}

	@Override
	public int insert(IceDsjOrders record) {
		// TODO Auto-generated method stub
		return mapper.insert(record);
	}

	@Override
	public int insertSelective(IceDsjOrders record) {
		// TODO Auto-generated method stub
		return mapper.insertSelective(record);
	}

	@Override
	public IceDsjOrders selectByPrimaryKey(Integer dxId) {
		// TODO Auto-generated method stub
		return mapper.selectByPrimaryKey(dxId);
	}

	@Override
	public int updateByPrimaryKeySelective(IceDsjOrders record) {
		// TODO Auto-generated method stub
		return mapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(IceDsjOrders record) {
		// TODO Auto-generated method stub
		return mapper.updateByPrimaryKey(record);
	}

	@Override
	public Integer selectCountByPage(TotalPage page) {
		// TODO Auto-generated method stub
		return mapper.selectCountByPage(page);
	}

}
