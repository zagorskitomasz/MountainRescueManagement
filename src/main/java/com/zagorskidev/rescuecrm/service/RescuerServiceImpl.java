package com.zagorskidev.rescuecrm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zagorskidev.rescuecrm.dao.RescuerDAO;
import com.zagorskidev.rescuecrm.entity.Rescuer;
import com.zagorskidev.rescuecrm.entity.RescuerDetail;
import com.zagorskidev.rescuecrm.utils.DataUtils;

/**
 * Implementation of services related to rescuer bean.
 * @author tomek
 *
 */
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
	 * Transfers data from incoming rescuer object to object mapped from DB with same id.
	 * @param rescuer
	 * @return
	 */
	private Rescuer updateExistingRescuer(Rescuer rescuer) {

		Rescuer tempRescuer = getRescuerById(rescuer.getId());

		tempRescuer.setFirstName(rescuer.getFirstName());
		tempRescuer.setLastName(rescuer.getLastName());
		tempRescuer.getRescuerDetail().setAddress(rescuer.getRescuerDetail().getAddress());
		tempRescuer.getRescuerDetail().setPhone(rescuer.getRescuerDetail().getPhone());
		tempRescuer.getRescuerDetail().setEmail(rescuer.getRescuerDetail().getEmail());

		return tempRescuer;
	}

	/**
	 * Anonymize rescuer instead of deleting it,
	 * so rescuer will be still shown in his operations, but with data like 'N/A' 
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
	 * Translate rescuer fields containing native diacritics to strings in English alphabet
	 * @param rescuer
	 */
	private void removeDiacritics(Rescuer rescuer) {

		RescuerDetail rescuerDetail = rescuer.getRescuerDetail();
		
		rescuer.setFirstName(DataUtils.removeDiacritics(rescuer.getFirstName()));
		rescuer.setLastName(DataUtils.removeDiacritics(rescuer.getLastName()));
		rescuerDetail.setEmail(DataUtils.removeDiacritics(rescuerDetail.getEmail()));
		rescuerDetail.setAddress(DataUtils.removeDiacritics(rescuerDetail.getAddress()));
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
}
