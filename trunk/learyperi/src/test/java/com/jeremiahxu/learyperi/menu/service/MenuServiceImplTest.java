package com.jeremiahxu.learyperi.menu.service;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.jeremiahxu.learyperi.menu.MenuBuilder;
import com.jeremiahxu.learyperi.menu.pojo.Menu;

/**
 * @author Jeremiah Xu
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationContext-test.xml" })
public class MenuServiceImplTest extends AbstractTransactionalJUnit4SpringContextTests {
	@Resource(name = "menuService")
	private MenuService menuService;

	public void setMenuService(MenuService menuService) {
		this.menuService = menuService;
	}

	@Test
	@Transactional
	@Rollback(value = true)
	public void createMenuAndComputeAllFields() {
		Menu menu = MenuBuilder.aMenu().withCode("code1").withName("菜单1").withImagePath("imagepath1").withUrl("url1").build();
		menuService.createMenu(menu);
		int id = menu.getId();
		Menu menuComp = menuService.findMenu(id);
		Assert.assertTrue("创建菜单失败", menu.equals(menuComp));
		Assert.assertTrue("idpath不正确", menuComp.getIdPath().equals("/" + id + "/"));
		Assert.assertTrue("level不正确", menuComp.getLevel() == 0);
		Assert.assertTrue("namepath不正确", menuComp.getNamePath().equals("/菜单1/"));
	}

	@Test
	@Transactional
	@Rollback(value = true)
	public void createMenuWithParentAndComputeAllFields() {
		Menu menu1 = MenuBuilder.aMenu().withCode("code1").withName("菜单1").withImagePath("imagepath1").withUrl("url1").build();
		menuService.createMenu(menu1);
		Menu parent = new Menu();
		parent.setId(menu1.getId());
		Menu menu11 = MenuBuilder.aMenu().withCode("code11").withName("菜单11").withImagePath("imagepath11").withUrl("url11")
				.with(parent).build();
		menuService.createMenu(menu11);
		int id = menu11.getId();
		Menu menuComp = menuService.findMenu(id);
		Assert.assertTrue("idpath不正确", menuComp.getIdPath().equals("/" + menu1.getId() + "/" + id + "/"));
		Assert.assertTrue("level不正确", menuComp.getLevel() == menu1.getLevel() + 1);
		Assert.assertTrue("namepath不正确", menuComp.getNamePath().equals("/菜单1/菜单11/"));
	}

	@Test
	@Transactional
	@Rollback(value = true)
	public void createMenuAndDeleteItThenNotFindIt() {
		Menu menu = MenuBuilder.aMenu().withCode("code1").withName("菜单1").withImagePath("imagepath1").withUrl("url1").build();
		menuService.createMenu(menu);
		int id = menu.getId();
		Menu menuDel = new Menu();
		menuDel.setId(id);
		menuService.deleteMenu(menu);
		Menu menuComp = menuService.findMenu(id);
		Assert.assertNull("菜单未删除", menuComp);
	}

	@Test
	@Transactional
	@Rollback(value = true)
	public void updateMenuAndFindIt() {
		Menu menu = MenuBuilder.aMenu().withCode("code1").withName("菜单1").withImagePath("imagepath1").withUrl("url1").build();
		menuService.createMenu(menu);
		int id = menu.getId();
		Menu menuUpdate = MenuBuilder.aMenu().withCode("code2").withName("菜单2").withImagePath("imagepath2").withUrl("url2")
				.build();
		menuUpdate.setId(id);
		menuService.updateMenu(menuUpdate);
		Menu menuComp = menuService.findMenu(id);
		Assert.assertTrue("更新失败", menuComp.getCode().equals(menuUpdate.getCode()));
		Assert.assertTrue("更新失败", menuComp.getName().equals(menuUpdate.getName()));
		Assert.assertTrue("更新失败", menuComp.getImagePath().equals(menuUpdate.getImagePath()));
		Assert.assertTrue("更新失败", menuComp.getUrl().equals(menuUpdate.getUrl()));
	}

}
