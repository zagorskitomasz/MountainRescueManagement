package com.zagorskidev.rescuecrm.entity;

import org.springframework.stereotype.Component;

@Component
public class States {
	
	private String[] rescuerStates;
	private String[] operationStates;
	
	public States() {
		
		rescuerStates = new String[] {"available", "busy", "oncall", "retired"};
		operationStates = new String[] {"current", "past"};
	}

	public String[] getRescuerStates() {
		return rescuerStates;
	}

	public void setRescuerStates(String[] rescuerStates) {
		this.rescuerStates = rescuerStates;
	}

	public String[] getOperationStates() {
		return operationStates;
	}

	public void setOperationStates(String[] operationStates) {
		this.operationStates = operationStates;
	}
}
