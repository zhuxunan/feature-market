package com.beagledata.featuremarket.service.impl;

import com.beagledata.commons.AjaxResult;
import com.beagledata.featuremarket.service.UserService;
import com.beagledata.featuremarket.shiro.orm.entity.*;
import com.beagledata.featuremarket.shiro.orm.mapper.*;
import com.beagledata.featuremarket.utils.UserSessionHolder;
import com.beagledata.utils.EncodeUtil;
import com.beagledata.utils.IdUtil;
import com.beagledata.utils.StringUtil;
import org.apache.ibatis.transaction.TransactionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author guozc
 */
@Service
public class UserServiceImpl implements UserService {
	private Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);
	private static final int PAGE_SIZE = 10;		//页容量
	@Autowired
	private UserMapper userMapper;
	@Autowired 
	private PermissionMapper permissionMapper;
	@Autowired
	private RoleMapper roleMapper;
	@Autowired
	private UserRoleMapper userRoleMapper;
	@Autowired
	private RolePermissionMapper rolePermissionMapper;
	@Override
	public User getUserByName(String userName) {
		try {
			return userMapper.getUser(userName);
		} catch (Exception e) {
			LOG.error("UserServiceImpl getUserByName error userName = " + userName, e);
		}
		return null;
	}
	@Override
	public User getByUsername(String username, boolean initRoles) {
		try {
			User user = userMapper.getByUserName(username);
			if (user != null && initRoles) {
				List<Role> roles = roleMapper.listByUser(user.getId());
				if (!roles.isEmpty()) {
					for (Role r : roles) {
						r.setPermissions(permissionMapper.listByRole(r.getId()));
					}
					user.setRoles(roles);
				}
			}
			
			return user;
		} catch (Exception e) {
			LOG.error("UserServiceImpl getByUsername error; username: " + username + " initRoles: " + initRoles, e);
		}
		return null;
	}
	/**
	 * @author guozc
	 * 2018年4月19日
	 */
	@Override
	public AjaxResult changePassword(String oldpassword, String newpassword) {
		try {
			if (StringUtil.isBlank(oldpassword) || StringUtil.isBlank(newpassword)) {
				return AjaxResult.newError().withMsg("新密码或旧密码为空");
			}
			User user = UserSessionHolder.get();
			int rows = userMapper.updatePassword(user.getUserName(), EncodeUtil.encodeMD5(oldpassword.trim()), EncodeUtil.encodeMD5(newpassword.trim()));
			if (rows < 1) {
				return AjaxResult.newError();
			}
		} catch (Exception e) {
			LOG.error("UserServiceImpl changePassword error oldpassword = " + oldpassword + " newpasword = " + newpassword, e);
			return AjaxResult.newError();
		}
		return AjaxResult.newSuccess();
	}

	@Override
	public List<Role> getRoleListByUserId(int userId) {
		try {
			return roleMapper.listByUser(userId);
		} catch (Exception e) {
			LOG.error("UserServiceImpl getRoleListByUserId error. userId = " + userId,e);
		}
		return Collections.emptyList();
	}
	/**
	 * @author guozc
	 *
	 * 2018年5月3日
	 */
	@Override
	public AjaxResult getByPage(int pageNo) {
		AjaxResult result = new AjaxResult();
		try {
			if (pageNo < 1) {
				pageNo = 1;
			}
			int count = userMapper.selectCount();
			int pageCount = (count + (PAGE_SIZE - 1)) / PAGE_SIZE;
			List<User> users = userMapper.selectPage((pageNo-1)*PAGE_SIZE,PAGE_SIZE);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("pageCount", pageCount);
			map.put("count", count);
			map.put("list", users);
			result.setData(map);
		} catch (Exception e) {
			LOG.error("UserServiceImpl getByPage error pageNo = " + pageNo, e);
			result.setCode(AjaxResult.CODE_ERROR);
		}
		return result;
	}
	/**
	 * @author guozc
	 *
	 * 2018年5月3日
	 */
	@Override
	public AjaxResult addUser(User user, int roleId) {
		try {
			if (StringUtil.isBlank(user.getUserName())) {
				return AjaxResult.newError().withMsg("用户名不合法");
			}
			if (StringUtil.isBlank(user.getPassword())) {
				return AjaxResult.newError().withMsg("密码不合法 ");
			}
			Role role = roleMapper.selectById(roleId);
			if (role == null) {
				return AjaxResult.newError().withMsg("角色不合法 ");
			}
            User u = userMapper.getByUserName(user.getUserName());
			if (null != u) {
			    return AjaxResult.newError().withMsg("用户名已被使用");
            }
            //将密码加密
			user.setPassword(EncodeUtil.encodeMD5(user.getPassword()));
			user.setUuid(IdUtil.UUID());
			userMapper.insert(user);
			UserRole userRole = new UserRole();
			userRole.setRoleId(roleId);
			userRole.setUserId(user.getId());
			userRoleMapper.insert(userRole);
		} catch (Exception e) {
			LOG.error("UserServiceImpl addUser error user = " + user,  e);
			return AjaxResult.newError();
		}
		return AjaxResult.newSuccess();
	}
	/**
	 * @author guozc
	 *
	 * 2018年5月3日
	 */
	@Override
	public AjaxResult updateAbled(String uuid, int able) {
		if(StringUtil.isBlank(uuid)) {
			return AjaxResult.newError();
		}
		try {
			int rows;
			if (able == 0) {
				rows = userMapper.updateAbled(uuid, true);
			} else {
				rows = userMapper.updateAbled(uuid, false);
			}
			
			if (rows > 0) {
				return AjaxResult.newSuccess();
			}
		} catch (Exception e) {
			LOG.error("UserServiceImpl updateAbled error uuid = " + uuid + " able = " + able,  e);
			return AjaxResult.newError();
		}
		return AjaxResult.newError();
	}

	/**
	 * @author guozc
	 *
	 * 2018年5月4日
	 */
	@Override
	public AjaxResult updateUser(User user, int roleId) {
		try {
            if (StringUtil.isNotBlank(user.getPassword())) {
                user.setPassword(EncodeUtil.encodeMD5(user.getPassword()));
            }
            userMapper.updateUser(user);
			Role role = roleMapper.selectById(roleId);
			if (role == null) {
				return AjaxResult.newError();
			}
			User user2 = userMapper.selectByUuid(user.getUuid());
			if (user2 == null) {
				return AjaxResult.newError();
			}
			userRoleMapper.updateRoleByUserId(user2.getId(), roleId);
		} catch (Exception e) {
			LOG.error("UserServiceImpl updateUser error user = " + user + " roleId = " + roleId,  e);
			return AjaxResult.newError();
		}
		
		return AjaxResult.newSuccess();
	}
	/**
	 * @author guozc
	 *
	 * 2018年6月28日
	 */
	@Override
	@Transactional
	public AjaxResult addRole(String code, String name, String ids) {
		try {
			String[] idArray = ids.split(",");//逗号分隔拆分
			if(idArray.length > 0) {
				if (checkRole(code, name)) {
					//不存在名称或者code相同的角色可以正常添加
					Role role = new Role();
					role.setCode(code);
					role.setName(name);
					roleMapper.insert(role);
					for (String permissionId : idArray) {
						RolePermission rolePermission = new RolePermission();
						rolePermission.setPermissionId(Integer.valueOf(permissionId));
						rolePermission.setRoleId(role.getId());
						rolePermissionMapper.insert(rolePermission);
					}
					return AjaxResult.newSuccess();
				} else {
					return AjaxResult.newError();
				}
			} else {
				return AjaxResult.newError();
			}
		} catch (Exception e) {
			LOG.error("UserServiceImpl addRole error ids = " + ids, e);
			throw new TransactionException();
		}
	}
	/**
	 * @author guozc
	 * 通过name 跟code判断是否已经存在角色
	 */
	private boolean checkRole(String code, String name) {
		Role role2 = roleMapper.selectByNameAndCode(code, name);
		if (role2 != null) {
			return false;
		} else {
			return true;
		}
	}
	/**
	 * @author guozc
	 *
	 * 2018年6月28日
	 */
	@Override
	public AjaxResult getRoles() {
		try {
			List<Role> list = roleMapper.selectList();
			for (Role role : list) {
				role.setPermissions(permissionMapper.listByRole(role.getId()));
			}
			return AjaxResult.newSuccess().withData(list);
		} catch (Exception e) {
			LOG.error("UserServiceImpl getRoles error ", e);
			return AjaxResult.newError();
		}
	}
	/**
	 * @author guozc
	 *
	 * 2018年6月28日
	 */
	@Transactional
	@Override
	public AjaxResult roleUpdate(String permission, int roleId) {
		try {
			String[] idArray = permission.split(",");//逗号分隔拆分
			if(idArray.length > 0) { 
				//解除原来的角色权限的关联关系
				rolePermissionMapper.deleteByRole(roleId);
				for (String permissionId : idArray) {
					RolePermission rolePermission = new RolePermission();
					rolePermission.setPermissionId(Integer.valueOf(permissionId));
					rolePermission.setRoleId(roleId);
					rolePermissionMapper.insert(rolePermission);
				}
				return AjaxResult.newSuccess();  
			} else {
				return AjaxResult.newError();
			}
		} catch (Exception e) {
			LOG.error("UserServiceImpl roleUpdate error permission = " + permission + " roleId = " + roleId , e);
		}
		throw new TransactionException();
	}
	/**
	 * @author guozc
	 *
	 * 2018年6月28日
	 */
	@Override
	public AjaxResult getPermissionList() {
		try {
			List<Permission> list = permissionMapper.selectList();
			return AjaxResult.newSuccess().withData(list);
		} catch (Exception e) {
			LOG.error("UserServiceImpl getPermissionList error", e);
			return AjaxResult.newError();
		}
	}

}
