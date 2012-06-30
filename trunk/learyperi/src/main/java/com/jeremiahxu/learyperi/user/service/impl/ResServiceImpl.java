package com.jeremiahxu.learyperi.user.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeremiahxu.learyperi.dao.GenericDao;
import com.jeremiahxu.learyperi.user.pojo.ResProfile;
import com.jeremiahxu.learyperi.user.service.ResService;

/**
 * 资源服务实现类。
 * 
 * @author Jeremiah Xu
 * 
 */
@Service("resService")
public class ResServiceImpl implements ResService {
    @Resource(name = "dao")
    private GenericDao<ResProfile, Integer> resourceDao;

    @Override
    public List<ResProfile> loadAllResource() {
        return this.resourceDao.queryAll(ResProfile.class);
    }

    @Override
    @Transactional
    public void createResource(ResProfile res) {
        this.resourceDao.save(res);
    }

    @Override
    @Transactional
    public void deleteResource(ResProfile res) {
        ResProfile resDel = this.getResourceDao().findById(ResProfile.class, res.getId());
        resDel.setRoles(null);
        this.resourceDao.update(resDel);
        this.resourceDao.delete(resDel);
    }

    @Override
    @Transactional
    public void updateResource(ResProfile res) {
        this.resourceDao.update(res);
    }

    @Override
    public ResProfile findResource(int id) {
        return this.resourceDao.findById(ResProfile.class, id);
    }

    public GenericDao<ResProfile, Integer> getResourceDao() {
        return resourceDao;
    }

    public void setResourceDao(GenericDao<ResProfile, Integer> resourceDao) {
        this.resourceDao = resourceDao;
    }
}
