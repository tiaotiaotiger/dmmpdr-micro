package com.bocom.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bocom.dao.DataMonitorHisDao;
import com.bocom.entity.DataMonitorHis;
import com.bocom.service.DataMonitorHisService;

@Service
public class DataMonitorHisServiceImpl implements DataMonitorHisService {

	@Autowired
	private DataMonitorHisDao dao;

	@Override
	public int addBatch(List<DataMonitorHis> list) {
		return dao.addBatch(list);
	}

	@Override
	public List<DataMonitorHis> listDataByParam(Map<String, Object> params) {
		return dao.listDataByParam(params);
	}

}
