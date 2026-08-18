package com.bornfire.qrcode.validators.mpm;

import static com.bornfire.valid.function.FunctionBuilder.of;
import static com.bornfire.valid.predicate.ComparablePredicate.greaterThan;
import static com.bornfire.valid.predicate.LogicalPredicate.not;
import static com.bornfire.valid.predicate.StringPredicate.isNumeric;
import static com.bornfire.valid.predicate.StringPredicate.stringEmptyOrNull;
import static com.bornfire.valid.predicate.StringPredicate.stringEquals;
import static com.bornfire.valid.predicate.StringPredicate.stringSize;
import static com.bornfire.valid.predicate.StringPredicate.stringSizeLessThanOrEqual;

import java.util.Collection;
import java.util.Map;

import com.bornfire.qrcode.core.model.mpm.TagLengthString;
import com.bornfire.qrcode.model.mpm.Unreserved;
import com.bornfire.qrcode.model.mpm.constants.UnreservedTemplateFieldCodes;

import com.bornfire.valid.AbstractValidator;

// @formatter:off
class UnreservedValidator extends AbstractValidator<Unreserved> {

  @Override
  public void rules() {

    ruleFor("GloballyUniqueIdentifier", Unreserved::getGloballyUniqueIdentifier)

      .must(not(stringEmptyOrNull(TagLengthString::getTag)))
        .withMessage("GloballyUniqueIdentifier tag is mandatory")
        .withAttempedValue(of(Unreserved::getGloballyUniqueIdentifier).andThen(TagLengthString::getTag))
        .critical()

      .must(stringSize(TagLengthString::getTag, 2))
        .withMessage("GloballyUniqueIdentifier tag must be size equal two")
        .withAttempedValue(of(Unreserved::getGloballyUniqueIdentifier).andThen(TagLengthString::getTag))
        .critical()

      .must(isNumeric(TagLengthString::getTag))
        .withMessage("GloballyUniqueIdentifier tag must be number")
        .withAttempedValue(of(Unreserved::getGloballyUniqueIdentifier).andThen(TagLengthString::getTag))
        .critical()

      .must(stringEquals(TagLengthString::getTag, UnreservedTemplateFieldCodes.ID_GLOBALLY_UNIQUE_IDENTIFIER))
        .withMessage(String.format("GloballyUniqueIdentifier tag must be '%s'", UnreservedTemplateFieldCodes.ID_GLOBALLY_UNIQUE_IDENTIFIER))
        .withAttempedValue(of(Unreserved::getGloballyUniqueIdentifier).andThen(TagLengthString::getTag))
        .critical()

      .must(not(stringEmptyOrNull(TagLengthString::getValue)))
        .withMessage("GloballyUniqueIdentifier value is mandatory")
        .withAttempedValue(of(Unreserved::getGloballyUniqueIdentifier).andThen(TagLengthString::getValue))
        .critical()

      .must(stringSizeLessThanOrEqual(TagLengthString::getValue, 32))
        .withMessage("GloballyUniqueIdentifier value must less then or equal size thirty-two")
        .withAttempedValue(of(Unreserved::getGloballyUniqueIdentifier).andThen(TagLengthString::getValue))
        .critical();

    ruleForEach(of(Unreserved::getContextSpecificData).andThen(Map::values))
      .whenever(greaterThan(Collection::size, 0))
        .withValidator(new TagLengthStringValidator("Unreserved.ContextSpecificData", "01", "99", 99));

  }

}
// @formatter:on
