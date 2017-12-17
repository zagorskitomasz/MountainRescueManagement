package com.zagorskidev.rescuecrm.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.zagorskidev.rescuecrm.entity.Rescuer;

/**
 * Implements persistence operations.
 * @author tomek
 *
 */
@Repository
@Transactional
public class RescuerDAOImpl extends AbstractDAO<Rescuer> implements RescuerDAO {

	@Override
	@SuppressWarnings("unchecked")
	public List<Rescuer> getAll() {
		
		EntityManager entityManager = getEntityManager();
		Query query = entityManager
				.createQuery("from Rescuer order by lastName, firstName", Rescuer.class);
		
		return query.getResultList();
	}
}
