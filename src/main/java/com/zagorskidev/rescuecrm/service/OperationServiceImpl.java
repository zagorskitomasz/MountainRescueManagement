package com.zagorskidev.rescuecrm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.zagorskidev.rescuecrm.dao.OperationDAO;
import com.zagorskidev.rescuecrm.entity.Operation;

public class OperationServiceImpl implements OperationService {

	@Autowired
	private OperationDAO operationDAO;
	
	@Override
	public List<Operation> getAllOperations() {
		
		return operationDAO.getAll();
	}

	@Override
	public List<Operation> getCurrentOperations() {
		
		return operationDAO.getSpecific("current");
	}

	@Override
	public List<Operation> getPastOperations() {
		
		return operationDAO.getSpecific("past");
	}

	@Override
	public Operation getOperationById(int id) {
		
		return operationDAO.get(id);
	}

	@Override
	public void addOperation(Operation operation) {
		
		operationDAO.persist(operation);
	}

	@Override
	public void updateOperation(Operation operation) {
		
		operationDAO.persist(operation);
	}

	@Override
	public void removeOperation(int id) {
		
		operationDAO.delete(id);
	}

}
