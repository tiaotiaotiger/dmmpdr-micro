package com.bocom.enums;

public enum DataTableImpLevelEnum {
	IMPORTANT(1, "重要"), NORMAL(2, "一般"), OTHER(3, "其他");

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

	private DataTableImpLevelEnum(Integer code, String name) {
		this.code = code;
		this.name = name;
	}

	private DataTableImpLevelEnum() {
	}

	public static String[] getAllCode() {
		DataTableImpLevelEnum[] values = DataTableImpLevelEnum.values();
		String[] tmp = new String[values.length];
		for (int i = 0; i < tmp.length; i++) {
			tmp[i] = values[i].getCode().toString();
		}
		return tmp;
	}

	public static String getNameByCode(String code) {

		if (code.equals("1")) {
			return "重要";
		} else if (code.equals("2")) {
			return "一般";
		} else {
			return "其他";
		}
	}
}
