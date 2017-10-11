package com.caizhixiang.biz.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.caizhixiang.biz.AreaBiz;
import com.caizhixiang.mapper.AreaMapper;
import com.caizhixiang.model.Area;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 描述： 地区（t_area） 接口实现
 * 
 * 作者： lhm 时间： 2017-07-31 13:59:57
 */
@Service
public class AreaBizImpl implements AreaBiz {

	@Autowired
	private AreaMapper AreaDao;

	@Override
	public PageInfo<Area> findPage(int pageNum, int pageSize, Map<String, Object> params) {
		PageHelper.startPage(pageNum, pageSize);
		List<Area> list = AreaDao.findByParams(params);
		PageInfo<Area> page = new PageInfo<Area>(list);
		return page;
	}

	@Override
	public Area get(Long id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		List<Area> list = AreaDao.findByParams(params);
		if (list == null || list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}

	@Override
	public List<Area> findByIds(List<Long> ids) {
		return AreaDao.findByIds(ids);
	}

	@Override
	public List<Area> findByExample(Area Area) {
		return AreaDao.findByExample(Area);
	}

	@Override
	public int count(Area Area) {
		return AreaDao.count(Area);
	}

	@Override
	public List<Area> findByParams(Map<String, Object> params) {
		return AreaDao.findByParams(params);
	}

	@Override
	public int countByParams(Map<String, Object> params) {
		return AreaDao.countByParams(params);
	}

	@Override
	public List<Area> findAll() {
		return AreaDao.findAll();
	}

	@Override
	public int save(Area Area) {
		return AreaDao.save(Area);
	}

	@Override
	public int update(Area Area) {
		return AreaDao.update(Area);
	}

	@Override
	public int delete(Long id) {
		return AreaDao.delete(id);
	}

	@Override
	public int saveOrUpdate(Area Area) {
		return AreaDao.saveOrUpdate(Area);
	}

	@Override
	public int batchSave(List<Area> AreaList) {
		return AreaDao.batchSave(AreaList);
	}

	@Override
	public int batchUpdate(List<Area> AreaList) {
		return AreaDao.batchUpdate(AreaList);
	}

	@Override
	public int batchDelete(List<Long> ids) {
		return AreaDao.batchDelete(ids);
	}

	@Override
	public Area findById(Long id) {
		return AreaDao.findById(id);
	}

	@Override
	public List<Area> getProvinceInfo() {
		// TODO Auto-generated method stub
		return AreaDao.getProvinceInfo();
	}

	@Override
	public List<Area> getCityInfo() {
		// TODO Auto-generated method stub
		return AreaDao.getCityInfo();
	}
	@Override
	public List<Area> queryProvince() throws Exception {
		// TODO Auto-generated method stub
		return AreaDao.queryProvince();
	}

	@Override
	public List<Area> findProvinceInfo() {
		// TODO Auto-generated method stub
		return AreaDao.findProvinceInfo();
	}

	public List<Area> queryCityAndDistrict(Area area) throws Exception {
		// TODO Auto-generated method stub
		return AreaDao.findByExample(area);
	}

	@Override
	public List<Area> findByParamsByOrder(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return AreaDao.findByParamsByOrder(params);
	}
}
