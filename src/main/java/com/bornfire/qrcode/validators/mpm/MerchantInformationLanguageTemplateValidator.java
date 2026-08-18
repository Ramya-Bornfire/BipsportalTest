package com.bornfire.qrcode.validators.mpm;

import static com.bornfire.valid.predicate.LogicalPredicate.not;
import static com.bornfire.valid.predicate.ObjectPredicate.nullValue;

import com.bornfire.qrcode.model.mpm.MerchantInformationLanguageTemplate;

import com.bornfire.valid.AbstractValidator;

// @formatter:off
class MerchantInformationLanguageTemplateValidator extends AbstractValidator<MerchantInformationLanguageTemplate> {

  @Override
  public void rules() {

    /**
     *
     */
    ruleFor(MerchantInformationLanguageTemplate::getValue)
      .whenever(not(nullValue()))
        .withValidator(new MerchantInformationLanguageValidator());

  }

}
// @formatter:on
