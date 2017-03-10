package com.bocom.service;

import java.util.List;
import java.util.Map;

import com.bocom.entity.DataTable;

/**
 * 数据表接口
 * 
 * @author chenzz
 */
public interface DataTableService {
	/**
	 * 删除数据表存在两种情况 1、只能删除废止的数据表；2、删除数据元信息（其他表不使用）
	 */
	public int delete(String id);

	/**
	 * 用于废止等调用
	 */
	public int updateMultiple(Map<String, Object> param);

	/**
	 * 提供数据名称，表名，所属部门，所属区域 模糊查询
	 */
	public List<DataTable> listDataByParam(Map<String, Object> param);

	/*
	 * 批量添加
	 */
	public int[] addBatch(List<DataTable> data);

}




