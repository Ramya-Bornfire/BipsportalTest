package com.bornfire.entity;

import javax.validation.constraints.NotBlank;

public class CIMDirectCustomerRemitterAccount {
	@NotBlank(message="Customer Name is Required")
	private String CustomerName;
	
	@NotBlank(message="Customer ID is Required")
	private String CustomerID;
	
	private String ReferenceNumber;


	public String getCustomerName() {
		return CustomerName;
	}

	public void setCustomerName(String customerName) {
		CustomerName = customerName;
	}

	public String getCustomerID() {
		return CustomerID;
	}

	public void setCustomerID(String customerID) {
		CustomerID = customerID;
	}

	public String getReferenceNumber() {
		return ReferenceNumber;
	}

	public void setReferenceNumber(String referenceNumber) {
		ReferenceNumber = referenceNumber;
	}

	public CIMDirectCustomerRemitterAccount(@NotBlank(message = "Remitter Account Name Required") String customerName,
			@NotBlank(message = "Remitter Account Number Required") String customerID, String referenceNumber) {
		super();
		CustomerName = customerName;
		CustomerID = customerID;
		ReferenceNumber = referenceNumber;
	}

	public CIMDirectCustomerRemitterAccount() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "CIMDirectCustomerRemitterAccount [CustomerName=" + CustomerName + ", CustomerID=" + CustomerID
				+ ", ReferenceNumber=" + ReferenceNumber + "]";
	}

	
}
