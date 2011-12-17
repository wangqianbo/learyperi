package com.jeremiahxu.learyperi.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.jeremiahxu.learyperi.user.pojo.UserProfile;
import com.jeremiahxu.learyperi.user.service.UserService;

/**
 * 登录过滤器
 * 
 * @author Jeremiah Xu
 * 
 */
public class LoginFilter extends UsernamePasswordAuthenticationFilter {
    public static final String USERNAME = "user.name";
    public static final String PASSWORD = "user.password";
    private UserService userService;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if (!request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        }
        String username = obtainUsername(request).trim();
        String password = obtainPassword(request);
        UserProfile user = this.userService.loadByNameAndPassword(username, password);
        if (user == null) {
            throw new AuthenticationServiceException("用户名或者密码错误！");
        }
        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);
        setDetails(request, authRequest);
        return this.getAuthenticationManager().authenticate(authRequest);
    }

    @Override
    protected String obtainUsername(HttpServletRequest request) {
        String username = request.getParameter(USERNAME);
        return null == username ? "" : username;
    }

    @Override
    protected String obtainPassword(HttpServletRequest request) {
        String password = request.getParameter(PASSWORD);
        return null == password ? "" : password;
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

}
