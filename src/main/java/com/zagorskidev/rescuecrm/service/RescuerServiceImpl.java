package com.zagorskidev.rescuecrm.service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zagorskidev.rescuecrm.dao.RescuerDAO;
import com.zagorskidev.rescuecrm.entity.Operation;
import com.zagorskidev.rescuecrm.entity.Rescuer;
import com.zagorskidev.rescuecrm.entity.RescuerDetail;
import com.zagorskidev.rescuecrm.utils.DataUtils;
import com.zagorskidev.rescuecrm.utils.RescuerWithAttachedFlag;

/**
 * Implementation of services related to rescuer bean.
 * 
 * @author tomek
 *
 */
@Service
public class RescuerServiceImpl implements RescuerService {

	@Autowired
	private RescuerDAO rescuerDAO;
	
	@Autowired
	private OperationService operationService;

	@Override
	public List<Rescuer> getAllRescuers() {

		List<Rescuer> rescuers = rescuerDAO.getAll();
		rescuers.removeIf(rescuer -> rescuer.getLastName().equals("N/A"));
		return rescuers;
	}

	@Override
	public List<Rescuer> getAvailableRescuers() {
		
		List<Rescuer> rescuers = getAllRescuers();
		rescuers.removeIf(rescuer -> !rescuer.isAvailable());
		
		return rescuers;
	}

	@Override
	public List<Rescuer> getBusyRescuers() {
		
		List<Rescuer> rescuers = getAllRescuers();
		rescuers.removeIf(rescuer -> rescuer.isAvailable());
		
		return rescuers;
	}

	@Override
	public Rescuer getRescuerById(int id) {

		return rescuerDAO.get(id);
	}

	@Override
	public void addRescuer(Rescuer rescuer) {

		rescuer.setAvailable();
		removeDiacritics(rescuer);
		rescuerDAO.persist(rescuer);
	}

	@Override
	public void updateRescuer(Rescuer rescuer) {

		Rescuer tempRescuer = updateExistingRescuer(rescuer);
		removeDiacritics(tempRescuer);

		rescuerDAO.merge(tempRescuer);
	}

	@Override
	public void removeRescuer(int id) {

		Rescuer rescuer = getRescuerById(id);

		if (rescuer.getOperations() == null || rescuer.getOperations().isEmpty())
			rescuerDAO.delete(id);
		else
			anonymize(rescuer);
	}

	/**
	 * Transfers data from incoming rescuer object to object mapped from DB with
	 * same id.
	 * 
	 * @param rescuer
	 * @return
	 */
	private Rescuer updateExistingRescuer(Rescuer rescuer) {

		Rescuer tempRescuer = getRescuerById(rescuer.getId());

		tempRescuer.setFirstName(rescuer.getFirstName());
		tempRescuer.setLastName(rescuer.getLastName());
		tempRescuer.setState(rescuer.getState());
		tempRescuer.getRescuerDetail().setAddress(rescuer.getRescuerDetail().getAddress());
		tempRescuer.getRescuerDetail().setPhone(rescuer.getRescuerDetail().getPhone());
		tempRescuer.getRescuerDetail().setEmail(rescuer.getRescuerDetail().getEmail());

		return tempRescuer;
	}

	/**
	 * Anonymize rescuer instead of deleting it, so rescuer will be still shown in
	 * his operations, but with data like 'N/A'
	 * 
	 * @param rescuer
	 */
	private void anonymize(Rescuer rescuer) {

		rescuer.setFirstName("N/A");
		rescuer.setLastName("N/A");

		RescuerDetail rescuerDetail = rescuer.getRescuerDetail();
		rescuerDetail.setAddress("N/A");
		rescuerDetail.setEmail(null);
		rescuerDetail.setPhone(null);

		updateRescuer(rescuer);
	}

	/**
	 * Translate rescuer fields containing native diacritics to strings in English
	 * alphabet
	 * 
	 * @param rescuer
	 */
	private void removeDiacritics(Rescuer rescuer) {

		RescuerDetail rescuerDetail = rescuer.getRescuerDetail();

		rescuer.setFirstName(DataUtils.removeDiacritics(rescuer.getFirstName()));
		rescuer.setLastName(DataUtils.removeDiacritics(rescuer.getLastName()));
		
		if (rescuerDetail != null) {
			rescuerDetail.setEmail(DataUtils.removeDiacritics(rescuerDetail.getEmail()));
			rescuerDetail.setAddress(DataUtils.removeDiacritics(rescuerDetail.getAddress()));
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Rescuer createEmptyRescuer() {

		Rescuer rescuer = new Rescuer();
		RescuerDetail rescuerDetail = new RescuerDetail();
		rescuer.setRescuerDetail(rescuerDetail);

		return rescuer;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void makeRescuersBusy(List<Rescuer> rescuers) {
		
		invokeForAllRescuers(rescuers, Rescuer::setBusy);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void makeRescuersAvailable(List<Rescuer> rescuers) {
		
		invokeForAllRescuers(rescuers, Rescuer::setAvailable);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Rescuer> prepareCandidates(Operation operation) {
		
		List<Rescuer> candidates = getAvailableRescuers();
		List<Rescuer> involvedRescuers = prepareInvolvedRescuers(operation);
		
		candidates.addAll(involvedRescuers);
		
		sortCandidates(candidates);
		
		return candidates;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<RescuerWithAttachedFlag> prepareCandidatesMap(Operation operation) {
		
		List<Rescuer> candidates = prepareCandidates(operation);
		List<RescuerWithAttachedFlag> candidatesMap = transferFromListToPseudoMap(operation, candidates);
		
		return candidatesMap;
	}

	private List<RescuerWithAttachedFlag> transferFromListToPseudoMap(Operation operation, List<Rescuer> candidates) {
		
		List<RescuerWithAttachedFlag> candidatesMap = new ArrayList<>();
		
		candidates.forEach(rescuer -> candidatesMap.add(
				new RescuerWithAttachedFlag(rescuer, 
						operation.getRescuers() != null && 
						operation.getRescuers().contains(rescuer) ? 
								true : false)));
		return candidatesMap;
	}

	private void sortCandidates(List<Rescuer> candidates) {
		
		candidates.sort((a, b) -> {
			if(a.getLastName().equals(b.getLastName()))
				return a.getFirstName().compareTo(b.getFirstName());
			else
				return a.getLastName().compareTo(b.getLastName());
		});
	}

	/**
	 * Creating list containing full Rescuer beans involved in operation.
	 * @param operation
	 * @return
	 */
	private List<Rescuer> prepareInvolvedRescuers(Operation operation) {
		
		List<Rescuer> tempRescuers = prepareIdOnlyInvolvedRescuers(operation);

		List<Rescuer> involvedRescuers = new ArrayList<>();
		tempRescuers.forEach(rescuer -> involvedRescuers.add(getRescuerById(rescuer.getId())));
		
		operation.setRescuers(involvedRescuers);
		
		return involvedRescuers;
	}

	private List<Rescuer> prepareIdOnlyInvolvedRescuers(Operation operation) {
		
		List<Rescuer> tempRescuers = new ArrayList<>();
		
		addFromDbOperation(operation, tempRescuers);
		
		return tempRescuers;
	}

	private void addFromStraightOperation(Operation operation, List<Rescuer> tempRescuers) {
		
		tempRescuers.addAll(operation.getRescuers());
	}

	private void addFromDbOperation(Operation operation, List<Rescuer> tempRescuers) {
		
		Operation dbOperation = operationService.getOperationById(operation.getId());
		if(dbOperation != null && dbOperation.getRescuers() != null)
			addFromStraightOperation(dbOperation, tempRescuers);
	}
	
	/**
	 * Call method on every rescuer. Get from DB -> consume -> save in DB.
	 * @param rescuers
	 * @param action
	 */
	private void invokeForAllRescuers(List<Rescuer> rescuers, Consumer<? super Rescuer> action) {
		
		List<Rescuer> realRescuers = new LinkedList<>();
		
		rescuers.forEach(rescuer -> realRescuers.add(getRescuerById(rescuer.getId())));
		realRescuers.forEach(action);
		realRescuers.forEach(rescuer -> rescuerDAO.merge(rescuer));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void updateCandidatesData(List<RescuerWithAttachedFlag> candidatesMap) {
		
		candidatesMap.forEach(rescuerWithFlag -> 
			rescuerWithFlag.setRescuer(getRescuerById(rescuerWithFlag.getRescuer().getId())));
	}
}
