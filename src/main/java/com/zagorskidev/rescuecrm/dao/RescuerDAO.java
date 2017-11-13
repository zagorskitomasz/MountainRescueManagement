package com.zagorskidev.rescuecrm.dao;

import java.util.List;

import com.zagorskidev.rescuecrm.entity.Rescuer;

public interface RescuerDAO {

	public List<Rescuer> getAll();
	public List<Rescuer> getSpecific(String state);
	public Rescuer get(int id);
	public void persist(Rescuer rescuer);
	public void delete(int id);
}
