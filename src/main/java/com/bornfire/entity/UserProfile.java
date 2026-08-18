package com.bornfire.entity;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "BIPS_USER_PROFILE")
public class UserProfile{
	private static final long serialVersionUID = 1L;
	private String bank_code;
	private String bank_name;
	private String branch_code;
	private String branch_name;

	@Column(name = "emp_id")
	private String empid;
	private String emp_name;

	@Id
	@Column(name = "user_id")
	private String userid;

	@Column(name = "user_name")
	private String username;
	private String inactive_time;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date acc_exp_date;
	private String login_low;
	private String login_high;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date disable_start_date;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date disable_end_date;
	private String password;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date password_expiry_date;
	private String user_status;
	private String login_status;
	private String virtual_flg;
	private String work_class;
	private String mob_number;
	private String email_id;
	private String role_id;
	private String role_desc;
	private String permissions;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private String per_effctive_date;
	private String admin;
	private String xbrl_configuration;
	private String xbrl_report;
	private String scheduler;
	private String execution;
	private String mis_reports;
	private String xml_reports;
	private String archivel;
	private String general_inq;
	private String audit_inq;
	private String channel;
	private String entry_user;
	private Date entry_time;
	private String auth_user;
	private Date auth_time;
	private String modify_user;
	private Date modify_time;
	private String entity_flg;
	private String auth_flg;
	private String modify_flg;
	private String del_flg;
	private String session_id;
	private String login_flg;
	private String user_locked_flg;
	private Integer no_of_attmp;
	private String disable_flg;
	@Lob
	private byte[] photo;
	private String domain_id;
	private String new_user_flg;
	private String remarks;
	private String userlog_flg;
	private String authenticate_flg;
	private String countrycode;

	
	public String getCountrycode() {
		return countrycode;
	}

	public void setCountrycode(String countrycode) {
		this.countrycode = countrycode;
	}

	public String getAuthenticate_flg() {
		return authenticate_flg;
	}

	public void setAuthenticate_flg(String authenticate_flg) {
		this.authenticate_flg = authenticate_flg;
	}

	public String getUserlog_flg() {
		return userlog_flg;
	}

	public void setUserlog_flg(String userlog_flg) {
		this.userlog_flg = userlog_flg;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
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

	public String getBranch_code() {
		return branch_code;
	}

	public void setBranch_code(String branch_code) {
		this.branch_code = branch_code;
	}

	public String getBranch_name() {
		return branch_name;
	}

	public void setBranch_name(String branch_name) {
		this.branch_name = branch_name;
	}

	public String getEmpid() {
		return empid;
	}

	public void setEmpid(String empid) {
		this.empid = empid;
	}

	public String getEmp_name() {
		return emp_name;
	}

	public void setEmp_name(String emp_name) {
		this.emp_name = emp_name;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String user_id) {
		this.userid = user_id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getInactive_time() {
		return inactive_time;
	}

	public void setInactive_time(String inactive_time) {
		this.inactive_time = inactive_time;
	}

	public Date getAcc_exp_date() {
		return acc_exp_date;
	}

	public void setAcc_exp_date(Date acc_exp_date) {
		this.acc_exp_date = acc_exp_date;
	}

	public void setAcc_exp_date(String acc_exp_date) throws ParseException {
		this.entry_time = new SimpleDateFormat("YYYY-MM-DD").parse(acc_exp_date);
	}

	public String getLogin_low() {
		return login_low;
	}

	public void setLogin_low(String login_low) {
		this.login_low = login_low;
	}

	public String getLogin_high() {
		return login_high;
	}

	public void setLogin_high(String login_high) {
		this.login_high = login_high;
	}

	public Date getDisable_start_date() {
		return disable_start_date;
	}

	public void setDisable_start_date(Date disable_start_date) {
		this.disable_start_date = disable_start_date;
	}

	public Date getDisable_end_date() {
		return disable_end_date;
	}

	public void setDisable_end_date(Date disable_end_date) {
		this.disable_end_date = disable_end_date;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getPassword_expiry_date() {
		return password_expiry_date;
	}

	public void setPassword_expiry_date(Date password_expiry_date) {
		this.password_expiry_date = password_expiry_date;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getUser_status() {
		return user_status;
	}

	public void setUser_status(String user_status) {
		this.user_status = user_status;
	}

	public String getLogin_status() {
		return login_status;
	}

	public void setLogin_status(String login_status) {
		this.login_status = login_status;
	}

	public String getVirtual_flg() {
		return virtual_flg;
	}

	public void setVirtual_flg(String virtual_flg) {
		this.virtual_flg = virtual_flg;
	}

	public String getWork_class() {
		return work_class;
	}

	public void setWork_class(String work_class) {
		this.work_class = work_class;
	}

	public String getMob_number() {
		return mob_number;
	}

	public void setMob_number(String mob_number) {
		this.mob_number = mob_number;
	}

	public String getEmail_id() {
		return email_id;
	}

	public void setEmail_id(String email_id) {
		this.email_id = email_id;
	}

	public String getRole_id() {
		return role_id;
	}

	public void setRole_id(String role_id) {
		this.role_id = role_id;
	}

	public String getRole_desc() {
		return role_desc;
	}

	public void setRole_desc(String role_desc) {
		this.role_desc = role_desc;
	}

	public String getPermissions() {
		return permissions;
	}

	public void setPermissions(String permissions) {
		this.permissions = permissions;
	}

	public String getPer_effctive_date() {
		return per_effctive_date;
	}

	public void setPer_effctive_date(String per_effctive_date) {
		this.per_effctive_date = per_effctive_date;
	}

	public String getAdmin() {
		return admin;
	}

	public void setAdmin(String admin) {
		this.admin = admin;
	}

	public String getXbrl_configuration() {
		return xbrl_configuration;
	}

	public void setXbrl_configuration(String xbrl_configuration) {
		this.xbrl_configuration = xbrl_configuration;
	}

	public String getXbrl_report() {
		return xbrl_report;
	}

	public void setXbrl_report(String xbrl_report) {
		this.xbrl_report = xbrl_report;
	}

	public String getScheduler() {
		return scheduler;
	}

	public void setScheduler(String scheduler) {
		this.scheduler = scheduler;
	}

	public String getExecution() {
		return execution;
	}

	public void setExecution(String execution) {
		this.execution = execution;
	}

	public String getMis_reports() {
		return mis_reports;
	}

	public void setMis_reports(String mis_reports) {
		this.mis_reports = mis_reports;
	}

	public String getXml_reports() {
		return xml_reports;
	}

	public void setXml_reports(String xml_reports) {
		this.xml_reports = xml_reports;
	}

	public String getArchivel() {
		return archivel;
	}

	public void setArchivel(String archivel) {
		this.archivel = archivel;
	}

	public String getGeneral_inq() {
		return general_inq;
	}

	public void setGeneral_inq(String general_inq) {
		this.general_inq = general_inq;
	}

	public String getAudit_inq() {
		return audit_inq;
	}

	public void setAudit_inq(String audit_inq) {
		this.audit_inq = audit_inq;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
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

	public String getDel_flg() {
		return del_flg;
	}

	public void setDel_flg(String del_flg) {
		this.del_flg = del_flg;
	}

	public String getSession_id() {
		return session_id;
	}

	public void setSession_id(String session_id) {
		this.session_id = session_id;
	}

	public String getLogin_flg() {
		return login_flg;
	}

	public void setLogin_flg(String login_flg) {
		this.login_flg = login_flg;
	}

	public String getUser_locked_flg() {
		return user_locked_flg;
	}

	public void setUser_locked_flg(String user_locked_flg) {
		this.user_locked_flg = user_locked_flg;
	}

	public Integer getNo_of_attmp() {
		return no_of_attmp;
	}

	public void setNo_of_attmp(Integer no_of_attmp) {
		this.no_of_attmp = no_of_attmp;
	}

	public String getDisable_flg() {
		return disable_flg;
	}

	public void setDisable_flg(String disable_flg) {
		this.disable_flg = disable_flg;
	}

	@JsonIgnore

	public String getDomain_id() {
		return domain_id;
	}

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	public void setDomain_id(String domain_id) {
		this.domain_id = domain_id;
	}

	public String getNew_user_flg() {
		return new_user_flg;
	}

	public void setNew_user_flg(String new_user_flg) {
		this.new_user_flg = new_user_flg;
	}

	
	public boolean isAccountNonExpired() {
		if (this.getAcc_exp_date().after(new Date())) {
			return true;
		} else {
			return false;
		}

	}

	public boolean isAccountNonLocked() {
		boolean status = true;
		if (this.getUser_locked_flg().equals("Y")) {
			status = false;
		} else {
			status = true;
		}

		return status;
	}

	public boolean isCredentialsNonExpired() {
		/*
		 * if (this.getPass_exp_date().after(new Date())) { return true; } else { return
		 * false; }
		 */
		return true;

	}

	public boolean isEnabled() {
		Date currDate = new Date();
		if ("Y".equals(this.getDisable_flg())
				|| (this.disable_start_date != null && this.disable_end_date != null
						&& currDate.after(this.disable_start_date) && currDate.before(this.disable_end_date))
				|| "N".equals(this.entity_flg)) {
			return false;
		} else {
			return true;
		}
	}

	public boolean ispasswordnotexpiry() {
		Date expiryDate = getPassword_expiry_date();
		Date currentDate = new Date();

		if (expiryDate.after(currentDate)) {
			return true; // Password has expired
		} else {
			return false; // Password has not expired
		}
	}

	public boolean isLoginAllowed() {

		DateFormat dateFormat = new SimpleDateFormat("hh:mm");

		try {
			Date loginHigh = dateFormat.parse(this.login_high);
			Date loginLow = dateFormat.parse(this.login_low);

			LocalTime high = LocalDateTime.ofInstant(loginHigh.toInstant(), ZoneId.systemDefault()).toLocalTime();
			LocalTime low = LocalDateTime.ofInstant(loginLow.toInstant(), ZoneId.systemDefault()).toLocalTime();
			LocalTime currTime = java.time.LocalTime.now();

			if (currTime.isAfter(low) && currTime.isBefore(high)) {
				return true;
			} else {

				return false;
			}

		} catch (ParseException e) {

			e.printStackTrace();
		}

		return false;
	}

	public UserProfile() {
	}

 

	@Override
	public String toString() {
		return "UserProfile [bank_code=" + bank_code + ", bank_name=" + bank_name + ", branch_code=" + branch_code
				+ ", branch_name=" + branch_name + ", empid=" + empid + ", emp_name=" + emp_name + ", userid=" + userid
				+ ", username=" + username + ", inactive_time=" + inactive_time + ", acc_exp_date=" + acc_exp_date
				+ ", login_low=" + login_low + ", login_high=" + login_high + ", disable_start_date="
				+ disable_start_date + ", disable_end_date=" + disable_end_date + ", password=" + password
				+ ", password_expiry_date=" + password_expiry_date + ", user_status=" + user_status + ", login_status="
				+ login_status + ", virtual_flg=" + virtual_flg + ", work_class=" + work_class + ", mob_number="
				+ mob_number + ", email_id=" + email_id + ", role_id=" + role_id + ", role_desc=" + role_desc
				+ ", permissions=" + permissions + ", per_effctive_date=" + per_effctive_date + ", admin=" + admin
				+ ", xbrl_configuration=" + xbrl_configuration + ", xbrl_report=" + xbrl_report + ", scheduler="
				+ scheduler + ", execution=" + execution + ", mis_reports=" + mis_reports + ", xml_reports="
				+ xml_reports + ", archivel=" + archivel + ", general_inq=" + general_inq + ", audit_inq=" + audit_inq
				+ ", channel=" + channel + ", entry_user=" + entry_user + ", entry_time=" + entry_time + ", auth_user="
				+ auth_user + ", auth_time=" + auth_time + ", modify_user=" + modify_user + ", modify_time="
				+ modify_time + ", entity_flg=" + entity_flg + ", auth_flg=" + auth_flg + ", modify_flg=" + modify_flg
				+ ", del_flg=" + del_flg + ", session_id=" + session_id + ", login_flg=" + login_flg
				+ ", user_locked_flg=" + user_locked_flg + ", no_of_attmp=" + no_of_attmp + ", disable_flg="
				+ disable_flg + ", photo=" + Arrays.toString(photo) + ", domain_id=" + domain_id + ", new_user_flg="
				+ new_user_flg + ", remarks=" + remarks + ", userlog_flg=" + userlog_flg + ", authenticate_flg="
				+ authenticate_flg + ", countrycode=" + countrycode + ", getCountrycode()=" + getCountrycode()
				+ ", getAuthenticate_flg()=" + getAuthenticate_flg() + ", getUserlog_flg()=" + getUserlog_flg()
				+ ", getRemarks()=" + getRemarks() + ", getBank_code()=" + getBank_code() + ", getBank_name()="
				+ getBank_name() + ", getBranch_code()=" + getBranch_code() + ", getBranch_name()=" + getBranch_name()
				+ ", getEmpid()=" + getEmpid() + ", getEmp_name()=" + getEmp_name() + ", getUserid()=" + getUserid()
				+ ", getUsername()=" + getUsername() + ", getInactive_time()=" + getInactive_time()
				+ ", getAcc_exp_date()=" + getAcc_exp_date() + ", getLogin_low()=" + getLogin_low()
				+ ", getLogin_high()=" + getLogin_high() + ", getDisable_start_date()=" + getDisable_start_date()
				+ ", getDisable_end_date()=" + getDisable_end_date() + ", getPassword()=" + getPassword()
				+ ", getPassword_expiry_date()=" + getPassword_expiry_date() + ", getUser_status()=" + getUser_status()
				+ ", getLogin_status()=" + getLogin_status() + ", getVirtual_flg()=" + getVirtual_flg()
				+ ", getWork_class()=" + getWork_class() + ", getMob_number()=" + getMob_number() + ", getEmail_id()="
				+ getEmail_id() + ", getRole_id()=" + getRole_id() + ", getRole_desc()=" + getRole_desc()
				+ ", getPermissions()=" + getPermissions() + ", getPer_effctive_date()=" + getPer_effctive_date()
				+ ", getAdmin()=" + getAdmin() + ", getXbrl_configuration()=" + getXbrl_configuration()
				+ ", getXbrl_report()=" + getXbrl_report() + ", getScheduler()=" + getScheduler() + ", getExecution()="
				+ getExecution() + ", getMis_reports()=" + getMis_reports() + ", getXml_reports()=" + getXml_reports()
				+ ", getArchivel()=" + getArchivel() + ", getGeneral_inq()=" + getGeneral_inq() + ", getAudit_inq()="
				+ getAudit_inq() + ", getChannel()=" + getChannel() + ", getEntry_user()=" + getEntry_user()
				+ ", getEntry_time()=" + getEntry_time() + ", getAuth_user()=" + getAuth_user() + ", getAuth_time()="
				+ getAuth_time() + ", getModify_user()=" + getModify_user() + ", getModify_time()=" + getModify_time()
				+ ", getEntity_flg()=" + getEntity_flg() + ", getAuth_flg()=" + getAuth_flg() + ", getModify_flg()="
				+ getModify_flg() + ", getDel_flg()=" + getDel_flg() + ", getSession_id()=" + getSession_id()
				+ ", getLogin_flg()=" + getLogin_flg() + ", getUser_locked_flg()=" + getUser_locked_flg()
				+ ", getNo_of_attmp()=" + getNo_of_attmp() + ", getDisable_flg()=" + getDisable_flg()
				+ ", getDomain_id()=" + getDomain_id() + ", getPhoto()=" + Arrays.toString(getPhoto())
				+ ", getNew_user_flg()=" + getNew_user_flg() 
				+ ", isAccountNonExpired()=" + isAccountNonExpired() + ", isAccountNonLocked()=" + isAccountNonLocked()
				+ ", isCredentialsNonExpired()=" + isCredentialsNonExpired() + ", isEnabled()=" + isEnabled()
				+ ", ispasswordnotexpiry()=" + ispasswordnotexpiry() + ", isLoginAllowed()=" + isLoginAllowed()
				+ ", isPresent()=" + isPresent() + ", get()=" + get() + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + ", toString()=" + super.toString() + "]";
	}

	 
	public UserProfile(String bank_code, String bank_name, String branch_code, String branch_name, String empid,
			String emp_name, String userid, String username, String inactive_time, Date acc_exp_date, String login_low,
			String login_high, Date disable_start_date, Date disable_end_date, String password,
			Date password_expiry_date, String user_status, String login_status, String virtual_flg, String work_class,
			String mob_number, String email_id, String role_id, String role_desc, String permissions,
			String per_effctive_date, String admin, String xbrl_configuration, String xbrl_report, String scheduler,
			String execution, String mis_reports, String xml_reports, String archivel, String general_inq,
			String audit_inq, String channel, String entry_user, Date entry_time, String auth_user, Date auth_time,
			String modify_user, Date modify_time, String entity_flg, String auth_flg, String modify_flg, String del_flg,
			String session_id, String login_flg, String user_locked_flg, Integer no_of_attmp, String disable_flg,
			byte[] photo, String domain_id, String new_user_flg, String remarks, String userlog_flg,
			String authenticate_flg, String countrycode) {
		super();
		this.bank_code = bank_code;
		this.bank_name = bank_name;
		this.branch_code = branch_code;
		this.branch_name = branch_name;
		this.empid = empid;
		this.emp_name = emp_name;
		this.userid = userid;
		this.username = username;
		this.inactive_time = inactive_time;
		this.acc_exp_date = acc_exp_date;
		this.login_low = login_low;
		this.login_high = login_high;
		this.disable_start_date = disable_start_date;
		this.disable_end_date = disable_end_date;
		this.password = password;
		this.password_expiry_date = password_expiry_date;
		this.user_status = user_status;
		this.login_status = login_status;
		this.virtual_flg = virtual_flg;
		this.work_class = work_class;
		this.mob_number = mob_number;
		this.email_id = email_id;
		this.role_id = role_id;
		this.role_desc = role_desc;
		this.permissions = permissions;
		this.per_effctive_date = per_effctive_date;
		this.admin = admin;
		this.xbrl_configuration = xbrl_configuration;
		this.xbrl_report = xbrl_report;
		this.scheduler = scheduler;
		this.execution = execution;
		this.mis_reports = mis_reports;
		this.xml_reports = xml_reports;
		this.archivel = archivel;
		this.general_inq = general_inq;
		this.audit_inq = audit_inq;
		this.channel = channel;
		this.entry_user = entry_user;
		this.entry_time = entry_time;
		this.auth_user = auth_user;
		this.auth_time = auth_time;
		this.modify_user = modify_user;
		this.modify_time = modify_time;
		this.entity_flg = entity_flg;
		this.auth_flg = auth_flg;
		this.modify_flg = modify_flg;
		this.del_flg = del_flg;
		this.session_id = session_id;
		this.login_flg = login_flg;
		this.user_locked_flg = user_locked_flg;
		this.no_of_attmp = no_of_attmp;
		this.disable_flg = disable_flg;
		this.photo = photo;
		this.domain_id = domain_id;
		this.new_user_flg = new_user_flg;
		this.remarks = remarks;
		this.userlog_flg = userlog_flg;
		this.authenticate_flg = authenticate_flg;
		this.countrycode = countrycode;
	}

	public boolean isPresent() {
		// TODO Auto-generated method stub
		return false;
	}

	public UserProfile get() {
		// TODO Auto-generated method stub
		return null;
	}

	public UserProfile(IPSUserProfileMod userProfile) {
		this.bank_code = userProfile.getBank_code();
		this.bank_name = userProfile.getBank_name();
		this.branch_code = userProfile.getBranch_code();
		this.branch_name = userProfile.getBranch_name();
		this.empid = userProfile.getEmpid();
		this.emp_name = userProfile.getEmp_name();
		this.userid = userProfile.getUserid();
		this.username = userProfile.getUsername();
		this.inactive_time = userProfile.getInactive_time();
		this.acc_exp_date = userProfile.getAcc_exp_date();
		this.login_low = userProfile.getLogin_low();
		this.login_high = userProfile.getLogin_high();
		this.disable_start_date = userProfile.getDisable_start_date();
		this.disable_end_date = userProfile.getDisable_end_date();
		this.password = userProfile.getPassword();
		this.password_expiry_date = userProfile.getPassword_expiry_date();
		this.user_status = userProfile.getUser_status();
		this.login_status = userProfile.getLogin_status();
		this.virtual_flg = userProfile.getVirtual_flg();
		this.work_class = userProfile.getWork_class();
		this.mob_number = userProfile.getMob_number();
		this.email_id = userProfile.getEmail_id();
		this.role_id = userProfile.getRole_id();
		this.role_desc = userProfile.getRole_desc();
		this.permissions = userProfile.getPermissions();
		this.per_effctive_date = userProfile.getPer_effctive_date();
		this.admin = userProfile.getAdmin();
		this.xbrl_configuration = userProfile.getXbrl_configuration();
		this.xbrl_report = userProfile.getXbrl_report();
		this.scheduler = userProfile.getScheduler();
		this.execution = userProfile.getExecution();
		this.mis_reports = userProfile.getMis_reports();
		this.xml_reports = userProfile.getXml_reports();
		this.archivel = userProfile.getArchivel();
		this.general_inq = userProfile.getGeneral_inq();
		this.audit_inq = userProfile.getAudit_inq();
		this.channel = userProfile.getChannel();
		this.entry_user = userProfile.getEntry_user();
		this.entry_time = userProfile.getEntry_time();
		this.auth_user = userProfile.getAuth_user();
		this.auth_time = userProfile.getAuth_time();
		this.modify_user = userProfile.getModify_user();
		this.modify_time = userProfile.getModify_time();
		this.entity_flg = userProfile.getEntity_flg();
		this.auth_flg = userProfile.getAuth_flg();
		this.modify_flg = userProfile.getModify_flg();
		this.del_flg = userProfile.getDel_flg();
		this.session_id = userProfile.getSession_id();
		
		this.login_flg = userProfile.getLogin_flg();
		this.user_locked_flg = userProfile.getUser_locked_flg();
		this.no_of_attmp = userProfile.getNo_of_attmp();
		this.disable_flg = userProfile.getDisable_flg();
		this.domain_id = userProfile.getDomain_id();
		this.new_user_flg = userProfile.getNew_user_flg();
		this.remarks = userProfile.getRemarks();
		this.photo = userProfile.getPhoto();
		this.userlog_flg = userProfile.getUserlog_flg();
		this.authenticate_flg = userProfile.getAuthenticate_flg();
		this.countrycode = userProfile.getCountrycode();

	}

}