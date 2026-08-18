package com.bornfire.entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Table(name = "BIPS_ALERT_TABLE")
public class AlertEntity {
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "ALERT_ID")
	    private int alertId;

	    @Column(name = "ALERT_TYPE")
	    private String alertType;

	    @Column(name = "ALERT_MESSAGE")
	    private String alertMessage;

	    @Column(name = "SCREEN_NAME")
	    private String screenName;

	
	    public AlertEntity() {
			super();
			// TODO Auto-generated constructor stub
		}

		public AlertEntity(int alertId, String alertType, String alertMessage, String screenName) {
			super();
			this.alertId = alertId;
			this.alertType = alertType;
			this.alertMessage = alertMessage;
			this.screenName = screenName;
		}

		// Getters and Setters
	    public int getAlertId() {
	        return alertId;
	    }

	    public void setAlertId(int alertId) {
	        this.alertId = alertId;
	    }

	    public String getAlertType() {
	        return alertType;
	    }

	    public void setAlertType(String alertType) {
	        this.alertType = alertType;
	    }

	    public String getAlertMessage() {
	        return alertMessage;
	    }

	    public void setAlertMessage(String alertMessage) {
	        this.alertMessage = alertMessage;
	    }

	    public String getScreenName() {
	        return screenName;
	    }

	    public void setScreenName(String screenName) {
	        this.screenName = screenName;
	    }

	    @Override
	    public String toString() {
	        return "BipsAlert{" +
	                "alertId=" + alertId +
	                ", alertType='" + alertType + '\'' +
	                ", alertMessage='" + alertMessage + '\'' +
	                ", screenName='" + screenName + '\'' +
	                '}';
	    }
}
