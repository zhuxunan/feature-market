package com.beagledata.featuremarket.shiro.orm.mapper;

import com.beagledata.featuremarket.shiro.orm.entity.UserRole;

/**
 * @author zhuxn
 */
public interface UserRoleMapper {
	/**
	 * 插入用户角色关联关系
	 * @author zhuxn
	 */
	int insert(UserRole userRole);

	/**
	 * 根据userId修改roleId
	 * @param userId
	 * @param roleId
	 * @return
	 */
	int updateRoleByUserId(int userId, int roleId);
}
