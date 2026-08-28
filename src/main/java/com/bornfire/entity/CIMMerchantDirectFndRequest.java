package com.bornfire.entity;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

@com.fasterxml.jackson.annotation.JsonIgnoreProperties(ignoreUnknown = true)
public class CIMMerchantDirectFndRequest {
	@NotNull(message="Remitter Account Details Required")
	@Valid
	private CIMDirectMerchantRemitterAccount RemitterAccount;
	
	@NotNull(message="Merchant Account Details Required")
	@Valid
	private CIMDirectMerchantBenAccount MerchantAccount;
	
	
	private CIMAddlDataFieldRequest AdditionalDataInformation;

	@JsonProperty("reqUniqueID")
	private String ReqUniqueId;
	
	@JsonProperty("trRmks")
	private String TrRmks;
	
	public CIMDirectMerchantRemitterAccount getRemitterAccount() {
		return RemitterAccount;
	}

	public void setRemitterAccount(CIMDirectMerchantRemitterAccount remitterAccount) {
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

	public String getReqUniqueId() {
		return ReqUniqueId;
	}

	public void setReqUniqueId(String reqUniqueId) {
		ReqUniqueId = reqUniqueId;
	}

	public String getTrRmks() {
		return TrRmks;
	}

	public void setTrRmks(String trRmks) {
		TrRmks = trRmks;
	}

	
	
	
}
