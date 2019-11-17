package com.beagledata.featuremarket.shiro.orm.entity;

import com.beagledata.commons.orm.entity.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

public class User extends BaseEntity {
	
	private static final long serialVersionUID = -2599970989937179968L;
	private String uuid;
	//用户名
	private String userName;
	//密码
	private String password;
	//是否停用
	private boolean disabled;
	//店铺名称
	private String storeName;
	
	private List<Role> roles;		//用户角色
	
	public User(){
		
	}
	
	public User(String userName,String password){
		this.userName = userName;
		this.password = password;
	}

	@Override
	public String getUuid() {
		return uuid;
	}

	@Override
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isDisabled() {
		return disabled;
	}

	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this)
				.append("uuid", uuid)
				.append("userName", userName)
				.append("password", password)
				.append("disabled", disabled)
				.append("storeName", storeName)
				.append("roles", roles)
				.toString();
	}
}
