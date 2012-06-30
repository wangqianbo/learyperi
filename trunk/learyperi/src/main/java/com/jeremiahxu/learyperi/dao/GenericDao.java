package com.jeremiahxu.learyperi.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 数据持久化操作通用接口。
 * 
 * @author Jeremiah Xu
 * 
 */
public interface GenericDao<T, ID extends Serializable> {
    /**
     * 新增实体
     * 
     * @param entity
     */
    public void save(T entity);

    /**
     * 更新实体
     * 
     * @param entity
     * @return
     */
    public T update(T entity);

    /**
     * 删除实体
     * 
     * @param entity
     */
    public void delete(T entity);

    /**
     * 删除实体
     * 
     * @param clazz
     * @param id
     */
    public void delete(Class<T> clazz, ID id);

    /**
     * 根据实体id取得实体
     * 
     * @param clazz
     * @param id
     * @return
     */
    public T findById(Class<T> clazz, ID id);

    /**
     * 刷新数据到数据库
     */
    public void flush();

    /**
     * 清除一级缓存
     */
    public void clear();

    /**
     * 查询出指定实体类型的所有数据
     * 
     * @param clazz
     * @return
     */
    public List<T> queryAll(Class<T> clazz);

    /**
     * 执行一个JPQL
     * 
     * @param jpql
     * @return
     */
    public List<T> query(String jpql);

    /**
     * 按照指定条件参数执行一个JPQL
     * 
     * @param jpql
     * @param params
     *            JPQL的执行条件参数，是一个MAP，key是属性名，value是属性值。
     * @return
     */
    public List<T> query(String jpql, Map<String, ? extends Object> params);

    /**
     * 根据指定页码，每页记录数来执行JPQL查询
     * 
     * @param jpql
     *            实际上是一个查询的JPQL
     * @param pageNumber
     *            页码
     * @param numberPerPage
     *            每页记录数
     * @return
     */
    public List<T> query(String jpql, int pageNumber, int numberPerPage);

    /**
     * 根据指定条件、页码、每页记录数执行一个JPQL。
     * 
     * @param jpql
     *            实际上是一个查询的JPQL
     * @param params
     *            JPQL的执行条件参数，是一个MAP，key是属性名，value是属性值。
     * @param pageNumber
     *            页码
     * @param numberPerPage
     *            每页记录数
     * @return
     */
    public List<T> query(String jpql, Map<String, ? extends Object> params, int pageNumber, int numberPerPage);

}
