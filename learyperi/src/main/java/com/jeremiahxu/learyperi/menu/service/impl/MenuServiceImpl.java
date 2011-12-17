package com.jeremiahxu.learyperi.menu.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    /**
     * 计算idpath、namepath和level属性。
     * 
     * @param menu
     */
    private void computePathAndLevel(Menu menu) {
        String idPath = menu.getId() + "/";
        String namePath = menu.getName() + "/";
        int level = 0;
        Menu parent = menu.getParent();
        if (parent == null) {
            idPath = "/" + idPath;
            namePath = "/" + namePath;
            level = 0;
        } else {
            parent = this.findMenu(parent.getId());
            idPath = parent.getIdPath() + idPath;
            namePath = parent.getNamePath() + namePath;
            level = parent.getLevel() + 1;
        }
        menu.setIdPath(idPath);
        menu.setNamePath(namePath);
        menu.setLevel(level);
        if (menu.getChildren() != null) {
            for (Menu child : menu.getChildren()) {
                computePathAndLevel(child);
            }
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.jeremiahxu.learyperi.menu.service.MenuService#createMenu(com.jeremiahxu
     * .learyperi.menu.pojo.Menu)
     */
    @Override
    @Transactional
    public void createMenu(Menu menu) {
        this.getMenuDao().save(menu);
        this.computePathAndLevel(menu);
        this.getMenuDao().update(menu);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.jeremiahxu.learyperi.menu.service.MenuService#deleteMenu(com.jeremiahxu
     * .learyperi.menu.pojo.Menu)
     */
    @Override
    @Transactional
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
    @Transactional
    public void updateMenu(Menu menu) {
        this.computePathAndLevel(menu);
        this.getMenuDao().update(menu);
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

    /*
     * (non-Javadoc)
     * 
     * @see com.jeremiahxu.learyperi.menu.service.MenuService#findRoot()
     */
    @Override
    public Menu findRoot() {
        List<Menu> rs = this.getMenuDao().queryByJPQL("select m from Menu m where m.code='root_menu' and m.level=0");
        if (rs.size() == 1) {
            return rs.get(0);
        } else {
            return null;
        }
    }

    public GenericDao<Menu, Integer> getMenuDao() {
        return menuDao;
    }

    public void setMenuDao(GenericDao<Menu, Integer> menuDao) {
        this.menuDao = menuDao;
    }

}
