package com.bocom.enums;

public enum DataMonitorTypeEnum {
	TABBLE_N_COLUMN(0, "表及字段信息"), SERVER_STATUS(1, "服务器状态"), TABLE_COUNT(2,
			"表数据量");
	private Integer code;
	private String name;

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private DataMonitorTypeEnum(Integer code, String name) {
		this.code = code;
		this.name = name;
	}

	private DataMonitorTypeEnum() {
	}

}
