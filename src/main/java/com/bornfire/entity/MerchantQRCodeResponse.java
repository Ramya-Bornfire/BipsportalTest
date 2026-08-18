package com.bornfire.entity;

public class MerchantQRCodeResponse {
    private String base64QR;
	public MerchantQRCodeResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getBase64QR() {
		return base64QR;
	}
	public void setBase64QR(String base64qr) {
		base64QR = base64qr;
	}
	public MerchantQRCodeResponse(String base64QR) {
        this.base64QR = base64QR;
    }

}
