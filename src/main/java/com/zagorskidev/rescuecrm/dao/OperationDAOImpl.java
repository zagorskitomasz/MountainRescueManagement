package com.zagorskidev.rescuecrm.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.zagorskidev.rescuecrm.entity.Operation;
import com.zagorskidev.rescuecrm.entity.OperationDetail;

@Repository
@Transactional
public class OperationDAOImpl extends AbstractDAO<Operation> implements OperationDAO {

	@Override
	@SuppressWarnings("unchecked")
	public List<Operation> getSpecific(String state) {
		
		EntityManager entityManager = getEntityManager();
		Query query = entityManager
				.createQuery("from Operation where state=:itemState order by id desc", Operation.class);
		query.setParameter("itemState", state);
		
		return query.getResultList();
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Operation> getAll() {
		
		EntityManager entityManager = getEntityManager();
		Query query = entityManager
				.createQuery("from Operation order by id desc", Operation.class);
		return query.getResultList();
	}
	
	@Override
	public Operation addLazyData(Operation operation) {
		
		OperationDetail operationDetail = operation.getOperationDetail();
		operationDetail.getRescuer();
		operation.getRescuers();
		
		return operation;
	}
}
