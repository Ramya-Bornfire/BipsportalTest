package com.bornfire.qrcode.validators;

import com.bornfire.qrcode.model.cpm.ConsumerPresentedMode;
import com.bornfire.qrcode.validators.cpm.ConsumerPresentedModeValidator;

import com.bornfire.valid.Validator;
import com.bornfire.valid.context.ValidationResult;

public final class ConsumerPresentedModeValidate {

  private static final Validator<ConsumerPresentedMode> VALIDATOR = new ConsumerPresentedModeValidator();

  private ConsumerPresentedModeValidate() {
    super();
  }

  public static final ValidationResult validate(final ConsumerPresentedMode instance) {
    return VALIDATOR.validate(instance);
  }

}
