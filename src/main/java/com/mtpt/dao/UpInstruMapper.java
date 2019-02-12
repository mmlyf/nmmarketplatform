package com.mtpt.dao;

import java.util.List;

import com.mtpt.bean.UpInstru;

public interface UpInstruMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UpInstru record);

    int insertSelective(UpInstru record);

    UpInstru selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UpInstru record);

    int updateByPrimaryKey(UpInstru record);
    
    List<String> selectAllForInstruct();
}