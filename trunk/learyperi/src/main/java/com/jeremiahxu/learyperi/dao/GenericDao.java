package com.jeremiahxu.learyperi.dao;

import java.io.Serializable;
import java.util.List;

/**
 * 数据持久化操作通用接口。
 * 
 * @author Jeremiah Xu
 * 
 */
public interface GenericDao<T, ID extends Serializable> {
    public void save(T entity);

    public T update(T entity);

    public void delete(T entity);

    public void delete(Class<T> clazz, ID id);

    public T findById(Class<T> clazz, ID id);

    public List<T> queryByJPQL(String jpql);

    public void flush();

    public List<T> queryAll(Class<T> clazz);

}
