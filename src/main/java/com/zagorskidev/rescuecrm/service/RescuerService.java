package com.zagorskidev.rescuecrm.service;

import java.util.List;

import com.zagorskidev.rescuecrm.entity.Rescuer;

public interface RescuerService {

	public List<Rescuer> getAllRescuers();
	public List<Rescuer> getAvailableRescuers();
	public List<Rescuer> getBusyRescuers();
	public List<Rescuer> getOnCallRescuers();
	public List<Rescuer> getRetiredRescuers();
	
	public Rescuer getRescuerById(int id);
	
	public void addRescuer(Rescuer rescuer);
	public void updateRescuer(Rescuer rescuer);
	public void removeRescuer(int id);
}
