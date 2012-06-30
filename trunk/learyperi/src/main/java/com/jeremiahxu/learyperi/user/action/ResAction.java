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

import com.jeremiahxu.learyperi.user.pojo.ResProfile;
import com.jeremiahxu.learyperi.user.service.ResService;

/**
 * organization action
 * 
 * @author Jeremiah Xu
 * 
 */
@Controller
@ParentPackage(value = "struts-learyperi")
@Action(namespace = "/page/res", name = "res")
@Results({ @Result(name = "toList", type = ServletRedirectResult.class, value = "/page/res/res!listRes.action"),
        @Result(name = "list", type = FreemarkerResult.class, value = "/page/res/resList.xhtml"), @Result(name = "show", type = FreemarkerResult.class, value = "/page/res/resDetail.xhtml"),
        @Result(name = "toNew", type = FreemarkerResult.class, value = "/page/res/resNew.xhtml"), @Result(name = "toEdit", type = FreemarkerResult.class, value = "/page/res/resEdit.xhtml") })
@Scope("request")
public class ResAction {
    protected Logger log = LoggerFactory.getLogger(this.getClass());
    @Resource(name = "resService")
    private ResService resService;
    private ResProfile res;

    /**
     * 列出所有资源列表
     * 
     * @return
     * @throws Exception
     */
    public String listRes() throws Exception {
        log.debug("listRes");
        return "list";
    }

    /**
     * 创建资源
     * 
     * @return
     * @throws Exception
     */
    public String createRes() throws Exception {
        this.getResService().createResource(this.getRes());
        log.debug("create res");
        return "toList";
    }

    /**
     * 修改资源
     * 
     * @return
     * @throws Exception
     */
    public String updateRes() throws Exception {
        this.getResService().updateResource(this.getRes());
        log.debug("update res");
        return "toList";
    }

    /**
     * 删除资源
     * 
     * @return
     * @throws Exception
     */
    public String deleteRes() throws Exception {
        this.getResService().deleteResource(this.getRes());
        log.debug("delete res");
        return "toList";
    }

    /**
     * 显示资源明细
     * 
     * @return
     * @throws Exception
     */
    public String showRes() throws Exception {
        this.setRes(this.getResService().findResource(this.getRes().getId()));
        log.debug("show res");
        return "show";
    }

    /**
     * 跳转去新增资源页面
     * 
     * @return
     * @throws Exception
     */
    public String toNew() throws Exception {
        log.debug("to new res");
        return "toNew";
    }

    /**
     * 跳转去编辑页面
     * 
     * @return
     * @throws Exception
     */
    public String toEdit() throws Exception {
        this.setRes(this.getResService().findResource(this.getRes().getId()));
        log.debug("to edit res");
        return "toEdit";
    }

    public ResService getResService() {
        return resService;
    }

    public void setResService(ResService resService) {
        this.resService = resService;
    }

    public ResProfile getRes() {
        return res;
    }

    public void setRes(ResProfile res) {
        this.res = res;
    }

}
