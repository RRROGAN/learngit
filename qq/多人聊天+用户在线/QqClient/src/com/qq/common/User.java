/**
 * 这是用户信息类
 */
package com.qq.common;

public class User implements java.io.Serializable {
	//序列化，使之能在网络中传输
	private String userId;
	private String passwd;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
}
