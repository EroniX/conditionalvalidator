package com.eronix.conditionalvalidationtest;

import com.eronix.conditionalvalidation.Conditional;
import com.eronix.conditionalvalidation.ConditionalType;

@Conditional(
		conditionalFieldType = ConditionalType.EQUALS,
		conditionalField = "doPorn",
		conditionalFieldParam = "true", 
		
		targetFieldType = ConditionalType.MIN,
		targetField = "age",
		targetFieldParam = "18"
	)
	public class User {	
		private Integer age;
		private Boolean doPorn;
		
		public Integer getAge() {
			return age;
		}
		
		public void setAge(Integer age) {
			this.age = age;
		}
		
		public Boolean getDoPorn() {
			return doPorn;
		}
		
		public Boolean isDoPorn() {
			return doPorn;
		}
		
		public void setDoPorn(Boolean doPorn) {
			this.doPorn = doPorn;
		}
	}
