package com.bornfire.service;

import java.math.BigDecimal;
import java.util.Calendar;
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

import com.bornfire.config.Encryption;
import com.bornfire.config.PasswordEncryption;
import com.bornfire.config.SequenceGenerator;
import com.bornfire.entity.EncryptionEntity;
import com.bornfire.entity.IPSAuditRepo;
import com.bornfire.entity.IPSAuditTable;
import com.bornfire.entity.UnitManagementEntity;
import com.bornfire.entity.UnitManagementRepository;
import com.bornfire.entity.UserManagementEntity;
import com.bornfire.entity.UserManagementRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
@ConfigurationProperties("output")
@Transactional
public class UserProfileService {
	private static final Logger logger = LoggerFactory.getLogger(UserProfileService.class);

	ObjectMapper objectMapper = new ObjectMapper();

	@Autowired
	Encryption encryption;
	
	@Autowired
	Environment env;

	@Autowired
	UnitManagementRepository UnitManagementRepository;

	@Autowired
	UserManagementRepository Usermanagementrepository;
	
	@Autowired
	SequenceGenerator sequence;
	
	@Autowired
	IPSAuditRepo ipsAuditTableRep;
	
	public List<UserManagementEntity> getMerchantUnitAllUserService(String merchant_user_id, String unit_id) {
		List<UserManagementEntity> existingUser = Usermanagementrepository.findMerchantandUnit(merchant_user_id, unit_id);
		if (existingUser.isEmpty()) {
			//System.out.println("No Unit Data found for merchant user id: " + merchant_user_id);
			return Collections.emptyList();
		} else {
			logger.debug(existingUser.toString());
			return existingUser;
		}
	}

	// Merchant wise User Management List
	public List<UserManagementEntity> getAllUserService(String merchant_user_id) {
		List<UserManagementEntity> existingUser = Usermanagementrepository.findByAll(merchant_user_id);
		if (existingUser.isEmpty()) {
			//System.out.println("No Usermanagement found for merchant user id: " + merchant_user_id);
			return Collections.emptyList();
		} else {
			logger.debug(existingUser.toString());
			return existingUser;
		}
	}

	// Unit Wise User Management List
	public List<UserManagementEntity> getUnitAllUserService(String merchant_user_id, String unit_id) {
		List<UserManagementEntity> existingUser = Usermanagementrepository.findByAllUnit(merchant_user_id, unit_id);
		if (existingUser.isEmpty()) {
			//System.out.println("No Unit Data found for merchant user id: " + merchant_user_id);
			return Collections.emptyList();
		} else {
			logger.debug(existingUser.toString());
			return existingUser;
		}
	}
	
	public String DeleteUserDataService(String userid, String remark, String verifyuser) {
		UserManagementEntity userId = Usermanagementrepository.findByMerchantUserId(userid);
		userId.setAccount_expiry_date1(new Date());
		userId.setDel_flag1("Y");
		userId.setRemark(remark);
		userId.setUser_status1("INACTIVE");
		userId.setDelete_user(verifyuser);
		userId.setDelete_time(new Date());
		
		Usermanagementrepository.save(userId);
		String audit_ref_no = sequence.generateRequestUUId();
		IPSAuditTable audit = new IPSAuditTable();
		audit.setAudit_date(new Date());
		audit.setEntry_time(new Date());
		audit.setEntry_user(verifyuser);
		audit.setFunc_code("USER DELETION");
		audit.setRemarks(userid + " : USER DELETED SUCCESSFULLY");
		audit.setAudit_table("BIPS_MERCHANT_USER_MANAGEMENT");
		audit.setAudit_screen("USER DETAILS");
		audit.setEvent_id(userid);
		audit.setEvent_name("DELETE");
		audit.setModi_details("-");
		audit.setAudit_ref_no(audit_ref_no);

		ipsAuditTableRep.save(audit);
		
		return "Deleted successful";
	
	}

	// Creation/Addition of new User
	public String postUserDataService(EncryptionEntity EncryptedString, String psuDeviceID) throws Exception {
	    // Check if EncryptedString or psuDeviceID is null
	    if (EncryptedString == null || psuDeviceID == null) {
	        throw new IllegalArgumentException("EncryptedString and psuDeviceID cannot be null");
	    }

	    String decryptedData = encryption.decrypt(EncryptedString.getEncryptedstring(), psuDeviceID);
	    // Check if decryptedData is null
	    if (decryptedData == null) {
	        throw new IllegalStateException("Decryption failed, decryptedData is null");
	    }

	    UserManagementEntity userData = objectMapper.readValue(decryptedData, UserManagementEntity.class);
	    // Check if userData is null
	    if (userData == null) {
	        throw new IllegalStateException("Failed to parse decrypted data, userData is null");
	    }

	    Map<String, String> response = new HashMap<>();
	    List<String> userId = Usermanagementrepository.getuserid(userData.getUser_id());
	    // Check if userId is null
	    if (userId == null) {
	        throw new IllegalStateException("Failed to retrieve user ID from repository");
	    }

	    if (userId.isEmpty()) {
	        final Calendar cal = Calendar.getInstance();
	        cal.add(Calendar.MONTH, 6);
	        final Calendar cal1 = Calendar.getInstance();
	        cal1.add(Calendar.YEAR, 1);

	        String userPassword = env.getProperty("userpassword");
	        if (userPassword == null) {
	            throw new IllegalStateException("Environment property 'userpassword' is not set");
	        }

	        String encryptedPassword = PasswordEncryption.getEncryptedPassword(userPassword);
	        userData.setPassword_expiry_date1(cal.getTime());
	        userData.setPassword_life1("180");
	        userData.setAccount_expiry_date1(cal1.getTime());
	        userData.setPassword1(encryptedPassword);
	        userData.setUser_disable_flag1("N");
	        userData.setDel_flag1("N");
	        userData.setUser_status1("ACTIVE");
	        userData.setLogin_status1("N"); 
	        userData.setEntry_flag("N");
	        userData.setModify_flag("N");
	        userData.setUser_locked_flg("N");
	        userData.setNo_of_attmp(BigDecimal.ZERO);
	        userData.setEntry_time(new Date());
	        userData.setUser_category("User");
	        userData.setAuthentication_flg("N");
	        Usermanagementrepository.save(userData);

	        String audit_ref_no = sequence.generateRequestUUId();
	        if (audit_ref_no == null) {
	            throw new IllegalStateException("Failed to generate audit reference number");
	        }

	        IPSAuditTable audit = new IPSAuditTable();
	        audit.setAudit_date(new Date());
	        audit.setEntry_time(new Date());
	        audit.setEntry_user(userData.getEntry_user());
	        audit.setFunc_code("USER CREATION");
	        audit.setRemarks(userData.getUser_id() + " : User Created Successfully");
	        audit.setAudit_table("BIPS_MERCHANT_USER_MANAGEMENT");
	        audit.setAudit_screen("USER DETAILS");
	        audit.setEvent_id(userData.getUser_id());
	        audit.setEvent_name(userData.getUser_name());
	        audit.setModi_details("-");
	        audit.setAudit_ref_no(audit_ref_no);

	        ipsAuditTableRep.save(audit);

	        response.put("Status", "Success");
	        response.put("Message", "User Added successfully.");
	    } else {
	        response.put("Status", "Failure");
	        response.put("Message", "Data Not received and Not saved.");
	    }

	    String jsonUserData = objectMapper.writeValueAsString(response);
	    String encryptedData = encryption.encrypt(jsonUserData, psuDeviceID);
	    // Check if encryptedData is null
	    if (encryptedData == null) {
	        throw new IllegalStateException("Encryption failed, encryptedData is null");
	    }

	    return encryptedData;
	}


	// Update existing user
	public String UpdateExisitingUserService(EncryptionEntity EncryptedString, String psuDeviceID) throws Exception {
		try {

			//System.out.println("Encrypted data: " + EncryptedString.getEncryptedstring());
			String decryptedData = encryption.decrypt(EncryptedString.getEncryptedstring(), psuDeviceID);
			//System.out.println("Decrypted data: " + decryptedData);
			UserManagementEntity UpdateUser = objectMapper.readValue(decryptedData, UserManagementEntity.class);
			//System.out.println("Decrypted user ID: " + UpdateUser.getMerchant_user_id());
			//System.out.println("Decrypted user: " + UpdateUser);
			//System.out.println(UpdateUser.getUser_disable_from_date1() +""+ UpdateUser.getUser_disable_to_date1());
			UserManagementEntity existingUser = Usermanagementrepository.findByMerchantUserId(UpdateUser.getUser_id());
			System.out.print(UpdateUser.getUser_id());
			existingUser.setUser_id(UpdateUser.getUser_id());
			existingUser.setUser_name(UpdateUser.getUser_name());
			existingUser.setDefault_device_id(UpdateUser.getDefault_device_id());
			existingUser.setAlternative_device_id1(UpdateUser.getAlternative_device_id1());
			existingUser.setAlternative_device_id2(UpdateUser.getAlternative_device_id2());
			existingUser.setEmail_address1(UpdateUser.getEmail_address1());
			existingUser.setMobile_no1(UpdateUser.getMobile_no1());
			existingUser.setUser_designation(UpdateUser.getUser_designation());
			existingUser.setUser_role(UpdateUser.getUser_role());
			existingUser.setMake_or_checker(UpdateUser.getMake_or_checker());
			existingUser.setAlternate_mobile_no1(UpdateUser.getAlternate_mobile_no1());
			existingUser.setAlternate_email_id1(UpdateUser.getAlternate_email_id1());
			existingUser.setVerify_user(UpdateUser.getVerify_user());
			existingUser.setVerify_time(UpdateUser.getVerify_time());
			existingUser.setModify_user(UpdateUser.getModify_user());
			existingUser.setUnit_id_u(UpdateUser.getUnit_id_u());
			existingUser.setUnit_name_u(UpdateUser.getUnit_name_u());
			existingUser.setUnit_type_u(UpdateUser.getUnit_type_u());
			existingUser.setModify_time(new Date());
			existingUser.setEntry_flag(UpdateUser.getEntry_flag());
			existingUser.setModify_flag(UpdateUser.getModify_flag());
			existingUser.setUnit_id_u(UpdateUser.getUnit_id_u());
			existingUser.setUnit_name_u(UpdateUser.getUnit_name_u());
			existingUser.setUnit_type_u(UpdateUser.getUnit_type_u());
			existingUser.setCountrycode(UpdateUser.getCountrycode());
			existingUser.setAlt_countrycode(UpdateUser.getAlt_countrycode());
			existingUser.setLogin_channel1(UpdateUser.getLogin_channel1());
			existingUser.setPassword_expiry_date1(UpdateUser.getPassword_expiry_date1());
//			String encryptedPassword = PasswordEncryption.getEncryptedPassword(UpdateUser.getPassword1());
//			existingUser.setPassword1(encryptedPassword);
			existingUser.setLogin_status1(UpdateUser.getLogin_status1());
			if (Objects.nonNull(UpdateUser.getPhoto())) {
				existingUser.setPhoto(UpdateUser.getPhoto());
			}
			if (Objects.nonNull(UpdateUser.getUser_disable_from_date1())) {
				existingUser.setUser_disable_from_date1(UpdateUser.getUser_disable_from_date1());
			}
			if (Objects.nonNull(UpdateUser.getUser_disable_to_date1())) {
				existingUser.setUser_disable_to_date1(UpdateUser.getUser_disable_to_date1());
			}	
			System.out.print(existingUser);
			Usermanagementrepository.save(existingUser);

			Map<String, String> response = new HashMap<>();
			response.put("Status", "Success");
			response.put("Message", "User Updated and saved successfully.");
			logger.debug("User Updated and saved successfully.");
			String jsonUserData = objectMapper.writeValueAsString(response);
			String encryptedData = encryption.encrypt(jsonUserData, psuDeviceID);
			//System.out.println("Encrypted data: " + encryptedData);
			return encryptedData;
		} catch (Exception e) {
			System.out.print("Exception------->" + e.getMessage());
			Map<String, String> response = new HashMap<>();
			response.put("Status", "Failed");
			response.put("Message", "Not Updating");
			logger.debug("Failed to Update User.");
			String jsonUserData = objectMapper.writeValueAsString(response);
			String encryptedData = encryption.encrypt(jsonUserData, psuDeviceID);
			//System.out.println("Encrypted data: " + encryptedData);
			return encryptedData;
		}

	}

	public UserManagementEntity getOneUser(String user_id) {
		UserManagementEntity existingUser = Usermanagementrepository.findByMerchantUserId(user_id);
		return existingUser;
	}
	
	public String VerifyUserDetails(EncryptionEntity EncryptedString, String psuDeviceID) throws Exception {
		try {
			////System.out.println("Encrypted data: " + EncryptedString.getEncryptedstring());
			String decryptedData = encryption.decrypt(EncryptedString.getEncryptedstring(), psuDeviceID);
			//System.out.println("Decrypted data: " + decryptedData);
			UserManagementEntity UpdateUser = objectMapper.readValue(decryptedData, UserManagementEntity.class);
			//System.out.println("Decrypted user ID: " + UpdateUser.getMerchant_user_id());
			//System.out.println("Decrypted user: " + UpdateUser);

			UserManagementEntity existingUser = Usermanagementrepository.findByMerchantUserId(UpdateUser.getUser_id());
				existingUser.setEntry_flag("Y");
				existingUser.setModify_flag("N");
			existingUser.setVerify_user(UpdateUser.getEntry_user());
			existingUser.setVerify_time(new Date());
			Usermanagementrepository.save(existingUser);
			String audit_ref_no = sequence.generateRequestUUId();
			IPSAuditTable audit = new IPSAuditTable();
			audit.setAudit_date(new Date());
			audit.setEntry_time(new Date());
			audit.setEntry_user(UpdateUser.getEntry_user());
			audit.setFunc_code("VERIFY USER");
			audit.setRemarks(existingUser.getUser_id()+ " : USER IS VERIFIED SUCCESSFULLY");
			audit.setAudit_table("BIPS_MERCHANT_USER_MANAGEMENT");
			audit.setAudit_screen("VERIFY USER");
			audit.setEvent_id(existingUser.getUser_id());
			audit.setEvent_name(existingUser.getUser_name());
			audit.setModi_details("-");
			audit.setAudit_ref_no(audit_ref_no);
			ipsAuditTableRep.save(audit);
			Map<String, String> response = new HashMap<>();
			response.put("Status", "Success");
			response.put("Message", "Data Verified and Saved successfully.");
			//System.out.println("User Verified Successfully");
			String jsonUserData = objectMapper.writeValueAsString(response);
			String encryptedData = encryption.encrypt(jsonUserData, psuDeviceID);
			//System.out.println("Encrypted data: " + encryptedData);
			return encryptedData;
		} catch (Exception ex) {
			//System.out.println("Update representative service-->" + ex.getMessage());
			String jsonUserData = objectMapper.writeValueAsString("Failed to update representative");
			String encryptedData = encryption.encrypt(jsonUserData, psuDeviceID);
			//System.out.println("Encrypted data: " + encryptedData);
			return encryptedData;
		}

	}
	
	public String VerifyInternetUserDetails(String user_id, String USERID) throws Exception {
		try {
			UserManagementEntity existingUser = Usermanagementrepository.findByMerchantUserId(user_id);
			
			existingUser.setEntry_flag("Y");
			existingUser.setModify_flag("N");
			existingUser.setVerify_user(USERID);
			existingUser.setVerify_time(new Date());
			Usermanagementrepository.save(existingUser);
			String audit_ref_no = sequence.generateRequestUUId();
			IPSAuditTable audit = new IPSAuditTable();
			audit.setAudit_date(new Date());
			audit.setEntry_time(new Date());
			audit.setEntry_user(existingUser.getEntry_user());
			audit.setFunc_code("VERIFY USER");
			audit.setRemarks(existingUser.getUser_id()+ " : USER IS VERIFIED SUCCESSFULLY");
			audit.setAudit_table("BIPS_MERCHANT_USER_MANAGEMENT");
			audit.setAudit_screen("VERIFY USER");
			audit.setEvent_id(existingUser.getUser_id());
			audit.setEvent_name(existingUser.getUser_name());
			audit.setModi_details("-");
			audit.setAudit_ref_no(audit_ref_no);
			ipsAuditTableRep.save(audit);
			return  "User Verified and Saved successfully.";
		} catch (Exception ex) {
			logger.debug("Update representative service-->" + ex.getMessage());
			
			return "Failed to update representative";
		}

	}
	
	public String deleteUnitDataService(String unitId, String merchantId, String remark, String verifyUser) {
	    // Retrieve the unit by unitId
	    UnitManagementEntity unit = UnitManagementRepository.findByUnitId(unitId);
	    
	    // Check if the unit exists before proceeding
	    if (unit != null) {
	        // Update unit details
	        unit.setRemarks(remark);
	        unit.setDelete_user(verifyUser);
	        unit.setDel_flg("Y");
	        unit.setDelete_date(new Date());
	        
	        // Save the updated unit entity
	        UnitManagementRepository.save(unit);
	        
	        // Create and save an audit entry
	        String auditRefNo = sequence.generateRequestUUId();
	        IPSAuditTable audit = new IPSAuditTable();
	        audit.setAudit_date(new Date());
	        audit.setEntry_time(new Date());
	        audit.setEntry_user(verifyUser);
	        audit.setFunc_code("UNIT DELETION");
	        audit.setRemarks(unitId + " : UNIT DELETED SUCCESSFULLY");
	        audit.setAudit_table("BIPS_MERCHANT_UNIT_MANAGEMENT");
	        audit.setAudit_screen("UNIT DETAILS");
	        audit.setEvent_id(unitId);
	        audit.setEvent_name("DELETE");
	        audit.setModi_details("-");
	        audit.setAudit_ref_no(auditRefNo);

	        ipsAuditTableRep.save(audit);
	        
	        // Delete users associated with the unit
	        deleteUsersUnderUnitId(unitId, merchantId, verifyUser);
	        
	        return "Unit and associated users deleted successfully.";
	    } else {
	        return "Unit not found.";
	    }
	}
	
	public String deleteUsersUnderUnitId(String unitId, String merchantId, String verifyUser) {
	    List<UserManagementEntity> userList = Usermanagementrepository.findByUnitandMerchant(merchantId, unitId);
	    
	    if (userList != null && !userList.isEmpty()) {
	        for (UserManagementEntity user : userList) {
	            user.setAccount_expiry_date1(new Date());
	            user.setDel_flag1("Y");
	            user.setRemark("This user's unit id was deleted. So, this user will also be deleted.");
	            user.setUser_status1("INACTIVE");
	            user.setDelete_user(verifyUser);
	            user.setDelete_time(new Date());
	            
	            Usermanagementrepository.save(user);
	            
	            String auditRefNo = sequence.generateRequestUUId();
	            IPSAuditTable audit = new IPSAuditTable();
	            audit.setAudit_date(new Date());
	            audit.setEntry_time(new Date());
	            audit.setEntry_user(verifyUser);
	            audit.setFunc_code("USER DELETION");
	            audit.setRemarks(user.getUser_id() + " : User deleted under unit = " + unitId + " successfully.");
	            audit.setAudit_table("BIPS_MERCHANT_USER_MANAGEMENT");
	            audit.setAudit_screen("USER DETAILS");
	            audit.setEvent_id(user.getUser_id());
	            audit.setEvent_name("DELETE");
	            audit.setModi_details("-");
	            audit.setAudit_ref_no(auditRefNo);

	            ipsAuditTableRep.save(audit);
	        }
	        return "Deleted Successfully";
	    } else {
	        return "No users found to delete.";
	    }
	}
}
