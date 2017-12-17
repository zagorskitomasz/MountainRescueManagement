package com.zagorskidev.rescuecrm.validation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * Validation grants that list will contain at least one rescuer.
 * @author tomek
 *
 */
@Constraint(validatedBy=AtLeastOneConstraintValidator.class)
@Retention(RUNTIME)
@Target({ FIELD, METHOD })
public @interface AtLeastOne {

	public String message() default "attach at least one rescuer";
	public Class<?>[] groups() default {};
	public Class<? extends Payload>[] payload() default {};
}
