package com.eronix.conditionalvalidation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Repeatable(Conditionals.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = { ConditionalValidator.class })
public @interface Conditional {

	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};

	ConditionalType conditionalFieldType();
	String conditionalFieldParam();
	String conditionalField();

	ConditionalType targetFieldType();
	String targetFieldParam();
	String targetField();

	String message() default "The field value is not appropriate.";
}
