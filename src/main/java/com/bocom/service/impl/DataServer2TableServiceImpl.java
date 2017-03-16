package com.bocom.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bocom.dao.DataServer2TableDao;
import com.bocom.entity.DataServer2Table;
import com.bocom.service.DataServer2TableService;

@Service
public class DataServer2TableServiceImpl implements DataServer2TableService {
	@Autowired
	private DataServer2TableDao dataServer2TableDao;

	@Override
	public int[] addBatch(List<DataServer2Table> data) {
		dataServer2TableDao.addBatch(data);
		int[] returnVal = new int[data.size()];
		for (int i = 0, len = data.size(); i < len; i++) {
			returnVal[i] = Integer.valueOf(data.get(i).getId());
		}
		return returnVal;
	}

	@Override
	public List<DataServer2Table> listDataByParam(Map<String, Object> param) {
		return dataServer2TableDao.listDataByParam(param);
	}

	@Override
	public int deleteByRefId(Map<String, Object> param) {
		return dataServer2TableDao.deleteByRefId(param);
	}
	
	@Override
	public List<DataServer2Table> selectTableIdByServerId(String serverId) {
		
		return dataServer2TableDao.selectTableIdByServerId(serverId);
	}
}
