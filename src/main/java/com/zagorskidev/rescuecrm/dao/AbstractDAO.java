package com.zagorskidev.rescuecrm.dao;

import java.lang.reflect.ParameterizedType;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractDAO<Type>{

    private final Class<Type> persistentClass;
    private final String typeArgName;
    
    @SuppressWarnings("unchecked")
    public AbstractDAO(){
    	
        this.persistentClass = (Class<Type>) 
        		((ParameterizedType) this.getClass()
        				.getGenericSuperclass())
        				.getActualTypeArguments()[0];
        this.typeArgName = this.persistentClass.getSimpleName();
    }
	
	@Autowired
	private SessionFactory sessionFactory;
	
	protected SessionFactory getSessionFactory() {
		
		return this.sessionFactory;
	}
	
	public Type get(int id) {
		
		Session currentSession = sessionFactory.getCurrentSession();
		Type item = currentSession.get(persistentClass, id);
		return addLazyData(item);
	}

	public void persist(Type item) {
		
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.persist(item);
	}

	public void delete(int id) {

		Session currentSession = sessionFactory.getCurrentSession();
		Query<?> query = currentSession.createQuery("delete " +typeArgName+ " where id=:itemId");
		query.setParameter("itemId", id);
		query.executeUpdate();
	}
	
	public abstract Type addLazyData(Type item);
}
