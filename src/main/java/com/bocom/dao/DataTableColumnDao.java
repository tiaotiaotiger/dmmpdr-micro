package com.bocom.dao;

import java.util.List;

import com.bocom.entity.DataTableColumn;

public interface DataTableColumnDao {

	public int deleteBatchByTbId(List<String> tbId);

	public int addBatch(List<DataTableColumn> data);
}
