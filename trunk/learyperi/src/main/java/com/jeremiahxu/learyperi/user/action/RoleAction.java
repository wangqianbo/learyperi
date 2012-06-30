package com.jeremiahxu.learyperi.user.action;

import java.util.List;

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

import com.jeremiahxu.learyperi.PagedInfo;
import com.jeremiahxu.learyperi.user.pojo.RoleProfile;
import com.jeremiahxu.learyperi.user.service.RoleService;

/**
 * organization action
 * 
 * @author Jeremiah Xu
 * 
 */
@Controller
@ParentPackage(value = "struts-learyperi")
@Action(namespace = "/page/role", name = "role")
@Results({ @Result(name = "toList", type = ServletRedirectResult.class, value = "/page/role/role!listRole.action"),
        @Result(name = "list", type = FreemarkerResult.class, value = "/page/role/roleList.xhtml"), @Result(name = "show", type = FreemarkerResult.class, value = "/page/role/roleDetail.xhtml"),
        @Result(name = "toNew", type = FreemarkerResult.class, value = "/page/role/roleNew.xhtml"), @Result(name = "toEdit", type = FreemarkerResult.class, value = "/page/role/roleEdit.xhtml") })
@Scope("request")
public class RoleAction {
    protected Logger log = LoggerFactory.getLogger(this.getClass());
    @Resource(name = "roleService")
    private RoleService roleService;
    private RoleProfile role;
    private List<RoleProfile> roleList;
    private PagedInfo pagedInfo = new PagedInfo();

    /**
     * 查询角色信息
     * 
     * @return
     * @throws Exception
     */
    public String listRole() throws Exception {
        this.setRoleList(roleService.findPagedRoles(pagedInfo.getPageNumber(), pagedInfo.getNumberPerPage()));
        log.debug("list role");
        return "list";
    }

    /**
     * 创建角色
     * 
     * @return
     * @throws Exception
     */
    public String createRole() throws Exception {
        this.getRoleService().createRole(this.getRole());
        log.debug("create role");
        return "toList";
    }

    /**
     * 修改角色
     * 
     * @return
     * @throws Exception
     */
    public String updateRole() throws Exception {
        this.getRoleService().updateRole(this.getRole());
        log.debug("update role");
        return "toList";
    }

    /**
     * 删除角色
     * 
     * @return
     * @throws Exception
     */
    public String deleteRole() throws Exception {
        this.getRoleService().deleteRole(this.getRole());
        log.debug("delete role");
        return "toList";
    }

    /**
     * 显示角色明细
     * 
     * @return
     * @throws Exception
     */
    public String showRole() throws Exception {
        this.setRole(this.getRoleService().findRole(this.getRole().getId()));
        log.debug("show role");
        return "show";
    }

    /**
     * 跳转去新建角色页面。
     * 
     * @return
     * @throws Exception
     */
    public String toNew() throws Exception {
        log.debug("to new role");
        return "toNew";
    }

    /**
     * 跳转去编辑页面
     * 
     * @return
     * @throws Exception
     */
    public String toEdit() throws Exception {
        this.setRole(this.getRoleService().findRole(this.getRole().getId()));
        log.debug("to edit role");
        return "toEdit";
    }

    public RoleService getRoleService() {
        return roleService;
    }

    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    public RoleProfile getRole() {
        return role;
    }

    public void setRole(RoleProfile role) {
        this.role = role;
    }

    public List<RoleProfile> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<RoleProfile> roleList) {
        this.roleList = roleList;
    }

    public PagedInfo getPagedInfo() {
        return pagedInfo;
    }

    public void setPagedInfo(PagedInfo pagedInfo) {
        this.pagedInfo = pagedInfo;
    }

}
