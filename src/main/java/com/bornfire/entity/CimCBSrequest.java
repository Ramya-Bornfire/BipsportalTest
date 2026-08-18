package com.bornfire.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CimCBSrequest {
	
	@JsonProperty("header")
	private CimCBSrequestHeader header;
	
	@JsonProperty("data")
	private CimCBSrequestData data;

	@JsonProperty("header")
	public CimCBSrequestHeader getHeader() {
		return header;
	}

	@JsonProperty("header")
	public void setHeader(CimCBSrequestHeader header) {
		this.header = header;
	}

	@JsonProperty("data")
	public CimCBSrequestData getData() {
		return data;
	}

	@JsonProperty("data")
	public void setData(CimCBSrequestData data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "CimCBSrequest [header=" + header + ", data=" + data + "]";
	}
	
	
	
	
}
