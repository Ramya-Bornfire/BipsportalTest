package com.bornfire.qrcode.model.mpm;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;

import com.bornfire.qrcode.core.model.mpm.TagLengthString;
import com.bornfire.qrcode.model.mpm.constants.MerchantAccountInformationFieldCodes;


public class MerchantAccountInformationReservedAdditional implements MerchantAccountInformation {

	private static final long serialVersionUID = 3394308551644415429L;

	// Globally Unique Identifier
	private TagLengthString globallyUniqueIdentifier;
	
	// Globally Unique Identifier
	private TagLengthString payeeParticipantCode;
	// Globally Unique Identifier
	private TagLengthString merchantAccountNumber;
	// Globally Unique Identifier
	private TagLengthString merchantID;

	// Payment network specific
	private final Map<String, TagLengthString> paymentNetworkSpecific = new LinkedHashMap<>();

	public MerchantAccountInformationReservedAdditional() {
		super();
	}

	public MerchantAccountInformationReservedAdditional(final String globallyUniqueIdentifier) {
		this.setGloballyUniqueIdentifier(globallyUniqueIdentifier);
	}

	public MerchantAccountInformationReservedAdditional(final String globallyUniqueIdentifier, final String tag,
			final String value) {
		this.setGloballyUniqueIdentifier(globallyUniqueIdentifier, tag, value);
	}

	public final void setGloballyUniqueIdentifier(final String globallyUniqueIdentifier) {
		this.globallyUniqueIdentifier = new TagLengthString(
				MerchantAccountInformationFieldCodes.ID_GLOBALLY_UNIQUE_IDENTIFIER, globallyUniqueIdentifier);
	}
	
	public final void setPayeeParticipantCode(final String payeeParticipantCode) {
		this.payeeParticipantCode = new TagLengthString(
				MerchantAccountInformationFieldCodes.PAYEE_PARTICIPANT_CODE, payeeParticipantCode);
	}
	
	public final void setMerchantAccountNumber(final String merchantAccountNumber) {
		this.merchantAccountNumber = new TagLengthString(
				MerchantAccountInformationFieldCodes.MERCHANT_ACCOUNT_NUMBER, merchantAccountNumber);
	}
	
	public final void setMerchantID(final String merchantID) {
		this.merchantID = new TagLengthString(
				MerchantAccountInformationFieldCodes.MERCHANT_ID, merchantID);
	}
	

	public final void setGloballyUniqueIdentifier(final String globallyUniqueIdentifier, final String tag,
			final String value) {
		this.globallyUniqueIdentifier = new TagLengthString(
				MerchantAccountInformationFieldCodes.ID_GLOBALLY_UNIQUE_IDENTIFIER, globallyUniqueIdentifier);
		this.addPaymentNetworkSpecific(tag, value);
	}
	

	public void addPaymentNetworkSpecific(final TagLengthString tagLengthString) {
		paymentNetworkSpecific.put(tagLengthString.getTag(), tagLengthString);
	}

	public void addPaymentNetworkSpecific(final String tag, final String value) {
		paymentNetworkSpecific.put(tag, new TagLengthString(tag, value));
	}

	@Override
	public String toString() {

		final StringBuilder sb = new StringBuilder();

		Optional.ofNullable(globallyUniqueIdentifier).ifPresent(tlv -> sb.append(tlv.toString()));
		Optional.ofNullable(payeeParticipantCode).ifPresent(tlv -> sb.append(tlv.toString()));
		Optional.ofNullable(merchantAccountNumber).ifPresent(tlv -> sb.append(tlv.toString()));
		Optional.ofNullable(merchantID).ifPresent(tlv -> sb.append(tlv.toString()));


		for (final Entry<String, TagLengthString> entry : paymentNetworkSpecific.entrySet()) {
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

	public Map<String, TagLengthString> getPaymentNetworkSpecific() {
		return paymentNetworkSpecific;
	}

}
