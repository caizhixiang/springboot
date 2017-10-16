package com.caizhixiang.web.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.caizhixiang.model.IntegralDTO;
import com.caizhixiang.service.PoiService;
import com.caizhixiang.util.ExcelImportUtil;
import com.caizhixiang.web.result.ResultBean;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class PoiController {

	@Autowired
	private PoiService poiService;
	@RequestMapping("/")
	public String index(){
		
		return "poiExcel";
	}
	
	@RequestMapping("/uploadFile")
	@ResponseBody
	public ResultBean<Void> importExcel(MultipartFile uploadFile,HttpServletRequest request) throws IOException{
		ResultBean<Void> resultBean=new ResultBean<>();
		if (uploadFile==null) {
			log.info("---上传的文件不存在---");
			resultBean.setMsg("上传的文件不存在");
			return resultBean;
		}
		String filename = uploadFile.getOriginalFilename();
		InputStream inputStream = uploadFile.getInputStream();
		
		List<IntegralDTO> parseExcel = ExcelImportUtil.parseExcel(inputStream);
		if(CollectionUtils.isEmpty(parseExcel)){
			log.info("上传的文件不能为空");
			resultBean.setMsg("上传的文件不能为空");
			return resultBean;
		}
		int save = poiService.batchSave(parseExcel);
		log.info("batchSave result:",save);
		if (save>0) {
			log.info("上传成功");
			resultBean.setMsg("上传成功");
			return resultBean;
		}
		
		resultBean.setMsg("上传失败");
		return resultBean;
	}
}
