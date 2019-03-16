package com.eronix.conditionalvalidation;

import java.util.Objects;

public enum ConditionalType {
	NOT_NULL(Objects::nonNull),
	NOT_BLANK(fieldValue -> NOT_NULL.isValid(fieldValue) && !"".equals(fieldValue.toString().trim()));

	private final ValidateFunction validateFunction;

	ConditionalType(ValidateFunction validateFunction) {
		this.validateFunction = validateFunction;
	}

	@FunctionalInterface
	interface ValidateFunction {
		boolean isValid(Object fieldValue);
	}

	public boolean isValid(Object fieldValue) {
		return validateFunction.isValid(fieldValue);
	}
}