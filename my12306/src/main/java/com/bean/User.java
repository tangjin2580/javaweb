package com.bean;

public class User {
	private int id;
	private String username;
	private String password;
	private String realname;
	private String sex;
	private Province province;
	private CertType certType;
	private int number;
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	private String cert;
	private java.sql.Date birthday;
	private String loginIP;


	public String getLoginIP() {
		return loginIP;
	}
	public void setLoginIP(String loginIP) {
		this.loginIP = loginIP;
	}
	public java.sql.Date getBirthday() {
		return birthday;
	}
	public void setBirthday(java.sql.Date birthday) {
		this.birthday = birthday;
	}
	public String getCert() {
		return cert;
	}
	public void setCert(String cert) {
		this.cert = cert;
	}
	public CertType getCertType() {
		return certType;
	}
	public void setCertType(CertType certType) {
		this.certType = certType;
	}
	public Province getProvince() {
		return province;
	}
	public void setProvince(Province province) {
		this.province = province;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
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

}
