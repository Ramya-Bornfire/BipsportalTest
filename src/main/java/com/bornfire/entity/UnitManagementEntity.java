package com.bornfire.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "BIPS_MERCHANT_UNIT_MANAGEMENT")
public class UnitManagementEntity {

	private String merchant_user_id;
	private String merchant_name;
	@Id
	private String unit_id;
	private String location_detail;
	private String unit_type;
	private String unit_name;
	private String address_1;
	private String address_2;
	private String city;
	private String state_;
	private String country;
	private String zip_code;
	private String phone_no;
	private String remarks;
	private String email_id;
	private String branch_head;
	private String designation;
	private String bankcode_name;
	private String branchcode_name;
	private String branch_swiftcode;
	private String entry_user;
	private String modify_user;
	private String verify_user;
	@DateTimeFormat(pattern = "dd-MM-yyyy HH:mm")
	private Date entry_time;
	@DateTimeFormat(pattern = "dd-MM-yyyy HH:mm")
	private Date modify_time;
	@DateTimeFormat(pattern = "dd-MM-yyyy HH:mm")
	private Date verify_time;
	private String contact_person1_name;
	private String contact_person2_name;
	private String contact_person3_name;
	private String contact_person4_name;
	private String contact_person5_name;
	private String contact_person6_name;
	private BigDecimal contact_person1_mobile;
	private BigDecimal contact_person2_mobile;
	private BigDecimal contact_person3_mobile;
	private BigDecimal contact_person4_mobile;
	private BigDecimal contact_person5_mobile;
	private BigDecimal contact_person6_mobile;
	private String contact_person1_email;
	private String contact_person2_email;
	private String contact_person3_email;
	private String contact_person4_email;
	private String contact_person5_email;
	private String contact_person6_email;
	private String ph_countrycode;
	private String cp1_countrycode;
	private String cp2_countrycode;
	private String cp3_countrycode;
	private String cp4_countrycode;
	private String cp5_countrycode;
	private String cp6_countrycode;
	private String entry_flag;
	private String modify_flag;
	private String del_flg;
	private String delete_user;
	private Date delete_date;
	
	
	public String getDel_flg() {
		return del_flg;
	}

	public void setDel_flg(String del_flg) {
		this.del_flg = del_flg;
	}

	public String getDelete_user() {
		return delete_user;
	}

	public void setDelete_user(String delete_user) {
		this.delete_user = delete_user;
	}

	public Date getDelete_date() {
		return delete_date;
	}

	public void setDelete_date(Date delete_date) {
		this.delete_date = delete_date;
	}

	public String getContact_person4_name() {
		return contact_person4_name;
	}

	public void setContact_person4_name(String contact_person4_name) {
		this.contact_person4_name = contact_person4_name;
	}

	public String getContact_person5_name() {
		return contact_person5_name;
	}

	public void setContact_person5_name(String contact_person5_name) {
		this.contact_person5_name = contact_person5_name;
	}

	public String getContact_person6_name() {
		return contact_person6_name;
	}

	public void setContact_person6_name(String contact_person6_name) {
		this.contact_person6_name = contact_person6_name;
	}

	public BigDecimal getContact_person4_mobile() {
		return contact_person4_mobile;
	}

	public void setContact_person4_mobile(BigDecimal contact_person4_mobile) {
		this.contact_person4_mobile = contact_person4_mobile;
	}

	public BigDecimal getContact_person5_mobile() {
		return contact_person5_mobile;
	}

	public void setContact_person5_mobile(BigDecimal contact_person5_mobile) {
		this.contact_person5_mobile = contact_person5_mobile;
	}

	public BigDecimal getContact_person6_mobile() {
		return contact_person6_mobile;
	}

	public void setContact_person6_mobile(BigDecimal contact_person6_mobile) {
		this.contact_person6_mobile = contact_person6_mobile;
	}

	public String getContact_person4_email() {
		return contact_person4_email;
	}

	public void setContact_person4_email(String contact_person4_email) {
		this.contact_person4_email = contact_person4_email;
	}

	public String getContact_person5_email() {
		return contact_person5_email;
	}

	public void setContact_person5_email(String contact_person5_email) {
		this.contact_person5_email = contact_person5_email;
	}

	public String getContact_person6_email() {
		return contact_person6_email;
	}

	public void setContact_person6_email(String contact_person6_email) {
		this.contact_person6_email = contact_person6_email;
	}

	public String getPh_countrycode() {
		return ph_countrycode;
	}

	public void setPh_countrycode(String ph_countrycode) {
		this.ph_countrycode = ph_countrycode;
	}

	public String getCp1_countrycode() {
		return cp1_countrycode;
	}

	public void setCp1_countrycode(String cp1_countrycode) {
		this.cp1_countrycode = cp1_countrycode;
	}

	public String getCp2_countrycode() {
		return cp2_countrycode;
	}

	public void setCp2_countrycode(String cp2_countrycode) {
		this.cp2_countrycode = cp2_countrycode;
	}

	public String getCp3_countrycode() {
		return cp3_countrycode;
	}

	public void setCp3_countrycode(String cp3_countrycode) {
		this.cp3_countrycode = cp3_countrycode;
	}

	public String getCp4_countrycode() {
		return cp4_countrycode;
	}

	public void setCp4_countrycode(String cp4_countrycode) {
		this.cp4_countrycode = cp4_countrycode;
	}

	public String getCp5_countrycode() {
		return cp5_countrycode;
	}

	public void setCp5_countrycode(String cp5_countrycode) {
		this.cp5_countrycode = cp5_countrycode;
	}

	public String getCp6_countrycode() {
		return cp6_countrycode;
	}

	public void setCp6_countrycode(String cp6_countrycode) {
		this.cp6_countrycode = cp6_countrycode;
	}

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

	public String getUnit_id() {
		return unit_id;
	}

	public void setUnit_id(String unit_id) {
		this.unit_id = unit_id;
	}

	public String getLocation_detail() {
		return location_detail;
	}

	public void setLocation_detail(String location_detail) {
		this.location_detail = location_detail;
	}

	public String getUnit_type() {
		return unit_type;
	}

	public void setUnit_type(String unit_type) {
		this.unit_type = unit_type;
	}

	public String getUnit_name() {
		return unit_name;
	}

	public void setUnit_name(String unit_name) {
		this.unit_name = unit_name;
	}

	public String getAddress_1() {
		return address_1;
	}

	public void setAddress_1(String address_1) {
		this.address_1 = address_1;
	}

	public String getAddress_2() {
		return address_2;
	}

	public void setAddress_2(String address_2) {
		this.address_2 = address_2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState_() {
		return state_;
	}

	public void setState_(String state_) {
		this.state_ = state_;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getZip_code() {
		return zip_code;
	}

	public void setZip_code(String zip_code) {
		this.zip_code = zip_code;
	}

	public String getPhone_no() {
		return phone_no;
	}

	public void setPhone_no(String phone_no) {
		this.phone_no = phone_no;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getEmail_id() {
		return email_id;
	}

	public void setEmail_id(String email_id) {
		this.email_id = email_id;
	}

	public String getBranch_head() {
		return branch_head;
	}

	public void setBranch_head(String branch_head) {
		this.branch_head = branch_head;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getContact_person1_name() {
		return contact_person1_name;
	}

	public void setContact_person1_name(String contact_person1_name) {
		this.contact_person1_name = contact_person1_name;
	}

	public BigDecimal getContact_person1_mobile() {
		return contact_person1_mobile;
	}

	public void setContact_person1_mobile(BigDecimal contact_person1_mobile) {
		this.contact_person1_mobile = contact_person1_mobile;
	}

	public String getContact_person2_name() {
		return contact_person2_name;
	}

	public void setContact_person2_name(String contact_person2_name) {
		this.contact_person2_name = contact_person2_name;
	}

	public BigDecimal getContact_person2_mobile() {
		return contact_person2_mobile;
	}

	public void setContact_person2_mobile(BigDecimal contact_person2_mobile) {
		this.contact_person2_mobile = contact_person2_mobile;
	}

	public String getBankcode_name() {
		return bankcode_name;
	}

	public void setBankcode_name(String bankcode_name) {
		this.bankcode_name = bankcode_name;
	}

	public String getBranchcode_name() {
		return branchcode_name;
	}

	public void setBranchcode_name(String branchcode_name) {
		this.branchcode_name = branchcode_name;
	}

	public String getBranch_swiftcode() {
		return branch_swiftcode;
	}

	public void setBranch_swiftcode(String branch_swiftcode) {
		this.branch_swiftcode = branch_swiftcode;
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

	public String getContact_person3_name() {
		return contact_person3_name;
	}

	public void setContact_person3_name(String contact_person3_name) {
		this.contact_person3_name = contact_person3_name;
	}

	public BigDecimal getContact_person3_mobile() {
		return contact_person3_mobile;
	}

	public void setContact_person3_mobile(BigDecimal contact_person3_mobile) {
		this.contact_person3_mobile = contact_person3_mobile;
	}

	public String getContact_person1_email() {
		return contact_person1_email;
	}

	public void setContact_person1_email(String contact_person1_email) {
		this.contact_person1_email = contact_person1_email;
	}

	public String getContact_person2_email() {
		return contact_person2_email;
	}

	public void setContact_person2_email(String contact_person2_email) {
		this.contact_person2_email = contact_person2_email;
	}

	public String getContact_person3_email() {
		return contact_person3_email;
	}

	public void setContact_person3_email(String contact_person3_email) {
		this.contact_person3_email = contact_person3_email;
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

	@Override
	public String toString() {
		return "UnitManagementEntity [merchant_user_id=" + merchant_user_id + ", merchant_name=" + merchant_name
				+ ", unit_id=" + unit_id + ", location_detail=" + location_detail + ", unit_type=" + unit_type
				+ ", unit_name=" + unit_name + ", address_1=" + address_1 + ", address_2=" + address_2 + ", city="
				+ city + ", state_=" + state_ + ", country=" + country + ", zip_code=" + zip_code + ", phone_no="
				+ phone_no + ", remarks=" + remarks + ", email_id=" + email_id + ", branch_head=" + branch_head
				+ ", designation=" + designation + ", bankcode_name=" + bankcode_name + ", branchcode_name="
				+ branchcode_name + ", branch_swiftcode=" + branch_swiftcode + ", entry_user=" + entry_user
				+ ", modify_user=" + modify_user + ", verify_user=" + verify_user + ", entry_time=" + entry_time
				+ ", modify_time=" + modify_time + ", verify_time=" + verify_time + ", contact_person1_name="
				+ contact_person1_name + ", contact_person2_name=" + contact_person2_name + ", contact_person3_name="
				+ contact_person3_name + ", contact_person4_name=" + contact_person4_name + ", contact_person5_name="
				+ contact_person5_name + ", contact_person6_name=" + contact_person6_name + ", contact_person1_mobile="
				+ contact_person1_mobile + ", contact_person2_mobile=" + contact_person2_mobile
				+ ", contact_person3_mobile=" + contact_person3_mobile + ", contact_person4_mobile="
				+ contact_person4_mobile + ", contact_person5_mobile=" + contact_person5_mobile
				+ ", contact_person6_mobile=" + contact_person6_mobile + ", contact_person1_email="
				+ contact_person1_email + ", contact_person2_email=" + contact_person2_email
				+ ", contact_person3_email=" + contact_person3_email + ", contact_person4_email="
				+ contact_person4_email + ", contact_person5_email=" + contact_person5_email
				+ ", contact_person6_email=" + contact_person6_email + ", ph_countrycode=" + ph_countrycode
				+ ", cp1_countrycode=" + cp1_countrycode + ", cp2_countrycode=" + cp2_countrycode + ", cp3_countrycode="
				+ cp3_countrycode + ", cp4_countrycode=" + cp4_countrycode + ", cp5_countrycode=" + cp5_countrycode
				+ ", cp6_countrycode=" + cp6_countrycode + ", entry_flag=" + entry_flag + ", modify_flag=" + modify_flag
				+ ", getContact_person4_name()=" + getContact_person4_name() + ", getContact_person5_name()="
				+ getContact_person5_name() + ", getContact_person6_name()=" + getContact_person6_name()
				+ ", getContact_person4_mobile()=" + getContact_person4_mobile() + ", getContact_person5_mobile()="
				+ getContact_person5_mobile() + ", getContact_person6_mobile()=" + getContact_person6_mobile()
				+ ", getContact_person4_email()=" + getContact_person4_email() + ", getContact_person5_email()="
				+ getContact_person5_email() + ", getContact_person6_email()=" + getContact_person6_email()
				+ ", getPh_countrycode()=" + getPh_countrycode() + ", getCp1_countrycode()=" + getCp1_countrycode()
				+ ", getCp2_countrycode()=" + getCp2_countrycode() + ", getCp3_countrycode()=" + getCp3_countrycode()
				+ ", getCp4_countrycode()=" + getCp4_countrycode() + ", getCp5_countrycode()=" + getCp5_countrycode()
				+ ", getCp6_countrycode()=" + getCp6_countrycode() + ", getMerchant_user_id()=" + getMerchant_user_id()
				+ ", getMerchant_name()=" + getMerchant_name() + ", getUnit_id()=" + getUnit_id()
				+ ", getLocation_detail()=" + getLocation_detail() + ", getUnit_type()=" + getUnit_type()
				+ ", getUnit_name()=" + getUnit_name() + ", getAddress_1()=" + getAddress_1() + ", getAddress_2()="
				+ getAddress_2() + ", getCity()=" + getCity() + ", getState_()=" + getState_() + ", getCountry()="
				+ getCountry() + ", getZip_code()=" + getZip_code() + ", getPhone_no()=" + getPhone_no()
				+ ", getRemarks()=" + getRemarks() + ", getEmail_id()=" + getEmail_id() + ", getBranch_head()="
				+ getBranch_head() + ", getDesignation()=" + getDesignation() + ", getContact_person1_name()="
				+ getContact_person1_name() + ", getContact_person1_mobile()=" + getContact_person1_mobile()
				+ ", getContact_person2_name()=" + getContact_person2_name() + ", getContact_person2_mobile()="
				+ getContact_person2_mobile() + ", getBankcode_name()=" + getBankcode_name() + ", getBranchcode_name()="
				+ getBranchcode_name() + ", getBranch_swiftcode()=" + getBranch_swiftcode() + ", getEntry_user()="
				+ getEntry_user() + ", getModify_user()=" + getModify_user() + ", getVerify_user()=" + getVerify_user()
				+ ", getEntry_time()=" + getEntry_time() + ", getModify_time()=" + getModify_time()
				+ ", getVerify_time()=" + getVerify_time() + ", getContact_person3_name()=" + getContact_person3_name()
				+ ", getContact_person3_mobile()=" + getContact_person3_mobile() + ", getContact_person1_email()="
				+ getContact_person1_email() + ", getContact_person2_email()=" + getContact_person2_email()
				+ ", getContact_person3_email()=" + getContact_person3_email() + ", getEntry_flag()=" + getEntry_flag()
				+ ", getModify_flag()=" + getModify_flag() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}

	public UnitManagementEntity(String merchant_user_id, String merchant_name, String unit_id, String location_detail,
			String unit_type, String unit_name, String address_1, String address_2, String city, String state_,
			String country, String zip_code, String phone_no, String remarks, String email_id, String branch_head,
			String designation, String bankcode_name, String branchcode_name, String branch_swiftcode,
			String entry_user, String modify_user, String verify_user, Date entry_time, Date modify_time,
			Date verify_time, String contact_person1_name, String contact_person2_name, String contact_person3_name,
			String contact_person4_name, String contact_person5_name, String contact_person6_name,
			BigDecimal contact_person1_mobile, BigDecimal contact_person2_mobile, BigDecimal contact_person3_mobile,
			BigDecimal contact_person4_mobile, BigDecimal contact_person5_mobile, BigDecimal contact_person6_mobile,
			String contact_person1_email, String contact_person2_email, String contact_person3_email,
			String contact_person4_email, String contact_person5_email, String contact_person6_email,
			String ph_countrycode, String cp1_countrycode, String cp2_countrycode, String cp3_countrycode,
			String cp4_countrycode, String cp5_countrycode, String cp6_countrycode, String entry_flag,
			String modify_flag,String del_flg,String delete_user,Date delete_date) {
		super();
		
		this.del_flg=del_flg;
		this.delete_user=delete_user;
		this.delete_date=delete_date;
		this.merchant_user_id = merchant_user_id;
		this.merchant_name = merchant_name;
		this.unit_id = unit_id;
		this.location_detail = location_detail;
		this.unit_type = unit_type;
		this.unit_name = unit_name;
		this.address_1 = address_1;
		this.address_2 = address_2;
		this.city = city;
		this.state_ = state_;
		this.country = country;
		this.zip_code = zip_code;
		this.phone_no = phone_no;
		this.remarks = remarks;
		this.email_id = email_id;
		this.branch_head = branch_head;
		this.designation = designation;
		this.bankcode_name = bankcode_name;
		this.branchcode_name = branchcode_name;
		this.branch_swiftcode = branch_swiftcode;
		this.entry_user = entry_user;
		this.modify_user = modify_user;
		this.verify_user = verify_user;
		this.entry_time = entry_time;
		this.modify_time = modify_time;
		this.verify_time = verify_time;
		this.contact_person1_name = contact_person1_name;
		this.contact_person2_name = contact_person2_name;
		this.contact_person3_name = contact_person3_name;
		this.contact_person4_name = contact_person4_name;
		this.contact_person5_name = contact_person5_name;
		this.contact_person6_name = contact_person6_name;
		this.contact_person1_mobile = contact_person1_mobile;
		this.contact_person2_mobile = contact_person2_mobile;
		this.contact_person3_mobile = contact_person3_mobile;
		this.contact_person4_mobile = contact_person4_mobile;
		this.contact_person5_mobile = contact_person5_mobile;
		this.contact_person6_mobile = contact_person6_mobile;
		this.contact_person1_email = contact_person1_email;
		this.contact_person2_email = contact_person2_email;
		this.contact_person3_email = contact_person3_email;
		this.contact_person4_email = contact_person4_email;
		this.contact_person5_email = contact_person5_email;
		this.contact_person6_email = contact_person6_email;
		this.ph_countrycode = ph_countrycode;
		this.cp1_countrycode = cp1_countrycode;
		this.cp2_countrycode = cp2_countrycode;
		this.cp3_countrycode = cp3_countrycode;
		this.cp4_countrycode = cp4_countrycode;
		this.cp5_countrycode = cp5_countrycode;
		this.cp6_countrycode = cp6_countrycode;
		this.entry_flag = entry_flag;
		this.modify_flag = modify_flag;
	}

	public UnitManagementEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

}
