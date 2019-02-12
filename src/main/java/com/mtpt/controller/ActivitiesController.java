package com.mtpt.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mtpt.bean.TBActivilist;
import com.mtpt.bean.TBHfcs;
import com.mtpt.bean.page.TBRecordPage;
import com.mtpt.service.ITBActivilistService;
import com.mtpt.service.ITBBlackListService;
import com.mtpt.service.ITBHfcsService;

import sun.util.logging.resources.logging_fr;

@Controller
@RequestMapping("/reqact")
public class ActivitiesController {
	
	@Resource
	ITBActivilistService activiService;
	@Resource
	ITBHfcsService hfcsService;
	
	private SimpleDateFormat sdf = null;
	@RequestMapping(value="/selectpage",method = {RequestMethod.POST,RequestMethod.GET})
	private void selectActByRecordPage(TBRecordPage page,HttpServletResponse response) {
		int totals = activiService.selectActiCount(page);
		page.setTotalRecord(totals);
		List<TBActivilist> actlist = activiService.selectActiByPage(page);
		JSONObject jsonmap = new JSONObject();
		List<JSONObject> jsonlist = new ArrayList<>();
		sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		for(TBActivilist tbActivilist : actlist) {
			JSONObject map = new JSONObject();
			map.put("actiid", tbActivilist.getId());
			map.put("actname", tbActivilist.getActititle());
			map.put("starttime", sdf.format(tbActivilist.getStartTime()));
			map.put("endtime", sdf.format(tbActivilist.getEndTime()));
			map.put("addtime", sdf.format(tbActivilist.getAddtime()));
			map.put("detail",tbActivilist.getDatadetail());
			jsonlist.add(map);
		}
		jsonmap.put("code", 0);
		jsonmap.put("msg", "");
		jsonmap.put("count", totals);
		jsonmap.put("data", jsonlist);
		response.setContentType("text/html;charset=utf-8");
		try {
			PrintWriter pw = response.getWriter();
			pw.write(jsonmap.toString());
			pw.flush();
			pw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="/selectdetail",method = {RequestMethod.POST,RequestMethod.GET})
	private void selectDetailDataForActi(HttpServletResponse response) {
		List<TBHfcs> hfcslist = hfcsService.selectByAll();
		JSONObject jsoncdata = new JSONObject();
		JSONObject jsondata = new JSONObject();
		String[] nstr = {"首页pv",
				"测试点击数",
				"结果点击数",
				"订购页PV",
				"验证码获取数",
				"立即订购数",
				"确认订购数"};
		for(TBHfcs tbHfcs:hfcslist) {
			jsondata.put("首页pv", tbHfcs.getHfSpv());
			jsondata.put("测试点击数", tbHfcs.getHfCsks());
			jsondata.put("结果点击数", tbHfcs.getHfCheckresult());
			jsondata.put("订购页PV", tbHfcs.getHfOrderpv());
			jsondata.put("验证码获取数", tbHfcs.getHfVercode());
			int licount = tbHfcs.getHfSgnum()+
					tbHfcs.getHfEgnum()+
					tbHfcs.getHfTgnum()+
					tbHfcs.getHfFgnum()+
					tbHfcs.getHfQgnum()+
					tbHfcs.getHfYgnum()+
					tbHfcs.getHfEsnum()+
					tbHfcs.getHfTsnum()+
					tbHfcs.getHfFsnum()+
					tbHfcs.getHfYbsnum();
			jsondata.put("立即订购数", licount);
			int concount =  tbHfcs.getHfQrnums()
					+ tbHfcs.getHfQrnume()
					+ tbHfcs.getHfQrnumt()
					+tbHfcs.getHfQrnumf()
					+tbHfcs.getHfQrnumq()+
					tbHfcs.getHfQrnumyb()+
					tbHfcs.getHfQrnumss()+
					tbHfcs.getHfQrnumse()+
					tbHfcs.getHfQrnumsf()+
					tbHfcs.getHfQrnumsyb();
			jsondata.put("确认订购数", concount);
		}
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("nstr", nstr);
		jsonObject.put("data", jsondata);
		response.setContentType("text/html;charset=utf-8");
		try {
			PrintWriter pw = response.getWriter();
			pw.write(jsonObject.toString());
			pw.flush();
			pw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
