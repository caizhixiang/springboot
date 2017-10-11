package com.caizhixiang.biz;

/**
 * 
 * Licensed under The MIT License (MIT)
 */

import java.util.List;
import java.util.Map;

import com.caizhixiang.model.Area;
import com.github.pagehelper.PageInfo;

/**
 * 描述： 地区（t_area） 接口
 * 
 * 作者： lhm
 * 时间： 2017-07-31 13:59:57
 */
public interface AreaBiz {

	public PageInfo<Area> findPage(int pageNum, int pageSize, Map<String, Object> params);

	public Area get(Long id);
	
	public Area findById(Long id);
	
	public List<Area> findByIds(List<Long> ids);
	
	public List<Area> findByExample(Area Area);
	
	public int count(Area Area);
	
	public List<Area> findByParams(Map<String, Object> params);
	
	public int countByParams(Map<String, Object> params);
	
	public List<Area> findAll();

	public int save(Area Area);

    public int update(Area Area);

    public int delete(Long id);
    
    public int saveOrUpdate(Area Area);
    
    public int batchSave(List<Area> AreaList);
    
    public int batchUpdate(List<Area> AreaList);
    
    public int batchDelete(List<Long> ids);

	public List<Area> getProvinceInfo();

	public List<Area> getCityInfo();
	
	public List<Area> findProvinceInfo();
	/**
	 * 查询出所有省
	 * @param params
	 * @return
	 */
	public List<Area> queryProvince()throws Exception;
	
	/**
	 * 查询出市和县
	 * @param params
	 * @return
	 */
	public List<Area> queryCityAndDistrict(Area area)throws Exception;

	/**
	 * 按照指定顺序展示数据
	 * @param params
	 * @return
	 */
	public List<Area> findByParamsByOrder(Map<String, Object> params);
	
}