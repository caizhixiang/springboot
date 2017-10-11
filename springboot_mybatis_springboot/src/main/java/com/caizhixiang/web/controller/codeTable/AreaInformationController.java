package com.caizhixiang.web.controller.codeTable;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.caizhixiang.service.AreaService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/")
public class AreaInformationController {
	@Autowired
	private AreaService areaService;

	@RequestMapping("/index")
	public String bankListIndex() {

		return "codeTable/areaInformation";
	}

	@RequestMapping("/findPage")
	@ResponseBody
	public Map<String, Object> findPage(@RequestParam(defaultValue = "1") int pageNum,
			@RequestParam(defaultValue = "10") int pageSize, @RequestParam Map<String, Object> params) {
		Map<String, Object> result = areaService.findPage(pageNum, pageSize, params);
		return result;

	}



}
