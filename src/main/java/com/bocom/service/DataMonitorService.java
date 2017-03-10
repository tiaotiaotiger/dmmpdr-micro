package com.bocom.service;

import java.util.List;
import java.util.Map;

import com.bocom.entity.DataServer;

/**
 * 数据监控处理。数据服务器的状态更新；数据表数据量更新
 * 
 * @author user
 *
 */
public interface DataMonitorService {
	/**
	 * 更新服务器下所有表及字段信息
	 * @param list 服务器信息
	 * @param param 参数
	 */
	public void updateTableNColumn(List<DataServer> list);

	/**
	 * 更新服务器状态包括部分更新和全部更新
	 * @param list 服务器信息
	 * @param param 参数
	 */
	public void updateServerStatus(List<DataServer> list, Map<String,Object> param);

	/**
	 * 更新表数据量
	 * @param tableId 表ID
	 * @param tableName 表名
	 */
	public void updateTableCount(Map<String, Object> param);
}
