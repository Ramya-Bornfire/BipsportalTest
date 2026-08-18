package com.bornfire.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name="BIPS_TRAN_IPS_TABLE")
@IdClass(TranIPSTableID.class)
public class TranIPSTable  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String sequence_unique_id;
	@Id
	private String msg_id;
	@Id
	private String msg_sub_type;
	private String msg_code;
	private String msg_type;
	private Date msg_date;
	private String ack_status;
	private String response_status;
	private String response_error_code;
	private String response_error_desc;
	private String msg_sender;
	private String msg_receiver;
	private String net_mir;
	private String user_ref;
	private String end_end_id;
	
	@Lob 
	private String mx_msg;
	
	
	public String getSequence_unique_id() {
		return sequence_unique_id;
	}
	public void setSequence_unique_id(String sequence_unique_id) {
		this.sequence_unique_id = sequence_unique_id;
	}
	public String getMsg_id() {
		return msg_id;
	}
	public void setMsg_id(String msg_id) {
		this.msg_id = msg_id;
	}
	public String getMsg_code() {
		return msg_code;
	}
	public void setMsg_code(String msg_code) {
		this.msg_code = msg_code;
	}
	public String getMsg_type() {
		return msg_type;
	}
	public void setMsg_type(String msg_type) {
		this.msg_type = msg_type;
	}
	public Date getMsg_date() {
		return msg_date;
	}
	public void setMsg_date(Date msg_date) {
		this.msg_date = msg_date;
	}
	public String getAck_status() {
		return ack_status;
	}
	public void setAck_status(String ack_status) {
		this.ack_status = ack_status;
	}
	public String getResponse_status() {
		return response_status;
	}
	public void setResponse_status(String response_status) {
		this.response_status = response_status;
	}
	public String getResponse_error_code() {
		return response_error_code;
	}
	public void setResponse_error_code(String response_error_code) {
		this.response_error_code = response_error_code;
	}
	public String getResponse_error_desc() {
		return response_error_desc;
	}
	public void setResponse_error_desc(String response_error_desc) {
		this.response_error_desc = response_error_desc;
	}
	public String getMsg_sub_type() {
		return msg_sub_type;
	}
	public void setMsg_sub_type(String msg_sub_type) {
		this.msg_sub_type = msg_sub_type;
	}
	public String getMsg_sender() {
		return msg_sender;
	}
	public void setMsg_sender(String msg_sender) {
		this.msg_sender = msg_sender;
	}
	public String getMsg_receiver() {
		return msg_receiver;
	}
	public void setMsg_receiver(String msg_receiver) {
		this.msg_receiver = msg_receiver;
	}
	public String getNet_mir() {
		return net_mir;
	}
	public void setNet_mir(String net_mir) {
		this.net_mir = net_mir;
	}
	public String getUser_ref() {
		return user_ref;
	}
	public void setUser_ref(String user_ref) {
		this.user_ref = user_ref;
	}
	public String getEnd_end_id() {
		return end_end_id;
	}
	public void setEnd_end_id(String end_end_id) {
		this.end_end_id = end_end_id;
	}
	public String getMx_msg() {
		return mx_msg;
	}
	public void setMx_msg(String mx_msg) {
		this.mx_msg = mx_msg;
	}
	
	
	public TranIPSTable(String sequence_unique_id,String msg_id, String msg_sub_type, String msg_code, String msg_type, Date msg_date,
			String response_status, String response_error_code, String response_error_desc, String msg_sender,
			String msg_receiver, String net_mir, String user_ref, String end_end_id, String mx_msg) {
		super();
		this.sequence_unique_id = sequence_unique_id;
		this.msg_id = msg_id;
		this.msg_sub_type = msg_sub_type;
		this.msg_code = msg_code;
		this.msg_type = msg_type;
		this.msg_date = msg_date;
		this.response_status = response_status;
		this.response_error_code = response_error_code;
		this.response_error_desc = response_error_desc;
		this.msg_sender = msg_sender;
		this.msg_receiver = msg_receiver;
		this.net_mir = net_mir;
		this.user_ref = user_ref;
		this.end_end_id = end_end_id;
		this.mx_msg = mx_msg;
	}
	public TranIPSTable() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
}
