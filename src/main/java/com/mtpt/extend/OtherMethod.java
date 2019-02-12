package com.mtpt.extend;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.criteria.CriteriaBuilder.Case;

public class OtherMethod {
	public static Date addDay(Date date, int num) {
		Calendar startDT = Calendar.getInstance();
		startDT.setTime(date);
		startDT.add(Calendar.DAY_OF_MONTH, num);
		return startDT.getTime();
	}
	
	public static Date reduceDay(Date date,int num) {
		Calendar startDT = Calendar.getInstance();
		startDT.setTime(date);
		int year = startDT.get(Calendar.YEAR);
		int month = startDT.get(Calendar.MONTH);
		int day = startDT.get(Calendar.DATE)-num;
		startDT.set(year, month, day);
		return startDT.getTime();
	}
	
	public static int getTheMonthOfDays(int month) {
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		switch (month) {
		case 1:
		case 3:
		case 5:
		case 7:
		case 8:
		case 10:
		case 12:
			return 31;
		case 4:
		case 6:
		case 9:
		case 11:
			return 30;
		case 2:
			if ((year%4==0 && year%100!=0) || year%400==0) {
				return 29;
			}else {
				return 28;
			}
		default:
			return 0;
		
		}
	}
	
	public static void main(String[] args) {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		System.out.println("当前日期是："+sdf.format(date));
		date = reduceDay(date, 3);
		System.out.println("减去的日期是："+sdf.format(date));
	}
}
