package com.caizhixiang.service;

import java.util.List;

import com.caizhixiang.model.IntegralDTO;

public interface PoiService {
	public int batchSave(List<IntegralDTO> list);
	
	public List<IntegralDTO> findAll();
}
