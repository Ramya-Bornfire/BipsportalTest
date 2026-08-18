package com.bornfire.controller;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bornfire.config.Encryption;
import com.bornfire.config.PasswordEncryption;
import com.bornfire.entity.BIPS_PasswordManagement_Repo;
import com.bornfire.entity.BIPS_Password_Management_Entity;
import com.bornfire.entity.EncryptionEntity;
import com.bornfire.entity.UnitManagementEntity;
import com.bornfire.entity.UnitManagementRepository;
import com.bornfire.service.UserProfileService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/api")
public class UnitManagementController {
	private static final Logger logger = LoggerFactory.getLogger(DeviceManagementController.class);

	ObjectMapper objectMapper = new ObjectMapper();

	@Autowired
	UserProfileService service;
	
	@Autowired
	Encryption encryption;

	@Autowired
	UnitManagementRepository UnitManagementRepository;

	@Autowired
	BIPS_PasswordManagement_Repo bIPS_PasswordManagement_Repo;

	@Autowired
	Environment env;

	@PostMapping("/Addunit")
	public String addDeviceData(@RequestBody EncryptionEntity EncryptedString, @RequestParam String psuDeviceID)
			throws Exception {
		try {
			//System.out.println("Encrypted data: " + EncryptedString.getEncryptedstring());
			String decryptedData = encryption.decrypt(EncryptedString.getEncryptedstring(), psuDeviceID);
			//System.out.println("Decrypted data: " + decryptedData);
			ObjectMapper objectMapper = new ObjectMapper();
			UnitManagementEntity addDevice = objectMapper.readValue(decryptedData, UnitManagementEntity.class);
			UnitManagementEntity existingUnit = UnitManagementRepository.findByUnitId(addDevice.getUnit_id());
			Map<String, String> response = new HashMap<>();
			if (existingUnit == null) {
				UnitManagementRepository.save(addDevice);
				response.put("Status", "Success");
				response.put("Message", "Data received and saved successfully.");
				logger.debug("Device Created Successfully");
			} else {
				//System.out.println("Existing unit: " + existingUnit.getUnit_id());
				response.put("Status", "Failed");
				response.put("Message", "The Device already exists");
				logger.debug("Device not created");
			}
			String jsonResponse = objectMapper.writeValueAsString(response);
			String encryptedResponse = encryption.encrypt(jsonResponse, psuDeviceID);
			//System.out.println("Encrypted data: " + encryptedResponse);
			return encryptedResponse;
		} catch (Exception ex) {
			logger.debug("Failed to create Device---->" + ex.getMessage(), ex);
			Map<String, String> response = new HashMap<>();
			response.put("Status", "Failed");
			response.put("Message", "Failed to Create Device");
			ObjectMapper objectMapper = new ObjectMapper();
			String jsonResponse = objectMapper.writeValueAsString(response);
			String encryptedResponse = encryption.encrypt(jsonResponse, psuDeviceID);
			//System.out.println("Encrypted data: " + encryptedResponse);
			return encryptedResponse;
		}
	}

	@RequestMapping(value = "Unitedit", method = RequestMethod.POST)
	public ResponseEntity<Map<String, String>> UnitManage(
			@RequestBody UnitManagementEntity bIPS_Unit_Mangement_Entity) {

		UnitManagementEntity unitDet = UnitManagementRepository.findByUnitId(bIPS_Unit_Mangement_Entity.getUnit_id());

		if (Objects.nonNull(unitDet)) {
			UnitManagementRepository.save(bIPS_Unit_Mangement_Entity);
			Map<String, String> response = new HashMap<>();
			response.put("Status", "Success");
			response.put("Message", "Unit Management updated successfully.");
			return ResponseEntity.ok().body(response);
		} else {
			//System.out.println("No matching records found for unit_id: " + bIPS_Unit_Mangement_Entity.getUnit_id());
			Map<String, String> response = new HashMap<>();
			response.put("Status", "Failed");
			response.put("Message", "No matching records found in Unit Management Entity.");
			return ResponseEntity.ok().body(response);
		}
	}

	@PostMapping(value = "UnitSubmitverifyapi")
	@ResponseBody
	public String UnitSubmitverify(@RequestParam(required = false) String userId,
			@RequestParam(required = false) String merchantId, @RequestParam(required = false) String unitId)
			throws SQLException {

		try {
			//System.out.println("User Id : " + userId);
			//System.out.println("Merchant Id : " + merchantId);
			//System.out.println("Unit Id : " + unitId);

			String password = env.getProperty("userpassword");
			String response = "";

			UnitManagementEntity unitDet = UnitManagementRepository.findByUnitId(unitId);
			List<BIPS_Password_Management_Entity> passEntityList = new ArrayList<>();

			if (Objects.nonNull(unitDet)) {
				if (Objects.nonNull(unitDet.getContact_person1_name())) {
					BIPS_Password_Management_Entity passEntity = new BIPS_Password_Management_Entity();
					LocalDate currentDate = LocalDate.now();
					LocalDate accountExpiryDate = currentDate.plusYears(1);
					Date sqlAccountExpiryDate = java.sql.Date.valueOf(accountExpiryDate);
					String encryptedPassword = PasswordEncryption.getEncryptedPasswordNew(password);
					passEntity.setAccount_expiry_date(sqlAccountExpiryDate);
					passEntity.setAlternate_email_id(unitDet.getEmail_id());
					passEntity.setAlternate_mobile_no(null);
					passEntity.setEmail_address(unitDet.getContact_person1_email());
					passEntity.setMerchant_corporate_name(unitDet.getMerchant_name());
					passEntity.setMerchant_legal_user_id(unitDet.getMerchant_user_id());
					passEntity.setMerchant_name(unitDet.getMerchant_name());
					passEntity.setMerchant_rep_id(unitDet.getMerchant_user_id() + unitDet.getUnit_id() + "R01");
					passEntity.setMer_representative_name(unitDet.getContact_person1_name());
					passEntity.setMobile_no(unitDet.getContact_person1_mobile());
					passEntity.setNo_of_active_devices(BigDecimal.valueOf(1));
					passEntity.setNo_of_concurrent_users(BigDecimal.valueOf(1));
					passEntity.setPassword(encryptedPassword);
					passEntity.setUnit_id(unitDet.getUnit_id());
					passEntity.setUnit_name(unitDet.getUnit_name());
					passEntity.setUnit_type(unitDet.getUnit_type());
					passEntity.setMerchant_user_id(unitDet.getMerchant_user_id());
					passEntity.setEntry_time(new Date());
					passEntity.setEntry_user(userId);
					passEntity.setLogin_channel("WEB");
					passEntity.setNo_of_attmp(0);
					passEntity.setUser_locked_flg("N");
					passEntity.setDel_flag("N");
					passEntity.setUser_disable_flag("N");
					passEntity.setPwlog_flg("UNIT");
					passEntity.setUser_status("ACTIVE");
					passEntity.setLogin_status("N");
					passEntity.setEntry_flag("Y");
					passEntity.setModify_flag("N");
					passEntity.setVerify_user(userId);
					passEntity.setVerify_time(new Date());
					passEntity.setPassword_life("180");
					LocalDate expiryDate = currentDate.plusDays(180);
					passEntity.setPassword_expiry_date(java.sql.Date.valueOf(expiryDate));
					passEntityList.add(passEntity);
				}

				if (Objects.nonNull(unitDet.getContact_person2_name())) {
					BIPS_Password_Management_Entity passEntity = new BIPS_Password_Management_Entity();
					LocalDate currentDate = LocalDate.now();
					String encryptedPassword = PasswordEncryption.getEncryptedPasswordNew(password);
					LocalDate accountExpiryDate = currentDate.plusYears(1);
					Date sqlAccountExpiryDate = java.sql.Date.valueOf(accountExpiryDate);
					passEntity.setAccount_expiry_date(sqlAccountExpiryDate);
					passEntity.setAlternate_email_id(unitDet.getEmail_id());
					passEntity.setAlternate_mobile_no(null);
					passEntity.setEmail_address(unitDet.getContact_person2_email());
					passEntity.setMerchant_corporate_name(unitDet.getMerchant_name());
					passEntity.setMerchant_legal_user_id(unitDet.getMerchant_user_id());
					passEntity.setMerchant_name(unitDet.getMerchant_name());
					passEntity.setMerchant_rep_id(unitDet.getMerchant_user_id() + unitDet.getUnit_id() + "R02");
					passEntity.setMer_representative_name(unitDet.getContact_person2_name());
					passEntity.setMobile_no(unitDet.getContact_person2_mobile());
					passEntity.setNo_of_active_devices(BigDecimal.valueOf(1));
					passEntity.setNo_of_concurrent_users(BigDecimal.valueOf(1));
					passEntity.setPassword(encryptedPassword);
					passEntity.setUnit_id(unitDet.getUnit_id());
					passEntity.setUnit_name(unitDet.getUnit_name());
					passEntity.setUnit_type(unitDet.getUnit_type());
					passEntity.setMerchant_user_id(unitDet.getMerchant_user_id());
					passEntity.setEntry_time(new Date());
					passEntity.setEntry_user(userId);
					passEntity.setModify_flag("N");
					passEntity.setEntry_flag("Y");
					passEntity.setLogin_channel("WEB");
					passEntity.setNo_of_attmp(0);
					passEntity.setUser_locked_flg("N");
					passEntity.setLogin_status("N");
					passEntity.setUser_disable_flag("N");
					passEntity.setPwlog_flg("UNIT");
					passEntity.setUser_status("ACTIVE");
					passEntity.setDel_flag("N");
					passEntity.setPassword_life("180");
					passEntity.setVerify_user(userId);
					passEntity.setVerify_time(new Date());
					LocalDate expiryDate = currentDate.plusDays(180);
					passEntity.setPassword_expiry_date(java.sql.Date.valueOf(expiryDate));
					passEntityList.add(passEntity);
				}

				if (Objects.nonNull(unitDet.getContact_person3_name())) {
					BIPS_Password_Management_Entity passEntity = new BIPS_Password_Management_Entity();
					LocalDate currentDate1 = LocalDate.now();
					LocalDate accountExpiryDate = currentDate1.plusYears(1);
					Date sqlAccountExpiryDate = java.sql.Date.valueOf(accountExpiryDate);
					passEntity.setAccount_expiry_date(sqlAccountExpiryDate);
					passEntity.setAlternate_email_id(unitDet.getEmail_id());
					passEntity.setAlternate_mobile_no(null);
					passEntity.setEmail_address(unitDet.getContact_person3_email());
					passEntity.setMerchant_corporate_name(unitDet.getMerchant_name());
					passEntity.setMerchant_legal_user_id(unitDet.getMerchant_user_id());
					passEntity.setMerchant_name(unitDet.getMerchant_name());
					passEntity.setMerchant_rep_id(unitDet.getMerchant_user_id() + unitDet.getUnit_id() + "R03");
					passEntity.setMer_representative_name(unitDet.getContact_person3_name());
					passEntity.setMobile_no(unitDet.getContact_person3_mobile());
					passEntity.setNo_of_active_devices(BigDecimal.valueOf(1));
					passEntity.setNo_of_concurrent_users(BigDecimal.valueOf(1));
					String encryptedPassword = PasswordEncryption.getEncryptedPasswordNew(password);
					passEntity.setPassword(encryptedPassword);
					LocalDate today = LocalDate.now();
					LocalDate expiryDate = today.plusDays(180);
					passEntity.setPassword_life("180");
					passEntity.setPassword_expiry_date(java.sql.Date.valueOf(expiryDate));
					passEntity.setUnit_id(unitDet.getUnit_id());
					passEntity.setMerchant_user_id(unitDet.getMerchant_user_id());
					passEntity.setUnit_name(unitDet.getUnit_name());
					passEntity.setUnit_type(unitDet.getUnit_type());
					passEntity.setDel_flag("N");
					passEntity.setEntry_time(new Date());
					passEntity.setEntry_user(userId);
					passEntity.setLogin_channel("WEB");
					passEntity.setNo_of_attmp(0);
					passEntity.setUser_locked_flg("N");
					passEntity.setLogin_status("N");
					passEntity.setUser_disable_flag("N");
					passEntity.setPwlog_flg("UNIT");
					passEntity.setUser_status("ACTIVE");
					passEntity.setModify_flag(unitDet.getModify_flag());
					passEntity.setEntry_flag("Y");
					passEntity.setModify_flag("N");
					passEntity.setVerify_user(userId);
					passEntity.setVerify_time(new Date());
					passEntityList.add(passEntity);
				}

				if (Objects.nonNull(unitDet.getContact_person4_name())) {
					BIPS_Password_Management_Entity passEntity = new BIPS_Password_Management_Entity();
					LocalDate currentDate1 = LocalDate.now();
					LocalDate accountExpiryDate = currentDate1.plusYears(1);
					Date sqlAccountExpiryDate = java.sql.Date.valueOf(accountExpiryDate);
					passEntity.setAccount_expiry_date(sqlAccountExpiryDate);
					passEntity.setAlternate_email_id(unitDet.getEmail_id());
					passEntity.setAlternate_mobile_no(null);
					passEntity.setEmail_address(unitDet.getContact_person4_email());
					passEntity.setMerchant_corporate_name(unitDet.getMerchant_name());
					passEntity.setMerchant_legal_user_id(unitDet.getMerchant_user_id());
					passEntity.setMerchant_name(unitDet.getMerchant_name());
					passEntity.setMerchant_rep_id(unitDet.getMerchant_user_id() + unitDet.getUnit_id() + "R04");
					passEntity.setMer_representative_name(unitDet.getContact_person4_name());
					passEntity.setMobile_no(unitDet.getContact_person4_mobile());
					passEntity.setNo_of_active_devices(BigDecimal.valueOf(1));
					passEntity.setNo_of_concurrent_users(BigDecimal.valueOf(1));
					String encryptedPassword = PasswordEncryption.getEncryptedPasswordNew(password);
					passEntity.setPassword(encryptedPassword);
					LocalDate today = LocalDate.now();
					LocalDate expiryDate = today.plusDays(180);
					passEntity.setPassword_expiry_date(java.sql.Date.valueOf(expiryDate));
					passEntity.setUnit_id(unitDet.getUnit_id());
					passEntity.setMerchant_user_id(unitDet.getMerchant_user_id());
					passEntity.setUnit_name(unitDet.getUnit_name());
					passEntity.setUnit_type(unitDet.getUnit_type());
					passEntity.setEntry_time(new Date());
					passEntity.setEntry_user(userId);
					passEntity.setEntry_flag("Y");
					passEntity.setDel_flag("N");
					passEntity.setModify_flag("N");
					passEntity.setUser_locked_flg("N");
					passEntity.setLogin_status("N");
					passEntity.setUser_disable_flag("N");
					passEntity.setLogin_channel("WEB");
					passEntity.setNo_of_attmp(0);
					passEntity.setPwlog_flg("UNIT");
					passEntity.setUser_status("ACTIVE");
					passEntity.setVerify_user(userId);
					passEntity.setVerify_time(new Date());
					passEntity.setPassword_life("180");
					passEntityList.add(passEntity);
				}

				if (Objects.nonNull(unitDet.getContact_person5_name())) {
					BIPS_Password_Management_Entity passEntity = new BIPS_Password_Management_Entity();
					LocalDate currentDate1 = LocalDate.now();
					LocalDate accountExpiryDate = currentDate1.plusYears(1);
					Date sqlAccountExpiryDate = java.sql.Date.valueOf(accountExpiryDate);
					passEntity.setAccount_expiry_date(sqlAccountExpiryDate);
					passEntity.setAlternate_email_id(unitDet.getEmail_id());
					passEntity.setAlternate_mobile_no(null);
					passEntity.setEmail_address(unitDet.getContact_person5_email());
					passEntity.setMerchant_corporate_name(unitDet.getMerchant_name());
					passEntity.setMerchant_legal_user_id(unitDet.getMerchant_user_id());
					passEntity.setMerchant_name(unitDet.getMerchant_name());
					passEntity.setMerchant_rep_id(unitDet.getMerchant_user_id() + unitDet.getUnit_id() + "R05");
					passEntity.setMer_representative_name(unitDet.getContact_person5_name());
					passEntity.setMobile_no(unitDet.getContact_person5_mobile());
					passEntity.setNo_of_active_devices(BigDecimal.valueOf(1));
					passEntity.setNo_of_concurrent_users(BigDecimal.valueOf(1));
					String encryptedPassword = PasswordEncryption.getEncryptedPasswordNew(password);
					passEntity.setPassword(encryptedPassword);
					LocalDate today = LocalDate.now();
					LocalDate expiryDate = today.plusDays(180);
					passEntity.setPassword_expiry_date(java.sql.Date.valueOf(expiryDate));
					passEntity.setUnit_id(unitDet.getUnit_id());
					passEntity.setMerchant_user_id(unitDet.getMerchant_user_id());
					passEntity.setUnit_name(unitDet.getUnit_name());
					passEntity.setUnit_type(unitDet.getUnit_type());
					passEntity.setDel_flag("N");
					passEntity.setEntry_time(new Date());
					passEntity.setEntry_user(userId);
					passEntity.setLogin_channel("WEB");
					passEntity.setNo_of_attmp(0);
					passEntity.setUser_locked_flg("N");
					passEntity.setLogin_status("N");
					passEntity.setUser_disable_flag("N");
					passEntity.setPwlog_flg("UNIT");
					passEntity.setUser_status("ACTIVE");
					passEntity.setModify_flag(unitDet.getModify_flag());
					passEntity.setEntry_flag("Y");
					passEntity.setModify_flag("N");
					passEntity.setVerify_user(userId);
					passEntity.setPassword_life("180");
					passEntity.setVerify_time(new Date());
					passEntityList.add(passEntity);
				}

				if (Objects.nonNull(unitDet.getContact_person6_name())) {
					BIPS_Password_Management_Entity passEntity = new BIPS_Password_Management_Entity();
					LocalDate currentDate1 = LocalDate.now();
					LocalDate accountExpiryDate = currentDate1.plusYears(1);
					Date sqlAccountExpiryDate = java.sql.Date.valueOf(accountExpiryDate);
					passEntity.setAccount_expiry_date(sqlAccountExpiryDate);
					passEntity.setAlternate_email_id(unitDet.getEmail_id());
					passEntity.setAlternate_mobile_no(null);
					passEntity.setEmail_address(unitDet.getContact_person6_email());
					passEntity.setMerchant_corporate_name(unitDet.getMerchant_name());
					passEntity.setMerchant_legal_user_id(unitDet.getMerchant_user_id());
					passEntity.setMerchant_name(unitDet.getMerchant_name());
					passEntity.setMerchant_rep_id(unitDet.getMerchant_user_id() + unitDet.getUnit_id() + "R06");
					passEntity.setMer_representative_name(unitDet.getContact_person6_name());
					passEntity.setMobile_no(unitDet.getContact_person6_mobile());
					passEntity.setNo_of_active_devices(BigDecimal.valueOf(1));
					passEntity.setNo_of_concurrent_users(BigDecimal.valueOf(1));
					String encryptedPassword = PasswordEncryption.getEncryptedPasswordNew(password);
					passEntity.setPassword(encryptedPassword);
					LocalDate today = LocalDate.now();
					LocalDate expiryDate = today.plusDays(180);
					passEntity.setPassword_expiry_date(java.sql.Date.valueOf(expiryDate));
					passEntity.setUnit_id(unitDet.getUnit_id());
					passEntity.setMerchant_user_id(unitDet.getMerchant_user_id());
					passEntity.setUnit_name(unitDet.getUnit_name());
					passEntity.setUnit_type(unitDet.getUnit_type());
					passEntity.setDel_flag("N");
					passEntity.setEntry_time(new Date());
					passEntity.setEntry_user(userId);
					passEntity.setLogin_channel("WEB");
					passEntity.setNo_of_attmp(0);
					passEntity.setUser_locked_flg("N");
					passEntity.setLogin_status("N");
					passEntity.setUser_disable_flag("N");
					passEntity.setPwlog_flg("UNIT");
					passEntity.setUser_status("ACTIVE");
					passEntity.setModify_flag(unitDet.getModify_flag());
					passEntity.setEntry_flag("Y");
					passEntity.setPassword_life("180");
					passEntity.setModify_flag("N");
					passEntity.setVerify_user(userId);
					passEntity.setVerify_time(new Date());
					passEntityList.add(passEntity);
				}

				if (!passEntityList.isEmpty()) {
					bIPS_PasswordManagement_Repo.saveAll(passEntityList);
					unitDet.setEntry_flag("Y");
					unitDet.setModify_flag("N");
					unitDet.setVerify_time(new Date());
					unitDet.setVerify_user(userId);
					UnitManagementRepository.save(unitDet);
					response = "Unit Verified Successfully";
				} else {
					response = "Unit Verification Failed";
				}
			} else {
				response = "Unit Verification Failed";
			}
			return response;
		} catch (Exception e) {
			e.printStackTrace();
			return "Error occurred while processing the request";
		}
	}

	@GetMapping("/unitviewdetail")
	public UnitManagementEntity unitviewdetail(@RequestParam String unit_id) {
		UnitManagementEntity existingUser = UnitManagementRepository.getUnitId(unit_id);
		System.out.print(existingUser);
		return existingUser;
	}

	@GetMapping("/uniteditdetail")
	public UnitManagementEntity uniteditdetail(@RequestParam String unit_id) {
		UnitManagementEntity existingUser = UnitManagementRepository.getUnitId(unit_id);
		System.out.print(existingUser);
		return existingUser;
	}

	@GetMapping("/unitverifydetail")
	public UnitManagementEntity unitverifydetail(@RequestParam String unit_id) {
		UnitManagementEntity existingUser = UnitManagementRepository.getUnitId(unit_id);
		System.out.print(existingUser);
		return existingUser;
	}

	@GetMapping("/UnitList")
	public List<UnitManagementEntity> UnitList(@RequestParam String merchant_id) {
		return UnitManagementRepository.getUnitlist(merchant_id);
	}
	
	@GetMapping("/UnitDistinctList")
	public List<String> UnitDistinctList(@RequestParam String merchant_id) {
		return UnitManagementRepository.getpartUnitId(merchant_id);
	}

	@PostMapping("/DeleteUnitData")
	public String DeleteUserData(@RequestParam String unit_id,@RequestParam String merchantId, @RequestParam String remark, @RequestParam String delete_user) throws Exception {
		return service.deleteUnitDataService(unit_id,merchantId, remark,delete_user);
	}
}
