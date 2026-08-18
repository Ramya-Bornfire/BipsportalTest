package com.bornfire.qrcode.validators.mpm;

import static com.bornfire.valid.function.FunctionBuilder.of;
import static com.bornfire.valid.predicate.ComparablePredicate.betweenInclusive;
import static com.bornfire.valid.predicate.LogicalPredicate.not;
import static com.bornfire.valid.predicate.ObjectPredicate.nullValue;
import static com.bornfire.valid.predicate.StringPredicate.stringSizeBetween;

import com.bornfire.qrcode.model.mpm.PaymentSystemSpecific;
import com.bornfire.qrcode.model.mpm.PaymentSystemSpecificTemplate;

import com.bornfire.valid.AbstractValidator;

// @formatter:off
class PaymentSystemSpecificTemplateValidator extends AbstractValidator<PaymentSystemSpecificTemplate> {

  private final String tagStart;
  private final String tagEnd;
  private final Integer maxSizeValue;

  public PaymentSystemSpecificTemplateValidator(final String tagStart, final String tagEnd, final Integer maxSizeValue) {
    this.tagStart = tagStart;
    this.tagEnd = tagEnd;
    this.maxSizeValue = maxSizeValue;
  }

  @Override
  public void rules() {

    ruleFor("PaymentSystemSpecificTemplate", PaymentSystemSpecificTemplate::getTag)
      .must(betweenInclusive(tagStart, tagEnd))
      .critical();

    ruleFor("PaymentSystemSpecificTemplate", of(PaymentSystemSpecificTemplate::getValue).andThen(PaymentSystemSpecific::toString))
      .must(stringSizeBetween(1, maxSizeValue))
      .critical();

    ruleFor(PaymentSystemSpecificTemplate::getValue)
      .whenever(not(nullValue()))
        .withValidator(new PaymentSystemSpecificValidator());

  }

}
// @formatter:on
