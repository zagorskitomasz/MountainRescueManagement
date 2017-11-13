package com.zagorskidev.rescuecrm.service;

import java.util.List;

import com.zagorskidev.rescuecrm.entity.Operation;

public interface OperationService {

	public List<Operation> getAllOperations();
	public List<Operation> getCurrentOperations();
	public List<Operation> getPastOperations();
	
	public Operation getOperationById(int id);
	
	public void addOperation(Operation operation);
	public void updateOperation(Operation operation);
	public void removeOperation(int id);
}
