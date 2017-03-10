package com.bocom.util;

import java.io.UnsupportedEncodingException;

@SuppressWarnings("all")
public class ResponseUtil {
	private static String charset = "UTF-8";

	public static String success() {
		return success(null);
	}

	public static String success(Object object) {
		ResponseVo responseVo = new ResponseVo();
		responseVo.setSuccess(true);
		responseVo.setData(object);
		return getBASE64(JsonUtil.toJSon(responseVo));
	}

	public static String fail(String msg) {
		return fail(msg, null);
	}

	public static String fail(String msg, Object object) {
		ResponseVo responseVo = new ResponseVo();
		responseVo.setSuccess(false);
		responseVo.setData(object);
		responseVo.setMessage(msg);
		return getBASE64(JsonUtil.toJSon(responseVo));
	}

	public static String getBASE64(String s) {
		if (s == null)
			return null;
		try {
			return (new sun.misc.BASE64Encoder()).encode(s.getBytes(charset));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}
}
