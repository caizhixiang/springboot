package com.caizhixiang.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.caizhixiang.model.IntegralDTO;
@Repository
public interface PoiMapper {

	public int batchSave(List<IntegralDTO> list);
	
	public List<IntegralDTO> findAll();
}
