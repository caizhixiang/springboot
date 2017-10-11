package com.caizhixiang.mapper;



import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.caizhixiang.model.Area;


/**
 * 描述： 操作 地区（t_area）
 * 
 * 作者： lhm
 * 时间： 2017-07-31 13:59:57
 */
@Repository
public interface AreaMapper {

	public Area findById(Long id);
	
	public List<Area> findByIds(List<Long> ids);
	
	public List<Area> findByExample(Area area);
	
	public int count(Area area);
	
	public List<Area> findByParams(Map<String, Object> params);
	
	public int countByParams(Map<String, Object> params);
	
	public List<Area> findAll();

	public int save(Area area);

    public int update(Area area);

    public int delete(Long id);
    
    public int saveOrUpdate(Area area);
    
    public int batchSave(List<Area> areaList);
    
    public int batchUpdate(List<Area> areaList);
    
    public int batchDelete(List<Long> ids);

	public List<Area> getProvinceInfo();

	public List<Area> getCityInfo();
	
	public List<Area> findProvinceInfo();
	public List<Area> queryProvince();

	/**
	 * 按照指定顺序查询地区信息
	 * @param params
	 * @return
	 */
	public List<Area> findByParamsByOrder(Map<String, Object> params);
 }