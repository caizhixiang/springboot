package com.caizhixiang.web.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.caizhixiang.model.IntegralDTO;
import com.caizhixiang.service.PoiService;
import com.caizhixiang.util.DatePatternEnum;
import com.caizhixiang.util.ExcelImportUtil;
import com.caizhixiang.util.PoiExportUtil;
import com.caizhixiang.web.result.ResultBean;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class PoiController {

	@Autowired
	private PoiService poiService;

	@RequestMapping("/")
	public String index() {

		return "poiExcel";
	}

	@RequestMapping("/uploadFile")
	@ResponseBody
	public ResultBean<Void> importExcel(MultipartFile uploadFile, HttpServletRequest request) throws IOException {
		ResultBean<Void> resultBean = new ResultBean<>();
		if (uploadFile == null) {
			log.info("---上传的文件不存在---");
			resultBean.setMsg("上传的文件不存在");
			return resultBean;
		}
		String filename = uploadFile.getOriginalFilename();
		InputStream inputStream = uploadFile.getInputStream();

		List<IntegralDTO> parseExcel = ExcelImportUtil.parseExcel(inputStream);
		if (CollectionUtils.isEmpty(parseExcel)) {
			log.info("上传的文件不能为空");
			resultBean.setMsg("上传的文件不能为空");
			return resultBean;
		}
		int save = poiService.batchSave(parseExcel);
		log.info("batchSave result:", save);
		if (save > 0) {
			log.info("上传成功");
			resultBean.setMsg("上传成功");
			return resultBean;
		}

		resultBean.setMsg("上传失败");
		return resultBean;
	}

	@RequestMapping("/downloadExcel")
	public void downLoadExcel(HttpServletResponse res) {
		PoiExportUtil<IntegralDTO> excel = new PoiExportUtil<>();
		// 表頭
		String[] headers = { "序号", "订单号", "积分", "订单日期", "订单状态", "支付状态", "发货状态", "收货账号", "商品名称", "面值", "结算价", "数量" };
		List<IntegralDTO> dataset = poiService.findAll();
		BufferedOutputStream bos = null;
		try {
			String fileName = "测试表";
			// 设置response参数，可以打开下载页面
			res.reset();
			res.setContentType("application/vnd.ms-excel;charset=utf-8");
			res.setHeader("Content-Disposition",
					"attachment;filename=" + new String((fileName + ".xls").getBytes(), "iso-8859-1"));
			ServletOutputStream out = res.getOutputStream();

			bos = new BufferedOutputStream(out);
			excel.exportExcel("导出表", headers, dataset, bos, DatePatternEnum.yyyyMMddXHHmmss.getPattern());
		} catch (IOException e) {
			log.error("", e);
		} finally {
			try {
				bos.close();
			} catch (IOException e) {
				log.error("", e);
			}
		}
	}

}
