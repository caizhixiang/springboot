package com.caizhixiang.util;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.hssf.util.HSSFColor;

import com.caizhixiang.model.IntegralDTO;

//http://www.zslin.com/web/article/detail/30
public class PoiExportUtil {
	  public static HSSFWorkbook generateExcel(List<IntegralDTO> list, String title) {  
	        HSSFWorkbook book = new HSSFWorkbook();  
	        try{  
	            File desFile = new File("d:\\人员表.xls");  
	            FileOutputStream fos = new FileOutputStream(desFile);  
	                        HSSFSheet sheet = book.createSheet("Sheet1");  
	            sheet.autoSizeColumn(1, true);//自适应列宽度  
	            //样式设置  
	            HSSFCellStyle style = book.createCellStyle();  
	            style.setFillForegroundColor(HSSFColor.SKY_BLUE.index);  
	              style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);  
	              style.setBorderBottom(HSSFCellStyle.BORDER_THIN);  
	              style.setBorderLeft(HSSFCellStyle.BORDER_THIN);  
	              style.setBorderRight(HSSFCellStyle.BORDER_THIN);  
	              style.setBorderTop(HSSFCellStyle.BORDER_THIN);  
	              style.setAlignment(HSSFCellStyle.ALIGN_CENTER);  
	              // 生成一个字体  
	              HSSFFont font = book.createFont();  
	              font.setColor(HSSFColor.VIOLET.index);  
	              font.setFontHeightInPoints((short) 12);  
	              font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);  
	              // 把字体应用到当前的样式  
	              style.setFont(font);  
	              
	                
	              HSSFCellStyle style2 = book.createCellStyle();  
	                  style2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);  
	                  //设置上下左右边框  
	                  style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);  
	                  style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);  
	                  style2.setBorderRight(HSSFCellStyle.BORDER_THIN);  
	                  style2.setBorderTop(HSSFCellStyle.BORDER_THIN);  
	                  style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);  
	                    
	              //填充表头标题  
//	              int colSize = list.get(0).entrySet().size();  
//	              System.out.println("size:" + colSize);  
	              //合并单元格供标题使用(表名)  
//	              sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, colSize-1));  
	              HSSFRow firstRow = sheet.createRow(0);//第几行（从0开始）  
	              HSSFCell firstCell = firstRow.createCell(0);  
	              firstCell.setCellValue(title);  
	              firstCell.setCellStyle(style);  
	                
	              //填充表头header  
	              HSSFRow row = sheet.createRow(1);  
	              row.createCell(0).setCellValue("serialNum");
	              row.createCell(1).setCellValue("orderNo");
	              row.createCell(2).setCellValue("integral");
	              row.createCell(3).setCellValue("orderTime");
	              row.createCell(4).setCellValue("orderStatus");
	              row.createCell(5).setCellValue("payStatus");
	              row.createCell(6).setCellValue("sendStatus");
	              row.createCell(7).setCellValue("getAccount");
	              row.createCell(8).setCellValue("goodsName");
	              row.createCell(9).setCellValue("faceValue");
	              row.createCell(10).setCellValue("calcPrice");
	              row.createCell(11).setCellValue("quantity");
	              
//	              row.createCell(0).setCellStyle(style2);  
	                
	              //填充表格内容  
	              for(int i=0; i<list.size(); i++) {  
	                  HSSFRow row2 = sheet.createRow(i+2);//index：第几行  
	                  IntegralDTO dto = list.get(i);  
	                  row2.createCell(0).setCellValue(dto.getSerialNum());
	                  row2.createCell(1).setCellValue(dto.getOrderNo());
	                  row2.createCell(2).setCellValue(dto.getIntegral());
	                  row2.createCell(3).setCellValue(dto.getOrderTime());
	                  row2.createCell(4).setCellValue(dto.getOrderStatus());
	                  row2.createCell(5).setCellValue(dto.getPayStatus());
	                  row2.createCell(6).setCellValue(dto.getSendStatus());
	                  row2.createCell(7).setCellValue(dto.getGetAccount());
	                  row2.createCell(8).setCellValue(dto.getGoodsName());
	                  row2.createCell(9).setCellValue(dto.getFaceValue());
	                  row2.createCell(10).setCellValue(dto.getCalcPrice());
	                  row2.createCell(11).setCellValue(dto.getQuantity());
	              }  
	                
//	           book.write(fos);   
//	           fos.close();  
	        } catch(Exception ex) {  
	            ex.printStackTrace();  
	        }  
	        return book;  
	    }  
}
