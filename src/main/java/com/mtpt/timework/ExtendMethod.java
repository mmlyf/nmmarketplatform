package com.mtpt.timework;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.log4j.Logger;

import com.mtpt.bean.DataTotal;
import com.mtpt.bean.enumerate.SendMailType;
import com.mtpt.extend.SendMail;

public class ExtendMethod {
	
	private static Logger log = Logger.getLogger(ExtendMethod.class);
	private static String url = "133.96.79.52";//IP地址
	private static int port = 21;
	private static String user = "data_combine";//ftp的账号
	private static String paw = "Celue2018)&)(";//ftp的登录密码
	private static String path = "/data_out/";//ftp服务器上文件所在目录
	private static String urlpath = "D:/data_out/";//文件保存的本地文件路径
	
	public static void downloadFtpFile() {
		FTPClient ftpClient = connectFTP();
		String filepath	= downloadFile(ftpClient);
		SendMail.sendMailForCommon("运营数据导出文件", filepath,SendMailType.YUNYING);
	}
	/**
	 * 
	 * @return
	 * 建立对ftp服务器的连接
	 * 
	 */
	private static FTPClient connectFTP() {
		FTPClient ftpClient = new FTPClient();
		int reply = 0;
		try {
			ftpClient.connect(url, port);
			//解决中文乱码问题
            ftpClient.setControlEncoding("utf-8");
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
			ftpClient.login(user, paw);
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ftpClient;
	}
	
	/**
	 * 
	 * @param ftpClient
	 * @return
	 * 下载指定的ftp文件，按照指定的时间进行获取
	 * 
	 */
	private static String downloadFile(FTPClient ftpClient) {
		String localpath = "";
		if(path.startsWith("/")&&path.endsWith("/")){ 
			String directory = path; //path是在ftp服务器上文件所在的文件夹。使用"/文件夹/“格式
			
			try {
				ftpClient.changeWorkingDirectory(directory);//更换目录到文件所在目录
				ftpClient.enterLocalPassiveMode();
				FTPFile[] filelist = ftpClient.listFiles();//获取ftp服务器下所有文件
				if (filelist!=null) {
					for(int i=0;i<filelist.length;i++) {
						if (filelist[i].isFile()) {
							String filename = filelist[i].getName();
							Date date = filelist[i].getTimestamp().getTime();
							date = addDay(date, 1);//文件最后修改时间加一天
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
							String datestr = sdf.format(date);
							String nowstr = sdf.format(new Date());
							
							localpath = urlpath+filename;
							if (datestr.equals(nowstr)) {//判断当前时间与文件最后修改时间格式相同的时间是否相等
								log.info("当前发送文件的属性有：文件名"+filename);
								createFile(localpath);
								File file = new File(localpath);
								FileOutputStream fos = new FileOutputStream(file);
								log.info("导出文件");
								ftpClient.retrieveFile(filename, fos);
							}
						}
					}
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return localpath;
	}
	/*************************************************
	 * 
	 * @param allpath
	 * @return
	 * 创建指定的文件
	 */
	private static boolean createFile(String allpath) {
		File file = new File(allpath);
		boolean isCreate = false;
		if (!file.exists()) {
			try {
				isCreate = file.createNewFile();
				if(isCreate) {
					log.info("文件创建成功");
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				isCreate = false;
				log.error("创建文件失败");
				log.error(e.getMessage());
			}
		}
		return isCreate;
	}
	
	private static void disconnectFTP(FTPClient ftpClient) {
		if (ftpClient!=null&& ftpClient.isConnected()) {
			try {
				ftpClient.logout();
				ftpClient.disconnect();
				log.info("关闭ftp连接和登录");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				log.error("关闭ftp连接出现异常");
			}
			
		}
	}
	
	/**
	 * 
	 * @param bean
	 * @return
	 * 获取数据统计的邮件发送的表格的内容
	 * 
	 */
	public static String getMessageStr(DataTotal bean) {
		String res = "下行总数是："+bean.getMtall()+"<br>"
				+"下行成功数："+bean.getMtsuc()+"<br>"
				+"上行总数:"+bean.getMoall()+"<br>"
				+"上行有效："+bean.getMoable()+"<br>"
				+"订单总数："+bean.getOrderall()+"<br>"
				+"订单成功："+bean.getOrdersuc()+"<br><br><br><br><br>";
		res += "<table style=\"text-align:center;\" border=\"1\">\n" + 
				"<colgroup>\n" + 
				"<col width=\"12.5%\">\n" + 
				"<col width=\"12.5%\">\n" + 
				"<col width=\"12.5%\">\n" + 
				"<col width=\"12.5%\">\n" + 
				"<col width=\"12.5%\">\n" + 
				"<col width=\"12.5%\">\n" + 
				"<col width=\"12.5%\">\n" + 
				"<col width=\"12.5%\">\n" + 
				"</colgroup>\n" + 
				"<thead>\n" + 
				"<tr>\n" + 
				"<td  colspan=8>营销数据统计</td>\n" + 
				"</tr>\n" + 
				"<tr>\n" + 
				"<td>下行(总数)</td>\n" + 
				"<td>下行(成功)</td>\n" + 
				"<td>上行(总数)</td>\n" + 
				"<td>上行(有效)</td>\n" + 
				"<td>	低消上行(有效)</td>\n" + 
				"<td>冰激凌上行(有效)</td>\n" + 
				"<td>流量包上行(有效)</td>\n" + 
				"<td>冰激凌预约</td>\n" + 
				"</tr>\n" + 
				"</thead>\n" + 
				"<tbody>\n" + 
				"<tr>\n" + 
				"<td>"+bean.getMtall()+"</td>\n" + 
				"<td>"+bean.getMtsuc()+"</td>\n" + 
				"<td>"+bean.getMoall()+"</td>\n" + 
				"<td>"+bean.getMoable()+"</td>\n" +
				"<td>"+bean.getMoabledx()+"</td>\n" + 
				"<td>"+bean.getMoableice()+"</td>\n" + 
				"<td>"+bean.getMoablellb()+"</td>\n" +
				"<td>"+bean.getIcesuc()+"</td>\n" + 
				"</tr>\n" + 
				"<tr>\n" + 
				"<td>下行率(下行成功/下行总数)</td>\n" + 
				"<td>回复率(上行(总数)/下行(成功))</td>\n" + 
				"<td>低消上行率（低消上行数/上行总）</td>\n" + 
				"<td>流量包上行率（流量包上行数/上行总）</td>\n" + 
				"<td colspan=2>冰激凌上行率（冰激凌上行数/上行总）</td>\n" + 
				"<td colspan=2>回复订购率(上行有效/上行总)</td>\n" + 
				"</tr>\n" + 
				"<tr>\n" + 
				"<td>"+bean.getMtrate()+"</td>\n" + 
				"<td>"+bean.getMorate()+"</td>\n" + 
				"<td>"+bean.getModxrate()+"</td>\n" + 
				"<td>"+bean.getMollbrate()+"</td>\n" + 
				"<td colspan=2>"+bean.getMoicerate()+"</td>\n" + 
				"<td colspan=2>"+bean.getResorderrate()+"</td>\n" + 
				"</tr>\n" + 
				"<tr>\n" + 
				"<td>订购数</td>\n" + 
				"<td>订购成功数</td>\n" +
				"<td>低消订购成功</td>\n" + 
				"<td>流量包订购成功</td>\n" +  
				"<td>订购率</td>\n" + 
				"<td>订购成功率</td>\n" + 
				"<td>低消订购成功率</td>\n" + 
				"<td>流量包订购成功率</td>\n" + 
				"</tr>\n" + 
				"<tr>\n" + 
				"<td>"+bean.getOrderall()+"</td>\n" + 
				"<td>"+bean.getOrdersuc()+"</td>\n" + 
				"<td>"+bean.getOrdersucdx()+"</td>\n" + 
				"<td>"+bean.getOrdersucllb()+"</td>\n" + 
				"<td>"+bean.getOrderrate()+"</td>\n" + 
				"<td>"+bean.getOrdersucrate()+"</td>\n" + 
				"<td>"+bean.getOrdersucdxrate()+"</td>\n" + 
				"<td>"+bean.getOrdersucllbrate()+"</td>\n" + 
				"</tr>\n" +
				"</tbody>\n" + 
				"</table>";
		return res;
	}

	/**
	 * 
	 * @param date
	 * @param num
	 * @return
	 * 在当前时间上添加一天
	 * 
	 */
	public static Date addDay(Date date, int num) {
		Calendar startDT = Calendar.getInstance();
		startDT.setTime(date);
		startDT.add(Calendar.DAY_OF_MONTH, num);
		return startDT.getTime();
	}
}
