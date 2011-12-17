package com.jeremiahxu.learyperi.user.action;

import javax.annotation.Resource;

import org.apache.struts2.config.Action;
import org.apache.struts2.config.ParentPackage;
import org.apache.struts2.config.Result;
import org.apache.struts2.config.Results;
import org.apache.struts2.views.freemarker.FreemarkerResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.jeremiahxu.learyperi.user.pojo.UserProfile;
import com.jeremiahxu.learyperi.user.service.UserService;

/**
 * user action
 * 
 * @author Jeremiah Xu
 * 
 */
@Controller
@ParentPackage(value = "struts-learyperi")
@Action(namespace = "/page/user", name = "user")
@Results({ @Result(name = "login_page", type = FreemarkerResult.class, value = "/page/user/login.xhtml") })
@Scope("request")
public class UserAction {
    protected Logger log = LoggerFactory.getLogger(this.getClass());
    @Resource(name = "userService")
    private UserService userService;
    private UserProfile user;
    private String type;// 进入登录页面的原因类型
    private String tip;// 在登录页面显示的提示信息

    /**
     * 跳转去登录页面。
     * 
     * @return
     * @throws Exception
     */
    public String toLogin() throws Exception {
        // 登录失败后进入登录页面的情况
        if ("login_failure".equals(type)) {
            this.setTip("用户名或密码错误！");
        } else if ("session_timeout".equals(type)) {
            this.setTip("操作超时，请重新登录！");
        } else {
            this.setTip("请输入用户名和密码！");
        }
        log.info("toLogin.");
        return "login_page";
    }

    public UserProfile getUser() {
        return user;
    }

    public void setUser(UserProfile user) {
        this.user = user;
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

}
