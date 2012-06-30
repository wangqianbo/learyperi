package com.jeremiahxu.learyperi.user.action;

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

import com.jeremiahxu.learyperi.user.pojo.OrgProfile;
import com.jeremiahxu.learyperi.user.service.OrgService;

/**
 * organization action
 * 
 * @author Jeremiah Xu
 * 
 */
@Controller
@ParentPackage(value = "struts-learyperi")
@Action(namespace = "/page/org", name = "org")
@Results({ @Result(name = "toList", type = ServletRedirectResult.class, value = "/page/org/org!listOrg.action"),
        @Result(name = "list", type = FreemarkerResult.class, value = "/page/org/orgList.xhtml"), @Result(name = "show", type = FreemarkerResult.class, value = "/page/org/orgDetail.xhtml"),
        @Result(name = "toNew", type = FreemarkerResult.class, value = "/page/org/orgNew.xhtml"), @Result(name = "toEdit", type = FreemarkerResult.class, value = "/page/org/orgEdit.xhtml") })
@Scope("request")
public class OrgAction {
    protected Logger log = LoggerFactory.getLogger(this.getClass());
    @Resource(name = "orgService")
    private OrgService orgService;
    private OrgProfile org;

    /**
     * 列出所有组织机构信息
     * 
     * @return
     * @throws Exception
     */
    public String listOrg() throws Exception {
        log.debug("listOrg");
        return "list";
    }

    /**
     * 创建机构
     * 
     * @return
     * @throws Exception
     */
    public String createOrg() throws Exception {
        this.getOrgService().createOrg(this.getOrg());
        log.debug("create org.");
        return "toList";
    }

    /**
     * 修改机构
     * 
     * @return
     * @throws Exception
     */
    public String updateOrg() throws Exception {
        this.getOrgService().updateOrg(this.getOrg());
        log.debug("update org.");
        return "toList";
    }

    /**
     * 删除机构
     * 
     * @return
     * @throws Exception
     */
    public String deleteOrg() throws Exception {
        this.getOrgService().deleteOrg(this.getOrg());
        log.debug("delete org.");
        return "toList";
    }

    /**
     * 显示机构明细
     * 
     * @return
     * @throws Exception
     */
    public String showOrg() throws Exception {
        this.setOrg(this.getOrgService().findOrg(this.getOrg().getId()));
        log.debug("show org.");
        return "show";
    }

    /**
     * 跳转去新建页面
     * 
     * @return
     * @throws Exception
     */
    public String toNew() throws Exception {
        if ("root_org".equals(this.getOrg().getParent().getCode())) {// 如果父节点为根则为创建一级机构
            OrgProfile root = this.getOrgService().findRoot();
            this.getOrg().setParent(root);
        }
        log.debug("to new org");
        return "toNew";
    }

    /**
     * 跳转去编辑页面
     * 
     * @return
     * @throws Exception
     */
    public String toEdit() throws Exception {
        this.setOrg(this.getOrgService().findOrg(this.getOrg().getId()));
        log.debug("to edit org");
        return "toEdit";
    }

    public OrgProfile getOrg() {
        return org;
    }

    public void setOrg(OrgProfile org) {
        this.org = org;
    }

    public OrgService getOrgService() {
        return orgService;
    }

    public void setOrgService(OrgService orgService) {
        this.orgService = orgService;
    }

}
