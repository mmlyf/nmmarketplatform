package com.mtpt.methodforsend;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.mtpt.bean.RepeatOpera;
import com.mtpt.bean.TBMssage;
import com.mtpt.bean.TBRecord;
import com.mtpt.bean.page.TBMessagePage;
import com.mtpt.service.ITBMssageService;
import com.mtpt.service.impl.TBCbRecordService;
import com.mtpt.service.impl.TBMssageService;
import com.mtpt.service.impl.TBRecordService;
import com.mtpt.service.impl.TBReviewService;
import com.mtpt.utilclass.SpringContextUtil;
import com.sun.org.apache.xml.internal.security.utils.Base64;
public class SendFileIn {
	
	private static TBMssageService mssageService = (TBMssageService) SpringContextUtil.getBean("tbMssageService");
	
	private static TBRecordService recordService = (TBRecordService) SpringContextUtil.getBean("tbrecord");
	private static Logger log = Logger.getLogger(SendFileIn.class);
	private static BlockingQueue<String> phonequeue = new LinkedBlockingQueue<String>();
	private static String messageContent = "";
	private static ExecutorService pool = Executors.newSingleThreadExecutor();
	private static int taskid;
	public static boolean isStop = false;
	public static boolean isEnd = false;
	private static String sectime ;
	public static ArrayList<Integer> waitsend = new ArrayList<Integer>();
	public SendFileIn() {}
	
	public static int getTaskid() {
		return taskid;
	}
	public static void addTaskid(final int id) {
		if (pool.isShutdown()) {
			pool = Executors.newSingleThreadExecutor();
		}
		log.info("等待列表中的数量是："+waitsend.size());
		pool.execute(new Runnable() {
			
			public void run() {
				// TODO Auto-generated method stub
				synchronized (this) {
					taskid = id;
					TBRecord tbRecord = new TBRecord();
					tbRecord.setId(id);
					tbRecord.setState(3);
					recordService.updateByPrimaryKeySelective(tbRecord);
					send(id);
				}
			}
		});
	}
	
	private static void send(int id) {
		TBRecord tbRecord = recordService.selectByPrimaryKey(id);
//		String path = "/Users/lvgordon/Downloads/FILEIN/"+tbRecord.getNewfilename();
		String path = "E://newplatform/upload/"+tbRecord.getNewfilename();//内蒙环境
//		String path = "D://NEW_HSDTMarket_Platform/upload/"+tbRecord.getNewfilename();//河北环境
		File file = new File(path);
		log.info("当前文件的大小："+file.length());
		log.info("当前消息的ID是："+tbRecord.getMigId());
		if(tbRecord.getMigId()!=null) {
			log.info(mssageService);
			TBMssage tbMssage = mssageService.selectByPrimaryKey(tbRecord.getMigId());
			log.info("当前的消息的数量是："+tbMssage.getMisContent());
			messageContent = tbMssage.getMisContent();
		}else {
			messageContent = "当前并非任何运营消息";
		}
		log.debug("当前消息是："+messageContent);
		try {
			BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
			InputStreamReader in = new InputStreamReader(bis);
			BufferedReader reader = new BufferedReader(in);
			String line = "";
			while((line = reader.readLine())!=null) {
				phonequeue.offer(line);
			}
			outQueueForSend(phonequeue,tbRecord);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	private static void outQueueForSend(BlockingQueue<String> queue,TBRecord tbRecord) {
		log.info("进入");
		List<String> list = new ArrayList<String>();
		int count = 0;
		while(true) {
			try {
				if (!isEnd) {
					if (!isStop) {
						if (!queue.isEmpty()) {
							list.add(queue.take());
							count++;
						}else {
							log.debug("当前队列已经为空");
						}
						if(count%8==0||(queue.isEmpty()&&count>0)) {
							if (tbRecord.getIsdelblack().equals("是")) {
								list = Remove.blackList(list);
							}
							if(tbRecord.getIsdeldays().equals("是")) {
								RepeatOpera repeatOpera = new RepeatOpera();
								Date date = new Date();
								SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
								repeatOpera.setDatain(sdf.format(date));
								repeatOpera.setDays(tbRecord.getDeldays());
								list = Remove.threeDayList(list,repeatOpera);
							}
							if(!list.isEmpty()) {
								Thread thread = new Thread(new HandlerSend(list, messageContent, tbRecord.getId()));
								thread.start();
								Thread.currentThread().sleep(1000);
								list.removeAll(list);
							}else {
								log.debug("当前发送批次均为黑名单或者限定时间间隔内已经发送！");
							}
						}
						if (queue.isEmpty()) {
							break;
						}
					}else {
						Thread.currentThread().sleep(1000);
					}
				}else {
					Thread.currentThread().sleep(1000);
					pool.shutdownNow();
					break;
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (!isEnd&&queue.isEmpty()) {
			log.debug("队列数据取完");
			TBRecord overrecord = new TBRecord();
			overrecord.setId(taskid);
			overrecord.setState(4);
			overrecord.setEndtime(new Date());
			int result = recordService.updateByPrimaryKeySelective(overrecord);
			Iterator<Integer> it = waitsend.iterator();
			while(it.hasNext()) {
				if(it.next()==taskid) {
					it.remove();
				}
			}
			if (result>0) {
				log.debug("发送完成");
			}
		}else {
			log.debug("停止");
			for(Integer id:waitsend) {
				TBRecord endrecord = new TBRecord();
				endrecord.setId(id);
				endrecord.setState(7);
				recordService.updateByPrimaryKeySelective(endrecord);
			}
			isEnd = false;
			isStop = false;
			waitsend.clear();
		}
	}
}
class HandlerSend implements Runnable{
	private Logger log = Logger.getLogger(HandlerSend.class);
	
	private List<String> listphone ;
	private String messageContent;
	private int re_id;
	//产品接入号
	String spNumber="10655883";
//	String spNumber = "1065572778";//河北接入号
	//业务代码
	String servcieType="90860230";
	//linkId
	String linkId = "MOODDDS";
	char reportflag = '1';
	public HandlerSend(List<String> listphone,String messageContent,int re_id) {
		this.listphone = listphone;
		this.messageContent = messageContent;
		this.re_id = re_id;
	}
	public void run() {
		// TODO Auto-generated method stub
		String strxml ="{<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
				+ "<gwsmip>\n" + "  <message_header>\n"
				+ "    <command_id>0x3</command_id>\n"
				+ "    <sequence_number/>\n" + "  </message_header>\n"
				+ "  <message_body>\n" + "    <pk_total>1</pk_total>\n"
				+ "    <pk_number>1</pk_number>\n" + "    <user_numbers>\n";
		for(String dn :listphone) {
			strxml += "<user_number>"+dn+"</user_number>";
		}		
		strxml += "    </user_numbers>\n"
				+ "    <sp_number>"+spNumber+"</sp_number>\n"
				+ "    <service_type>"+servcieType+"</service_type>\n"
				+ "    <link_id>"+linkId+"</link_id>\n"
				+ "    <message_content>" + Base64.encode(messageContent.getBytes())
				+ "</message_content>\n"
				+ "    <report_flag>"+reportflag+"</report_flag>\n"
				+ "   </message_body>\n" + "</gwsmip>\n}";
		System.out.println("strxml的值是："+strxml);
		DataInDB.sendPhoneInDB(listphone, 1, re_id);//将已发送的号码存入数据表
		try {
			Socket socket = new Socket("127.0.0.1", 8809);
			OutputStream out = socket.getOutputStream();
			out.write(strxml.getBytes());
			out.flush();
			out.close();
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}


