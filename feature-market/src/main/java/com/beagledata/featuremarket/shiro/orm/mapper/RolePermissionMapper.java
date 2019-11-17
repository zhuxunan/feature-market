package com.beagledata.featuremarket.shiro.orm.mapper;


import com.beagledata.featuremarket.shiro.orm.entity.RolePermission;

public interface RolePermissionMapper {
	/**
	 * @author guozc
	 * 保存权限与角色关联关系
	 */
	int insert(RolePermission rolePermission);
	/**
	 * @author guozc
	 * 删除权限对应的权限
	 */
	int deleteByRole(int roleId);
	/**
	 * @author guozc
	 * 更新
	 */
	int update(int roleId, int pid);
	
}
