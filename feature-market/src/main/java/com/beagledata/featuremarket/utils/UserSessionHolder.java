package com.beagledata.featuremarket.utils;

import com.beagledata.featuremarket.common.Constants;
import com.beagledata.featuremarket.shiro.orm.entity.User;
import org.apache.shiro.SecurityUtils;

/**
 * @author guozc
 * 2017年4月27日
 */
public class UserSessionHolder {
	/**
	 * 获取session中的User对象
	 * @author guozc
	 */
	public static User get() {
		return (User) SecurityUtils.getSubject().getSession().getAttribute(Constants.SEESION_USER_KEY);
	}
	
	/**
	 * 获取session中的user对象的id
	 * @author guozc
	 */
	public static int getId() {
		return ((User) SecurityUtils.getSubject().getSession().getAttribute(Constants.SEESION_USER_KEY)).getId();
	}
	
	/**
	 * 获取session中的user对象的uuid
	 * @author guozc
	 */
	public static String getUuid() {
		return ((User) SecurityUtils.getSubject().getSession().getAttribute(Constants.SEESION_USER_KEY)).getUuid();
	}
}
