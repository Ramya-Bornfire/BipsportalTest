package com.bornfire.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="BIPS_TRANSACTION_MONITORING_TABLE")


public class TransactionMonitor implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Column(name="cim_message_id")
	private String bob_message_id;
	private String ipsx_message_id;
	@Id
	private String sequence_unique_id;
	private String tran_audit_number;
	private String	cbs_status;
	private String cbs_status_error;
	private Date cbs_response_time;
	private String ipsx_status;
	private String ipsx_status_error;
	private Date ipsx_response_time;
	private String tran_currency;
	private String tran_status;
	private String master_ref_id;
	private String end_end_id;
	
	
	private String msg_type;
	
	private Date	auth_time;
	private String	auth_user;
	private String	del_flg;
	private String	entity_cre_flg;
	private Date	entry_time;
	private String	entry_user;
	@Column(name="cim_account")
	private String	bob_account;
	private String	initiator_bank;
	private String	ips_status;
	private String	modify_flg;
	private Date	modify_time;
	private String	modify_user;
	private String	part_tran_type;
	private String	receiver_bank;
	private String  ipsx_account;
	private String	service_name;
	private String	session_id;
	private String	source_system;
	private String	transaction_ref_code;
	private String	transaction_type;
	private BigDecimal	tran_amount;
	private Date	tran_date;
	private String	akg_status;
	private String	akg_desc;
	private Date	akg_time;
	private String	response_status;
	private String	response_desc;
	private Date	response_time;
	private String device_id;
	private String device_ip;
	private String nat_id;
	private String ipsx_status_code;
	@Column(name="cim_account_name")
	private String bob_account_name;
	private String ipsx_account_name;
	
	private String mconnectin;
	private Date mconnectindate;
	
	private String mconnectout;
	private Date mconnectoutdate;
	
	private String cbsin;
	private Date cbsindate;
	
	private String cbsout;
	private Date cbsoutdate;
	
	
	private String cbsreversein;
	private Date cbsreverseindate;
	
	private String cbsreverseout;
	private Date cbsreverseoutdate;
	
	
	private String ipsin;
	private Date ipsindate;
	
	private String ipsout;
	private Date ipsoutdate;
	
	
	private String net_mir;
	private String user_ref;
	private String tran_type_code;
	private String instg_agt;
	private String instd_agt;
	
	private String reg_rep;
	private String rmt_info;
	private String pmt_inf_id;
	private String pmt_mtd;
	private String instr_id;
	private String svc_lvl;
	private String lcl_instrm;
	private String ctgy_purp;
	
	private String dbtr_agt;
	private String dbtr_agt_acc;
	private String cdtr_agt;
	private String cdtr_agt_acc;
	
	private Date value_date;
	
	private String charge_app_flg;
	private String chrg_br;
	private String clr_chennel;
	private String rmt_info_issuer;
	private String rmt_info_nb;
	
	

	@Override
	public String toString() {
		return "TransactionMonitor [bob_message_id=" + bob_message_id + ", ipsx_message_id=" + ipsx_message_id
				+ ", sequence_unique_id=" + sequence_unique_id + ", tran_audit_number=" + tran_audit_number
				+ ", cbs_status=" + cbs_status + ", cbs_status_error=" + cbs_status_error + ", cbs_response_time="
				+ cbs_response_time + ", ipsx_status=" + ipsx_status + ", ipsx_status_error=" + ipsx_status_error
				+ ", ipsx_response_time=" + ipsx_response_time + ", msg_type=" + msg_type + ", auth_time=" + auth_time
				+ ", auth_user=" + auth_user + ", del_flg=" + del_flg + ", entity_cre_flg=" + entity_cre_flg
				+ ", entry_time=" + entry_time + ", entry_user=" + entry_user + ", initiate_account=" + bob_account
				+ ", initiator_bank=" + initiator_bank + ", ips_status=" + ips_status + ", modify_flg=" + modify_flg
				+ ", modify_time=" + modify_time + ", modify_user=" + modify_user + ", part_tran_type=" + part_tran_type
				+ ", receiver_bank=" + receiver_bank + ", recipient_account=" + ipsx_account + ", service_name="
				+ service_name + ", session_id=" + session_id + ", source_system=" + source_system
				+ ", transaction_ref_code=" + transaction_ref_code + ", transaction_type=" + transaction_type
				+ ", tran_amount=" + tran_amount + ", tran_date=" + tran_date + ", akg_status=" + akg_status
				+ ", akg_desc=" + akg_desc + ", akg_time=" + akg_time + ", response_status=" + response_status
				+ ", response_desc=" + response_desc + ", response_time=" + response_time + "]";
	}
	public Date getAuth_time() {
		return auth_time;
	}
	public void setAuth_time(Date auth_time) {
		this.auth_time = auth_time;
	}
	public String getAuth_user() {
		return auth_user;
	}
	public void setAuth_user(String auth_user) {
		this.auth_user = auth_user;
	}
	public String getCbs_status() {
		return cbs_status;
	}
	public void setCbs_status(String cbs_status) {
		this.cbs_status = cbs_status;
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
	public Date getEntry_time() {
		return entry_time;
	}
	public void setEntry_time(Date entry_time) {
		this.entry_time = entry_time;
	}
	public String getEntry_user() {
		return entry_user;
	}
	public void setEntry_user(String entry_user) {
		this.entry_user = entry_user;
	}

	public String getInitiator_bank() {
		return initiator_bank;
	}
	public void setInitiator_bank(String initiator_bank) {
		this.initiator_bank = initiator_bank;
	}
	public String getIps_status() {
		return ips_status;
	}
	public void setIps_status(String ips_status) {
		this.ips_status = ips_status;
	}
	public String getModify_flg() {
		return modify_flg;
	}
	public void setModify_flg(String modify_flg) {
		this.modify_flg = modify_flg;
	}
	public Date getModify_time() {
		return modify_time;
	}
	public void setModify_time(Date modify_time) {
		this.modify_time = modify_time;
	}
	public String getModify_user() {
		return modify_user;
	}
	public void setModify_user(String modify_user) {
		this.modify_user = modify_user;
	}
	public String getPart_tran_type() {
		return part_tran_type;
	}
	public void setPart_tran_type(String part_tran_type) {
		this.part_tran_type = part_tran_type;
	}
	public String getReceiver_bank() {
		return receiver_bank;
	}
	public void setReceiver_bank(String receiver_bank) {
		this.receiver_bank = receiver_bank;
	}
	
	public String getService_name() {
		return service_name;
	}
	public void setService_name(String service_name) {
		this.service_name = service_name;
	}
	public String getSession_id() {
		return session_id;
	}
	public void setSession_id(String session_id) {
		this.session_id = session_id;
	}
	public String getSource_system() {
		return source_system;
	}
	public void setSource_system(String source_system) {
		this.source_system = source_system;
	}
	public String getTransaction_ref_code() {
		return transaction_ref_code;
	}
	public void setTransaction_ref_code(String transaction_ref_code) {
		this.transaction_ref_code = transaction_ref_code;
	}
	public String getTransaction_type() {
		return transaction_type;
	}
	public void setTransaction_type(String transaction_type) {
		this.transaction_type = transaction_type;
	}
	public BigDecimal getTran_amount() {
		return tran_amount;
	}
	public void setTran_amount(BigDecimal tran_amount) {
		this.tran_amount = tran_amount;
	}
	public Date getTran_date() {
		return tran_date;
	}
	public void setTran_date(Date tran_date) {
		this.tran_date = tran_date;
	}
	public String getAkg_status() {
		return akg_status;
	}
	public void setAkg_status(String akg_status) {
		this.akg_status = akg_status;
	}
	public String getAkg_desc() {
		return akg_desc;
	}
	public void setAkg_desc(String akg_desc) {
		this.akg_desc = akg_desc;
	}
	public Date getAkg_time() {
		return akg_time;
	}
	public void setAkg_time(Date akg_time) {
		this.akg_time = akg_time;
	}
	public String getResponse_status() {
		return response_status;
	}
	public void setResponse_status(String response_status) {
		this.response_status = response_status;
	}
	public String getResponse_desc() {
		return response_desc;
	}
	public void setResponse_desc(String response_desc) {
		this.response_desc = response_desc;
	}
	public Date getResponse_time() {
		return response_time;
	}
	public void setResponse_time(Date response_time) {
		this.response_time = response_time;
	}
	public String getTran_audit_number() {
		return tran_audit_number;
	}
	public void setTran_audit_number(String tran_audit_number) {
		this.tran_audit_number = tran_audit_number;
	}
	public String getMsg_type() {
		return msg_type;
	}
	public void setMsg_type(String msg_type) {
		this.msg_type = msg_type;
	}
	
	public Date getCbs_response_time() {
		return cbs_response_time;
	}
	public void setCbs_response_time(Date cbs_response_time) {
		this.cbs_response_time = cbs_response_time;
	}
	public String getCbs_status_error() {
		return cbs_status_error;
	}
	public void setCbs_status_error(String cbs_status_error) {
		this.cbs_status_error = cbs_status_error;
	}
	public String getIpsx_status() {
		return ipsx_status;
	}
	public void setIpsx_status(String ipsx_status) {
		this.ipsx_status = ipsx_status;
	}
	public String getIpsx_status_error() {
		return ipsx_status_error;
	}
	public void setIpsx_status_error(String ipsx_status_error) {
		this.ipsx_status_error = ipsx_status_error;
	}
	public Date getIpsx_response_time() {
		return ipsx_response_time;
	}
	public void setIpsx_response_time(Date ipsx_response_time) {
		this.ipsx_response_time = ipsx_response_time;
	}
	
	public String getSequence_unique_id() {
		return sequence_unique_id;
	}
	public void setSequence_unique_id(String sequence_unique_id) {
		this.sequence_unique_id = sequence_unique_id;
	}
	public String getBob_message_id() {
		return bob_message_id;
	}
	public void setBob_message_id(String bob_message_id) {
		this.bob_message_id = bob_message_id;
	}
	public String getIpsx_message_id() {
		return ipsx_message_id;
	}
	public void setIpsx_message_id(String ipsx_message_id) {
		this.ipsx_message_id = ipsx_message_id;
	}
	public String getBob_account() {
		return bob_account;
	}
	public void setBob_account(String bob_account) {
		this.bob_account = bob_account;
	}
	public String getIpsx_account() {
		return ipsx_account;
	}
	public void setIpsx_account(String ipsx_account) {
		this.ipsx_account = ipsx_account;
	}
	public String getTran_currency() {
		return tran_currency;
	}
	public void setTran_currency(String tran_currency) {
		this.tran_currency = tran_currency;
	}
	public String getTran_status() {
		return tran_status;
	}
	public void setTran_status(String tran_status) {
		this.tran_status = tran_status;
	}
	public String getDevice_id() {
		return device_id;
	}
	public void setDevice_id(String device_id) {
		this.device_id = device_id;
	}
	public String getDevice_ip() {
		return device_ip;
	}
	public void setDevice_ip(String device_ip) {
		this.device_ip = device_ip;
	}
	public String getNat_id() {
		return nat_id;
	}
	public void setNat_id(String nat_id) {
		this.nat_id = nat_id;
	}
	public String getIpsx_status_code() {
		return ipsx_status_code;
	}
	public void setIpsx_status_code(String ipsx_status_code) {
		this.ipsx_status_code = ipsx_status_code;
	}
	public String getMaster_ref_id() {
		return master_ref_id;
	}
	public void setMaster_ref_id(String master_ref_id) {
		this.master_ref_id = master_ref_id;
	}
	public String getMconnectin() {
		return mconnectin;
	}
	public void setMconnectin(String mconnectin) {
		this.mconnectin = mconnectin;
	}
	public Date getMconnectindate() {
		return mconnectindate;
	}
	public void setMconnectindate(Date mconnectindate) {
		this.mconnectindate = mconnectindate;
	}
	public String getMconnectout() {
		return mconnectout;
	}
	public void setMconnectout(String mconnectout) {
		this.mconnectout = mconnectout;
	}
	public Date getMconnectoutdate() {
		return mconnectoutdate;
	}
	public void setMconnectoutdate(Date mconnectoutdate) {
		this.mconnectoutdate = mconnectoutdate;
	}
	public String getCbsin() {
		return cbsin;
	}
	public void setCbsin(String cbsin) {
		this.cbsin = cbsin;
	}
	public Date getCbsindate() {
		return cbsindate;
	}
	public void setCbsindate(Date cbsindate) {
		this.cbsindate = cbsindate;
	}
	public String getCbsout() {
		return cbsout;
	}
	public void setCbsout(String cbsout) {
		this.cbsout = cbsout;
	}
	public Date getCbsoutdate() {
		return cbsoutdate;
	}
	public void setCbsoutdate(Date cbsoutdate) {
		this.cbsoutdate = cbsoutdate;
	}
	public String getCbsreversein() {
		return cbsreversein;
	}
	public void setCbsreversein(String cbsreversein) {
		this.cbsreversein = cbsreversein;
	}
	public Date getCbsreverseindate() {
		return cbsreverseindate;
	}
	public void setCbsreverseindate(Date cbsreverseindate) {
		this.cbsreverseindate = cbsreverseindate;
	}
	public String getCbsreverseout() {
		return cbsreverseout;
	}
	public void setCbsreverseout(String cbsreverseout) {
		this.cbsreverseout = cbsreverseout;
	}
	public Date getCbsreverseoutdate() {
		return cbsreverseoutdate;
	}
	public void setCbsreverseoutdate(Date cbsreverseoutdate) {
		this.cbsreverseoutdate = cbsreverseoutdate;
	}
	public String getIpsin() {
		return ipsin;
	}
	public void setIpsin(String ipsin) {
		this.ipsin = ipsin;
	}
	public Date getIpsindate() {
		return ipsindate;
	}
	public void setIpsindate(Date ipsindate) {
		this.ipsindate = ipsindate;
	}
	public String getIpsout() {
		return ipsout;
	}
	public void setIpsout(String ipsout) {
		this.ipsout = ipsout;
	}
	public Date getIpsoutdate() {
		return ipsoutdate;
	}
	public void setIpsoutdate(Date ipsoutdate) {
		this.ipsoutdate = ipsoutdate;
	}
	public String getEnd_end_id() {
		return end_end_id;
	}
	public void setEnd_end_id(String end_end_id) {
		this.end_end_id = end_end_id;
	}
	public String getBob_account_name() {
		return bob_account_name;
	}
	public void setBob_account_name(String bob_account_name) {
		this.bob_account_name = bob_account_name;
	}
	public String getIpsx_account_name() {
		return ipsx_account_name;
	}
	public void setIpsx_account_name(String ipsx_account_name) {
		this.ipsx_account_name = ipsx_account_name;
	}
	public String getNet_mir() {
		return net_mir;
	}
	public void setNet_mir(String net_mir) {
		this.net_mir = net_mir;
	}
	public String getUser_ref() {
		return user_ref;
	}
	public void setUser_ref(String user_ref) {
		this.user_ref = user_ref;
	}
	public String getTran_type_code() {
		return tran_type_code;
	}
	public void setTran_type_code(String tran_type_code) {
		this.tran_type_code = tran_type_code;
	}
	public String getInstg_agt() {
		return instg_agt;
	}
	public void setInstg_agt(String instg_agt) {
		this.instg_agt = instg_agt;
	}
	public String getInstd_agt() {
		return instd_agt;
	}
	public void setInstd_agt(String instd_agt) {
		this.instd_agt = instd_agt;
	}
	public String getReg_rep() {
		return reg_rep;
	}
	public void setReg_rep(String reg_rep) {
		this.reg_rep = reg_rep;
	}
	public String getRmt_info() {
		return rmt_info;
	}
	public void setRmt_info(String rmt_info) {
		this.rmt_info = rmt_info;
	}
	public String getPmt_inf_id() {
		return pmt_inf_id;
	}
	public void setPmt_inf_id(String pmt_inf_id) {
		this.pmt_inf_id = pmt_inf_id;
	}
	public String getPmt_mtd() {
		return pmt_mtd;
	}
	public void setPmt_mtd(String pmt_mtd) {
		this.pmt_mtd = pmt_mtd;
	}
	public String getInstr_id() {
		return instr_id;
	}
	public void setInstr_id(String instr_id) {
		this.instr_id = instr_id;
	}
	public String getSvc_lvl() {
		return svc_lvl;
	}
	public void setSvc_lvl(String svc_lvl) {
		this.svc_lvl = svc_lvl;
	}
	public String getLcl_instrm() {
		return lcl_instrm;
	}
	public void setLcl_instrm(String lcl_instrm) {
		this.lcl_instrm = lcl_instrm;
	}
	public String getCtgy_purp() {
		return ctgy_purp;
	}
	public void setCtgy_purp(String ctgy_purp) {
		this.ctgy_purp = ctgy_purp;
	}
	public String getDbtr_agt() {
		return dbtr_agt;
	}
	public void setDbtr_agt(String dbtr_agt) {
		this.dbtr_agt = dbtr_agt;
	}
	public String getDbtr_agt_acc() {
		return dbtr_agt_acc;
	}
	public void setDbtr_agt_acc(String dbtr_agt_acc) {
		this.dbtr_agt_acc = dbtr_agt_acc;
	}
	public String getCdtr_agt() {
		return cdtr_agt;
	}
	public void setCdtr_agt(String cdtr_agt) {
		this.cdtr_agt = cdtr_agt;
	}
	public String getCdtr_agt_acc() {
		return cdtr_agt_acc;
	}
	public void setCdtr_agt_acc(String cdtr_agt_acc) {
		this.cdtr_agt_acc = cdtr_agt_acc;
	}
	public Date getValue_date() {
		return value_date;
	}
	public void setValue_date(Date value_date) {
		this.value_date = value_date;
	}
	public String getCharge_app_flg() {
		return charge_app_flg;
	}
	public void setCharge_app_flg(String charge_app_flg) {
		this.charge_app_flg = charge_app_flg;
	}
	public String getChrg_br() {
		return chrg_br;
	}
	public void setChrg_br(String chrg_br) {
		this.chrg_br = chrg_br;
	}
	public String getClr_chennel() {
		return clr_chennel;
	}
	public void setClr_chennel(String clr_chennel) {
		this.clr_chennel = clr_chennel;
	}
	public String getRmt_info_issuer() {
		return rmt_info_issuer;
	}
	public void setRmt_info_issuer(String rmt_info_issuer) {
		this.rmt_info_issuer = rmt_info_issuer;
	}
	public String getRmt_info_nb() {
		return rmt_info_nb;
	}
	public void setRmt_info_nb(String rmt_info_nb) {
		this.rmt_info_nb = rmt_info_nb;
	}
	
	public TransactionMonitor(String msg_type, String tran_audit_number, String bob_message_id, String ipsx_message_id,
			String sequence_unique_id, String bob_account, String ipsx_account, String receiver_bank, BigDecimal tran_amount,
			Date tran_date, Date entry_time, String cbs_status, String entry_user, String tran_currency, String tran_status,
			String end_end_id, String ipsx_account_name, String bob_account_name, String tran_type_code, String instg_agt,
			String instd_agt, String instr_id, String svc_lvl, String lcl_instrm, String ctgy_purp, String dbtr_agt,
			String dbtr_agt_acc, String cdtr_agt, String cdtr_agt_acc, String chrg_br, String reg_rep, String rmt_info,
			String clr_chennel, String rmt_info_issuer, String rmt_info_nb, Date value_date) {
		
		super();
		this.bob_message_id = bob_message_id;
		this.ipsx_message_id = ipsx_message_id;
		this.sequence_unique_id = sequence_unique_id;
		this.tran_audit_number = tran_audit_number;
		this.cbs_status = cbs_status;
		this.tran_currency = tran_currency;
		this.tran_status = tran_status;
		this.end_end_id = end_end_id;
		this.msg_type = msg_type;
		this.entry_time = entry_time;
		this.entry_user = entry_user;
		this.bob_account = bob_account;
		this.receiver_bank = receiver_bank;
		this.ipsx_account = ipsx_account;
		this.tran_amount = tran_amount;
		this.tran_date = tran_date;
		this.bob_account_name = bob_account_name;
		this.ipsx_account_name = ipsx_account_name;
		this.tran_type_code = tran_type_code;
		this.instg_agt = instg_agt;
		this.instd_agt = instd_agt;
		this.reg_rep = reg_rep;
		this.rmt_info = rmt_info;
		this.instr_id = instr_id;
		this.svc_lvl = svc_lvl;
		this.lcl_instrm = lcl_instrm;
		this.ctgy_purp = ctgy_purp;
		this.dbtr_agt = dbtr_agt;
		this.dbtr_agt_acc = dbtr_agt_acc;
		this.cdtr_agt = cdtr_agt;
		this.cdtr_agt_acc = cdtr_agt_acc;
		this.value_date = value_date;
		this.chrg_br = chrg_br;
		this.clr_chennel = clr_chennel;
		this.rmt_info_issuer = rmt_info_issuer;
		this.rmt_info_nb = rmt_info_nb;
		
	}
	public TransactionMonitor() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	
}
