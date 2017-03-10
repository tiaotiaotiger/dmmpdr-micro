package com.bocom.enums;

/**
 * ClassName:DataServerImplementClassEnum
 * Function: 数据服务器类别
 * Date:     2017年1月6日 下午2:43:13
 * @author   chenzz
 * @since    JDK 1.6
 */
public enum DataServerImplementClassEnum {
	MYSQL("MYSQL"), ORACLE("ORACLE"), DB2("DB2"), MSSQL("MSSQL");

	private String code;

	private DataServerImplementClassEnum(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
