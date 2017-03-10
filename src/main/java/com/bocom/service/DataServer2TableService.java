package com.bocom.service;

import java.util.List;
import java.util.Map;

import com.bocom.entity.DataServer2Table;

/**
 * 数据服务器与数据表关联接口
 * 
 * @author chenzz
 */
public interface DataServer2TableService {
	/**
	 * 批量添加
	 */
	public int[] addBatch(List<DataServer2Table> data);

	/**
	 * 根据serverId或tableId删除删除
	 */
	public int deleteByRefId(Map<String, Object> param);

	public List<DataServer2Table> listDataByParam(Map<String, Object> param);

}
