package com.zagorskidev.rescuecrm.validation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * Validation grants that list won't contain same rescuer two or more times.
 * @author tomek
 *
 */
@Constraint(validatedBy=UniqueListConstraintValidator.class)
@Retention(RUNTIME)
@Target({ FIELD, METHOD })
public @interface UniqueList {

	public String message() default "can't attach one rescuer twice";
	public Class<?>[] groups() default {};
	public Class<? extends Payload>[] payload() default {};
}
