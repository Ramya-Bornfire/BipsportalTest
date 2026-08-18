package com.bornfire.entity;


import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "BIPS_NOTIFICATION")
public class BIPS_Notification_Entity {

	@DateTimeFormat(pattern="dd-MM-yyyy")
	private Date	message_date;
	@Id
	private String	message_type;
	private String	from_user;
	private String	to_user;
	private String	subject;
	private String	message_text;
	private String	status;
	private String	readflag;
	public Date getMessage_date() {
		return message_date;
	}
	public void setMessage_date(Date message_date) {
		this.message_date = message_date;
	}
	public String getMessage_type() {
		return message_type;
	}
	public void setMessage_type(String message_type) {
		this.message_type = message_type;
	}
	public String getFrom_user() {
		return from_user;
	}
	public void setFrom_user(String from_user) {
		this.from_user = from_user;
	}
	public String getTo_user() {
		return to_user;
	}
	public void setTo_user(String to_user) {
		this.to_user = to_user;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getMessage_text() {
		return message_text;
	}
	public void setMessage_text(String message_text) {
		this.message_text = message_text;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getReadflag() {
		return readflag;
	}
	public void setReadflag(String readflag) {
		this.readflag = readflag;
	}
	public BIPS_Notification_Entity(Date message_date, String message_type, String from_user, String to_user,
			String subject, String message_text, String status, String readflag) {
		super();
		this.message_date = message_date;
		this.message_type = message_type;
		this.from_user = from_user;
		this.to_user = to_user;
		this.subject = subject;
		this.message_text = message_text;
		this.status = status;
		this.readflag = readflag;
	}
	public BIPS_Notification_Entity() {
		super();
		// TODO Auto-generated constructor stub
	}

	
}
