package com.bornfire.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="BIPS_MERCHANT_QRCODE_GEN_TABLE")
public class MerchantQrGenTable {

	@Id
	
	private String p_id;
	private String	merchant_category_code;
	private String	merchant_name;
	private String	merchant_id;
	private String	merchant_acct_no;
	private String	global_unique_id;
	private String	payload_format_indicator;
	private String	poi_method;
	private String	payee_participant_code;
	private String	address1;
	private String	address2;
	private String	city;
	private String	state;
	private String	country;
	private String	phone;
	private String	mobile;
	private String	website;
	private String	mail_id;
	private String	transaction_crncy;
	private String	transaction_amt;
	private String	tip_or_conv_indicator;
	private String	conv_fees_type;
	private String	value_conv_fees;
	private String	bill_number;
	private String	store_label;
	private String	loyalty_number;
	private String	reference_label;
	private String	customer_label;
	private String	terminal_label;
	private String	purpose_of_tran;
	private String	additional_details;
	private String	remarks;
	private String	entry_user;
	@DateTimeFormat(pattern =  "dd-MM-yyyy")
	private Date	entry_time;
	private String	modify_user;
	@DateTimeFormat(pattern =  "dd-MM-yyyy")
	private Date	modify_time;
	private String	verify_user;
	@DateTimeFormat(pattern =  "dd-MM-yyyy")
	private Date	verify_time;
	private String	entity_flg;
	private String	modify_flg;
	private String	del_flg;
	private String	qr_code_ref;
	private String	zip_code;
	private String psu_device_id;
	private String psu_ip_address;
	private String psu_channel;
	private String status;
	private String reason;

	@Lob
    @Column(name = "qr_code", columnDefinition="BLOB")
    private byte[] qr_code;
	
	private String terminal_id;
	private String user_id;
	private String unit_id;

	public String getP_id() {
		return p_id;
	}

	public void setP_id(String p_id) {
		this.p_id = p_id;
	}

	public String getMerchant_category_code() {
		return merchant_category_code;
	}

	public void setMerchant_category_code(String merchant_category_code) {
		this.merchant_category_code = merchant_category_code;
	}

	public String getMerchant_name() {
		return merchant_name;
	}

	public void setMerchant_name(String merchant_name) {
		this.merchant_name = merchant_name;
	}

	public String getMerchant_id() {
		return merchant_id;
	}

	public void setMerchant_id(String merchant_id) {
		this.merchant_id = merchant_id;
	}

	public String getMerchant_acct_no() {
		return merchant_acct_no;
	}

	public void setMerchant_acct_no(String merchant_acct_no) {
		this.merchant_acct_no = merchant_acct_no;
	}

	public String getGlobal_unique_id() {
		return global_unique_id;
	}

	public void setGlobal_unique_id(String global_unique_id) {
		this.global_unique_id = global_unique_id;
	}

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

	public String getPayee_participant_code() {
		return payee_participant_code;
	}

	public void setPayee_participant_code(String payee_participant_code) {
		this.payee_participant_code = payee_participant_code;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
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

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getMail_id() {
		return mail_id;
	}

	public void setMail_id(String mail_id) {
		this.mail_id = mail_id;
	}

	public String getTransaction_crncy() {
		return transaction_crncy;
	}

	public void setTransaction_crncy(String transaction_crncy) {
		this.transaction_crncy = transaction_crncy;
	}

	public String getTransaction_amt() {
		return transaction_amt;
	}

	public void setTransaction_amt(String transaction_amt) {
		this.transaction_amt = transaction_amt;
	}

	public String getTip_or_conv_indicator() {
		return tip_or_conv_indicator;
	}

	public void setTip_or_conv_indicator(String tip_or_conv_indicator) {
		this.tip_or_conv_indicator = tip_or_conv_indicator;
	}

	public String getConv_fees_type() {
		return conv_fees_type;
	}

	public void setConv_fees_type(String conv_fees_type) {
		this.conv_fees_type = conv_fees_type;
	}

	public String getValue_conv_fees() {
		return value_conv_fees;
	}

	public void setValue_conv_fees(String value_conv_fees) {
		this.value_conv_fees = value_conv_fees;
	}

	public String getBill_number() {
		return bill_number;
	}

	public void setBill_number(String bill_number) {
		this.bill_number = bill_number;
	}

	public String getStore_label() {
		return store_label;
	}

	public void setStore_label(String store_label) {
		this.store_label = store_label;
	}

	public String getLoyalty_number() {
		return loyalty_number;
	}

	public void setLoyalty_number(String loyalty_number) {
		this.loyalty_number = loyalty_number;
	}

	public String getReference_label() {
		return reference_label;
	}

	public void setReference_label(String reference_label) {
		this.reference_label = reference_label;
	}

	public String getCustomer_label() {
		return customer_label;
	}

	public void setCustomer_label(String customer_label) {
		this.customer_label = customer_label;
	}

	public String getTerminal_label() {
		return terminal_label;
	}

	public void setTerminal_label(String terminal_label) {
		this.terminal_label = terminal_label;
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

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getEntry_user() {
		return entry_user;
	}

	public void setEntry_user(String entry_user) {
		this.entry_user = entry_user;
	}

	public Date getEntry_time() {
		return entry_time;
	}

	public void setEntry_time(Date entry_time) {
		this.entry_time = entry_time;
	}

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

	public String getQr_code_ref() {
		return qr_code_ref;
	}

	public void setQr_code_ref(String qr_code_ref) {
		this.qr_code_ref = qr_code_ref;
	}

	public String getZip_code() {
		return zip_code;
	}

	public void setZip_code(String zip_code) {
		this.zip_code = zip_code;
	}

	public String getPsu_device_id() {
		return psu_device_id;
	}

	public void setPsu_device_id(String psu_device_id) {
		this.psu_device_id = psu_device_id;
	}

	public String getPsu_ip_address() {
		return psu_ip_address;
	}

	public void setPsu_ip_address(String psu_ip_address) {
		this.psu_ip_address = psu_ip_address;
	}

	public String getPsu_channel() {
		return psu_channel;
	}

	public void setPsu_channel(String psu_channel) {
		this.psu_channel = psu_channel;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public byte[] getQr_code() {
		return qr_code;
	}

	public void setQr_code(byte[] qr_code) {
		this.qr_code = qr_code;
	}

	public MerchantQrGenTable() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getTerminal_id() {
		return terminal_id;
	}

	public void setTerminal_id(String terminal_id) {
		this.terminal_id = terminal_id;
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
	
}


