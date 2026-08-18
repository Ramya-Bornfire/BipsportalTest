package com.bornfire.entity;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CIMCustomerDirectFndRequest {
	@NotNull(message="Remitter Account Details Required")
	@Valid
	private CIMDirectCustomerRemitterAccount RemitterAccount;
	
	@NotNull(message="Merchant Account Details Required")
	@Valid
	private CIMDirectMerchantBenAccount MerchantAccount;
	
	
	private CIMAddlDataFieldRequest AdditionalDataInformation;

	
	public CIMDirectCustomerRemitterAccount getRemitterAccount() {
		return RemitterAccount;
	}

	public void setRemitterAccount(CIMDirectCustomerRemitterAccount remitterAccount) {
		RemitterAccount = remitterAccount;
	}

	public CIMDirectMerchantBenAccount getMerchantAccount() {
		return MerchantAccount;
	}

	public void setMerchantAccount(CIMDirectMerchantBenAccount merchantAccount) {
		MerchantAccount = merchantAccount;
	}

	public CIMAddlDataFieldRequest getAdditionalDataInformation() {
		return AdditionalDataInformation;
	}

	public void setAdditionalDataInformation(CIMAddlDataFieldRequest additionalDataInformation) {
		AdditionalDataInformation = additionalDataInformation;
	}


}
