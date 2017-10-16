package com.caizhixiang.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.util.IOUtils;

import com.caizhixiang.model.IntegralDTO;

/**
 * @author Administrator
 *
 */
public class ExcelImportUtil {
	  private final static String excel2003L =".xls";    //2003- 版本的excel    
      private final static String excel2007U =".xlsx";   //2007+ 版本的excel    
          
      /**  
       * 描述：获取IO流中的数据，组装成List<List<Object>>对象  
       * @param in,fileName  
       * @return  
       * @throws IOException   
       */    
      public  List<List<Object>> getBankListByExcel(InputStream in,String fileName) throws Exception{    
          List<List<Object>> list = null;    
              
          //创建Excel工作薄    
          Workbook work = this.getWorkbook(in,fileName);    
          if(null == work){    
              throw new Exception("创建Excel工作薄为空！");    
          }    
          Sheet sheet = null;    
          Row row = null;    
          Cell cell = null;    
              
          list = new ArrayList<List<Object>>();    
          //遍历Excel中所有的sheet    
          for (int i = 0; i < work.getNumberOfSheets(); i++) {    
              sheet = work.getSheetAt(i);    
              if(sheet==null){continue;}    
                  
              //遍历当前sheet中的所有行    
              for (int j = sheet.getFirstRowNum()+1; j < sheet.getLastRowNum()+1; j++) {    
                  row = sheet.getRow(j);    
                  if(row==null){continue;}    
                //  if(row==null||row.getFirstCellNum()==j){continue;}    
                      
                  //遍历所有的列    
                  List<Object> li = new ArrayList<Object>();    
                  for (int y = row.getFirstCellNum(); y < row.getLastCellNum(); y++) {    
                      cell = row.getCell(y);    
                      li.add(this.getCellValue(cell));    
                  }    
                  list.add(li);    
              }    
          }    
          //work.close();    
          return list;    
      }    
          
      /**  
       * 描述：根据文件后缀，自适应上传文件的版本   
       * @param inStr,fileName  
       * @return  
       * @throws Exception  
       */    
      public  Workbook getWorkbook(InputStream inStr,String fileName) throws Exception{    
          Workbook wb = null;    
          String fileType = fileName.substring(fileName.lastIndexOf("."));    
          if(excel2003L.equals(fileType)){    
              wb = new HSSFWorkbook(inStr);  //2003-    
          }else if(excel2007U.equals(fileType)){    
//              wb = new XSSFWorkbook(inStr);  //2007+    
          }else{    
              throw new Exception("解析的文件格式有误！");    
          }    
          return wb;    
      }    
      
      /**  
       * 描述：对表格中数值进行格式化  
       * @param cell  
       * @return  
       */    
      public  Object getCellValue(Cell cell){    
          Object value = null;    
          DecimalFormat df = new DecimalFormat("0");  //格式化number String字符    
          SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd");  //日期格式化    
          DecimalFormat df2 = new DecimalFormat("0.00");  //格式化数字    
              
          switch (cell.getCellType()) {    
          case Cell.CELL_TYPE_STRING:    
              value = cell.getRichStringCellValue().getString();    
              break;    
          case Cell.CELL_TYPE_NUMERIC:    
              if("General".equals(cell.getCellStyle().getDataFormatString())){    
                  value = df.format(cell.getNumericCellValue());    
              }else if("m/d/yy".equals(cell.getCellStyle().getDataFormatString())){    
                  value = sdf.format(cell.getDateCellValue());    
              }else{    
                  value = df2.format(cell.getNumericCellValue());    
              }    
              break;    
          case Cell.CELL_TYPE_BOOLEAN:    
              value = cell.getBooleanCellValue();    
              break;    
          case Cell.CELL_TYPE_BLANK:    
              value = "";    
              break;    
          default:    
              break;    
          }    
          return value;    
      }    
          
	
	
	
	
	
	
	
	
	
	
	 public static List<IntegralDTO> parseExcel(InputStream fis) {  
	        List<IntegralDTO> data = new ArrayList<IntegralDTO>();
	        try {  
	        	byte[] buf = IOUtils.toByteArray(fis);  
	        	ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(buf);  
	            HSSFWorkbook book = new HSSFWorkbook(byteArrayInputStream);  
	            HSSFSheet sheet = book.getSheetAt(0);  
	            int firstRow = sheet.getFirstRowNum();  
	            int lastRow = sheet.getLastRowNum();  
	            //遍历每一行
	            for(int i = firstRow + 1; i<lastRow+1; i++) {  
	            	IntegralDTO dto=new IntegralDTO();  
	                HSSFRow row = sheet.getRow(i);  
	                int firstCell = row.getFirstCellNum();  
	                int lastCell = row.getLastCellNum();  
	                //遍历每一个单元格
	                for(int j=firstCell; j<lastCell; j++) {  
	                    if(j==3||j==4){
	                    	continue;
	                    }
	                    HSSFCell cell = row.getCell(j);  
	                    if(cell!=null) {  
	                        cell.setCellType(HSSFCell.CELL_TYPE_STRING);  
	                    }  
	                    
	                }  
	                
	                dto.setSerialNum(row.getCell(0).getStringCellValue());
        			dto.setOrderNo(row.getCell(1).getStringCellValue());
        			dto.setIntegral(row.getCell(2).getStringCellValue());
        			Date time=row.getCell(3).getDateCellValue();
        			Date shortTime=row.getCell(4).getDateCellValue();
        			Calendar c1=Calendar.getInstance();
        			c1.setTime(time);
        			Calendar c2=Calendar.getInstance();
        			c2.setTime(shortTime);
        			c2.set(c1.get(Calendar.YEAR), c1.get(Calendar.MONTH), c1.get(Calendar.DAY_OF_MONTH));
        			dto.setOrderTime(c2.getTime());
        			dto.setOrderStatus(row.getCell(5).getStringCellValue());
        			dto.setPayStatus(row.getCell(6).getStringCellValue());
        			dto.setSendStatus(row.getCell(7).getStringCellValue());
        			dto.setGetAccount(row.getCell(8).getStringCellValue());
        			dto.setGoodsName(row.getCell(9).getStringCellValue());
        			dto.setFaceValue(row.getCell(10).getStringCellValue());
        			dto.setCalcPrice(row.getCell(11).getStringCellValue());
        			dto.setQuantity(row.getCell(12).getStringCellValue());
        			
        			data.add(dto);
	            }  
	        } catch (IOException e) {  
	            e.printStackTrace();  
	        }finally {
				try {
					fis.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}  
	        return data;  
	    }  
}
