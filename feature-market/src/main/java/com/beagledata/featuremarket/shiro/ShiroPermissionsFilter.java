package com.beagledata.featuremarket.shiro;

import com.alibaba.fastjson.JSONObject;
import com.beagledata.featuremarket.common.ApiCode;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.PermissionsAuthorizationFilter;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author guozc
 *
 * 2018年7月6日
 *  shiro 调用无权限返回json
 */
public class ShiroPermissionsFilter extends PermissionsAuthorizationFilter {

    /**
     * shiro认证perms资源失败后回调方法
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws IOException {
    	 Subject subject = getSubject(request, response);
         // If the subject isn't identified, redirect to login URL
    	 HttpServletResponse httpServletResponse = (HttpServletResponse) response;
    	 Map<String, Object> result = new HashMap<String, Object>();
         if (subject.getPrincipal() == null) {
             //ajax 返回json 表明session已经过期
             result.put("code", ApiCode.LOGIN_OVERDUE);
             httpServletResponse.setCharacterEncoding("UTF-8");
             httpServletResponse.setContentType("application/json");
             httpServletResponse.getWriter().write(JSONObject.toJSONString(result));
         } else {
             result.put("code", ApiCode.UN_AUTHORIZATION);
             httpServletResponse.setCharacterEncoding("UTF-8");
             httpServletResponse.setContentType("application/json");
             httpServletResponse.getWriter().write(JSONObject.toJSONString(result));
         }
        return false;
    }

}
