package com.bornfire.service;

import java.math.BigDecimal;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

import com.bornfire.config.Encryption;
import com.bornfire.config.PasswordEncryption;
import com.bornfire.config.SequenceGenerator;
import com.bornfire.entity.ChangePasswordEntity;
import com.bornfire.entity.ChangePasswordthroughOtp;
import com.bornfire.entity.EncryptionEntity;
import com.bornfire.entity.IPSAuditRepo;
import com.bornfire.entity.IPSAuditTable;
import com.bornfire.entity.LoginEntity;
import com.bornfire.entity.LoginRepository;
import com.bornfire.entity.LoginSessionEntity;
import com.bornfire.entity.LoginSessionHistoryEntity;
import com.bornfire.entity.LoginSessionHistoryRepo;
import com.bornfire.entity.LoginSessionRepository;
import com.bornfire.entity.LoginTabEntity;
import com.bornfire.entity.LogoutEntity;
import com.bornfire.entity.PasswordResetResponse;
import com.bornfire.entity.TwoFactorEntity;
import com.bornfire.entity.TwoFactorRepository;
import com.bornfire.entity.UserManagementEntity;
import com.bornfire.entity.UserManagementRepository;
import com.bornfire.entity.UserProfile;
import com.bornfire.entity.UserProfileRep;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javassist.NotFoundException;

@Service
@ConfigurationProperties("output")
public class LoginService {
	private static final Logger logger = LoggerFactory.getLogger(LoginService.class);
	@Autowired
	LoginRepository loginRepo;

	ObjectMapper objectMapper = new ObjectMapper();

	@Autowired
	Encryption encryption;

	@Autowired
	UserManagementRepository userRepo;

	@Autowired
	SequenceGenerator sequence;

	@Autowired
	IPSAuditRepo ipsAuditTableRep;

	@Autowired
	TwoFactorRepository TwoFARepo;

	int maxAttempts = 3;
	String response;
	BigDecimal currentAttempts;

	@Autowired
	LoginSessionRepository loginsessionrepo;

	@Autowired
	LoginSessionHistoryRepo loginSessionHistoryRepo;
	// Update Representative Profile
	public String UpdateUserDetailsProfile(EncryptionEntity EncryptedString, String psuDeviceID) throws Exception {
		try {
			//System.out.println("Encrypted data: " + EncryptedString.getEncryptedstring());
			String decryptedData = encryption.decrypt(EncryptedString.getEncryptedstring(), psuDeviceID);
			//System.out.println("Decrypted data: " + decryptedData);
			LoginEntity userLogin = objectMapper.readValue(decryptedData, LoginEntity.class);
			//System.out.println("Decrypted user ID: " + userLogin.getMerchant_user_id());
			//System.out.println("Decrypted user: " + userLogin);

			LoginEntity login = loginRepo.findByMerchantRepID(userLogin.getMerchant_rep_id());
			System.out.print("Coming to update User");
			logger.debug("Coming to update User");
			if (Objects.nonNull(login)) {
				if (Objects.nonNull(userLogin.getMer_representative_name())) {
					login.setMer_representative_name(userLogin.getMer_representative_name());
				}
				if (Objects.nonNull(userLogin.getMerchant_corporate_name())) {
					login.setMerchant_corporate_name(userLogin.getMerchant_corporate_name());
				}
				if (Objects.nonNull(userLogin.getPassword())) {
					login.setPassword(userLogin.getPassword());
				}
				if (Objects.nonNull(userLogin.getUser_disable_from_date())) {
					login.setUser_disable_from_date(userLogin.getUser_disable_from_date());
				}
				if (Objects.nonNull(userLogin.getUser_disable_to_date())) {
					login.setUser_disable_to_date(userLogin.getUser_disable_to_date());
				}
				if (Objects.nonNull(userLogin.getCountrycode())) {
					login.setCountrycode(userLogin.getCountrycode());
				}
				if (Objects.nonNull(userLogin.getMobile_no())) {
					login.setMobile_no(userLogin.getMobile_no());
				}
				if (Objects.nonNull(userLogin.getEmail_address())) {
					login.setEmail_address(userLogin.getEmail_address());
				}
				if (Objects.nonNull(userLogin.getMer_representative_name())) {
					login.setMer_representative_name(userLogin.getMer_representative_name());
				}
				if (Objects.nonNull(userLogin.getUnit_name())) {
					login.setUnit_name(userLogin.getUnit_name());
				}
				if (Objects.nonNull(userLogin.getUnit_type())) {
					login.setUnit_type(userLogin.getUnit_type());
				}

			}

			loginRepo.save(login);
			String jsonUserData = objectMapper.writeValueAsString(login.toString());
			String encryptedData = encryption.encrypt(jsonUserData, psuDeviceID);
			//System.out.println("Encrypted data: " + encryptedData);
			return encryptedData;

		} catch (Exception ex) {
			logger.debug("Update representative service-->" + ex.getMessage());
			String jsonUserData = objectMapper.writeValueAsString("Failed to update representative");
			String encryptedData = encryption.encrypt(jsonUserData, psuDeviceID);
			//System.out.println("Encrypted data: " + encryptedData);
			return encryptedData;
		}

	}

	// Verify Representative Profile
	public String VerifyUserDetailsProfile(EncryptionEntity EncryptedString, String psuDeviceID) throws Exception {
		try {
			//System.out.println("Encrypted data: " + EncryptedString.getEncryptedstring());
			String decryptedData = encryption.decrypt(EncryptedString.getEncryptedstring(), psuDeviceID);
			//System.out.println("Decrypted data: " + decryptedData);
			LoginEntity userLogin = objectMapper.readValue(decryptedData, LoginEntity.class);
			//System.out.println("Decrypted user ID: " + userLogin.getMerchant_user_id());
			//System.out.println("Decrypted user: " + userLogin);

			LoginEntity login = loginRepo.findByMerchantRepID(userLogin.getMerchant_rep_id());
			if (login.getEntry_flag() == "N" && login.getModify_flag() == "N") {
				login.setEntry_flag("Y");
			} else if (login.getModify_flag() == "N" && login.getEntry_flag() == "Y") {
				login.setModify_flag("Y");
			} else if (login.getModify_flag().trim().equals("Y") && login.getEntry_flag().trim().equals("N")) {
				login.setModify_flag("N");
				login.setEntry_flag("Y");
			}
			login.setVerify_user(userLogin.getEntry_user());
			login.setVerify_time(new Date());
			loginRepo.save(login);
			String audit_ref_no = sequence.generateRequestUUId();
			IPSAuditTable audit = new IPSAuditTable();
			audit.setAudit_date(new Date());
			audit.setEntry_time(new Date());
			audit.setEntry_user(login.getEntry_user());
			audit.setFunc_code("VERIFY REPRESENTATIVE");
			audit.setRemarks(login.getMerchant_rep_id() + " : REPRESENTATIVE IS VERIFIED SUCCESSFULLY");
			audit.setAudit_table("BIPS_PASSWORD_MANAGEMENT");
			audit.setAudit_screen("VERIFY REPRESENTATIVE");
			audit.setEvent_id(login.getMerchant_rep_id());
			audit.setEvent_name(login.getMer_representative_name());
			audit.setModi_details("-");
			audit.setAudit_ref_no(audit_ref_no);
			ipsAuditTableRep.save(audit);
			Map<String, String> response = new HashMap<>();
			response.put("Status", "Success");
			response.put("Message", "Data Verified and Saved successfully.");
			logger.debug("Device Created Successfully");
			String jsonUserData = objectMapper.writeValueAsString(response);
			String encryptedData = encryption.encrypt(jsonUserData, psuDeviceID);
			//System.out.println("Encrypted data: " + encryptedData);
			return encryptedData;
		} catch (Exception ex) {
			logger.debug("Update representative service-->" + ex.getMessage());
			String jsonUserData = objectMapper.writeValueAsString("Failed to update representative");
			String encryptedData = encryption.encrypt(jsonUserData, psuDeviceID);
			//System.out.println("Encrypted data: " + encryptedData);
			return encryptedData;
		}

	}

	// Utility method to strip the time part from a Date object
	private static Date stripTime(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	// Login for Tab Service
	public String AndroidLogin(EncryptionEntity EncryptedString, String psuDeviceID) throws Exception {
		//System.out.println("Encrypted data: " + EncryptedString.getEncryptedstring());
		String decryptedData = encryption.decrypt(EncryptedString.getEncryptedstring(), psuDeviceID);
		//System.out.println("Decrypted data: " + decryptedData);
		LoginTabEntity repLogin = objectMapper.readValue(decryptedData, LoginTabEntity.class);
		//System.out.println("Decrypted user: " + repLogin);

		LoginEntity login = loginRepo.findByMerchantRepID(repLogin.getUserid());
		UserManagementEntity login1 = userRepo.findByMerchantUserId(repLogin.getUserid());
		if (login != null) {
			//System.out.println("User ID found in LoginEntity table");
			String response = callRepLogin(login, repLogin.getPassword());
			LoginSession(login.getMerchant_user_id(), login.getMerchant_rep_id(), login.getUnit_id(),
					repLogin.getIp_address(), repLogin.getDevice_id(), "TAB", repLogin.getOs_version(),
					repLogin.getApp_version());
			return response;

		} else if (login1 != null) {
			//System.out.println("User ID found in UserManagementEntity table");
			String response = calluserLogin(login1, repLogin.getPassword());
			LoginSession(login1.getMerchant_user_id(), login1.getUser_id(), login1.getUnit_id_u(),
					repLogin.getIp_address(), repLogin.getDevice_id(), "TAB", repLogin.getOs_version(),
					repLogin.getApp_version());
			return response;
		} else {
			return "User not found. Please ensure correct credentials and attempt again.";
		}

	}

	// Login for Mobile
	public String UserLogin(EncryptionEntity EncryptedString, String psuDeviceID) throws NotFoundException,
			NoSuchAlgorithmException, InvalidKeySpecException, Exception, JsonProcessingException {

		//System.out.println("Encrypted data: " + EncryptedString.getEncryptedstring());
		String decryptedData = encryption.decrypt(EncryptedString.getEncryptedstring(), psuDeviceID);
		//System.out.println("Decrypted data: " + decryptedData);
		LoginTabEntity repLogin = objectMapper.readValue(decryptedData, LoginTabEntity.class);
		//System.out.println("Decrypted user: " + repLogin);

		UserManagementEntity login1 = userRepo.findByMerchantUserId(repLogin.getUserid());
		String response = calluserLogin(login1, repLogin.getPassword());
		LoginSession(login1.getMerchant_user_id(), login1.getUser_id(), login1.getUnit_id_u(), repLogin.getIp_address(),
				repLogin.getDevice_id(), "Mobile", repLogin.getOs_version(), repLogin.getApp_version());

		return response;
	}

	public void LoginSession(String merchantid, String repid, String unitid, String ipaddress, String deviceid,
			String devicetype, String osverison, String appversion) {
		LoginSessionEntity session = new LoginSessionEntity();
		session.setSession_id(UUID.randomUUID().toString());
		session.setMerchant_id(merchantid);
		session.setUser_id(repid);
		session.setUnit_id(unitid);
		session.setLogin_date(new Date());
		session.setLast_active_date(new Date());
		session.setSession_status("ACTIVE");
		session.setIp_address(ipaddress);
		session.setDevice_id(deviceid);
		session.setDevice_type(devicetype);
		session.setOs_version(osverison);
		session.setApp_version(appversion);
		session.setFailed_attempts(currentAttempts);
		session.setFailure_reason(response);
		loginsessionrepo.save(session);

	}

	public String callRepLogin(LoginEntity login, String password)
			throws NoSuchAlgorithmException, InvalidKeySpecException {
		Date currentDate = stripTime(new Date());
		Date dateToCheck = new Date(currentDate.getTime() + (2 * 24 * 60 * 60 * 1000));
		if (login.getMerchant_rep_id() != null) {
			currentAttempts = login.getNo_of_attmp();
			// check Account expires Date
			if (dateToCheck.compareTo(login.getAccount_expiry_date()) < 0) {

				// Check Password expires date
				if (dateToCheck.compareTo(login.getPassword_expiry_date()) < 0) {
					String originalPassword = password;
					String storedPassword = login.getPassword();
					boolean passwordMatches = PasswordEncryption.validatePassword(originalPassword, storedPassword);

					// Password Check
					if (passwordMatches) {
						//System.out.println("Password is correct!");
						logger.debug("login for tab service--->Password is correct");

						// Lock flag check
						if (!login.getUser_locked_flg().equals("Y") && login.getUser_locked_flg().equals("N")) {

							// Disable date check
							if (login.getUser_disable_from_date() != null && login.getUser_disable_to_date() != null) {
								if ((currentDate.equals(login.getUser_disable_from_date())
										|| currentDate.after(login.getUser_disable_from_date()))
										&& login.getUser_disable_flag().equals("Y")
										&& (currentDate.equals(login.getUser_disable_to_date())
												|| currentDate.before(login.getUser_disable_to_date()))) {
									//System.out.println("The current date is within the range.");
									logger.debug("login for tab service---> The current date is within the range.");
									response = "User disabled. Please ensure the credentials.";
									return response;
								} else {
									if (currentDate.after(login.getUser_disable_to_date())
											&& login.getUser_disable_flag().equals("Y")) {
										login.setUser_disable_flag("N");
										loginRepo.save(login);
									}
									//System.out.println("The current date is outside the range.");
									logger.debug("login for tab service---> The current date is outside the range.");

									// Check the Delflag and the password life
									if (login.getDel_flag().equals("N") && !login.getPassword_life().equals("0")) {

										// Check the login flag
										if (login.getLogin_status().equals("N")) {
											login.setLogin_status("Y");
											login.setLogin_channel("TAB");
											login.setNo_of_attmp(BigDecimal.ZERO);
											loginRepo.save(login);
										} else {
											response = "You're already logged in. Please log out from another device first.";
											return response;
										}
									} else {
										response = "User not found. Please ensure correct credentials and attempt again.";
										return response;
									}
								}
							} else {
								//System.out.println("User disable dates are not set.");
								logger.debug("login for tab service---> disable date is null");

								// Check the Delflag and the password life
								if (login.getDel_flag().equals("N") && !login.getPassword_life().equals("0")) {

									// Check the login flag
									if (login.getLogin_status().equals("N")) {
										login.setLogin_status("Y");
										login.setLogin_channel("TAB");
										login.setNo_of_attmp(BigDecimal.ZERO);
										loginRepo.save(login);
										String audit_ref_no = sequence.generateRequestUUId();
										IPSAuditTable audit = new IPSAuditTable();
										audit.setAudit_date(new Date());
										audit.setEntry_time(new Date());
										audit.setEntry_user(login.getMerchant_rep_id());
										audit.setFunc_code("LOGIN");
										audit.setRemarks("LOGIN SUCCESSFULLY");
										audit.setAudit_table("BIPS_PASSWORD_MANAGEMENT");
										audit.setAudit_screen("LOGIN");
										audit.setEvent_id(login.getMerchant_rep_id());
										audit.setEvent_name(login.getMer_representative_name());
										audit.setModi_details("-");
										audit.setAudit_ref_no(audit_ref_no);
										ipsAuditTableRep.save(audit);
									} else {
										response = "You're already logged in. Please log out from another device first.";
										return response;
									}
								} else {
									response = "User not found. Please ensure correct credentials and attempt again.";
									return response;
								}
							}
						} else {
							response = "Your Account is Locked";
							return response;
						}
					} else {
						currentAttempts = currentAttempts.add(BigDecimal.ONE);
						login.setNo_of_attmp(currentAttempts);
						System.out.print(currentAttempts.toString());
						logger.debug("login for tab service" + currentAttempts.toString());
						loginRepo.save(login);

						if (currentAttempts.compareTo(new BigDecimal(maxAttempts)) >= 0) {
							login.setUser_locked_flg("Y");
							System.out.print("flagchanged");
							loginRepo.save(login);
							response = "Account locked due to too many failed attempts.";
							return response;
						} else {
							response = "Incorrect password. Please double-check and try again.";
							return response;
						}
					}
				} else {
					response = "Your password has expired. Please reset it to continue.";
					return response;
				}
			} else {
				response = "Your account has expired. Please contact support for assistance.";
				return response;
			}

		} else {
			response = "Not Found";
			return response;
		}
		System.out.print("Service---------------------->" + login.toString());
		response = "Success";
		return login.toString();
	}

	public String calluserLogin(UserManagementEntity login, String password)
			throws NoSuchAlgorithmException, InvalidKeySpecException {
		Date currentDate = stripTime(new Date());
		Date dateToCheck = new Date(currentDate.getTime() + (2 * 24 * 60 * 60 * 1000));

		// Check the user
		if (login.getUser_id() != null) {
			currentAttempts = login.getNo_of_attmp();
			// Account Expires check
			if (dateToCheck.compareTo(login.getAccount_expiry_date1()) < 0) {

				// Password Expires check
				if (dateToCheck.compareTo(login.getPassword_expiry_date1()) < 0) {
					String originalPassword = password;
					String storedPassword = login.getPassword1();
					boolean passwordMatches = PasswordEncryption.validatePassword(originalPassword, storedPassword);

					// Password Check
					if (passwordMatches) {
						//System.out.println("Password is correct!");

						// Lock Flag Check
						if (!login.getUser_locked_flg().equals("Y") && login.getUser_locked_flg().equals("N")) {

							// Disable date check
							if (login.getUser_disable_from_date1() != null
									&& login.getUser_disable_to_date1() != null) {
								if ((currentDate.equals(login.getUser_disable_from_date1())
										|| currentDate.after(login.getUser_disable_from_date1()))
										&& login.getUser_disable_flag1().equals("Y")
										&& (currentDate.equals(login.getUser_disable_to_date1())
												|| currentDate.before(login.getUser_disable_to_date1()))) {
									//System.out.println("The current date is within the range.");
									logger.debug("Login service for Mobile The current date is within the range.");
									response = "User disabled. Please ensure the credentials.";
									return response;
								} else {
									if (currentDate.after(login.getUser_disable_to_date1())
											&& login.getUser_disable_flag1().equals("Y")) {
										login.setUser_disable_flag1("N");
										userRepo.save(login);
									}
									//System.out.println("The current date is outside the range.");
									logger.debug("Login service for Mobile The current date is outside the range.");
									if (login.getDel_flag1().equals("N") && !login.getPassword_life1().equals("0")) {
										if (login.getLogin_status1().equals("N")) {
											login.setLogin_status1("Y");
											login.setLogin_channel1("Mobile");
											login.setNo_of_attmp(BigDecimal.ZERO);
											userRepo.save(login);
											String audit_ref_no = sequence.generateRequestUUId();
											IPSAuditTable audit = new IPSAuditTable();
											audit.setAudit_date(new Date());
											audit.setEntry_time(new Date());
											audit.setEntry_user(login.getUser_id());
											audit.setFunc_code("LOGIN");
											audit.setRemarks("LOGIN SUCCESSFULLY");
											audit.setAudit_table("BIPS_MERCHANT_USER_MANAGEMENT");
											audit.setAudit_screen("LOGIN");
											audit.setEvent_id(login.getUser_id());
											audit.setEvent_name(login.getUser_name());
											audit.setModi_details("-");
											audit.setAudit_ref_no(audit_ref_no);
											ipsAuditTableRep.save(audit);
										} else {
											response = "You're already logged in to proceed, you must log out from another device first.";
											return response;
										}
									} else {
										response = "User not found. Please ensure correct credentials and attempt again.";
										return response;
									}
								}
							} else {
								//System.out.println("User disable dates are not set.");
								if (login.getDel_flag1().equals("N") && !login.getPassword_life1().equals("0")) {
									if (login.getLogin_status1().equals("N")) {
										login.setLogin_status1("Y");
										login.setLogin_channel1("TAB");
										login.setNo_of_attmp(BigDecimal.ZERO);
										userRepo.save(login);
									} else {
										response = "You're already logged in. Please log out from another device first.";
										return response;
									}
								} else {
									response = "User not found. Please ensure correct credentials and attempt again.";
									return response;
								}
							}
						} else {
							response = "Your Account is Locked";
							return response;
						}
					} else {
						currentAttempts = currentAttempts.add(BigDecimal.ONE);
						login.setNo_of_attmp(currentAttempts);
						System.out.print(currentAttempts.toString());
						userRepo.save(login);

						if (currentAttempts.compareTo(new BigDecimal(maxAttempts)) >= 0) {
							login.setUser_locked_flg("Y");
							System.out.print("flagchanged");
							userRepo.save(login);
							response = "Account locked due to too many failed attempts.";
							return response;
						} else {
							response = "Incorrect password. Please double-check and try again.";
							return response;
						}

					}
				} else {
					response = "Your password has expired. Please reset it to continue.";
					return response;
				}
			} else {
				response = "Your account has expired. Please contact support for assistance.";
				return response;
			}
		} else {
			response = "Not Found";
			return response;
		}
		return login.toString();
	}

	// Login for Tab Service
	public String RepLogin(EncryptionEntity EncryptedString, String psuDeviceID) throws Exception {
		//Date currentDate = stripTime(new Date());
		//Date dateToCheck = new Date(currentDate.getTime() + (2 * 24 * 60 * 60 * 1000));

		//System.out.println("Encrypted data: " + EncryptedString.getEncryptedstring());
		String decryptedData = encryption.decrypt(EncryptedString.getEncryptedstring(), psuDeviceID);
		//System.out.println("Decrypted data: " + decryptedData);
		LoginEntity repLogin = objectMapper.readValue(decryptedData, LoginEntity.class);
		//System.out.println("Decrypted user ID: " + repLogin.getMerchant_user_id());
		//System.out.println("Decrypted user: " + repLogin);

		LoginEntity login = loginRepo.findByMerchantRepID(repLogin.getMerchant_rep_id());
		return callRepLogin(login, repLogin.getPassword());
	}

	// Logout for Tab service
	public String Logoutfortab(EncryptionEntity EncryptedString, String psuDeviceID) throws Exception {

		//System.out.println("Encrypted data: " + EncryptedString.getEncryptedstring());
		String decryptedData = encryption.decrypt(EncryptedString.getEncryptedstring(), psuDeviceID);
		//System.out.println("Decrypted data: " + decryptedData);
		LogoutEntity replogout = objectMapper.readValue(decryptedData, LogoutEntity.class);

		//System.out.println("Decrypted user ID: " + replogout.getMerchant_user_id());
		//System.out.println("Decrypted user: " + replogout);
		LoginEntity login = loginRepo.findByMerchantRepID(replogout.getUser_id());

		System.out.print("Logout Representative");
		if (login.getLogin_status().equals("Y")) {
			login.setLogin_status("N");
			loginRepo.save(login);
			LoginSessionEntity killsesssion = loginsessionrepo.findByuserID(replogout.getUser_id());
			if (Objects.nonNull(killsesssion)) {
				LoginSessionHistoryLogout(replogout.getUser_id());
			}
			String audit_ref_no = sequence.generateRequestUUId();
			IPSAuditTable audit = new IPSAuditTable();
			audit.setAudit_date(new Date());
			audit.setEntry_time(new Date());
			audit.setEntry_user(login.getMerchant_rep_id());
			audit.setFunc_code("LOGOUT");
			audit.setRemarks("LOGOUT SUCCESSFULLY");
			audit.setAudit_table("BIPS_PASSWORD_MANAGEMENT");
			audit.setAudit_screen("LOGOUT");
			audit.setEvent_id(login.getMerchant_rep_id());
			audit.setEvent_name(login.getMer_representative_name());
			audit.setModi_details("-");
			audit.setAudit_ref_no(audit_ref_no);
			ipsAuditTableRep.save(audit);
		}
		return login.toString();
	}
	
	public String LogoutfortabUsingWeb(String MerchantRepId) throws Exception {

		LoginEntity login = loginRepo.findByMerchantRepID(MerchantRepId);

		System.out.print("Logout Representative");
		if (login.getLogin_status().equals("Y")) {
			login.setLogin_status("N");
			login.setUser_locked_flg("N");
			login.setNo_of_attmp(BigDecimal.ZERO);
			login.setUser_status("ACTIVE");
			loginRepo.save(login);
			LoginSessionEntity killsesssion = loginsessionrepo.findByuserID(MerchantRepId);
			if (Objects.nonNull(killsesssion)) {
				LoginSessionHistoryLogout(MerchantRepId);
			}
			String audit_ref_no = sequence.generateRequestUUId();
			IPSAuditTable audit = new IPSAuditTable();
			audit.setAudit_date(new Date());
			audit.setEntry_time(new Date());
			audit.setEntry_user(login.getMerchant_rep_id());
			audit.setFunc_code("LOGOUT");
			audit.setRemarks("LOGOUT SUCCESSFULLY");
			audit.setAudit_table("BIPS_PASSWORD_MANAGEMENT");
			audit.setAudit_screen("LOGOUT");
			audit.setEvent_id(login.getMerchant_rep_id());
			audit.setEvent_name(login.getMer_representative_name());
			audit.setModi_details("-");
			audit.setAudit_ref_no(audit_ref_no);
			ipsAuditTableRep.save(audit);
		}
		return login.toString();
	}

	private void LoginSessionHistoryLogout(String merchant_rep_id) {
		LoginSessionEntity session = loginsessionrepo.findByuserID(merchant_rep_id);
		LoginSessionHistoryEntity history = new LoginSessionHistoryEntity();
		history.setSession_id(session.getSession_id());
		history.setMerchant_id(session.getMerchant_id());
		history.setUser_id(session.getUser_id());
		history.setUnit_id(session.getUnit_id());
		history.setLogin_date(session.getLogin_date());
		history.setLogout_date(new Date()); // Assuming logout time is the last active date
		history.setSession_status("INACTIVE"); // or any appropriate status
		history.setIp_address(session.getIp_address());
		history.setDevice_id(session.getDevice_id());
		history.setDevice_type(session.getDevice_type());
		history.setOs_version(session.getOs_version());
		history.setApp_version(session.getApp_version());
		history.setSession_token(session.getSession_token());
		history.setFailed_attempts(session.getFailed_attempts());
		history.setFailure_reason(session.getFailure_reason());

		loginSessionHistoryRepo.save(history);

		// Delete the active session
		loginsessionrepo.delete(session);

	}

	// Check Password for Tab
	public String CheckPasswordForTab(String merchant_id, String password)
			throws NoSuchAlgorithmException, InvalidKeySpecException {
		LoginEntity login = loginRepo.findByMerchantRepID(merchant_id);
		String originalPassword = password;
		String storedPassword = login.getPassword();
		boolean passwordMatches = PasswordEncryption.validatePassword(originalPassword, storedPassword);
		if (passwordMatches) {
			//System.out.println(login.toString());
			return "Success";
		} else {
			return "Failed";
		}
	}

	// Check password for Mobile
	public String CheckPasswordForMobile(String user_id, String password)
			throws NoSuchAlgorithmException, InvalidKeySpecException {
		UserManagementEntity login = userRepo.findByMerchantUserId(user_id);
		String originalPassword = password;
		String storedPassword = login.getPassword1();
		boolean passwordMatches = PasswordEncryption.validatePassword(originalPassword, storedPassword);
		if (passwordMatches) {
			//System.out.println(login.toString());
			return "Success";
		} else {
			return "Failed";
		}
	}

	// Logout Service for Mobile
	public String LogoutMobile(EncryptionEntity EncryptedString, String psuDeviceID) throws Exception {
		//System.out.println("Encrypted data: " + EncryptedString.getEncryptedstring());
		String decryptedData = encryption.decrypt(EncryptedString.getEncryptedstring(), psuDeviceID);
		//System.out.println("Decrypted data: " + decryptedData);
		LogoutEntity userLogout = objectMapper.readValue(decryptedData, LogoutEntity.class);
		//System.out.println("Decrypted user ID: " + userLogout.getMerchant_user_id());
		//System.out.println("Decrypted user: " + userLogout);
		System.out.println("---------"+userLogout.getUser_id());
		UserManagementEntity login = userRepo.findByMerchantUserId(userLogout.getUser_id());
		System.out.println("uujbnj");
		if (login.getLogin_status1().equals("Y")) {
			login.setLogin_status1("N");
			userRepo.save(login);
			LoginSessionEntity killsesssion = loginsessionrepo.findByuserID(userLogout.getUser_id());
			if (Objects.nonNull(killsesssion)) {
				LoginSessionHistoryLogout(userLogout.getUser_id());
			}
			String audit_ref_no = sequence.generateRequestUUId();
			IPSAuditTable audit = new IPSAuditTable();
			audit.setAudit_date(new Date());
			audit.setEntry_time(new Date());
			audit.setEntry_user(login.getUser_id());
			audit.setFunc_code("LOGOUT");
			audit.setRemarks("LOGOUT SUCCESSFULLY");
			audit.setAudit_table("BIPS_MERCHANT_USER_MANAGEMENT");
			audit.setAudit_screen("LOGOUT");
			audit.setEvent_id(login.getUser_id());
			audit.setEvent_name(login.getUser_name());
			audit.setModi_details("-");
			audit.setAudit_ref_no(audit_ref_no);
			ipsAuditTableRep.save(audit);
		}
		return login.toString();
	}

	public String LogoutMobileUsingWeb(String user_id) throws Exception {
		
		UserManagementEntity login = userRepo.findByMerchantUserId(user_id);
		if (login.getLogin_status1().equals("Y")) {
			login.setLogin_status1("N");
			login.setUser_locked_flg("N");
			login.setNo_of_attmp(BigDecimal.ZERO);
			login.setUser_status1("ACTIVE");
			userRepo.save(login);
			LoginSessionEntity killsesssion = loginsessionrepo.findByuserID(user_id);
			if (Objects.nonNull(killsesssion)) {
				LoginSessionHistoryLogout(user_id);
			}
			String audit_ref_no = sequence.generateRequestUUId();
			IPSAuditTable audit = new IPSAuditTable();
			audit.setAudit_date(new Date());
			audit.setEntry_time(new Date());
			audit.setEntry_user(login.getUser_id());
			audit.setFunc_code("LOGOUT");
			audit.setRemarks("LOGOUT SUCCESSFULLY");
			audit.setAudit_table("BIPS_MERCHANT_USER_MANAGEMENT");
			audit.setAudit_screen("LOGOUT");
			audit.setEvent_id(login.getUser_id());
			audit.setEvent_name(login.getUser_name());
			audit.setModi_details("-");
			audit.setAudit_ref_no(audit_ref_no);
			ipsAuditTableRep.save(audit);
		}
		return login.toString();
	}
	// Generate OTP Reset Password for user
	public PasswordResetResponse SentOTPforUser(String user_id) {
		UserManagementEntity login = userRepo.findByMerchantUserId(user_id);
		if (login.getUser_id() != null) {
			System.out.print(login.getMobile_no1());
			BigDecimal mobileNumber = login.getMobile_no1();
			BigDecimal otp = new BigDecimal(sequence.generateOTP());
			System.out.print(otp);
			String category = login.getUser_category();
			return new PasswordResetResponse(mobileNumber, otp, category);
		} else {
			BigDecimal ch = BigDecimal.ZERO;
			return new PasswordResetResponse(ch, ch, "Not Found");

		}
	}

	// Generate OTP Reset Password For Merchant Representative
	public PasswordResetResponse SentOTPforMerchant(String merchant_rep_id) {
		LoginEntity login = loginRepo.findByMerchantRepID(merchant_rep_id);
		if (login.getMerchant_rep_id() != null) {
			//System.out.println(login.getMobile_no());
			BigDecimal mobileNumber = login.getMobile_no();
			BigDecimal otp = new BigDecimal(sequence.generateOTP());
			String category = login.getUser_category();
			System.out.print(otp);
			return new PasswordResetResponse(mobileNumber, otp, category);
		} else {
			BigDecimal ch = BigDecimal.ZERO;
			return new PasswordResetResponse(ch, ch, "Not Found");
		}

	}
	
	@Autowired
	UserProfileRep userProfileRep;
	
	// Generate OTP Reset Password For Merchant Representative
		public PasswordResetResponse SentOTPToBankUser(String merchant_rep_id) {
			UserProfile login = userProfileRep.findByIdCustom(merchant_rep_id);
			if (login.getUserid() != null) {
				//System.out.println(login.getMobile_no());
				BigDecimal mobileNumber = new BigDecimal(login.getMob_number());
				
				BigDecimal otp = new BigDecimal(sequence.generateOTP());
				String category = login.getUserid();
				System.out.print(otp);
				return new PasswordResetResponse(mobileNumber, otp, category);
			} else {
				BigDecimal ch = BigDecimal.ZERO;
				return new PasswordResetResponse(ch, ch, "Not Found");
			}

		}
	// Generate OTP Reset Password For Android Representative
	public PasswordResetResponse SentOTPforAndroid(String merchant_rep_id) {
		LoginEntity login = loginRepo.findByMerchantRepID(merchant_rep_id);
		UserManagementEntity login1 = userRepo.findByMerchantUserId(merchant_rep_id);
		if (login != null) {
			//System.out.println("User ID found in LoginEntity table");
			//System.out.println(login.getMobile_no());
			BigDecimal mobileNumber = login.getMobile_no();
			BigDecimal otp = new BigDecimal(sequence.generateOTP());
			String category = login.getUser_category();
			System.out.print(otp);
			return new PasswordResetResponse(mobileNumber, otp, category);
		} else if (login1 != null) {
			//System.out.println("User ID found in LoginEntity table");
			//System.out.println(login1.getMobile_no1());
			BigDecimal mobileNumber = login1.getMobile_no1();
			BigDecimal otp = new BigDecimal(sequence.generateOTP());
			String category = login1.getUser_category();
			System.out.print(otp);
			return new PasswordResetResponse(mobileNumber, otp, category);
		} else {
			BigDecimal ch = BigDecimal.ZERO;
			return new PasswordResetResponse(ch, ch, "Not Found");
		}

	}

	// Reset Password for User
	public String ChangeUserPassword(EncryptionEntity EncryptedString, String psuDeviceID)
			throws NotFoundException, NoSuchAlgorithmException, InvalidKeySpecException {
		try {
			//System.out.println("Encrypted data: " + EncryptedString.getEncryptedstring());
			String decryptedData = encryption.decrypt(EncryptedString.getEncryptedstring(), psuDeviceID);
			//System.out.println("Decrypted data: " + decryptedData);
			ChangePasswordEntity resetuserpassowrd = objectMapper.readValue(decryptedData, ChangePasswordEntity.class);
			//System.out.println("Decrypted user ID: " + resetuserpassowrd.getMerchant_user_id());
			//System.out.println("Decrypted user: " + resetuserpassowrd);
			final Calendar cal = Calendar.getInstance();
			cal.add(Calendar.MONTH, 6);
			UserManagementEntity login = userRepo.findByMerchantUserId(resetuserpassowrd.getUser_id());
			String originalPassword = resetuserpassowrd.getOldpassword();
			String storedPassword = login.getPassword1();
			boolean passwordMatches = PasswordEncryption.validatePassword(originalPassword, storedPassword);
			// Password Check
			if (passwordMatches) {
				String encryptedPassword = PasswordEncryption.getEncryptedPassword(resetuserpassowrd.getNewpassword());
				//System.out.println(encryptedPassword);
				login.setPassword1(encryptedPassword);
				login.setPassword_expiry_date1(cal.getTime());
				userRepo.save(login);
				String audit_ref_no = sequence.generateRequestUUId();
				IPSAuditTable audit = new IPSAuditTable();
				audit.setAudit_date(new Date());
				audit.setEntry_time(new Date());
				audit.setEntry_user(login.getUser_id());
				audit.setFunc_code("CHANGE PASSWORD");
				audit.setRemarks("PASSWORD CHANGED SUCCESSFULLY");
				audit.setAudit_table("BIPS_MERCHANT_USER_MANAGEMENT");
				audit.setAudit_screen("CHANGE PASSWORD");
				audit.setEvent_id(login.getUser_id());
				audit.setEvent_name(login.getUser_name());
				audit.setModi_details("-");
				audit.setAudit_ref_no(audit_ref_no);
				ipsAuditTableRep.save(audit);
				//System.out.println("Resetted the User Password");
				return "Success";
			}
			else {
				return "Incorrect Password";
			}
			
		} catch (Exception ex) {
			//System.out.println("Failed to Reset the User Password");
			return "Failed";
		}

	}
	
	
	public String ChangeNewUserPassword(EncryptionEntity EncryptedString, String psuDeviceID)
			throws NotFoundException, NoSuchAlgorithmException, InvalidKeySpecException {
		try {
			//System.out.println("Encrypted data: " + EncryptedString.getEncryptedstring());
			String decryptedData = encryption.decrypt(EncryptedString.getEncryptedstring(), psuDeviceID);
			//System.out.println("Decrypted data: " + decryptedData);
			ChangePasswordEntity resetuserpassowrd = objectMapper.readValue(decryptedData, ChangePasswordEntity.class);
			//System.out.println("Decrypted user ID: " + resetuserpassowrd.getMerchant_user_id());
			//System.out.println("Decrypted user: " + resetuserpassowrd);
			final Calendar cal = Calendar.getInstance();
			cal.add(Calendar.MONTH, 6);
			UserManagementEntity login = userRepo.findByMerchantUserId(resetuserpassowrd.getUser_id());
				String encryptedPassword = PasswordEncryption.getEncryptedPassword(resetuserpassowrd.getNewpassword());
				//System.out.println(encryptedPassword);
				login.setPassword1(encryptedPassword);
				login.setPassword_expiry_date1(cal.getTime());
				userRepo.save(login);
				String audit_ref_no = sequence.generateRequestUUId();
				IPSAuditTable audit = new IPSAuditTable();
				audit.setAudit_date(new Date());
				audit.setEntry_time(new Date());
				audit.setEntry_user(login.getUser_id());
				audit.setFunc_code("CHANGE PASSWORD");
				audit.setRemarks("PASSWORD CHANGED SUCCESSFULLY");
				audit.setAudit_table("BIPS_MERCHANT_USER_MANAGEMENT");
				audit.setAudit_screen("CHANGE PASSWORD");
				audit.setEvent_id(login.getUser_id());
				audit.setEvent_name(login.getUser_name());
				audit.setModi_details("-");
				audit.setAudit_ref_no(audit_ref_no);
				ipsAuditTableRep.save(audit);
				//System.out.println("Resetted the User Password");
				return "Success";
		} catch (Exception ex) {
			//System.out.println("Failed to Reset the User Password");
			return "Failed";
		}

	}
	
	
	
	
	public String ChangeMerchantNewPassword(EncryptionEntity EncryptedString, String psuDeviceID)
			throws NotFoundException, NoSuchAlgorithmException, InvalidKeySpecException {
		try {
			//System.out.println("Encrypted data: " + EncryptedString.getEncryptedstring());
			String decryptedData = encryption.decrypt(EncryptedString.getEncryptedstring(), psuDeviceID);
			//System.out.println("Decrypted data: " + decryptedData);
			ChangePasswordEntity resetMerchantpassword = objectMapper.readValue(decryptedData, ChangePasswordEntity.class);
			//System.out.println("Decrypted user ID: " + resetMerchantpassword.getMerchant_user_id());
			//System.out.println("Decrypted user: " + resetMerchantpassword);
			final Calendar cal = Calendar.getInstance();
			cal.add(Calendar.MONTH, 6);
			LoginEntity login = loginRepo.findByMerchantRepID(resetMerchantpassword.getUser_id());
				String encryptedPassword = PasswordEncryption.getEncryptedPassword(resetMerchantpassword.getNewpassword());
				//System.out.println(encryptedPassword);
				login.setPassword(encryptedPassword);
				login.setPassword_expiry_date(cal.getTime());
				loginRepo.save(login);
				String audit_ref_no = sequence.generateRequestUUId();
				IPSAuditTable audit = new IPSAuditTable();
				audit.setAudit_date(new Date());
				audit.setEntry_time(new Date());
				audit.setEntry_user(login.getMerchant_rep_id());
				audit.setFunc_code("CHANGE PASSWORD");
				audit.setRemarks("PASSWORD CHANGED SUCCESSFULLY");
				audit.setAudit_table("BIPS_PASSWORD_MANAGEMENT");
				audit.setAudit_screen("CHANGE PASSWORD");
				audit.setEvent_id(login.getMerchant_rep_id());
				audit.setEvent_name(login.getMer_representative_name());
				audit.setModi_details("-");
				audit.setAudit_ref_no(audit_ref_no);
				ipsAuditTableRep.save(audit);
				//System.out.println("Resetted the User Password");
				return "Success";	
			
		} catch (Exception ex) {
			//System.out.println("Failed to Reset the User Password");
			return "Failed";
		}

	}


	// Reset the Merchant Password
	public String ChangeMerchantPassword(EncryptionEntity EncryptedString, String psuDeviceID)
			throws NotFoundException, NoSuchAlgorithmException, InvalidKeySpecException {
		try {
			//System.out.println("Encrypted data: " + EncryptedString.getEncryptedstring());
			String decryptedData = encryption.decrypt(EncryptedString.getEncryptedstring(), psuDeviceID);
			//System.out.println("Decrypted data: " + decryptedData);
			ChangePasswordEntity resetMerchantpassword = objectMapper.readValue(decryptedData, ChangePasswordEntity.class);
			//System.out.println("Decrypted user ID: " + resetMerchantpassword.getMerchant_user_id());
			//System.out.println("Decrypted user: " + resetMerchantpassword);
			final Calendar cal = Calendar.getInstance();
			cal.add(Calendar.MONTH, 6);
			LoginEntity login = loginRepo.findByMerchantRepID(resetMerchantpassword.getUser_id());
			String originalPassword = resetMerchantpassword.getOldpassword();
			String storedPassword = login.getPassword();
			boolean passwordMatches = PasswordEncryption.validatePassword(originalPassword, storedPassword);
			
			System.out.println(originalPassword);
			// Password Check
			if (passwordMatches) {
				String encryptedPassword = PasswordEncryption.getEncryptedPassword(resetMerchantpassword.getNewpassword());
				//System.out.println(encryptedPassword);
				login.setPassword(encryptedPassword);
				login.setPassword_expiry_date(cal.getTime());
				loginRepo.save(login);
				String audit_ref_no = sequence.generateRequestUUId();
				IPSAuditTable audit = new IPSAuditTable();
				audit.setAudit_date(new Date());
				audit.setEntry_time(new Date());
				audit.setEntry_user(login.getMerchant_rep_id());
				audit.setFunc_code("CHANGE PASSWORD");
				audit.setRemarks("PASSWORD CHANGED SUCCESSFULLY");
				audit.setAudit_table("BIPS_PASSWORD_MANAGEMENT");
				audit.setAudit_screen("CHANGE PASSWORD");
				audit.setEvent_id(login.getMerchant_rep_id());
				audit.setEvent_name(login.getMer_representative_name());
				audit.setModi_details("-");
				audit.setAudit_ref_no(audit_ref_no);
				ipsAuditTableRep.save(audit);
				//System.out.println("Resetted the User Password");
				return "Success";

			}
			else {
				return "Incorrect Password";
			}
			
			
		} catch (Exception ex) {
			//System.out.println("Failed to Reset the User Password");
			return "Failed";
		}

	}
	
	
	
	public String ChangeMerchantNewPasswordForInternet(EncryptionEntity EncryptedString, String psuDeviceID)
			throws NotFoundException, NoSuchAlgorithmException, InvalidKeySpecException {
		try {
			//System.out.println("Encrypted data: " + EncryptedString.getEncryptedstring());
			String decryptedData = encryption.decrypt(EncryptedString.getEncryptedstring(), psuDeviceID);
			//System.out.println("Decrypted data: " + decryptedData);
			ChangePasswordEntity resetMerchantpassword = objectMapper.readValue(decryptedData,
					ChangePasswordEntity.class);
			//System.out.println("Decrypted user ID: " + resetMerchantpassword.getUser_id());
			//System.out.println("Decrypted user: " + resetMerchantpassword);
			final Calendar cal = Calendar.getInstance();
			cal.add(Calendar.MONTH, 6);
			LoginEntity login = loginRepo.findByMerchantRepID(resetMerchantpassword.getUser_id());
				String encryptedPassword = PasswordEncryption.getEncryptedPassword(resetMerchantpassword.getNewpassword());
				//System.out.println(encryptedPassword);
				login.setPassword(encryptedPassword);
				login.setPassword_expiry_date(cal.getTime());
				loginRepo.save(login);
				String audit_ref_no = sequence.generateRequestUUId();
				IPSAuditTable audit = new IPSAuditTable();
				audit.setAudit_date(new Date());
				audit.setEntry_time(new Date());
				audit.setEntry_user(login.getMerchant_rep_id());
				audit.setFunc_code("CHANGE PASSWORD");
				audit.setRemarks("PASSWORD CHANGED SUCCESSFULLY");
				audit.setAudit_table("BIPS_PASSWORD_MANAGEMENT");
				audit.setAudit_screen("CHANGE PASSWORD");
				audit.setEvent_id(login.getMerchant_rep_id());
				audit.setEvent_name(login.getMer_representative_name());
				audit.setModi_details("-");
				audit.setAudit_ref_no(audit_ref_no);
				ipsAuditTableRep.save(audit);
				//System.out.println("Resetted the User Password");
				return "Success";
		} catch (Exception ex) {
			//System.out.println("Failed to Reset the User Password");
			return "Failed";
		}
	}

	public String ChangeMerchantPasswordForInternet(EncryptionEntity EncryptedString, String psuDeviceID)
			throws NotFoundException, NoSuchAlgorithmException, InvalidKeySpecException {
		try {
			//System.out.println("Encrypted data: " + EncryptedString.getEncryptedstring());
			String decryptedData = encryption.decrypt(EncryptedString.getEncryptedstring(), psuDeviceID);
			//System.out.println("Decrypted data: " + decryptedData);
			ChangePasswordthroughOtp resetMerchantpassword = objectMapper.readValue(decryptedData,
					ChangePasswordthroughOtp.class);
			//System.out.println("Decrypted user ID: " + resetMerchantpassword.getUser_id());
			//System.out.println("Decrypted user: " + resetMerchantpassword);
			final Calendar cal = Calendar.getInstance();
			cal.add(Calendar.MONTH, 6);
			LoginEntity login = loginRepo.findByMerchantRepID(resetMerchantpassword.getUser_id());
			if(Objects.nonNull(login)) {
				String encryptedPassword = PasswordEncryption.getEncryptedPassword(resetMerchantpassword.getPassword());
				//System.out.println(encryptedPassword);
				login.setPassword(encryptedPassword);
				login.setPassword_expiry_date(cal.getTime());
				loginRepo.save(login);
				String audit_ref_no = sequence.generateRequestUUId();
				IPSAuditTable audit = new IPSAuditTable();
				audit.setAudit_date(new Date());
				audit.setEntry_time(new Date());
				audit.setEntry_user(login.getMerchant_rep_id());
				audit.setFunc_code("CHANGE PASSWORD");
				audit.setRemarks("PASSWORD CHANGED SUCCESSFULLY");
				audit.setAudit_table("BIPS_PASSWORD_MANAGEMENT");
				audit.setAudit_screen("CHANGE PASSWORD");
				audit.setEvent_id(login.getMerchant_rep_id());
				audit.setEvent_name(login.getMer_representative_name());
				audit.setModi_details("-");
				audit.setAudit_ref_no(audit_ref_no);
				ipsAuditTableRep.save(audit);
				//System.out.println("Resetted the User Password");
				return "Password Changed Successfully";

			}else {
				return "No User Found";
			}
			
		} catch (Exception ex) {
			//System.out.println("Failed to Reset the User Password");
			return "Failed";
		}

	}

	public String RepAuthentication(EncryptionEntity EncryptedString, String psuDeviceID) throws Exception {
		//System.out.println("Encrypted data: " + EncryptedString.getEncryptedstring());
		String decryptedData = encryption.decrypt(EncryptedString.getEncryptedstring(), psuDeviceID);
		//System.out.println("Decrypted data: " + decryptedData);
		TwoFactorEntity twofa = objectMapper.readValue(decryptedData, TwoFactorEntity.class);
		//System.out.println("Decrypted user: " + twofa);
		LoginEntity login = loginRepo.findByMerchantRepID(twofa.getUser_id());
		UserManagementEntity login1 = userRepo.findByMerchantUserId(twofa.getUser_id());
		if (login != null) {
			login.setAuthentication_flg("Y");
			loginRepo.save(login);

		} else if (login1 != null) {
			login1.setAuthentication_flg("Y");
			userRepo.save(login1);
		} else {
			Map<String, String> response = new HashMap<>();
			response.put("Status", "Failed");
			response.put("Message", "No data found");
			String jsonUserData = objectMapper.writeValueAsString(response);
			String encryptedData = encryption.encrypt(jsonUserData, psuDeviceID);
			//System.out.println("Encrypted data: " + encryptedData);
			return encryptedData;
		}
		TwoFARepo.save(twofa);
		Map<String, String> response = new HashMap<>();
		response.put("Status", "Success");
		response.put("Message", "Data received and saved successfully.");
		String jsonUserData = objectMapper.writeValueAsString(response);
		String encryptedData = encryption.encrypt(jsonUserData, psuDeviceID);
		//System.out.println("Encrypted data: " + encryptedData);
		return encryptedData;
	}

	public boolean checkAnswer(String userId, int answerNumber, String answer) {
		TwoFactorEntity userAnswers = TwoFARepo.findById(userId).orElse(null);

		if (userAnswers == null) {
			return false;
		}
		switch (answerNumber) {
		case 1:
			return answer.equals(userAnswers.getSecurity_answer_1());
		case 2:
			return answer.equals(userAnswers.getSecurity_answer_2());
		case 3:
			return answer.equals(userAnswers.getSecurity_answer_3());
		case 4:
			return answer.equals(userAnswers.getSecurity_answer_4());
		case 5:
			return answer.equals(userAnswers.getSecurity_answer_5());
		case 6:
			return answer.equals(userAnswers.getSecurity_answer_6());
		case 7:
			return answer.equals(userAnswers.getSecurity_answer_7());
		case 8:
			return answer.equals(userAnswers.getSecurity_answer_8());
		case 9:
			return answer.equals(userAnswers.getSecurity_answer_9());
		case 10:
			return answer.equals(userAnswers.getSecurity_answer_10());
		default:
			return false;
		}

	}
}
