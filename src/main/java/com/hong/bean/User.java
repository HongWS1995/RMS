package com.hong.bean;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

public class User {

	private Integer id;

	private String username;
	@JsonDeserialize
	private String password;

	private String tel;
	
	private String address;
	
	private String islogin;

	private Integer loginTimes;

	private String lastestLoginTime;

	private String isstaff;

	private Integer ptypeId;

	private Integer canlogin;

	private Ptype ptype;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getIslogin() {
		return islogin;
	}

	public void setIslogin(String islogin) {
		this.islogin = islogin;
	}

	public Integer getLoginTimes() {
		return loginTimes;
	}

	public void setLoginTimes(Integer loginTimes) {
		this.loginTimes = loginTimes;
	}

	public String getLastestLoginTime() {
		return lastestLoginTime;
	}

	public void setLastestLoginTime(String lastestLoginTime) {
		this.lastestLoginTime = lastestLoginTime;
	}

	public String getIsstaff() {
		return isstaff;
	}

	public void setIsstaff(String isstaff) {
		this.isstaff = isstaff;
	}

	public Integer getPtypeId() {
		return ptypeId;
	}

	public void setPtypeId(Integer ptypeId) {
		this.ptypeId = ptypeId;
	}

	public Integer getCanlogin() {
		return canlogin;
	}

	public void setCanlogin(Integer canlogin) {
		this.canlogin = canlogin;
	}

	public Ptype getPtype() {
		return ptype;
	}

	public void setPtype(Ptype ptype) {
		this.ptype = ptype;
	}
	
}