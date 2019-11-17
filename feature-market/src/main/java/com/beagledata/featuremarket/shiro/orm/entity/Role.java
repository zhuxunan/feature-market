package com.beagledata.featuremarket.shiro.orm.entity;

import com.beagledata.commons.orm.entity.BaseEntity;

import java.util.List;


/**
 * @author guozc
 *
 *	角色
 */
public class Role extends BaseEntity{
	
	private static final long serialVersionUID = -5149263317574061485L;
	private int id;
	private String code;//角色代码
	private String name;//角色名称
	
	private List<Permission> permissions;//权限列表
	
	public static final class ROLE {
		public static final int ADMIN = 1; //管理员
		public static final int USER = 2; //用户
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Permission> getPermissions() {
		return permissions;
	}
	public void setPermissions(List<Permission> permissions) {
		this.permissions = permissions;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Role [id=").append(id).append(", code=").append(code).append(", name=").append(name)
				.append(", permissions=").append(permissions).append("]");
		return builder.toString();
	}
	
	 
}
