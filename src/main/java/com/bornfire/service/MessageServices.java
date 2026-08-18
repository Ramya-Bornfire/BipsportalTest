package com.bornfire.service;

import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.bornfire.entity.IPSAuditRepo;

@Service
@EnableScheduling
public class MessageServices {

	private static final Logger logger = LoggerFactory.getLogger(MessageServices.class);

	@Autowired
	Environment env;

	@Autowired
	IPSAuditRepo ipsAuditTableRep;

	@Autowired
	RestTemplate restTemplate;

	// SMS

	public void sendSMStoMerchant(String msgInfo, String phoneNumber) {

		String Ipaddress = env.getProperty("sms.IPaddress");
		String SMSport = env.getProperty("sms.SMSport");
		String dcode = env.getProperty("sms.dcode");
		String subuid = env.getProperty("sms.subuid");
		String pwd = env.getProperty("sms.pwd");
		String sender = env.getProperty("sms.sender");
		String intflag = env.getProperty("sms.intflag");
		String msgtype = env.getProperty("sms.msgtype");
		String alert = env.getProperty("sms.alert");

		if (Objects.nonNull(msgInfo) && Objects.nonNull(phoneNumber)) {
			//System.out.println("SMS Test Msg : " + msgInfo);
			//System.out.println("SMS Test Phone Num : " + phoneNumber);
			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<String> entity = new HttpEntity<>(httpHeaders);
			ResponseEntity<String> response = null;
			String URL = new StringBuilder("http://").append(Ipaddress).append(":").append(SMSport)
					.append("/axiomdbrec/pushlistener?dcode=").append(dcode).append("&subuid=").append(subuid)
					.append("&pwd=").append(pwd).append("&sender=").append(sender).append("&pno=").append(phoneNumber)
					.append("&msgtxt=").append(msgInfo).append("&intflag=").append(intflag).append("&msgtype=")
					.append(msgtype).append("&alert=").append(alert).toString();
			//System.out.println("SMS MSG URL : " + URL);
			try {
				logger.info("Sending message to SMS API");
				response = restTemplate.postForEntity(URL, entity, String.class);
				if (response.getStatusCode() == HttpStatus.OK) {
					//System.out.println("Response Body: " + response.getBody());
				} else {
					logger.warn("Non-OK status received: " + response.getStatusCode());
					//System.out.println("Non-OK status received: " + response.getStatusCode());
				}
			} catch (Exception ex) {
				logger.info("Exception occurred while sending SMS to merchant");
				logger.info(ex.getLocalizedMessage());
				logger.error("Exception details: ", ex);
			}
		} else {
			logger.info("Exception occurred while sending SMS Request");
		}
	}

	// OTP 
	
	//@Scheduled(cron = "*/10 * * * * *")
	public ResponseEntity<String> sendOTPtoMerchant(String msgInfo, String phoneNumber) {

		String Ipaddress = env.getProperty("otp.IPaddress");
		String OTPport = env.getProperty("otp.OTPport");
		String dcode = env.getProperty("otp.dcode");
		String subuid = env.getProperty("otp.subuid");
		String pwd = env.getProperty("otp.pwd");
		String sender = env.getProperty("otp.sender");
		String intflag = env.getProperty("otp.intflag");
		String msgtype = env.getProperty("otp.msgtype");
		String alert = env.getProperty("otp.alert");

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<>(httpHeaders);
		ResponseEntity<String> response = null;

		if (Objects.nonNull(msgInfo) && Objects.nonNull(phoneNumber)) {
			//System.out.println("OTP Test Msg : " + msgInfo);
			//System.out.println("OTP Test Phone Num : " + phoneNumber);
			String URL = new StringBuilder("http://").append(Ipaddress).append(":").append(OTPport)
					.append("/axiomdbrec/pushlistener?dcode=").append(dcode).append("&subuid=").append(subuid)
					.append("&pwd=").append(pwd).append("&sender=").append(sender).append("&pno=").append(phoneNumber)
					.append("&msgtxt=").append(msgInfo).append("&intflag=").append(intflag).append("&msgtype=")
					.append(msgtype).append("&alert=").append(alert).toString();
			//System.out.println("OTP MSG URL : " + URL);
			try {
				logger.info("Sending message to OTP API");
				response = restTemplate.postForEntity(URL, entity, String.class);
				if (response.getStatusCode() == HttpStatus.OK) {
					//System.out.println("Response Body: " + response.getBody());
				} else {
					logger.warn("Non-OK status received: " + response.getStatusCode());
					//System.out.println("Non-OK status received: " + response.getStatusCode());
				}
			} catch (Exception ex) {
				logger.info("Exception occurred while sending OTP to merchant");
				logger.info(ex.getLocalizedMessage());
				logger.error("Exception details: ", ex);
			}
		} else {
			logger.info("Exception occurred while sending OTP Request");
		}
		return response;
	}

}
