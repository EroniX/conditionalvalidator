package com.eronix.ConditionalValidationTest;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Assert;
import org.junit.Test;

public class NotNullConditionalValidationTests {

	@Test
	public void notNullValid1Test() {
		NotNullUser user = new NotNullUser();
		user.setRequired(true);
		user.setUsername("vaczi8");
		
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		Set<ConstraintViolation<NotNullUser>> constraintViolations = validator.validate(user);
		
		Assert.assertTrue(constraintViolations.isEmpty());
	}
	
	@Test
	public void notNullValid2Test() {
		NotNullUser user = new NotNullUser();
		user.setRequired(false);
		
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		Set<ConstraintViolation<NotNullUser>> constraintViolations = validator.validate(user);
		
		Assert.assertTrue(constraintViolations.isEmpty());
	}

	@Test
	public void notNullInvalidTest() {
		NotNullUser user = new NotNullUser();
		user.setRequired(true);
		
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		Set<ConstraintViolation<NotNullUser>> constraintViolations = validator.validate(user);
		
		Assert.assertFalse(constraintViolations.isEmpty());
	}
}
