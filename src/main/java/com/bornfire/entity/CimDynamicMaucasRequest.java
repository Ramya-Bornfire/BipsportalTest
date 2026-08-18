package com.bornfire.entity;

import javax.validation.constraints.NotBlank;

import com.bornfire.exception.TranAmount;

public class CimDynamicMaucasRequest {

	@NotBlank(message="Merchant ID Required")
	private String merchant_ID;	
	@NotBlank(message="Transaction Amount Required")
	@TranAmount
	private String tran_amt;
	private String mob_num;
	private String loy_num;
	private String sto_label;
	private String cust_label;
	private String ref_label;
	private String ter_label;
	private String pur_tran;
	private String add_det;
	@NotBlank(message="Bill Number Required")
	private String bill_num;
	public String getMerchant_ID() {
		return merchant_ID;
	}
	public void setMerchant_ID(String merchant_ID) {
		this.merchant_ID = merchant_ID;
	}
	public String getTran_amt() {
		return tran_amt;
	}
	public void setTran_amt(String tran_amt) {
		this.tran_amt = tran_amt;
	}
	public String getMob_num() {
		return mob_num;
	}
	public void setMob_num(String mob_num) {
		this.mob_num = mob_num;
	}
	public String getLoy_num() {
		return loy_num;
	}
	public void setLoy_num(String loy_num) {
		this.loy_num = loy_num;
	}
	public String getSto_label() {
		return sto_label;
	}
	public void setSto_label(String sto_label) {
		this.sto_label = sto_label;
	}
	public String getCust_label() {
		return cust_label;
	}
	public void setCust_label(String cust_label) {
		this.cust_label = cust_label;
	}
	public String getRef_label() {
		return ref_label;
	}
	public void setRef_label(String ref_label) {
		this.ref_label = ref_label;
	}
	public String getTer_label() {
		return ter_label;
	}
	public void setTer_label(String ter_label) {
		this.ter_label = ter_label;
	}
	public String getPur_tran() {
		return pur_tran;
	}
	public void setPur_tran(String pur_tran) {
		this.pur_tran = pur_tran;
	}
	public String getAdd_det() {
		return add_det;
	}
	public void setAdd_det(String add_det) {
		this.add_det = add_det;
	}
	public String getBill_num() {
		return bill_num;
	}
	public void setBill_num(String bill_num) {
		this.bill_num = bill_num;
	}
	public CimDynamicMaucasRequest(@NotBlank(message = "Merchant ID Required") String merchant_ID,
			@NotBlank(message = "Transaction Amount Required") String tran_amt, String mob_num, String loy_num,
			String sto_label, String cust_label, String ref_label, String ter_label, String pur_tran, String add_det,
			@NotBlank(message = "Bill Number Required") String bill_num) {
		super();
		this.merchant_ID = merchant_ID;
		this.tran_amt = tran_amt;
		this.mob_num = mob_num;
		this.loy_num = loy_num;
		this.sto_label = sto_label;
		this.cust_label = cust_label;
		this.ref_label = ref_label;
		this.ter_label = ter_label;
		this.pur_tran = pur_tran;
		this.add_det = add_det;
		this.bill_num = bill_num;
	}
	public CimDynamicMaucasRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
