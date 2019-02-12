package com.mtpt.service;

import com.mtpt.bean.IceDsjOrders;
import com.mtpt.bean.page.TotalPage;

public interface IIceDsjOrdersService {
	int deleteByPrimaryKey(Integer dxId);

    int insert(IceDsjOrders record);

    int insertSelective(IceDsjOrders record);

    IceDsjOrders selectByPrimaryKey(Integer dxId);

    int updateByPrimaryKeySelective(IceDsjOrders record);

    int updateByPrimaryKey(IceDsjOrders record);
    
    Integer selectCountByPage(TotalPage page);
}
