package com.zagorskidev.rescuecrm.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.zagorskidev.rescuecrm.entity.Operation;
import com.zagorskidev.rescuecrm.entity.OperationDetail;

@Repository
@Transactional
public class OperationDAOImpl extends AbstractDAO<Operation> implements OperationDAO {

	@Override
	public List<Operation> getSpecific(String state) {
		
		Session currentSession = getSessionFactory().getCurrentSession();
		Query<Operation> query = currentSession
				.createQuery("from Operation where state=:itemState order by id desc", Operation.class)
				.setParameter("itemState", state);
		return query.getResultList();
	}
	
	@Override
	public List<Operation> getAll() {
		
		Session currentSession = getSessionFactory().getCurrentSession();
		Query<Operation> query = currentSession
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
