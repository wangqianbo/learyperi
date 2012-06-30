package com.jeremiahxu.learyperi.user;

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

import com.jeremiahxu.learyperi.dao.GenericDao;
import com.jeremiahxu.learyperi.user.pojo.OrgProfile;
import com.jeremiahxu.learyperi.user.pojo.RoleProfile;
import com.jeremiahxu.learyperi.user.pojo.UserProfile;

/**
 * OrgProfile对象持久化测试
 * 
 * @author Jeremiah Xu
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationContext-test.xml" })
public class UserTest extends AbstractTransactionalJUnit4SpringContextTests {
    @Resource(name = "dao")
    private GenericDao<UserProfile, Integer> userDao;
    @Resource(name = "dao")
    private GenericDao<OrgProfile, Integer> orgDao;
    @Resource(name = "dao")
    private GenericDao<RoleProfile, Integer> roleDao;

    public void setDao(GenericDao<UserProfile, Integer> dao) {
        this.userDao = dao;
    }

    @Test
    @Transactional
    @Rollback
    public void userSaveThenIdIsGreaterThanZero() {
        UserProfile user = UserBuilder.aUser().withName("name1").withPassword("pass1").withFirstName("firstname1").withLastName("lastname1").build();
        userDao.save(user);
        Assert.assertNotNull("用户保存失败", user.getId());
    }

    @Test
    @Transactional
    @Rollback
    public void userSaveAndDeleteThenNotFound() {
        UserProfile user1 = UserBuilder.aUser().withName("name1").withPassword("pass1").withFirstName("firstname1").withLastName("lastname1").build();
        userDao.save(user1);
        int id = user1.getId();
        userDao.flush();
        userDao.clear();
        UserProfile userDel = userDao.findById(UserProfile.class, id);
        userDao.delete(userDel);
        UserProfile user = userDao.findById(UserProfile.class, id);
        Assert.assertNull("用户未被正确删除", user);
    }

    @Test
    @Transactional
    @Rollback
    public void userUpdate() {
        UserProfile user = UserBuilder.aUser().withName("name1").withPassword("pass1").withFirstName("firstname1").withLastName("lastname1").build();
        userDao.save(user);
        int id = user.getId();
        UserProfile userUpdate = UserBuilder.aUser().withId(id).withName("name2").withPassword("pass2").withFirstName("firstname2").withLastName("lastname2").build();
        userDao.update(userUpdate);
        userDao.flush();
        userDao.clear();
        UserProfile userComp = userDao.findById(UserProfile.class, id);
        Assert.assertTrue("用户更新失败", userComp.getName().equals(userUpdate.getName()));
        Assert.assertTrue("用户更新失败", userComp.getPassword().equals(userUpdate.getPassword()));
        Assert.assertTrue("用户更新失败", userComp.getFirstName().equals(userUpdate.getFirstName()));
        Assert.assertTrue("用户更新失败", userComp.getLastName().equals(userUpdate.getLastName()));
    }

    @Test
    @Transactional
    @Rollback
    public void threeUserSaveThenFindThreeUser() {
        UserProfile user1 = UserBuilder.aUser().withName("name1").withPassword("pass1").withFirstName("firstname1").withLastName("lastname1").build();
        userDao.save(user1);
        UserProfile user2 = UserBuilder.aUser().withName("name2").withPassword("pass2").withFirstName("firstname2").withLastName("lastname2").build();
        userDao.save(user2);
        UserProfile user3 = UserBuilder.aUser().withName("name3").withPassword("pass3").withFirstName("firstname3").withLastName("lastname3").build();
        userDao.save(user3);
        userDao.flush();
        userDao.clear();
        List<UserProfile> rs1 = userDao.query("select u from UserProfile u");
        List<UserProfile> rs2 = userDao.queryAll(UserProfile.class);
        Assert.assertTrue("添加的记录条数和查询出的记录条数不一致", rs1.size() == 3);
        Assert.assertTrue("添加的记录条数和查询出的记录条数不一致", rs2.size() == 3);
    }

    @Test
    @Transactional
    @Rollback
    public void userCreateCascadeRoleAndOrgRelation() {
        RoleProfile role1 = RoleBuilder.aRole().withCode("code1").withDescription("desc1").withName("name1").build();
        RoleProfile role2 = RoleBuilder.aRole().withCode("code2").withDescription("desc2").withName("name2").build();
        OrgProfile org = OrgBuilder.aOrg().withCode("code1").withDescription("desc1").withLevel(1).withName("name1").withOrder(11).build();
        Set<RoleProfile> roles = new HashSet<RoleProfile>();
        roles.add(role1);
        roles.add(role2);
        UserProfile user = UserBuilder.aUser().withRoles(roles).withOrg(org).withName("name1").withPassword("pass1").withFirstName("firstname1").withLastName("lastname1").build();
        roleDao.save(role1);
        roleDao.save(role2);
        orgDao.save(org);
        userDao.save(user);
        int userId = user.getId();
        int orgId = org.getId();
        roleDao.flush();
        roleDao.clear();
        orgDao.flush();
        orgDao.clear();
        userDao.flush();
        userDao.clear();
        UserProfile userComp = userDao.findById(UserProfile.class, userId);
        Assert.assertNotNull("用户所属组织机构关系未级联保存", userComp.getOrg());
        Assert.assertTrue("用户所属组织机构关系未正确保存", userComp.getOrg().getId() == orgId);
        Assert.assertTrue("用户所属角色关系未正确保存", userComp.getRoles().size() == 2);
    }

    @Test
    @Transactional
    @Rollback
    public void userUpdateCascadeOrgAndRoleRelation() {
        RoleProfile role1 = RoleBuilder.aRole().withCode("code1").withDescription("desc1").withName("name1").build();
        RoleProfile role2 = RoleBuilder.aRole().withCode("code2").withDescription("desc2").withName("name2").build();
        OrgProfile org = OrgBuilder.aOrg().withCode("code1").withDescription("desc1").withLevel(1).withName("name1").withOrder(11).build();
        Set<RoleProfile> roles = new HashSet<RoleProfile>();
        roles.add(role1);
        roles.add(role2);
        UserProfile user = UserBuilder.aUser().withName("name1").withPassword("pass1").withFirstName("firstname1").withLastName("lastname1").build();
        roleDao.save(role1);
        roleDao.save(role2);
        orgDao.save(org);
        userDao.save(user);
        int userId = user.getId();
        int orgId = org.getId();
        roleDao.flush();
        roleDao.clear();
        orgDao.flush();
        orgDao.clear();
        userDao.flush();
        userDao.clear();
        UserProfile userUpdate = userDao.findById(UserProfile.class, userId);
        userUpdate.setOrg(org);
        userUpdate.setRoles(roles);
        userDao.update(userUpdate);
        userDao.flush();
        userDao.clear();
        UserProfile userComp = userDao.findById(UserProfile.class, userId);
        Assert.assertNotNull("用户所属组织机构关系未级联保存", userComp.getOrg());
        Assert.assertTrue("用户所属组织机构关系未正确保存", userComp.getOrg().getId() == orgId);
        Assert.assertTrue("用户所属角色关系未正确保存", userComp.getRoles().size() == 2);
    }

    @Test
    @Transactional
    @Rollback
    public void userDeleteCascadeOrgAndRoleRelation() {
        RoleProfile role1 = RoleBuilder.aRole().withCode("code1").withDescription("desc1").withName("name1").build();
        RoleProfile role2 = RoleBuilder.aRole().withCode("code2").withDescription("desc2").withName("name2").build();
        OrgProfile org = OrgBuilder.aOrg().withCode("code1").withDescription("desc1").withLevel(1).withName("name1").withOrder(11).build();
        Set<RoleProfile> roles = new HashSet<RoleProfile>();
        roles.add(role1);
        roles.add(role2);
        UserProfile user = UserBuilder.aUser().withRoles(roles).withOrg(org).withName("name1").withPassword("pass1").withFirstName("firstname1").withLastName("lastname1").build();
        roleDao.save(role1);
        roleDao.save(role2);
        orgDao.save(org);
        userDao.save(user);
        int userId = user.getId();
        int orgId = org.getId();
        int roleId1 = role1.getId();
        int roleId2 = role2.getId();
        roleDao.flush();
        roleDao.clear();
        orgDao.flush();
        orgDao.clear();
        userDao.flush();
        userDao.clear();
        UserProfile userDel = userDao.findById(UserProfile.class, userId);
        userDel.setOrg(null);
        userDel.setRoles(null);
        userDao.update(userDel);
        userDao.delete(userDel);
        UserProfile userComp = userDao.findById(UserProfile.class, userId);
        OrgProfile orgComp = orgDao.findById(OrgProfile.class, orgId);
        RoleProfile roleComp1 = roleDao.findById(RoleProfile.class, roleId1);
        RoleProfile roleComp2 = roleDao.findById(RoleProfile.class, roleId2);
        Assert.assertNull("未正确删除用户", userComp);
        Assert.assertNotNull("不应该级联删除组织机构", orgComp);
        Assert.assertNotNull("不应级联删除角色", roleComp1);
        Assert.assertNotNull("不应级联删除角色", roleComp2);
    }
}
