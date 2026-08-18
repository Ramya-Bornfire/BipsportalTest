package com.bornfire.entity;

import java.security.Timestamp;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "NOTIFICATION_PARM_MASTER")
public class NotificationEntity {
		@Id
	   private String record_srl_no;
		@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
		@DateTimeFormat(pattern = "dd-MM-yyyy")
	    private Date record_date;
	    private String tran_category;
	    private String notification_event_no;
	    private String notification_event_desc;
	    private String notification_limit;
	    private String notification_user_1;
	    private String notification_user_2;
	    private String notification_user_3;
	    private String notification_sms_flg;
	    private String notification_mobile_1;
	    private String notification_mobile_2;
	    private String notification_mobile_3;
	    private String countrycode_1;
	    private String countrycode_2;
	    private String countrycode_3;
	    private String notification_email_flg;
	    private String notification_email_1;
	    private String notification_email_2;
	    private String notification_email_3;
	    private String alert_flg;
	    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	    @DateTimeFormat(pattern = "dd-MM-yyyy")
	    private Date start_date;
	    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	    @DateTimeFormat(pattern = "dd-MM-yyyy")
	    private Date end_date;
	    private String entity_flg;
	    private String del_flg;
	    @DateTimeFormat(pattern = "dd-MM-yyyy")
		@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	    private Date entry_time;
	    @DateTimeFormat(pattern = "dd-MM-yyyy")
		@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	    private Date modify_time;
	    @DateTimeFormat(pattern = "dd-MM-yyyy")
		@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	    private Date verify_time;
	    private String entry_user;
	    private String modify_user;
	    private String verify_user;
	    private String usercategory;
	    private String channel;
	    private String merchantid;
	    private String unitid;
	    private String frequency;
		public String getRecord_srl_no() {
			return record_srl_no;
		}
		public void setRecord_srl_no(String record_srl_no) {
			this.record_srl_no = record_srl_no;
		}
		public Date getRecord_date() {
			return record_date;
		}
		public void setRecord_date(Date record_date) {
			this.record_date = record_date;
		}
		public String getTran_category() {
			return tran_category;
		}
		public void setTran_category(String tran_category) {
			this.tran_category = tran_category;
		}
		public String getNotification_event_no() {
			return notification_event_no;
		}
		public void setNotification_event_no(String notification_event_no) {
			this.notification_event_no = notification_event_no;
		}
		public String getNotification_event_desc() {
			return notification_event_desc;
		}
		public void setNotification_event_desc(String notification_event_desc) {
			this.notification_event_desc = notification_event_desc;
		}
		public String getNotification_limit() {
			return notification_limit;
		}
		public void setNotification_limit(String notification_limit) {
			this.notification_limit = notification_limit;
		}
		public String getNotification_user_1() {
			return notification_user_1;
		}
		public void setNotification_user_1(String notification_user_1) {
			this.notification_user_1 = notification_user_1;
		}
		public String getNotification_user_2() {
			return notification_user_2;
		}
		public void setNotification_user_2(String notification_user_2) {
			this.notification_user_2 = notification_user_2;
		}
		public String getNotification_user_3() {
			return notification_user_3;
		}
		public void setNotification_user_3(String notification_user_3) {
			this.notification_user_3 = notification_user_3;
		}
		public String getNotification_sms_flg() {
			return notification_sms_flg;
		}
		public void setNotification_sms_flg(String notification_sms_flg) {
			this.notification_sms_flg = notification_sms_flg;
		}
		public String getNotification_mobile_1() {
			return notification_mobile_1;
		}
		public void setNotification_mobile_1(String notification_mobile_1) {
			this.notification_mobile_1 = notification_mobile_1;
		}
		public String getNotification_mobile_2() {
			return notification_mobile_2;
		}
		public void setNotification_mobile_2(String notification_mobile_2) {
			this.notification_mobile_2 = notification_mobile_2;
		}
		public String getNotification_mobile_3() {
			return notification_mobile_3;
		}
		public void setNotification_mobile_3(String notification_mobile_3) {
			this.notification_mobile_3 = notification_mobile_3;
		}
		public String getCountrycode_1() {
			return countrycode_1;
		}
		public void setCountrycode_1(String countrycode_1) {
			this.countrycode_1 = countrycode_1;
		}
		public String getCountrycode_2() {
			return countrycode_2;
		}
		public void setCountrycode_2(String countrycode_2) {
			this.countrycode_2 = countrycode_2;
		}
		public String getCountrycode_3() {
			return countrycode_3;
		}
		public void setCountrycode_3(String countrycode_3) {
			this.countrycode_3 = countrycode_3;
		}
		public String getNotification_email_flg() {
			return notification_email_flg;
		}
		public void setNotification_email_flg(String notification_email_flg) {
			this.notification_email_flg = notification_email_flg;
		}
		public String getNotification_email_1() {
			return notification_email_1;
		}
		public void setNotification_email_1(String notification_email_1) {
			this.notification_email_1 = notification_email_1;
		}
		public String getNotification_email_2() {
			return notification_email_2;
		}
		public void setNotification_email_2(String notification_email_2) {
			this.notification_email_2 = notification_email_2;
		}
		public String getNotification_email_3() {
			return notification_email_3;
		}
		public void setNotification_email_3(String notification_email_3) {
			this.notification_email_3 = notification_email_3;
		}
		public String getAlert_flg() {
			return alert_flg;
		}
		public void setAlert_flg(String alert_flg) {
			this.alert_flg = alert_flg;
		}
		public Date getStart_date() {
			return start_date;
		}
		public void setStart_date(Date start_date) {
			this.start_date = start_date;
		}
		public Date getEnd_date() {
			return end_date;
		}
		public void setEnd_date(Date end_date) {
			this.end_date = end_date;
		}
		public String getEntity_flg() {
			return entity_flg;
		}
		public void setEntity_flg(String entity_flg) {
			this.entity_flg = entity_flg;
		}
		public String getDel_flg() {
			return del_flg;
		}
		public void setDel_flg(String del_flg) {
			this.del_flg = del_flg;
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
		public String getUsercategory() {
			return usercategory;
		}
		public void setUsercategory(String usercategory) {
			this.usercategory = usercategory;
		}
		public String getChannel() {
			return channel;
		}
		public void setChannel(String channel) {
			this.channel = channel;
		}
		public String getMerchantid() {
			return merchantid;
		}
		public void setMerchantid(String merchantid) {
			this.merchantid = merchantid;
		}
		public String getUnitid() {
			return unitid;
		}
		public void setUnitid(String unitid) {
			this.unitid = unitid;
		}
		public String getFrequency() {
			return frequency;
		}
		public void setFrequency(String frequency) {
			this.frequency = frequency;
		}
		public NotificationEntity(String record_srl_no, Date record_date, String tran_category,
				String notification_event_no, String notification_event_desc, String notification_limit,
				String notification_user_1, String notification_user_2, String notification_user_3,
				String notification_sms_flg, String notification_mobile_1, String notification_mobile_2,
				String notification_mobile_3, String countrycode_1, String countrycode_2, String countrycode_3,
				String notification_email_flg, String notification_email_1, String notification_email_2,
				String notification_email_3, String alert_flg, Date start_date, Date end_date, String entity_flg,
				String del_flg, Date entry_time, Date modify_time, Date verify_time, String entry_user,
				String modify_user, String verify_user, String usercategory, String channel, String merchantid,
				String unitid, String frequency) {
			super();
			this.record_srl_no = record_srl_no;
			this.record_date = record_date;
			this.tran_category = tran_category;
			this.notification_event_no = notification_event_no;
			this.notification_event_desc = notification_event_desc;
			this.notification_limit = notification_limit;
			this.notification_user_1 = notification_user_1;
			this.notification_user_2 = notification_user_2;
			this.notification_user_3 = notification_user_3;
			this.notification_sms_flg = notification_sms_flg;
			this.notification_mobile_1 = notification_mobile_1;
			this.notification_mobile_2 = notification_mobile_2;
			this.notification_mobile_3 = notification_mobile_3;
			this.countrycode_1 = countrycode_1;
			this.countrycode_2 = countrycode_2;
			this.countrycode_3 = countrycode_3;
			this.notification_email_flg = notification_email_flg;
			this.notification_email_1 = notification_email_1;
			this.notification_email_2 = notification_email_2;
			this.notification_email_3 = notification_email_3;
			this.alert_flg = alert_flg;
			this.start_date = start_date;
			this.end_date = end_date;
			this.entity_flg = entity_flg;
			this.del_flg = del_flg;
			this.entry_time = entry_time;
			this.modify_time = modify_time;
			this.verify_time = verify_time;
			this.entry_user = entry_user;
			this.modify_user = modify_user;
			this.verify_user = verify_user;
			this.usercategory = usercategory;
			this.channel = channel;
			this.merchantid = merchantid;
			this.unitid = unitid;
			this.frequency = frequency;
		}
		public NotificationEntity() {
			super();
			// TODO Auto-generated constructor stub
		}
		@Override
		public String toString() {
			return "NotificationEntity [record_srl_no=" + record_srl_no + ", record_date=" + record_date
					+ ", tran_category=" + tran_category + ", notification_event_no=" + notification_event_no
					+ ", notification_event_desc=" + notification_event_desc + ", notification_limit="
					+ notification_limit + ", notification_user_1=" + notification_user_1 + ", notification_user_2="
					+ notification_user_2 + ", notification_user_3=" + notification_user_3 + ", notification_sms_flg="
					+ notification_sms_flg + ", notification_mobile_1=" + notification_mobile_1
					+ ", notification_mobile_2=" + notification_mobile_2 + ", notification_mobile_3="
					+ notification_mobile_3 + ", countrycode_1=" + countrycode_1 + ", countrycode_2=" + countrycode_2
					+ ", countrycode_3=" + countrycode_3 + ", notification_email_flg=" + notification_email_flg
					+ ", notification_email_1=" + notification_email_1 + ", notification_email_2="
					+ notification_email_2 + ", notification_email_3=" + notification_email_3 + ", alert_flg="
					+ alert_flg + ", start_date=" + start_date + ", end_date=" + end_date + ", entity_flg=" + entity_flg
					+ ", del_flg=" + del_flg + ", entry_time=" + entry_time + ", modify_time=" + modify_time
					+ ", verify_time=" + verify_time + ", entry_user=" + entry_user + ", modify_user=" + modify_user
					+ ", verify_user=" + verify_user + ", usercategory=" + usercategory + ", channel=" + channel
					+ ", merchantid=" + merchantid + ", unitid=" + unitid + ", frequency=" + frequency + "]";
		}
		
	    
	    
}
