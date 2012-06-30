package com.jeremiahxu.learyperi.user.service;

import java.util.HashSet;
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

import com.jeremiahxu.learyperi.user.OrgBuilder;
import com.jeremiahxu.learyperi.user.RoleBuilder;
import com.jeremiahxu.learyperi.user.UserBuilder;
import com.jeremiahxu.learyperi.user.UserException;
import com.jeremiahxu.learyperi.user.pojo.OrgProfile;
import com.jeremiahxu.learyperi.user.pojo.RoleProfile;
import com.jeremiahxu.learyperi.user.pojo.UserProfile;

/**
 * 用户服务实现类测试
 * 
 * @author Jeremiah Xu
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationContext-test.xml" })
public class UserServiceImplTest extends AbstractTransactionalJUnit4SpringContextTests {
    @Resource(name = "userService")
    private UserService userService;
    @Resource(name = "orgService")
    private OrgService orgService;
    @Resource(name = "roleService")
    private RoleService roleService;

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public OrgService getOrgService() {
        return orgService;
    }

    public void setOrgService(OrgService orgService) {
        this.orgService = orgService;
    }

    public RoleService getRoleService() {
        return roleService;
    }

    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    @Test
    @Transactional
    @Rollback(value = true)
    public void createUserAndDeleteItThenNotFindIt() {
        UserProfile user = UserBuilder.aUser().withName("name1").withPassword("pass1").withFirstName("firstname1").withLastName("lastname1").build();
        userService.createUser(user);
        int id = user.getId();
        UserProfile userDel = new UserProfile();
        userDel.setId(id);
        userService.deleteUser(userDel);
        UserProfile userComp = userService.findUser(id);
        Assert.assertNull("用户未删除", userComp);
    }

    @Test
    @Transactional
    @Rollback(value = true)
    public void updateUserAndFindIt() {
        UserProfile user = UserBuilder.aUser().withName("name1").withPassword("pass1").withFirstName("firstname1").withLastName("lastname1").build();
        userService.createUser(user);
        int id = user.getId();
        UserProfile userUpdate = UserBuilder.aUser().withId(id).withName("name11").withPassword("pass11").withFirstName("firstname11").withLastName("lastname11").build();
        userService.updateUser(userUpdate);
        UserProfile userComp = userService.findUser(id);
        Assert.assertTrue("用户更新失败", userComp.getName().equals(userUpdate.getName()));
        Assert.assertTrue("用户更新失败", userComp.getPassword().equals(userUpdate.getPassword()));
        Assert.assertTrue("用户更新失败", userComp.getFirstName().equals(userUpdate.getFirstName()));
        Assert.assertTrue("用户更新失败", userComp.getLastName().equals(userUpdate.getLastName()));
    }

    @Test
    @Transactional
    @Rollback(value = true)
    public void createUserAndLoadByName() {
        UserProfile user = UserBuilder.aUser().withName("name1").withPassword("pass1").withFirstName("firstname1").withLastName("lastname1").build();
        userService.createUser(user);
        String name = user.getName();
        UserProfile userComp = userService.loadByName(name);
        Assert.assertTrue("取得用户不正确", userComp.getName().equals(user.getName()));
        Assert.assertTrue("取得用户不正确", userComp.getPassword().equals(user.getPassword()));
        Assert.assertTrue("取得用户不正确", userComp.getFirstName().equals(user.getFirstName()));
        Assert.assertTrue("取得用户不正确", userComp.getLastName().equals(user.getLastName()));
        try {
            userService.loadByName("test");
            Assert.fail("不存在的用户未抛出异常");
        } catch (UserException ex) {
        }
    }

    @Test
    @Transactional
    @Rollback(value = true)
    public void createUserThenLoadByNameAndPass() {
        UserProfile user = UserBuilder.aUser().withName("name1").withPassword("pass1").withFirstName("firstname1").withLastName("lastname1").build();
        userService.createUser(user);
        String name = user.getName();
        String password = user.getPassword();
        UserProfile userNone = userService.loadByNameAndPassword("name1", "nopass");
        UserProfile userComp = userService.loadByNameAndPassword(name, password);
        Assert.assertNull("输入错误的密码取得的用户信息应该为null", userNone);
        Assert.assertNotNull("未正确取得用户信息", userComp);
    }

    @Test
    @Transactional
    @Rollback(value = true)
    public void deleteUserCascadeOrgAndRoleRelation() {
        OrgProfile org = OrgBuilder.aOrg().withCode("code1").withDescription("desc1").withLevel(1).withName("name1").withOrder(11).build();
        RoleProfile role = RoleBuilder.aRole().withCode("code1").withDescription("desc1").withName("name1").build();
        Set<RoleProfile> roles = new HashSet<RoleProfile>();
        roles.add(role);
        UserProfile user = UserBuilder.aUser().withOrg(org).withRoles(roles).withName("name1").withPassword("pass1").withFirstName("firstname1").withLastName("lastname1").build();
        orgService.createOrg(org);
        roleService.createRole(role);
        userService.createUser(user);
        int userId = user.getId();
        int roleId = role.getId();
        int orgId = org.getId();
        UserProfile userDel = userService.findUser(userId);
        userService.deleteUser(userDel);
        UserProfile userComp = userService.findUser(userId);
        OrgProfile orgComp = orgService.findOrg(orgId);
        RoleProfile roleComp = roleService.findRole(roleId);
        Assert.assertNull("用户未正确删除", userComp);
        Assert.assertNotNull("组织机构不应该级联删除", orgComp);
        Assert.assertNotNull("角色不应该级联删除", roleComp);
    }
}
