package com.zagorskidev.rescuecrm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zagorskidev.rescuecrm.dao.RescuerDAO;
import com.zagorskidev.rescuecrm.entity.Rescuer;

@Service
public class RescuerServiceImpl implements RescuerService {

	@Autowired
	private RescuerDAO rescuerDAO;
	
	@Override
	public List<Rescuer> getAllRescuers() {
		
		List<Rescuer> rescuers = rescuerDAO.getAll();
		rescuers.removeIf(rescuer -> rescuer.getLastName().equals("N/A"));
		return rescuers;
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
		
		rescuerDAO.merge(rescuer);
	}

	@Override
	public void removeRescuer(int id) {
		
		rescuerDAO.delete(id);
	}

}
