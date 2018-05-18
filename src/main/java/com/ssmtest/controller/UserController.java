package com.ssmtest.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.ssmtest.entity.User;
import com.ssmtest.service.UserService;

@SessionAttributes("currentUser")
@Controller
public class UserController {

    @Resource
    private UserService userService;

    /**
     * 用户登录
     *
     * @param userName
     * @param password
     * @return String
     * @throws Exception
     */
    @RequestMapping("/login")
    public String login(@RequestParam("userName") String userName,
                        @RequestParam("password") String password, Model model) throws Exception {
        User user = new User();
        user.setUsername(userName);
        user.setPassword(password);
        User userresult = userService.loginByUserNameAndPassword(user);
        if (userresult != null) {
            //登录成功
            List<User> lists = userService.selectUserList();
            model.addAttribute("userLists", lists);//回显用户信息
            model.addAttribute("currentUser", userresult.getUsername());
//			如果验证成功重定向到main.jsp页面 省略path因为在springmvc配置了视图解析器
            return "redirect:main";
        }
        return "error";
    }

    @RequestMapping("/main")
//	这个路由重复使用了  如果初次加载访问main currentPage为null  默认等于1了 其实他就是查询语句
    public String main(@RequestParam(value = "currentPage", defaultValue = "1", required = false) int currentPage, Model model) {
        model.addAttribute("pagemsg", userService.findByPage(currentPage));//回显分页数据
//		没有重定向只是把参数返回
        return "main";
    }

    /**
     * 跳到编辑页面
     *
     * @param
     * @param model
     * @return
     */
    @RequestMapping("/edit")
    public String editpage(@RequestParam("id") int id,
                           Model model) {
        User user = userService.selectByPrimaryKey(id);
        model.addAttribute("returnUser", user);
        return "edit";
    }

    /**
     * 保存用户数据
     *
     * @return
     */
    @RequestMapping("/save")
//	他这个很好的利用了一个路由两个操作 （保存，修改）
    public String save(User user) {
        System.out.println(user.toString());
        if (user.getId() == null) {
            //id为null是保存 动态保存有什么属性存什么属性
            userService.insertSelective(user);
        } else {
            //有id值为修改
            userService.updateByPrimaryKeySelective(user);
        }
        return "redirect:main";
    }

    /**
     * 删除用户数据
     *
     * @param id
     * @return
     */
    @RequestMapping("/delete")
    public String delete(@RequestParam("id") int id) {
        userService.deleteByPrimaryKey(id);
        return "redirect:main";
    }

    /**
     * 添加一个用户数据
     *
     * @return
     */
    @RequestMapping("/add")
    public String add(Model model) {
//		一直不让用户去修改id值  就是为了分辨是add 还是修改
        model.addAttribute("returnUser", new User());
//		重定向给deit
        return "edit";
    }
}
