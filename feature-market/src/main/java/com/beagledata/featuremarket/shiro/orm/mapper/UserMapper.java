package com.beagledata.featuremarket.shiro.orm.mapper;

import com.beagledata.featuremarket.shiro.orm.entity.User;
import org.apache.ibatis.annotations.Param;
import java.util.List;


public interface UserMapper {
	/**
	 * 根据用户名查询用户信息
	 * @author guozc
	 * @param userName
	 * @return
	 */
	User getUser(String userName);
	/**
	 *@author guozc 2017年4月27日
	 */
	User getByUserName(String username);
	/**
	 * @author guozc
	 * 根据用户名与密码查询用户
	 */
	User selectByNameAndPass(String userName, String password);
	/**
	 * @author guozc
	 * 更改用户密码
	 */
	int updatePassword(String userName, String oldpassword, String newpassword);
	/**
	 * @author guozc
	 * 查询用户数量
	 */
	int selectCount();
	/**
	 * @author guozc
	 * 分页查询
	 */
	List<User> selectPage(@Param(value = "start") int start,
						  @Param(value = "pageSize") int pageSize);
	/**
	 * @author guozc
	 * 添加用户
	 */
	int insert(User user);
	/**
	 * @author guozc
	 * 修改用户可用状态
	 */
	int updateAbled(String uuid, boolean abled);
	/**
	 * @author guozc
	 * 修改用户信息
	 */
	int updateUser(User user);
	/**
	 * @author guozc
	 * 通过uuid查询
	 */
	User selectByUuid(String uuid);
	/**
	 * 根据userId查询店铺信息
	 * @param userId
	 * @return
	 */
	User selectById(@Param(value = "userId") int userId);

	/**
	 * 修改店铺名称
	 * @param userName
	 * @param storeName
	 * @return
	 */
	int updateStoreName(@Param(value = "userName") String userName,
                        @Param(value = "storeName") String storeName);
}
