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
import com.bornfire.qrcode.model.mpm.MerchantAccountInformationReservedAdditional;
import com.bornfire.qrcode.model.mpm.constants.MerchantAccountInformationFieldCodes;

import com.bornfire.valid.AbstractValidator;

// @formatter:off
class MerchantAccountInformationReservedAdditionalValidator extends AbstractValidator<MerchantAccountInformationReservedAdditional> {

  @Override
  public void rules() {

    ruleFor("GloballyUniqueIdentifier", MerchantAccountInformationReservedAdditional::getGloballyUniqueIdentifier)

      .must(not(stringEmptyOrNull(TagLengthString::getTag)))
        .withMessage("GloballyUniqueIdentifier tag is mandatory")
        .withAttempedValue(of(MerchantAccountInformationReservedAdditional::getGloballyUniqueIdentifier).andThen(TagLengthString::getTag))
        .critical()

      .must(stringSize(TagLengthString::getTag, 2))
        .withMessage("GloballyUniqueIdentifier tag must be size equal two")
        .withAttempedValue(of(MerchantAccountInformationReservedAdditional::getGloballyUniqueIdentifier).andThen(TagLengthString::getTag))
        .critical()

      .must(isNumeric(TagLengthString::getTag))
        .withMessage("GloballyUniqueIdentifier tag must be number")
        .withAttempedValue(of(MerchantAccountInformationReservedAdditional::getGloballyUniqueIdentifier).andThen(TagLengthString::getTag))
        .critical()

      .must(stringEquals(TagLengthString::getTag, MerchantAccountInformationFieldCodes.ID_GLOBALLY_UNIQUE_IDENTIFIER))
        .withMessage(String.format("GloballyUniqueIdentifier tag must be '%s'", MerchantAccountInformationFieldCodes.ID_GLOBALLY_UNIQUE_IDENTIFIER))
        .withAttempedValue(of(MerchantAccountInformationReservedAdditional::getGloballyUniqueIdentifier).andThen(TagLengthString::getTag))
        .critical()

      .must(not(stringEmptyOrNull(TagLengthString::getValue)))
        .withMessage("GloballyUniqueIdentifier value is mandatory")
        .withAttempedValue(of(MerchantAccountInformationReservedAdditional::getGloballyUniqueIdentifier).andThen(TagLengthString::getValue))
        .critical()

      .must(stringSizeLessThanOrEqual(TagLengthString::getValue, 11))
        .withMessage("GloballyUniqueIdentifier value must less then or equal eleven")
        .withAttempedValue(of(MerchantAccountInformationReservedAdditional::getGloballyUniqueIdentifier).andThen(TagLengthString::getValue))
        .critical();
    
    ruleFor("PayeeParticipantCode", MerchantAccountInformationReservedAdditional::getPayeeParticipantCode)

    .must(not(stringEmptyOrNull(TagLengthString::getTag)))
      .withMessage("PayeeParticipantCode tag is mandatory")
      .withAttempedValue(of(MerchantAccountInformationReservedAdditional::getPayeeParticipantCode).andThen(TagLengthString::getTag))
      .critical()

    .must(stringSize(TagLengthString::getTag, 2))
      .withMessage("PayeeParticipantCode tag must be size equal two")
      .withAttempedValue(of(MerchantAccountInformationReservedAdditional::getPayeeParticipantCode).andThen(TagLengthString::getTag))
      .critical()

    .must(isNumeric(TagLengthString::getTag))
      .withMessage("PayeeParticipantCode tag must be number")
      .withAttempedValue(of(MerchantAccountInformationReservedAdditional::getPayeeParticipantCode).andThen(TagLengthString::getTag))
      .critical()

    .must(stringEquals(TagLengthString::getTag, MerchantAccountInformationFieldCodes.PAYEE_PARTICIPANT_CODE))
      .withMessage(String.format("PayeeParticipantCode tag must be '%s'", MerchantAccountInformationFieldCodes.PAYEE_PARTICIPANT_CODE))
      .withAttempedValue(of(MerchantAccountInformationReservedAdditional::getPayeeParticipantCode).andThen(TagLengthString::getTag))
      .critical()

    .must(not(stringEmptyOrNull(TagLengthString::getValue)))
      .withMessage("PayeeParticipantCode value is mandatory")
      .withAttempedValue(of(MerchantAccountInformationReservedAdditional::getPayeeParticipantCode).andThen(TagLengthString::getValue))
      .critical()

    .must(stringSizeLessThanOrEqual(TagLengthString::getValue, 12))
      .withMessage("PayeeParticipantCode value must less then or equal twelve")
      .withAttempedValue(of(MerchantAccountInformationReservedAdditional::getPayeeParticipantCode).andThen(TagLengthString::getValue))
      .critical();
    
    ruleFor("MerchantAccountNumber", MerchantAccountInformationReservedAdditional::getMerchantAccountNumber)

    .must(not(stringEmptyOrNull(TagLengthString::getTag)))
      .withMessage("MerchantAccountNumber tag is mandatory")
      .withAttempedValue(of(MerchantAccountInformationReservedAdditional::getMerchantAccountNumber).andThen(TagLengthString::getTag))
      .critical()

    .must(stringSize(TagLengthString::getTag, 2))
      .withMessage("MerchantAccountNumber tag must be size equal two")
      .withAttempedValue(of(MerchantAccountInformationReservedAdditional::getMerchantAccountNumber).andThen(TagLengthString::getTag))
      .critical()

    .must(isNumeric(TagLengthString::getTag))
      .withMessage("MerchantAccountNumber tag must be number")
      .withAttempedValue(of(MerchantAccountInformationReservedAdditional::getMerchantAccountNumber).andThen(TagLengthString::getTag))
      .critical()

    .must(stringEquals(TagLengthString::getTag, MerchantAccountInformationFieldCodes.MERCHANT_ACCOUNT_NUMBER))
      .withMessage(String.format("MerchantAccountNumber tag must be '%s'", MerchantAccountInformationFieldCodes.MERCHANT_ACCOUNT_NUMBER))
      .withAttempedValue(of(MerchantAccountInformationReservedAdditional::getMerchantAccountNumber).andThen(TagLengthString::getTag))
      .critical()

    .must(not(stringEmptyOrNull(TagLengthString::getValue)))
      .withMessage("MerchantAccountNumber value is mandatory")
      .withAttempedValue(of(MerchantAccountInformationReservedAdditional::getMerchantAccountNumber).andThen(TagLengthString::getValue))
      .critical()

    .must(stringSizeLessThanOrEqual(TagLengthString::getValue, 38))
      .withMessage("MerchantAccountNumber value must less then or equal thirty eight")
      .withAttempedValue(of(MerchantAccountInformationReservedAdditional::getMerchantAccountNumber).andThen(TagLengthString::getValue))
      .critical();
    
    ruleFor("MerchantID", MerchantAccountInformationReservedAdditional::getMerchantID)

    .must(not(stringEmptyOrNull(TagLengthString::getTag)))
      .withMessage("MerchantID tag is mandatory")
      .withAttempedValue(of(MerchantAccountInformationReservedAdditional::getMerchantID).andThen(TagLengthString::getTag))
      .critical()

    .must(stringSize(TagLengthString::getTag, 2))
      .withMessage("MerchantID tag must be size equal two")
      .withAttempedValue(of(MerchantAccountInformationReservedAdditional::getMerchantID).andThen(TagLengthString::getTag))
      .critical()

    .must(isNumeric(TagLengthString::getTag))
      .withMessage("MerchantID tag must be number")
      .withAttempedValue(of(MerchantAccountInformationReservedAdditional::getMerchantID).andThen(TagLengthString::getTag))
      .critical()

    .must(stringEquals(TagLengthString::getTag, MerchantAccountInformationFieldCodes.MERCHANT_ID))
      .withMessage(String.format("MerchantID tag must be '%s'", MerchantAccountInformationFieldCodes.MERCHANT_ID))
      .withAttempedValue(of(MerchantAccountInformationReservedAdditional::getMerchantID).andThen(TagLengthString::getTag))
      .critical()

    .must(not(stringEmptyOrNull(TagLengthString::getValue)))
      .withMessage("MerchantID value is mandatory")
      .withAttempedValue(of(MerchantAccountInformationReservedAdditional::getMerchantID).andThen(TagLengthString::getValue))
      .critical()

    .must(stringSizeLessThanOrEqual(TagLengthString::getValue, 15))
      .withMessage("MerchantID value must less then or equal Fifteen")
      .withAttempedValue(of(MerchantAccountInformationReservedAdditional::getMerchantID).andThen(TagLengthString::getValue))
      .critical();


    ruleForEach(of(MerchantAccountInformationReservedAdditional::getPaymentNetworkSpecific).andThen(Map::values))
      .whenever(greaterThan(Collection::size, 0))
        .withValidator(new TagLengthStringValidator("MerchantAccountInformation.PaymentNetworkSpecific", "01", "99", 99));

  }

}
// @formatter:on
