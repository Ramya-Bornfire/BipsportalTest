package com.bornfire.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "SERVICE_REQUEST_MONITORING")
public class ServiceReqEntity {
	@Id
	private String request_id;
	private String merchant_id;
	private String request_type;
	private String request_description;
	private String steps_to_reproduce;
	private String error_message;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private Date request_date;
	private String priority;
	private String contact_email;
	private String contact_phone;
	 private String countrycode;
	private String additional_notes;
	private String status;
	private String approved_by;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private Date approved_date;
	private String assign_to;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private Date assigned_date;
	private String entry_user;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private Date entry_date;
	private String entry_flag;
	private String del_flag;
	private String unit_id;
	private String user_id;
	public String getRequest_id() {
		return request_id;
	}
	public void setRequest_id(String request_id) {
		this.request_id = request_id;
	}
	public String getMerchant_id() {
		return merchant_id;
	}
	public void setMerchant_id(String merchant_id) {
		this.merchant_id = merchant_id;
	}
	public String getRequest_type() {
		return request_type;
	}
	public void setRequest_type(String request_type) {
		this.request_type = request_type;
	}
	public String getRequest_description() {
		return request_description;
	}
	public void setRequest_description(String request_description) {
		this.request_description = request_description;
	}
	public String getSteps_to_reproduce() {
		return steps_to_reproduce;
	}
	public void setSteps_to_reproduce(String steps_to_reproduce) {
		this.steps_to_reproduce = steps_to_reproduce;
	}
	public String getError_message() {
		return error_message;
	}
	public void setError_message(String error_message) {
		this.error_message = error_message;
	}
	public Date getRequest_date() {
		return request_date;
	}
	public void setRequest_date(Date request_date) {
		this.request_date = request_date;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public String getContact_email() {
		return contact_email;
	}
	public void setContact_email(String contact_email) {
		this.contact_email = contact_email;
	}
	public String getContact_phone() {
		return contact_phone;
	}
	public void setContact_phone(String contact_phone) {
		this.contact_phone = contact_phone;
	}
	public String getCountrycode() {
		return countrycode;
	}
	public void setCountrycode(String countrycode) {
		this.countrycode = countrycode;
	}
	public String getAdditional_notes() {
		return additional_notes;
	}
	public void setAdditional_notes(String additional_notes) {
		this.additional_notes = additional_notes;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getApproved_by() {
		return approved_by;
	}
	public void setApproved_by(String approved_by) {
		this.approved_by = approved_by;
	}
	public Date getApproved_date() {
		return approved_date;
	}
	public void setApproved_date(Date approved_date) {
		this.approved_date = approved_date;
	}
	public String getAssign_to() {
		return assign_to;
	}
	public void setAssign_to(String assign_to) {
		this.assign_to = assign_to;
	}
	public Date getAssigned_date() {
		return assigned_date;
	}
	public void setAssigned_date(Date assigned_date) {
		this.assigned_date = assigned_date;
	}
	public String getEntry_user() {
		return entry_user;
	}
	public void setEntry_user(String entry_user) {
		this.entry_user = entry_user;
	}
	public Date getEntry_date() {
		return entry_date;
	}
	public void setEntry_date(Date entry_date) {
		this.entry_date = entry_date;
	}
	public String getEntry_flag() {
		return entry_flag;
	}
	public void setEntry_flag(String entry_flag) {
		this.entry_flag = entry_flag;
	}
	public String getDel_flag() {
		return del_flag;
	}
	public void setDel_flag(String del_flag) {
		this.del_flag = del_flag;
	}
	public String getUnit_id() {
		return unit_id;
	}
	public void setUnit_id(String unit_id) {
		this.unit_id = unit_id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public ServiceReqEntity(String request_id, String merchant_id, String request_type, String request_description,
			String steps_to_reproduce, String error_message, Date request_date, String priority, String contact_email,
			String contact_phone, String countrycode, String additional_notes, String status, String approved_by,
			Date approved_date, String assign_to, Date assigned_date, String entry_user, Date entry_date,
			String entry_flag, String del_flag, String unit_id, String user_id) {
		super();
		this.request_id = request_id;
		this.merchant_id = merchant_id;
		this.request_type = request_type;
		this.request_description = request_description;
		this.steps_to_reproduce = steps_to_reproduce;
		this.error_message = error_message;
		this.request_date = request_date;
		this.priority = priority;
		this.contact_email = contact_email;
		this.contact_phone = contact_phone;
		this.countrycode = countrycode;
		this.additional_notes = additional_notes;
		this.status = status;
		this.approved_by = approved_by;
		this.approved_date = approved_date;
		this.assign_to = assign_to;
		this.assigned_date = assigned_date;
		this.entry_user = entry_user;
		this.entry_date = entry_date;
		this.entry_flag = entry_flag;
		this.del_flag = del_flag;
		this.unit_id = unit_id;
		this.user_id = user_id;
	}
	public ServiceReqEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "ServiceReqEntity [request_id=" + request_id + ", merchant_id=" + merchant_id + ", request_type="
				+ request_type + ", request_description=" + request_description + ", steps_to_reproduce="
				+ steps_to_reproduce + ", error_message=" + error_message + ", request_date=" + request_date
				+ ", priority=" + priority + ", contact_email=" + contact_email + ", contact_phone=" + contact_phone
				+ ", countrycode=" + countrycode + ", additional_notes=" + additional_notes + ", status=" + status
				+ ", approved_by=" + approved_by + ", approved_date=" + approved_date + ", assign_to=" + assign_to
				+ ", assigned_date=" + assigned_date + ", entry_user=" + entry_user + ", entry_date=" + entry_date
				+ ", entry_flag=" + entry_flag + ", del_flag=" + del_flag + ", unit_id=" + unit_id + ", user_id="
				+ user_id + "]";
	}
	
	
	
}
