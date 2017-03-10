package com.bocom.enums;

public enum DataMonitorCategoyEnum {
	AUTOMATIC(1, "自动更新"), MANUAL(2, "手动更新");
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

	private DataMonitorCategoyEnum(Integer code, String name) {
		this.code = code;
		this.name = name;
	}

	private DataMonitorCategoyEnum() {
	}

}
