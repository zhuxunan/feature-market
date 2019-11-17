package com.beagledata.featuremarket.shiro;

import org.apache.shiro.web.servlet.AdviceFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author guozc
 *
 * 2018年4月23日
 */
public class FormFilter extends AdviceFilter {
	@Override 
	protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
	       HttpServletRequest httpRequest = WebUtils.toHttp(request);
	       HttpServletResponse httpResponse = WebUtils.toHttp(response);
	       httpResponse.setHeader("Access-control-Allow-Origin", "*");
           httpResponse.setHeader("Access-Control-Allow-Methods", httpRequest.getMethod());
           httpResponse.setHeader("Access-Control-Allow-Headers", httpRequest.getHeader("Access-Control-Request-Headers"));
	       if (httpRequest.getMethod().equals(RequestMethod.OPTIONS.name())) {
	           httpResponse.setStatus(HttpStatus.OK.value());
	           return false;
	       }
	       return super.preHandle(request, response);
	   }
}
