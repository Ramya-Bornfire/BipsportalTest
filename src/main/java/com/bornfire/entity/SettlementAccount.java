package com.bornfire.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "BIPS_SETTL_ACCTS")
public class SettlementAccount implements Serializable {

	@Id
	private String acc_code;
	private String mir_acct;
	private String our_acct;
	private String acct_name;
	private BigDecimal acct_limit;
	private BigDecimal acct_debit_cap;
	private String acct_chk_flg;
	private String acct_criteria;
	private String acct_remarks;
	private String acct_type;
	private String category;
	private String account_number;
	private String name;
	private String crncy;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date entry_time;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date modify_time;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date verify_time;
	private String entry_user;
	private String modify_user;
	private String verify_user;
	private String del_flg;
	private String entity_flg;
	private String modify_flg;
	private BigDecimal acct_bal;
	private BigDecimal not_bal;
	private Date acct_bal_time;
	private BigDecimal prev_acct_bal;
	
	private String tran_code;
	private String tran_type;
	

	public String getMir_acct() {
		return mir_acct;
	}

	public String getOur_acct() {
		return our_acct;
	}

	public String getAcct_name() {
		return acct_name;
	}

	public BigDecimal getAcct_limit() {
		return acct_limit;
	}

	public BigDecimal getAcct_debit_cap() {
		return acct_debit_cap;
	}

	public String getAcct_chk_flg() {
		return acct_chk_flg;
	}

	public String getAcct_criteria() {
		return acct_criteria;
	}

	public String getAcct_remarks() {
		return acct_remarks;
	}

	public String getAcct_type() {
		return acct_type;
	}

	public String getCategory() {
		return category;
	}

	public String getAccount_number() {
		return account_number;
	}

	public String getName() {
		return name;
	}

	public String getCrncy() {
		return crncy;
	}

	public Date getEntry_time() {
		return entry_time;
	}

	public Date getModify_time() {
		return modify_time;
	}

	public Date getVerify_time() {
		return verify_time;
	}

	public String getEntry_user() {
		return entry_user;
	}

	public String getModify_user() {
		return modify_user;
	}

	public String getVerify_user() {
		return verify_user;
	}

	public String getDel_flg() {
		return del_flg;
	}

	public String getEntity_flg() {
		return entity_flg;
	}

	public String getModify_flg() {
		return modify_flg;
	}

	public void setMir_acct(String mir_acct) {
		this.mir_acct = mir_acct;
	}

	public void setOur_acct(String our_acct) {
		this.our_acct = our_acct;
	}

	public void setAcct_name(String acct_name) {
		this.acct_name = acct_name;
	}

	public void setAcct_limit(BigDecimal acct_limit) {
		this.acct_limit = acct_limit;
	}

	public void setAcct_debit_cap(BigDecimal acct_debit_cap) {
		this.acct_debit_cap = acct_debit_cap;
	}

	public void setAcct_chk_flg(String acct_chk_flg) {
		this.acct_chk_flg = acct_chk_flg;
	}

	public void setAcct_criteria(String acct_criteria) {
		this.acct_criteria = acct_criteria;
	}

	public void setAcct_remarks(String acct_remarks) {
		this.acct_remarks = acct_remarks;
	}

	public void setAcct_type(String acct_type) {
		this.acct_type = acct_type;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public void setAccount_number(String account_number) {
		this.account_number = account_number;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setCrncy(String crncy) {
		this.crncy = crncy;
	}

	public void setEntry_time(Date entry_time) {
		this.entry_time = entry_time;
	}

	public void setModify_time(Date modify_time) {
		this.modify_time = modify_time;
	}

	public void setVerify_time(Date verify_time) {
		this.verify_time = verify_time;
	}

	public void setEntry_user(String entry_user) {
		this.entry_user = entry_user;
	}

	public void setModify_user(String modify_user) {
		this.modify_user = modify_user;
	}

	public void setVerify_user(String verify_user) {
		this.verify_user = verify_user;
	}

	public void setDel_flg(String del_flg) {
		this.del_flg = del_flg;
	}

	public void setEntity_flg(String entity_flg) {
		this.entity_flg = entity_flg;
	}

	public void setModify_flg(String modify_flg) {
		this.modify_flg = modify_flg;
	}

	public String getAcc_code() {
		return acc_code;
	}

	public void setAcc_code(String acc_code) {
		this.acc_code = acc_code;
	}
	
	

	public BigDecimal getAcct_bal() {
		return acct_bal;
	}

	public void setAcct_bal(BigDecimal acct_bal) {
		this.acct_bal = acct_bal;
	}

	public BigDecimal getNot_bal() {
		return not_bal;
	}

	public void setNot_bal(BigDecimal not_bal) {
		this.not_bal = not_bal;
	}
	
	
	

	public Date getAcct_bal_time() {
		return acct_bal_time;
	}

	public void setAcct_bal_time(Date acct_bal_time) {
		this.acct_bal_time = acct_bal_time;
	}
	
	

	

	public String getTran_code() {
		return tran_code;
	}

	public void setTran_code(String tran_code) {
		this.tran_code = tran_code;
	}

	public String getTran_type() {
		return tran_type;
	}

	public void setTran_type(String tran_type) {
		this.tran_type = tran_type;
	}

	public BigDecimal getPrev_acct_bal() {
		return prev_acct_bal;
	}

	public void setPrev_acct_bal(BigDecimal prev_acct_bal) {
		this.prev_acct_bal = prev_acct_bal;
	}

	public SettlementAccount(String mir_acct, String our_acct, String acct_name, BigDecimal acct_limit,
			BigDecimal acct_debit_cap, String acct_chk_flg, String acct_criteria, String acct_remarks, String acct_type,
			String category, String account_number, String name, String crncy, Date entry_time, Date modify_time,
			Date verify_time, String entry_user, String modify_user, String verify_user, String del_flg,
			String entity_flg, String modify_flg, String acc_code) {
		super();
		this.mir_acct = mir_acct;
		this.our_acct = our_acct;
		this.acct_name = acct_name;
		this.acct_limit = acct_limit;
		this.acct_debit_cap = acct_debit_cap;
		this.acct_chk_flg = acct_chk_flg;
		this.acct_criteria = acct_criteria;
		this.acct_remarks = acct_remarks;
		this.acct_type = acct_type;
		this.category = category;
		this.account_number = account_number;
		this.name = name;
		this.crncy = crncy;
		this.entry_time = entry_time;
		this.modify_time = modify_time;
		this.verify_time = verify_time;
		this.entry_user = entry_user;
		this.modify_user = modify_user;
		this.verify_user = verify_user;
		this.del_flg = del_flg;
		this.entity_flg = entity_flg;
		this.modify_flg = modify_flg;
		this.acc_code = acc_code;
	}

	public SettlementAccount() {
		super();
		// TODO Auto-generated constructor stub
	}

}
