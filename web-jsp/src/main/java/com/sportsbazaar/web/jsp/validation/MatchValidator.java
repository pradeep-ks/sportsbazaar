package com.sportsbazaar.web.jsp.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.sportsbazaar.web.jsp.annotation.Match;

public class MatchValidator implements ConstraintValidator<Match, Object> {

    private String[] fieldNames;

    private boolean nullable;

    @Override
    public void initialize(Match constraintAnnotation) {
	this.fieldNames = constraintAnnotation.fieldNames();
	this.nullable = constraintAnnotation.nullable();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
	boolean valid = true;
	List<String> fieldValues = new ArrayList<>(this.fieldNames.length);
	for (String fieldName : this.fieldNames) {
	    String fieldValue = ContraintValidationHelper.getFieldValue(String.class, fieldName, value);
	    if (fieldValue == null && !this.nullable) {
		valid = false;
		break;
	    } else {
		fieldValues.add(fieldValue);
	    }
	}

	if (valid) {
	    valid = ContraintValidationHelper.isValid(fieldValues);
	}

	if (!valid) {
	    context.disableDefaultConstraintViolation();
	    context.buildConstraintViolationWithTemplate(context.getDefaultConstraintMessageTemplate())
		    .addPropertyNode("confirmPassword").addConstraintViolation();
	}

	return valid;
    }

}
