package com.jeremiahxu.learyperi.action;

import org.apache.struts2.config.Action;
import org.apache.struts2.config.ParentPackage;
import org.apache.struts2.config.Result;
import org.apache.struts2.config.Results;
import org.apache.struts2.views.freemarker.FreemarkerResult;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 * 首页跳转
 * 
 * @author Jeremiah Xu
 * 
 */
@Controller
@ParentPackage(value = "struts-learyperi")
@Action(namespace = "/", name = "index")
@Results({ @Result(name = "success", type = FreemarkerResult.class, value = "/main.xhtml") })
@Scope("request")
public class IndexAction {
    /**
     * 跳转去首页
     * 
     * @return
     * @throws Exception
     */
    public String home() throws Exception {
        return "success";
    }
}
