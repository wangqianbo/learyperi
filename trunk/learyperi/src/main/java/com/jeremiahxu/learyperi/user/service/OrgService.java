package com.jeremiahxu.learyperi.user.service;

import com.jeremiahxu.learyperi.user.pojo.OrgProfile;

/**
 * 组织机构信息服务类
 * 
 * @author Jeremiah Xu
 * 
 */
public interface OrgService {
    /**
     * 创建一个组织机构
     * 
     * @param org
     */
    public void createOrg(OrgProfile org);

    /**
     * 删除一个组织机构
     * 
     * @param org
     */
    public void deleteOrg(OrgProfile org);

    /**
     * 更新一个组织机构
     * 
     * @param org
     */
    public void updateOrg(OrgProfile org);

    /**
     * 根据id取得组织机构信息
     * 
     * @param id
     * @return
     */
    public OrgProfile findOrg(int id);

    /**
     * 取得组织机构根节点
     * 
     * @return
     */
    public OrgProfile findRoot();
}
