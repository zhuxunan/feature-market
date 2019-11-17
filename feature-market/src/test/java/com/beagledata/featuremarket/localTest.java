package com.beagledata.featuremarket;

import com.alibaba.fastjson.JSON;
import com.beagledata.featuremarket.service.UserService;
import com.beagledata.featuremarket.shiro.orm.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = FeatureMarketApplication.class)
public class localTest {
    @Autowired
    UserService userService;

    @Test
    public void test1() {
        User admin = userService.getByUsername("admin", true);
        System.out.println(JSON.toJSONString(admin));
    }
}
