package com.jeremiahxu.learyperi.user.pojo;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * 用户实体对象
 * 
 * @author Jeremiah Xu
 * 
 */
@Entity
@Table(name = "T_USER_INFO")
public class UserProfile implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "USI_ID", length = 10)
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Integer id;// 唯一标识
    @Column(name = "USI_NAME", length = 50, nullable = false)
    private String name;// 唯一用户名
    @Column(name = "USI_PASS", length = 50, nullable = false)
    private String password;// 密码
    @Column(name = "USI_FIRST_NAME", length = 50, nullable = false)
    private String firstName;// 用户的名字
    @Column(name = "USI_LAST_NAME", length = 50, nullable = false)
    private String lastName;// 用户的姓
    @ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.REFRESH }, targetEntity = RoleProfile.class)
    @JoinTable(name = "T_USER_ROLE_REL", joinColumns = { @JoinColumn(name = "USI_ID") }, inverseJoinColumns = { @JoinColumn(name = "ROI_ID") })
    private Set<RoleProfile> roles;// 用户所属的角色列表
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "OGI_ID")
    private OrgProfile org;// 用户所属组织机构

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Set<RoleProfile> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleProfile> roles) {
        this.roles = roles;
    }

    public OrgProfile getOrg() {
        return org;
    }

    public void setOrg(OrgProfile org) {
        this.org = org;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
