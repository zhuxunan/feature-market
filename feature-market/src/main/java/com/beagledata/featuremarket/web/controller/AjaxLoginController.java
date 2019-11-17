package com.beagledata.featuremarket.web.controller;

import com.beagledata.commons.AjaxResult;
import com.beagledata.featuremarket.common.ApiCode;
import com.beagledata.featuremarket.common.Constants;
import com.beagledata.featuremarket.service.UserService;
import com.beagledata.featuremarket.shiro.UserType;
import com.beagledata.featuremarket.shiro.orm.entity.Permission;
import com.beagledata.featuremarket.shiro.orm.entity.Role;
import com.beagledata.featuremarket.shiro.orm.entity.User;
import com.beagledata.featuremarket.shiro.overwrite.UserLoginToken;
import com.beagledata.featuremarket.shiro.realm.UserDbRealm;
import com.beagledata.featuremarket.web.vo.LoginResponse;
import com.beagledata.utils.EncodeUtil;
import com.beagledata.utils.StringUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.mgt.RealmSecurityManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.DefaultSessionKey;
import org.apache.shiro.session.mgt.SessionKey;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.List;

/**
 * @author guozc
 *
 * 2018年7月17日
 */
@RestController
@RequestMapping("login")
public class AjaxLoginController {
	@Autowired
	private UserService userService;
	/**
	 * @author guozc
	 * 供货商登陆
	 */
	@RequestMapping("ajaxLogin")
	public AjaxResult supplierLogin(User userInfo) {
		AjaxResult result = new AjaxResult();
	    Subject subject = SecurityUtils.getSubject();
	    UserLoginToken token = new UserLoginToken(userInfo.getUserName().trim(), EncodeUtil.encodeMD5(userInfo.getPassword().trim()));
	    token.setLoginType(UserType.NORMAL);
	    try {  
	        subject.login(token);
			LoginResponse response = buildUserResponse(subject,userInfo);
			result.setData(response);
		}  catch (IncorrectCredentialsException e) {
	    	result.setCode(ApiCode.UN_CORRECT_PASS);
	    }  catch (AuthenticationException e) {  
	    	result.setCode(ApiCode.UN_CORRECT_PASS);  
	    } catch (Exception e) {  
	    	result.setCode(AjaxResult.CODE_ERROR);  
	    }  
	    return result;  
	}
	
	/**
	 * 
	 * @author guozc
	 *
	 * 2019年7月19日
	 * 检查token 是否有效
	 */
	@RequestMapping(value = "check", method = RequestMethod.POST)
	public AjaxResult check(String token) {
	    if (StringUtil.isNotBlank(token)) {
	        SessionKey key = new DefaultSessionKey(token);
	        try {
				Session session = SecurityUtils.getSecurityManager().getSession(key);
				if (session != null) {
					return AjaxResult.newSuccess();
				}
			} catch (Exception e){}
	    }
	    //默认返回失败
	    return AjaxResult.newError();
	}
	
	/**
	 * 构建商户登录返回参数
	 * @param subject
	 * @return
	 */
	private LoginResponse buildUserResponse(Subject subject, User userInfo) {
		LoginResponse response = new LoginResponse();
		User user = (User)subject.getSession().getAttribute(Constants.SEESION_USER_KEY);
		user = userService.getByUsername(user.getUserName(), true);
		List<Role> roles = user.getRoles();
		List<String> permissions = new ArrayList<String>();
		for (Role role : roles) {
			for (Permission permission : role.getPermissions()) {
				permissions.add(permission.getCode());
			}
		}
		response.setStoreName(user.getStoreName());
		response.setToken(subject.getSession().getId().toString());
		response.setPermissions(permissions);
		RealmSecurityManager securityManager =
	                (RealmSecurityManager) SecurityUtils.getSecurityManager();
		UserDbRealm userDbRealm = (UserDbRealm)securityManager.getRealms().iterator().next();
	        //删除登录人对应的缓存
		userDbRealm.getAuthorizationCache().remove(subject.getPrincipals());
	        //重新加载subject
	    subject.releaseRunAs();

		return response;
	}

}
