package com.bornfire.exception;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy=TranAmountValidator.class)
@Documented
public @interface TranAmount {
	String message() default"Tran Amount mustbe greater than Zero";
	
	Class<?>[] groups() default{};
	
	Class<? extends Payload>[] payload() default{};
}
