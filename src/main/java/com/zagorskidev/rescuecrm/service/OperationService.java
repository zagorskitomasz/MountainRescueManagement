package com.zagorskidev.rescuecrm.service;

import java.util.List;

import com.zagorskidev.rescuecrm.entity.Operation;

/**
 * Grants services related to rescue operation.
 * @author tomek
 *
 */
public interface OperationService {

	public List<Operation> getAllOperations();
	public List<Operation> getRunningOperations();
	public List<Operation> getFinishedOperations();
	
	public Operation getOperationById(int id);
	public void addOperation(Operation operation);
	public void updateOperation(Operation operation);
	public void removeOperation(int id);
	public void finishOperation(int id);
	
	/**
	 * Empty operation contains list of empty rescuers and is used for taking data from JSP view.
	 * @return
	 */
	public Operation createEmptyOperation();
}
