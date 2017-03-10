package com.bocom.service;

import java.util.List;
import java.util.Map;

import com.bocom.entity.DataServer;

/**
 * 数据服务器接口
 * 
 * @author chenzz
 */
public interface DataServerService {
	public int[] addBatch(List<DataServer> data);

	public int updateMultiple(Map<String, Object> param);

	/**
	 * 提供IP，用户名，名称 模糊查询
	 */
	public List<DataServer> listDataByParam(Map<String, Object> param);

	public List<DataServer> listDataByTableId(List<String> tableId);
}
