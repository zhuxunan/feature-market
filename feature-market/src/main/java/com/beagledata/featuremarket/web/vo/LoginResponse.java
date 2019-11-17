package com.beagledata.featuremarket.web.vo;

import org.apache.commons.lang3.builder.ToStringBuilder;
import java.util.List;

/**
 * @author zhuxn
 * 登录返回Vo
 */
public class LoginResponse {
    //登录密钥
    private String token;
    private List<String> permissions;
	/**
	 * 公众号uuid
	 */
	private String pUuid;
	/**
	 * 店铺名称
	 */
	private String storeName;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public List<String> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<String> permissions) {
		this.permissions = permissions;
	}

	public String getpUuid() {
		return pUuid;
	}

	public void setpUuid(String pUuid) {
		this.pUuid = pUuid;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this)
				.append("token", token)
				.append("permissions", permissions)
				.append("pUuid", pUuid)
				.append("storeName", storeName)
				.toString();
	}
}
