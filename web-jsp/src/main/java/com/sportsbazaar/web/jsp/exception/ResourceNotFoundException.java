package com.sportsbazaar.web.jsp.exception;

public class ResourceNotFoundException extends RuntimeException {

    private String resourceName;

    private String fieldName;

    private Object fieldValue;

    public ResourceNotFoundException(String resourceName, String fieldName, Object fieldValue) {
	super(resourceName + " not found with " + fieldName + " = " + fieldValue);
	this.resourceName = resourceName;
	this.fieldName = fieldName;
	this.fieldValue = fieldValue;
    }

    public String getResourceName() {
	return resourceName;
    }

    public String getFieldName() {
	return fieldName;
    }

    public Object getFieldValue() {
	return fieldValue;
    }

}
