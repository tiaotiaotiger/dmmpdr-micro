package com.bocom.enums;

public enum DataServerDataTypeEnum {
	MASTER(1, "主服务器"), SLAVE(2, "备服务器");

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

	private DataServerDataTypeEnum(Integer code, String name) {
		this.code = code;
		this.name = name;
	}

	private DataServerDataTypeEnum() {

	}

}
