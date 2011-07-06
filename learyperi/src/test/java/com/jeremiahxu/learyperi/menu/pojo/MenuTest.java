package com.jeremiahxu.learyperi.menu.pojo;

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
	@Rollback(value = true)
	@Transactional
	public void menuSavedAndGetId() {
		dao.save(menu1);
		int id = menu1.getId();
		Assert.assertTrue(id > 0);
	}

}
