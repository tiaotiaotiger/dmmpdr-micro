package com.bocom.dao;

import java.util.List;
import java.util.Map;

import com.bocom.entity.DataServer2Table;

public interface DataServer2TableDao {
	public int addBatch(List<DataServer2Table> data);
	
	public int deleteBatch(List<String> ids);
	
	public int deleteByRefId(Map<String, Object> param);

	public List<DataServer2Table> listDataByParam(Map<String, Object> param);
	
	public List<DataServer2Table> selectServerIdBytableId(String tableIds);
}
