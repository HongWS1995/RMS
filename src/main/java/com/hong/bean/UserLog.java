package com.hong.bean;

public class UserLog {

	private Integer id;

	private Integer userId;

	private String remoteIp;

	private String alterData;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getRemoteIp() {
		return remoteIp;
	}

	public void setRemoteIp(String remoteIp) {
		this.remoteIp = remoteIp;
	}

	public String getAlterData() {
		return alterData;
	}

	public void setAlterData(String alterData) {
		this.alterData = alterData;
	}
}