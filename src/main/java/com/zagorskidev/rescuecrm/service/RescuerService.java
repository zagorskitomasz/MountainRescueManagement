package com.zagorskidev.rescuecrm.service;

import java.util.List;

import com.zagorskidev.rescuecrm.entity.Rescuer;

/**
 * Grants services related to rescue operation.
 * @author tomek
 *
 */
public interface RescuerService {

	public List<Rescuer> getAllRescuers();
	public Rescuer getRescuerById(int id);
	public void addRescuer(Rescuer rescuer);
	public void updateRescuer(Rescuer rescuer);
	public void removeRescuer(int id);
	
	/**
	 * Empty rescuer is used for taking data from JSP view.
	 * @return
	 */
	public Rescuer createEmptyRescuer();
}
