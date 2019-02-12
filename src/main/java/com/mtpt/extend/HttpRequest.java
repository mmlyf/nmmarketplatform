package com.mtpt.extend;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;


public class HttpRequest {
	private static Logger log = Logger.getLogger(HttpRequest.class);
	/**
	 *向指定URL发送get请求
	 * 
	 */
 	public static String sendGet(String url, String param) {
		String urlparam = url +"?"+param;
		String result = "";
		BufferedReader reader = null;
		try {
			URL urlpa = new URL(urlparam);
			URLConnection conn = urlpa.openConnection();
			conn.setRequestProperty("accept", "application/json");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			conn.connect();
			Map<String, List<String>> headerMap = conn.getHeaderFields();
			for(String key:headerMap.keySet()) {
				log.info("header data is "+headerMap.get(key));
			}
			reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line = "";
			while(reader.ready()&&(line = reader.readLine())!=null) {
				result += line;
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				if (reader!=null) {
					reader.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}
 	/**
 	 * 向指定的URL发送的post的方法
 	 * 
 	 */
 	public static String sendPost(String url,String param) {
 		String result = "";
 		PrintWriter pw = null;
 		BufferedReader bReader = null;
 		try {
			URL urlPost = new URL(url);
			URLConnection connection  = urlPost.openConnection();
			connection.setRequestProperty("accept", "*/*");
			connection.setRequestProperty("connection", "Keep-Alive");
			connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setConnectTimeout(4000);
			connection.setReadTimeout(5000);
			pw = new PrintWriter(connection.getOutputStream());
			pw.print(param);
			pw.flush();
			bReader  = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String lineStr = "";
			while(bReader.ready()&&(lineStr = bReader.readLine())!=null) {
				result += lineStr;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				if (bReader!=null) {
					bReader.close();
				}
				if(pw!=null) {
					pw.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
 		return result;
 	}
}
