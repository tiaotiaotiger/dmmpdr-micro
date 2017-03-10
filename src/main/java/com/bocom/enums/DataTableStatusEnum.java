package com.bocom.enums;

public enum DataTableStatusEnum {
	ABOLISH(0, "废止"), VALID(1, "有效");

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

	private DataTableStatusEnum(Integer code, String name) {
		this.code = code;
		this.name = name;
	}

	private DataTableStatusEnum() {
	}

	public static String[] getAllCode() {
		DataTableStatusEnum[] values = DataTableStatusEnum.values();
		String[] tmp = new String[values.length];
		for (int i = 0; i < tmp.length; i++) {
			tmp[i] = values[i].getCode().toString();
		}
		return tmp;
	}

	public static String getNameByCode(String code) {

		if (code.equals("0")) {
			return "废止";
		} else {
			return "有效";
		}
	}
}
