package com.caizhixiang.model;

import java.util.Date;


/**
 * 描述： 地区（t_area）
 * 
 * 作者： lhm 时间： 2017-07-31 13:59:56
 */
public class Area extends IdEntity {

	private static final long serialVersionUID = 1L;

	/** 地区编码 */
	private Integer areaCode;

	/** 地区名称 */
	private String areaName;

	/** 地区级别 */
	private String areaLevel;

	/** 上级地区编码 */
	private Integer parentAreaCode;

	/** 上级地区名称 */
	private String parentAreaName;

	/** 邮政编码前缀 */
	private String postalCodePrefix;

	/** 城市等级 */
	private String cityLevel;

	/** 城市拼音 */
	private String cityPinyin;

	/** 城市拼音缩写 */
	private String pinyinShort;

	/** 城市首字母 */
	private String cityInitials;

	/** 中文名称缩写 */
	private String nameAbbreviation;

	/** 是否省会城市（0 否 1 是） */
	private Long isProvincial;

	/** 是否是直辖市（0 否，1 是） */
	private Long isMunicipality;

	/** 显示顺序 */
	private Integer displayOrder;

	/** 状态（0作废 1 启用） */
	private Integer statu;

	/** 创建时间 */
	private Date createTime;
	
	/** 是否是自定义地区(Y：是  N  ： 否) */
	private String isCustom;

	/** 是否不设区县的地级市（Y ：是    N：否） */
	private String noDistrictsOrCountries;
	
	@Override
	public String toString() {
		return "Area [areaCode=" + areaCode + ", areaName=" + areaName + ", areaLevel=" + areaLevel
				+ ", parentAreaCode=" + parentAreaCode + ", parentAreaName=" + parentAreaName + ", postalCodePrefix="
				+ postalCodePrefix + ", cityLevel=" + cityLevel + ", cityPinyin=" + cityPinyin + ", pinyinShort="
				+ pinyinShort + ", cityInitials=" + cityInitials + ", nameAbbreviation=" + nameAbbreviation
				+ ", isProvincial=" + isProvincial + ", isMunicipality=" + isMunicipality + ", displayOrder="
				+ displayOrder + ", statu=" + statu + ", createTime=" + createTime + ", isCustom=" + isCustom
				+ ", noDistrictsOrCountries=" + noDistrictsOrCountries + "]";
	}

	public String getIsCustom() {
		return isCustom;
	}

	public void setIsCustom(String isCustom) {
		this.isCustom = isCustom;
	}

	public String getNoDistrictsOrCountries() {
		return noDistrictsOrCountries;
	}

	public void setNoDistrictsOrCountries(String noDistrictsOrCountries) {
		this.noDistrictsOrCountries = noDistrictsOrCountries;
	}

	public Integer getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(Integer areaCode) {
		this.areaCode = areaCode;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getAreaLevel() {
		return areaLevel;
	}

	public void setAreaLevel(String areaLevel) {
		this.areaLevel = areaLevel;
	}

	public Integer getParentAreaCode() {
		return parentAreaCode;
	}

	public void setParentAreaCode(Integer parentAreaCode) {
		this.parentAreaCode = parentAreaCode;
	}

	public String getParentAreaName() {
		return parentAreaName;
	}

	public void setParentAreaName(String parentAreaName) {
		this.parentAreaName = parentAreaName;
	}

	public String getPostalCodePrefix() {
		return postalCodePrefix;
	}

	public void setPostalCodePrefix(String postalCodePrefix) {
		this.postalCodePrefix = postalCodePrefix;
	}

	public String getCityLevel() {
		return cityLevel;
	}

	public void setCityLevel(String cityLevel) {
		this.cityLevel = cityLevel;
	}

	public String getCityPinyin() {
		return cityPinyin;
	}

	public void setCityPinyin(String cityPinyin) {
		this.cityPinyin = cityPinyin;
	}

	public String getPinyinShort() {
		return pinyinShort;
	}

	public void setPinyinShort(String pinyinShort) {
		this.pinyinShort = pinyinShort;
	}

	public String getCityInitials() {
		return cityInitials;
	}

	public void setCityInitials(String cityInitials) {
		this.cityInitials = cityInitials;
	}

	public String getNameAbbreviation() {
		return nameAbbreviation;
	}

	public void setNameAbbreviation(String nameAbbreviation) {
		this.nameAbbreviation = nameAbbreviation;
	}

	public Long getIsProvincial() {
		return isProvincial;
	}

	public void setIsProvincial(Long isProvincial) {
		this.isProvincial = isProvincial;
	}

	public Long getIsMunicipality() {
		return isMunicipality;
	}

	public void setIsMunicipality(Long isMunicipality) {
		this.isMunicipality = isMunicipality;
	}

	public Integer getDisplayOrder() {
		return displayOrder;
	}

	public void setDisplayOrder(Integer displayOrder) {
		this.displayOrder = displayOrder;
	}

	public Integer getStatu() {
		return statu;
	}

	public void setStatu(Integer statu) {
		this.statu = statu;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	

}