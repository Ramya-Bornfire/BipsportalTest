package com.bornfire.service;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bornfire.config.Encryption;
import com.bornfire.controller.RateMaintanceController;
import com.bornfire.entity.EncryptionEntity;
import com.bornfire.entity.RateMaintainanceEntity;
import com.bornfire.entity.RateMaintainanceRepository;
import com.bornfire.entity.UserManagementEntity;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
@ConfigurationProperties("output")
@Transactional
public class RateService {

	private static final Logger logger = LoggerFactory.getLogger(RateMaintanceController.class);

	ObjectMapper objectMapper = new ObjectMapper();

	@Autowired
	Encryption encryption;

	@Autowired
	RateMaintainanceRepository ratemaintanceRepository;

	@Autowired
	SessionFactory sessionFactory;
	// All Rate List
	public List<RateMaintainanceEntity> getAllrate() {
		return ratemaintanceRepository.findAll();
	}

	// Creation/Addition of new rate
	public String CreateRateData(EncryptionEntity EncryptedString, String psuDeviceID) throws Exception {
		try {
			logger.info("Encrypted data: {}", EncryptedString.getEncryptedstring());
			String decryptedData = encryption.decrypt(EncryptedString.getEncryptedstring(), psuDeviceID);
			logger.info("Decrypted data: {}", decryptedData);
			RateMaintainanceEntity rateData = objectMapper.readValue(decryptedData, RateMaintainanceEntity.class);
			logger.info("Decrypted user: {}", rateData);
        	BigDecimal SRLNUMBER = (BigDecimal) sessionFactory.getCurrentSession().createNativeQuery("SELECT RATE_SRL.NEXTVAL AS SRL_NO FROM DUAL")
					.getSingleResult();
			rateData.setsrl(SRLNUMBER.toString());
			ratemaintanceRepository.save(rateData);
			Map<String, String> response = new HashMap<>();
			response.put("Status", "Success");
			response.put("Message", "Data received and saved successfully.");
			logger.debug("Rate Data is added successfully");
			String jsonUserData = objectMapper.writeValueAsString(response);
			String encryptedResponse = encryption.encrypt(jsonUserData, psuDeviceID);
			logger.info("Encrypted response: {}", encryptedResponse);
			return encryptedResponse;
		} catch (Exception ex) {
			logger.error("Failed to create rate", ex);
			Map<String, String> response = new HashMap<>();
			response.put("Status", "Failed");
			response.put("Message", "Failed to create a rate");
			try {
				String jsonUserData = objectMapper.writeValueAsString(response);
				String encryptedResponse = encryption.encrypt(jsonUserData, psuDeviceID);
				logger.info("Encrypted response: {}", encryptedResponse);
				return encryptedResponse;
			} catch (Exception e) {
				logger.error("Failed to encrypt error response", e);
				throw new RuntimeException("Failed to create rate and encrypt error response", e);
			}
		}
	}

	// UpdateRateData
	public String updateRateService(EncryptionEntity EncryptedString, String psuDeviceID) throws Exception {
		try {
			//System.out.println("Encrypted data: " + EncryptedString.getEncryptedstring());
			String decryptedData = encryption.decrypt(EncryptedString.getEncryptedstring(), psuDeviceID);
			//System.out.println("Decrypted data: " + decryptedData);
			RateMaintainanceEntity updaterate = objectMapper.readValue(decryptedData, RateMaintainanceEntity.class);
			//System.out.println("Decrypted user: " + updaterate);
			RateMaintainanceEntity existingrate = ratemaintanceRepository.findByMerchantUserId(updaterate.getsrl());
			if (Objects.nonNull(existingrate)) {
				Optional.ofNullable(updaterate.getAudit_date()).ifPresent(existingrate::setAudit_date);
				Optional.ofNullable(updaterate.getBilling_currency()).ifPresent(existingrate::setBilling_currency);
				Optional.ofNullable(updaterate.getEffective_date()).ifPresent(existingrate::setEffective_date);
				Optional.ofNullable(updaterate.getSettlement_currency())
						.ifPresent(existingrate::setSettlement_currency);
				Optional.ofNullable(updaterate.getRate()).ifPresent(existingrate::setRate);
				Optional.ofNullable(updaterate.getsrl()).ifPresent(existingrate::setsrl);
				ratemaintanceRepository.save(updaterate);
				Map<String, String> response = new HashMap<>();
				response.put("Status", "Success");
				response.put("Message", "Data Updated and saved successfully.");
				logger.debug("Rate is updated Successfully");
				String jsonUserData = objectMapper.writeValueAsString(response);
				String encryptedData = encryption.encrypt(jsonUserData, psuDeviceID);
				//System.out.println("Encrypted data: " + encryptedData);
				return encryptedData;
			} else {
				Map<String, String> response = new HashMap<>();
				response.put("Status", "Failed");
				response.put("Message", "User not Found");
				logger.debug("There is no exisiting rate found");
				String jsonUserData = objectMapper.writeValueAsString(response);
				String encryptedData = encryption.encrypt(jsonUserData, psuDeviceID);
				//System.out.println("Encrypted data: " + encryptedData);
				return encryptedData;
			}
		} catch (Exception e) {
			System.out.print("Exception------->" + e.getMessage());
			logger.debug("Failed to update rate" + e.getMessage());
			Map<String, String> response = new HashMap<>();
			response.put("status", "Failed");
			response.put("message", "Not updated");
			String jsonUserData = objectMapper.writeValueAsString(response);
			String encryptedData = encryption.encrypt(jsonUserData, psuDeviceID);
			//System.out.println("Encrypted data: " + encryptedData);
			return encryptedData;
		}
	}

	public RateMaintainanceEntity getOneRate(String srl) {
		RateMaintainanceEntity existingUser = ratemaintanceRepository.findByMerchantUserId(srl);
		return existingUser;
	}
}
