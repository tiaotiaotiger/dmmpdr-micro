package com.bocom.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bocom.entity.DataServer2Table;
import com.bocom.service.DataServer2TableService;
import com.bocom.service.DataServerService;
import com.bocom.service.DataTableColumnService;
import com.bocom.service.DataTableService;
import com.bocom.service.DeleteServerAndTableService;

@Service
public class DeleteServerAndTableServiceImpl implements
		DeleteServerAndTableService {
	@Autowired
	private DataServerService dataServerService;
	@Autowired
	private DataServer2TableService dataServer2TableService;
	@Autowired
	private DataTableService dataTableService;
	@Autowired
	private DataTableColumnService dataTableColumnService;

	@Override
	public Integer delete(String serverIds) {
		List<String> id = new ArrayList<String>();
		String[] str = serverIds.split(",");
		for (int i = 0; i < str.length; i++) {
			id.add(str[i]);
		}
		dataServerService.deleteBatch(id);
		for (String serverId : id) {
			List<DataServer2Table> tableIds = dataServer2TableService
					.selectTableIdByServerId(serverId);
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("serverId", serverId);
			dataServer2TableService.deleteByRefId(param);
			for (DataServer2Table dataServer2Table : tableIds) {
				String Id = dataServer2Table.getTableId();
				dataTableService.delete(Id);
				dataTableColumnService.deleteBatchByTbId(Id);
			}
		}
		return 1;
	}
}
