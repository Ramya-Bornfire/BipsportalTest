package com.bornfire.qrcode.validators.mpm;

import static com.bornfire.valid.predicate.StringPredicate.stringSizeBetween;

import com.bornfire.qrcode.model.mpm.MerchantAccountInformationReserved;

import com.bornfire.valid.AbstractValidator;

// @formatter:off
class MerchantAccountInformationReservedValidator extends AbstractValidator<MerchantAccountInformationReserved> {

  @Override
  public void rules() {

    ruleFor(MerchantAccountInformationReserved::getValue)
      .must(stringSizeBetween(1, 99))
        .withFieldName("value")
        .withMessage("MerchantAccountInformation value must be between one and ninety-nine")
        .withAttempedValue(MerchantAccountInformationReserved::getValue)
        .critical();
  }

}
// @formatter:on
