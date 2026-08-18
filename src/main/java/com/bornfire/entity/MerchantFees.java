package com.bornfire.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="BIPS_MERCHANT_FEES_TABLE")
public class MerchantFees {
	private String type;
	private String fee_com_code;
	private String description;
	private String periodicity;
	@Id
	private String account_no;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date start_date;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date end_date;
	@Column(name="status")
	private String status1;
	private String script_name;
	private String entry_user;
	private Date entry_time;
	private String modify_user;
	private Date modify_time;
	private String verify_user;
	private Date verify_time;
	private String entity_flg;
	private String modify_flg;
	private String del_flg;
	
	public MerchantFees() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	public MerchantFees(String type, String fee_com_code, String description, String periodicity, String account_no,
			Date start_date, Date end_date, String status1, String script_name, String entry_user, Date entry_time,
			String modify_user, Date modify_time, String verify_user, Date verify_time, String entity_flg,
			String modify_flg, String del_flg) {
		super();
		this.type = type;
		this.fee_com_code = fee_com_code;
		this.description = description;
		this.periodicity = periodicity;
		this.account_no = account_no;
		this.start_date = start_date;
		this.end_date = end_date;
		this.status1 = status1;
		this.script_name = script_name;
		this.entry_user = entry_user;
		this.entry_time = entry_time;
		this.modify_user = modify_user;
		this.modify_time = modify_time;
		this.verify_user = verify_user;
		this.verify_time = verify_time;
		this.entity_flg = entity_flg;
		this.modify_flg = modify_flg;
		this.del_flg = del_flg;
	}


	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getFee_com_code() {
		return fee_com_code;
	}

	public void setFee_com_code(String fee_com_code) {
		this.fee_com_code = fee_com_code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPeriodicity() {
		return periodicity;
	}

	public void setPeriodicity(String periodicity) {
		this.periodicity = periodicity;
	}

	public String getAccount_no() {
		return account_no;
	}

	public void setAccount_no(String account_no) {
		this.account_no = account_no;
	}

	public Date getStart_date() {
		return start_date;
	}

	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}

	public Date getEnd_date() {
		return end_date;
	}

	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}

	

	public String getStatus1() {
		return status1;
	}


	public void setStatus1(String status1) {
		this.status1 = status1;
	}


	public String getScript_name() {
		return script_name;
	}

	public void setScript_name(String script_name) {
		this.script_name = script_name;
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


	@Override
	public String toString() {
		return "MerchantFees [type=" + type + ", fee_com_code=" + fee_com_code + ", description=" + description
				+ ", periodicity=" + periodicity + ", account_no=" + account_no + ", start_date=" + start_date
				+ ", end_date=" + end_date + ", status1=" + status1 + ", script_name=" + script_name + ", entry_user="
				+ entry_user + ", entry_time=" + entry_time + ", modify_user=" + modify_user + ", modify_time="
				+ modify_time + ", verify_user=" + verify_user + ", verify_time=" + verify_time + ", entity_flg="
				+ entity_flg + ", modify_flg=" + modify_flg + ", del_flg=" + del_flg + "]";
	}
	
	
}
