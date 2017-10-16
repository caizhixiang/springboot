package com.caizhixiang.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.caizhixiang.biz.PoiBiz;
import com.caizhixiang.model.IntegralDTO;
import com.caizhixiang.service.PoiService;
@Service
public class PoiServiceImpl implements PoiService {

	@Autowired
	private PoiBiz poiBiz;

	@Override
	public int batchSave(List<IntegralDTO> list) {
		// TODO Auto-generated method stub
		return poiBiz.batchSave(list);
	}

	@Override
	public List<IntegralDTO> findAll() {
		// TODO Auto-generated method stub
		return poiBiz.findAll();
	}
	
}
