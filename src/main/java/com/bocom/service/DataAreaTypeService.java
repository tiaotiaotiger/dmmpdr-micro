package com.bocom.service;

import java.util.List;

import com.bocom.entity.DataAreaType;

/**
 * 隶属区域接口
 * 
 * @author chenzz
 */
public interface DataAreaTypeService {
	/**
	 * 根据区域名称查询是否存在
	 */
	public List<DataAreaType> getByName(String areaName);
}
