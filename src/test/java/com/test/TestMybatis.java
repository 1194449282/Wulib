package com.test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ssmtest.entity.User;
import com.ssmtest.service.UserService;

public class TestMybatis {
    private ApplicationContext ac = null;
    @Resource
    private UserService userService = null;

    @Before
    public void before() {
        ac = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        userService = (UserService) ac.getBean("userService");
    }

    @Test
    public void testinsertSelective() {
        //User user = new User("李白","1234","libai@163.com","10086","销售主管");
        //User user = new User("李清照","1234","liqingzhao@163.com","10087","高管");
        //User user = new User("杜甫","1234","dufu@163.com","10088","客户经理");
        //User user = new User("岑参","1234","censhen@163.com","10089","系统管理员");
        User user = new User("温庭筠", "1234", "wentingyu@163.com", "10090", "销售主管");
        userService.insertSelective(user);

    }

    @Test
    public void testselectByPrimaryKey() {
        int id = 1;
        User user = userService.selectByPrimaryKey(id);
        System.out.println(user.toString());

    }

    @Test
    public void testselectUserList() {
        List<User> lists = new ArrayList<>();
        lists = userService.selectUserList();
        Iterator<User> it = lists.iterator();
        System.out.println("总记录数：" + userService.selectCount());
        while (it.hasNext()) {
            User user = it.next();
            System.out.println(user.toString());
        }
    }
}
