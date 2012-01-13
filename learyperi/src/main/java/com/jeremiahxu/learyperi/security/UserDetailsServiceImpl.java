package com.jeremiahxu.learyperi.security;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.jeremiahxu.learyperi.user.pojo.RoleProfile;
import com.jeremiahxu.learyperi.user.pojo.UserProfile;
import com.jeremiahxu.learyperi.user.service.UserService;

/**
 * @author Jeremiah Xu
 * 
 */
public class UserDetailsServiceImpl implements UserDetailsService {

    @Resource(name = "userService")
    private UserService userService;

    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.security.core.userdetails.UserDetailsService#
     * loadUserByUsername(java.lang.String)
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {
        // 根据用户登录名读取数据库中用户信息。
        UserProfile userProfile = this.userService.loadByName(username);
        List<GrantedAuthority> auths = new ArrayList<GrantedAuthority>();
        // 取得该用户所有角色
        for (RoleProfile role : userProfile.getRoles()) {
            GrantedAuthority auth = new SimpleGrantedAuthority(role.getCode());
            auths.add(auth);
        }
        User user = new User(username, userProfile.getPassword(), true, true, true, true, auths);
        return user;
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

}
