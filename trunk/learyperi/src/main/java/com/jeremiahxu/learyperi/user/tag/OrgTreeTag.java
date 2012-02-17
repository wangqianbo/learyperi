package com.jeremiahxu.learyperi.user.tag;

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

import com.jeremiahxu.learyperi.user.pojo.OrgProfile;
import com.jeremiahxu.learyperi.user.service.OrgService;

/**
 * 展示组织机构树的标签
 * 
 * @author Jeremiah Xu
 * 
 */
public class OrgTreeTag extends SimpleTagSupport {
    protected Logger log = LoggerFactory.getLogger(this.getClass());
    private OrgProfile org;
    private String topOrgClass = "jstree-open";// 顶层组织机构class
    private String subOrgClass = "jstree-open";// 各级子组织机构class

    @Override
    public void doTag() throws JspException, IOException {
        if (org == null) {// 如果标签的org属性没有值则读取数据库取得组织结构信息
            ServletContext servletContext = ((PageContext) this.getJspContext()).getServletContext();
            WebApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
            OrgService orgService = (OrgService) ctx.getBean("orgService");
            this.setOrg(orgService.findRoot());
        }
        // 生成第一层组织结构
        List<OrgProfile> orgList = this.getOrg().getChildren();
        StringBuffer html = new StringBuffer();
        if (this.getTopOrgClass() == null) {
            html.append("<ul>");
        } else {
            html.append("<ul class=\"").append(this.getTopOrgClass()).append("\">");
        }
        for (OrgProfile orgItem : orgList) {
            html.append("<li>");
            html.append("<a id=\"").append(orgItem.getId()).append("\" href=\"#\">").append(orgItem.getName()).append("</a>");
            // 递归生成组织结构
            html.append(this.writeHTML(orgItem));
            html.append("</li>");
        }
        html.append("</ul>");
        try {
            getJspContext().getOut().print(html);
        } catch (Exception ex) {
            log.error("orgtree's tag error.", ex);
        }
    }

    /**
     * 递归组织结构生成HTML
     * 
     * @param org
     * @return
     */
    private String writeHTML(OrgProfile org) {
        List<OrgProfile> orgList = org.getChildren();
        if (orgList.size() == 0) {
            return "";
        }
        StringBuffer html = new StringBuffer();
        if (this.getSubOrgClass() == null) {
            html.append("<ul>");
        } else {
            html.append("<ul class=\"").append(this.getSubOrgClass()).append("\">");
        }
        for (OrgProfile orgItem : orgList) {
            html.append("<li>");
            html.append("<a id=\"").append(orgItem.getId()).append("\" href=\"#\">").append(orgItem.getName()).append("</a>");
            html.append(this.writeHTML(orgItem));
            html.append("</li>");
        }
        html.append("</ul>");
        return html.toString();
    }

    public OrgProfile getOrg() {
        return org;
    }

    public void setOrg(OrgProfile org) {
        this.org = org;
    }

    public String getTopOrgClass() {
        return topOrgClass;
    }

    public void setTopOrgClass(String topOrgClass) {
        this.topOrgClass = topOrgClass;
    }

    public String getSubOrgClass() {
        return subOrgClass;
    }

    public void setSubOrgClass(String subOrgClass) {
        this.subOrgClass = subOrgClass;
    }

}
