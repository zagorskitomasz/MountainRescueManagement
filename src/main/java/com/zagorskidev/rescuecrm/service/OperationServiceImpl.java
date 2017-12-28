package com.zagorskidev.rescuecrm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zagorskidev.rescuecrm.dao.OperationDAO;
import com.zagorskidev.rescuecrm.entity.Operation;
import com.zagorskidev.rescuecrm.entity.OperationDetail;
import com.zagorskidev.rescuecrm.entity.Rescuer;
import com.zagorskidev.rescuecrm.utils.DataUtils;

/**
 * Implementation of services related to operation bean.
 * @author tomek
 *
 */
@Service
public class OperationServiceImpl implements OperationService {

	@Autowired
	private OperationDAO operationDAO;
	
	@Autowired
	private EmailService emailService;

	@Override
	public List<Operation> getAllOperations() {

		return operationDAO.getAll();
	}

	@Override
	public Operation getOperationById(int id) {

		return operationDAO.get(id);
	}

	@Override
	public void addOperation(Operation operation) {

		removeDiacritics(operation);
		operationDAO.persist(operation);
		emailService.notifyOperationCreated(operation);
	}

	@Override
	public void updateOperation(Operation operation) {

		removeDiacritics(operation);
		operationDAO.merge(operation);
		emailService.notifyOperationUpdated(operation);
	}

	/**
	 * Translate operation fields containing native diacritics to strings in English alphabet
	 * @param operation
	 */
	private void removeDiacritics(Operation operation) {

		OperationDetail operationDetail = operation.getOperationDetail();
		
		operation.setDestination(DataUtils.removeDiacritics(operation.getDestination()));
		operationDetail.setDescription(DataUtils.removeDiacritics(operationDetail.getDescription()));
	}

	@Override
	public void removeOperation(int id) {

		operationDAO.delete(id);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Operation createEmptyOperation() {

		Operation operation = new Operation();
		OperationDetail operationDetail = new OperationDetail();
		operation.setOperationDetail(operationDetail);

		createEmptyRescuersList(operation);

		return operation;
	}

	private void createEmptyRescuersList(Operation operation) {
		for (int i = 0; i < 3; i++)
			operation.addRescuer(new Rescuer());
	}
}
