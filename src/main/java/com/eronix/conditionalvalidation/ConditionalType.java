package com.eronix.conditionalvalidation;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

public enum ConditionalType {

	/**
	 * Checking for null equality, for Objects
	 */
	NULL {
		@Override
		public boolean check(Object fieldValue, Object param) {
			return fieldValue == null;
		}
	},

	/**
	 * Checking for inverse null equality, for Objects
	 */
	NOT_NULL {
		@Override
		public boolean check(Object fieldValue, Object param) {
			return !NULL.check(fieldValue, null);
		}
	},

	/**
	 * Checking of the value is blank
	 */
	BLANK {
		@Override
		public boolean check(Object fieldValue, Object param) {
			if (NULL.check(fieldValue, null)) {
				return true;
			}
			return "".equals(fieldValue.toString().trim());
		}
	},

	/**
	 * Checking if the value is not blank
	 */
	NOT_BLANK {
		@Override
		public boolean check(Object fieldValue, Object param) {
			return !BLANK.check(fieldValue, null);
		}
	},

	/**
	 * Checking if the value is equals with the param
	 */
	EQUALS {
		@Override
		public boolean check(Object fieldValue, Object param) {
			if (NULL.check(fieldValue, null)) {
				return false;
			}
			if (NULL.check(param, null)) {
				throw new IllegalArgumentException("Param should be defined");
			}
			return fieldValue.equals(param);
		}
	},

	/**
	 * Checking if the value is not equals with the param
	 */
	NOT_EQUALS {
		@Override
		public boolean check(Object fieldValue, Object param) {
			return !EQUALS.check(fieldValue, param);
		}
	},

	/**
	 * Checking if the value is in the past
	 */
	PAST {
		@Override
		public boolean check(Object fieldValue, Object param) {
			if (NULL.check(fieldValue, null)) {
				return false;
			}
			if (fieldValue instanceof Date) {
				Date date = (Date) fieldValue;
				Date today = Calendar.getInstance().getTime();
				return date.before(today);
			}
			throw new IllegalArgumentException("The argument should be a Date");
		}
	},

	/**
	 * Checking if the value is in the future
	 */
	FUTURE {
		@Override
		public boolean check(Object fieldValue, Object param) {
			if (NULL.check(fieldValue, null)) {
				return false;
			}
			if (fieldValue instanceof Date) {
				Date date = (Date) fieldValue;
				Date today = Calendar.getInstance().getTime();
				return date.after(today);
			}
			throw new IllegalArgumentException("The argument should be a Date");
		}
	},

	/**
	 * Checking the size of the List
	 */
	SIZE {
		@Override
		public boolean check(Object fieldValue, Object param) {
			if (NULL.check(fieldValue, null)) {
				return false;
			}
			if (NULL.check(param, null)) {
				throw new IllegalArgumentException("Param should be defined");
			}
			if (fieldValue instanceof Collection) {
				int size = (int) param;
				Collection fieldValueCol = (Collection) fieldValue;
				return fieldValueCol.size() == size;
			}
			throw new IllegalArgumentException("The argument should be a List");
		}
	},

	/**
	 * Checking if the value is bigger than the min value
	 */
	MIN {
		@Override
		public boolean check(Object fieldValue, Object param) {
			if (NULL.check(fieldValue, null)) {
				return false;
			}
			if (NULL.check(param, null)) {
				throw new IllegalArgumentException("Param should be defined");
			}
			if (fieldValue instanceof String) {
				return ((String) fieldValue).compareTo((String) param) > 0;
			}
			throw new IllegalArgumentException("The argument type is not supported");
		}
	},

	/**
	 * Checking if the value is smaller than the max value
	 */
	MAX {
		@Override
		public boolean check(Object fieldValue, Object param) {
			if (NULL.check(fieldValue, null)) {
				return false;
			}
			if (NULL.check(param, null)) {
				throw new IllegalArgumentException("Param should be defined");
			}
			if (fieldValue instanceof String) {
				return ((String) fieldValue).compareTo((String) param) < 0;
			}
			throw new IllegalArgumentException("The argument type is not supported");
		}
	},

	/**
	 * Checking the length of a string
	 */
	LENGTH {
		@Override
		public boolean check(Object fieldValue, Object param) {
			if (NULL.check(fieldValue, null)) {
				return false;
			}
			if (NULL.check(param, null)) {
				throw new IllegalArgumentException("Param should be defined");
			}
			if (fieldValue instanceof String) {
				int length = (int) param;
				String fieldValueStr = (String) fieldValue;
				return fieldValueStr.length() == length;
			}
			throw new IllegalArgumentException("The argument should be a String");
		}
	};

	public boolean check(Object fieldValue, Object param) {
		return false;
	}
}