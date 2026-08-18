package com.bornfire.entity;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "BIPS_ACCESS_ROLE_TABLE")
public class IPSAccessRole {

	@Id
	private String	role_id;
	private String	role_desc;
	private String	permissions;
	private String	work_class;
	private String	domain_id;
	private String	admin;
	private String	entity_flg;
	private String	auth_flg;
	private String	modify_flg;
	private String	del_flg;
	private String	menulist;
	private String	entry_user;
	private String	modify_user;
	private String	auth_user;
	private Date	entry_time;
	private Date	modify_time;
	private Date	auth_time;
	private String audit_operations;
	private String ips_parameter;
	private String ips_operations;
	private String monitoring;
	private String myt_registration;
	private String wallet_master;
	private String consent_registration;
	private String merchant_registration;
	private String new_role_flg;
	private String remarks;
	private String user_profile_maintenance;
	private String access_role;
	private String service_charges_fees;
	private String wallet_fees_charges;
	private String merchant_category_code;
	private String notification_parameter;
	private String user_activity_audits;
	private String merchant_master;
	private String merchant_operations;
	private String service_audits;

	
	public String getService_audits() {
		return service_audits;
	}
	public void setService_audits(String service_audits) {
		this.service_audits = service_audits;
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
	public String getWork_class() {
		return work_class;
	}
	public void setWork_class(String work_class) {
		this.work_class = work_class;
	}
	public String getDomain_id() {
		return domain_id;
	}
	public void setDomain_id(String domain_id) {
		this.domain_id = domain_id;
	}
	public String getAdmin() {
		return admin;
	}
	public void setAdmin(String admin) {
		this.admin = admin;
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
	public String getMenulist() {
		return menulist;
	}
	public void setMenulist(String menulist) {
		this.menulist = menulist;
	}
	public String getEntry_user() {
		return entry_user;
	}
	public void setEntry_user(String entry_user) {
		this.entry_user = entry_user;
	}
	public String getModify_user() {
		return modify_user;
	}
	public void setModify_user(String modify_user) {
		this.modify_user = modify_user;
	}
	public String getAuth_user() {
		return auth_user;
	}
	public void setAuth_user(String auth_user) {
		this.auth_user = auth_user;
	}
	public Date getEntry_time() {
		return entry_time;
	}
	public void setEntry_time(Date entry_time) {
		this.entry_time = entry_time;
	}
	public Date getModify_time() {
		return modify_time;
	}
	public void setModify_time(Date modify_time) {
		this.modify_time = modify_time;
	}
	public Date getAuth_time() {
		return auth_time;
	}
	public void setAuth_time(Date auth_time) {
		this.auth_time = auth_time;
	}
	public String getAudit_operations() {
		return audit_operations;
	}
	public void setAudit_operations(String audit_operations) {
		this.audit_operations = audit_operations;
	}
	public String getIps_operations() {
		return ips_operations;
	}
	public void setIps_operations(String ips_operations) {
		this.ips_operations = ips_operations;
	}
	public String getMonitoring() {
		return monitoring;
	}
	public void setMonitoring(String monitoring) {
		this.monitoring = monitoring;
	}
	public String getMyt_registration() {
		return myt_registration;
	}
	public void setMyt_registration(String myt_registration) {
		this.myt_registration = myt_registration;
	}
	public String getWallet_master() {
		return wallet_master;
	}
	public void setWallet_master(String wallet_master) {
		this.wallet_master = wallet_master;
	}
	public String getUser_profile_maintenance() {
		return user_profile_maintenance;
	}
	public void setUser_profile_maintenance(String user_profile_maintenance) {
		this.user_profile_maintenance = user_profile_maintenance;
	}
	public String getAccess_role() {
		return access_role;
	}
	public void setAccess_role(String access_role) {
		this.access_role = access_role;
	}
	public String getService_charges_fees() {
		return service_charges_fees;
	}
	public void setService_charges_fees(String service_charges_fees) {
		this.service_charges_fees = service_charges_fees;
	}
	public String getWallet_fees_charges() {
		return wallet_fees_charges;
	}
	public void setWallet_fees_charges(String wallet_fees_charges) {
		this.wallet_fees_charges = wallet_fees_charges;
	}
	public String getMerchant_category_code() {
		return merchant_category_code;
	}
	public void setMerchant_category_code(String merchant_category_code) {
		this.merchant_category_code = merchant_category_code;
	}
	public String getNotification_parameter() {
		return notification_parameter;
	}
	public void setNotification_parameter(String notification_parameter) {
		this.notification_parameter = notification_parameter;
	}
	public String getUser_activity_audits() {
		return user_activity_audits;
	}
	public void setUser_activity_audits(String user_activity_audits) {
		this.user_activity_audits = user_activity_audits;
	}
	public String getMerchant_master() {
		return merchant_master;
	}
	public void setMerchant_master(String merchant_master) {
		this.merchant_master = merchant_master;
	}
	public String getMerchant_operations() {
		return merchant_operations;
	}
	public void setMerchant_operations(String merchant_operations) {
		this.merchant_operations = merchant_operations;
	}
	public IPSAccessRole() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getConsent_registration() {
		return consent_registration;
	}
	public void setConsent_registration(String consent_registration) {
		this.consent_registration = consent_registration;
	}
	public String getMerchant_registration() {
		return merchant_registration;
	}
	public void setMerchant_registration(String merchant_registration) {
		this.merchant_registration = merchant_registration;
	}
	
	public String getNew_role_flg() {
		return new_role_flg;
	}
	public void setNew_role_flg(String new_role_flg) {
		this.new_role_flg = new_role_flg;
	}
	
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getIps_parameter() {
		return ips_parameter;
	}
	public void setIps_parameter(String ips_parameter) {
		this.ips_parameter = ips_parameter;
	}
	
	
	public IPSAccessRole(String role_id, String role_desc, String permissions, String work_class, String domain_id,
			String admin, String entity_flg, String auth_flg, String modify_flg, String del_flg, String menulist,
			String entry_user, String modify_user, String auth_user, Date entry_time, Date modify_time, Date auth_time,
			String audit_operations, String ips_parameter, String ips_operations, String monitoring,
			String myt_registration, String wallet_master, String consent_registration, String merchant_registration,
			String new_role_flg, String remarks, String user_profile_maintenance, String access_role,
			String service_charges_fees, String wallet_fees_charges, String merchant_category_code,
			String notification_parameter, String user_activity_audits, String merchant_master,
			String merchant_operations, String service_audits) {
		super();
		this.role_id = role_id;
		this.role_desc = role_desc;
		this.permissions = permissions;
		this.work_class = work_class;
		this.domain_id = domain_id;
		this.admin = admin;
		this.entity_flg = entity_flg;
		this.auth_flg = auth_flg;
		this.modify_flg = modify_flg;
		this.del_flg = del_flg;
		this.menulist = menulist;
		this.entry_user = entry_user;
		this.modify_user = modify_user;
		this.auth_user = auth_user;
		this.entry_time = entry_time;
		this.modify_time = modify_time;
		this.auth_time = auth_time;
		this.audit_operations = audit_operations;
		this.ips_parameter = ips_parameter;
		this.ips_operations = ips_operations;
		this.monitoring = monitoring;
		this.myt_registration = myt_registration;
		this.wallet_master = wallet_master;
		this.consent_registration = consent_registration;
		this.merchant_registration = merchant_registration;
		this.new_role_flg = new_role_flg;
		this.remarks = remarks;
		this.user_profile_maintenance = user_profile_maintenance;
		this.access_role = access_role;
		this.service_charges_fees = service_charges_fees;
		this.wallet_fees_charges = wallet_fees_charges;
		this.merchant_category_code = merchant_category_code;
		this.notification_parameter = notification_parameter;
		this.user_activity_audits = user_activity_audits;
		this.merchant_master = merchant_master;
		this.merchant_operations = merchant_operations;
		this.service_audits = service_audits;
	}
	/*
	 * public IPSAccessRole(String role_id, String role_desc, String permissions,
	 * String work_class, String domain_id, String admin, String entity_flg, String
	 * auth_flg, String modify_flg, String del_flg, String menulist, String
	 * entry_user, String modify_user, String auth_user, Date entry_time, Date
	 * modify_time, Date auth_time, String audit_operations, String ips_parameter,
	 * String ips_operations, String monitoring, String myt_registration, String
	 * wallet_master, String consent_registration, String merchant_registration,
	 * String new_role_flg, String remarks) { super(); this.role_id = role_id;
	 * this.role_desc = role_desc; this.permissions = permissions; this.work_class =
	 * work_class; this.domain_id = domain_id; this.admin = admin; this.entity_flg =
	 * entity_flg; this.auth_flg = auth_flg; this.modify_flg = modify_flg;
	 * this.del_flg = del_flg; this.menulist = menulist; this.entry_user =
	 * entry_user; this.modify_user = modify_user; this.auth_user = auth_user;
	 * this.entry_time = entry_time; this.modify_time = modify_time; this.auth_time
	 * = auth_time; this.audit_operations = audit_operations; this.ips_parameter =
	 * ips_parameter; this.ips_operations = ips_operations; this.monitoring =
	 * monitoring; this.myt_registration = myt_registration; this.wallet_master =
	 * wallet_master; this.consent_registration = consent_registration;
	 * this.merchant_registration = merchant_registration; this.new_role_flg =
	 * new_role_flg; this.remarks = remarks; }
	 */

	public IPSAccessRole (IPSAccessRoleModTable ipsAccessRole) {
		this.role_id = ipsAccessRole.getRole_id();
		this.role_desc = ipsAccessRole.getRole_desc();
		this.permissions = ipsAccessRole.getPermissions();
		this.work_class = ipsAccessRole.getWork_class();
		this.domain_id = ipsAccessRole.getDomain_id();
		this.admin = ipsAccessRole.getAdmin();
		this.entity_flg = ipsAccessRole.getEntity_flg();
		this.auth_flg = ipsAccessRole.getAuth_flg();
		this.modify_flg = ipsAccessRole.getModify_flg();
		this.del_flg = ipsAccessRole.getDel_flg();
		this.menulist = ipsAccessRole.getMenulist();
		this.entry_user = ipsAccessRole.getEntry_user();
		this.modify_user = ipsAccessRole.getModify_user();
		this.auth_user = ipsAccessRole.getAuth_user();
		this.entry_time = ipsAccessRole.getEntry_time();
		this.modify_time = ipsAccessRole.getModify_time();
		this.auth_time = ipsAccessRole.getAuth_time();
		this.audit_operations = ipsAccessRole.getAudit_operations();
		this.ips_operations = ipsAccessRole.getIps_operations();
		this.monitoring = ipsAccessRole.getMonitoring();
		this.myt_registration = ipsAccessRole.getMyt_registration();
		this.wallet_master = ipsAccessRole.getWallet_master();
		this.consent_registration = ipsAccessRole.getConsent_registration();
		this.merchant_registration = ipsAccessRole.getMerchant_registration();
		this.new_role_flg=ipsAccessRole.getNew_role_flg();
		this.remarks=ipsAccessRole.getRemarks();
		this.ips_parameter=ipsAccessRole.getIps_parameter();
		this.user_profile_maintenance=ipsAccessRole.getUser_profile_maintenance();
		this.access_role=ipsAccessRole.getAccess_role();
		this.service_charges_fees=ipsAccessRole.getService_charges_fees();
		this.wallet_fees_charges=ipsAccessRole.getWallet_fees_charges();
		this.merchant_category_code=ipsAccessRole.getMerchant_category_code();
		this.notification_parameter=ipsAccessRole.getNotification_parameter();
		this.user_activity_audits=ipsAccessRole.getUser_activity_audits();
		this.service_audits=ipsAccessRole.getService_audits();
	}
	
	
	
	
}