package com.bornfire.entity;

public class LoginResponse {
	 private String status;
	    private String message;
	   
	    // Constructor without LoginEntity
	    public LoginResponse(String status, String message) {
	        this.status = status;
	        this.message = message;
	    }


		public String getStatus() {
			return status;
		}


		public void setStatus(String status) {
			this.status = status;
		}


		public String getMessage() {
			return message;
		}


		public void setMessage(String message) {
			this.message = message;
		}

		
	    
	    
}
