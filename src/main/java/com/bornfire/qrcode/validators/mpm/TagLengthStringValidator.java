package com.bornfire.qrcode.validators.mpm;

import static com.bornfire.valid.predicate.ComparablePredicate.betweenInclusive;
import static com.bornfire.valid.predicate.LogicalPredicate.not;
import static com.bornfire.valid.predicate.StringPredicate.isNumeric;
import static com.bornfire.valid.predicate.StringPredicate.stringEmptyOrNull;
import static com.bornfire.valid.predicate.StringPredicate.stringSize;
import static com.bornfire.valid.predicate.StringPredicate.stringSizeBetween;

import com.bornfire.qrcode.core.model.mpm.TagLengthString;

import com.bornfire.valid.AbstractValidator;

// @formatter:off
class TagLengthStringValidator extends AbstractValidator<TagLengthString> {

  private final String tagStart;
  private final String tagEnd;
  private final Integer maxSizeValue;
  private final String fieldName;

  public TagLengthStringValidator(final String fieldName, final String tagStart, final String tagEnd, final Integer maxSizeValue) {
    this.fieldName = fieldName;
    this.tagStart = tagStart;
    this.tagEnd = tagEnd;
    this.maxSizeValue = maxSizeValue;
  }

  @Override
  public void rules() {

    ruleFor(TagLengthString::getTag)

      .must(not(stringEmptyOrNull()))
        .withFieldName("tag")
        .withMessage(String.format("%s tag is mandatory", fieldName))
        .withAttempedValue(TagLengthString::getTag)
        .critical()

      .must(stringSize(2))
        .withFieldName("tag")
        .withMessage(String.format("%s tag must be size equal two", fieldName))
        .withAttempedValue(TagLengthString::getTag)
        .critical()

      .must(isNumeric())
        .withFieldName("tag")
        .withMessage(String.format("%s tag must be number", fieldName))
        .withAttempedValue(TagLengthString::getTag)
        .critical()

      .must(betweenInclusive(tagStart, tagEnd))
        .withFieldName("tag")
        .withMessage(String.format("%s tag must be betwwen '%s' and '%s'", fieldName, tagStart, tagEnd))
        .withAttempedValue(TagLengthString::getTag)
        .critical();

    ruleFor(TagLengthString::getValue)
      .must(stringSizeBetween(1, maxSizeValue))
        .withFieldName("value")
        .withMessage(String.format("%s value must less then or equal size %d", fieldName, maxSizeValue))
        .withAttempedValue(TagLengthString::getValue)
        .critical();

  }

}
// @formatter:on
