package com.jeremiahxu.learyperi.menu;

import java.util.List;

import javax.annotation.Resource;

import junit.framework.Assert;

import org.hibernate.ejb.EntityManagerFactoryImpl;
import org.hibernate.stat.Statistics;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.orm.jpa.EntityManagerFactoryInfo;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jeremiahxu.learyperi.dao.GenericDao;
import com.jeremiahxu.learyperi.menu.pojo.Menu;

/**
 * Menu对象的缓存测试
 * 
 * @author Jeremiah Xu
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationContext-test.xml" })
public class MenuCacheTest extends AbstractTransactionalJUnit4SpringContextTests {
    @Resource(name = "dao")
    private GenericDao<Menu, Integer> dao;

    public void setDao(GenericDao<Menu, Integer> dao) {
        this.dao = dao;
    }

    @Before
    public void prepareMenuData(){
        Menu menu1 = MenuBuilder.aMenu().withCode("code1").withName("菜单1").withType(1).withLevel(1).withOrder(1).withImagePath("imagepath1").withUrl("url1").build();
        Menu menu11 = MenuBuilder.aMenu().withCode("code11").withName("菜单11").withType(1).withLevel(1).withOrder(1).withImagePath("imagepath11").withUrl("url11").withParent(menu1).build();
        Menu menu12 = MenuBuilder.aMenu().withCode("code12").withName("菜单12").withType(1).withLevel(1).withOrder(1).withImagePath("imagepath12").withUrl("url12").withParent(menu1).build();
        dao.save(menu1);
        dao.save(menu11);
        dao.save(menu12);        
    }
    @Test
    @Rollback
    public void queryThreeMenuByCache() {
        List<Menu> result = dao.queryAll(Menu.class);
        Assert.assertEquals(3, result.size());
        result = dao.queryAll(Menu.class);
        Assert.assertEquals(3, result.size());
        EntityManagerFactoryInfo entityManagerFactoryInfo = (EntityManagerFactoryInfo) applicationContext.getBean("entityManagerFactory");
        EntityManagerFactoryImpl emfImp = (EntityManagerFactoryImpl) entityManagerFactoryInfo.getNativeEntityManagerFactory();
        Statistics sts = emfImp.getSessionFactory().getStatistics();
        System.out.println(sts);
        Assert.assertEquals(3, sts.getEntityInsertCount());
        Assert.assertEquals(1, sts.getQueryCachePutCount());
        Assert.assertEquals(1, sts.getQueryCacheHitCount());
        sts.clear();
    }

    @Test
    @Rollback
    public void createMenuAndFindByCache() {
        Menu menu = MenuBuilder.aMenu().withCode("code1").withName("菜单1").withType(1).withLevel(1).withOrder(1).withImagePath("imagepath1").withUrl("url1").build();
        dao.save(menu);
        int id = menu.getId();
        Menu m = dao.findById(Menu.class, id);
        Assert.assertNotNull(m);
        EntityManagerFactoryInfo entityManagerFactoryInfo = (EntityManagerFactoryInfo) applicationContext.getBean("entityManagerFactory");
        EntityManagerFactoryImpl emfImp = (EntityManagerFactoryImpl) entityManagerFactoryInfo.getNativeEntityManagerFactory();
        Statistics sts = emfImp.getSessionFactory().getStatistics();
        System.out.println(sts);
        Assert.assertEquals(1, sts.getEntityInsertCount());
        Assert.assertEquals(1, sts.getEntityLoadCount());
        sts.clear();
    }
}
