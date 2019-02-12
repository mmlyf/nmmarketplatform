package com.mtpt.dao;

import com.mtpt.bean.TBOrders;

public interface TBOrdersMapper {
    int deleteByPrimaryKey(String id);

    int insert(TBOrders record);

    int insertSelective(TBOrders record);

    TBOrders selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(TBOrders record);

    int updateByPrimaryKeyWithBLOBs(TBOrders record);

    int updateByPrimaryKey(TBOrders record);
}