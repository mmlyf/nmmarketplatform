package com.mtpt.extend;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.mtpt.bean.Review;
import com.mtpt.bean.TBReview;
import com.mtpt.bean.TBUsers;
import com.mtpt.service.impl.TBReviewService;
import com.mtpt.utilclass.SpringContextUtil;

import sun.tools.jar.resources.jar;

public class OutputFile {
//	private static String alipath = "/Users/lvgordon/Downloads/little/";//测试环境
//	private static String modeldatapath = "/Users/lvgordon/Downloads/little/";//测试环境
	private static String alipath = "E://newplatform/fileout/";//内蒙
	private static String modeldatapath ="E://newplatform/modelfileout/";
	
	private static TBReviewService reviewService = (TBReviewService) SpringContextUtil.getBean("reservice");
	
	/**
	 * 
	 * @param userlist
	 * @return
	 * 导出支付宝绑定用户的支付宝ID和号码
	 * 
	 */
	public static String outputAilPay(List<TBUsers> userlist) {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String namestr = sdf.format(date);
		String filename = namestr +".xlsx";
		String alifilepath = alipath + filename;
		File file = new File(alifilepath);
		if(!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet();
		XSSFRow rowzero = sheet.createRow(0);
		Cell cell1 = rowzero.createCell(0);
		Cell cell2 = rowzero.createCell(1);
		cell1.setCellValue("号码");
		cell2.setCellValue("支付宝ID");
		int i = 1;
		for(TBUsers tbUsers : userlist) {
			XSSFRow row = sheet.createRow(i);
			Cell rowcell1 = row.createCell(0);
			Cell rowcell2 = row.createCell(1);
			rowcell1.setCellValue(tbUsers.getMobile());
			rowcell2.setCellValue(tbUsers.getOpenid());
			i++;
		}
		FileOutputStream fos;
		try {
			fos = new FileOutputStream(file);
			workbook.write(fos);
			workbook.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return alifilepath;
	}
	
	
	/**
	 * 
	 * @param re_id
	 * @return
	 * 导出维度数据中的号码
	 * 
	 */
	public static String exportModelData(int re_id) {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String namestr = sdf.format(date);
		String allpath = modeldatapath + namestr +".txt";
		File file = new File(allpath);
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		sdf = new SimpleDateFormat("yyyy-MM-dd");
		TBReview tbReview = reviewService.selectByPrimaryKey(re_id);
		Review review = new Review();
		review.setCity(tbReview.getCity());
		review.setDangw(tbReview.getDangw());
		review.setProduct(tbReview.getProduct());
		sdf = new SimpleDateFormat("yyyy-MM-dd");
		review.setSecTime(sdf.format(tbReview.getSecTime()));
		review.setSourceType(tbReview.getSourceType());
		try {
			FileWriter fw = new FileWriter(file);
			BufferedWriter bWriter = new BufferedWriter(fw);
//			if (tbReview.getPrelx().equals("dsj_dx_all")) {
//				List<TBDsjDxAll> dxlist = dxService.selectByReview(review);
//				for(TBDsjDxAll tbDsjDxAll:dxlist) {
//					bWriter.write(tbDsjDxAll.getDxDn());
//					bWriter.newLine();
//				}
//				bWriter.flush();
//				bWriter.close();
//			}else {
//				List<TBDsjIceAll> icelist = iceService.selectByReview(review);
//				for (TBDsjIceAll	 tbDsjIceAll:icelist) {
//					bWriter.write(tbDsjIceAll.getDxDn());
//					bWriter.newLine();
//				}
//				bWriter.flush();
//				bWriter.close();
//			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return allpath;
	}
}
