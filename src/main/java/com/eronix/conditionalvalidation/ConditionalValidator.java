package com.eronix.conditionalvalidation;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.beanutils.BeanUtils;

public class ConditionalValidator implements ConstraintValidator<Conditional, Object> {

	private ConditionalType conditionalFieldType;
	private String conditionalFieldParam;
	private String conditionalField;
	
	private ConditionalType targetFieldType;
	private String targetFieldParam;
	private String targetField;
	
	private String message;

	@Override
	public void initialize(Conditional conditional) {
		conditionalFieldType = conditional.conditionalFieldType();
		conditionalFieldParam = conditional.conditionalFieldParam();
		conditionalField = conditional.conditionalField();
		
		targetFieldType = conditional.targetFieldType();
		targetFieldParam = conditional.targetFieldParam();
		targetField = conditional.targetField();
		
		message = conditional.message();
	}

	@Override
	public boolean isValid(Object bean, ConstraintValidatorContext context) {
		boolean valid = true;
		try {
			Object conditionalFieldValue = BeanUtils.getProperty(bean, conditionalField);
			if(conditionalFieldType.check(conditionalFieldValue, conditionalFieldParam)) {
				Object targetFieldValue = BeanUtils.getProperty(bean, targetField);
				if(!targetFieldType.check(targetFieldValue, targetFieldParam)) {
					context.disableDefaultConstraintViolation();
					context.buildConstraintViolationWithTemplate(message)
						.addPropertyNode(targetField)
						.addConstraintViolation();
					valid = false;
				}
			}
		} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
			e.printStackTrace();
			valid = false;
		}
		return valid;
	}
}