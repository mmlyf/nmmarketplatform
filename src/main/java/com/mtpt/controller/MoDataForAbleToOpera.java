package com.mtpt.controller;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.RequestWrapper;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mtpt.bean.DsjAll;
import com.mtpt.bean.DsjBookData;
import com.mtpt.bean.MoBookData;
import com.mtpt.bean.TBBlackList;
import com.mtpt.bean.TBCBRecord;
import com.mtpt.bean.TBNmCity;
import com.mtpt.bean.TBRecord;
import com.mtpt.bean.TBReview;
import com.mtpt.extend.HttpRequest;
import com.mtpt.service.IDsjAllService;
import com.mtpt.service.IDsjBookDataService;
import com.mtpt.service.IMoBookDataService;
import com.mtpt.service.ITBBlackListService;
import com.mtpt.service.ITBCbRecordService;
import com.mtpt.service.ITBNmCityService;
import com.mtpt.service.ITBRecordService;
import com.mtpt.service.ITBReviewService;
import com.mtpt.service.IUpInstruService;
import com.mtpt.service.impl.UpInstruService;

import sun.util.logging.resources.logging;
/**
 * 
 * @author lvgordon
 * 处理上行的数据，判断是预定指令则下行二次确认
 * 二次确认之后保存预定数据表
 * 
 */
@Controller
@RequestMapping("/moopera")
public class MoDataForAbleToOpera {
	private Logger log = Logger.getLogger(MoDataForAbleToOpera.class);
	@Resource
	IUpInstruService upins;
	@Resource
	IMoBookDataService bookDataService;//上行预约有效的指令
	@Resource
	ITBBlackListService blackListService;
	@Resource
	ITBCbRecordService tbCbRecordService;
	@Resource
	ITBReviewService reviewService;
	@Resource 
	ITBRecordService recordService;
	@Resource
	IDsjAllService dsjAllService;
	@Resource
	ITBNmCityService nmcityService;
	@Resource 
	IDsjBookDataService dsjBookDataService;
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	private String resultMsg;
	@RequestMapping(value="/opera",method= {RequestMethod.POST,RequestMethod.GET})
	private void moDataOperationToBook(String phone,String message,
			HttpServletRequest request,HttpServletResponse res) throws UnsupportedEncodingException {
		phone = phone.substring(2, phone.length());
		Date date = new Date();
		String nowstr = sdf.format(date);
		log.debug("phoneNum:"+phone);
		log.debug("messageContent:"+message);
		DsjAll dsjAll = dsjAllService.selectDataByPhone(phone);
		
		if(message.equals("Y")||message.equals("y")) {
			MoBookData moBookData = bookDataService.selectByPhoneNum(phone);//查找当前号码已上行的内容
			if(moBookData==null) {
				resultMsg = "未收到您上行的有效指令，请回复有效指令之后。回复\"Y\"我们将为您在3-4个工作日内办理！";
			}else {
				String motimestr = sdf.format(moBookData.getMoTime());//回复上行时间与回复有效指令比较
				if(motimestr.equals(nowstr)) {
					DsjBookData dsjBookData = new DsjBookData();
					MoBookData bookData = new MoBookData();
					bookData.setId(moBookData.getId());
					bookData.setConfimTime(date);
					bookData.setState(1);
					bookDataService.updateByPrimaryKeySelective(bookData);//更新当前预定表中的状态
					dsjBookData.setDn(phone);
					dsjBookData.setMoMessage(moBookData.getMessage());
					dsjBookData.setMoTime(moBookData.getMoTime());
					dsjBookData.setState(1);
					TBCBRecord tbcbRecord = tbCbRecordService.selectDataForPhone(phone);
					if(tbcbRecord!=null) {
						dsjBookData.setMtTime(tbcbRecord.getCdSendtime());
						if(tbcbRecord.getSendflag() != null&&tbcbRecord.getSendflag()==2) {
							TBReview tbReview = reviewService.selectByPrimaryKey(tbcbRecord.getRdId());
							dsjBookData.setMsgid(tbReview.getMigId());
						}else if(tbcbRecord.getSendflag()==1||tbcbRecord.getSendflag()==null) {
							TBRecord tbRecord = recordService.selectByPrimaryKey(tbcbRecord.getRdId());
							dsjBookData.setMsgid(tbRecord.getMigId());
						}
					}
					if(dsjAll!=null) {
						dsjBookData.setFirdw(dsjAll.getDxFirdw());
						dsjBookData.setFirp(dsjAll.getDxFirp());
						dsjBookData.setSystype(dsjAll.getDxSys());
						int num = Integer.parseInt(dsjAll.getDxCity());
						TBNmCity tbNmCity = nmcityService.selectCityNameByNum(num);
						if(tbNmCity!=null) {
							dsjBookData.setCity(tbNmCity.getCityname());
						}else {
							dsjBookData.setCity("");
						}
					}
					dsjBookData.setDatafrom(1);
					dsjBookData.setAddtime(date);
					int result = dsjBookDataService.insertSelective(dsjBookData);
					if(result>0) {
						resultMsg = "您的畅越大流量"+dsjAll.getDxFirdw()+"元档位预约已提交成功,我们将在本月内为您办理，办理成功后将有短信提醒通知。";
					}else {
						resultMsg = "当前提交预约提交失败，请重试！";
					}
				}else {
					resultMsg = "不好意思，您回复的确认已超时。若还想办理请重新回复指令，并在有效时间内确认！";
				}
			}
		}else if(message.equals("T")||message.equals("t")){
			TBBlackList tbBlackList = new TBBlackList();
			tbBlackList.setDn(phone);
			int result = blackListService.insertSelective(tbBlackList);
			System.out.println("黑名单插入："+result);
			if (result>0) {
				log.debug("退订成功");
				resultMsg = "";
			}else {
				log.debug("退订失败");
				resultMsg = "";
			}
		}else {
			List<String> orderlist = upins.selectAllForInstruct();
			int msgindex = orderlist.indexOf(message);
			if(msgindex!=-1) {
				
				if(dsjAll!=null) {
					System.out.println("档位为："+dsjAll.getDxFirdw()+"\n上行的指令是："+message);
					if (dsjAll.getDxFirdw().equals(message)) {
						MoBookData moBook = new MoBookData();
						moBook.setDn(phone);
						moBook.setMessage(message);
						moBook.setMoTime(date);
						moBook.setState(0);
						bookDataService.insertSelective(moBook);
						resultMsg = "当前提交已成功，若确认办理请回复\"Y\"确认办理。若不确定办理可忽略此条信息！";
					}else {
						resultMsg = "温馨提示：很抱歉，此产品暂时未对您的套餐开放，我们会在开放后的第一时间里通知您，敬请谅解，谢谢.";
					}
				}else {
					System.out.println("dsj为空");
					resultMsg = "温馨提示：很抱歉，此产品暂时未对您的套餐开放，我们会在开放后的第一时间里通知您，敬请谅解，谢谢.";
				}
			}else {
				resultMsg = "当前编码错误，请核实！";
			}
		}
		System.out.println("当前消息为："+resultMsg);
		if(!resultMsg.equals("")) {
			String param = "phoneNumber="+phone+"&messageContent="+resultMsg;
			HttpRequest.sendPost("http://58.18.172.86:8088/HSDT_NMMarket_Platform/send/msgcode", param);
		}
	}
}
