package com.beagledata.featuremarket.web.controller.admin;

import com.beagledata.commons.AjaxResult;
import com.beagledata.featuremarket.service.UserService;
import com.beagledata.featuremarket.shiro.orm.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhuxn
 * 2019年11月15日
 */
@RestController
@RequestMapping("admin/user")
public class AdminController {
	@Autowired
	private UserService userService;
	/**
	 * @author guozc
	 * 分页查询用户列表
	 */
	@RequestMapping("list/{pageNo}")
	public AjaxResult userList(@PathVariable int pageNo) {
		return userService.getByPage(pageNo);
	}
	
	/**
	 * @author guozc
	 * 添加用户
	 */
	@RequestMapping("add")
	public AjaxResult userAdd(User user, int roleId) {
		return userService.addUser(user,roleId);
	}
	/**
	 * @author guozc
	 * 修改可用状态
	 */
	@RequestMapping("abled")
	public AjaxResult userAbled(String uuid, int able) {
		return userService.updateAbled(uuid, able);
	}
	/**
	 *
	 * @author guozc
	 * @param roleId 必填
	 */
	@RequestMapping("update")
	public AjaxResult userUpdate(User user, int roleId) {
		return userService.updateUser(user, roleId);
	}
	/**
	 * @author guozc
	 * 创建角色
	 */
	@RequestMapping("role/add")
	public AjaxResult roleAdd(String code, String name, String permission) {
		return userService.addRole(code, name, permission);
	}
	/**
	 * @author guozc
	 * 角色列表查询
	 */
	@RequestMapping("role/list")
	public AjaxResult roleList() {
		return userService.getRoles();
	}
	/**
	 * @author guozc
	 * 修改角色对应的权限
	 */
	@RequestMapping("role/update")
	public AjaxResult roleUpdate(String permission, int roleId) {
		try {
			return userService.roleUpdate(permission, roleId);
		} catch (Exception e) {
			return AjaxResult.newError();
		}
	}
	/**
	 * @author guozc
	 * 权限列表查询
	 */
	@RequestMapping("permission/list")
	public AjaxResult permissionList() {
		return userService.getPermissionList();
	}
}
