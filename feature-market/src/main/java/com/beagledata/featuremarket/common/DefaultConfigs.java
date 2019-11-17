package com.beagledata.featuremarket.common;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author guozc
 */
@Configuration
@ConfigurationProperties(prefix = "default-configs")
public class DefaultConfigs {
	/**
	 * 应用请求地址
	 */
	private String applicationUrl;
	/**
	 * 移动端服务请求地址
	 */
	private String mobileApplicationUrl;

	public String getApplicationUrl() {
		return applicationUrl;
	}

	public void setApplicationUrl(String applicationUrl) {
		this.applicationUrl = applicationUrl;
	}

	public String getMobileApplicationUrl() {
		return mobileApplicationUrl;
	}

	public void setMobileApplicationUrl(String mobileApplicationUrl) {
		this.mobileApplicationUrl = mobileApplicationUrl;
	}

	@Override
	public String toString() {
		final StringBuffer sb = new StringBuffer("DefaultConfigs{");
		sb.append("applicationUrl='").append(applicationUrl).append('\'');
		sb.append(", mobileApplicationUrl='").append(mobileApplicationUrl).append('\'');
		sb.append('}');
		return sb.toString();
	}
}
