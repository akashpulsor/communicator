package com.communicate.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import com.communicate.dao.GenericDao;
import com.communicate.model.User;

public class GenericDaoImpl<T>  extends HibernateDaoSupport implements GenericDao<T> {

	@PersistenceContext
	EntityManager entityManager;
	private Class<T> type;
	
	
	
	public GenericDaoImpl(Class<T> type) {
		super();
		this.type = type;
	}
	@Override
	@Transactional(readOnly=true)
	public T get( Long id ) {
		
		if (id == null) {
			return null;
		}
		else {
			return this.getHibernateTemplate().load(type, id);
			//return entityManager.find(type, id);
		}
	}

	@Override
	@Transactional(readOnly=true)
	public List<T> getall() {
		// TODO Auto-generated method stub
		return entityManager.createQuery(
				"select o from " + type.getName() + "o"
				).getResultList();
		
	}

	@Override
	public void save(T t) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().save(t);
	}

	@Override
	public void delete(T t) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().delete(t);
	}

	@PersistenceContext
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

}
