package com.beagledata.featuremarket.shiro.orm.entity;

import com.beagledata.commons.orm.entity.BaseEntity;

/**
 * @author guozc
 *
 *	权限许可
 */
public class Permission extends BaseEntity{
	private static final long serialVersionUID = -6740978915745153866L;
	private String code;//权限代码
	private String name;//权限名称
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}

}
