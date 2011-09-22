package com.jeremiahxu.learyperi.menu.service;

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
	 * 根据id取得菜单项。
	 * 
	 * @param id
	 * @return
	 */
	public Menu findMenu(int id);

	/**
	 * 取得菜单跟节点。
	 * 
	 * @return
	 */
	public Menu findRoot();

}
