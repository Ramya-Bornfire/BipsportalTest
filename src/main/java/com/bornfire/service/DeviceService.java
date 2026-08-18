package com.bornfire.service;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;

import com.bornfire.config.Encryption;
import com.bornfire.config.SequenceGenerator;
import com.bornfire.entity.DeviceManagementEntity;
import com.bornfire.entity.DeviceManagementRepository;
import com.bornfire.entity.EncryptionEntity;
import com.bornfire.entity.IPSAuditRepo;
import com.bornfire.entity.IPSAuditTable;
import com.bornfire.entity.LoginEntity;
import com.bornfire.entity.UserManagementEntity;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
@ConfigurationProperties("output")
@Transactional
public class DeviceService {
	private static final Logger logger = LoggerFactory.getLogger(DeviceService.class);

	ObjectMapper objectMapper = new ObjectMapper();

	@Autowired
	Encryption encryption;

	@Autowired
	Environment env;

	@Autowired
	DeviceManagementRepository devicemanagement;

	@Autowired
	SequenceGenerator sequence;

	@Autowired
	IPSAuditRepo ipsAuditTableRep;
	
	public String DeleteDeviceDataService(String deviceid, String remark, String verifyuser) {
		System.out.println(deviceid);
		DeviceManagementEntity userId = devicemanagement.getdeviceWebForDelete(deviceid);
		userId.setDel_flg("Y");
		userId.setRemark(remark);
		userId.setDevice_status("INACTIVE");
		userId.setDelete_user(verifyuser);
		userId.setDelete_time(new Date());
		userId.setDevice_removeddate(new Date());
		devicemanagement.save(userId);
		String audit_ref_no = sequence.generateRequestUUId();
		IPSAuditTable audit = new IPSAuditTable();
		audit.setAudit_date(new Date());
		audit.setEntry_time(new Date());
		audit.setEntry_user(verifyuser);
		audit.setFunc_code("DEVICE DELETE");
		audit.setRemarks(deviceid + " : DEVICE DELETED SUCCESSFULLY");
		audit.setAudit_table("BIPS_MERCHANT_DEVICE_MANAGEMENT");
		audit.setAudit_screen("DEVICE DETAILS");
		audit.setEvent_id(deviceid);
		audit.setEvent_name(deviceid);
		audit.setAudit_ref_no(audit_ref_no);
		ipsAuditTableRep.save(audit);
		return "Deleted successful";
	
	}

	// Single api
	public List<DeviceManagementEntity> getAllDevice(String merchant_user_id, String unit_id) {
		List<DeviceManagementEntity> existingunitdevicelist = devicemanagement.findAllDevice(merchant_user_id, unit_id);
		if (existingunitdevicelist.isEmpty()) {
			//System.out.println("No Device found for merchantid: " + merchant_user_id);
			return Collections.emptyList();
		} else {
			return existingunitdevicelist;
		}
	}

	// Device Management list
	public List<DeviceManagementEntity> getAllDeviceDetailsService(String merchant_user_id) {
		List<DeviceManagementEntity> existingdevicelist = devicemanagement.findByAll(merchant_user_id);
		if (existingdevicelist.isEmpty()) {
			//System.out.println("No Device found for merchantid: " + merchant_user_id);
			return Collections.emptyList();
		} else {
			return existingdevicelist;
		}
	}

	// Unit Device Management List
	public List<DeviceManagementEntity> getAllUnitDeviceDetailsService(String merchant_user_id, String unit_id) {
		List<DeviceManagementEntity> existingunitdevicelist = devicemanagement.findByAllUnit(merchant_user_id, unit_id);
		if (existingunitdevicelist.isEmpty()) {
			//System.out.println("No Device found for merchantid: " + merchant_user_id);
			return Collections.emptyList();
		} else {
			return existingunitdevicelist;
		}
	}

	public String getcheckDeviceDetailsService(String device_id) {
		// String id = env.getProperty("internaldeviceid");
		/*
		 * String decryptedData = null; try { String key = Encryption.generateKey();
		 * //System.out.println("key : " + key); String encryptedData =
		 * Encryption.encryptDevice(device_id, key);
		 * //System.out.println("Encrypted Data: " + encryptedData); decryptedData =
		 * Encryption.decryptDevice(encryptedData, key);
		 * //System.out.println("Decrypted Data: " + decryptedData); } catch (Exception e)
		 * { e.printStackTrace(); } return decryptedData;
		 */
		
		
		
		String deviceid=devicemanagement.findBydeviceId(device_id);
		if(Objects.nonNull(deviceid)) {
			return "Device Found";
		}
		else {
			return "Device Not Found";
		}
	}

	// Creation/Addition of new device
	public String AddDeviceDataService(EncryptionEntity EncryptedString, String psuDeviceID) throws Exception {
		try {
			//System.out.println("Encrypted data: " + EncryptedString.getEncryptedstring());
			String decryptedData = encryption.decrypt(EncryptedString.getEncryptedstring(), psuDeviceID);
			//System.out.println("Decrypted data: " + decryptedData);
			DeviceManagementEntity adddevice = objectMapper.readValue(decryptedData, DeviceManagementEntity.class);
			//System.out.println("Decrypted user ID: " + adddevice.getMerchant_user_id());
			//System.out.println("Decrypted user: " + adddevice);

			DeviceManagementEntity existingdevice = devicemanagement.findByMerchantUserId(adddevice.getDevice_identification_no());
			if (Objects.isNull(existingdevice)) {
				adddevice.setDel_flg("N");
				adddevice.setDevice_status("ACTIVE");
				adddevice.setEntry_flag("N");
				adddevice.setModify_flag("N");
				String id = env.getProperty("internaldeviceid");
				String androidsecureid = adddevice.getDevice_machine_id();
				String encrypteddeviceData = encryption.encrypt(androidsecureid, id);
				adddevice.setDevice_machine_id(encrypteddeviceData);
				//System.out.println("Encrypted data: " + encrypteddeviceData);
				devicemanagement.save(adddevice);
				//System.out.println("existingdevice" + existingdevice);
				String audit_ref_no = sequence.generateRequestUUId();
				IPSAuditTable audit = new IPSAuditTable();
				audit.setAudit_date(new Date());
				audit.setEntry_time(new Date());
				audit.setEntry_user(existingdevice.getEntry_user());
				audit.setFunc_code("DEVICE CREATION");
				audit.setRemarks(existingdevice.getDevice_id() + " : DEVICE CREATED SUCCESSFULLY");
				audit.setAudit_table("BIPS_MERCHANT_DEVICE_MANAGEMENT");
				audit.setAudit_screen("DEVICE DETAILS");
				audit.setEvent_id(existingdevice.getDevice_id());
				audit.setEvent_name(existingdevice.getDevice_name());
				audit.setModi_details("-");
				audit.setAudit_ref_no(audit_ref_no);

				ipsAuditTableRep.save(audit);

				Map<String, String> response = new HashMap<>();
				response.put("Status", "Success");
				response.put("Message", "Data received and saved successfully.");
				logger.debug("Device Created Successfully");
				String jsonUserData = objectMapper.writeValueAsString(response);
				String encryptedData = encryption.encrypt(jsonUserData, psuDeviceID);
				//System.out.println("Encrypted data: " + encryptedData);
				return encryptedData;
			} else {
				Map<String, String> response = new HashMap<>();
				response.put("Status", "Failed");
				response.put("Message", "The  Device is already exists");
				logger.debug("Device not created");
				String jsonUserData = objectMapper.writeValueAsString(response);
				String encryptedData = encryption.encrypt(jsonUserData, psuDeviceID);
				//System.out.println("Encrypted data: " + encryptedData);
				return encryptedData;
			}
		} catch (Exception ex) {
			logger.debug("Failed to created Device---->" + ex.getMessage());
			//System.out.println("Failed to created Device---->" + ex.getMessage());
			Map<String, String> response = new HashMap<>();
			response.put("Status", "Failed");
			response.put("Message", "Failed to Create Device");
			String jsonUserData = objectMapper.writeValueAsString(response);
			String encryptedData = encryption.encrypt(jsonUserData, psuDeviceID);
			//System.out.println("Encrypted data: " + encryptedData);
			return encryptedData;
		}

	}

	// Update the Existing Device
	public String UpdateDeviceService(EncryptionEntity EncryptedString, String psuDeviceID) throws Exception {
		try {

			//System.out.println("Encrypted data: " + EncryptedString.getEncryptedstring());
			String decryptedData = encryption.decrypt(EncryptedString.getEncryptedstring(), psuDeviceID);
			//System.out.println("Decrypted data: " + decryptedData);
			DeviceManagementEntity deviceUpdateRequest = objectMapper.readValue(decryptedData,
					DeviceManagementEntity.class);
			//System.out.println("Decrypted user ID: " + deviceUpdateRequest.getMerchant_user_id());
			//System.out.println("Decrypted user: " + deviceUpdateRequest);

			DeviceManagementEntity existingdevice = devicemanagement
					.findByMerchantUserId(deviceUpdateRequest.getDevice_identification_no());
			if (Objects.isNull(existingdevice)) {
				Map<String, String> response = new HashMap<>();
				response.put("Status", "Failed");
				response.put("Message", "No existing device Found");
				logger.debug("No existing  to device Found updated the device");
				String jsonUserData = objectMapper.writeValueAsString(response);
				String encryptedData = encryption.encrypt(jsonUserData, psuDeviceID);
				//System.out.println("Encrypted data: " + encryptedData);
				return encryptedData;
			} else {
				//System.out.println(existingdevice);
				//System.out.println(deviceUpdateRequest.getMerchant_user_id());
				//existingdevice.setDevice_id(deviceUpdateRequest.getDevice_id());
				existingdevice.setDefined_user(deviceUpdateRequest.getDefined_user());
				existingdevice.setApproved_user(deviceUpdateRequest.getApproved_user());
				existingdevice.setUser1(deviceUpdateRequest.getUser1());
				existingdevice.setUser2(deviceUpdateRequest.getUser2());
				existingdevice.setLocation(deviceUpdateRequest.getLocation());
				existingdevice.setDevice_make(deviceUpdateRequest.getDevice_make());
				existingdevice.setStore_id(deviceUpdateRequest.getStore_id());
				existingdevice.setFingerprint_enable(deviceUpdateRequest.getFingerprint_enable());
				existingdevice.setFace_recognition_enabled(deviceUpdateRequest.getFace_recognition_enabled());
				existingdevice.setDevice_status(deviceUpdateRequest.getDevice_status());
				existingdevice.setUnit_id_d(deviceUpdateRequest.getUnit_id_d());
				existingdevice.setUnit_name_d(deviceUpdateRequest.getUnit_name_d());
				existingdevice.setUnit_type_d(deviceUpdateRequest.getUnit_type_d());
				existingdevice.setModify_user(deviceUpdateRequest.getModify_user());
				existingdevice.setModify_time(deviceUpdateRequest.getModify_time());
				existingdevice.setVerify_user(deviceUpdateRequest.getVerify_user());
				existingdevice.setVerify_time(deviceUpdateRequest.getVerify_time());
				existingdevice.setEntry_flag(deviceUpdateRequest.getEntry_flag());
				existingdevice.setModify_flag(deviceUpdateRequest.getModify_flag());
				if (Objects.nonNull(deviceUpdateRequest.getDevice_machine_id())) {
					String id = env.getProperty("internaldeviceid");
					String androidsecureid = deviceUpdateRequest.getDevice_machine_id();
					String encrypteddeviceData = encryption.encrypt(androidsecureid, id);
					existingdevice.setDevice_machine_id(encrypteddeviceData);
					//System.out.println("Encrypted data: " + encrypteddeviceData);
				}
				//System.out.println(existingdevice);
				devicemanagement.save(existingdevice);
				Map<String, String> response = new HashMap<>();
				response.put("Status", "Success");
				response.put("Message", "Data Updated and saved successfully.");
				logger.debug("Successfully updated the device");
				String jsonUserData = objectMapper.writeValueAsString(response);
				String encryptedData = encryption.encrypt(jsonUserData, psuDeviceID);
				//System.out.println("Encrypted data: " + encryptedData);
				return encryptedData;
			}
		} catch (Exception e) {
			System.out.print("Exception-------" + e.getMessage());
			logger.debug("Failed to Update Device---->" + e.getMessage());
			Map<String, String> response = new HashMap<>();
			response.put("Status", "Failed");
			response.put("Message", "Not Updating");
			String jsonUserData = objectMapper.writeValueAsString(response);
			String encryptedData = encryption.encrypt(jsonUserData, psuDeviceID);
			//System.out.println("Encrypted data: " + encryptedData);
			return encryptedData;
		}
	}

	public String VerifyDeviceDetails(EncryptionEntity EncryptedString, String psuDeviceID) throws Exception {
		try {
			//System.out.println("Encrypted data: " + EncryptedString.getEncryptedstring());
			String decryptedData = encryption.decrypt(EncryptedString.getEncryptedstring(), psuDeviceID);
			//System.out.println("Decrypted data: " + decryptedData);
			DeviceManagementEntity deviceUpdateRequest = objectMapper.readValue(decryptedData,
					DeviceManagementEntity.class);
			//System.out.println("Decrypted user ID: " + deviceUpdateRequest.getMerchant_user_id());
			//System.out.println("Decrypted user: " + deviceUpdateRequest);

			DeviceManagementEntity existingdevice = devicemanagement
					.findByMerchantUserId(deviceUpdateRequest.getDevice_identification_no());
			existingdevice.setEntry_flag("Y");
			existingdevice.setModify_flag("N");
			existingdevice.setVerify_user(deviceUpdateRequest.getEntry_user());
			existingdevice.setVerify_time(new Date());
			devicemanagement.save(existingdevice);
			String audit_ref_no = sequence.generateRequestUUId();
			IPSAuditTable audit = new IPSAuditTable();
			audit.setAudit_date(new Date());
			audit.setEntry_time(new Date());
			audit.setEntry_user(existingdevice.getEntry_user());
			audit.setFunc_code("VERIFY DEVICE");
			audit.setRemarks(existingdevice.getDevice_id() + " : DEVICE IS VERIFIED SUCCESSFULLY");
			audit.setAudit_table("BIPS_MERCHANT_DEVICE_MANAGEMENT");
			audit.setAudit_screen("VERIFY DEVICE");
			audit.setEvent_id(existingdevice.getDevice_id());
			audit.setEvent_name(existingdevice.getDevice_name());
			audit.setModi_details("-");
			audit.setAudit_ref_no(audit_ref_no);
			ipsAuditTableRep.save(audit);
			Map<String, String> response = new HashMap<>();
			response.put("Status", "Success");
			response.put("Message", "Data Verified and Saved successfully.");
			logger.debug("Device Created Successfully");
			System.out.print("Device Created Successfully");
			String jsonUserData = objectMapper.writeValueAsString(response);
			String encryptedData = encryption.encrypt(jsonUserData, psuDeviceID);
			//System.out.println("Encrypted data: " + encryptedData);

			return encryptedData;
		} catch (Exception ex) {
			logger.debug("Update representative service-->" + ex.getMessage());
			System.out.print("Failed--->" + ex.getMessage());
			String jsonUserData = objectMapper.writeValueAsString("Failed to update representative");
			String encryptedData = encryption.encrypt(jsonUserData, psuDeviceID);
			//System.out.println("Encrypted data: " + encryptedData);
			return encryptedData;
		}

	}
}
