package com.bocom.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bocom.dao.DataTableDao;
import com.bocom.entity.DataTable;
import com.bocom.service.DataAreaTypeService;
import com.bocom.service.DataServer2TableService;
import com.bocom.service.DataTableColumnService;
import com.bocom.service.DataTableService;

@Service
public class DataTableServiceImpl implements DataTableService {
	@Autowired
	private DataTableDao dataTableDao;
	@Autowired
	private DataTableColumnService dataTableColumnService;
	@Autowired
	private DataServer2TableService dataServer2TableService;
	@Autowired
	private DataAreaTypeService dataAreaTypeService;

	@Override
	public int delete(String id) {
		if (StringUtils.isNotEmpty(id)) {
			List<String> idList = Arrays.asList(id.split(","));
			if (null != idList && idList.size() > 0) {
				// 删除table信息
				dataTableDao.deleteBatch(idList);
				// 删除字段信息
				dataTableColumnService.deleteBatchByTbId(id);
			}
		}
		return 1;
	}

	@Override
	public int updateMultiple(Map<String, Object> param) {
		return dataTableDao.updateMultiple(param);
	}

	@Override
	public List<DataTable> listDataByParam(Map<String, Object> param) {
		return dataTableDao.listDataByParam(param);
	}

	@Override
	public int[] addBatch(List<DataTable> data) {
		dataTableDao.addBatch(data);
		int[] returnVal = new int[data.size()];
		for (int i = 0, len = data.size(); i < len; i++) {
			returnVal[i] = Integer.valueOf(data.get(i).getId());
		}
		return returnVal;
	}
}
