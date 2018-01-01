package com.zagorskidev.rescuecrm.utils;

import java.util.List;

import com.zagorskidev.rescuecrm.entity.Operation;
import com.zagorskidev.rescuecrm.validation.AtLeastOne;

/**
 * Bean containing rescue operation and pseudo map of candidates to being involved in operation.
 * @author tomek
 *
 */
public class OperationWithMap {

	private Operation operation;
	
	@AtLeastOne
	private List<RescuerWithAttachedFlag> candidatesMap;
	
	public OperationWithMap() {}
	
	public OperationWithMap(Operation operation, List<RescuerWithAttachedFlag> candidatesMap) {
		
		super();
		this.operation = operation;
		this.candidatesMap = candidatesMap;
	}

	public Operation getOperation() {
		return operation;
	}

	public void setOperation(Operation operation) {
		this.operation = operation;
	}

	public List<RescuerWithAttachedFlag> getCandidatesMap() {
		return candidatesMap;
	}
	
	public void setCandidatesMap(List<RescuerWithAttachedFlag> candidatesMap) {
		this.candidatesMap = candidatesMap;
	}
}
