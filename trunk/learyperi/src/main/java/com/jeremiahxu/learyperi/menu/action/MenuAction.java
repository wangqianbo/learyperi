package com.jeremiahxu.learyperi.menu.action;

import javax.annotation.Resource;

import org.apache.struts2.config.Action;
import org.apache.struts2.config.ParentPackage;
import org.apache.struts2.config.Result;
import org.apache.struts2.config.Results;
import org.apache.struts2.dispatcher.ServletRedirectResult;
import org.apache.struts2.views.freemarker.FreemarkerResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.jeremiahxu.learyperi.menu.pojo.Menu;
import com.jeremiahxu.learyperi.menu.service.MenuService;

/**
 * menu action
 * 
 * @author Jeremiah Xu
 * 
 */
@Controller("menuAction")
@ParentPackage(value = "struts-learyperi")
@Action(namespace = "/page/menu", name = "menu")
@Results({ @Result(name = "toList", type = ServletRedirectResult.class, value = "/page/menu/menu!listMenu.action"),
        @Result(name = "list", type = FreemarkerResult.class, value = "/page/menu/menuList.xhtml"), @Result(name = "show", type = FreemarkerResult.class, value = "/page/menu/menuDetail.xhtml"),
        @Result(name = "toNew", type = FreemarkerResult.class, value = "/page/menu/menuNew.xhtml"), @Result(name = "toEdit", type = FreemarkerResult.class, value = "/page/menu/menuEdit.xhtml") })
@Scope("request")
public class MenuAction {
    protected Logger log = LoggerFactory.getLogger(this.getClass());
    @Resource(name = "menuService")
    private MenuService menuService;
    private String message;
    private Menu menu;

    /**
     * 列出所有菜单
     * 
     * @return
     * @throws Exception
     */
    public String listMenu() throws Exception {
        log.debug("list menu.");
        return "list";
    }

    /**
     * 创建菜单
     * 
     * @return
     * @throws Exception
     */
    public String createMenu() throws Exception {
        this.getMenuService().createMenu(this.getMenu());
        log.debug("create menu.");
        return "toList";
    }

    /**
     * 修改菜单
     * 
     * @return
     * @throws Exception
     */
    public String updateMenu() throws Exception {
        this.getMenuService().updateMenu(this.getMenu());
        log.debug("update menu.");
        return "toList";
    }

    /**
     * 删除菜单
     * 
     * @return
     * @throws Exception
     */
    public String deleteMenu() throws Exception {
        this.getMenuService().deleteMenu(this.getMenu());
        log.debug("delete menu.");
        return "toList";
    }

    /**
     * 显示菜单明细
     * 
     * @return
     * @throws Exception
     */
    public String showMenu() throws Exception {
        this.setMenu(this.getMenuService().findMenu(this.getMenu().getId()));
        log.debug("show menu.");
        return "show";
    }

    /**
     * 跳转去新建页面
     * 
     * @return
     */
    public String toNew() {
        if ("root_menu".equals(this.getMenu().getParent().getCode())) {// 如果父菜单为根则属于新建一级菜单
            Menu root = this.getMenuService().findRoot();
            this.getMenu().setParent(root);
        }
        log.debug("to new menu.");
        return "toNew";
    }

    /**
     * 去菜单编辑页面
     * 
     * @return
     * @throws Exception
     */
    public String toEdit() throws Exception {
        this.setMenu(this.getMenuService().findMenu(this.getMenu().getId()));
        log.debug("to edit menu.");
        return "toEdit";
    }

    public MenuService getMenuService() {
        return menuService;
    }

    public void setMenuService(MenuService menuService) {
        this.menuService = menuService;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

}
