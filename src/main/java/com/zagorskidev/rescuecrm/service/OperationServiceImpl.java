package com.zagorskidev.rescuecrm.service;

import java.util.Arrays;
import java.util.LinkedList;
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
	
	@Autowired
	private RescuerService rescuerService;

	@Override
	public List<Operation> getAllOperations() {

		return operationDAO.getAll();
	}

	@Override
	public List<Operation> getRunningOperations() {

		List<Operation> operations = operationDAO.getAll();
		operations.removeIf(operation -> !operation.isRunning());
		
		return operations;
	}

	@Override
	public List<Operation> getFinishedOperations() {

		List<Operation> operations = operationDAO.getAll();
		operations.removeIf(operation -> operation.isRunning());
		
		return operations;
	}

	@Override
	public Operation getOperationById(int id) {

		return operationDAO.get(id);
	}

	@Override
	public void addOperation(Operation operation) {

		removeDiacritics(operation);
		
		operation.setRunning();
		rescuerService.makeRescuersBusy(operation.getRescuers());
		
		operationDAO.persist(operation);
		emailService.notifyOperationCreated(operation);
	}

	@Override
	public void updateOperation(Operation operation) {

		removeDiacritics(operation);
		updateInvolvedRescuers(operation);
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
	
	/**
	 * Update list of involved rescuers if some of them changed during update operation action.
	 * @param operation
	 */
	private void updateInvolvedRescuers(Operation operation) {
		
		List<Rescuer> oldList = getOperationById(operation.getId()).getRescuers();
		List<Rescuer> newList = prepareListOfNewRescuers(operation);
		
		releaseUnusedRescuers(oldList, newList);
		involveNewRescuers(oldList, newList);
	}

	private List<Rescuer> prepareListOfNewRescuers(Operation operation) {
		
		List<Rescuer> newList = new LinkedList<>();
		for(Rescuer rescuer : operation.getRescuers())
			newList.add(rescuerService.getRescuerById(rescuer.getId()));
		
		return newList;
	}

	/**
	 * Release rescuers if they are no longer involved in operation.
	 * @param oldList
	 * @param newList
	 */
	private void releaseUnusedRescuers(List<Rescuer> oldList, List<Rescuer> newList) {
		
		for(Rescuer rescuer : oldList)
			if(!newList.contains(rescuer))
				rescuerService.makeRescuersAvailable(Arrays.asList(rescuer));
	}

	/**
	 * Makes new rescuers busy if they are added to existing operation.
	 * @param oldList
	 * @param newList
	 */
	private void involveNewRescuers(List<Rescuer> oldList, List<Rescuer> newList) {
		
		for(Rescuer rescuer : newList)
			if(!oldList.contains(rescuer))
				rescuerService.makeRescuersBusy(Arrays.asList(rescuer));
	}

	@Override
	public void removeOperation(int id) {

		rescuerService.makeRescuersAvailable(getOperationById(id).getRescuers());
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

	@Override
	public void finishOperation(int id) {
		
		Operation operation = getOperationById(id);
		rescuerService.makeRescuersAvailable(operation.getRescuers());
		operation.setFinished();
		updateOperation(operation);
	}
}
