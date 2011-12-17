package com.jeremiahxu.learyperi.menu.tag;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.jeremiahxu.learyperi.menu.MenuException;
import com.jeremiahxu.learyperi.menu.MenuType;
import com.jeremiahxu.learyperi.menu.pojo.Menu;
import com.jeremiahxu.learyperi.menu.service.MenuService;

/**
 * 展示菜单树的标签
 * 
 * @author Jeremiah Xu
 * 
 */
public class MenuTreeTag extends SimpleTagSupport {
    private static final long serialVersionUID = 1L;
    protected Logger log = LoggerFactory.getLogger(this.getClass());
    private Menu menu;
    private String topMenuClass;// 顶层菜单class
    private String subMenuClass;// 各级子菜单class

    @Override
    public void doTag() throws JspException, IOException {
        if (this.menu == null) {// 如果标签的menu属性没有值则读取数据库取得菜单树
            ServletContext servletContext = ((PageContext) this.getJspContext()).getServletContext();
            WebApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
            MenuService menuService = (MenuService) ctx.getBean("menuService");
            this.setMenu(menuService.findRoot());
        }
        if (this.getMenu() == null) {
            throw new MenuException("no menu data.");
        }
        // 生成第一层菜单
        List<Menu> menuList = this.getMenu().getChildren();
        StringBuffer html = new StringBuffer();
        if (this.getTopMenuClass() == null) {
            html.append("<ul>");
        } else {
            html.append("<ul class=\"").append(this.getTopMenuClass()).append("\">");
        }
        for (Menu menuItem : menuList) {
            html.append("<li>");
            html.append("<a id=\"").append(menuItem.getId()).append("\"");
            switch (menuItem.getType()) {
            case MenuType.OPEN_INNER_PAGE:// 在主窗体内打开子页面
                html.append(" name=\".").append(menuItem.getUrl()).append("\" href=\"#\">");
                break;
            case MenuType.OPEN_POPUP_PAGE:// 弹出窗体
                html.append(" name=\".").append(menuItem.getUrl()).append("\" target=\"_blank\" href=\"#\">");
                break;
            case MenuType.WHOLE_PAGE_FORWARD:// 整个页面刷新
                html.append(" href=\".").append(menuItem.getUrl()).append("\">");
                break;
            case MenuType.NO_OPERATION:// 不做任何操作
                html.append(" href=\"#\">");
                break;
            default:
            }
            html.append(menuItem.getName()).append("</a>");
            // 递归生成菜单
            html.append(this.writeHTML(menuItem));
            html.append("</li>");
        }
        html.append("</ul>");
        try {
            getJspContext().getOut().print(html);
        } catch (Exception ex) {
            log.error("menutree's tag error.", ex);
        }
    }

    /**
     * 递归菜单生成HTML
     * 
     * @param menu
     * @return
     */
    private String writeHTML(Menu menu) {
        List<Menu> menuList = menu.getChildren();
        if (menuList.size() == 0) {
            return "";
        }
        StringBuffer html = new StringBuffer();
        if (this.getSubMenuClass() == null) {
            html.append("<ul>");
        } else {
            html.append("<ul class=\"").append(this.getSubMenuClass()).append("\">");
        }
        for (Menu menuItem : menuList) {
            html.append("<a id=\"").append(menuItem.getId()).append("\"");
            switch (menuItem.getType()) {
            case MenuType.OPEN_INNER_PAGE:// 在主窗体内打开子页面
                html.append(" name=\".").append(menuItem.getUrl()).append("\" href=\"#\">");
                break;
            case MenuType.OPEN_POPUP_PAGE:// 弹出窗体
                html.append(" name=\".").append(menuItem.getUrl()).append("\" target=\"_blank\" href=\"#\">");
                break;
            case MenuType.WHOLE_PAGE_FORWARD:// 整个页面刷新
                html.append(" href=\".").append(menuItem.getUrl()).append("\">");
                break;
            case MenuType.NO_OPERATION:// 不做任何操作
                html.append(" href=\"#\">");
                break;
            default:
            }
            html.append(menuItem.getName()).append("</a>");
            html.append(this.writeHTML(menuItem));
            html.append("</li>");
        }
        html.append("</ul>");
        return html.toString();
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public String getTopMenuClass() {
        return topMenuClass;
    }

    public void setTopMenuClass(String topMenuClass) {
        this.topMenuClass = topMenuClass;
    }

    public String getSubMenuClass() {
        return subMenuClass;
    }

    public void setSubMenuClass(String subMenuClass) {
        this.subMenuClass = subMenuClass;
    }

}
