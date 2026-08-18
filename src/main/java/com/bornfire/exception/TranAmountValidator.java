package com.bornfire.exception;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class TranAmountValidator implements ConstraintValidator<TranAmount, String>{

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		
		if(value!=null) {
			if(!value.equals("")) {
				if(Double.parseDouble(value)>=0){
					return true;
				}else{
					return false;
				}
			}else{
				return false;
			}
			
		}else {
			return false;
		}
		
		
	}

}
