package com.caizhixiang.service;

import java.util.Map;

import com.caizhixiang.model.Area;

public interface AreaService {

	Map<String, Object> findPage(int pageNum, int pageSize, Map<String, Object> params);

	Area findById(Long id);

}
