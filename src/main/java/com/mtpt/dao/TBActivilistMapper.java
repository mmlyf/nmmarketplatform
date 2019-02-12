package com.mtpt.dao;

import java.util.List;

import com.mtpt.bean.TBActivilist;
import com.mtpt.bean.page.TBRecordPage;

public interface TBActivilistMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TBActivilist record);

    int insertSelective(TBActivilist record);

    TBActivilist selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TBActivilist record);

    int updateByPrimaryKey(TBActivilist record);
    
    List<TBActivilist> selectActiByPage(TBRecordPage page);
    
    Integer selectActiCount(TBRecordPage page);
}