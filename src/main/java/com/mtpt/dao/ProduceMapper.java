package com.mtpt.dao;

import java.util.List;

import com.mtpt.bean.RepeatOpera;
import com.mtpt.bean.Review;

public interface ProduceMapper {
	Integer selectModelCount(Review review);
	
	List<String> selectThreeday(RepeatOpera repeatOpera);
	
	Integer selectDxCount(String phonenum);
	
	Integer selectIceCount(String phonenum);
}
