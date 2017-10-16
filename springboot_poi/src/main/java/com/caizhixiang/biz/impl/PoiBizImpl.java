package com.caizhixiang.biz.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.caizhixiang.biz.PoiBiz;
import com.caizhixiang.mapper.PoiMapper;
import com.caizhixiang.model.IntegralDTO;
@Service
public class PoiBizImpl implements PoiBiz {

	@Autowired
	private PoiMapper poiMapper;
	@Override
	public int batchSave(List<IntegralDTO> list) {
		// TODO Auto-generated method stub
		return poiMapper.batchSave(list);
	}

}
