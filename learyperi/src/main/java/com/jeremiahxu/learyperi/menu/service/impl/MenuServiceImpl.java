package com.jeremiahxu.learyperi.menu.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jeremiahxu.learyperi.dao.GenericDao;
import com.jeremiahxu.learyperi.menu.pojo.Menu;
import com.jeremiahxu.learyperi.menu.service.MenuService;

/**
 * 菜单操作服务实现类。
 * 
 * @author Jeremiah Xu
 * 
 */
@Service("menuService")
public class MenuServiceImpl implements MenuService {

	@Resource(name = "dao")
	private GenericDao<Menu, Integer> menuDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.jeremiahxu.learyperi.menu.service.MenuService#createMenu(com.jeremiahxu
	 * .learyperi.menu.pojo.Menu)
	 */
	@Override
	public void createMenu(Menu menu) {
		this.getMenuDao().save(menu);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.jeremiahxu.learyperi.menu.service.MenuService#deleteMenu(com.jeremiahxu
	 * .learyperi.menu.pojo.Menu)
	 */
	@Override
	public void deleteMenu(Menu menu) {
		Menu menuDel = this.getMenuDao().findById(Menu.class, menu.getId());
		this.getMenuDao().delete(menuDel);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.jeremiahxu.learyperi.menu.service.MenuService#updateMenu(com.jeremiahxu
	 * .learyperi.menu.pojo.Menu)
	 */
	@Override
	public void updateMenu(Menu menu) {
		this.getMenuDao().update(menu);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jeremiahxu.learyperi.menu.service.MenuService#findAllMenu()
	 */
	@Override
	public List<Menu> findAllMenu() {
		return this.getMenuDao().findAll(Menu.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jeremiahxu.learyperi.menu.service.MenuService#findMenu(int)
	 */
	@Override
	public Menu findMenu(int id) {
		return this.getMenuDao().findById(Menu.class, id);
	}

	public GenericDao<Menu, Integer> getMenuDao() {
		return menuDao;
	}

	public void setMenuDao(GenericDao<Menu, Integer> menuDao) {
		this.menuDao = menuDao;
	}

}
