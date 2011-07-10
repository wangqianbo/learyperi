package com.jeremiahxu.learyperi.menu;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.jeremiahxu.learyperi.dao.GenericDao;
import com.jeremiahxu.learyperi.menu.pojo.Menu;

/**
 * @author Jeremiah Xu
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationContext-test.xml" })
public class MenuTest extends AbstractTransactionalJUnit4SpringContextTests {
	@Resource(name = "dao")
	private GenericDao<Menu, Integer> dao;

	public void setDao(GenericDao<Menu, Integer> dao) {
		this.dao = dao;
	}

	@Test
	@DirtiesContext
	@Transactional
	@Rollback(value = true)
	public void menuSaveThenIdIsGreaterThanZero() {
		Menu menu = MenuBuilder.aMenu().withCode("code1").withName("菜单1").withImagePath("imagepath1").withUrl("url1").build();
		dao.save(menu);
		int id = menu.getId();
		Assert.assertTrue(id > 0);
	}

	@Test
	@DirtiesContext
	@Transactional
	@Rollback(value = true)
	public void threeMenuSaveThenParentIdIsCorrect() {
		Menu menu1 = MenuBuilder.aMenu().withCode("code1").withName("菜单1").withImagePath("imagepath1").withUrl("url1").build();
		Menu menu11 = MenuBuilder.aMenu().withCode("code11").withName("菜单11").withImagePath("imagepath11").withUrl("url11")
				.with(menu1).build();
		Menu menu12 = MenuBuilder.aMenu().withCode("code12").withName("菜单12").withImagePath("imagepath12").withUrl("url12")
				.with(menu1).build();
		dao.save(menu1);
		dao.save(menu11);
		dao.save(menu12);
		Assert.assertTrue("子菜单保存后的父节点不正确", menu11.getParent().getId() == menu1.getId());
		Assert.assertTrue("子菜单保存后的父节点不正确", menu12.getParent().getId() == menu1.getId());
	}

	@Test
	@DirtiesContext
	@Transactional
	@Rollback(value = true)
	public void menuSaveAndDeleteThenNotFound() {
		Menu menu1 = MenuBuilder.aMenu().withCode("code1").withName("菜单1").withImagePath("imagepath1").withUrl("url1").build();
		dao.save(menu1);
		int id = menu1.getId();
		dao.delete(menu1);
		Menu menu = dao.findById(Menu.class, id);
		Assert.assertNull("菜单项未被删除", menu);
	}

	@Test
	@DirtiesContext
	@Transactional
	@Rollback(value = true)
	public void menuUpdate() {
		Menu menu = MenuBuilder.aMenu().withCode("code1").withName("菜单1").withImagePath("imagepath1").withUrl("url1")
				.withLevel(1).withIdPath("/1/").withNamePath("/菜单1/").build();
		dao.save(menu);
		int id = menu.getId();
		Menu menuUpdate = MenuBuilder.aMenu().withCode("code2").withName("菜单2").withImagePath("imagepath2").withUrl("url2")
				.withLevel(2).withIdPath("/2/").withNamePath("/菜单2/").with(id).build();
		dao.update(menuUpdate);
		Menu menuComp = dao.findById(Menu.class, id);
		Assert.assertTrue("更新失败", menuComp.getCode().equals(menuUpdate.getCode()));
		Assert.assertTrue("更新失败", menuComp.getName().equals(menuUpdate.getName()));
		Assert.assertTrue("更新失败", menuComp.getImagePath().equals(menuUpdate.getImagePath()));
		Assert.assertTrue("更新失败", menuComp.getUrl().equals(menuUpdate.getUrl()));
		Assert.assertTrue("更新失败", menuComp.getLevel() == menuUpdate.getLevel());
		Assert.assertTrue("更新失败", menuComp.getIdPath().equals(menuUpdate.getIdPath()));
		Assert.assertTrue("更新失败", menuComp.getNamePath().equals(menuUpdate.getNamePath()));
	}

	@Test
	@DirtiesContext
	@Transactional
	@Rollback(value = true)
	public void threeMenuSaveThenFindThreeMenu() {
		Menu menu1 = MenuBuilder.aMenu().withCode("code1").withName("菜单1").withImagePath("imagepath1").withUrl("url1")
				.withLevel(1).withIdPath("/1/").withNamePath("/菜单1/").build();
		Menu menu2 = MenuBuilder.aMenu().withCode("code2").withName("菜单2").withImagePath("imagepath2").withUrl("url2")
				.withLevel(1).withIdPath("/2/").withNamePath("/菜单2/").build();
		Menu menu3 = MenuBuilder.aMenu().withCode("code3").withName("菜单3").withImagePath("imagepath3").withUrl("url3")
				.withLevel(1).withIdPath("/3/").withNamePath("/菜单3/").build();
		dao.save(menu1);
		dao.save(menu2);
		dao.save(menu3);
		List<Menu> rs = dao.queryByJPQL("select m from Menu m");
		Assert.assertTrue("添加的记录条数和查询出的记录条数不一致", rs.size() == 3);
	}

}
