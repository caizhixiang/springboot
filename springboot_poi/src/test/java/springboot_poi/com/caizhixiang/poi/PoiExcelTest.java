package springboot_poi.com.caizhixiang.poi;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.converters.CalendarConverter;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.junit.Test;
import org.springframework.util.ResourceUtils;

import com.caizhixiang.util.DatePatternEnum;
import com.caizhixiang.util.DateUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PoiExcelTest {
	@Test
	public void readExcel() throws FileNotFoundException, IOException {
		List<IntegralDTO> result=new ArrayList<>();
		HSSFWorkbook workbook=new HSSFWorkbook(new FileInputStream(ResourceUtils.getFile("classpath:test.xls")));
		HSSFSheet sheet = workbook.getSheetAt(0);
		int rowNum = sheet.getLastRowNum();
		int cellNum = sheet.getRow(0).getLastCellNum();
		for (int i=1;i<rowNum+1;i++) {
			HSSFRow row = sheet.getRow(i);
			for(int j=0;j<cellNum;j++){
				HSSFCell cell = row.getCell(j);
				if(j==3||j==4){
					continue;
				}
				
				if(cell!=null){
					cell.setCellType(Cell.CELL_TYPE_STRING);//将数字类型的字段转为字符类型
				}
			}
			IntegralDTO dto=new IntegralDTO();
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
			
			result.add(dto);
			
		}
		
		result.stream().forEach(System.out::println);
	}
	
}
