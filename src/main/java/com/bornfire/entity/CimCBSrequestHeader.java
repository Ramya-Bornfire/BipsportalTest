package com.bornfire.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CimCBSrequestHeader {
	
	@JsonProperty("requestUUId")
	private String requestUUId;
	
	@JsonProperty("channelId")
	private String channelId;
	
	@JsonProperty("serviceRequestVersion")
	private String serviceRequestVersion;
	
	@JsonProperty("serviceRequestId")
	private String serviceRequestId;
	
	@JsonProperty("messageDateTime")
	private String messageDateTime;
	
	@JsonProperty("countryCode")
	private String countryCode;

	
	@JsonProperty("requestUUId")
	public String getRequestUUId() {
		return requestUUId;
	}

	@JsonProperty("requestUUId")
	public void setRequestUUId(String requestUUId) {
		this.requestUUId = requestUUId;
	}

	@JsonProperty("channelId")
	public String getChannelId() {
		return channelId;
	}

	@JsonProperty("channelId")
	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	@JsonProperty("serviceRequestVersion")
	public String getServiceRequestVersion() {
		return serviceRequestVersion;
	}

	@JsonProperty("serviceRequestVersion")
	public void setServiceRequestVersion(String serviceRequestVersion) {
		this.serviceRequestVersion = serviceRequestVersion;
	}

	@JsonProperty("serviceRequestId")
	public String getServiceRequestId() {
		return serviceRequestId;
	}

	@JsonProperty("serviceRequestId")
	public void setServiceRequestId(String serviceRequestId) {
		this.serviceRequestId = serviceRequestId;
	}

	@JsonProperty("messageDateTime")
	public String getMessageDateTime() {
		return messageDateTime;
	}

	@JsonProperty("messageDateTime")
	public void setMessageDateTime(String messageDateTime) {
		this.messageDateTime = messageDateTime;
	}
	
	@JsonProperty("countryCode")
	public String getCountryCode() {
		return countryCode;
	}

	@JsonProperty("countryCode")
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	@Override
	public String toString() {
		return "CimCBSrequestHeader [requestUUId=" + requestUUId + ", channelId=" + channelId
				+ ", serviceRequestVersion=" + serviceRequestVersion + ", serviceRequestId=" + serviceRequestId
				+ ", messageDateTime=" + messageDateTime + "]";
	}

	
	
	
}
