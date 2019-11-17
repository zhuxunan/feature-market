package com.beagledata.featuremarket.common;

import com.alibaba.fastjson.support.spring.FastJsonJsonView;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class ExceptionHandler implements HandlerExceptionResolver {  

    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception ex) {  
        ModelAndView mv = new ModelAndView();  
        FastJsonJsonView view = new FastJsonJsonView();  
        Map<String, Object> attributes = new HashMap<String, Object>();  
        if (ex instanceof UnauthenticatedException) {  
            attributes.put("code",ApiCode.UN_CORRECT_PASS);  
        } else if (ex instanceof UnauthorizedException) {  
            attributes.put("code", ApiCode.UN_AUTHORIZATION);  
        } else {  
            attributes.put("code", ApiCode.LOGIN_OVERDUE);  
        }  
        view.setAttributesMap(attributes);  
        mv.setView(view);  
        return mv;  
    }  
}  
