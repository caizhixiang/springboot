package com.caizhixiang.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.caizhixiang.biz.AreaBiz;
import com.caizhixiang.model.Area;
import com.caizhixiang.service.AreaService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AreaServiceImpl implements AreaService {
	
	@Autowired
	private AreaBiz areaBiz;

	@Override
	public Map<String, Object> findPage(int pageNum, int pageSize, Map<String, Object> params) {
		Map<String, Object> result = Maps.newHashMap();
		PageHelper.startPage(pageNum, pageSize);
		List<Area> list=areaBiz.findByParams(params);
		PageInfo<Area> page = new PageInfo<Area>(list);
		if (page != null) {
			result.put("totalCount", page.getTotal());
			result.put("data", page.getList());
		}
		return result;
	}

	@Override
	public Area findById(Long id) {
		log.info("findById start,param:{}",id);
		Area area = areaBiz.findById(id);
		return area;
	}


}
