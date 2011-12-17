package com.jeremiahxu.learyperi.user.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jeremiahxu.learyperi.dao.GenericDao;
import com.jeremiahxu.learyperi.user.UserException;
import com.jeremiahxu.learyperi.user.pojo.UserProfile;
import com.jeremiahxu.learyperi.user.service.UserService;

/**
 * 用户管理服务实现类。
 * 
 * @author Jeremiah Xu
 * 
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Resource(name = "dao")
    private GenericDao<UserProfile, Integer> userDao;

    @Override
    public UserProfile loadByName(String name) {
        String jpql = "select u from UserProfile u where u.name='" + name + "'";
        List<UserProfile> rs = userDao.queryByJPQL(jpql);
        if (rs.size() < 1) {// 未取得用户信息
            throw new UserException("user[" + name + "] is not exists.");
        } else if (rs.size() == 1) {// 正确取得用户信息
            return rs.get(0);
        } else {// 取得的用户信息有多条
            throw new UserException("user[" + name + "] is not only one.");
        }
    }

    @Override
    public UserProfile loadByNameAndPassword(String name, String password) {
        String jpql = "select u from UserProfile u where u.name='" + name + "' and u.password='" + password + "'";
        List<UserProfile> rs = userDao.queryByJPQL(jpql);
        if (rs.size() < 1) {// 用户登录失败
            return null;
        } else if (rs.size() == 1) {// 正确取得用户信息，用户登录成功。
            return rs.get(0);
        } else {// 取得用户信息超过一条
            throw new UserException("user[" + name + "] is not only one.");
        }
    }

    @Override
    public List<UserProfile> loadAllUser() {
        return this.userDao.queryAll(UserProfile.class);
    }

    @Override
    public void createUser(UserProfile user) {
        this.userDao.save(user);
    }

    @Override
    public void deleteUser(UserProfile user) {
        this.userDao.delete(user);
    }

    @Override
    public void updateUser(UserProfile user) {
        this.userDao.update(user);
    }

    @Override
    public UserProfile findUser(int id) {
        return this.userDao.findById(UserProfile.class, id);
    }

    public GenericDao<UserProfile, Integer> getUserDao() {
        return userDao;
    }

    public void setUserDao(GenericDao<UserProfile, Integer> userDao) {
        this.userDao = userDao;
    }
}
