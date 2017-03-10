package com.bocom.service;

import java.util.List;

import com.bocom.entity.DataTableColumn;

/**
 * 数据表字段接口
 * 
 * @author chenzz
 */
public interface DataTableColumnService {

	/**
	 * 根据表ID删出该表对应的字段
	 * 
	 * @param tbId
	 *            表ID
	 */
	public int deleteBatchByTbId(String tbId);

	public int[] addBatch(List<DataTableColumn> data);
}
