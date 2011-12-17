package com.jeremiahxu.learyperi.security;

import java.util.Collection;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

/**
 * @author Jeremiah Xu
 * 
 */
public class AccessDecisionManagerImpl implements AccessDecisionManager {

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.springframework.security.access.AccessDecisionManager#decide(org.
     * springframework.security.core.Authentication, java.lang.Object,
     * java.util.Collection)
     */
    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
        if (configAttributes == null) {
            return;
        }
        for (ConfigAttribute ca : configAttributes) {
            String role = ((SecurityConfig) ca).getAttribute();
            for (GrantedAuthority ga : authentication.getAuthorities()) {
                if (role.equals(ga.getAuthority())) {
                    return;
                }
            }
        }
        throw new AccessDeniedException("no right");
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.springframework.security.access.AccessDecisionManager#supports(org
     * .springframework.security.access.ConfigAttribute)
     */
    @Override
    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.springframework.security.access.AccessDecisionManager#supports(java
     * .lang.Class)
     */
    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }

}
