package com.bornfire.qrcode.validators.cpm;

import static com.bornfire.valid.predicate.StringPredicate.stringEquals;

import com.bornfire.qrcode.model.cpm.PayloadFormatIndicator;

import com.bornfire.valid.AbstractValidator;

// @formatter:off
class PayloadFormatIndicatorValidator extends AbstractValidator<PayloadFormatIndicator> {

  @Override
  public void rules() {

    ruleFor("PayloadFormatIndicator.value", PayloadFormatIndicator::getStringValue)
      .must(stringEquals("CPV01"))
        .withMessage("PayloadFormatIndicator value must be equal 'CPV01'");

  }

}
// @formatter:on
