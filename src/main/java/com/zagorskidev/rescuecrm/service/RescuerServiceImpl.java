package com.zagorskidev.rescuecrm.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zagorskidev.rescuecrm.dao.RescuerDAO;
import com.zagorskidev.rescuecrm.entity.Rescuer;
import com.zagorskidev.rescuecrm.entity.RescuerDetail;

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

		removeDiactrics(rescuer);
		rescuerDAO.persist(rescuer);
	}

	@Override
	public void updateRescuer(Rescuer rescuer) {

		Rescuer tempRescuer = updateExistingRescuer(rescuer);
		removeDiactrics(tempRescuer);

		rescuerDAO.merge(tempRescuer);
	}

	@Override
	public void removeRescuer(int id) {

		Rescuer rescuer = getRescuerById(id);

		if (rescuer.getOperations() == null || rescuer.getOperations().isEmpty())
			rescuerDAO.delete(id);
		else
			anonimize(rescuer);
	}

	private Rescuer updateExistingRescuer(Rescuer rescuer) {

		Rescuer tempRescuer = getRescuerById(rescuer.getId());

		tempRescuer.setFirstName(rescuer.getFirstName());
		tempRescuer.setLastName(rescuer.getLastName());
		tempRescuer.getRescuerDetail().setAddress(rescuer.getRescuerDetail().getAddress());
		tempRescuer.getRescuerDetail().setPhone(rescuer.getRescuerDetail().getPhone());
		tempRescuer.getRescuerDetail().setEmail(rescuer.getRescuerDetail().getEmail());

		return tempRescuer;
	}

	private void anonimize(Rescuer rescuer) {

		rescuer.setFirstName("N/A");
		rescuer.setLastName("N/A");

		RescuerDetail rescuerDetail = rescuer.getRescuerDetail();
		rescuerDetail.setAddress("N/A");
		rescuerDetail.setEmail(null);
		rescuerDetail.setPhone(null);

		updateRescuer(rescuer);
	}

	private void removeDiactrics(Rescuer rescuer) {

		if (rescuer.getFirstName() != null)
			rescuer.setFirstName(StringUtils.stripAccents(rescuer.getFirstName()).replace('ł', 'l').replace('Ł', 'L'));
		if (rescuer.getLastName() != null)
			rescuer.setLastName(StringUtils.stripAccents(rescuer.getLastName()).replace('ł', 'l').replace('Ł', 'L'));
		if (rescuer.getRescuerDetail().getEmail() != null)
			rescuer.getRescuerDetail().setEmail(StringUtils.stripAccents(rescuer.getRescuerDetail().getEmail())
					.replace('ł', 'l').replace('Ł', 'L'));
		if (rescuer.getRescuerDetail().getAddress() != null)
			rescuer.getRescuerDetail().setAddress(StringUtils.stripAccents(rescuer.getRescuerDetail().getAddress())
					.replace('ł', 'l').replace('Ł', 'L'));
	}

	@Override
	public Rescuer createEmptyRescuer() {

		Rescuer rescuer = new Rescuer();
		RescuerDetail rescuerDetail = new RescuerDetail();
		rescuer.setRescuerDetail(rescuerDetail);

		return rescuer;
	}
}
