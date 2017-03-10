package com.bocom.enums;

public enum DataAreaTypeEnum {
	CJLYQ(1, "采集来源区"), SJJJQ(2, "数据集结区"), BZKU(3, "标准库区"), ZTKU(4, "专题库区"), JWYPTSJQ(
			5, "警务云平台数据区"), JWYYSJQ(6, "警务应用数据区"), GXQ(7, "共享区"), FZQ(8, "废止区"), WDYQ(
			9, "未定义区");

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

	private DataAreaTypeEnum(Integer code, String name) {
		this.code = code;
		this.name = name;
	}

	private DataAreaTypeEnum() {
	}
}
