package com.mtpt.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mtpt.bean.DsjBookData;
import com.mtpt.bean.Products;
import com.mtpt.bean.TBMssage;
import com.mtpt.bean.page.DsjBookPage;
import com.mtpt.extend.HttpRequest;
import com.mtpt.extend.ProductNameType;
import com.mtpt.service.IDsjAllService;
import com.mtpt.service.IDsjBookDataService;
import com.mtpt.service.IProduceService;
import com.mtpt.service.IProductsService;
import com.mtpt.service.ITBMssageService;

@Controller
@RequestMapping("/buscontro")
public class BusinessModuleController {
	private Logger log = Logger.getLogger(BusinessModuleController.class);
	
	@Resource 
	IProductsService productsService;
	@Resource
	IProduceService pdcService;
	@Resource
	IDsjBookDataService dsjBookDataService;
	@Resource 
	ITBMssageService mssageService;
	HttpSession session = null;
	SimpleDateFormat sdf = null;
	/**
	 * 
	 * @param page
	 * @param response
	 * @param request
	 * 查找订购列表中的数据，或者根据一些条件进行查询
	 * 
	 */
	@RequestMapping(value="/selectbypage",method = {RequestMethod.POST,RequestMethod.GET})
	private void selectOrdersByPage(DsjBookPage page,HttpServletResponse response,HttpServletRequest request) {
		session = request.getSession();
		String name = (String) session.getAttribute("realname");
		log.info(name+"访问订单列表！");
		log.debug(page.getDn());
		sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		int totals = dsjBookDataService.selectDsjBookCountByPage(page);
		page.setTotalRecord(totals);
		List<DsjBookData> dsjlist = dsjBookDataService.selectDsjBookDataByPage(page);
		JSONObject jsonmap = new JSONObject();
		List<JSONObject> jsonlist = new ArrayList<JSONObject>();
		int i = 1;
		for(DsjBookData dsjBookData:dsjlist) {
			JSONObject map = new JSONObject();
			map.put("id",dsjBookData.getId());
			map.put("dn", dsjBookData.getDn());
			map.put("city", dsjBookData.getCity());
			map.put("firp", dsjBookData.getFirp());
			map.put("firdw", dsjBookData.getFirdw());
			map.put("momsg", dsjBookData.getMoMessage());
			if(dsjBookData.getMoTime()!=null) {
				String motime = sdf.format(dsjBookData.getMoTime());
				map.put("motime", motime);
			}else {
				map.put("motime", "");
			}
			if(dsjBookData.getMtTime()!=null) {
				String mttime = sdf.format(dsjBookData.getMtTime());
				map.put("mttime", mttime);
			}else {
				map.put("mttime", "");
			}
			map.put("systype", dsjBookData.getSystype());
			if(dsjBookData.getState()==1) {
				map.put("state", "成功");
			}
			if (dsjBookData.getDatafrom()==1) {
				map.put("qudao", "短信");
			}else {
				map.put("qudao", "支付宝");
			}
			if(dsjBookData.getMsgid()!=null) {
				TBMssage tbMssage = mssageService.selectByPrimaryKey(dsjBookData.getMsgid());
				map.put("msgContent", tbMssage.getMisContent());
			}else {
				map.put("msgContent", "");
			}
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
	
	/**
	 * 
	 * @param phonenum
	 * @param response
	 * @param request
	 * 根据号码查询订购列表和详细档位等数据
	 * 
	 */
//	@RequestMapping(value="/selectdndetails",method = {RequestMethod.POST,RequestMethod.GET})
//	private void selectByPhoneForDetails(String phonenum,HttpServletResponse response,HttpServletRequest request) {
//		session = request.getSession();
//		String name = (String) session.getAttribute("realname");
//		log.info(name+"查找号码"+phonenum+"的详细信息(档位、订单等信息)");
//		Integer dxcount = pdcService.selectDxCount(phonenum);
//		if (dxcount==null) {
//			dxcount = 0;
//		}
//		Integer icecount = pdcService.selectIceCount(phonenum);
//		if (icecount==null) {
//			icecount = 0;
//		}
//		List<TBDsjDxAll> dxList = pdcService.selectDxByPhoneDetails(phonenum);
//		List<TBDsjIceAll> icelist = pdcService.selectIceByPhoneDetails(phonenum);
//		JSONObject jsonmap = new JSONObject();
//		List<JSONObject> jsonlist = new ArrayList<>();
//		int count = 1;
//		if (dxList!=null) {
//			for(TBDsjDxAll tbDsjDxAll:dxList) {
//				for(Orders orders:tbDsjDxAll.getOrders()) {
//					JSONObject map = new JSONObject();
//					map.put("id", count);
//					map.put("dn", tbDsjDxAll.getDxDn());
//					map.put("city", tbDsjDxAll.getDxCity());
//					map.put("ap", tbDsjDxAll.getDxAp());
//					map.put("sys", tbDsjDxAll.getDxSys());
//					map.put("inner", tbDsjDxAll.getDxInn());
//					map.put("rh", tbDsjDxAll.getDxRh());
//					map.put("rhlx", tbDsjDxAll.getDxRhlx());
//					map.put("dxfirp", tbDsjDxAll.getDxFirp());
//					map.put("dxfirdw", tbDsjDxAll.getDxFirdw());
//					map.put("ifdx", tbDsjDxAll.getDxIfdx());
//					map.put("agw", orders.getSerialno());
//					sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//					map.put("ordertime", sdf.format(orders.getPurchasetime()));
//					Products products = productsService.selectByPrimaryKey(orders.getProductid());
//					String productname = ProductNameType.getName(products.getVagabondizetype(),
//							products.getPackagetype(), products.getEffecttype());
//					map.put("price", products.getCost());
//					map.put("productname", productname+"-"+products.getProductname());
//					map.put("state", orders.getBssstate());
//					map.put("source", ProductNameType.getSource(orders.getLsource()));
//					jsonlist.add(map);
//					count++;
//				}
//			}
//		}
//		if (icelist!=null) {
//			for(TBDsjIceAll tbDsjIceAll:icelist) {
//				JSONObject icemap = new JSONObject();
//				icemap.put("id", count);
//				icemap.put("dn", tbDsjIceAll.getDxDn());
//				icemap.put("city", tbDsjIceAll.getDxCity());
//				icemap.put("ap", tbDsjIceAll.getDxAp());
//				icemap.put("sys", tbDsjIceAll.getDxSys());
//				icemap.put("inner", tbDsjIceAll.getDxInn());
//				icemap.put("rh", tbDsjIceAll.getDxRh());
//				icemap.put("rhlx", tbDsjIceAll.getDxRhlx());
//				icemap.put("dxfirp", tbDsjIceAll.getDxFirp());
//				icemap.put("dxfirdw", tbDsjIceAll.getDxFirdw());
//				icemap.put("ifdx", tbDsjIceAll.getDxIfdx());
//				jsonlist.add(icemap);
//				count++;
//			}
//		}
//		jsonmap.put("code", 0);
//		jsonmap.put("msg", "");
//		jsonmap.put("count", dxcount+icecount);
//		jsonmap.put("data", jsonlist);
//		response.setContentType("text/html;charset=utf-8");
//		try {
//			PrintWriter pw = response.getWriter();
//			pw.write(jsonmap.toString());
//			pw.flush();
//			pw.close();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	
//	@RequestMapping(value="/orderud",method = {RequestMethod.POST,RequestMethod.GET})
//	private void UnsubscripeAndMakeup(String id,String phonenum,Integer actioncode,HttpServletRequest request,HttpServletResponse response) {
//		String getnumdetailurl = "http://mobile99.uninforun.com/unicom-hb/api/Unicom/GetUserInfo";
//		String orderurl = "http://mobile99.uninforun.com/unicom-hb/api/Unicom/Order";
//		String paramcode = "phonenum="+phonenum;
//		String networkType = "";
//		String productCode = "";
//		String agwCode = "";
//		int code ;
//		boolean is4G = false;
//		String resultjsonstr = HttpRequest.sendGet(getnumdetailurl, paramcode);
//		JSONObject resultjson = new JSONObject(resultjsonstr);
//		if (resultjson.getBoolean("result")) {
//			JSONObject datajson = resultjson.getJSONObject("data");
//			networkType = datajson.getString("NetworkType");
//		}
//		Orders orders = orderService.selectByPrimaryKey(id);
//		Products products = productsService.selectByPrimaryKey(orders.getProductid());
//		if (networkType.equals("2G")||networkType.equals("3G")) {
//			productCode = products.getProductcode23g();
//			is4G = false;
//		}else if(networkType.equals("4G")) {
//			productCode = products.getProductcode4g();
//			is4G = true;
//		}
//		String parameorder = "phoneNum="+phonenum+"&productCode="+productCode+"&actionType="+actioncode+"&is4G="+is4G;
//		String resultorder = HttpRequest.sendPost(orderurl, parameorder);
//		JSONObject jsonorder = new JSONObject(resultorder);
//		if (jsonorder.getBoolean("result")) {
//			agwCode = jsonorder.getString("data");
//		}
//		Orders upOrders = new Orders();
//		upOrders.setId(id);
//		upOrders.setSerialno(agwCode);
//		int uporder = orderService.updateByPrimaryKeySelective(upOrders);
//		if(uporder>0) {
//			code = 0;
//		}else {
//			code = 1;
//		}
//		try {
//			PrintWriter pw = response.getWriter();
//			pw.write("{code:"+code+"}");
//			pw.flush();
//			pw.close();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
}
