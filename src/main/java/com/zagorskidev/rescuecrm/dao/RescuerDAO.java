package com.zagorskidev.rescuecrm.dao;

import java.util.List;

import com.zagorskidev.rescuecrm.entity.Rescuer;

public interface RescuerDAO {

	public List<Rescuer> getAll();
	public Rescuer get(int id);
	public void persist(Rescuer rescuer);
	public void merge(Rescuer rescuer);
	public void delete(int id);
}
