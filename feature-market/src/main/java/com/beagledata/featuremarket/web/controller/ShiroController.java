package com.beagledata.featuremarket.web.controller;

import com.beagledata.commons.AjaxResult;
import org.apache.shiro.SecurityUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author guozc
 * 2018年3月21日
 */
@RestController
@RequestMapping("shiro")
public class ShiroController {
	/**
	 * @author guozc
	 * 退出登录
	 */
	@RequestMapping("logout")
	public AjaxResult logout() {
		SecurityUtils.getSubject().logout();
		return AjaxResult.newSuccess().withMsg("注销成功");
	}
}
