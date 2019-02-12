package com.mtpt.service;

import java.util.List;

import com.mtpt.bean.TBRecord;
import com.mtpt.bean.page.TBRecordPage;

public interface ITBRecordService {
	int deleteByPrimaryKey(Integer id);

    int insert(TBRecord record);

    int insertSelective(TBRecord record);

    TBRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TBRecord record);

    int updateByPrimaryKey(TBRecord record);
    
    List<TBRecord> selectByRecordPage(TBRecordPage page);
    
    Integer selectAllCount(TBRecordPage page);
    
    List<TBRecord> selectTaskByAddTime(TBRecordPage page);
}
