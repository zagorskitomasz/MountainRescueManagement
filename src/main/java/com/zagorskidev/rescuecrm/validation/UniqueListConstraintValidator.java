package com.zagorskidev.rescuecrm.validation;

import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.zagorskidev.rescuecrm.entity.Rescuer;

public class UniqueListConstraintValidator implements ConstraintValidator<UniqueList, List<Rescuer>> {

	@Override
	public void initialize(UniqueList uniqueList) {
	}

	@Override
	public boolean isValid(List<Rescuer> rescuers, ConstraintValidatorContext constraintValidatorContext) {

		for(int i=0; i<rescuers.size(); i++) {
			for(int j=0; j<rescuers.size(); j++) {
				
				if(i!=j && rescuers.get(i).getId()==rescuers.get(j).getId() && rescuers.get(i).getId()!=0)
					return false;
			}
		}
		return true;
	}

}
