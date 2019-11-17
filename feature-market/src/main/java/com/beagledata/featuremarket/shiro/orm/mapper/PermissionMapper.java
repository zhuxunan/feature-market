package com.beagledata.featuremarket.shiro.orm.mapper;


import com.beagledata.featuremarket.shiro.orm.entity.Permission;
import java.util.List;

public interface PermissionMapper {
	/**
	 * @author guozc
	 */
	List<Permission> listByRole(int roleId);
	/**
	 * @author guozc
	 * 查询系统权限
	 */
	List<Permission> selectList();
	
}
