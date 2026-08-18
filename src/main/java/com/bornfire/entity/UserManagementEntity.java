package com.bornfire.entity;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "BIPS_MERCHANT_USER_MANAGEMENT")
public class UserManagementEntity {
	private String merchant_user_id;
	private String merchant_name;
	private String merchant_legal_user_id;
	private String merchant_corporate_name;
	@Id
	private String user_id;
	private String user_name;
	private String user_designation;
	private String user_role;
	private String password1;
	private Date password_expiry_date1;
	private String password_life1;
	private Date account_expiry_date1;
	private String make_or_checker;
	private String user_category;
	private String supervisor_flag;
	private String user_disable_flag1;
	private Date user_disable_from_date1;
	private Date user_disable_to_date1;
	private String del_flag1;
	private String user_status1;
	private String login_status1;
	private String login_channel1;
	private BigDecimal mobile_no1;
	private BigDecimal alternate_mobile_no1;
	private String email_address1;
	private String alternate_email_id1;
	private String default_device_id;
	private String alternative_device_id1;
	private String entry_user;
	private String modify_user;
	private String verify_user;
	private Date entry_time;
	private Date modify_time;
	private Date verify_time;
	private String alternative_device_id2;
	private String unit_id_u;
	private String unit_type_u;
	private String unit_name_u;
	private String entry_flag;
	private String modify_flag;
	private BigDecimal no_of_attmp;
	private String user_locked_flg;
	private String authentication_flg;
	private byte[] photo;
	private String countrycode;
	private String alt_countrycode;
	private String remark;
	private Date delete_time;
	private String delete_user;
	public String getMerchant_user_id() {
		return merchant_user_id;
	}
	public void setMerchant_user_id(String merchant_user_id) {
		this.merchant_user_id = merchant_user_id;
	}
	public String getMerchant_name() {
		return merchant_name;
	}
	public void setMerchant_name(String merchant_name) {
		this.merchant_name = merchant_name;
	}
	public String getMerchant_legal_user_id() {
		return merchant_legal_user_id;
	}
	public void setMerchant_legal_user_id(String merchant_legal_user_id) {
		this.merchant_legal_user_id = merchant_legal_user_id;
	}
	public String getMerchant_corporate_name() {
		return merchant_corporate_name;
	}
	public void setMerchant_corporate_name(String merchant_corporate_name) {
		this.merchant_corporate_name = merchant_corporate_name;
	}
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
	public String getUser_designation() {
		return user_designation;
	}
	public void setUser_designation(String user_designation) {
		this.user_designation = user_designation;
	}
	public String getUser_role() {
		return user_role;
	}
	public void setUser_role(String user_role) {
		this.user_role = user_role;
	}
	public String getPassword1() {
		return password1;
	}
	public void setPassword1(String password1) {
		this.password1 = password1;
	}
	public Date getPassword_expiry_date1() {
		return password_expiry_date1;
	}
	public void setPassword_expiry_date1(Date password_expiry_date1) {
		this.password_expiry_date1 = password_expiry_date1;
	}
	public String getPassword_life1() {
		return password_life1;
	}
	public void setPassword_life1(String password_life1) {
		this.password_life1 = password_life1;
	}
	public Date getAccount_expiry_date1() {
		return account_expiry_date1;
	}
	public void setAccount_expiry_date1(Date account_expiry_date1) {
		this.account_expiry_date1 = account_expiry_date1;
	}
	public String getMake_or_checker() {
		return make_or_checker;
	}
	public void setMake_or_checker(String make_or_checker) {
		this.make_or_checker = make_or_checker;
	}
	public String getUser_category() {
		return user_category;
	}
	public void setUser_category(String user_category) {
		this.user_category = user_category;
	}
	public String getSupervisor_flag() {
		return supervisor_flag;
	}
	public void setSupervisor_flag(String supervisor_flag) {
		this.supervisor_flag = supervisor_flag;
	}
	public String getUser_disable_flag1() {
		return user_disable_flag1;
	}
	public void setUser_disable_flag1(String user_disable_flag1) {
		this.user_disable_flag1 = user_disable_flag1;
	}
	public Date getUser_disable_from_date1() {
		return user_disable_from_date1;
	}
	public void setUser_disable_from_date1(Date user_disable_from_date1) {
		this.user_disable_from_date1 = user_disable_from_date1;
	}
	public Date getUser_disable_to_date1() {
		return user_disable_to_date1;
	}
	public void setUser_disable_to_date1(Date user_disable_to_date1) {
		this.user_disable_to_date1 = user_disable_to_date1;
	}
	public String getDel_flag1() {
		return del_flag1;
	}
	public void setDel_flag1(String del_flag1) {
		this.del_flag1 = del_flag1;
	}
	public String getUser_status1() {
		return user_status1;
	}
	public void setUser_status1(String user_status1) {
		this.user_status1 = user_status1;
	}
	public String getLogin_status1() {
		return login_status1;
	}
	public void setLogin_status1(String login_status1) {
		this.login_status1 = login_status1;
	}
	public String getLogin_channel1() {
		return login_channel1;
	}
	public void setLogin_channel1(String login_channel1) {
		this.login_channel1 = login_channel1;
	}
	public BigDecimal getMobile_no1() {
		return mobile_no1;
	}
	public void setMobile_no1(BigDecimal mobile_no1) {
		this.mobile_no1 = mobile_no1;
	}
	public BigDecimal getAlternate_mobile_no1() {
		return alternate_mobile_no1;
	}
	public void setAlternate_mobile_no1(BigDecimal alternate_mobile_no1) {
		this.alternate_mobile_no1 = alternate_mobile_no1;
	}
	public String getEmail_address1() {
		return email_address1;
	}
	public void setEmail_address1(String email_address1) {
		this.email_address1 = email_address1;
	}
	public String getAlternate_email_id1() {
		return alternate_email_id1;
	}
	public void setAlternate_email_id1(String alternate_email_id1) {
		this.alternate_email_id1 = alternate_email_id1;
	}
	public String getDefault_device_id() {
		return default_device_id;
	}
	public void setDefault_device_id(String default_device_id) {
		this.default_device_id = default_device_id;
	}
	public String getAlternative_device_id1() {
		return alternative_device_id1;
	}
	public void setAlternative_device_id1(String alternative_device_id1) {
		this.alternative_device_id1 = alternative_device_id1;
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
	public String getVerify_user() {
		return verify_user;
	}
	public void setVerify_user(String verify_user) {
		this.verify_user = verify_user;
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
	public Date getVerify_time() {
		return verify_time;
	}
	public void setVerify_time(Date verify_time) {
		this.verify_time = verify_time;
	}
	public String getAlternative_device_id2() {
		return alternative_device_id2;
	}
	public void setAlternative_device_id2(String alternative_device_id2) {
		this.alternative_device_id2 = alternative_device_id2;
	}
	public String getUnit_id_u() {
		return unit_id_u;
	}
	public void setUnit_id_u(String unit_id_u) {
		this.unit_id_u = unit_id_u;
	}
	public String getUnit_type_u() {
		return unit_type_u;
	}
	public void setUnit_type_u(String unit_type_u) {
		this.unit_type_u = unit_type_u;
	}
	public String getUnit_name_u() {
		return unit_name_u;
	}
	public void setUnit_name_u(String unit_name_u) {
		this.unit_name_u = unit_name_u;
	}
	public String getEntry_flag() {
		return entry_flag;
	}
	public void setEntry_flag(String entry_flag) {
		this.entry_flag = entry_flag;
	}
	public String getModify_flag() {
		return modify_flag;
	}
	public void setModify_flag(String modify_flag) {
		this.modify_flag = modify_flag;
	}
	public BigDecimal getNo_of_attmp() {
		return no_of_attmp;
	}
	public void setNo_of_attmp(BigDecimal no_of_attmp) {
		this.no_of_attmp = no_of_attmp;
	}
	public String getUser_locked_flg() {
		return user_locked_flg;
	}
	public void setUser_locked_flg(String user_locked_flg) {
		this.user_locked_flg = user_locked_flg;
	}
	public String getAuthentication_flg() {
		return authentication_flg;
	}
	public void setAuthentication_flg(String authentication_flg) {
		this.authentication_flg = authentication_flg;
	}
	public byte[] getPhoto() {
		return photo;
	}
	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}
	public String getCountrycode() {
		return countrycode;
	}
	public void setCountrycode(String countrycode) {
		this.countrycode = countrycode;
	}
	public String getAlt_countrycode() {
		return alt_countrycode;
	}
	public void setAlt_countrycode(String alt_countrycode) {
		this.alt_countrycode = alt_countrycode;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Date getDelete_time() {
		return delete_time;
	}
	public void setDelete_time(Date delete_time) {
		this.delete_time = delete_time;
	}
	public String getDelete_user() {
		return delete_user;
	}
	public void setDelete_user(String delete_user) {
		this.delete_user = delete_user;
	}
	public UserManagementEntity(String merchant_user_id, String merchant_name, String merchant_legal_user_id,
			String merchant_corporate_name, String user_id, String user_name, String user_designation, String user_role,
			String password1, Date password_expiry_date1, String password_life1, Date account_expiry_date1,
			String make_or_checker, String user_category, String supervisor_flag, String user_disable_flag1,
			Date user_disable_from_date1, Date user_disable_to_date1, String del_flag1, String user_status1,
			String login_status1, String login_channel1, BigDecimal mobile_no1, BigDecimal alternate_mobile_no1,
			String email_address1, String alternate_email_id1, String default_device_id, String alternative_device_id1,
			String entry_user, String modify_user, String verify_user, Date entry_time, Date modify_time,
			Date verify_time, String alternative_device_id2, String unit_id_u, String unit_type_u, String unit_name_u,
			String entry_flag, String modify_flag, BigDecimal no_of_attmp, String user_locked_flg,
			String authentication_flg, byte[] photo, String countrycode, String alt_countrycode, String remark,
			Date delete_time, String delete_user) {
		super();
		this.merchant_user_id = merchant_user_id;
		this.merchant_name = merchant_name;
		this.merchant_legal_user_id = merchant_legal_user_id;
		this.merchant_corporate_name = merchant_corporate_name;
		this.user_id = user_id;
		this.user_name = user_name;
		this.user_designation = user_designation;
		this.user_role = user_role;
		this.password1 = password1;
		this.password_expiry_date1 = password_expiry_date1;
		this.password_life1 = password_life1;
		this.account_expiry_date1 = account_expiry_date1;
		this.make_or_checker = make_or_checker;
		this.user_category = user_category;
		this.supervisor_flag = supervisor_flag;
		this.user_disable_flag1 = user_disable_flag1;
		this.user_disable_from_date1 = user_disable_from_date1;
		this.user_disable_to_date1 = user_disable_to_date1;
		this.del_flag1 = del_flag1;
		this.user_status1 = user_status1;
		this.login_status1 = login_status1;
		this.login_channel1 = login_channel1;
		this.mobile_no1 = mobile_no1;
		this.alternate_mobile_no1 = alternate_mobile_no1;
		this.email_address1 = email_address1;
		this.alternate_email_id1 = alternate_email_id1;
		this.default_device_id = default_device_id;
		this.alternative_device_id1 = alternative_device_id1;
		this.entry_user = entry_user;
		this.modify_user = modify_user;
		this.verify_user = verify_user;
		this.entry_time = entry_time;
		this.modify_time = modify_time;
		this.verify_time = verify_time;
		this.alternative_device_id2 = alternative_device_id2;
		this.unit_id_u = unit_id_u;
		this.unit_type_u = unit_type_u;
		this.unit_name_u = unit_name_u;
		this.entry_flag = entry_flag;
		this.modify_flag = modify_flag;
		this.no_of_attmp = no_of_attmp;
		this.user_locked_flg = user_locked_flg;
		this.authentication_flg = authentication_flg;
		this.photo = photo;
		this.countrycode = countrycode;
		this.alt_countrycode = alt_countrycode;
		this.remark = remark;
		this.delete_time = delete_time;
		this.delete_user = delete_user;
	}
	public UserManagementEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "UserManagementEntity [merchant_user_id=" + merchant_user_id + ", merchant_name=" + merchant_name
				+ ", merchant_legal_user_id=" + merchant_legal_user_id + ", merchant_corporate_name="
				+ merchant_corporate_name + ", user_id=" + user_id + ", user_name=" + user_name + ", user_designation="
				+ user_designation + ", user_role=" + user_role + ", password1=" + password1
				+ ", password_expiry_date1=" + password_expiry_date1 + ", password_life1=" + password_life1
				+ ", account_expiry_date1=" + account_expiry_date1 + ", make_or_checker=" + make_or_checker
				+ ", user_category=" + user_category + ", supervisor_flag=" + supervisor_flag + ", user_disable_flag1="
				+ user_disable_flag1 + ", user_disable_from_date1=" + user_disable_from_date1
				+ ", user_disable_to_date1=" + user_disable_to_date1 + ", del_flag1=" + del_flag1 + ", user_status1="
				+ user_status1 + ", login_status1=" + login_status1 + ", login_channel1=" + login_channel1
				+ ", mobile_no1=" + mobile_no1 + ", alternate_mobile_no1=" + alternate_mobile_no1 + ", email_address1="
				+ email_address1 + ", alternate_email_id1=" + alternate_email_id1 + ", default_device_id="
				+ default_device_id + ", alternative_device_id1=" + alternative_device_id1 + ", entry_user="
				+ entry_user + ", modify_user=" + modify_user + ", verify_user=" + verify_user + ", entry_time="
				+ entry_time + ", modify_time=" + modify_time + ", verify_time=" + verify_time
				+ ", alternative_device_id2=" + alternative_device_id2 + ", unit_id_u=" + unit_id_u + ", unit_type_u="
				+ unit_type_u + ", unit_name_u=" + unit_name_u + ", entry_flag=" + entry_flag + ", modify_flag="
				+ modify_flag + ", no_of_attmp=" + no_of_attmp + ", user_locked_flg=" + user_locked_flg
				+ ", authentication_flg=" + authentication_flg + ", photo=" + Arrays.toString(photo) + ", countrycode="
				+ countrycode + ", alt_countrycode=" + alt_countrycode + ", remark=" + remark + ", delete_time="
				+ delete_time + ", delete_user=" + delete_user + "]";
	}
	
	
}
