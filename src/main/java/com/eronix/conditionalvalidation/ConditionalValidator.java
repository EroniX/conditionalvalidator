package com.eronix.conditionalvalidation;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.beanutils.BeanUtils;

public class ConditionalValidator implements ConstraintValidator<Conditional, Object> {

	private ConditionalType conditionalType;
	private String selected;
	private String[] fields;
	private String message;
	private String[] values;

	@Override
	public void initialize(Conditional conditional) {
		conditionalType = conditional.conditionalType();
		selected = conditional.selected();
		fields = conditional.fields();
		message = conditional.message();
		values = conditional.values();
	}

	@Override
	public boolean isValid(Object bean, ConstraintValidatorContext context) {
		boolean valid = true;
		try {
			Object actualValue = BeanUtils.getProperty(bean, selected);
			if (Arrays.asList(values).contains(actualValue)) {
				for (String field : fields) {
					Object fieldValue = BeanUtils.getProperty(bean, field);
					if (!conditionalType.isValid(fieldValue)) {
						context.disableDefaultConstraintViolation();
						context.buildConstraintViolationWithTemplate(message)
							.addPropertyNode(field)
							.addConstraintViolation();
						valid = false;
					}
				}
			}
		} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
			e.printStackTrace();
			valid = false;
		}
		return valid;
	}
}