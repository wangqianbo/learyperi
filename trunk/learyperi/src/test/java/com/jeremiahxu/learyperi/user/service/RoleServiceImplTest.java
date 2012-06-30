package com.jeremiahxu.learyperi.user.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.jeremiahxu.learyperi.user.ResBuilder;
import com.jeremiahxu.learyperi.user.RoleBuilder;
import com.jeremiahxu.learyperi.user.UserBuilder;
import com.jeremiahxu.learyperi.user.pojo.ResProfile;
import com.jeremiahxu.learyperi.user.pojo.RoleProfile;
import com.jeremiahxu.learyperi.user.pojo.UserProfile;

/**
 * 角色服务实现类测试
 * 
 * @author Jeremiah Xu
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationContext-test.xml" })
public class RoleServiceImplTest extends AbstractTransactionalJUnit4SpringContextTests {
    @Resource(name = "roleService")
    private RoleService roleService;
    @Resource(name = "userService")
    private UserService userService;
    @Resource(name = "resService")
    private ResService resService;

    public RoleService getRoleService() {
        return roleService;
    }

    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public ResService getResService() {
        return resService;
    }

    public void setResService(ResService resService) {
        this.resService = resService;
    }

    @Test
    @Transactional
    @Rollback(value = true)
    public void createRoleAndDeleteItThenNotFindIt() {
        RoleProfile role = RoleBuilder.aRole().withCode("code1").withName("name1").withDescription("desc1").build();
        roleService.createRole(role);
        int id = role.getId();
        RoleProfile roleDel = new RoleProfile();
        roleDel.setId(id);
        roleService.deleteRole(roleDel);
        RoleProfile roleComp = roleService.findRole(id);
        Assert.assertNull("角色未删除", roleComp);
    }

    @Test
    @Transactional
    @Rollback(value = true)
    public void updateRoleAndFindIt() {
        RoleProfile role = RoleBuilder.aRole().withCode("code1").withName("name1").withDescription("desc1").build();
        roleService.createRole(role);
        int id = role.getId();
        RoleProfile roleUpdate = RoleBuilder.aRole().withId(id).withCode("code11").withName("name11").withDescription("desc11").build();
        roleService.updateRole(roleUpdate);
        RoleProfile roleComp = roleService.findRole(id);
        Assert.assertTrue("角色更新失败", roleComp.getName().equals(roleUpdate.getName()));
        Assert.assertTrue("角色更新失败", roleComp.getCode().equals(roleUpdate.getCode()));
        Assert.assertTrue("角色更新失败", roleComp.getDescription().equals(roleUpdate.getDescription()));
    }

    @Test
    @Transactional
    @Rollback(value = true)
    public void deleteRoleCascadeUserAndResRelation() {
        UserProfile user = UserBuilder.aUser().withName("name1").withPassword("pass1").withFirstName("firstname1").withLastName("lastname1").build();
        Set<UserProfile> users = new HashSet<UserProfile>();
        users.add(user);
        ResProfile res = ResBuilder.aRes().withName("name1").withUrl("url1").build();
        Set<ResProfile> resources = new HashSet<ResProfile>();
        resources.add(res);
        RoleProfile role = RoleBuilder.aRole().withUsers(users).withResource(resources).withCode("code1").withName("name1").withDescription("desc1").build();
        userService.createUser(user);
        resService.createResource(res);
        roleService.createRole(role);
        int userId = user.getId();
        int resId = res.getId();
        int roleId = role.getId();
        RoleProfile roleDel = roleService.findRole(roleId);
        roleService.deleteRole(roleDel);
        RoleProfile roleComp = roleService.findRole(roleId);
        UserProfile userComp = userService.findUser(userId);
        ResProfile resComp = resService.findResource(resId);
        Assert.assertNull("角色未正确删除", roleComp);
        Assert.assertNotNull("用户不应该级联删除", userComp);
        Assert.assertNotNull("资源不应该级联删除", resComp);
    }

    @Test
    @Transactional
    @Rollback(value = true)
    public void createRolesAndQueryPaged() {
        RoleProfile role1 = RoleBuilder.aRole().withCode("code1").withName("name1").withDescription("desc1").build();
        RoleProfile role2 = RoleBuilder.aRole().withCode("code2").withName("name2").withDescription("desc2").build();
        RoleProfile role3 = RoleBuilder.aRole().withCode("code3").withName("name3").withDescription("desc3").build();
        roleService.createRole(role1);
        roleService.createRole(role2);
        roleService.createRole(role3);
        List<RoleProfile> rs = roleService.findPagedRoles(1, 2);
        Assert.assertEquals("角色信息分页记录数不正确", rs.size(), 2);
        Assert.assertEquals("角色信息分页记录不正确", rs.get(0).getCode(), "code1");
        Assert.assertEquals("角色信息分页记录不正确", rs.get(1).getCode(), "code2");
        rs = roleService.findPagedRoles(2, 2);
        Assert.assertEquals("角色信息分页记录数不正确", rs.size(), 1);
        Assert.assertEquals("角色信息分页记录不正确", rs.get(0).getCode(), "code3");
    }
}
