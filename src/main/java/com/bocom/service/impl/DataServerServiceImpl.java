package com.bocom.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bocom.dao.DataServerDao;
import com.bocom.entity.DataServer;
import com.bocom.service.DataMonitorService;
import com.bocom.service.DataServerService;

@Service
public class DataServerServiceImpl implements DataServerService {
	private static Logger LOG = LoggerFactory
			.getLogger(DataServerServiceImpl.class);
	@Autowired
	private DataServerDao dataServerDao;
	@Autowired
	private DataMonitorService dataMonitorService;

	@Override
	public int[] addBatch(final List<DataServer> list) {
		dataServerDao.addBatch(list);
		// 同步表信息及字段
		new Thread() {
			@Override
			public void run() {
				try {
					LOG.info("Sync TableAndColumn starting...");
					dataMonitorService.updateTableNColumn(list);
					LOG.info("Sync TableAndColumn success.");
				} catch (Exception e) {
					LOG.error("Sync TableAndColumn error", e);
				}
			}
		}.start();

		int[] returnVal = new int[list.size()];
		for (int i = 0, len = list.size(); i < len; i++) {
			returnVal[i] = Integer.valueOf(list.get(i).getId());
		}
		return returnVal;
	}

	@Override
	public int updateMultiple(Map<String, Object> param) {
		return dataServerDao.updateMultiple(param);
	}

	@Override
	public List<DataServer> listDataByParam(Map<String, Object> param) {
		return dataServerDao.listDataByParam(param);
	}

	@Override
	public List<DataServer> listDataByTableId(List<String> tableId) {
		return dataServerDao.listDataByTableId(tableId);
	}
	
	@Override
	public int deleteBatch(List<String> id) {
		return dataServerDao.deleteBatch(id);
	}
}
