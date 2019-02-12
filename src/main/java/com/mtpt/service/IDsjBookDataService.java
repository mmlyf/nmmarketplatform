package com.mtpt.service;

import java.util.List;

import com.mtpt.bean.DsjBookData;
import com.mtpt.bean.page.DsjBookPage;

public interface IDsjBookDataService {
	int deleteByPrimaryKey(Integer id);

    int insert(DsjBookData record);

    int insertSelective(DsjBookData record);

    DsjBookData selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DsjBookData record);

    int updateByPrimaryKey(DsjBookData record);
    
    List<DsjBookData> selectDsjBookDataByPage(DsjBookPage page);
    
    Integer selectDsjBookCountByPage(DsjBookPage page);
}
