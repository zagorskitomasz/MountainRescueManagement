package com.zagorskidev.rescuecrm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.zagorskidev.rescuecrm.dao.RescuerDAO;
import com.zagorskidev.rescuecrm.entity.Rescuer;

public class RescuerServiceImpl implements RescuerService {

	@Autowired
	private RescuerDAO rescuerDAO;
	
	@Override
	public List<Rescuer> getAllRescuers() {
		
		return rescuerDAO.getAll();
	}

	@Override
	public List<Rescuer> getAvailableRescuers() {
		
		return rescuerDAO.getSpecific("available");
	}

	@Override
	public List<Rescuer> getBusyRescuers() {
		
		return rescuerDAO.getSpecific("busy");
	}

	@Override
	public List<Rescuer> getOnCallRescuers() {
		
		return rescuerDAO.getSpecific("oncall");
	}

	@Override
	public List<Rescuer> getRetiredRescuers() {
		
		return rescuerDAO.getSpecific("retired");
	}

	@Override
	public Rescuer getRescuerById(int id) {
		
		return rescuerDAO.get(id);
	}

	@Override
	public void addRescuer(Rescuer rescuer) {
		
		rescuerDAO.persist(rescuer);
	}

	@Override
	public void updateRescuer(Rescuer rescuer) {
		
		rescuerDAO.persist(rescuer);
	}

	@Override
	public void removeRescuer(int id) {
		
		rescuerDAO.delete(id);
	}

}
