package com.zagorskidev.rescuecrm.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zagorskidev.rescuecrm.dao.OperationDAO;
import com.zagorskidev.rescuecrm.entity.Operation;
import com.zagorskidev.rescuecrm.entity.OperationDetail;
import com.zagorskidev.rescuecrm.entity.Rescuer;

@Service
public class OperationServiceImpl implements OperationService {

	@Autowired
	private OperationDAO operationDAO;

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

		removeDiactrics(operation);
		operationDAO.persist(operation);
	}

	@Override
	public void updateOperation(Operation operation) {

		removeDiactrics(operation);
		operationDAO.merge(operation);
	}

	private void removeDiactrics(Operation operation) {

		if (operation.getDestination() != null)
			operation.setDestination(
					StringUtils.stripAccents(operation.getDestination()).replace('ł', 'l').replace('Ł', 'L'));
		if (operation.getOperationDetail().getDescription() != null)
			operation.getOperationDetail().setDescription(StringUtils
					.stripAccents(operation.getOperationDetail().getDescription()).replace('ł', 'l').replace('Ł', 'L'));
	}

	@Override
	public void removeOperation(int id) {

		operationDAO.delete(id);
	}

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
