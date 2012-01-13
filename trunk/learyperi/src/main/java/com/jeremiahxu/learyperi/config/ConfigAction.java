package com.jeremiahxu.learyperi.config;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.config.Action;
import org.apache.struts2.config.ParentPackage;
import org.apache.struts2.config.Result;
import org.apache.struts2.config.Results;
import org.apache.struts2.views.freemarker.FreemarkerResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;

import com.jeremiahxu.learyperi.LearyperiException;
import com.jeremiahxu.learyperi.menu.pojo.Menu;
import com.jeremiahxu.learyperi.menu.service.MenuService;
import com.jeremiahxu.learyperi.user.pojo.OrgProfile;
import com.jeremiahxu.learyperi.user.pojo.ResProfile;
import com.jeremiahxu.learyperi.user.pojo.RoleProfile;
import com.jeremiahxu.learyperi.user.pojo.UserProfile;
import com.jeremiahxu.learyperi.user.service.OrgService;
import com.jeremiahxu.learyperi.user.service.ResService;
import com.jeremiahxu.learyperi.user.service.RoleService;
import com.jeremiahxu.learyperi.user.service.UserService;

/**
 * 系统安装初始化的操作类
 * 
 * @author Jeremiah Xu
 * 
 */
@Controller
@ParentPackage(value = "struts-learyperi")
@Action(namespace = "/page/config", name = "setup")
@Results({ @Result(name = "setup", type = FreemarkerResult.class, value = "setup.xhtml"), @Result(name = "success", type = FreemarkerResult.class, value = "setup_success.xhtml") })
@Scope("request")
public class ConfigAction {
    protected Logger log = LoggerFactory.getLogger(this.getClass());
    public static String CONFIG_LOCATION = "initData.xml";
    @Resource(name = "resService")
    private ResService resService;
    @Resource(name = "orgService")
    private OrgService orgService;
    @Resource(name = "roleService")
    private RoleService roleService;
    @Resource(name = "userService")
    private UserService userService;
    @Resource(name = "menuService")
    private MenuService menuService;

    /**
     * 跳转到初始化页面
     * 
     * @return
     * @throws Exception
     */
    public String toSetup() throws Exception {
        return "setup";
    }

    /**
     * 初始化数据
     * 
     * @return
     * @throws Exception
     */
    public String initData() throws Exception {
        // 判断是否有数据
        List<ResProfile> resList = this.resService.loadAllResource();
        if (resList != null && resList.size() > 0) {
            throw new LearyperiException("清空数据库后才可以初始化!");
        }
        List<RoleProfile> roleList = this.roleService.loadAllRole();
        if (roleList != null && roleList.size() > 0) {
            throw new LearyperiException("清空数据库后才可以初始化!");
        }
        OrgProfile orgRoot = this.orgService.findRoot();
        if (orgRoot != null) {
            throw new LearyperiException("清空数据库后才可以初始化!");
        }
        List<UserProfile> userList = this.userService.loadAllUser();
        if (userList != null && userList.size() > 0) {
            throw new LearyperiException("清空数据库后才可以初始化!");
        }
        Menu menuRoot = this.menuService.findRoot();
        if (menuRoot != null) {
            throw new LearyperiException("清空数据库后才可以初始化!");
        }
        ApplicationContext ctx = new ClassPathXmlApplicationContext(CONFIG_LOCATION);
        // 初始化资源信息
        Map<String, ResProfile> resMap = ctx.getBeansOfType(ResProfile.class);
        for (ResProfile res : resMap.values()) {
            this.resService.createResource(res);
        }
        log.info("resource init success.");
        // 初始化角色信息
        Map<String, RoleProfile> roleMap = ctx.getBeansOfType(RoleProfile.class);
        for (RoleProfile role : roleMap.values()) {
            this.roleService.createRole(role);
        }
        log.info("role init success.");
        // 初始化组织机构信息
        OrgProfile org = (OrgProfile) ctx.getBean("org_root");
        this.orgService.createOrg(org);
        log.info("organization init success.");
        // 初始化用户信息
        Map<String, UserProfile> userMap = ctx.getBeansOfType(UserProfile.class);
        for (UserProfile user : userMap.values()) {
            this.userService.createUser(user);
        }
        log.info("user init success.");
        // 初始化菜单信息
        Menu menu = (Menu) ctx.getBean("menu_root");
        this.menuService.createMenu(menu);
        log.info("learyperi init success.");
        return "success";
    }

    public ResService getResService() {
        return resService;
    }

    public void setResService(ResService resService) {
        this.resService = resService;
    }

    public OrgService getOrgService() {
        return orgService;
    }

    public void setOrgService(OrgService orgService) {
        this.orgService = orgService;
    }

    public RoleService getRoleService() {
        return roleService;
    }

    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public MenuService getMenuService() {
        return menuService;
    }

    public void setMenuService(MenuService menuService) {
        this.menuService = menuService;
    }

}
