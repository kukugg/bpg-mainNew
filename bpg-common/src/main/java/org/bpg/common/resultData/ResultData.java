package org.bpg.common.resultData;

import java.util.HashMap;
import java.util.Map;

/**
 * @Title:R
 * @Description:返回数据	
 * @Author hjp	
 * @Date 2018年6月6日
 */
public class ResultData extends HashMap<String, Object> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ResultData() {
		put("code", 0);
		put("msg", "success");
	}
	
	public static ResultData error() {
		return error(500, "未知异常，请联系管理员");
	}
	
	public static ResultData error(String msg) {
		return error(500, msg);
	}
	
	public static ResultData error(int code, String msg) {
		ResultData r = new ResultData();
		r.put("code", code);
		r.put("msg", msg);
		return r;
	}

	public static ResultData ok(String msg) {
		ResultData r = new ResultData();
		r.put("msg", msg);
		return r;
	}
	
	public static ResultData ok(Map<String, Object> map) {
		ResultData r = new ResultData();
		r.putAll(map);
		return r;
	}
	
	public static ResultData ok() {
		return new ResultData();
	}

	@Override
	public ResultData put(String key, Object value) {
		super.put(key, value);
		return this;
	}

}
