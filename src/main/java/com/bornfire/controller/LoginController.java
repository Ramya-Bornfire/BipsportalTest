package com.bornfire.controller;

import java.lang.reflect.Method;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bornfire.config.Encryption;
import com.bornfire.config.PasswordEncryption;
import com.bornfire.entity.AccessandRolesRepository;
import com.bornfire.entity.BIPS_MerUserManagement_Repo;
import com.bornfire.entity.BIPS_Mer_User_Management_Entity;
import com.bornfire.entity.BIPS_PasswordManagement_Repo;
import com.bornfire.entity.BIPS_Password_Management_Entity;
import com.bornfire.entity.EncryptionEntity;
import com.bornfire.entity.IPSAccessRole;
import com.bornfire.entity.InfoTableEntity;
import com.bornfire.entity.InfoTableRepo;
import com.bornfire.entity.LoginEntity;
import com.bornfire.entity.LoginSecurity;
import com.bornfire.entity.LoginSecurityRepository;
import com.bornfire.entity.MerchantMaster;
import com.bornfire.entity.MerchantMasterRep;
import com.bornfire.entity.PasswordResetResponse;
import com.bornfire.entity.UserProfile;
import com.bornfire.entity.UserProfileRep;
import com.bornfire.service.LoginService;
import com.bornfire.service.MessageServices;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/api")
public class LoginController {

	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	ObjectMapper objectMapper = new ObjectMapper();

	@Autowired
	Environment env;

	@Autowired
	Encryption encryption;
	@Autowired
	LoginService loginService;

	@Autowired
	AccessandRolesRepository accessandRolesRepository;

	@Autowired
	MessageServices messageServices;

	@Autowired
	InfoTableRepo infoTableRepo;

	@Autowired
	BIPS_PasswordManagement_Repo bIPS_PasswordManagement_Repo;

	@Autowired
	LoginSecurityRepository loginSecurityRep;
	
	@Autowired
	BIPS_MerUserManagement_Repo bIPS_MerUserManagement_Repo;

	@GetMapping("/ws/infoForEveryScreen")
	public ResponseEntity<?> infoForEveryScreen(@RequestParam(required = true) String screen_id) {
		InfoTableEntity descForScreenId = infoTableRepo.findByScreenId(screen_id);

		if (Objects.nonNull(descForScreenId)) {
			return ResponseEntity.ok(descForScreenId);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No data found for this screen ID");
		}
	}

	// Update user profile of representative
	@PostMapping("UpdateRepresentativeProfile")
	public String UpdateRepDetails(@RequestBody EncryptionEntity EncryptedString,
			@RequestHeader(value = "PSU_Device_ID", required = true) String psuDeviceID) throws Exception {
		String Updatedrepprofile = loginService.UpdateUserDetailsProfile(EncryptedString, psuDeviceID);
		if (Objects.isNull(Updatedrepprofile)) {
			return "Not Updated";
		} else {
			return Updatedrepprofile;
		}
	}

	@RequestMapping(value = "VerifyRepresentativedetails", method = { RequestMethod.POST, RequestMethod.PUT })
	public String VerifyExisitingUser(@RequestBody EncryptionEntity EncryptedString,
			@RequestHeader(value = "PSU_Device_ID", required = true) String psuDeviceID) throws Exception {
		return loginService.VerifyUserDetailsProfile(EncryptedString, psuDeviceID);
	}

	@GetMapping("OtpForAndroid")
	public String SendOTPAndroidMerchant(@RequestParam String merchant_rep_id,
			@RequestHeader(value = "PSU_Device_ID", required = true) String psuDeviceID) throws Exception {
		try {
			PasswordResetResponse Representative = loginService.SentOTPforAndroid(merchant_rep_id);
			if (Objects.nonNull(Representative)) {
				String message = "{ Mobile=" + (env.getProperty("otp.countryCode") + Representative.getMobileNumber())
						+ ", OTP=" + Representative.getOtp() + ", UserCategory=" + Representative.getUsercategory()
						+ "}";
				logger.debug("OTP sent to Merchant: " + message);
				String msgInfo = "Dear" + merchant_rep_id + ", " + Representative.getOtp()
						+ " is your OTP to authenticate your login. Do not share it with anyone else.";
				messageServices.sendOTPtoMerchant(msgInfo, String.valueOf(Representative.getMobileNumber()));
				return encryption.SuccessResponseMessage(message, psuDeviceID);
			} else {
				return encryption.FailedResponseMessage("No User Found", psuDeviceID);
			}
		} catch (Exception e) {
			System.out.print("Exception: " + e.getMessage());
			logger.debug("OTP sent to Merchant: " + e.getMessage());
			return encryption.FailedResponseMessage(e.getMessage(), psuDeviceID);
		}
	}

	// Login for Tab
	@PostMapping("LoginForTab")
	public String LoginTab(@RequestBody EncryptionEntity EncryptedString,
			@RequestHeader(value = "PSU_Device_ID", required = true) String psuDeviceID) throws Exception {
		try {
			String logindata = loginService.RepLogin(EncryptedString, psuDeviceID);
			String message = logindata.toString();
			if (Objects.nonNull(logindata)) {
				switch (message) {
				case "You're already logged in. Please log out from another device first.":
				case "Incorrect password. Please double-check and try again.":
				case "Your Account is Locked":
				case "Account locked due to too many failed attempts.":
				case "Your password has expired. Please reset it to continue.":
				case "Your account has expired. Please contact support for assistance.":
				case "User not found. Please ensure correct credentials and attempt again.":
				case "Not Found":
				case "User disabled. Please ensure the credentials.":
					return encryption.FailedResponseMessage(message, psuDeviceID);
				default:
					System.out.print("Service---------------------->" + message);
					logger.debug("Login for TAB: " + message);
					return encryption.SuccessResponseMessage(message, psuDeviceID);
				}
			} else {
				return encryption.FailedResponseMessage(message, psuDeviceID);
			}
		} catch (Exception e) {
			logger.debug("Login for TAB Exception: " + e.getMessage());
			// System.out.println("Exception: " + e.getMessage());
			return encryption.FailedResponseMessage(e.getMessage(), psuDeviceID);
		}
	}

	// Login for Tab
	@PostMapping("LoginAndroid")
	public String LoginAndroidTab(@RequestBody EncryptionEntity EncryptedString,
			@RequestHeader(value = "PSU_Device_ID", required = true) String psuDeviceID) throws Exception {
		try {
			String logindata = loginService.AndroidLogin(EncryptedString, psuDeviceID);
			String message = logindata.toString();
			if (Objects.nonNull(logindata)) {
				switch (message) {
				case "You're already logged in. Please log out from another device first.":
				case "Incorrect password. Please double-check and try again.":
				case "Your Account is Locked":
				case "Account locked due to too many failed attempts.":
				case "Your password has expired. Please reset it to continue.":
				case "Your account has expired. Please contact support for assistance.":
				case "User not found. Please ensure correct credentials and attempt again.":
				case "Not Found":
				case "User disabled. Please ensure the credentials.":
					return encryption.FailedResponseMessage(message, psuDeviceID);
				default:
					System.out.print("Service---------------------->" + message);
					logger.debug("Login for TAB: " + message);
					return encryption.SuccessResponseMessage(message, psuDeviceID);
				}
			} else {
				return encryption.FailedResponseMessage(message, psuDeviceID);
			}
		} catch (Exception e) {
			logger.debug("Login for TAB Exception: " + e.getMessage());
			// System.out.println("Exception: " + e.getMessage());
			return encryption.FailedResponseMessage(e.getMessage(), psuDeviceID);
		}
	}

	// Logout for Tab
	@PostMapping("LogoutForTab")
	public String LogoutforTab(@RequestBody EncryptionEntity EncryptedString,
			@RequestHeader(value = "PSU_Device_ID", required = true) String psuDeviceID) throws Exception {
		try {
			String repdetails = loginService.Logoutfortab(EncryptedString, psuDeviceID);
			String message = repdetails.toString();
			if (Objects.nonNull(repdetails)) {
				logger.debug("Logout for TAB: " + message);
				return encryption.SuccessResponseMessage(message, psuDeviceID);

			} else {
				return encryption.FailedResponseMessage(message, psuDeviceID);
			}
		} catch (Exception e) {
			logger.debug("Logout for TAB Exception: " + e.getMessage());
			// System.out.println("Exception: " + e.getMessage());
			return encryption.FailedResponseMessage(e.getMessage(), psuDeviceID);
		}
	}

	// For Web
	@GetMapping("LogoutforTabUsingWeb")
	public String LogoutForTabUsingWeb(@RequestParam String MerchantRepId) throws Exception {
		try {
			String repdetails = loginService.LogoutfortabUsingWeb(MerchantRepId);
			String message = repdetails.toString();
			if (Objects.nonNull(repdetails)) {
				logger.debug("Logout for TAB: " + message);
				return "Representative Id Logged out Successfully";

			} else {
				return "Representative Id Not Found";
			}
		} catch (Exception e) {
			return e.getMessage();
		}
	}

	// Login for Mobile
	@PostMapping("LoginforMobile")
	public String MobileLogin(@RequestBody EncryptionEntity EncryptedString,
			@RequestHeader(value = "PSU_Device_ID", required = true) String psuDeviceID) throws Exception {
		try {
			String mobilelogin = loginService.UserLogin(EncryptedString, psuDeviceID);
			String message = mobilelogin.toString();
			if (Objects.nonNull(mobilelogin)) {
				switch (message) {

				case "You're already logged in to proceed, you must log out from another device first.":
				case "Incorrect password. Please double-check and try again.":
				case "Your password has expired. Please reset it to continue.":
				case "Your account has expired. Please contact support for assistance.":
				case "Your Account is Locked":
				case "Account locked due to too many failed attempts.":
				case "User not found. Please ensure correct credentials and attempt again.":
				case "Not Found":
				case "User disabled. Please ensure the credentials.":
					return encryption.FailedResponseMessage(message, psuDeviceID);
				default:
					logger.debug("Login for Mobile: " + message);
					return encryption.SuccessResponseMessage(message, psuDeviceID);
				}
			} else {
				return encryption.FailedResponseMessage(message, psuDeviceID);
			}

		} catch (Exception e) {
			logger.debug("Login for Mobile:Failed" + e.getMessage());
			System.out.print("Exception: " + e.getMessage());
			return encryption.FailedResponseMessage(e.getMessage(), psuDeviceID);

		}
	}

	// Logout for Mobile
	@PostMapping("LogoutforMobile")
	public String MobileLogout(@RequestBody EncryptionEntity EncryptedString,
			@RequestHeader(value = "PSU_Device_ID", required = true) String psuDeviceID) throws Exception {
		try {
			String mobilelogin = loginService.LogoutMobile(EncryptedString, psuDeviceID);
			String message = mobilelogin.toString();
			if (Objects.nonNull(mobilelogin)) {
				logger.debug("Logout for Mobile: " + message);
				return encryption.SuccessResponseMessage(message, psuDeviceID);
			} else {
				return encryption.FailedResponseMessage(message, psuDeviceID);
			}
		} catch (Exception e) {
			System.out.print("Exception: " + e.getMessage());
			return encryption.FailedResponseMessage(e.getMessage(), psuDeviceID);
		}
	}

	// For Web
	@GetMapping("LogoutforMobileUsingWeb")
	public String LogoutforMobileUsingWeb(@RequestParam(required = true) String user_id) throws Exception {
		try {
			String mobilelogin = loginService.LogoutMobileUsingWeb(user_id);
			String message = mobilelogin.toString();
			if (Objects.nonNull(mobilelogin)) {
				logger.debug("Logout for Mobile: " + message);
				return "User Id Logged Out Successfully";
			} else {
				return "User Id Not Found";
			}
		} catch (Exception e) {
			System.out.print("Exception: " + e.getMessage());
			return e.getMessage();
		}
	}

	// Sending for user
	@GetMapping("OtpForUser")
	public String SendOTPUser(@RequestParam String user_id,
			@RequestHeader(value = "PSU_Device_ID", required = true) String psuDeviceID) throws Exception {
		try {
			PasswordResetResponse user = loginService.SentOTPforUser(user_id);
			if (Objects.nonNull(user)) {
				String message = "{ Mobile=" + (env.getProperty("otp.countryCode") + user.getMobileNumber()) + ", OTP="
						+ user.getOtp() + "}";
				String msgInfo = "Dear" + user_id + ", " + user.getOtp()
						+ " is your OTP to authenticate your login. Do not share it with anyone else.";
				messageServices.sendOTPtoMerchant(msgInfo, String.valueOf(user.getMobileNumber()));
				logger.debug("OTP sent to User: " + message);
				return encryption.SuccessResponseMessage(message, psuDeviceID);
			} else {
				return encryption.FailedResponseMessage("No User Found", psuDeviceID);
			}
		} catch (Exception e) {
			System.out.print("Exception: " + e.getMessage());
			logger.debug("OTP sent to User: " + e.getMessage());
			return encryption.FailedResponseMessage(e.getMessage(), psuDeviceID);
		}
	}

	// Sent OTP for Merchant
	@GetMapping("OtpForMerchant")
	public String SendOTPMerchant(@RequestParam String merchant_rep_id,
			@RequestHeader(value = "PSU_Device_ID", required = true) String psuDeviceID) throws Exception {
		try {
			PasswordResetResponse Representative = loginService.SentOTPforMerchant(merchant_rep_id);
			if (Objects.nonNull(Representative)) {
				String message = "{ Mobile=" + (env.getProperty("otp.countryCode") + Representative.getMobileNumber())
						+ ", OTP=" + Representative.getOtp() + "}";
				if (Representative.getOtp() != null) {
					String msgInfo = "Dear " + merchant_rep_id + ", " + Representative.getOtp()
							+ " is your OTP to authenticate your login. Do not share it with anyone else.";
					messageServices.sendOTPtoMerchant(msgInfo, String.valueOf(Representative.getMobileNumber()));
					logger.debug("OTP sent to Merchant: " + message);
				}
				return encryption.SuccessResponseMessage(message, psuDeviceID);
			} else {
				return encryption.FailedResponseMessage("No User Found", psuDeviceID);
			}
		} catch (Exception e) {
			System.out.print("Exception: " + e.getMessage());
			logger.debug("OTP sent to Merchant: " + e.getMessage());
			return encryption.FailedResponseMessage(e.getMessage(), psuDeviceID);
		}
	}

	// Send Otp to Bank User
	@GetMapping("OtpToBankUser")
	public String SendOTPBankUser(@RequestParam String merchant_rep_id,
			@RequestHeader(value = "PSU_Device_ID", required = true) String psuDeviceID) throws Exception {
		try {
			PasswordResetResponse Representative = loginService.SentOTPToBankUser(merchant_rep_id);
			if (Objects.nonNull(Representative)) {
				String message = "{ Mobile=" + (env.getProperty("otp.countryCode") + Representative.getMobileNumber())
						+ ", OTP=" + Representative.getOtp() + "}";
				if (Representative.getOtp() != null) {
					String msgInfo = "Dear " + merchant_rep_id + ", " + Representative.getOtp()
							+ " is your OTP to authenticate your login. Do not share it with anyone else.";
					messageServices.sendOTPtoMerchant(msgInfo, String.valueOf(Representative.getMobileNumber()));
					logger.debug("OTP sent to Merchant: " + message);
				}
				return message;
			} else {
				return "No User Found";
			}
		} catch (Exception e) {
			System.out.print("Exception: " + e.getMessage());
			logger.debug("OTP sent to Merchant: " + e.getMessage());
			return e.getMessage();
		}
	}

	// Reset User Password
	@PostMapping("ResetUserPassword")
	public String ResetUserPassword(@RequestBody EncryptionEntity EncryptedString,
			@RequestHeader(value = "PSU_Device_ID", required = true) String psuDeviceID) throws Exception {
		try {
			String userdetails = loginService.ChangeUserPassword(EncryptedString, psuDeviceID);
			if (Objects.nonNull(userdetails)) {
				String message = userdetails.toString();
				if (message == "Success") {
					logger.debug("Reset User" + message);
					return encryption.SuccessResponseMessage(message, psuDeviceID);
				} else if (message == "Incorrect Password") {
					return encryption.FailedResponseMessage(message, psuDeviceID);
				} else {
					return encryption.FailedResponseMessage("No User Found", psuDeviceID);
				}
			} else {
				return encryption.FailedResponseMessage("No User Found", psuDeviceID);
			}
		} catch (Exception e) {
			System.out.print("Exception: " + e.getMessage());
			logger.debug("Failed to Reset User" + e.getMessage());
			return encryption.FailedResponseMessage(e.getMessage(), psuDeviceID);
		}
	}

	// New password
	@PostMapping("ResetUserNewPassword")
	public String ResetUserNewPassword(@RequestBody EncryptionEntity EncryptedString,
			@RequestHeader(value = "PSU_Device_ID", required = true) String psuDeviceID) throws Exception {
		try {
			String userdetails = loginService.ChangeNewUserPassword(EncryptedString, psuDeviceID);
			if (Objects.nonNull(userdetails)) {
				String message = userdetails.toString();
				if (message == "Success") {
					logger.debug("Reset User" + message);
					return encryption.SuccessResponseMessage(message, psuDeviceID);
				} else {
					return encryption.FailedResponseMessage("No User Found", psuDeviceID);
				}
			} else {
				return encryption.FailedResponseMessage("No User Found", psuDeviceID);
			}
		} catch (Exception e) {
			System.out.print("Exception: " + e.getMessage());
			logger.debug("Failed to Reset User" + e.getMessage());
			return encryption.FailedResponseMessage(e.getMessage(), psuDeviceID);
		}
	}

	// Reset Merchant Password
	@PostMapping("ResetMerchantPassword")
	public String ResetMerchantPassword(@RequestBody EncryptionEntity EncryptedString,
			@RequestHeader(value = "PSU_Device_ID", required = true) String psuDeviceID) throws Exception {
		try {
			String repdetails = loginService.ChangeMerchantPassword(EncryptedString, psuDeviceID);
			if (Objects.nonNull(repdetails)) {
				String message = repdetails.toString();
				if (message == "Success") {
					logger.debug("Reset User" + message);
					return encryption.SuccessResponseMessage(message, psuDeviceID);
				} else if (message == "Incorrect Password") {
					return encryption.FailedResponseMessage(message, psuDeviceID);
				} else {
					return encryption.FailedResponseMessage("No User Found", psuDeviceID);
				}
			} else {
				return encryption.FailedResponseMessage("No User Found", psuDeviceID);
			}
		} catch (Exception e) {
			System.out.print("Exception: " + e.getMessage());
			logger.debug("Reset User" + e.getMessage());
			return encryption.FailedResponseMessage(e.getMessage(), psuDeviceID);
		}
	}

	@PostMapping("ResetMerchantNewPassword")
	public String ResetMerchantNewPassword(@RequestBody EncryptionEntity EncryptedString,
			@RequestHeader(value = "PSU_Device_ID", required = true) String psuDeviceID) throws Exception {
		try {
			String repdetails = loginService.ChangeMerchantNewPassword(EncryptedString, psuDeviceID);
			if (Objects.nonNull(repdetails)) {
				String message = repdetails.toString();
				if (message == "Success") {
					logger.debug("Reset User" + message);
					return encryption.SuccessResponseMessage(message, psuDeviceID);
				} else {
					return encryption.FailedResponseMessage("No User Found", psuDeviceID);
				}
			} else {
				return encryption.FailedResponseMessage("No User Found", psuDeviceID);
			}
		} catch (Exception e) {
			System.out.print("Exception: " + e.getMessage());
			logger.debug("Reset User" + e.getMessage());
			return encryption.FailedResponseMessage(e.getMessage(), psuDeviceID);
		}
	}

	@PostMapping("ResetMerchantPasswordForInternet")
	public String ResetMerchantPasswordForInternet(@RequestBody EncryptionEntity EncryptedString,
			@RequestHeader(value = "PSU_Device_ID", required = true) String psuDeviceID) throws Exception {
		try {
			String repdetails = loginService.ChangeMerchantPasswordForInternet(EncryptedString, psuDeviceID);
			if (Objects.nonNull(repdetails)) {
				String message = repdetails.toString();
				logger.debug("Reset Merchant" + message);
				return encryption.SuccessResponseMessage(message, psuDeviceID);
			} else {
				return encryption.FailedResponseMessage("No User Found", psuDeviceID);
			}
		} catch (Exception e) {
			System.out.print("Exception: " + e.getMessage());
			logger.debug("Reset User" + e.getMessage());
			return encryption.FailedResponseMessage(e.getMessage(), psuDeviceID);
		}
	}

	@PostMapping("ResetMerchantNewPasswordForInternet")
	public String ResetMerchantNewPasswordForInternet(@RequestBody EncryptionEntity EncryptedString,
			@RequestHeader(value = "PSU_Device_ID", required = true) String psuDeviceID) throws Exception {
		try {
			String repdetails = loginService.ChangeMerchantNewPasswordForInternet(EncryptedString, psuDeviceID);
			if (Objects.nonNull(repdetails)) {
				String message = repdetails.toString();
				logger.debug("Reset Merchant" + message);
				return encryption.SuccessResponseMessage(message, psuDeviceID);
			} else {
				return encryption.FailedResponseMessage("No User Found", psuDeviceID);
			}
		} catch (Exception e) {
			System.out.print("Exception: " + e.getMessage());
			logger.debug("Reset User" + e.getMessage());
			return encryption.FailedResponseMessage(e.getMessage(), psuDeviceID);
		}
	}

	// Check password for tab for lock screen
	@GetMapping("CheckPasswordForTab")
	public String CheckPasswordForTab(@RequestParam String merchant_id, @RequestParam String password,
			@RequestHeader(value = "PSU_Device_ID", required = true) String psuDeviceID) throws Exception {
		try {
			String repdetails = loginService.CheckPasswordForTab(merchant_id, password);
			if (Objects.nonNull(repdetails)) {
				String message = repdetails.toString();
				logger.debug("Check password for  Merchant" + message);
				return encryption.SuccessResponseMessage(message, psuDeviceID);
			} else {
				return encryption.FailedResponseMessage("No User Found", psuDeviceID);
			}
		} catch (Exception e) {
			System.out.print("Exception: " + e.getMessage());
			logger.debug("Check password for Merchant" + e.getMessage());
			return encryption.FailedResponseMessage(e.getMessage(), psuDeviceID);
		}
	}

	// Check Password for the Mobile
	@GetMapping("CheckPasswordForMobile")
	public String CheckPasswordForMobile(@RequestParam String merchant_id, @RequestParam String password,
			@RequestHeader(value = "PSU_Device_ID", required = true) String psuDeviceID) throws Exception {
		try {
			String userdetails = loginService.CheckPasswordForMobile(merchant_id, password);
			if (Objects.nonNull(userdetails)) {
				String message = userdetails.toString();
				logger.debug("Check password for user" + message);
				return encryption.SuccessResponseMessage(message, psuDeviceID);
			} else {
				return encryption.FailedResponseMessage("No User Found", psuDeviceID);
			}
		} catch (Exception e) {
			System.out.print("Exception: " + e.getMessage());
			logger.debug("Check password for user" + e.getMessage());
			return encryption.FailedResponseMessage(e.getMessage(), psuDeviceID);
		}
	}

	@GetMapping("bipsUserForAuthentication")
	public BIPS_Password_Management_Entity bipsUserForAuthentication(@RequestParam String userid) {
		// System.out.println("Hi" + bIPS_PasswordManagement_Repo.getrole(userid) +
		// userid);
		System.out.println("useridpw" + userid);
		BIPS_Password_Management_Entity bipsUserAuthenticate = bIPS_PasswordManagement_Repo.getrole(userid);
		return bipsUserAuthenticate;
	}
	
	@GetMapping("bipsUserManageForAuthentication")
	public BIPS_Mer_User_Management_Entity bipsUserManageForAuthentication(@RequestParam String userid) {
		// System.out.println("Hi" + bIPS_PasswordManagement_Repo.getrole(userid) +
		// userid);
		System.out.println("userid" + userid);
		BIPS_Mer_User_Management_Entity bipsUserAuthenticate = bIPS_MerUserManagement_Repo.getrole(userid);
		return bipsUserAuthenticate;
	}

	@GetMapping("updateLoginStatus")
	public void updateLoginStatus(@RequestParam String userid) {
		// System.out.println("Hi" + bIPS_PasswordManagement_Repo.getrole(userid) +
		// userid);
		BIPS_Password_Management_Entity bipsUserAuthenticate = bIPS_PasswordManagement_Repo.getrole(userid);
		bipsUserAuthenticate.setLogin_status("N");
		bIPS_PasswordManagement_Repo.save(bipsUserAuthenticate);
	}

	@PersistenceContext
	private EntityManager entityManager;

	@GetMapping("updateLoginLockedFlg")
	public void updateLoginLockedFlg(@RequestParam String merchant_rep_id) {
		bIPS_PasswordManagement_Repo.updateLoginLockedFlg1(merchant_rep_id, 3);
	}

	@GetMapping("getRoleMenu")
	public IPSAccessRole getRoleMenu(@RequestParam String role_id) {
		IPSAccessRole a = accessandRolesRepository.getRoleMenu(role_id);
		return a;
	}

	// Authentication for Mobile
	@PostMapping("AuthenticationForRep")
	public String Authentication(@RequestBody EncryptionEntity EncryptedString,
			@RequestHeader(value = "PSU_Device_ID", required = true) String psuDeviceID) throws Exception {
		try {
			String mobilelogin = loginService.RepAuthentication(EncryptedString, psuDeviceID);
			String message = mobilelogin.toString();
			if (Objects.nonNull(message)) {
				logger.debug("message authentication: " + message);
				return encryption.SuccessResponseMessage(message, psuDeviceID);

			} else {
				return encryption.FailedResponseMessage("Failed", psuDeviceID);
			}

		} catch (Exception e) {
			logger.debug("message authentication:Failed" + e.getMessage());
			System.out.print("Exception: " + e.getMessage());
			return encryption.FailedResponseMessage(e.getMessage(), psuDeviceID);

		}
	}

	@GetMapping("CheckTwoFactorAnswer")
	public boolean CheckTwoFactorAnswer(@RequestParam String userId, @RequestParam int answerNumber,
			@RequestParam String answer) {
		return loginService.checkAnswer(userId, answerNumber, answer);
	}

	// SMS & OTP

	@GetMapping("sendSMStoMerchant")
	public void sendSMStoMerchant(@RequestParam String msgInfo, @RequestParam String phoneNumber) {
		System.out.println(env.getProperty("sms.countryCode") + phoneNumber);
		messageServices.sendSMStoMerchant(msgInfo, (env.getProperty("sms.countryCode") + phoneNumber));
	}

	@GetMapping("sendOTPtoMerchant")
	public void sendOTPtoMerchant(@RequestParam String msgInfo, @RequestParam String phoneNumber) {
		messageServices.sendOTPtoMerchant(msgInfo, (env.getProperty("otp.countryCode") + phoneNumber));
	}

	@Autowired
	UserProfileRep userProfileRep;

	@GetMapping("userIdForChangePassword")
	public ResponseEntity<Optional<UserProfile>> userIdForChangePassword(
			@RequestParam(required = true) String user_id) {
		Optional<UserProfile> response = userProfileRep.findById(user_id);

		if (response.isPresent()) {
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(Optional.empty(), HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("changePasswordForWeb")
	public ResponseEntity<String> changePasswords(@RequestParam String oldpass, @RequestParam String newpass,
			@RequestParam String userid) throws Exception {

		try {
			// Retrieve user details and security data
			Optional<BIPS_Password_Management_Entity> bipsUp = bIPS_PasswordManagement_Repo.findById(userid);
			List<LoginSecurity> dataList = loginSecurityRep.findAll();

			// Logging (replace with proper logger in production)
			System.out.println("User ID: " + userid);
			System.out.println("Security Data: " + dataList);

			if (bipsUp.isPresent()) {
				BIPS_Password_Management_Entity bipsUser = bipsUp.get();

				// Validate old password
				if (PasswordEncryption.validatePassword(oldpass, bipsUser.getPassword())) {

					// Check if new password is different from old password
					if (!PasswordEncryption.validatePassword(newpass, bipsUser.getPassword())) {
						// Encrypt the new password and update the user entity
						String encryptedPassword = PasswordEncryption.getEncryptedPassword(newpass);
						bipsUser.setPassword(encryptedPassword);
						LocalDate expiryDate = LocalDate.now().plusDays(180);
						// Convert LocalDate to java.util.Date
						Date expiryDateAsDate = Date.from(expiryDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
						bipsUser.setPassword_expiry_date(expiryDateAsDate);

						// Save updated entity
						bIPS_PasswordManagement_Repo.save(bipsUser);

						return ResponseEntity.ok("Password Changed Successfully");
					} else {
						return ResponseEntity.status(HttpStatus.BAD_REQUEST)
								.body("New password cannot be the same as the old password");
					}
				} else {
					return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Incorrect Old Password!");
				}
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
			}

		} catch (Exception e) {
			// Handle any unexpected exceptions
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("An error occurred while changing the password. Please contact the administrator.");
		}
	}

	@GetMapping("changePasswordForWeb2FA")
	public ResponseEntity<String> changePasswordForWeb2FA(@RequestParam String oldpass, @RequestParam String newpass,
			@RequestParam String userid) throws Exception {

		try {
			Optional<BIPS_Password_Management_Entity> bipsUp = bIPS_PasswordManagement_Repo.findById(userid);
			List<LoginSecurity> dataList = loginSecurityRep.findAll();
			System.out.println("User ID: " + userid);
			System.out.println("Security Data: " + dataList);
			if (bipsUp.isPresent()) {
				BIPS_Password_Management_Entity bipsUser = bipsUp.get();
				if (!PasswordEncryption.validatePassword(oldpass, bipsUser.getPassword())) {
					String encryptedPassword = PasswordEncryption.getEncryptedPassword(newpass);
					bipsUser.setPassword(encryptedPassword);
					LocalDate expiryDate = LocalDate.now().plusDays(180);
					Date expiryDateAsDate = Date.from(expiryDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
					bipsUser.setPassword_expiry_date(expiryDateAsDate);
					bIPS_PasswordManagement_Repo.save(bipsUser);
					return ResponseEntity.ok("Password Changed Successfully");
				} else {
					return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
							.body("New password cannot be the same as the old password");
				}	
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("An error occurred while changing the password. Please contact the administrator.");
		}
	}
	
	@Autowired
	MerchantMasterRep merchantMasterRep;
	
	@GetMapping("bipsUserManageForAuthenticationValidate")
	public BIPS_Mer_User_Management_Entity bipsUserManageForAuthenticationValidate(@RequestParam String userid) {

		BIPS_Mer_User_Management_Entity bipsUserAuthenticate = bIPS_MerUserManagement_Repo.getrole(userid);
		if (Objects.nonNull(bipsUserAuthenticate)) {
			MerchantMaster merchantDet = merchantMasterRep.findByIdCustom(bipsUserAuthenticate.getMerchant_user_id());
			if (Objects.nonNull(merchantDet)) {
				return bipsUserAuthenticate;
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

}