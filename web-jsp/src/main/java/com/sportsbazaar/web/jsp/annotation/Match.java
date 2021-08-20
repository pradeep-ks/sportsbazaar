package com.sportsbazaar.web.jsp.annotation;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.sportsbazaar.web.jsp.validation.MatchValidator;

@Documented
@Retention(RUNTIME)
@Target(TYPE)
@Constraint(validatedBy = MatchValidator.class)
public @interface Match {

    String[] fieldNames();
    
    boolean nullable() default false;
    
    String message() default "Password and confirm password did not match";
    
    Class<?>[] groups() default {};
    
    Class<? extends Payload>[] payload() default {};
}
