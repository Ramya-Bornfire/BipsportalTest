package com.bornfire.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bornfire.entity.EncryptionEntity;
import com.bornfire.entity.UserManagementEntity;
import com.bornfire.entity.UserManagementRepository;
import com.bornfire.service.UserProfileService;

@RestController
@RequestMapping("/api")
public class UserManagementController {

	@Autowired
	UserProfileService service;

	@Autowired
	UserManagementRepository Usermanagementrepository;
	
	//Single User Management List
	@GetMapping("/AllUserManagementList")
	public List<UserManagementEntity> GetMerchantUnitAllUser(@RequestParam String merchant_user_id,
			@RequestParam String unit_id) {
		return service.getMerchantUnitAllUserService(merchant_user_id, unit_id);
	}
	// Merchant wise User Management List
	@GetMapping("/UserManagementList")
	public List<UserManagementEntity> GetAllUser(@RequestParam String merchant_user_id) {
		return service.getAllUserService(merchant_user_id);
	}

	// Unit Wise User Management List
	@GetMapping("/UnitWiseUserManagementList")
	public List<UserManagementEntity> GetUnitAllUser(@RequestParam String merchant_user_id,
			@RequestParam String unit_id) {
		return service.getUnitAllUserService(merchant_user_id, unit_id);
	}

	// Creation/Addition of new User
	@PostMapping("/AddUserData")
	public String postUserData(@RequestBody EncryptionEntity EncryptedString,
			@RequestHeader(value = "PSU_Device_ID", required = true) String psuDeviceID) throws Exception {
		return service.postUserDataService(EncryptedString, psuDeviceID);
	}
	
	@PostMapping("/DeleteUserData")
	public String DeleteUserData(@RequestParam String userid,@RequestParam String remark, @RequestParam String verifyuser) throws Exception {
		return service.DeleteUserDataService(userid, remark,verifyuser);
	}

	// Update existing user
	@RequestMapping(value = "UpdateUser", method = { RequestMethod.POST })
	public String UpdateExisitingUser(@RequestBody EncryptionEntity EncryptedString,
			@RequestHeader(value = "PSU_Device_ID", required = true) String psuDeviceID) throws Exception {
		return service.UpdateExisitingUserService(EncryptedString, psuDeviceID);
	}

	//Verify User for Internet Application
	@RequestMapping(value = "InternetVerifyUserdetails", method = { RequestMethod.POST ,RequestMethod.GET })
	public String InternetVerifyUserdetails(@RequestParam String user_id,@RequestParam String USERID) throws Exception {
		return service.VerifyInternetUserDetails(user_id, USERID);
	}
	// Update existing user
	@RequestMapping(value = "VerifyUserdetails", method = { RequestMethod.POST })
	public String VerifyExisitingUser(@RequestBody EncryptionEntity EncryptedString,
			@RequestHeader(value = "PSU_Device_ID", required = true) String psuDeviceID) throws Exception {
		return service.VerifyUserDetails(EncryptedString, psuDeviceID);
	}

	@GetMapping("/uerviewdetail")
	public UserManagementEntity uerviewdetail(@RequestParam String user_id) {
		UserManagementEntity existingUser = Usermanagementrepository.findByIdCustom(user_id);
		System.out.print(existingUser);
		// logger.debug(existingUser.toString());
		return existingUser;
	}

	@GetMapping("/uereditdetail")
	public UserManagementEntity uereditdetail(@RequestParam String user_id) {
		UserManagementEntity existingUser = Usermanagementrepository.findByIdCustom(user_id);
		System.out.print(existingUser);
		// logger.debug(existingUser.toString());
		return existingUser;
	}

	@GetMapping("/UserManagementView")
	public UserManagementEntity UserManagementView(@RequestParam String merchant_user_id) {
		return service.getOneUser(merchant_user_id);
	}
	
	@GetMapping("/defaultUserid")                                                     
	public List<String> defaultUserid(@RequestParam String merchant_id) {
		List<String> existingUser = Usermanagementrepository.getuserid(merchant_id);
        System.out.print(existingUser);
      //  logger.debug(existingUser.toString());
		return existingUser; 
	}

}
