package com.bocom.service;

import java.util.List;
import java.util.Map;

import com.bocom.entity.DataMonitorHis;

public interface DataMonitorHisService {

	public int addBatch(List<DataMonitorHis> list);

	public List<DataMonitorHis> listDataByParam(Map<String, Object> params);
}
