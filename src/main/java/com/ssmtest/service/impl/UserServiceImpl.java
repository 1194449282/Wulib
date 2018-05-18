package com.ssmtest.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssmtest.dao.UserDao;
import com.ssmtest.entity.PageBean;
import com.ssmtest.entity.User;
import com.ssmtest.service.UserService;

/**
 * User类业务层实现类
 *
 * @author Peng
 * @Date2016年12月13日上午9:54:56
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return userDao.deleteByPrimaryKey(id);
    }

    @Override
    public int insertSelective(User record) {

        return userDao.insertSelective(record);
    }

    @Override
    public User selectByPrimaryKey(Integer id) {

        return userDao.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(User record) {
        return userDao.updateByPrimaryKeySelective(record);
    }

    @Override
    public List<User> selectUserList() {

        return userDao.selectUserList();
    }

    @Override
    public int selectCount() {
        return userDao.selectCount();
    }

    @Override
    public User loginByUserNameAndPassword(User record) {

        return userDao.loginByUserNameAndPassword(record);
    }

    @Override
    public PageBean<User> findByPage(int currentPage) {
        // limit目的为了找到每页展示的条目和当前的条目（默认等于0（页数-1再乘以2）从第一个开始查）
        HashMap<String, Object> map = new HashMap<String, Object>();
        PageBean<User> pageBean = new PageBean<User>();

        //封装当前页数  默认为1
        pageBean.setCurrPage(currentPage);


        //每页显示的数据 写死的就等于2
        int pageSize = 2;
        pageBean.setPageSize(pageSize);


        //封装总记录数  查询了一下总个数
        int totalCount = userDao.selectCount();
//		设置给了封装字段总数
        pageBean.setTotalCount(totalCount);


        //封装总页数
        double tc = totalCount;
//		总页数除以2  如果总页数是3/2=1.5  向上取整2 页数就是2
        Double num = Math.ceil(tc / pageSize);//向上取整
//		整数设置给总页数
        pageBean.setTotalPage(num.intValue());
//      给map  添加两个属性  当前页数-1 乘 2
        map.put("start", (currentPage - 1) * pageSize);
//		第二个属性就是2
        map.put("size", pageBean.getPageSize());
        //根据开始字段和每页展示字段总数2 查询对应的users
        List<User> lists = userDao.findByPage(map);
//		把查询出来的users给pagebean  pagebean字段全了
        pageBean.setLists(lists);

        return pageBean;
    }
}
