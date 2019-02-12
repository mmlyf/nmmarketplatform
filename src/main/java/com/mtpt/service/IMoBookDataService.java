package com.mtpt.service;

import java.util.List;

import com.mtpt.bean.MoBookData;

public interface IMoBookDataService {
	int deleteByPrimaryKey(Integer id);

    int insert(MoBookData record);

    int insertSelective(MoBookData record);

    MoBookData selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MoBookData record);

    int updateByPrimaryKey(MoBookData record);
    
    MoBookData selectByPhoneNum(String phone);
}
