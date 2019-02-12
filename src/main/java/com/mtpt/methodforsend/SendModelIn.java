package com.mtpt.methodforsend;

import java.io.IOException;
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

import org.apache.log4j.Logger;
import org.omg.CORBA.REBIND;

import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.mtpt.bean.RepeatOpera;
import com.mtpt.bean.Review;
import com.mtpt.bean.TBMssage;
import com.mtpt.bean.TBRecord;
import com.mtpt.bean.TBReview;
import com.mtpt.bean.page.TBMessagePage;
import com.mtpt.service.impl.TBMssageService;
import com.mtpt.service.impl.TBRecordService;
import com.mtpt.service.impl.TBReviewService;
import com.mtpt.utilclass.SpringContextUtil;
import com.sun.org.apache.xml.internal.security.utils.Base64;

import sun.nio.ch.SelChImpl;

public class SendModelIn {
	private static TBMssageService mssageService = (TBMssageService) SpringContextUtil.getBean("tbMssageService");
	
	private static TBReviewService reviewService = (TBReviewService) SpringContextUtil.getBean("reservice");
	private static Logger log = Logger.getLogger(SendFileIn.class);
	private static BlockingQueue<String> queue = new LinkedBlockingQueue<String>();
	private static String messageContent = "";
	private static ExecutorService pool = Executors.newSingleThreadExecutor();
	private static int re_id;
	public static boolean isStop = false;
	public static boolean isEnd = false;
	private static String sectime ;
	public static ArrayList<Integer> waitsend = new ArrayList<Integer>();
	public SendModelIn() {
		// TODO Auto-generated constructor stub
	}
	
	public static int getRe_id() {
		return re_id;
	}

	public static void addRe_id(final int id) {
		if (pool.isShutdown()) {
			pool = Executors.newSingleThreadExecutor();
		}
		pool.execute(new Runnable() {
			public void run() {
				// TODO Auto-generated method stub
				re_id = id;
				TBReview tbReview = new TBReview();
				tbReview.setId(id);
				tbReview.setState(3);
				reviewService.updateByPrimaryKeySelective(tbReview);
				send(id);
			}
		});
	}
	
	private static void send(int id) {
		TBReview tbReview = reviewService.selectByPrimaryKey(id);
		Review review = new Review();
		review.setCity(tbReview.getCity());
		review.setDangw(tbReview.getDangw());
		review.setProduct(tbReview.getProduct());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String sectime = sdf.format(tbReview.getSecTime());
		review.setSecTime(sectime);
//		if (tbReview.getPrelx().equals("dsj_dx_all")) {
//			List<TBDsjDxAll> dxAlls = dxAllService.selectByReview(review);
//			for(TBDsjDxAll tbDsjDxAll:dxAlls) {
//				queue.offer(tbDsjDxAll.getDxDn());
//			}
//		}else {
//			List<TBDsjIceAll> iceAlls = iceAllService.selectByReview(review);
//			for (TBDsjIceAll tbDsjIceAll:iceAlls) {
//				queue.offer(tbDsjIceAll.getDxDn());
//			}
//		}
		TBMssage tbMssage = mssageService.selectByPrimaryKey(tbReview.getMigId());
		messageContent = tbMssage.getMisContent();
		log.info("当前消息的值是："+messageContent);
		outQueueForSend(queue,tbReview);
	}
	
	private static void outQueueForSend(BlockingQueue<String> queue,TBReview tbReview) {
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
							if (tbReview.getIsdelblack().equals("是")) {
								list = Remove.blackList(list);
							}
							if(tbReview.getIsdeldays().equals("是")) {
								RepeatOpera repeatOpera = new RepeatOpera();
								Date date = new Date();
								SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
								repeatOpera.setDatain(sdf.format(date));
								repeatOpera.setDays(tbReview.getDeldays());
								list = Remove.threeDayList(list,repeatOpera);
							}
							if(!list.isEmpty()) {
							Thread thread = new Thread(new HandlerSend(list, messageContent, tbReview.getId()));
							thread.start();
							Thread.currentThread().sleep(1000);
							list.removeAll(list);
							}else {
								log.debug("当前的发送的队列被筛选之后，无数据发送");
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
			TBReview overreview = new TBReview();
			overreview.setId(re_id);
			overreview.setState(4);
			int result = reviewService.updateByPrimaryKeySelective(overreview);
			Iterator<Integer> it = waitsend.iterator();
			while (it.hasNext()) {
				if (it.next()==re_id) {
					it.remove();
				}
			}
			if (result>0) {
				log.debug("发送完成");
			}
		}else {
			log.debug("停止");
			for(Integer id:waitsend) {
				TBReview endreview = new TBReview();
				endreview.setId(id);
				endreview.setState(7);
				reviewService.updateByPrimaryKeySelective(endreview);
			}
			isEnd = false;
			isStop = false;
			waitsend.clear();
		}
	}
}

class HandlerModelSend implements Runnable{
	private Logger log = Logger.getLogger(HandlerModelSend.class);
	private List<String> phonelist;
	private String messageContent;
	private Integer reid;
	//产品接入号
	String spNumber="10655883";
//	String spNumber = "1065572778";//河北接入号
	//业务代码
	String servcieType="90860230";
	//linkId
	String linkId = "MOODDDS";
	char reportflag = '1';
	public HandlerModelSend(List<String> phonelist,String messageContent,Integer reid) {
		this.phonelist = phonelist;
		this.messageContent = messageContent;
		this.reid = reid;
	}

	public void run() {
		// TODO Auto-generated method stub
		String strxml ="{<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
				+ "<gwsmip>\n" + "  <message_header>\n"
				+ "    <command_id>0x3</command_id>\n"
				+ "    <sequence_number/>\n" + "  </message_header>\n"
				+ "  <message_body>\n" + "    <pk_total>1</pk_total>\n"
				+ "    <pk_number>1</pk_number>\n" + "    <user_numbers>\n";
		for(String dn :phonelist) {
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
		boolean res = DataInDB.sendPhoneInDB(phonelist, 2, reid);
		if (res) {
			log.debug("数据已经存入数据表");
		}else {
			log.debug("数据存入失败");
		}
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
