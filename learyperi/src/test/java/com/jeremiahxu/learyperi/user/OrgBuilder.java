package com.jeremiahxu.learyperi.user;

import java.util.List;

import com.jeremiahxu.learyperi.user.pojo.OrgProfile;

public class OrgBuilder {
    private OrgProfile org;

    private OrgBuilder() {
        org = new OrgProfile();
    }

    public static OrgBuilder aOrg() {
        return new OrgBuilder();
    }

    public OrgProfile build() {
        return org;
    }

    public OrgBuilder withCode(String code) {
        this.org.setCode(code);
        return this;
    }

    public OrgBuilder withName(String name) {
        this.org.setName(name);
        return this;
    }

    public OrgBuilder withDescription(String description) {
        this.org.setDescription(description);
        return this;
    }

    public OrgBuilder withIdPath(String idPath) {
        this.org.setIdPath(idPath);
        return this;
    }

    public OrgBuilder withLevel(int level) {
        this.org.setLevel(level);
        return this;
    }

    public OrgBuilder withNamePath(String namePath) {
        this.org.setNamePath(namePath);
        return this;
    }

    public OrgBuilder withOrder(int order) {
        this.org.setOrder(order);
        return this;
    }

    public OrgBuilder withParent(OrgProfile parent) {
        this.org.setParent(parent);
        return this;
    }

    public OrgBuilder withChildren(List<OrgProfile> children) {
        this.org.setChildren(children);
        return this;
    }

    public OrgBuilder withId(int id) {
        this.org.setId(id);
        return this;
    }

}
