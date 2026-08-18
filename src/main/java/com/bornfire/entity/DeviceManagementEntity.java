package com.bornfire.entity;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "BIPS_MERCHANT_DEVICE_MANAGEMENT")
public class DeviceManagementEntity{
	
	private String	alternate_user_id;
	private String	approved_user;
	private String	default_user_id;
	private String	defined_user;
	private Date	delete_time;
	private String	delete_user;
	@Id
	private String	device_id;
	private String	device_identification_no;
	private String	device_machine_id;
	private String	device_make;
	private String	device_model;
	private String	device_name;
	private Date	device_removeddate;
	private String	device_status;
	private String	disable_flag;
	private String	entry_flag;
	private Date	entry_time;
	private String	entry_user;
	private String	face_recognition_enabled;
	private String	fingerprint_enable;
	private String	location;
	private String	merchant_corporate_name;
	private String	merchant_legal_user_id;
	private String	merchant_name;
	private String	merchant_user_id;
	private String	modify_flag;
	private Date	modify_time;
	private String	modify_user;
	private String	remark;
	private String	store_id;
	private String	terminal_id;
	private String	unit_id_d;
	private String	unit_name_d;
	private String	unit_type_d;
	private String	user1;
	private String	user2;
	private Date	verify_time;
	private String	verify_user;
	private String del_flg;
	
	
	public String getDel_flg() {
		return del_flg;
	}
	public void setDel_flg(String del_flg) {
		this.del_flg = del_flg;
	}
	public String getAlternate_user_id() {
		return alternate_user_id;
	}
	public void setAlternate_user_id(String alternate_user_id) {
		this.alternate_user_id = alternate_user_id;
	}
	public String getApproved_user() {
		return approved_user;
	}
	public void setApproved_user(String approved_user) {
		this.approved_user = approved_user;
	}
	public String getDefault_user_id() {
		return default_user_id;
	}
	public void setDefault_user_id(String default_user_id) {
		this.default_user_id = default_user_id;
	}
	public String getDefined_user() {
		return defined_user;
	}
	public void setDefined_user(String defined_user) {
		this.defined_user = defined_user;
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
	public String getDevice_id() {
		return device_id;
	}
	public void setDevice_id(String device_id) {
		this.device_id = device_id;
	}
	public String getDevice_identification_no() {
		return device_identification_no;
	}
	public void setDevice_identification_no(String device_identification_no) {
		this.device_identification_no = device_identification_no;
	}
	public String getDevice_machine_id() {
		return device_machine_id;
	}
	public void setDevice_machine_id(String device_machine_id) {
		this.device_machine_id = device_machine_id;
	}
	public String getDevice_make() {
		return device_make;
	}
	public void setDevice_make(String device_make) {
		this.device_make = device_make;
	}
	public String getDevice_model() {
		return device_model;
	}
	public void setDevice_model(String device_model) {
		this.device_model = device_model;
	}
	public String getDevice_name() {
		return device_name;
	}
	public void setDevice_name(String device_name) {
		this.device_name = device_name;
	}
	public Date getDevice_removeddate() {
		return device_removeddate;
	}
	public void setDevice_removeddate(Date device_removeddate) {
		this.device_removeddate = device_removeddate;
	}
	public String getDevice_status() {
		return device_status;
	}
	public void setDevice_status(String device_status) {
		this.device_status = device_status;
	}
	public String getDisable_flag() {
		return disable_flag;
	}
	public void setDisable_flag(String disable_flag) {
		this.disable_flag = disable_flag;
	}
	public String getEntry_flag() {
		return entry_flag;
	}
	public void setEntry_flag(String entry_flag) {
		this.entry_flag = entry_flag;
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
	public String getFace_recognition_enabled() {
		return face_recognition_enabled;
	}
	public void setFace_recognition_enabled(String face_recognition_enabled) {
		this.face_recognition_enabled = face_recognition_enabled;
	}
	public String getFingerprint_enable() {
		return fingerprint_enable;
	}
	public void setFingerprint_enable(String fingerprint_enable) {
		this.fingerprint_enable = fingerprint_enable;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getMerchant_corporate_name() {
		return merchant_corporate_name;
	}
	public void setMerchant_corporate_name(String merchant_corporate_name) {
		this.merchant_corporate_name = merchant_corporate_name;
	}
	public String getMerchant_legal_user_id() {
		return merchant_legal_user_id;
	}
	public void setMerchant_legal_user_id(String merchant_legal_user_id) {
		this.merchant_legal_user_id = merchant_legal_user_id;
	}
	public String getMerchant_name() {
		return merchant_name;
	}
	public void setMerchant_name(String merchant_name) {
		this.merchant_name = merchant_name;
	}
	public String getMerchant_user_id() {
		return merchant_user_id;
	}
	public void setMerchant_user_id(String merchant_user_id) {
		this.merchant_user_id = merchant_user_id;
	}
	public String getModify_flag() {
		return modify_flag;
	}
	public void setModify_flag(String modify_flag) {
		this.modify_flag = modify_flag;
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
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getStore_id() {
		return store_id;
	}
	public void setStore_id(String store_id) {
		this.store_id = store_id;
	}
	public String getTerminal_id() {
		return terminal_id;
	}
	public void setTerminal_id(String terminal_id) {
		this.terminal_id = terminal_id;
	}
	public String getUnit_id_d() {
		return unit_id_d;
	}
	public void setUnit_id_d(String unit_id_d) {
		this.unit_id_d = unit_id_d;
	}
	public String getUnit_name_d() {
		return unit_name_d;
	}
	public void setUnit_name_d(String unit_name_d) {
		this.unit_name_d = unit_name_d;
	}
	public String getUnit_type_d() {
		return unit_type_d;
	}
	public void setUnit_type_d(String unit_type_d) {
		this.unit_type_d = unit_type_d;
	}
	public String getUser1() {
		return user1;
	}
	public void setUser1(String user1) {
		this.user1 = user1;
	}
	public String getUser2() {
		return user2;
	}
	public void setUser2(String user2) {
		this.user2 = user2;
	}
	public Date getVerify_time() {
		return verify_time;
	}
	public void setVerify_time(Date verify_time) {
		this.verify_time = verify_time;
	}
	public String getVerify_user() {
		return verify_user;
	}
	public void setVerify_user(String verify_user) {
		this.verify_user = verify_user;
	}
	public DeviceManagementEntity(String alternate_user_id, String approved_user, String default_user_id,
			String defined_user, Date delete_time, String delete_user, String device_id,
			String device_identification_no, String device_machine_id, String device_make, String device_model,
			String device_name, Date device_removeddate, String device_status, String disable_flag, String entry_flag,
			Date entry_time, String entry_user, String face_recognition_enabled, String fingerprint_enable,
			String location, String merchant_corporate_name, String merchant_legal_user_id, String merchant_name,
			String merchant_user_id, String modify_flag, Date modify_time, String modify_user, String remark,
			String store_id, String terminal_id, String unit_id_d, String unit_name_d, String unit_type_d, String user1,
			String user2, Date verify_time, String verify_user,String del_flg) {
		super();
		this.alternate_user_id = alternate_user_id;
		this.approved_user = approved_user;
		this.default_user_id = default_user_id;
		this.defined_user = defined_user;
		this.delete_time = delete_time;
		this.delete_user = delete_user;
		this.device_id = device_id;
		this.device_identification_no = device_identification_no;
		this.device_machine_id = device_machine_id;
		this.device_make = device_make;
		this.device_model = device_model;
		this.device_name = device_name;
		this.device_removeddate = device_removeddate;
		this.device_status = device_status;
		this.disable_flag = disable_flag;
		this.entry_flag = entry_flag;
		this.entry_time = entry_time;
		this.entry_user = entry_user;
		this.face_recognition_enabled = face_recognition_enabled;
		this.fingerprint_enable = fingerprint_enable;
		this.location = location;
		this.merchant_corporate_name = merchant_corporate_name;
		this.merchant_legal_user_id = merchant_legal_user_id;
		this.merchant_name = merchant_name;
		this.merchant_user_id = merchant_user_id;
		this.modify_flag = modify_flag;
		this.modify_time = modify_time;
		this.modify_user = modify_user;
		this.remark = remark;
		this.store_id = store_id;
		this.terminal_id = terminal_id;
		this.unit_id_d = unit_id_d;
		this.unit_name_d = unit_name_d;
		this.unit_type_d = unit_type_d;
		this.user1 = user1;
		this.user2 = user2;
		this.verify_time = verify_time;
		this.verify_user = verify_user;
		this.del_flg = del_flg;
	}
	public DeviceManagementEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "DeviceManagementEntity [alternate_user_id=" + alternate_user_id + ", approved_user=" + approved_user
				+ ", default_user_id=" + default_user_id + ", defined_user=" + defined_user + ", delete_time="
				+ delete_time + ", delete_user=" + delete_user + ", device_id=" + device_id
				+ ", device_identification_no=" + device_identification_no + ", device_machine_id=" + device_machine_id
				+ ", device_make=" + device_make + ", device_model=" + device_model + ", device_name=" + device_name
				+ ", device_removeddate=" + device_removeddate + ", device_status=" + device_status + ", disable_flag="
				+ disable_flag + ", entry_flag=" + entry_flag + ", entry_time=" + entry_time + ", entry_user="
				+ entry_user + ", face_recognition_enabled=" + face_recognition_enabled + ", fingerprint_enable="
				+ fingerprint_enable + ", location=" + location + ", merchant_corporate_name=" + merchant_corporate_name
				+ ", merchant_legal_user_id=" + merchant_legal_user_id + ", merchant_name=" + merchant_name
				+ ", merchant_user_id=" + merchant_user_id + ", modify_flag=" + modify_flag + ", modify_time="
				+ modify_time + ", modify_user=" + modify_user + ", remark=" + remark + ", store_id=" + store_id
				+ ", terminal_id=" + terminal_id + ", unit_id_d=" + unit_id_d + ", unit_name_d=" + unit_name_d
				+ ", unit_type_d=" + unit_type_d + ", user1=" + user1 + ", user2=" + user2 + ", verify_time="
				+ verify_time + ", verify_user=" + verify_user + ", del_flg=" + del_flg + "]";
	}
	
	
}
