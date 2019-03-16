package com.eronix.ConditionalValidationTest;

import com.eronix.conditionalvalidation.Conditional;
import com.eronix.conditionalvalidation.ConditionalType;

import lombok.Getter;
import lombok.Setter;

@Conditional(
		selected = "required", 
		values = { "true" }, 
		fields = { "email" }, 
		conditionalType = ConditionalType.NOT_BLANK)
public class NotBlankUser {

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
