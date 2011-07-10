package com.jeremiahxu.learyperi.menu;

import com.jeremiahxu.learyperi.menu.pojo.Menu;

public class MenuBuilder {
	private Menu menu;

	private MenuBuilder() {
		menu = new Menu();
	}

	public static MenuBuilder aMenu() {
		return new MenuBuilder();
	}

	public Menu build() {
		return menu;
	}

	public MenuBuilder withCode(String code) {
		this.menu.setCode(code);
		return this;
	}

	public MenuBuilder withName(String name) {
		this.menu.setName(name);
		return this;
	}

	public MenuBuilder withImagePath(String imagePath) {
		this.menu.setImagePath(imagePath);
		return this;
	}

	public MenuBuilder withUrl(String url) {
		this.menu.setUrl(url);
		return this;
	}

	public MenuBuilder withIdPath(String idPath) {
		this.menu.setIdPath(idPath);
		return this;
	}

	public MenuBuilder withLevel(int level) {
		this.menu.setLevel(level);
		return this;
	}

	public MenuBuilder withNamePath(String namePath) {
		this.menu.setNamePath(namePath);
		return this;
	}

	public MenuBuilder with(Menu parent) {
		this.menu.setParent(parent);
		return this;
	}

	public MenuBuilder with(int id) {
		this.menu.setId(id);
		return this;
	}

}
