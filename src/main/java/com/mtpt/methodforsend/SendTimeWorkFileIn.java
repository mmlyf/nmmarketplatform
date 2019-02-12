package com.mtpt.methodforsend;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

import org.apache.log4j.Logger;

import com.mtpt.bean.TBMssage;
import com.mtpt.bean.TBRecord;
import com.mtpt.bean.TBReview;
import com.mtpt.service.impl.TBMssageService;
import com.mtpt.service.impl.TBRecordService;
import com.mtpt.service.impl.TBReviewService;
import com.mtpt.utilclass.SpringContextUtil;

public class SendTimeWorkFileIn {
	private static Logger log = Logger.getLogger(SendTimeWorkFileIn.class);
	private static TBMssageService mssageService = (TBMssageService) SpringContextUtil.getBean("tbMssageService");
	private static TBRecordService recordService = (TBRecordService) SpringContextUtil.getBean("tbrecord");
	private static TBReviewService reviewService = (TBReviewService) SpringContextUtil.getBean("reservice");
	private static ExecutorService filepool = Executors.newSingleThreadExecutor();
	private static ExecutorService modelpool = Executors.newSingleThreadExecutor();
	public static void addFileTaskId(int taskid) {
		Timer timer = new Timer();
		filepool.execute(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				TBRecord tbRecord = recordService.selectByPrimaryKey(taskid);
				timer.schedule(new TimerTask() {
					@Override
					public void run() {
						// TODO Auto-generated method stub
						SendFileIn.waitsend.add(taskid);
						TBRecord tbRecord = new TBRecord();
						tbRecord.setState(5);
						tbRecord.setId(taskid);
						recordService.updateByPrimaryKeySelective(tbRecord);
						SendFileIn.addTaskid(taskid);
					}
				}, tbRecord.getWorktime());
			}
		});
	}
	
	public static void addModelTaskId(int id) {
		Timer timer = new Timer();
		modelpool.execute(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				TBReview tbReview = reviewService.selectByPrimaryKey(id);
				timer.schedule(new TimerTask() {
					@Override
					public void run() {
						// TODO Auto-generated method stub
						TBReview tbReview = new TBReview();
						tbReview.setId(id);
						tbReview.setState(5);
						reviewService.updateByPrimaryKeySelective(tbReview);
//						SendModelIn.addRe_id(id);
					}
				}, tbReview.getWorktime());
			}
		});
	}
	
}
