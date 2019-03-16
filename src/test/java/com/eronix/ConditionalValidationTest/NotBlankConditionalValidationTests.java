package com.eronix.ConditionalValidationTest;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Assert;
import org.junit.Test;

public class NotBlankConditionalValidationTests {

	@Test
	public void notBlankValid1Test() {
		NotBlankUser user = new NotBlankUser();
		user.setRequired(true);
		user.setEmail("vaczi8");
		
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		Set<ConstraintViolation<NotBlankUser>> constraintViolations = validator.validate(user);
		
		Assert.assertTrue(constraintViolations.isEmpty());
	}
	
	@Test
	public void notBlankValid2Test() {
		NotBlankUser user = new NotBlankUser();
		user.setRequired(false);
		
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		Set<ConstraintViolation<NotBlankUser>> constraintViolations = validator.validate(user);
		
		Assert.assertTrue(constraintViolations.isEmpty());
	}

	@Test
	public void notBlank1InvalidTest() {
		NotBlankUser user = new NotBlankUser();
		user.setRequired(true);
		
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		Set<ConstraintViolation<NotBlankUser>> constraintViolations = validator.validate(user);
		
		Assert.assertFalse(constraintViolations.isEmpty());
	}
	
	@Test
	public void notBlank2InvalidTest() {
		NotBlankUser user = new NotBlankUser();
		user.setRequired(true);
		user.setEmail("");
		
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		Set<ConstraintViolation<NotBlankUser>> constraintViolations = validator.validate(user);
		
		Assert.assertFalse(constraintViolations.isEmpty());
	}
}
