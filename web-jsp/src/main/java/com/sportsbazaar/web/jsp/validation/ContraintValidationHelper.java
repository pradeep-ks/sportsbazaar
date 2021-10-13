package com.sportsbazaar.web.jsp.validation;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Set;

public abstract class ContraintValidationHelper {

    public static <T> T getFieldValue(Class<T> requiredType, String fieldName, Object value) {
	if (requiredType == null) {
	    throw new IllegalArgumentException("The requiredType cannot be NULL");
	}
	if (fieldName == null) {
	    throw new IllegalArgumentException("The fieldName cannot be NULL");
	}
	if (value == null) {
	    throw new IllegalArgumentException("The value cannot be NULL");
	}

	T returnValue = null;
	try {
	    PropertyDescriptor descriptor = new PropertyDescriptor(fieldName, value.getClass());
	    Method readMethod = descriptor.getReadMethod();
	    if (readMethod == null) {
		throw new IllegalArgumentException(
			"Property " + fieldName + " of " + value.getClass() + " is not readable!");
	    }
	    if (requiredType.isAssignableFrom(readMethod.getReturnType())) {
		Object fieldValue;
		try {
		    fieldValue = readMethod.invoke(value);
		    returnValue = requiredType.cast(fieldValue);
		} catch (Exception e) {
		    System.err.println(e.getMessage());
		}
	    }
	} catch (IntrospectionException e) {
	    throw new IllegalArgumentException("Property " + fieldName + " is not defined in " + value.getClass());
	}
	return returnValue;
    }

    public static boolean isValid(List<String> fieldValues) {
	Set<String> valuesSet = Set.<String>copyOf(fieldValues);
	return valuesSet.size() == 1;
    }

}
