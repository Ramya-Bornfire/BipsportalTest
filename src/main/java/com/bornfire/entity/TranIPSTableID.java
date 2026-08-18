package com.bornfire.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class TranIPSTableID implements Serializable {

	private String msg_id;
	
	private String msg_sub_type;

	public String getMsg_id() {
		return msg_id;
	}

	public void setMsg_id(String msg_id) {
		this.msg_id = msg_id;
	}

	public String getMsg_sub_type() {
		return msg_sub_type;
	}

	public void setMsg_sub_type(String msg_sub_type) {
		this.msg_sub_type = msg_sub_type;
	}
	
	
	
}
