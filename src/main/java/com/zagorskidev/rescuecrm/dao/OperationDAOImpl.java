package com.zagorskidev.rescuecrm.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.zagorskidev.rescuecrm.entity.Operation;

/**
 * Implements persistence operations.
 * @author tomek
 *
 */
@Repository
@Transactional
public class OperationDAOImpl extends AbstractDAO<Operation> implements OperationDAO {

	/**
	 * {@inheritDoc}
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<Operation> getAll() {
		
		EntityManager entityManager = getEntityManager();
		Query query = entityManager
				.createQuery("from Operation order by state desc, id desc", Operation.class);
		
		return query.getResultList();
	}
}
