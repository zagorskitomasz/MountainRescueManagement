package com.zagorskidev.rescuecrm.validation;

import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.zagorskidev.rescuecrm.utils.RescuerWithAttachedFlag;

/**
 * Implements validation granting that list will contain at least one rescuer.
 * @author tomek
 *
 */
public class AtLeastOneConstraintValidator implements ConstraintValidator<AtLeastOne, List<RescuerWithAttachedFlag>> {

	@Override
	public void initialize(AtLeastOne atLeastOne) {
	}

	@Override
	public boolean isValid(List<RescuerWithAttachedFlag> rescuers, ConstraintValidatorContext constraintValidatorContext) {

		for(RescuerWithAttachedFlag rescuer : rescuers) {
			if(rescuer.isAttached())
				return true;
		}
		return false;
	}
}