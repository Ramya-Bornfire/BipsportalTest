package com.bornfire.entity;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class FeesAndChargesEntity {
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date tran_date;
	private String sequence_unique_id;
	private String initiator_bank;
	private String tran_currency;
	private BigDecimal tran_amount;
	private String tran_status;
	private String tran_rmks;
	private String ipsx_account;
	private String cim_account;
	private String ipsx_account_name;
	private String cim_account_name;
	private String part_tran_type;
	private String merchant_id;
	private String reversal_remarks;
	private String merchant_bill_number;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date reversal_date;
	private BigDecimal reversal_amount;
	private String auth_user;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date auth_time;
	private String charge_app_flg;
	private String conv_fee;
	private  String user_id;
	private String user_name;
	
	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public Date getTran_date() {
		return tran_date;
	}

	public void setTran_date(Date tran_date) {
		this.tran_date = tran_date;
	}

	public String getSequence_unique_id() {
		return sequence_unique_id;
	}

	public void setSequence_unique_id(String sequence_unique_id) {
		this.sequence_unique_id = sequence_unique_id;
	}

	public String getInitiator_bank() {
		return initiator_bank;
	}

	public void setInitiator_bank(String initiator_bank) {
		this.initiator_bank = initiator_bank;
	}

	public String getTran_currency() {
		return tran_currency;
	}

	public void setTran_currency(String tran_currency) {
		this.tran_currency = tran_currency;
	}

	public BigDecimal getTran_amount() {
		return tran_amount;
	}

	public void setTran_amount(BigDecimal tran_amount) {
		this.tran_amount = tran_amount;
	}

	public String getTran_status() {
		return tran_status;
	}

	public void setTran_status(String tran_status) {
		this.tran_status = tran_status;
	}

	public String getTran_rmks() {
		return tran_rmks;
	}

	public void setTran_rmks(String tran_rmks) {
		this.tran_rmks = tran_rmks;
	}

	public String getIpsx_account() {
		return ipsx_account;
	}

	public void setIpsx_account(String ipsx_account) {
		this.ipsx_account = ipsx_account;
	}

	public String getCim_account() {
		return cim_account;
	}

	public void setCim_account(String cim_account) {
		this.cim_account = cim_account;
	}

	public String getIpsx_account_name() {
		return ipsx_account_name;
	}

	public void setIpsx_account_name(String ipsx_account_name) {
		this.ipsx_account_name = ipsx_account_name;
	}

	public String getCim_account_name() {
		return cim_account_name;
	}

	public void setCim_account_name(String cim_account_name) {
		this.cim_account_name = cim_account_name;
	}

	public String getPart_tran_type() {
		return part_tran_type;
	}

	public void setPart_tran_type(String part_tran_type) {
		this.part_tran_type = part_tran_type;
	}

	public String getMerchant_id() {
		return merchant_id;
	}

	public void setMerchant_id(String merchant_id) {
		this.merchant_id = merchant_id;
	}

	public String getReversal_remarks() {
		return reversal_remarks;
	}

	public void setReversal_remarks(String reversal_remarks) {
		this.reversal_remarks = reversal_remarks;
	}

	public String getMerchant_bill_number() {
		return merchant_bill_number;
	}

	public void setMerchant_bill_number(String merchant_bill_number) {
		this.merchant_bill_number = merchant_bill_number;
	}

	public Date getReversal_date() {
		return reversal_date;
	}

	public void setReversal_date(Date reversal_date) {
		this.reversal_date = reversal_date;
	}

	public BigDecimal getReversal_amount() {
		return reversal_amount;
	}

	public void setReversal_amount(BigDecimal reversal_amount) {
		this.reversal_amount = reversal_amount;
	}

	public String getAuth_user() {
		return auth_user;
	}

	public void setAuth_user(String auth_user) {
		this.auth_user = auth_user;
	}

	public Date getAuth_time() {
		return auth_time;
	}

	public void setAuth_time(Date auth_time) {
		this.auth_time = auth_time;
	}

	public String getCharge_app_flg() {
		return charge_app_flg;
	}

	public void setCharge_app_flg(String charge_app_flg) {
		this.charge_app_flg = charge_app_flg;
	}

	public String getConv_fee() {
		return conv_fee;
	}

	public void setConv_fee(String conv_fee) {
		this.conv_fee = conv_fee;
	}

	public FeesAndChargesEntity(Date tran_date, String sequence_unique_id, String initiator_bank, String tran_currency,
			BigDecimal tran_amount, String tran_status, String tran_rmks, String ipsx_account, String cim_account,
			String ipsx_account_name, String cim_account_name, String part_tran_type, String merchant_id,
			String reversal_remarks, String merchant_bill_number, Date reversal_date, BigDecimal reversal_amount,
			String auth_user, Date auth_time, String charge_app_flg, String conv_fee,String user_id, String user_name) {
		super();
		this.tran_date = tran_date;
		this.sequence_unique_id = sequence_unique_id;
		this.initiator_bank = initiator_bank;
		this.tran_currency = tran_currency;
		this.tran_amount = tran_amount;
		this.tran_status = tran_status;
		this.tran_rmks = tran_rmks;
		this.ipsx_account = ipsx_account;
		this.cim_account = cim_account;
		this.ipsx_account_name = ipsx_account_name;
		this.cim_account_name = cim_account_name;
		this.part_tran_type = part_tran_type;
		this.merchant_id = merchant_id;
		this.reversal_remarks = reversal_remarks;
		this.merchant_bill_number = merchant_bill_number;
		this.reversal_date = reversal_date;
		this.reversal_amount = reversal_amount;
		this.auth_user = auth_user;
		this.auth_time = auth_time;
		this.charge_app_flg = charge_app_flg;
		this.conv_fee = conv_fee;
		this.user_id=user_id;
		this.user_name=user_name;
	}

	public FeesAndChargesEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FeesAndChargesEntity(Object object1, Object object2, Object object3, Object object4, Object object5,
			Object object6, Object object7, Object object8, Object object9, Object object10, Object object11,
			Object object12, Object object13, Object object14, Object object15, Object object16, Object object17,
			Object object18, Object object19, Object object20, Object object21,Object object22,Object object23) {
		super();
		this.tran_date = (Date) object1;
		this.sequence_unique_id = (String) object2;
		this.initiator_bank = (String) object3;
		this.tran_currency = (String) object4;
		this.tran_amount = (BigDecimal) object5;
		this.tran_status = (String) object6;
		this.tran_rmks = (String) object7;
		this.ipsx_account = (String) object8;
		this.cim_account = (String) object9;
		this.ipsx_account_name = (String) object10;
		this.cim_account_name = (String) object11;
		this.part_tran_type = (String) object12;
		this.merchant_id = (String) object13;
		this.reversal_remarks = (String) object14;
		this.merchant_bill_number = (String) object15;
		this.reversal_date = (Date) object16;
		this.reversal_amount = (BigDecimal) object17;
		this.auth_user = (String) object18;
		this.auth_time = (Date) object19;
		this.charge_app_flg = (String) object20;
		this.conv_fee = (String) object21;
		this.user_id=(String) object22;
		this.user_name=(String) object23;
	}

}
