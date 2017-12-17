package com.zagorskidev.rescuecrm.dao;

import java.lang.reflect.ParameterizedType;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

/**
 * Implements common persistence operations for subclasses.
 * @author tomek
 *
 * @param <Type>
 */
@Transactional
public abstract class AbstractDAO<Type>{

    private final Class<Type> persistentClass;
    private final String typeArgName;
    
    /**
     * Initialize fields related to generic type useful for persistence operations.
     */
    @SuppressWarnings("unchecked")
    public AbstractDAO(){
    	
        this.persistentClass = (Class<Type>) 
        		((ParameterizedType) this.getClass()
        				.getGenericSuperclass())
        				.getActualTypeArguments()[0];
        this.typeArgName = this.persistentClass.getSimpleName();
    }
	
    @PersistenceContext
	private EntityManager entityManager;
    
	protected EntityManager getEntityManager() {
		
		return this.entityManager;
	}
	
	/**
	 * Single item by id.
	 * @param id
	 * @return
	 */
	public Type get(int id) {
		
		Type item = entityManager.find(persistentClass, id);
		return item;
	}

	/**
	 * Add new.
	 * @param item
	 */
	public void persist(Type item) {
		
		entityManager.persist(item);
	}
	
	/**
	 * Update.
	 * @param item
	 */
	public void merge(Type item) {
		
		entityManager.merge(item);
	}

	public void delete(int id) {

		Query query = entityManager.createQuery("delete " +typeArgName+ " where id=:itemId");
		query.setParameter("itemId", id);
		query.executeUpdate();
	}
}
