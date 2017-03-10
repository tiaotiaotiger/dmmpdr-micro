package com.bocom.dao;

import java.util.List;
import java.util.Map;

import com.bocom.entity.DataServer;

public interface DataServerDao {

	public int addBatch(List<DataServer> data);

	public int updateMultiple(Map<String, Object> param);

	public List<DataServer> listDataByParam(Map<String, Object> param);

	public List<DataServer> listDataByTableId(List<String> tableId);
}
