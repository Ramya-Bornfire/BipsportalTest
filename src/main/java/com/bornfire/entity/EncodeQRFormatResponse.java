package com.bornfire.entity;

import java.util.List;

public class EncodeQRFormatResponse {

	private boolean isSuccess;
	
	private String qrMsg;
	private String error;
	
	private List<String> error_desc;

	public boolean isSuccess() {
		return isSuccess;
	}

	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public List<String> getError_desc() {
		return error_desc;
	}

	public void setError_desc(List<String> error_desc) {
		this.error_desc = error_desc;
	}

	public String getQrMsg() {
		return qrMsg;
	}

	public void setQrMsg(String qrMsg) {
		this.qrMsg = qrMsg;
	}
	
}
