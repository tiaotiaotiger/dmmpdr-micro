package com.bocom.dao;

import java.util.List;

import com.bocom.entity.DataAreaType;

public interface DataAreaTypeDao {
	public List<DataAreaType> getByName(String areaName);
	
}
