package com.zagorskidev.rescuecrm.service;

import java.util.List;

import com.zagorskidev.rescuecrm.entity.Operation;
import com.zagorskidev.rescuecrm.entity.Rescuer;
import com.zagorskidev.rescuecrm.utils.RescuerWithAttachedFlag;

/**
 * Grants services related to rescue operation.
 * @author tomek
 *
 */
public interface RescuerService {

	public List<Rescuer> getAllRescuers();
	public List<Rescuer> getAvailableRescuers();
	public List<Rescuer> getBusyRescuers();
	
	public Rescuer getRescuerById(int id);
	public void addRescuer(Rescuer rescuer);
	public void updateRescuer(Rescuer rescuer);
	public void removeRescuer(int id);
	
	/**
	 * Empty rescuer is used for taking data from JSP view.
	 * @return
	 */
	public Rescuer createEmptyRescuer();
	
	/**
	 * Changes state of rescuer and persist it into DB.
	 * @param rescuers
	 */
	public void makeRescuersBusy(List<Rescuer> rescuers);
	
	/**
	 * Changes state of rescuer and persist it into DB.
	 * @param rescuers
	 */
	public void makeRescuersAvailable(List<Rescuer> rescuers);
	
	/**
	 * Prepares list of rescuers available for operation.
	 * @param operation
	 * @return
	 */
	public List<Rescuer> prepareCandidates(Operation operation);
	
	/**
	 * Creates pseudo map with rescuers paired with flags marking their involvement in given operation.
	 * @param operation
	 * @return
	 */
	public List<RescuerWithAttachedFlag> prepareCandidatesMap(Operation operation);
	
	/**
	 * Complete candidates data from DB.
	 * @param candidatesMap
	 */
	public void updateCandidatesData(List<RescuerWithAttachedFlag> candidatesMap);
}
