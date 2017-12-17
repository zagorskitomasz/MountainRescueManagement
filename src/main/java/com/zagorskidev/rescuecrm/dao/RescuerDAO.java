package com.zagorskidev.rescuecrm.dao;

import java.util.List;

import com.zagorskidev.rescuecrm.entity.Rescuer;

/**
 * Grants database persistence operations for rescuer beans.
 * @author tomek
 *
 */
public interface RescuerDAO {

	/**
	 * Returns list sorted by last names.
	 * @return
	 */
	public List<Rescuer> getAll();
	public Rescuer get(int id);
	public void persist(Rescuer rescuer);
	public void merge(Rescuer rescuer);
	public void delete(int id);
}
