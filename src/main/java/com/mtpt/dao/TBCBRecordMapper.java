package com.mtpt.dao;

import java.util.List;

import com.mtpt.bean.TBCBRecord;
import com.mtpt.bean.page.TotalPage;

public interface TBCBRecordMapper {
	int deleteByPrimaryKey(Integer cdId);

    int insert(TBCBRecord record);

    int insertSelective(TBCBRecord record);

    TBCBRecord selectByPrimaryKey(Integer cdId);

    int updateByPrimaryKeySelective(TBCBRecord record);

    int updateByPrimaryKey(TBCBRecord record);
    
    Integer selectCountByPage(TotalPage page);
    
    int insertByList(List<TBCBRecord> list);
    
    TBCBRecord selectDataForPhone(String phone);//获取当前号码最近的下行记录
}