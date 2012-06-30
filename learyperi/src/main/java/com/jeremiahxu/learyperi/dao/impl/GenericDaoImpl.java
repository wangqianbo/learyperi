package com.jeremiahxu.learyperi.dao.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.jeremiahxu.learyperi.dao.GenericDao;

/**
 * 数据持久化操作实现类。
 * 
 * @author Jeremiah Xu
 * 
 */
@Repository("dao")
public class GenericDaoImpl<T, ID extends Serializable> implements GenericDao<T, ID> {
    @PersistenceContext
    protected EntityManager em;

    @Override
    public void save(T entity) {
        em.persist(entity);
    }

    @Override
    public T update(T entity) {
        return em.merge(entity);
    }

    @Override
    public void delete(T entity) {
        em.remove(entity);
    }

    @Override
    public void delete(Class<T> clazz, ID id) {
        T obj = em.find(clazz, id);
        em.remove(obj);
    }

    @Override
    public T findById(Class<T> clazz, ID id) {
        return em.find(clazz, id);
    }

    @Override
    public void flush() {
        em.flush();
    }

    @Override
    public void clear() {
        em.clear();
    }

    @Override
    public List<T> queryAll(Class<T> clazz) {
        String jpql = "SELECT o FROM " + clazz.getSimpleName() + " o";
        return query(jpql);
    }

    @Override
    public List<T> query(String jpql) {
        return query(jpql, null);
    }

    @Override
    public List<T> query(String jpql, Map<String, ? extends Object> params) {
        return query(jpql, params, -1, -1);
    }

    @Override
    public List<T> query(String jpql, int pageNumber, int numberPerPage) {
        return query(jpql, null, pageNumber, numberPerPage);
    }

    @Override
    public List<T> query(String jpql, Map<String, ? extends Object> params, int pageNumber, int numberPerPage) {
        Query query = em.createQuery(jpql);
        if (params != null && !params.isEmpty()) {// 如果有条件参数
            for (String key : params.keySet()) {
                query.setParameter(key, params.get(key));
            }
        }
        if (pageNumber >= 0 && numberPerPage > 0) {
            int firstResult = (pageNumber - 1) * numberPerPage;
            int maxResult = pageNumber * numberPerPage;
            query.setFirstResult(firstResult);
            query.setMaxResults(maxResult);
        }
        @SuppressWarnings("unchecked")
        List<T> rs = query.getResultList();
        return rs;
    }

}
