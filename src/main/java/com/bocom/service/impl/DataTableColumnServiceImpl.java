package com.bocom.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bocom.dao.DataTableColumnDao;
import com.bocom.entity.DataTableColumn;
import com.bocom.service.DataTableColumnService;

@Service
public class DataTableColumnServiceImpl implements DataTableColumnService {
	@Autowired
	private DataTableColumnDao dataTableColumnDao;

	@Override
	public int deleteBatchByTbId(String tbIds) {
		List<String> idList = Arrays.asList(tbIds.split(","));
		// 删除table信息
		dataTableColumnDao.deleteBatchByTbId(idList);
		return 1;
	}

	@Override
	public int[] addBatch(List<DataTableColumn> data) {
		dataTableColumnDao.addBatch(data);
		int[] returnVal = new int[data.size()];
		for (int i = 0, len = data.size(); i < len; i++) {
			returnVal[i] = Integer.valueOf(data.get(i).getId());
		}
		return returnVal;
	}
}
