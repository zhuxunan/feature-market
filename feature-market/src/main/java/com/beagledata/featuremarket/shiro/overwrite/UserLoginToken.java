package com.beagledata.featuremarket.shiro.overwrite;

import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * @author guozc
 * 2018年7月17日
 */
public class UserLoginToken extends UsernamePasswordToken {
	 
	private static final long serialVersionUID = 983173081932367916L;
	/**
	 * 判断登录类型
	 */
	private String loginType;
	/**
	 * 微信登录openid
	 */
	private String openid;
 
	public String getLoginType() {
		return this.loginType;
	}
 
	public void setLoginType(String loginType) {
		this.loginType = loginType;
	}
 
	/**
	 * Shiro 构造方法
	 */
	public UserLoginToken(String username, String password) {
		super(username, password);
	}
 
	public UserLoginToken() {
 
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}
	
}

