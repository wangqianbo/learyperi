package com.jeremiahxu.learyperi.user.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jeremiahxu.learyperi.dao.GenericDao;
import com.jeremiahxu.learyperi.user.pojo.RoleProfile;
import com.jeremiahxu.learyperi.user.service.RoleService;

/**
 * 角色信息服务实现类
 * 
 * @author Jeremiah Xu
 * 
 */
@Service("roleService")
public class RoleServiceImpl implements RoleService {
    @Resource(name = "dao")
    private GenericDao<RoleProfile, Integer> roleDao;

    /*
     * (non-Javadoc)
     * 
     * @see com.jeremiahxu.learyperi.user.service.RoleService#loadAllRole()
     */
    @Override
    public List<RoleProfile> loadAllRole() {
        return this.roleDao.queryAll(RoleProfile.class);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.jeremiahxu.learyperi.user.service.RoleService#createRole(com.jeremiahxu
     * .learyperi.user.pojo.RoleProfile)
     */
    @Override
    public void createRole(RoleProfile role) {
        this.roleDao.save(role);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.jeremiahxu.learyperi.user.service.RoleService#deleteRole(com.jeremiahxu
     * .learyperi.user.pojo.RoleProfile)
     */
    @Override
    public void deleteRole(RoleProfile role) {
        this.roleDao.delete(role);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.jeremiahxu.learyperi.user.service.RoleService#updateRole(com.jeremiahxu
     * .learyperi.user.pojo.RoleProfile)
     */
    @Override
    public void updateRole(RoleProfile role) {
        this.roleDao.update(role);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.jeremiahxu.learyperi.user.service.RoleService#findRole(int)
     */
    @Override
    public RoleProfile findRole(int id) {
        return this.roleDao.findById(RoleProfile.class, id);
    }

    public GenericDao<RoleProfile, Integer> getRoleDao() {
        return roleDao;
    }

    public void setRoleDao(GenericDao<RoleProfile, Integer> roleDao) {
        this.roleDao = roleDao;
    }

}
