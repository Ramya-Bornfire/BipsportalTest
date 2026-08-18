package com.bornfire.qrcode.model.mpm;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;

import com.bornfire.qrcode.core.model.mpm.TagLengthString;
import com.bornfire.qrcode.model.mpm.constants.MerchantAccountInformationFieldCodes;



public class PaymentSystemSpecific implements Serializable {

  private static final long serialVersionUID = 6244729218605588349L;

  // Globally Unique Identifier
  private TagLengthString globallyUniqueIdentifier;
  
  // Payee Participant Code
  private TagLengthString payeeParticipantCode;
  
  // Merchant Account Number
  private TagLengthString merchantAccountNumber;
  
  // Merchant ID
  private TagLengthString merchantID;

  // Context Specific Data
  private final Map<String, TagLengthString> paymentSystemSpecific = new LinkedHashMap<>();

  public PaymentSystemSpecific() {
    super();
  }

  public PaymentSystemSpecific(final String globallyUniqueIdentifier) {
    this.setGloballyUniqueIdentifier(globallyUniqueIdentifier);
  }
  
 
  public PaymentSystemSpecific(final String globallyUniqueIdentifier, final String tag, final String value) {
    this.setGloballyUniqueIdentifier(globallyUniqueIdentifier, tag, value);
  }

  public final void setGloballyUniqueIdentifier(final String globallyUniqueIdentifier) {
    this.globallyUniqueIdentifier = new TagLengthString(MerchantAccountInformationFieldCodes.ID_GLOBALLY_UNIQUE_IDENTIFIER, globallyUniqueIdentifier);
  }
  
  public final void setPayeeParticipantCode(final String payeeParticipantCode) {
	    this.payeeParticipantCode = new TagLengthString(MerchantAccountInformationFieldCodes.PAYEE_PARTICIPANT_CODE, payeeParticipantCode);
	  }
  
  public final void setMerchantAccountNumber(final String merchantAccountNumber) {
	    this.merchantAccountNumber = new TagLengthString(MerchantAccountInformationFieldCodes.MERCHANT_ACCOUNT_NUMBER, merchantAccountNumber);
	  }
  
  public final void setMerchantID(final String merchantID) {
	    this.merchantID = new TagLengthString(MerchantAccountInformationFieldCodes.MERCHANT_ID, merchantID);
	  }

  public final void setGloballyUniqueIdentifier(final String globallyUniqueIdentifier, final TagLengthString paymentSystemSpecific) {
    this.globallyUniqueIdentifier = new TagLengthString(MerchantAccountInformationFieldCodes.ID_GLOBALLY_UNIQUE_IDENTIFIER, globallyUniqueIdentifier);
    this.addPaymentSystemSpecific(paymentSystemSpecific);
  }

  public final void setGloballyUniqueIdentifier(final String globallyUniqueIdentifier, final String tag, final String value) {
    this.globallyUniqueIdentifier = new TagLengthString(MerchantAccountInformationFieldCodes.ID_GLOBALLY_UNIQUE_IDENTIFIER, globallyUniqueIdentifier);
    this.addPaymentSystemSpecific(tag, value);
  }

  public void addPaymentSystemSpecific(final TagLengthString tagLengthString) {
    paymentSystemSpecific.put(tagLengthString.getTag(), tagLengthString);
  }

  public void addPaymentSystemSpecific(final String tag, final String value) {
    paymentSystemSpecific.put(tag, new TagLengthString(tag, value));
  }

  @Override
  public String toString() {

    final StringBuilder sb = new StringBuilder();

    Optional.ofNullable(globallyUniqueIdentifier).ifPresent(tlv -> sb.append(tlv.toString()));
    Optional.ofNullable(payeeParticipantCode).ifPresent(tlv -> sb.append(tlv.toString()));
    Optional.ofNullable(merchantAccountNumber).ifPresent(tlv -> sb.append(tlv.toString()));
    Optional.ofNullable(merchantID).ifPresent(tlv -> sb.append(tlv.toString()));


    for (final Entry<String, TagLengthString> entry : paymentSystemSpecific.entrySet()) {
      Optional.ofNullable(entry.getValue()).ifPresent(tlv -> sb.append(tlv.toString()));
    }

    final String string = sb.toString();

    if (StringUtils.isBlank(string)) {
      return StringUtils.EMPTY;
    }

    return string;
  }

public TagLengthString getGloballyUniqueIdentifier() {
	return globallyUniqueIdentifier;
}

public void setGloballyUniqueIdentifier(TagLengthString globallyUniqueIdentifier) {
	this.globallyUniqueIdentifier = globallyUniqueIdentifier;
}

public TagLengthString getPayeeParticipantCode() {
	return payeeParticipantCode;
}

public void setPayeeParticipantCode(TagLengthString payeeParticipantCode) {
	this.payeeParticipantCode = payeeParticipantCode;
}

public TagLengthString getMerchantAccountNumber() {
	return merchantAccountNumber;
}

public void setMerchantAccountNumber(TagLengthString merchantAccountNumber) {
	this.merchantAccountNumber = merchantAccountNumber;
}

public TagLengthString getMerchantID() {
	return merchantID;
}

public void setMerchantID(TagLengthString merchantID) {
	this.merchantID = merchantID;
}
public Map<String, TagLengthString> getPaymentSystemSpecific() {
	return paymentSystemSpecific;
}
  
  

}
