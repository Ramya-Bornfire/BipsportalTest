package com.bornfire.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Embeddable;

@Embeddable
public class Userdetailsid implements Serializable{

	private BigDecimal	customer_id;
	private String	user_id;
	private BigDecimal	mobile_num;
	
	
	public Userdetailsid() {
		super();
		// TODO Auto-generated constructor stub
	}
	public BigDecimal getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(BigDecimal customer_id) {
		this.customer_id = customer_id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public BigDecimal getMobile_num() {
		return mobile_num;
	}
	public void setMobile_num(BigDecimal mobile_num) {
		this.mobile_num = mobile_num;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((customer_id == null) ? 0 : customer_id.hashCode());
		result = prime * result + ((mobile_num == null) ? 0 : mobile_num.hashCode());
		result = prime * result + ((user_id == null) ? 0 : user_id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Userdetailsid other = (Userdetailsid) obj;
		if (customer_id == null) {
			if (other.customer_id != null)
				return false;
		} else if (!customer_id.equals(other.customer_id))
			return false;
		if (mobile_num == null) {
			if (other.mobile_num != null)
				return false;
		} else if (!mobile_num.equals(other.mobile_num))
			return false;
		if (user_id == null) {
			if (other.user_id != null)
				return false;
		} else if (!user_id.equals(other.user_id))
			return false;
		return true;
	}
	public Userdetailsid(BigDecimal customer_id, String user_id, BigDecimal mobile_num) {
		this.customer_id = customer_id;
		this.user_id = user_id;
		this.mobile_num = mobile_num;
	}
	public Userdetailsid(String userid) {
		// TODO Auto-generated constructor stub
		this.user_id = userid;
	}
	
	
}
