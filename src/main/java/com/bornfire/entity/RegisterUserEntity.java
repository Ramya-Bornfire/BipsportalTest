package com.bornfire.entity;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "register_user")
public class RegisterUserEntity {

    @Id
    @Column(name = "customer_id")
    private String customerId;

    @Column(name = "customer_name", nullable = false)
    private String customerName;

    @Column(name = "mobile_number", nullable = false)
    private String mobileNumber;

    @Column(name = "acct_number", nullable = false, unique = true)
    private String acctNumber;

    @Column(name = "acct_type", nullable = false)
    private String acctType;

    @Column(name = "email")
    private String email;
    @Column(name = "entry_user")
    private String entryUser;
    @Column(name = "modify_user")
	private String modifyUser;
    @Column(name = "verify_user")
	private String verifyUser;
    @Column(name = "entry_time")
	private Date entryTime;
    @Column(name = "modify_time")
	private Date modifyTime;
    @Column(name = "verify_time")
	private Date verifyTime;
    @Column(name = "del_flag")
	private String delFlag;
    @Column(name = "entity_flag")
	private String entityFlag;
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getAcctNumber() {
		return acctNumber;
	}
	public void setAcctNumber(String acctNumber) {
		this.acctNumber = acctNumber;
	}
	public String getAcctType() {
		return acctType;
	}
	public void setAcctType(String acctType) {
		this.acctType = acctType;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEntryUser() {
		return entryUser;
	}
	public void setEntryUser(String entryUser) {
		this.entryUser = entryUser;
	}
	public String getModifyUser() {
		return modifyUser;
	}
	public void setModifyUser(String modifyUser) {
		this.modifyUser = modifyUser;
	}
	public String getVerifyUser() {
		return verifyUser;
	}
	public void setVerifyUser(String verifyUser) {
		this.verifyUser = verifyUser;
	}
	public Date getEntryTime() {
		return entryTime;
	}
	public void setEntryTime(Date entryTime) {
		this.entryTime = entryTime;
	}
	public Date getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}
	public Date getVerifyTime() {
		return verifyTime;
	}
	public void setVerifyTime(Date verifyTime) {
		this.verifyTime = verifyTime;
	}
	public String getDelFlag() {
		return delFlag;
	}
	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}
	public String getEntityFlag() {
		return entityFlag;
	}
	public void setEntityFlag(String entityFlag) {
		this.entityFlag = entityFlag;
	}
	public RegisterUserEntity(String customerId, String customerName, String mobileNumber, String acctNumber,
			String acctType, String email, String entryUser, String modifyUser, String verifyUser, Date entryTime,
			Date modifyTime, Date verifyTime, String delFlag, String entityFlag) {
		super();
		this.customerId = customerId;
		this.customerName = customerName;
		this.mobileNumber = mobileNumber;
		this.acctNumber = acctNumber;
		this.acctType = acctType;
		this.email = email;
		this.entryUser = entryUser;
		this.modifyUser = modifyUser;
		this.verifyUser = verifyUser;
		this.entryTime = entryTime;
		this.modifyTime = modifyTime;
		this.verifyTime = verifyTime;
		this.delFlag = delFlag;
		this.entityFlag = entityFlag;
	}
	public RegisterUserEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "RegisterUserEntity [customerId=" + customerId + ", customerName=" + customerName + ", mobileNumber="
				+ mobileNumber + ", acctNumber=" + acctNumber + ", acctType=" + acctType + ", email=" + email
				+ ", entryUser=" + entryUser + ", modifyUser=" + modifyUser + ", verifyUser=" + verifyUser
				+ ", entryTime=" + entryTime + ", modifyTime=" + modifyTime + ", verifyTime=" + verifyTime
				+ ", delFlag=" + delFlag + ", entityFlag=" + entityFlag + "]";
	}

	
    
}
