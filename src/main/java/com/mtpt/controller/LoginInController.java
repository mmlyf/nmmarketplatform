package com.mtpt.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mtpt.bean.TBSuser;
import com.mtpt.bean.TBUsers;
import com.mtpt.service.ITBSuserService;
/**
 * 
 * @author lvgordon
 * 登录的请求数据
 *
 */
@Controller
@RequestMapping("/login")
public class LoginInController {
	private Logger log = Logger.getLogger(LoginInController.class);
	static {
//		PropertyConfigurator.configure(Thread.currentThread().getContextClassLoader().getResource("log.properties").getPath());
		PropertyConfigurator.configure("E://newplatform/config/log.properties");//河北
	}
	@Resource
	ITBSuserService service;
	@RequestMapping(value="/byIdPaw",method = {RequestMethod.POST,RequestMethod.GET})
	private void loginInByIdPaw(String username,String password,HttpServletRequest request,HttpServletResponse response) {
		
		HashMap<String, String> map = new HashMap<String, String>();
		if (username ==null&&password==null) {
			log.debug("用户名或者密码为空！");
		}else {
			map.put("username", username);
			map.put("password",password);
			TBSuser tbSuser = service.selectByNamePaw(map);
			try {
				PrintWriter pw = response.getWriter();
				JSONObject json = new JSONObject();
				if (tbSuser!=null) {
					log.info(username+"登录系统");
					json.put("code", 200);
					HttpSession session = request.getSession();
					session.setAttribute("uid", tbSuser.getId());
					session.setAttribute("username", tbSuser.getUsername());
					session.setAttribute("realname", tbSuser.getRealname());
					session.setAttribute("permision", tbSuser.getPermission());
				}else {
					log.info(username+"登录系统失败！！");
					json.put("code", 404);
				}
				pw.write(json.toString());
				pw.flush();
				pw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	@RequestMapping(value="logout",method = {RequestMethod.POST,RequestMethod.GET})
	private void logOutPlatform(HttpServletRequest request,HttpServletResponse response) {
		HttpSession session = request.getSession();
		log.info(session.getAttribute("realname")+"登出系统");
		session.invalidate();
		try {
			response.sendRedirect("../jsp/login.jsp");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="/change",method = {RequestMethod.POST,RequestMethod.GET})
	private void changePassword(TBSuser tbSuser,	HttpServletRequest request,HttpServletResponse response) {
		HttpSession session = request.getSession();
		String name = (String) session.getAttribute("realname");
		log.info(name+"执行修改密码为："+tbSuser.getPassword()+"\nuid的值是："+tbSuser.getId()+"!");
		int result = service.updateByPrimaryKeySelective(tbSuser);
		JSONObject json = new JSONObject();
		if (result>0) {
			json.put("code", 0);
		}else {
			json.put("code", 1);
		}
		try {
			PrintWriter pw = response.getWriter();
			pw.write(json.toString());
			pw.flush();
			pw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
