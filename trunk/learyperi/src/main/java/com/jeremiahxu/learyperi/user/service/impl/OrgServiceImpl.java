package com.jeremiahxu.learyperi.user.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeremiahxu.learyperi.dao.GenericDao;
import com.jeremiahxu.learyperi.user.pojo.OrgProfile;
import com.jeremiahxu.learyperi.user.service.OrgService;

/**
 * 组织机构信息服务实现类
 * 
 * @author Jeremiah Xu
 * 
 */
@Service("orgService")
public class OrgServiceImpl implements OrgService {
    @Resource(name = "dao")
    private GenericDao<OrgProfile, Integer> orgDao;

    /**
     * 计算idpath、namepath和level属性。
     * 
     * @param org
     */
    private void computePathAndLevel(OrgProfile org) {
        String idPath = org.getId() + "/";
        String namePath = org.getName() + "/";
        int level = 0;
        OrgProfile parent = org.getParent();
        if (parent == null) {
            idPath = "/" + idPath;
            namePath = "/" + namePath;
            level = 0;
        } else {
            parent = this.findOrg(parent.getId());
            idPath = parent.getIdPath() + idPath;
            namePath = parent.getNamePath() + namePath;
            level = parent.getLevel() + 1;
        }
        org.setIdPath(idPath);
        org.setNamePath(namePath);
        org.setLevel(level);
        if (org.getChildren() != null) {
            for (OrgProfile child : org.getChildren()) {
                computePathAndLevel(child);
            }
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.jeremiahxu.learyperi.user.service.OrgService#findRoot()
     */
    @Override
    public OrgProfile findRoot() {
        List<OrgProfile> rs = this.getOrgDao().queryByJPQL("select o from OrgProfile o where o.code='root_org' and o.level=0");
        if (rs.size() == 1) {
            return rs.get(0);
        } else {
            return null;
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.jeremiahxu.learyperi.user.service.OrgService#createOrg(com.jeremiahxu
     * .learyperi.user.pojo.OrgProfile)
     */
    @Override
    @Transactional
    public void createOrg(OrgProfile org) {
        this.getOrgDao().save(org);
        this.computePathAndLevel(org);
        this.getOrgDao().update(org);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.jeremiahxu.learyperi.user.service.OrgService#deleteOrg(com.jeremiahxu
     * .learyperi.user.pojo.OrgProfile)
     */
    @Override
    @Transactional
    public void deleteOrg(OrgProfile org) {
        OrgProfile orgDel = this.getOrgDao().findById(OrgProfile.class, org.getId());
        this.getOrgDao().delete(orgDel);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.jeremiahxu.learyperi.user.service.OrgService#updateOrg(com.jeremiahxu
     * .learyperi.user.pojo.OrgProfile)
     */
    @Override
    @Transactional
    public void updateOrg(OrgProfile org) {
        this.computePathAndLevel(org);
        this.getOrgDao().update(org);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.jeremiahxu.learyperi.user.service.OrgService#findOrg(int)
     */
    @Override
    public OrgProfile findOrg(int id) {
        return this.getOrgDao().findById(OrgProfile.class, id);
    }

    public GenericDao<OrgProfile, Integer> getOrgDao() {
        return orgDao;
    }

    public void setOrgDao(GenericDao<OrgProfile, Integer> orgDao) {
        this.orgDao = orgDao;
    }

}
