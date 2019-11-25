package com.program.pojo;

public class ResultInfo {
	// 表示状态
	private int code;
	// 返回的信息
	private String info;

	public ResultInfo(int code, String info) {
		super();
		this.code = code;
		this.info = info;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

}
