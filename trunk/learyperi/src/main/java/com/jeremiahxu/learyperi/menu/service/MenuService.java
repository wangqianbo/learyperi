package com.jeremiahxu.learyperi.menu.service;

import java.util.List;

import com.jeremiahxu.learyperi.menu.pojo.Menu;

/**
 * 对菜单的操作的服务接口
 * 
 * @author Jeremiah Xu
 * 
 */
public interface MenuService {

	/**
	 * 创建菜单项。
	 * 
	 * @param menu
	 */
	public void createMenu(Menu menu);

	/**
	 * 删除菜单项，根据id删除。
	 * 
	 * @param menu
	 */
	public void deleteMenu(Menu menu);

	/**
	 * 修改菜单项，根据id修改。
	 * 
	 * @param menu
	 */
	public void updateMenu(Menu menu);

	/**
	 * 查询出所有菜单项。
	 */
	public List<Menu> findAllMenu();

	/**
	 * 根据id取得菜单项。
	 * 
	 * @param id
	 * @return
	 */
	public Menu findMenu(int id);

}
