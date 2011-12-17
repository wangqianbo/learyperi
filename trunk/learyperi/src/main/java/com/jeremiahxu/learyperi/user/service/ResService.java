package com.jeremiahxu.learyperi.user.service;

import java.util.List;

import com.jeremiahxu.learyperi.user.pojo.ResProfile;

/**
 * 资源服务接口。
 * 
 * @author Jeremiah Xu
 * 
 */
public interface ResService {
    /**
     * 取得所有资源
     * 
     * @return
     */
    public List<ResProfile> loadAllResource();

    /**
     * 创建资源信息
     * 
     * @param res
     */
    public void createResource(ResProfile res);

    /**
     * 删除资源信息
     * 
     * @param res
     */
    public void deleteResource(ResProfile res);

    /**
     * 更新资源信息
     * 
     * @param res
     */
    public void updateResource(ResProfile res);

    /**
     * 根据资源id取得资源信息
     * 
     * @param id
     * @return
     */
    public ResProfile findResource(int id);
}
