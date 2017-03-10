package com.bocom.dao;

import java.util.List;
import java.util.Map;

import com.bocom.entity.DataMonitorHis;

public interface DataMonitorHisDao {

	public int addBatch(List<DataMonitorHis> list);

	public List<DataMonitorHis> listDataByParam(Map<String, Object> params);
}
