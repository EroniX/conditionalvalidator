package com.eronix.ConditionalValidationTest;

import com.eronix.conditionalvalidation.Conditional;
import com.eronix.conditionalvalidation.ConditionalType;

import lombok.Getter;
import lombok.Setter;

@Conditional(
		selected = "required", 
		values = { "true" }, 
		fields = { "username" }, 
		conditionalType = ConditionalType.NOT_NULL)
public class NotNullUser {

	@Getter
	@Setter
	private String username;

	@Getter
	@Setter
	private String email;

	@Getter
	@Setter
	private Boolean required;
}
