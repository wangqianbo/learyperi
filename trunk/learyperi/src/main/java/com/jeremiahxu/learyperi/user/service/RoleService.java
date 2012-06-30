package com.jeremiahxu.learyperi.user.service;

import java.util.List;

import com.jeremiahxu.learyperi.user.pojo.RoleProfile;

/**
 * 角色信息服务接口
 * 
 * @author Jeremiah Xu
 * 
 */
public interface RoleService {
    /**
     * 取得所有角色信息
     * 
     * @return
     */
    public List<RoleProfile> loadAllRole();

    /**
     * 创建角色信息
     * 
     * @param role
     */
    public void createRole(RoleProfile role);

    /**
     * 删除角色信息
     * 
     * @param role
     */
    public void deleteRole(RoleProfile role);

    /**
     * 更新角色信息
     * 
     * @param role
     */
    public void updateRole(RoleProfile role);

    /**
     * 根据角色id取得角色信息
     * 
     * @param id
     * @return
     */
    public RoleProfile findRole(int id);

    /**
     * 根据查询条件查询角色信息
     * 
     * @param pageNumber
     *            页码
     * @param numberPerPage
     *            每页记录数
     * @return
     */
    public List<RoleProfile> findPagedRoles(int pageNumber, int numberPerPage);
}
