package com.bornfire.qrcode.validators.cpm;

import static com.bornfire.valid.predicate.CollectionPredicate.empty;
import static com.bornfire.valid.predicate.CollectionPredicate.hasSizeBetweenInclusive;
import static com.bornfire.valid.predicate.LogicalPredicate.not;
import static com.bornfire.valid.predicate.ObjectPredicate.nullValue;

import com.bornfire.qrcode.model.cpm.ConsumerPresentedMode;

import com.bornfire.valid.AbstractValidator;

// @formatter:off
public class ConsumerPresentedModeValidator extends AbstractValidator<ConsumerPresentedMode> {

  @Override
  public void rules() {

    setPropertyOnContext("cpm");

    ruleFor("PayloadFormatIndicator", ConsumerPresentedMode::getPayloadFormatIndicator)
      .must(not(nullValue()))
        .withMessage("PayloadFormatIndicator must be present")
      .whenever(not(nullValue()))
        .withValidator(new PayloadFormatIndicatorValidator());

    ruleFor("ApplicationTemplate", ConsumerPresentedMode::getApplicationTemplates)
      .must(not(empty()))
        .withMessage("ApplicationTemplate must be present")
      .must(hasSizeBetweenInclusive(1, 2))
        .when(not(empty()))
        .withMessage("ApplicationTemplate list size must be between one and two");

    ruleForEach(ConsumerPresentedMode::getApplicationTemplates)
      .whenever(not(empty()))
        .withValidator(new ApplicationTemplateValidator());

    ruleFor(ConsumerPresentedMode::getCommonDataTemplate)
      .whenever(not(nullValue()))
        .withValidator(new CommonDataTemplateValidator());
  }

}

// @formatter:on
