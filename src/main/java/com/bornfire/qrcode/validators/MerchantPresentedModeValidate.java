package com.bornfire.qrcode.validators;

import com.bornfire.qrcode.model.mpm.MerchantPresentedMode;
import com.bornfire.qrcode.validators.mpm.MerchantPresentedModeValidator;

import com.bornfire.valid.Validator;
import com.bornfire.valid.context.ValidationResult;

public final class MerchantPresentedModeValidate {

  private static final Validator<MerchantPresentedMode> VALIDATOR = new MerchantPresentedModeValidator();

  private MerchantPresentedModeValidate() {
    super();
  }

  public static final ValidationResult validate(final MerchantPresentedMode instance) {
    return VALIDATOR.validate(instance);
  }

}
