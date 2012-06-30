package com.jeremiahxu.learyperi.user;

import java.util.Set;

import com.jeremiahxu.learyperi.user.pojo.OrgProfile;
import com.jeremiahxu.learyperi.user.pojo.RoleProfile;
import com.jeremiahxu.learyperi.user.pojo.UserProfile;

public class UserBuilder {
    private UserProfile user;

    private UserBuilder() {
        user = new UserProfile();
    }

    public static UserBuilder aUser() {
        return new UserBuilder();
    }

    public UserProfile build() {
        return user;
    }

    public UserBuilder withName(String name) {
        this.user.setName(name);
        return this;
    }

    public UserBuilder withId(int id) {
        this.user.setId(id);
        return this;
    }

    public UserBuilder withPassword(String password) {
        this.user.setPassword(password);
        return this;
    }

    public UserBuilder withFirstName(String firstName) {
        this.user.setFirstName(firstName);
        return this;
    }

    public UserBuilder withLastName(String lastName) {
        this.user.setLastName(lastName);
        return this;
    }

    public UserBuilder withRoles(Set<RoleProfile> roles) {
        this.user.setRoles(roles);
        return this;
    }

    public UserBuilder withOrg(OrgProfile org) {
        this.user.setOrg(org);
        return this;
    }
}
