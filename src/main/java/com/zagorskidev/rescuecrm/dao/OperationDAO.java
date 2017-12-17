package com.zagorskidev.rescuecrm.dao;

import java.util.List;

import com.zagorskidev.rescuecrm.entity.Operation;

/**
 * Grants database persistence operations for rescue operations beans.
 * @author tomek
 *
 */
public interface OperationDAO {

	public List<Operation> getAll();
	public Operation get(int id);
	public void persist(Operation operation);
	public void merge(Operation operation);
	public void delete(int id);
}
