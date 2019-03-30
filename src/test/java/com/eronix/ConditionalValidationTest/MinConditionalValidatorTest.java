package com.eronix.conditionalvalidationtest;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Assert;
import org.junit.Test;

public class MinConditionalValidatorTest {

	@Test
	public void minValid1Test() {
		User user = new User();
		user.setAge(32);
		user.setDoPorn(true);
		
		Assert.assertTrue(validate(user));
	}
	
	@Test
	public void minValid2Test() {
		User user = new User();
		user.setAge(15);
		user.setDoPorn(false);
		
		Assert.assertTrue(validate(user));
	}
	
	@Test
	public void minInvalid1Test() {
		User user = new User();
		user.setAge(16);
		user.setDoPorn(true);
		
		Assert.assertFalse(validate(user));
	}
	
	private static boolean validate(User user) {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		Set<ConstraintViolation<User>> constraintViolations = validator.validate(user);
		return constraintViolations.isEmpty();
	}
}
