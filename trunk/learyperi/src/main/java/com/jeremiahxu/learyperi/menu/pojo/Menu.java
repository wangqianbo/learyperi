package com.jeremiahxu.learyperi.menu.pojo;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * menu item
 * 
 * @author Jeremiah Xu
 * 
 */
@Entity
@Table(name = "T_MENU_INFO")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Menu implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "MI_ID", length = 10)
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Integer id;
	@Column(name = "MI_CODE", length = 20, nullable = false, unique = true)
	private String code = "";// 代码
	@Column(name = "MI_NAME", length = 40, nullable = false)
	private String name = "";// 名称
	@Column(name = "MI_LEVEL", length = 2, nullable = false)
	private int level = 0;// 层级
	@Column(name = "MI_URL", length = 250, nullable = false)
	private String url = "";// 要访问的URL
	@Column(name = "MI_IMG_PATH", length = 250, nullable = false)
	private String imagePath = "";// 菜单上显示的图片
	@Column(name = "MI_ID_PATH", length = 250, nullable = false)
	private String idPath = "";// id的全路径
	@Column(name = "MI_NAME_PATH", length = 250, nullable = false)
	private String namePath = "";// 名称的全路径
	@OneToMany(fetch = FetchType.EAGER, cascade = { CascadeType.REFRESH, CascadeType.REMOVE }, targetEntity = Menu.class,
			mappedBy = "parent")
	private List<Menu> children;// 子节点列表
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
	@JoinColumn(name = "MI_PARENT_ID")
	private Menu parent;// 父节点

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public String getIdPath() {
		return idPath;
	}

	public void setIdPath(String idPath) {
		this.idPath = idPath;
	}

	public String getNamePath() {
		return namePath;
	}

	public void setNamePath(String namePath) {
		this.namePath = namePath;
	}

	public List<Menu> getChildren() {
		return children;
	}

	public void setChildren(List<Menu> children) {
		this.children = children;
	}

	public Menu getParent() {
		return parent;
	}

	public void setParent(Menu parent) {
		this.parent = parent;
	}

}
