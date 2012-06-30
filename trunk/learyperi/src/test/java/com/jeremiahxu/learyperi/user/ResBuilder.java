package com.jeremiahxu.learyperi.user;

import java.util.Set;

import com.jeremiahxu.learyperi.user.pojo.ResProfile;
import com.jeremiahxu.learyperi.user.pojo.RoleProfile;

public class ResBuilder {
    private ResProfile res;

    private ResBuilder() {
        res = new ResProfile();
    }

    public static ResBuilder aRes() {
        return new ResBuilder();
    }

    public ResProfile build() {
        return res;
    }

    public ResBuilder withName(String name) {
        this.res.setName(name);
        return this;
    }

    public ResBuilder withUrl(String url) {
        this.res.setUrl(url);
        return this;
    }

    public ResBuilder withId(int id) {
        this.res.setId(id);
        return this;
    }

    public ResBuilder withRoles(Set<RoleProfile> roles) {
        this.res.setRoles(roles);
        return this;
    }

}
