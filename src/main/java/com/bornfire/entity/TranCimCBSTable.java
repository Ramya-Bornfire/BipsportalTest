package com.bornfire.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="BIPS_TRAN_CIM_CBS_TABLE")
public class TranCimCBSTable implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String sequence_unique_id;
	
	@Id
	private String request_uuid;
	private String channel_id;
	private String service_request_version;
	private String service_request_id;
	private Date message_date_time;
	private String tran_no;
	private String init_channel;
	private String init_tran_no;
	private String post_to_cbs;
	private String tran_type;
	private String isreversal;
	private String tran_no_from_cbs;
	private String customer_name;
	private String from_account_no;
	private String to_account_no;
	private BigDecimal tran_amt;
	@JsonFormat(pattern="dd-MM-yyyy")
	private Date tran_date;
	private String tran_currency;
	private String tran_particular_code;
	private String debit_remarks;
	private String credit_remarks;
	private String resv_field_1;
	private String resv_field_2;
	private String status;
	private String status_code;
	private String message;
	private String entry_user;
	private Date entry_time;
	private String entity_cre_flg;
	private Date value_date;
	private String settl_acct_type;
	private String tran_charge_type;
	private String init_sub_tran_no;
	private String error_code;
	private String error_msg;
	private String ips_master_ref_id;
	private Date message_res_time;
	private String remitterbank;
	private String remitterbankcode;
	private String remitterswiftcode;
	private String beneficiarybank;
	private String beneficiarybankcode;
	private String beneficiaryswiftcode;
	private String transactionnotocbs;



	public TranCimCBSTable() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getSequence_unique_id() {
		return sequence_unique_id;
	}
	public void setSequence_unique_id(String sequence_unique_id) {
		this.sequence_unique_id = sequence_unique_id;
	}
	public String getRequest_uuid() {
		return request_uuid;
	}
	public void setRequest_uuid(String request_uuid) {
		this.request_uuid = request_uuid;
	}
	public String getChannel_id() {
		return channel_id;
	}
	public void setChannel_id(String channel_id) {
		this.channel_id = channel_id;
	}
	public String getService_request_version() {
		return service_request_version;
	}
	public void setService_request_version(String service_request_version) {
		this.service_request_version = service_request_version;
	}
	public String getService_request_id() {
		return service_request_id;
	}
	public void setService_request_id(String service_request_id) {
		this.service_request_id = service_request_id;
	}
	public Date getMessage_date_time() {
		return message_date_time;
	}
	public void setMessage_date_time(Date message_date_time) {
		this.message_date_time = message_date_time;
	}
	public String getTran_no() {
		return tran_no;
	}
	public void setTran_no(String tran_no) {
		this.tran_no = tran_no;
	}
	public String getInit_channel() {
		return init_channel;
	}
	public void setInit_channel(String init_channel) {
		this.init_channel = init_channel;
	}
	public String getInit_tran_no() {
		return init_tran_no;
	}
	public void setInit_tran_no(String init_tran_no) {
		this.init_tran_no = init_tran_no;
	}
	public String getPost_to_cbs() {
		return post_to_cbs;
	}
	public void setPost_to_cbs(String post_to_cbs) {
		this.post_to_cbs = post_to_cbs;
	}
	public String getTran_type() {
		return tran_type;
	}
	public void setTran_type(String tran_type) {
		this.tran_type = tran_type;
	}
	public String getIsreversal() {
		return isreversal;
	}
	public void setIsreversal(String isreversal) {
		this.isreversal = isreversal;
	}
	public String getTran_no_from_cbs() {
		return tran_no_from_cbs;
	}
	public void setTran_no_from_cbs(String tran_no_from_cbs) {
		this.tran_no_from_cbs = tran_no_from_cbs;
	}
	public String getCustomer_name() {
		return customer_name;
	}
	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}
	public String getFrom_account_no() {
		return from_account_no;
	}
	public void setFrom_account_no(String from_account_no) {
		this.from_account_no = from_account_no;
	}
	public String getTo_account_no() {
		return to_account_no;
	}
	public void setTo_account_no(String to_account_no) {
		this.to_account_no = to_account_no;
	}
	public BigDecimal getTran_amt() {
		return tran_amt;
	}
	public void setTran_amt(BigDecimal tran_amt) {
		this.tran_amt = tran_amt;
	}
	public Date getTran_date() {
		return tran_date;
	}
	public void setTran_date(Date tran_date) {
		this.tran_date = tran_date;
	}
	public String getTran_currency() {
		return tran_currency;
	}
	public void setTran_currency(String tran_currency) {
		this.tran_currency = tran_currency;
	}
	public String getTran_particular_code() {
		return tran_particular_code;
	}
	public void setTran_particular_code(String tran_particular_code) {
		this.tran_particular_code = tran_particular_code;
	}
	public String getDebit_remarks() {
		return debit_remarks;
	}
	public void setDebit_remarks(String debit_remarks) {
		this.debit_remarks = debit_remarks;
	}
	public String getCredit_remarks() {
		return credit_remarks;
	}
	public void setCredit_remarks(String credit_remarks) {
		this.credit_remarks = credit_remarks;
	}
	
	public String getResv_field_1() {
		return resv_field_1;
	}
	public void setResv_field_1(String resv_field_1) {
		this.resv_field_1 = resv_field_1;
	}
	public String getResv_field_2() {
		return resv_field_2;
	}
	public void setResv_field_2(String resv_field_2) {
		this.resv_field_2 = resv_field_2;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getStatus_code() {
		return status_code;
	}
	public void setStatus_code(String status_code) {
		this.status_code = status_code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
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
	public String getEntity_cre_flg() {
		return entity_cre_flg;
	}
	public void setEntity_cre_flg(String entity_cre_flg) {
		this.entity_cre_flg = entity_cre_flg;
	}
	public Date getValue_date() {
		return value_date;
	}
	public void setValue_date(Date value_date) {
		this.value_date = value_date;
	}
	public String getTran_charge_type() {
		return tran_charge_type;
	}
	public void setTran_charge_type(String tran_charge_type) {
		this.tran_charge_type = tran_charge_type;
	}
	public String getSettl_acct_type() {
		return settl_acct_type;
	}
	public void setSettl_acct_type(String settl_acct_type) {
		this.settl_acct_type = settl_acct_type;
	}
	public String getInit_sub_tran_no() {
		return init_sub_tran_no;
	}
	public void setInit_sub_tran_no(String init_sub_tran_no) {
		this.init_sub_tran_no = init_sub_tran_no;
	}
	public String getError_code() {
		return error_code;
	}
	public void setError_code(String error_code) {
		this.error_code = error_code;
	}
	public String getError_msg() {
		return error_msg;
	}
	public void setError_msg(String error_msg) {
		this.error_msg = error_msg;
	}
	public String getIps_master_ref_id() {
		return ips_master_ref_id;
	}
	public void setIps_master_ref_id(String ips_master_ref_id) {
		this.ips_master_ref_id = ips_master_ref_id;
	}
	public Date getMessage_res_time() {
		return message_res_time;
	}
	public void setMessage_res_time(Date message_res_time) {
		this.message_res_time = message_res_time;
	}
	public String getRemitterbank() {
		return remitterbank;
	}
	public void setRemitterbank(String remitterbank) {
		this.remitterbank = remitterbank;
	}
	public String getRemitterbankcode() {
		return remitterbankcode;
	}
	public void setRemitterbankcode(String remitterbankcode) {
		this.remitterbankcode = remitterbankcode;
	}
	public String getRemitterswiftcode() {
		return remitterswiftcode;
	}
	public void setRemitterswiftcode(String remitterswiftcode) {
		this.remitterswiftcode = remitterswiftcode;
	}
	public String getBeneficiarybank() {
		return beneficiarybank;
	}
	public void setBeneficiarybank(String beneficiarybank) {
		this.beneficiarybank = beneficiarybank;
	}
	public String getBeneficiarybankcode() {
		return beneficiarybankcode;
	}
	public void setBeneficiarybankcode(String beneficiarybankcode) {
		this.beneficiarybankcode = beneficiarybankcode;
	}
	public String getBeneficiaryswiftcode() {
		return beneficiaryswiftcode;
	}
	public void setBeneficiaryswiftcode(String beneficiaryswiftcode) {
		this.beneficiaryswiftcode = beneficiaryswiftcode;

	}
	public String getTransactionnotocbs() {
		return transactionnotocbs;
	}
	public void setTransactionnotocbs(String transactionnotocbs) {
		this.transactionnotocbs = transactionnotocbs;
	}
	public TranCimCBSTable(String sequence_unique_id, String request_uuid, String channel_id,
			String service_request_version, String service_request_id, Date message_date_time, String tran_no,
			String init_channel, String init_tran_no, String post_to_cbs, String tran_type, String isreversal,
			String tran_no_from_cbs, String customer_name, String from_account_no, String to_account_no,
			BigDecimal tran_amt, Date tran_date, String tran_currency, String tran_particular_code,
			String debit_remarks, String credit_remarks, String resv_field_1, String resv_field_2, String status,
			String status_code, String message, String entry_user, Date entry_time, String entity_cre_flg,
			Date value_date, String settl_acct_type, String tran_charge_type, String init_sub_tran_no,
			String error_code, String error_msg, String ips_master_ref_id, Date message_res_time, String remitterbank,
			String remitterbankcode, String remitterswiftcode, String beneficiarybank, String beneficiarybankcode,
			String beneficiaryswiftcode, String transactionnotocbs) {
		super();
		this.sequence_unique_id = sequence_unique_id;
		this.request_uuid = request_uuid;
		this.channel_id = channel_id;
		this.service_request_version = service_request_version;
		this.service_request_id = service_request_id;
		this.message_date_time = message_date_time;
		this.tran_no = tran_no;
		this.init_channel = init_channel;
		this.init_tran_no = init_tran_no;
		this.post_to_cbs = post_to_cbs;
		this.tran_type = tran_type;
		this.isreversal = isreversal;
		this.tran_no_from_cbs = tran_no_from_cbs;
		this.customer_name = customer_name;
		this.from_account_no = from_account_no;
		this.to_account_no = to_account_no;
		this.tran_amt = tran_amt;
		this.tran_date = tran_date;
		this.tran_currency = tran_currency;
		this.tran_particular_code = tran_particular_code;
		this.debit_remarks = debit_remarks;
		this.credit_remarks = credit_remarks;
		this.resv_field_1 = resv_field_1;
		this.resv_field_2 = resv_field_2;
		this.status = status;
		this.status_code = status_code;
		this.message = message;
		this.entry_user = entry_user;
		this.entry_time = entry_time;
		this.entity_cre_flg = entity_cre_flg;
		this.value_date = value_date;
		this.settl_acct_type = settl_acct_type;
		this.tran_charge_type = tran_charge_type;
		this.init_sub_tran_no = init_sub_tran_no;
		this.error_code = error_code;
		this.error_msg = error_msg;
		this.ips_master_ref_id = ips_master_ref_id;
		this.message_res_time = message_res_time;
		this.remitterbank = remitterbank;
		this.remitterbankcode = remitterbankcode;
		this.remitterswiftcode = remitterswiftcode;
		this.beneficiarybank = beneficiarybank;
		this.beneficiarybankcode = beneficiarybankcode;
		this.beneficiaryswiftcode = beneficiaryswiftcode;
		this.transactionnotocbs = transactionnotocbs;
	}
	
	
	
	
	}
