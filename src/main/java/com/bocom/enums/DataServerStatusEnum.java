package com.bocom.enums;

public enum DataServerStatusEnum {
	ONLINE(1, "在线"), OFFLINE(0, "离线");

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

	private DataServerStatusEnum(Integer code, String name) {
		this.code = code;
		this.name = name;
	}

	private DataServerStatusEnum() {

	}

	public static String[] getAllCode() {
		DataServerStatusEnum[] values = DataServerStatusEnum.values();
		String[] tmp = new String[values.length];
		for (int i = 0; i < tmp.length; i++) {
			tmp[i] = values[i].getCode().toString();
		}
		return tmp;
	}

	public static String getNameByCode(String code) {

		if (code.equals("0")) {
			return "离线";
		} else {
			return "在线";
		}
	}

}
