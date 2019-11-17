package com.beagledata.featuremarket.shiro.orm.entity;

import java.io.Serializable;

/**
 * @author guozc
 *
 * 角色和权限关联
 */
public class RolePermission implements Serializable{
	
	private static final long serialVersionUID = -3462114712436224585L;
	
	private int roleId;//角色id
	private int permissionId;//权限id
	
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public int getPermissionId() {
		return permissionId;
	}
	public void setPermissionId(int permissionId) {
		this.permissionId = permissionId;
	}

}
