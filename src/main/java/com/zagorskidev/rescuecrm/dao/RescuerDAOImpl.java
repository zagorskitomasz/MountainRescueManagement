package com.zagorskidev.rescuecrm.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.zagorskidev.rescuecrm.entity.OperationDetail;
import com.zagorskidev.rescuecrm.entity.Rescuer;

@Repository
@Transactional
public class RescuerDAOImpl extends AbstractDAO<Rescuer> implements RescuerDAO {

	@Override
	@SuppressWarnings("unchecked")
	public List<Rescuer> getSpecific(String state) {
		
		EntityManager entityManager = getEntityManager();
		Query query = entityManager
				.createQuery("from Rescuer where state=:itemState order by lastName", Rescuer.class)
				.setParameter("itemState", state);
		return query.getResultList();
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Rescuer> getAll() {
		
		EntityManager entityManager = getEntityManager();
		Query query = entityManager
				.createQuery("from Rescuer where state!=:notAvailable order by lastName", Rescuer.class)
				.setParameter("notAvailable", "N/A");
		return query.getResultList();
	}
	
	@Override
	public Rescuer addLazyData(Rescuer rescuer) {
		
		rescuer.getRescuerDetail();
		List<OperationDetail> operationDetails = rescuer.getOperationDetails();
		operationDetails.forEach(detail -> detail.getOperation());
		rescuer.getOperations();
		return rescuer;
	}
}
