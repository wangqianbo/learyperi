package com.jeremiahxu.learyperi.dao.impl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
    public T findById(Class<T> clazz, ID id) {
        return em.find(clazz, id);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<T> queryByJPQL(String jpql) {
        return em.createQuery(jpql).getResultList();
    }

    @Override
    public void flush() {
        em.flush();
    }

    @Override
    public List<T> queryAll(Class<T> clazz) {
        String jpql = "SELECT o FROM " + clazz.getSimpleName() + " o";
        return queryByJPQL(jpql);
    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

}
