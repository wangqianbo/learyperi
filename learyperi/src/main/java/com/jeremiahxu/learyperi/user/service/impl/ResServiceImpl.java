package com.jeremiahxu.learyperi.user.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

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
    public void createResource(ResProfile res) {
        this.resourceDao.save(res);
    }

    @Override
    public void deleteResource(ResProfile res) {
        this.resourceDao.delete(res);
    }

    @Override
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
