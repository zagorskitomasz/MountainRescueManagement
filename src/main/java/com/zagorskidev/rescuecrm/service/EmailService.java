package com.zagorskidev.rescuecrm.service;

import com.zagorskidev.rescuecrm.entity.Operation;

/**
 * Grants services related to email sending.
 * @author tomek
 *
 */
public interface EmailService {

	public void notifyOperationCreated(Operation operation);
	public void notifyOperationUpdated(Operation operation);
}
