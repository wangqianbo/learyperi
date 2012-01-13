package com.jeremiahxu.learyperi.user.pojo;

import java.io.Serializable;
import java.util.ArrayList;
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

/**
 * 组织结构实体对象
 * 
 * @author Jeremiah Xu
 * 
 */
@Entity
@Table(name = "T_ORG_INFO")
public class OrgProfile implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "OGI_ID", length = 10)
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Integer id;// 唯一标识
    @Column(name = "OGI_CODE", length = 50, nullable = false, unique = true)
    private String code;// 组织机构代码
    @Column(name = "OGI_NAME", length = 50, nullable = false)
    private String name;// 组织机构名称
    @Column(name = "OGI_LEVEL", length = 2, nullable = false)
    private int level = 0;// 组织机构在树形结构中所处的层级
    @Column(name = "OGI_ORDER", length = 5, nullable = false)
    private int order = 0;// 组织机构显示的顺序
    @Column(name = "OGI_DESC", length = 100, nullable = false)
    private String description;// 组织机构描述
    @Column(name = "OGI_ID_PATH", length = 250, nullable = false)
    private String idPath = "";// 组织机构的ID全路径，例如：/123/124/323/
    @Column(name = "OGI_NAME_PATH", length = 250, nullable = false)
    private String namePath = "";// 组织机构的名称全路径，例如：/root_org/总公司/分公司/
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "OGI_PARENT_ID")
    private OrgProfile parent;// 所属上级组织机构
    @OneToMany(fetch = FetchType.EAGER, cascade = { CascadeType.ALL }, targetEntity = OrgProfile.class, mappedBy = "parent")
    private List<OrgProfile> children = new ArrayList<OrgProfile>();// 下级组织机构列表

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public OrgProfile getParent() {
        return parent;
    }

    public void setParent(OrgProfile parent) {
        parent.getChildren().add(this);
        this.parent = parent;
    }

    public List<OrgProfile> getChildren() {
        return children;
    }

    public void setChildren(List<OrgProfile> children) {
        for (OrgProfile org : children) {
            org.setParent(this);
        }
        this.children = children;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
