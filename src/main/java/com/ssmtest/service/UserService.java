package com.ssmtest.service;

import java.util.List;

import com.ssmtest.entity.PageBean;
import com.ssmtest.entity.User;

/**
 * User类业务层接口
 *
 * @author Peng
 * @Date2016年12月13日上午9:54:40
 */
public interface UserService {

    int deleteByPrimaryKey(Integer id);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    User loginByUserNameAndPassword(User record);

    List<User> selectUserList();

    int selectCount();

    int updateByPrimaryKeySelective(User record);

    PageBean<User> findByPage(int currentPage);
}
