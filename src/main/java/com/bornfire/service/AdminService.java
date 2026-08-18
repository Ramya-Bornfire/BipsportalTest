package com.bornfire.service;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bornfire.config.Encryption;
import com.bornfire.config.SequenceGenerator;
import com.bornfire.entity.AllCustomTransaction;
import com.bornfire.entity.BIPS_Alert_Entity;
import com.bornfire.entity.BIPS_Alert_Repo;
import com.bornfire.entity.BIPS_MerDeviceManagement_Repo;
import com.bornfire.entity.BIPS_Mer_Device_Management_Entity;
import com.bornfire.entity.BIPS_Notification_Entity;
import com.bornfire.entity.BIPS_Notification_Repo;
import com.bornfire.entity.DeviceManagementEntity;
import com.bornfire.entity.DeviceManagementRepository;
import com.bornfire.entity.EncryptionEntity;
import com.bornfire.entity.IPSAuditRepo;
import com.bornfire.entity.IPSAuditTable;
import com.bornfire.entity.NotificationEntity;
import com.bornfire.entity.NotificationRepository;
import com.bornfire.entity.OutwardTransactionMonitoringTable;
import com.bornfire.entity.OutwardTransactionMonitoringTableRep;
import com.bornfire.entity.ServiceReqEntity;
import com.bornfire.entity.ServiceReqRepository;
import com.bornfire.entity.UnitManagementEntity;
import com.bornfire.entity.UnitManagementRepository;
import com.bornfire.entity.UserManagementEntity;
import com.bornfire.entity.UserManagementRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
@ConfigurationProperties("output")
@Transactional
public class AdminService {
	@Autowired
	ServiceReqRepository servicereqrepository;

	@Autowired
	OutwardTransactionMonitoringTableRep outwardTransaction;

	@Autowired
	BIPS_Alert_Repo bIPS_Alert_Repo;

	@Autowired
	BIPS_Notification_Repo bIPS_Notification_Repo;

	@Autowired
	NotificationRepository notificationrepo;

	@Autowired
	BIPS_MerDeviceManagement_Repo bIPS_MerDeviceManagement_Repo;

	@Autowired
	Encryption encryption;

	@Autowired
	Environment env;

	ObjectMapper objectMapper = new ObjectMapper();

	@Autowired
	SequenceGenerator sequence;

	@Autowired
	IPSAuditRepo ipsAuditTableRep;

	public List<NotificationEntity> getAllNotification(String merchant_id, String unit_id) {
		return notificationrepo.getallnotification(merchant_id, unit_id);
	}

	public List<NotificationEntity> getAllNotification(String merchant_id) {
		return notificationrepo.getmerchantnotification(merchant_id);
	}

	public List<NotificationEntity> getUnitNotification(String merchant_id, String unit_id) {
		return notificationrepo.getunitnotification(merchant_id, unit_id);
	}

	// Creation/Addition of new User
	public String postNotification(EncryptionEntity EncryptedString, String psuDeviceID) throws Exception {
		// System.out.println("Encrypted data: " +
		// EncryptedString.getEncryptedstring());
		String decryptedData = encryption.decrypt(EncryptedString.getEncryptedstring(), psuDeviceID);
		// System.out.println("Decrypted data: " + decryptedData);
		NotificationEntity userData = objectMapper.readValue(decryptedData, NotificationEntity.class);
		// System.out.println("Decrypted user: " + userData);
		userData.setEntity_flg("Y");
		userData.setDel_flg("N");
		userData.setEntry_time(new Date());
		notificationrepo.save(userData);

		String audit_ref_no = sequence.generateRequestUUId();
		IPSAuditTable audit = new IPSAuditTable();
		audit.setAudit_date(new Date());
		audit.setEntry_time(new Date());
		audit.setEntry_user(userData.getEntry_user());
		audit.setFunc_code("NOTIFICATION CREATION");
		audit.setRemarks(userData.getRecord_srl_no() + " : NOTIFICATION Created Successfully");
		audit.setAudit_table("NOTIFICATION_PARM_MASTER");
		audit.setAudit_screen("NOTIFICATION");
		audit.setEvent_id(userData.getNotification_event_no());
		audit.setEvent_name(userData.getNotification_event_desc());
		audit.setModi_details("-");
		audit.setAudit_ref_no(audit_ref_no);

		ipsAuditTableRep.save(audit);

		String response = "Notification created successfully";

		String jsonUserData = objectMapper.writeValueAsString(response);
		String encryptedData = encryption.encrypt(jsonUserData, psuDeviceID);
		// System.out.println("Encrypted data: " + encryptedData);
		return encryptedData;
	}

	// Creation/Addition of new User
	public String postService(EncryptionEntity EncryptedString, String psuDeviceID) throws Exception {
		// System.out.println("Encrypted data: " +
		// EncryptedString.getEncryptedstring());
		String decryptedData = encryption.decrypt(EncryptedString.getEncryptedstring(), psuDeviceID);
		// System.out.println("Decrypted data: " + decryptedData);
		ServiceReqEntity userData = objectMapper.readValue(decryptedData, ServiceReqEntity.class);
		// System.out.println("Decrypted user: " + userData);
		userData.setEntry_flag("Y");
		userData.setEntry_date(new Date());
		userData.setDel_flag("N");
		servicereqrepository.save(userData);

		String audit_ref_no = sequence.generateRequestUUId();
		IPSAuditTable audit = new IPSAuditTable();
		audit.setAudit_date(new Date());
		audit.setEntry_time(new Date());
		audit.setEntry_user(userData.getEntry_user());
		audit.setFunc_code("SERVICE CREATION");
		audit.setRemarks(userData.getRequest_id() + " : SERVICE REQUEST CREATED SUCCESSFULLY");
		audit.setAudit_table("SERVICE_REQUEST_MONITORING");
		audit.setAudit_screen("SERVICE_REQUEST");
		audit.setEvent_id(userData.getRequest_id());
		audit.setEvent_name(userData.getRequest_description());
		audit.setModi_details("-");
		audit.setAudit_ref_no(audit_ref_no);

		ipsAuditTableRep.save(audit);

		String response = "Service Request Created Successfully";

		String jsonUserData = objectMapper.writeValueAsString(response);
		String encryptedData = encryption.encrypt(jsonUserData, psuDeviceID);
		// System.out.println("Encrypted data: " + encryptedData);
		return encryptedData;
	}

	// All Service Request List
	public List<ServiceReqEntity> getAllservice(String merchantid) {
		return servicereqrepository.findByAll(merchantid);
	}

	// Unit Service Request List
	public List<ServiceReqEntity> getUnitservice(String merchantid, String unitid) {
		return servicereqrepository.findByunitAll(merchantid, unitid);
	}

	// single api for service
	public List<ServiceReqEntity> getAllUnitservice(String merchantid, String unitid) {
		List<ServiceReqEntity> reponse = servicereqrepository.findByAllMerchantId(merchantid, unitid);
		// System.out.println("Bipsportal Response----->"+reponse.toString());
		return servicereqrepository.findByAllMerchantId(merchantid, unitid);
	}

	// All Transaction list
	public List<OutwardTransactionMonitoringTable> getAllTransactions(String user_id, String Merchant_id) {
		List<OutwardTransactionMonitoringTable> response = outwardTransaction.findTransactionById(user_id, Merchant_id);
		// System.out.println("response------------------> " + response.toString());
		if (response.isEmpty()) {
			// System.out.println("No transactions found for user_id: " + user_id);
			return Collections.emptyList();
		} else {
			return response;
		}
	}

	// All User Transaction List
	public List<OutwardTransactionMonitoringTable> getUserTransaction(String user_id, String Device_id,
			String Merchant_id) {
		List<OutwardTransactionMonitoringTable> response = outwardTransaction.findTansactionUserById(user_id, Device_id,
				Merchant_id);
		// System.out.println("response------------------> " + response.toString());
		if (response.isEmpty()) {
			// System.out.println("No transactions found for user_id: " + user_id + "And
			// Device:" + Device_id);
			return Collections.emptyList();
		} else {
			return response;
		}
	}
	List<Object[]> response;
	// All Transaction List
	public List<AllCustomTransaction> getallMerchantTransaction(String user_id, String merchant_id, String fromdate, String todate,String type) {
		if(type.equals("MOBILEVIEW"))
		{
			 response = outwardTransaction.findAllTansactionOnedayById(user_id,merchant_id,fromdate, todate);
		}
		else {
			 response = outwardTransaction.findAllTansactionById(user_id,merchant_id,fromdate, todate);
		}
		if (response.isEmpty()) {
			return Collections.emptyList();
		} else {
			return response.stream().map(this::mapToCusomDataList).collect(Collectors.toList());
		}
	}
	
	private AllCustomTransaction mapToCusomDataList(Object[] transaction) {
		return new AllCustomTransaction(transaction[0], transaction[1], transaction[2], transaction[3], transaction[4],
				transaction[5], transaction[6], transaction[7], transaction[8], transaction[9], transaction[10],
				transaction[11], transaction[12], transaction[13], transaction[14], transaction[15], transaction[16],
				transaction[17], transaction[18],transaction[19],transaction[20]);
	}

	// All Account Statement Merchant wise
	public List<Object[]> AccountStatementMerchantWise(String merchantid, String Fromdate, String todate) {
		List<Object[]> response = outwardTransaction.findByMerchantTransactionsDateRange(merchantid, Fromdate, todate);
		if (response.isEmpty()) {
			// System.out.println("No transactions found for merchantid: " + merchantid);
			return Collections.emptyList();
		} else {
			return response;
		}
	}

	// All Account Statement Unit wise
	public List<Object[]> AccountStatementMerchantUnitWise(String merchantid, String Fromdate, String todate,
			String unitid) {
		List<Object[]> response = outwardTransaction.findByMerchantUnitTransactionsDateRange(merchantid, Fromdate,
				todate, unitid);
		if (response.isEmpty()) {
			// System.out.println("No transactions found for merchantid: " + merchantid +
			// "And UnitID: " + unitid);
			return Collections.emptyList();
		} else {
			return response;
		}
	}

	// All Account Statement user wise
	public List<Object[]> AccountStatementUserWise(String merchantid,String Fromdate, String todate, String unitid,
			String userId) {
		List<Object[]> response = outwardTransaction.findByUserTransactionsDateRange(merchantid, Fromdate, todate,
				unitid, userId);
		if (response.isEmpty()) {
			// System.out.println("No transactions found for merchantid: " + merchantid +
			// "And UnitID: " + unitid+ "And UserID: " + userId);
			return Collections.emptyList();
		} else {
			return response;
		}
	}

	// All Account State Device wise
	public List<Object[]> AccountStatementDeviceWise(String merchantid,String Fromdate, String todate, String unitid,
			String deviceId) {
		List<Object[]> response = outwardTransaction.findByDeviceTransactionsDateRange(merchantid, Fromdate, todate,
				unitid, deviceId);
		if (response.isEmpty()) {
			// System.out.println("No transactions found for merchantid: " + merchantid +
			// "And UnitID: " + unitid+ "And deviceId: " + deviceId);
			return Collections.emptyList();
		} else {
			return response;
		}
	}

	public List<BIPS_Alert_Entity> getAlertList() {
		List<BIPS_Alert_Entity> response = bIPS_Alert_Repo.getlst();
		// System.out.println("response------------------> " + response.toString());
		if (response.isEmpty()) {
			return Collections.emptyList();
		} else {
			return response;
		}
	}

	public List<BIPS_Notification_Entity> getNotificationList() {
		List<BIPS_Notification_Entity> response = bIPS_Notification_Repo.getlst();
		// System.out.println("response------------------> " + response.toString());
		if (response.isEmpty()) {
			return Collections.emptyList();
		} else {
			return response;
		}
	}

	public BIPS_Mer_Device_Management_Entity getAdminViewDevice(String device_id) {
		BIPS_Mer_Device_Management_Entity response = bIPS_MerDeviceManagement_Repo.getdevice(device_id);
		// System.out.println("response------------------> " + response.toString());
		return response;
	}

	public List<OutwardTransactionMonitoringTable> getAllTransactionsMerchantRep(String user_id, String Merchant_id) {
		List<OutwardTransactionMonitoringTable> response = outwardTransaction.findTransactionByIdRep(Merchant_id);
		// System.out.println("response------------------> " + response.toString());
		if (response.isEmpty()) {
			// System.out.println("No transactions found for user_id: " + user_id);
			return Collections.emptyList();
		} else {
			return response;
		}
	}
	
	@Autowired
	UserManagementRepository userManagementRepository; 
	
	public String getUniqueUserId(String merchant_id) {
		List<UserManagementEntity> response = userManagementRepository.getuserid1(merchant_id);
		if(response.size()>0) {
			String userId = response.get(0).getUser_id();
			String lastThreeChars = userId.substring(userId.length() - 3);
			int number = Integer.parseInt(lastThreeChars);
			number += 1;
			String incrementedNumber = String.format("%03d", number);
			String newUserId = userId.substring(0, userId.length() - 3) + incrementedNumber;

			System.out.println(newUserId);
			return newUserId;
		}else {
			String newUserId=merchant_id+"U001";
			return newUserId;
		}
	}
	
	@Autowired
	UnitManagementRepository unitManagementRepository;
	
	public String getUniqueUnitId(String merchant_id) {
		List<UnitManagementEntity> response = unitManagementRepository.getUniqueUnitlist(merchant_id);
		if(response.size()>0) {
			String unitId = response.get(0).getUnit_id();
			String lastThreeChars = unitId.substring(unitId.length() - 3);
			int number = Integer.parseInt(lastThreeChars);
			number += 1;
			String incrementedNumber = String.format("%03d", number);
			String newUnitId = unitId.substring(0, unitId.length() - 3) + incrementedNumber;

			System.out.println(newUnitId);
			return newUnitId;
		}else {
			String newUnitId="B001";
			return newUnitId;
		}
	}
	
	@Autowired
	DeviceManagementRepository deviceManagementRepository;
	
	public String getUniqueDeviceId(String merchant_id) {
		List<DeviceManagementEntity> response = deviceManagementRepository.getaddDevice(merchant_id);
		if(response.size()>0) {
			String deviceId = response.get(0).getDevice_id();
			String lastThreeChars = deviceId.substring(deviceId.length() - 3);
			int number = Integer.parseInt(lastThreeChars);
			number += 1;
			String incrementedNumber = String.format("%03d", number);
			String newDeviceId = deviceId.substring(0, deviceId.length() - 3) + incrementedNumber;

			System.out.println(newDeviceId);
			return newDeviceId;
		}else {
			String newDeviceId=merchant_id+"D001";
			return newDeviceId;
		}
	}
	
	public String getUniqueSrId() {
		List<ServiceReqEntity> response = servicereqrepository.desclist();
		if(response.size()>0) {
			String srId = response.get(0).getRequest_id();
			String lastThreeChars = srId.substring(srId.length() - 3);
			int number = Integer.parseInt(lastThreeChars);
			number += 1;
			String incrementedNumber = String.format("%03d", number);
			String newSrId = srId.substring(0, srId.length() - 3) + incrementedNumber;

			System.out.println(newSrId);
			return newSrId;
		}else {
			String newSrId="SR001";
			return newSrId;
		}
	}
	
	public String getUniqueNpId() {
		List<NotificationEntity> response = notificationrepo.descList();
		if(response.size()>0) {
			String npId = response.get(0).getRecord_srl_no();
			String lastThreeChars = npId.substring(npId.length() - 3);
			int number = Integer.parseInt(lastThreeChars);
			number += 1;
			String incrementedNumber = String.format("%03d", number);
			String newNpId = npId.substring(0, npId.length() - 3) + incrementedNumber;

			System.out.println(newNpId);
			return newNpId;
		}else {
			String newNpId="NP001";
			return newNpId;
		}
	}
}
