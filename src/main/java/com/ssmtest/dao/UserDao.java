package com.ssmtest.dao;

import java.util.HashMap;
import java.util.List;

import com.ssmtest.entity.User;

public interface UserDao {
    /**
     * 根据主键删除一条用户数据
     *
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 插入一条用户数据 ，可以不完整
     *
     * @param record
     * @return
     */
    int insertSelective(User record);

    /**
     * 根据主键查询一条用户数据
     *
     * @param id
     * @return
     */
    User selectByPrimaryKey(Integer id);

    /**
     * 用户登录
     *
     * @param record
     * @return
     */
    User loginByUserNameAndPassword(User record);

    /**
     * 根据主键更新一条用户数据，可以不完整 -
     *
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(User record);

    /**
     * 查询所有用户数据
     *
     * @return
     */
    List<User> selectUserList();

    /**
     * 查询用户记录总数
     *
     * @return
     */
    int selectCount();

    /**
     * 分页操作，调用findByPage limit分页方法
     *
     * @param map
     * @return
     */
    List<User> findByPage(HashMap<String, Object> map);
}