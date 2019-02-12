package com.mtpt.service;

import com.mtpt.bean.TBNmCity;

public interface ITBNmCityService {
	int deleteByPrimaryKey(Integer id);

	int insert(TBNmCity record);

	int insertSelective(TBNmCity record);

	TBNmCity selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(TBNmCity record);

	int updateByPrimaryKey(TBNmCity record);

	TBNmCity selectCityNameByNum(Integer num);
}
