package com.bornfire.qrcode.validators.mpm;

import static com.bornfire.valid.function.FunctionBuilder.of;
import static com.bornfire.valid.predicate.ComparablePredicate.betweenInclusive;
import static com.bornfire.valid.predicate.LogicalPredicate.not;
import static com.bornfire.valid.predicate.ObjectPredicate.nullValue;
import static com.bornfire.valid.predicate.StringPredicate.stringSizeBetween;

import com.bornfire.qrcode.model.mpm.Unreserved;
import com.bornfire.qrcode.model.mpm.UnreservedTemplate;

import com.bornfire.valid.AbstractValidator;

// @formatter:off
class UnreservedTemplateValidator extends AbstractValidator<UnreservedTemplate> {

  private final String tagStart;
  private final String tagEnd;
  private final Integer maxSizeValue;

  public UnreservedTemplateValidator(final String tagStart, final String tagEnd, final Integer maxSizeValue) {
    this.tagStart = tagStart;
    this.tagEnd = tagEnd;
    this.maxSizeValue = maxSizeValue;
  }

  @Override
  public void rules() {

    ruleFor("UnreservedTemplate", UnreservedTemplate::getTag)
      .must(betweenInclusive(tagStart, tagEnd));

    ruleFor("UnreservedTemplate", of(UnreservedTemplate::getValue).andThen(Unreserved::toString))
      .must(stringSizeBetween(1, maxSizeValue));

    ruleFor(UnreservedTemplate::getValue)
      .whenever(not(nullValue()))
        .withValidator(new UnreservedValidator());

  }

}
// @formatter:on
