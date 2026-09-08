package com.bornfire.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "customer_transaction_details")
public class CustomerTransactionEntity {

	private String payload_format_indicator;
	@Size(min = 2, max = 2, message = "PointOfInitiationFormat must contains 2 characters")
	private String poi_method;
	@NotBlank(message = "GloballyUniqueIdentifier Required")
	@Size(max = 11, message = "GloballyUniqueIdentifier should not exceed 11 characters")
	private String global_unique_id;
	@NotBlank(message = "PayeeParticipantCode Required")
	@Size(min = 8, max = 12, message = "PayeeParticipantCode should not exceed 12 characters")
	private String payee_participant_code;
	private String customer_acct_no;
	private String customer_id;
	private String customer_name;
	private String city;
	private String state;
	private String country;
	private String phone;
	private String customer_mobile;
	private String customer_transaction_crncy;
	private String customer_transaction_amt;
	private String conv_fees_type;
	private String customer_bill_number;
	private String customer_reference_label;
	private String customer_deviceid;
	private String purpose_of_tran;
	private String additional_details;
	private String customer_remarks;
	private String customer_status;
	@Size(min = 4, max = 4, message = "MerchantCategoryCode should contains 4 characters")
	private String mcc;
	@NotBlank(message = "Currency Required")
	@Size(min = 3, max = 3, message = "Currency should contains 3 characters")
	private String currency;
	@NotBlank(message = "CountryCode Required")
	//@Size(max = 2, message = "CountryCode should not exceed 2 characters")
	private String country_code;
	private String postal_code;
	private String merchant_status;
	private String transaction_status;
	private String merchant_mob_no;
	@Id
	private String merchant_reference_label;
	private String merchant_deviceid;
	private String merchant_terminal_label;
	@Size(max = 38, message = "MerchantAcctNumber should not exceed 38 characters")
	private String merchant_acct_no;
	@NotBlank(message = "Merchant ID Required")
	@Size(max = 15, message = "Merchant ID should not exceed 15 characters")
	private String merchant_id;
	@NotBlank(message = "MerchantName Required")
	@Size(max = 25, message = "MerchantName should not exceed 25 characters")
	private String merchant_name;
	private String entry_user;
	private Date entry_time; 
	
//	private String entry_time;
//	public String getEntry_time() {
//		return entry_time;
//	}
//
//	public void setEntry_time(String entry_time) {
//		this.entry_time = entry_time;
//	}

	private String modify_user;
	private Date modify_time;
	private String verify_user;
	private Date verify_time;
	private String entity_flg;
	private String modify_flg;
	private String del_flg;
	private String user_id;
	private String unit_id;

	public String getPayload_format_indicator() {
		return payload_format_indicator;
	}

	public void setPayload_format_indicator(String payload_format_indicator) {
		this.payload_format_indicator = payload_format_indicator;
	}

	public String getPoi_method() {
		return poi_method;
	}

	public void setPoi_method(String poi_method) {
		this.poi_method = poi_method;
	}

	public String getGlobal_unique_id() {
		return global_unique_id;
	}

	public void setGlobal_unique_id(String global_unique_id) {
		this.global_unique_id = global_unique_id;
	}

	public String getPayee_participant_code() {
		return payee_participant_code;
	}

	public void setPayee_participant_code(String payee_participant_code) {
		this.payee_participant_code = payee_participant_code;
	}

	public String getCustomer_acct_no() {
		return customer_acct_no;
	}

	public void setCustomer_acct_no(String customer_acct_no) {
		this.customer_acct_no = customer_acct_no;
	}

	public String getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(String customer_id) {
		this.customer_id = customer_id;
	}

	public String getCustomer_name() {
		return customer_name;
	}

	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCustomer_mobile() {
		return customer_mobile;
	}

	public void setCustomer_mobile(String customer_mobile) {
		this.customer_mobile = customer_mobile;
	}

	public String getCustomer_transaction_crncy() {
		return customer_transaction_crncy;
	}

	public void setCustomer_transaction_crncy(String customer_transaction_crncy) {
		this.customer_transaction_crncy = customer_transaction_crncy;
	}

	public String getCustomer_transaction_amt() {
		return customer_transaction_amt;
	}

	public void setCustomer_transaction_amt(String customer_transaction_amt) {
		this.customer_transaction_amt = customer_transaction_amt;
	}

	public String getConv_fees_type() {
		return conv_fees_type;
	}

	public void setConv_fees_type(String conv_fees_type) {
		this.conv_fees_type = conv_fees_type;
	}

	public String getCustomer_bill_number() {
		return customer_bill_number;
	}

	public void setCustomer_bill_number(String customer_bill_number) {
		this.customer_bill_number = customer_bill_number;
	}

	public String getCustomer_reference_label() {
		return customer_reference_label;
	}

	public void setCustomer_reference_label(String customer_reference_label) {
		this.customer_reference_label = customer_reference_label;
	}

	public String getCustomer_deviceid() {
		return customer_deviceid;
	}

	public void setCustomer_deviceid(String customer_deviceid) {
		this.customer_deviceid = customer_deviceid;
	}

	public String getPurpose_of_tran() {
		return purpose_of_tran;
	}

	public void setPurpose_of_tran(String purpose_of_tran) {
		this.purpose_of_tran = purpose_of_tran;
	}

	public String getAdditional_details() {
		return additional_details;
	}

	public void setAdditional_details(String additional_details) {
		this.additional_details = additional_details;
	}

	public String getCustomer_remarks() {
		return customer_remarks;
	}

	public void setCustomer_remarks(String customer_remarks) {
		this.customer_remarks = customer_remarks;
	}

	public String getCustomer_status() {
		return customer_status;
	}

	public void setCustomer_status(String customer_status) {
		this.customer_status = customer_status;
	}

	public String getMcc() {
		return mcc;
	}

	public void setMcc(String mcc) {
		this.mcc = mcc;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getCountry_code() {
		return country_code;
	}

	public void setCountry_code(String country_code) {
		this.country_code = country_code;
	}

	public String getPostal_code() {
		return postal_code;
	}

	public void setPostal_code(String postal_code) {
		this.postal_code = postal_code;
	}

	public String getMerchant_status() {
		return merchant_status;
	}

	public void setMerchant_status(String merchant_status) {
		this.merchant_status = merchant_status;
	}

	public String getTransaction_status() {
		return transaction_status;
	}

	public void setTransaction_status(String transaction_status) {
		this.transaction_status = transaction_status;
	}

	public String getMerchant_mob_no() {
		return merchant_mob_no;
	}

	public void setMerchant_mob_no(String merchant_mob_no) {
		this.merchant_mob_no = merchant_mob_no;
	}

	public String getMerchant_reference_label() {
		return merchant_reference_label;
	}

	public void setMerchant_reference_label(String merchant_reference_label) {
		this.merchant_reference_label = merchant_reference_label;
	}

	public String getMerchant_deviceid() {
		return merchant_deviceid;
	}

	public void setMerchant_deviceid(String merchant_deviceid) {
		this.merchant_deviceid = merchant_deviceid;
	}

	public String getMerchant_terminal_label() {
		return merchant_terminal_label;
	}

	public void setMerchant_terminal_label(String merchant_terminal_label) {
		this.merchant_terminal_label = merchant_terminal_label;
	}

	public String getMerchant_acct_no() {
		return merchant_acct_no;
	}

	public void setMerchant_acct_no(String merchant_acct_no) {
		this.merchant_acct_no = merchant_acct_no;
	}

	public String getMerchant_id() {
		return merchant_id;
	}

	public void setMerchant_id(String merchant_id) {
		this.merchant_id = merchant_id;
	}

	public String getMerchant_name() {
		return merchant_name;
	}

	public void setMerchant_name(String merchant_name) {
		this.merchant_name = merchant_name;
	}

	public String getEntry_user() {
		return entry_user;
	}

	public void setEntry_user(String entry_user) {
		this.entry_user = entry_user;
	}

	
    public Date getEntry_time() { return entry_time; }
	 
	public void setEntry_time(Date entry_time) { this.entry_time = entry_time; }
	

	public String getModify_user() {
		return modify_user;
	}

	public void setModify_user(String modify_user) {
		this.modify_user = modify_user;
	}

	public Date getModify_time() {
		return modify_time;
	}

	public void setModify_time(Date modify_time) {
		this.modify_time = modify_time;
	}

	public String getVerify_user() {
		return verify_user;
	}

	public void setVerify_user(String verify_user) {
		this.verify_user = verify_user;
	}

	public Date getVerify_time() {
		return verify_time;
	}

	public void setVerify_time(Date verify_time) {
		this.verify_time = verify_time;
	}

	public String getEntity_flg() {
		return entity_flg;
	}

	public void setEntity_flg(String entity_flg) {
		this.entity_flg = entity_flg;
	}

	public String getModify_flg() {
		return modify_flg;
	}

	public void setModify_flg(String modify_flg) {
		this.modify_flg = modify_flg;
	}

	public String getDel_flg() {
		return del_flg;
	}

	public void setDel_flg(String del_flg) {
		this.del_flg = del_flg;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getUnit_id() {
		return unit_id;
	}

	public void setUnit_id(String unit_id) {
		this.unit_id = unit_id;
	}
	
	

	public CustomerTransactionEntity(String payload_format_indicator,
			
			
			@Size(min = 2, max = 2, message = "PointOfInitiationFormat must contains 2 characters") String poi_method,
			@NotBlank(message = "GloballyUniqueIdentifier Required") @Size(max = 11, message = "GloballyUniqueIdentifier should not exceed 11 characters") String global_unique_id,
			@NotBlank(message = "PayeeParticipantCode Required") @Size(min = 8, max = 12, message = "PayeeParticipantCode should not exceed 12 characters") String payee_participant_code,
			String customer_acct_no, String customer_id, String customer_name, String city, String state,
			String country, String phone, String customer_mobile, String customer_transaction_crncy,
			String customer_transaction_amt, String conv_fees_type, String customer_bill_number,
			String customer_reference_label, String customer_deviceid, String purpose_of_tran,
			String additional_details, String customer_remarks, String customer_status,
			@Size(min = 4, max = 4, message = "MerchantCategoryCode should contains 4 characters") String mcc,
			@NotBlank(message = "Currency Required") @Size(min = 3, max = 3, message = "Currency should contains 3 characters") String currency,
			@NotBlank(message = "CountryCode Required") @Size(max = 2, message = "CountryCode should not exceed 2 characters") String country_code,
			String postal_code, String merchant_status, String transaction_status, String merchant_mob_no, String merchant_reference_label,
			String merchant_deviceid, String merchant_terminal_label,
			@Size(max = 38, message = "MerchantAcctNumber should not exceed 38 characters") String merchant_acct_no,
			@NotBlank(message = "Merchant ID Required") @Size(max = 15, message = "Merchant ID should not exceed 15 characters") String merchant_id,
			@NotBlank(message = "MerchantName Required") @Size(max = 25, message = "MerchantName should not exceed 25 characters") String merchant_name,
			String entry_user, Date entry_time, String modify_user, Date modify_time, String verify_user,
			Date verify_time, String entity_flg, String modify_flg, String del_flg, String user_id, String unit_id) {
		
		
		super();
		this.payload_format_indicator = payload_format_indicator;
		this.poi_method = poi_method;
		this.global_unique_id = global_unique_id;
		this.payee_participant_code = payee_participant_code;
		this.customer_acct_no = customer_acct_no;
		this.customer_id = customer_id;
		this.customer_name = customer_name;
		this.city = city;
		this.state = state;
		this.country = country;
		this.phone = phone;
		this.customer_mobile = customer_mobile;
		this.customer_transaction_crncy = customer_transaction_crncy;
		this.customer_transaction_amt = customer_transaction_amt;
		this.conv_fees_type = conv_fees_type;
		this.customer_bill_number = customer_bill_number;
		this.customer_reference_label = customer_reference_label;
		this.customer_deviceid = customer_deviceid;
		this.purpose_of_tran = purpose_of_tran;
		this.additional_details = additional_details;
		this.customer_remarks = customer_remarks;
		this.customer_status = customer_status;
		this.mcc = mcc;
		this.currency = currency;
		this.country_code = country_code;
		this.postal_code = postal_code;
		this.merchant_status = merchant_status;
		this.transaction_status = transaction_status;
		this.merchant_mob_no = merchant_mob_no;
		this.merchant_reference_label = merchant_reference_label;
		this.merchant_deviceid = merchant_deviceid;
		this.merchant_terminal_label = merchant_terminal_label;
		this.merchant_acct_no = merchant_acct_no;
		this.merchant_id = merchant_id;
		this.merchant_name = merchant_name;
		this.entry_user = entry_user;
		this.entry_time = entry_time;
		this.modify_user = modify_user;
		this.modify_time = modify_time;
		this.verify_user = verify_user;
		this.verify_time = verify_time;
		this.entity_flg = entity_flg;
		this.modify_flg = modify_flg;
		this.del_flg = del_flg;
		this.user_id = user_id;
		this.unit_id = unit_id;
	}

	public CustomerTransactionEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "CustomerTransactionEntity [payload_format_indicator=" + payload_format_indicator + ", poi_method="
				+ poi_method + ", global_unique_id=" + global_unique_id + ", payee_participant_code="
				+ payee_participant_code + ", customer_acct_no=" + customer_acct_no + ", customer_id=" + customer_id
				+ ", customer_name=" + customer_name + ", city=" + city + ", state=" + state + ", country=" + country
				+ ", phone=" + phone + ", customer_mobile=" + customer_mobile + ", customer_transaction_crncy="
				+ customer_transaction_crncy + ", customer_transaction_amt=" + customer_transaction_amt
				+ ", conv_fees_type=" + conv_fees_type + ", customer_bill_number=" + customer_bill_number
				+ ", customer_reference_label=" + customer_reference_label + ", customer_deviceid=" + customer_deviceid
				+ ", purpose_of_tran=" + purpose_of_tran + ", additional_details=" + additional_details
				+ ", customer_remarks=" + customer_remarks + ", customer_status=" + customer_status + ", mcc=" + mcc
				+ ", currency=" + currency + ", country_code=" + country_code + ", postal_code=" + postal_code
				+ ", merchant_status=" + merchant_status + ", transaction_status=" + transaction_status + ", merchant_mob_no=" + merchant_mob_no
				+ ", merchant_reference_label=" + merchant_reference_label + ", merchant_deviceid=" + merchant_deviceid
				+ ", merchant_terminal_label=" + merchant_terminal_label + ", merchant_acct_no=" + merchant_acct_no
				+ ", merchant_id=" + merchant_id + ", merchant_name=" + merchant_name + ", entry_user=" + entry_user
				+ ", entry_time=" + entry_time + ", modify_user=" + modify_user + ", modify_time=" + modify_time
				+ ", verify_user=" + verify_user + ", verify_time=" + verify_time + ", entity_flg=" + entity_flg
				+ ", modify_flg=" + modify_flg + ", del_flg=" + del_flg + ", user_id=" + user_id + ", unit_id="
				+ unit_id + "]";
	}

}
