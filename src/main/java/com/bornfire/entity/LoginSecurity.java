package com.bornfire.entity;


import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "BIPS_LOGIN_SEC_TABLE")
public class LoginSecurity{
	
	@Id
	private String ref_num;
	private BigDecimal pw_life;
	private BigDecimal nof_atp;
	private String login_low;
	private String login_high;
	private String inact_time;
	private BigDecimal pw_warn_days;
	private String pre_pw_chk_flg;
	private BigDecimal nof_pre_pws;
	private BigDecimal pw_length;
	private String com_flg;
	private String alpha_flg;
	private String cap_flg;
	private String num_flg;
	private String spl_char_flg;
	private String entry_user;
	private Date entry_time;
	private Date verify_time;
	private String modify_user;
	private Date modify_time;
	private String entity_flg;
	private String auth_flg;
	private String modify_flg;
	
	
	
	
	
	
	public BigDecimal getPw_life() {
		return pw_life;
	}
	public void setPw_life(BigDecimal pw_life) {
		this.pw_life = pw_life;
	}
	public BigDecimal getNof_atp() {
		return nof_atp;
	}
	public void setNof_atp(BigDecimal nof_atp) {
		this.nof_atp = nof_atp;
	}
	
	public String getLogin_low() {
		return login_low;
	}
	public String getLogin_high() {
		return login_high;
	}
	public String getInact_time() {
		return inact_time;
	}
	public void setLogin_low(String login_low) {
		this.login_low = login_low;
	}
	public void setLogin_high(String login_high) {
		this.login_high = login_high;
	}
	public void setInact_time(String inact_time) {
		this.inact_time = inact_time;
	}
	public BigDecimal getPw_warn_days() {
		return pw_warn_days;
	}
	public void setPw_warn_days(BigDecimal pw_warn_days) {
		this.pw_warn_days = pw_warn_days;
	}
	public String getPre_pw_chk_flg() {
		return pre_pw_chk_flg;
	}
	public void setPre_pw_chk_flg(String pre_pw_chk_flg) {
		this.pre_pw_chk_flg = pre_pw_chk_flg;
	}
	public BigDecimal getNof_pre_pws() {
		return nof_pre_pws;
	}
	public void setNof_pre_pws(BigDecimal nof_pre_pws) {
		this.nof_pre_pws = nof_pre_pws;
	}
	public BigDecimal getPw_length() {
		return pw_length;
	}
	public void setPw_length(BigDecimal pw_length) {
		this.pw_length = pw_length;
	}
	
	public String getAlpha_flg() {
		return alpha_flg;
	}
	public void setAlpha_flg(String alpha_flg) {
		this.alpha_flg = alpha_flg;
	}
	public String getCap_flg() {
		return cap_flg;
	}
	public void setCap_flg(String cap_flg) {
		this.cap_flg = cap_flg;
	}
	public String getNum_flg() {
		return num_flg;
	}
	public void setNum_flg(String num_flg) {
		this.num_flg = num_flg;
	}
	public String getSpl_char_flg() {
		return spl_char_flg;
	}
	public void setSpl_char_flg(String spl_char_flg) {
		this.spl_char_flg = spl_char_flg;
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
	
	public Date getVerify_time() {
		return verify_time;
	}
	public void setVerify_time(Date verify_time) {
		this.verify_time = verify_time;
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
	
	public String getCom_flg() {
		return com_flg;
	}
	public void setCom_flg(String com_flg) {
		this.com_flg = com_flg;
	}
	public String getEntity_flg() {
		return entity_flg;
	}
	public void setEntity_flg(String entity_flg) {
		this.entity_flg = entity_flg;
	}
	public String getAuth_flg() {
		return auth_flg;
	}
	public void setAuth_flg(String auth_flg) {
		this.auth_flg = auth_flg;
	}
	public String getModify_flg() {
		return modify_flg;
	}
	public void setModify_flg(String modify_flg) {
		this.modify_flg = modify_flg;
	}
	
	
	public String getRef_num() {
		return ref_num;
	}
	public void setRef_num(String ref_num) {
		this.ref_num = ref_num;
	}
	public LoginSecurity(BigDecimal pw_life, BigDecimal nof_atp, String login_low, String login_high, String inact_time,
			BigDecimal pw_warn_days, String pre_pw_chk_flg, BigDecimal nof_pre_pws, BigDecimal pw_length,
			String com_flg, String alpha_flg, String cap_flg, String num_flg, String spl_char_flg,
			String entry_user, Date entry_time, String verify_user, Date verify_time, String modify_user,
			Date modify_time, String entity_flg, String auth_flg, String modify_flg) {
		super();
		this.pw_life = pw_life;
		this.nof_atp = nof_atp;
		this.login_low = login_low;
		this.login_high = login_high;
		this.inact_time = inact_time;
		this.pw_warn_days = pw_warn_days;
		this.pre_pw_chk_flg = pre_pw_chk_flg;
		this.nof_pre_pws = nof_pre_pws;
		this.pw_length = pw_length;
		this.com_flg = com_flg;
		this.alpha_flg = alpha_flg;
		this.cap_flg = cap_flg;
		this.num_flg = num_flg;
		this.spl_char_flg = spl_char_flg;
		this.entry_user = entry_user;
		this.entry_time = entry_time;
		this.verify_time = verify_time;
		this.modify_user = modify_user;
		this.modify_time = modify_time;
		this.entity_flg = entity_flg;
		this.auth_flg = auth_flg;
		this.modify_flg = modify_flg;
	}
	
	public LoginSecurity() {
		
	 }	
}
