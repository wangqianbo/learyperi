package com.jeremiahxu.learyperi.menu.pojo;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.AbstractTransactionalDataSourceSpringContextTests;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.jeremiahxu.learyperi.dao.GenericDao;

/**
 * @author Jeremiah Xu
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationContext-test.xml", "/applicationContext-menu.xml" })
public class MenuTest extends AbstractTransactionalDataSourceSpringContextTests {
	@Resource(name = "dao")
	private GenericDao<Menu, Integer> dao;
	@Resource(name = "menu1")
	private Menu menu1;
	@Resource(name = "menu11")
	private Menu menu11;
	@Resource(name = "menu12")
	private Menu menu12;

	public void setMenu1(Menu menu1) {
		this.menu1 = menu1;
	}

	public void setMenu11(Menu menu11) {
		this.menu11 = menu11;
	}

	public void setMenu12(Menu menu12) {
		this.menu12 = menu12;
	}

	public void setDao(GenericDao<Menu, Integer> dao) {
		this.dao = dao;
	}

	@Test
	@DirtiesContext
	@Transactional
	@Rollback(value = true)
	public void menuSaveThenIdIsGreaterThanZero() {
		dao.save(menu1);
		int id = menu1.getId();
		Assert.assertTrue(id > 0);
	}

	@Test
	@DirtiesContext
	@Transactional
	@Rollback(value = true)
	public void threeMenuSaveThenParentIdIsCorrect() {
		menu11.setParent(menu1);
		menu12.setParent(menu1);
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
		dao.save(menu1);
		int id = menu1.getId();
		menu1.setCode("codeA");
		menu1.setIdPath("idpathA");
		menu1.setImagePath("imagePathA");
		menu1.setLevel((short) 2);
		menu1.setName("nameA");
		menu1.setNamePath("namePathA");
		menu1.setUrl("urlA");
		dao.update(menu1);
		Menu menu = dao.findById(Menu.class, id);
		Assert.assertTrue("更新失败", menu.equals(menu1));
	}

	@Test
	@DirtiesContext
	@Transactional
	@Rollback(value = true)
	public void threeMenuSaveThenFindThreeMenu() {
		dao.save(menu1);
		dao.save(menu11);
		dao.save(menu12);
		List<Menu> rs = dao.findAll(Menu.class);
		Assert.assertTrue("添加的记录条数和查询出的记录条数不一致", rs.size() == 3);
	}

}
