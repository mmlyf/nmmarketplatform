package com.mtpt.service;

import com.mtpt.bean.DsjAll;

public interface IDsjAllService {
	int deleteByPrimaryKey(Integer id);

    int insert(DsjAll record);

    int insertSelective(DsjAll record);

    DsjAll selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DsjAll record);

    int updateByPrimaryKey(DsjAll record);
    
    DsjAll selectDataByPhone(String phone);
}
