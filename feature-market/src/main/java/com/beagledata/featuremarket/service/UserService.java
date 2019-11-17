package com.beagledata.featuremarket.service;

import com.beagledata.commons.AjaxResult;
import com.beagledata.featuremarket.shiro.orm.entity.Role;
import com.beagledata.featuremarket.shiro.orm.entity.User;
import java.util.List;

/**
 * 
 * @author guozc
 *
 */
public interface UserService {
	
	/**
	 * 
	 * @param userName
	 * @return
	 * @author guozc
	 * 根据用户名获取用户。
	 */
	User getUserByName(String userName);
	/**
	 * 通过用户名获取User
	 * 
	 * @author guozc
	 *
	 * @param username 用户名
	 * @param initRoles 是否初始化角色列表
	 * @return
	 */
	User getByUsername(String username, boolean initRoles);
	/**
	 * @author guozc
	 * 修改密码
	 */
	AjaxResult changePassword(String oldpassword, String newpassword);
	/**
	 * @author guozc
	 * 分页查询用户列表
	 */
	AjaxResult getByPage(int pageNo);
	/**
	 * 根据用户id查询角色列表
	 * @author zhuxn
	 * @param userId
	 * @return
	 */
	List<Role> getRoleListByUserId(int userId);
	/**
	 * @author guozc
	 * 添加用户
	 */
	AjaxResult addUser(User user, int roleId);
	/**
	 * @author guozc
	 * 修改用户可用状态
	 */
	AjaxResult updateAbled(String uuid, int able);
	/**
	 * @author guozc
	 * 修改用户信息
	 */
	AjaxResult updateUser(User user, int roleId);
	/**
	 * @author guozc
	 * 添加用户角色
	 */
	AjaxResult addRole(String code, String name, String ids);
	/**
	 * @author guozc
	 * 查询角色列表
	 */
	AjaxResult getRoles();
	/**
	 * @author guozc
	 * 更新角色
	 */
	AjaxResult roleUpdate(String permission, int roleId);
	/**
	 * @author guozc
	 * 查询系统权限列表
	 */
	AjaxResult getPermissionList();

}
