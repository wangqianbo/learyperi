package com.jeremiahxu.learyperi.user.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional
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
    @Transactional
    public void deleteRole(RoleProfile role) {
        RoleProfile roleDel = this.getRoleDao().findById(RoleProfile.class, role.getId());
        roleDel.setUsers(null);
        roleDel.setResources(null);
        this.roleDao.update(roleDel);
        this.roleDao.delete(roleDel);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.jeremiahxu.learyperi.user.service.RoleService#updateRole(com.jeremiahxu
     * .learyperi.user.pojo.RoleProfile)
     */
    @Override
    @Transactional
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

    @Override
    public List<RoleProfile> findPagedRoles(int pageNumber, int numberPerPage) {
        String jpql = "select r from RoleProfile r order by r.code";
        return roleDao.query(jpql, pageNumber, numberPerPage);
    }

    public GenericDao<RoleProfile, Integer> getRoleDao() {
        return roleDao;
    }

    public void setRoleDao(GenericDao<RoleProfile, Integer> roleDao) {
        this.roleDao = roleDao;
    }

}
