package com.bornfire.exception;

import java.util.List;

public class ErrorResponse {
	private String error;
	private List<String> error_desc;
	private String error_desc_new;

	public ErrorResponse(String error, List<String> error_desc) {
		super();
		this.error = error;
		this.error_desc = error_desc;
	}

	public ErrorResponse(String error, String error_desc_new) {
		// TODO Auto-generated constructor stub
		super();
		this.error = error;
		this.error_desc_new = error_desc_new;
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

	public String getError_desc_new() {
		return error_desc_new;
	}

	public void setError_desc_new(String error_desc_new) {
		this.error_desc_new = error_desc_new;
	}

}
