package com.bocom.dao;

import java.util.List;
import java.util.Map;

import com.bocom.entity.DataTable;

public interface DataTableDao {
	public int deleteBatch(List<String> id);

	public int updateMultiple(Map<String, Object> param);

	public List<DataTable> listDataByParam(Map<String, Object> param);

	public int addBatch(List<DataTable> data);

}
