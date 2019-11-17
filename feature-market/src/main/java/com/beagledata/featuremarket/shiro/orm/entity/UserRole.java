package com.beagledata.featuremarket.shiro.orm.entity;

import java.io.Serializable;

/**
 * @author liulu
 *	
 *	用户和角色关联表
 */
public class UserRole implements Serializable{

	private static final long serialVersionUID = 6712955567222402187L;
	
	private int userId;//用户id
	private int roleId;//角色id
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	
}
