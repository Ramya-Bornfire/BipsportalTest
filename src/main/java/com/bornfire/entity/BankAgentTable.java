package com.bornfire.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="BIPS_OTHER_BANK_AGENT_TABLE")
public class BankAgentTable {
	@Id
	private String bank_code;
	private String bank_name;
	private String bank_agent;
	private String bank_agent_account;
	private String entry_user;
	private Date entry_time;
	private String modify_user;
	private Date modify_time;
	private String del_user;
	private Date del_time;
	private String del_flg;
	private String entity_cre_flg;
	private String disable_flg;
	private String agent_type;
	public BankAgentTable() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public BankAgentTable(String bank_code, String bank_name, String bank_agent, String bank_agent_account,
			String entry_user, Date entry_time, String modify_user, Date modify_time, String del_user, Date del_time,
			String del_flg, String entity_cre_flg, String disable_flg, String agent_type) {
		super();
		this.bank_code = bank_code;
		this.bank_name = bank_name;
		this.bank_agent = bank_agent;
		this.bank_agent_account = bank_agent_account;
		this.entry_user = entry_user;
		this.entry_time = entry_time;
		this.modify_user = modify_user;
		this.modify_time = modify_time;
		this.del_user = del_user;
		this.del_time = del_time;
		this.del_flg = del_flg;
		this.entity_cre_flg = entity_cre_flg;
		this.disable_flg = disable_flg;
		this.agent_type = agent_type;
	}

	public String getBank_code() {
		return bank_code;
	}
	public void setBank_code(String bank_code) {
		this.bank_code = bank_code;
	}
	public String getBank_name() {
		return bank_name;
	}
	public void setBank_name(String bank_name) {
		this.bank_name = bank_name;
	}
	public String getBank_agent() {
		return bank_agent;
	}
	public void setBank_agent(String bank_agent) {
		this.bank_agent = bank_agent;
	}
	public String getBank_agent_account() {
		return bank_agent_account;
	}
	public void setBank_agent_account(String bank_agent_account) {
		this.bank_agent_account = bank_agent_account;
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
	public String getDel_user() {
		return del_user;
	}
	public void setDel_user(String del_user) {
		this.del_user = del_user;
	}
	public Date getDel_time() {
		return del_time;
	}
	public void setDel_time(Date del_time) {
		this.del_time = del_time;
	}
	public String getDel_flg() {
		return del_flg;
	}
	public void setDel_flg(String del_flg) {
		this.del_flg = del_flg;
	}
	public String getEntity_cre_flg() {
		return entity_cre_flg;
	}
	public void setEntity_cre_flg(String entity_cre_flg) {
		this.entity_cre_flg = entity_cre_flg;
	}
	public String getDisable_flg() {
		return disable_flg;
	}
	public void setDisable_flg(String disable_flg) {
		this.disable_flg = disable_flg;
	}
	public String getAgent_type() {
		return agent_type;
	}
	public void setAgent_type(String agent_type) {
		this.agent_type = agent_type;
	}
	
	
}
