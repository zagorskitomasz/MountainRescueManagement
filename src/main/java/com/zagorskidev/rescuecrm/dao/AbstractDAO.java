package com.zagorskidev.rescuecrm.dao;

import java.lang.reflect.ParameterizedType;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
    
    /**
     * Initialize fields related to generic type useful for persistence operations.
     */
    @SuppressWarnings("unchecked")
    public AbstractDAO(){
    	
        this.persistentClass = (Class<Type>) 
        		((ParameterizedType) this.getClass()
        				.getGenericSuperclass())
        				.getActualTypeArguments()[0];
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
		
		Type toDelete = get(id);
		entityManager.remove(toDelete);
	}
}
