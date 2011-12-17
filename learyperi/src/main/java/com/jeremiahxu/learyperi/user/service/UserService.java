package com.jeremiahxu.learyperi.user.service;

import java.util.List;

import com.jeremiahxu.learyperi.user.pojo.UserProfile;

/**
 * 用户信息管理服务接口
 * 
 * @author Jeremiah Xu
 * 
 */
public interface UserService {
    /**
     * 根据用户名取得用户信息
     * 
     * @param name
     * @return
     */
    public UserProfile loadByName(String name);

    /**
     * 根据用户名和密码取得用户信息
     * 
     * @param name
     * @param password
     * @return
     */
    public UserProfile loadByNameAndPassword(String name, String password);

    /**
     * 取得所有用户信息
     * 
     * @return
     */
    public List<UserProfile> loadAllUser();

    /**
     * 创建用户信息
     * 
     * @param user
     */
    public void createUser(UserProfile user);

    /**
     * 删除用户信息
     * 
     * @param user
     */
    public void deleteUser(UserProfile user);

    /**
     * 更新用户信息
     * 
     * @param user
     */
    public void updateUser(UserProfile user);

    /**
     * 根据用户id取得用户信息
     * 
     * @param id
     * @return
     */
    public UserProfile findUser(int id);

}
