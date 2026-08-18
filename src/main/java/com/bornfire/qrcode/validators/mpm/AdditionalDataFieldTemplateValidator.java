package com.bornfire.qrcode.validators.mpm;

import static com.bornfire.valid.predicate.LogicalPredicate.not;
import static com.bornfire.valid.predicate.ObjectPredicate.nullValue;

import com.bornfire.qrcode.model.mpm.AdditionalDataFieldTemplate;

import com.bornfire.valid.AbstractValidator;

// @formatter:off
class AdditionalDataFieldTemplateValidator extends AbstractValidator<AdditionalDataFieldTemplate> {

  @Override
  public void rules() {

    /**
    *
    */
   ruleFor(AdditionalDataFieldTemplate::getValue)
     .whenever(not(nullValue()))
       .withValidator(new AdditionalDataFieldValidator());

  }

}
// @formatter:on
