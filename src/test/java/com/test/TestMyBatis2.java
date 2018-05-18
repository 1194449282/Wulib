package com.test;


import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.ssmtest.entity.User;
import com.ssmtest.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class) // 表示继承了SpringJUnit4ClassRunner类
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
/**
 * spring测试
 *
 * @author Peng
 * @Date2016年12月13日上午11:52:56
 */
public class TestMyBatis2 {
    //TestMyBatis2类定义的日志信息就能够显示出来了
    private static Logger logger = Logger.getLogger(TestMyBatis2.class);

    @Resource
    private UserService userService = null;

    @Test
    public void testselectByPrimaryKey() {
        User user = userService.selectByPrimaryKey(1);
        //logger.info("值：" + user.toString());
        logger.info(JSON.toJSONString(user));//JSon格式的内容
    }
}
