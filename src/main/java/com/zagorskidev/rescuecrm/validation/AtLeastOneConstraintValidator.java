package com.zagorskidev.rescuecrm.validation;

import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.zagorskidev.rescuecrm.entity.Rescuer;

public class AtLeastOneConstraintValidator implements ConstraintValidator<AtLeastOne, List<Rescuer>> {

	@Override
	public void initialize(AtLeastOne atLeastOne) {
	}

	@Override
	public boolean isValid(List<Rescuer> rescuers, ConstraintValidatorContext constraintValidatorContext) {

		for(Rescuer rescuer : rescuers) {
			if(rescuer.getId()>0)
				return true;
		}
		return false;
	}
}
