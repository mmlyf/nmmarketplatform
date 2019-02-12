package com.mtpt.timework;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.mtpt.bean.TBRecord;
import com.mtpt.bean.TBReview;
import com.mtpt.bean.enumerate.SendMailType;
import com.mtpt.extend.SendMail;
import com.mtpt.methodforsend.SendFileIn;
import com.mtpt.methodforsend.SendModelIn;
//import com.mtpt.methodforsend.SendModelIn;
import com.mtpt.service.ITBRecordService;
import com.mtpt.service.ITBReviewService;

@Component("stopwork")
public class StopWorkTimeTask {
	private Logger log = Logger.getLogger(StopWorkTimeTask.class);
	@Resource 
	ITBReviewService reviewService;
	@Resource 
	ITBRecordService recordService;
	/**
	 * 定时关闭短信通道
	 */
	public void stopSendMessage()
	{
		log.debug("暂停当前的发送任务！");
		
		SendFileIn.isStop = true;
		for(int id: SendFileIn.waitsend) {
			TBRecord tbRecord = new TBRecord();
			tbRecord.setId(id);
			tbRecord.setState(6);
			recordService.updateByPrimaryKeySelective(tbRecord);
		}
		SendModelIn.isStop = true;
		for(int id: SendModelIn.waitsend) {
			TBReview tbReview = new TBReview();
			tbReview.setId(id);
			tbReview.setState(6);
			reviewService.updateByPrimaryKeySelective(tbReview);
		}
		SendMail.sendMailForCommon("内蒙短信下发暂停成功", "", SendMailType.DEVELOP);
	} 
}
