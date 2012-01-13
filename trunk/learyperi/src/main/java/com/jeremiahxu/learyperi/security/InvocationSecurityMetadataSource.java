package com.jeremiahxu.learyperi.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.AntPathRequestMatcher;
import org.springframework.security.web.util.RequestMatcher;

import com.jeremiahxu.learyperi.user.pojo.ResProfile;
import com.jeremiahxu.learyperi.user.pojo.RoleProfile;
import com.jeremiahxu.learyperi.user.service.ResService;

/**
 * @author Jeremiah Xu
 * 
 */
public class InvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
    private static Map<String, Collection<ConfigAttribute>> resourceMap = null;

    @Resource(name = "resService")
    private ResService resService;

    /**
     * 加载资源和角色对应关系。
     */
    @PostConstruct
    public void loadResourceDefine() {
        if (resourceMap == null) {
            resourceMap = new HashMap<String, Collection<ConfigAttribute>>();
        }
        // 取得所有资源
        List<ResProfile> resources = this.resService.loadAllResource();
        // 遍历资源列表
        for (ResProfile resource : resources) {
            Collection<ConfigAttribute> atts = new ArrayList<ConfigAttribute>();
            // 遍历该资源下所有角色
            for (RoleProfile role : resource.getRoles()) {
                ConfigAttribute ca = new SecurityConfig(role.getCode());
                atts.add(ca);
            }
            resourceMap.put(resource.getUrl(), atts);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.springframework.security.access.SecurityMetadataSource#getAttributes
     * (java.lang.Object)
     */
    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        HttpServletRequest request = ((FilterInvocation) object).getHttpRequest();
        for (String resURL : resourceMap.keySet()) {
            RequestMatcher requestMatcher = new AntPathRequestMatcher(resURL);
            if (requestMatcher.matches(request)) {
                return resourceMap.get(resURL);
            }
        }
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.security.access.SecurityMetadataSource#
     * getAllConfigAttributes()
     */
    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.springframework.security.access.SecurityMetadataSource#supports(java
     * .lang.Class)
     */
    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }

    public ResService getResourceService() {
        return resService;
    }

    public void setResourceService(ResService resourceService) {
        this.resService = resourceService;
    }

}