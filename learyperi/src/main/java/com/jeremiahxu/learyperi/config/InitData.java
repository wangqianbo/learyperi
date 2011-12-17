package com.jeremiahxu.learyperi.config;

import java.util.List;

import com.jeremiahxu.learyperi.menu.pojo.Menu;
import com.jeremiahxu.learyperi.user.pojo.OrgProfile;
import com.jeremiahxu.learyperi.user.pojo.ResProfile;
import com.jeremiahxu.learyperi.user.pojo.RoleProfile;
import com.jeremiahxu.learyperi.user.pojo.UserProfile;

/**
 * 初始化数据的类
 * 
 * @author Jeremiah Xu
 * 
 */
public class InitData {
    private List<ResProfile> resList;
    private List<RoleProfile> roleList;
    private OrgProfile org;
    private List<UserProfile> userList;
    private List<Menu> menu;

    public List<ResProfile> getResList() {
        return resList;
    }

    public void setResList(List<ResProfile> resList) {
        this.resList = resList;
    }

    public List<RoleProfile> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<RoleProfile> roleList) {
        this.roleList = roleList;
    }

    public OrgProfile getOrg() {
        return org;
    }

    public void setOrg(OrgProfile org) {
        this.org = org;
    }

    public List<UserProfile> getUserList() {
        return userList;
    }

    public void setUserList(List<UserProfile> userList) {
        this.userList = userList;
    }

    public List<Menu> getMenu() {
        return menu;
    }

    public void setMenu(List<Menu> menu) {
        this.menu = menu;
    }

}
