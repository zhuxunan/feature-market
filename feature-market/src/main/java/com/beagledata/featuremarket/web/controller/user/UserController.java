package com.beagledata.featuremarket.web.controller.user;

import com.beagledata.commons.AjaxResult;
import com.beagledata.featuremarket.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户模块
 * @author zhuxn
 */
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * @author zhuxn
     * 修改密码
     */
    @RequestMapping("changePwd")
    public AjaxResult changePwd(String oldpassword, String newpassword) {
        return userService.changePassword(oldpassword,newpassword);
    }

}
