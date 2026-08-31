package com.bornfire.controller;

import java.math.BigDecimal;
import com.bornfire.entity.CustomerTransactionEntity;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bornfire.config.Encryption;
import com.bornfire.entity.AlertEntity;
import com.bornfire.entity.AlertRepo;
import com.bornfire.entity.AllCustomTransaction;
import com.bornfire.entity.BIPS_Alert_Entity;
import com.bornfire.entity.BIPS_Charge_Back_Entity;
import com.bornfire.entity.BIPS_Charge_Back_Rep;
import com.bornfire.entity.BIPS_Mer_Device_Management_Entity;
import com.bornfire.entity.BIPS_Notification_Entity;
import com.bornfire.entity.CustomerPayResponse;
import com.bornfire.entity.EncryptionEntity;
import com.bornfire.entity.FeesAndChargesEntity;
import com.bornfire.entity.MerchantMaster;
import com.bornfire.entity.MerchantMasterRep;
import com.bornfire.entity.NotificationEntity;
import com.bornfire.entity.OutwardTransactionMonitoringTable;
import com.bornfire.entity.OutwardTransactionMonitoringTableRep;
import com.bornfire.entity.ReferenceCodeRep;
import com.bornfire.entity.ServiceReqEntity;
import com.bornfire.service.AdminService;
import com.bornfire.service.OutwardTransactionService;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api")
@Tag(description = "Admin Module - API Information", name = "Admin Module - Rest Controller")
public class AdminModule {
	private static final Logger logger = LoggerFactory.getLogger(AdminModule.class);

	@Autowired
	AdminService service;

	@Autowired
	OutwardTransactionService OTService;

	ObjectMapper objectMapper = new ObjectMapper();

	@Autowired
	Encryption encryption;

	@Autowired
	OutwardTransactionMonitoringTableRep OTRepo;

	@Autowired
	BIPS_Charge_Back_Rep chargebackrepo;

	@Autowired
	AlertRepo repo;

	@Autowired
	MerchantMasterRep merchantmasterRep;

	@Autowired
	SessionFactory sessionFactory;

	@Autowired
	OutwardTransactionMonitoringTableRep outwardTransactionMonitoringTableRep;
	
	@GetMapping("/AlertListForAdmin")
	public List<AlertEntity> getAllAlert() {
		return repo.findAll();
	}

	// Creation/Addition of new User
	@PostMapping("/AddNotification")
	public String postNotificationData(@RequestBody EncryptionEntity EncryptedString,
			@RequestHeader(value = "PSU_Device_ID", required = true) String psuDeviceID) throws Exception {
		return service.postNotification(EncryptedString, psuDeviceID);
	}

	// Single Notification List
	@GetMapping("/AllNotificationList")
	public List<NotificationEntity> GetMerchantUnitAllUser(@RequestParam String merchant_id,
			@RequestParam String unit_id) {
		return service.getAllNotification(merchant_id, unit_id);
	}

	// Single All Customer Transaction list
	@GetMapping("/AllCustomerTransactionList")
	public List<AllCustomTransaction> getAllMerchantCustomerUnitDetails(@RequestParam String merchant_id,
			@RequestParam String unit_id, @RequestParam String fromdate,
			@RequestParam String todate, @RequestParam String type) {
		return OTService.getAllCustomerTransaction(merchant_id, unit_id, fromdate,todate,type);
	}
	
	// User and Device Combination Transaction list
	@GetMapping("/AllTransactionListHistory")
	public List<AllCustomTransaction> getTransalltransaction(@RequestParam String user_id, @RequestParam String merchant_id,@RequestParam String fromdate,
			@RequestParam String todate,@RequestParam String type) {
		return service.getallMerchantTransaction(user_id,merchant_id,fromdate,todate,type);
	}
	
	// Unit Wise Fees and Charges List
		@GetMapping("/AllFeesAndChargesList")
		public List<FeesAndChargesEntity> getAllFeesDetails(@RequestParam String merchant_id,
				@RequestParam String unit_id,@RequestParam String fromdate,
				@RequestParam String todate,@RequestParam String type) {
			return OTService.getAllFeesChargesTransactions(merchant_id, unit_id, fromdate,todate,type);
		}
		
		// Service Request List
		@GetMapping("/AllServiceRequestList")
		public List<ServiceReqEntity> gealltServiceDetails(@RequestParam String merchant_id,
				@RequestParam String unit_id) {
			//System.out.println("Bipsportal Controlerr----->"+merchant_id+unit_id);
			return service.getAllUnitservice(merchant_id, unit_id);
		}
		
		@GetMapping("/AllChargeBackList")
		public List<BIPS_Charge_Back_Entity> getAllChargeBacks(@RequestParam String merchant_id,
				@RequestParam String unit_id,@RequestParam String fromdate,
				@RequestParam String todate) {
			return chargebackrepo.getAllListchargeback(merchant_id, unit_id,fromdate,todate);
		}
		@GetMapping("/AllMerchantPendingChargeBack")
		public List<BIPS_Charge_Back_Entity> getAllPendingChargeBacks(@RequestParam String merchant_id,
				@RequestParam String unit_id,@RequestParam String fromdate,
				@RequestParam String todate) {
			return chargebackrepo.getAllPendingTransaction(merchant_id, unit_id,fromdate,todate);
		}
		@GetMapping("/AllMerchantRevertedChargeBack")
		public List<BIPS_Charge_Back_Entity> getAllRevertedChargeBacks(@RequestParam String merchant_id,
				@RequestParam String unit_id,@RequestParam String fromdate,
				@RequestParam String todate) {
			return chargebackrepo.getAllRevertedTransaction(merchant_id, unit_id,fromdate,todate);
		}


	@GetMapping("/NotificationListforMerchant")
	public List<NotificationEntity> getAllNotification(@RequestParam String merchant_id) {
		return service.getAllNotification(merchant_id);
	}

	@GetMapping("/NotificationListforUnit")
	public List<NotificationEntity> getUnitNotification(@RequestParam String merchant_id,
			@RequestParam String unit_id) {
		return service.getUnitNotification(merchant_id, unit_id);
	}

	@GetMapping("/NotificationSequence")
	public String NotificationSequence() {
		Session hs = sessionFactory.getCurrentSession();

		BigDecimal srlno = (BigDecimal) hs.createNativeQuery("SELECT NOTIFI_SRL_SEQUENCE.NEXTVAL AS SRL_NO FROM DUAL")
				.getSingleResult();
		return srlno.toString();
	}

	@GetMapping("/ChargeBackList")
	public List<BIPS_Charge_Back_Entity> getAllChargeBacksDetails(@RequestParam String merchant_id) {
		return chargebackrepo.getAllListMerchant(merchant_id);
	}
	
	@GetMapping("/ChargeBackListPending")
	public List<BIPS_Charge_Back_Entity> getAllChargeBacksDetailRevert(@RequestParam String merchant_id) {
		return chargebackrepo.getAllListMerPending(merchant_id);
	}
	
	@GetMapping("/ChargeBackListRevert")
	public List<BIPS_Charge_Back_Entity> getAllChargeBacksDetailsPending(@RequestParam String merchant_id) {
		return chargebackrepo.getAllListMerRevert(merchant_id);
	}

	// Unit-wise Charge Back List
	@GetMapping("/UnitChargeBackList")
	public List<BIPS_Charge_Back_Entity> getAllUnitChargeBacksDetails(@RequestParam String merchant_id,
			@RequestParam String unit_id) {
		return chargebackrepo.getAllListUnit(merchant_id, unit_id);
	}
	
	@GetMapping("/UnitChargeBackListRevert")
	public List<BIPS_Charge_Back_Entity> getAllUnitChargeBacksDetailsRevert(@RequestParam String merchant_id,
			@RequestParam String unit_id) {
		return chargebackrepo.getAllListUnitRevert(merchant_id, unit_id);
	}
	
	@GetMapping("/UnitChargeBackListPending")
	public List<BIPS_Charge_Back_Entity> getAllUnitChargeBacksDetailsPending(@RequestParam String merchant_id,
			@RequestParam String unit_id) {
		return chargebackrepo.getAllListUnitPending(merchant_id, unit_id);
	}

	// All pending chargebacklist
	@GetMapping("/AllPendingChargeBack")
	public List<BIPS_Charge_Back_Entity> getAllPendingChargeBacksDetails(@RequestParam String merchant_id) {
		return chargebackrepo.getPendingTransactionMerchantList(merchant_id);
	}

	// Unit pending chargebacklist
	@GetMapping("/UnitPendingChargeBack")
	public List<BIPS_Charge_Back_Entity> getUnitPendingChargeBacksDetails(@RequestParam String merchant_id,
			@RequestParam String unit_id) {
		return chargebackrepo.getPendingTransactionUnitList(merchant_id, unit_id);
	}

	@GetMapping("/AllRevertedChargeBack")
	public List<BIPS_Charge_Back_Entity> getAllRevertedChargeBacksDetails(@RequestParam String merchant_id) {
		return chargebackrepo.getRevertedTransactionMerchantList(merchant_id);
	}

	@GetMapping("/UnitRevertedChargeBack")
	public List<BIPS_Charge_Back_Entity> getUnitRevertedChargeBacksDetails(@RequestParam String merchant_id,
			@RequestParam String unit_id) {
		return chargebackrepo.getRevertedTransactionUnitList(merchant_id, unit_id);
	}

	// Initiated Charge Backs
	@RequestMapping(value = "InititedChargeBack", method = { RequestMethod.POST })
	public String UpdateChargeBack(@RequestParam String userid, @RequestParam String seqUniqueID,
			@RequestParam String merchant_id) throws Exception {
		try {
			OutwardTransactionMonitoringTable existingUser = OTRepo.findByMerchantUserId(seqUniqueID);
			MerchantMaster merchantdata = merchantmasterRep.getMerlst(merchant_id);
			if (Objects.nonNull(existingUser)) {
				BIPS_Charge_Back_Entity chargeBackEntity = new BIPS_Charge_Back_Entity(existingUser);
				chargeBackEntity.setReversal_remarks("PENDING");
				chargeBackEntity.setEntry_user(userid);
				chargeBackEntity.setEntry_time(new Date());
				chargeBackEntity.setEntity_cre_flg("Y");
				chargeBackEntity.setRevert_status_flg("N");
				existingUser.setReversal_remarks("INITIATED");
				if (Objects.nonNull(merchantdata.getChargeback_approval())
						&& merchantdata.getChargeback_approval().equals("MERCHANT")) {
					if (!merchantdata.getChargeback_amount().equals(BigDecimal.ZERO)) {
						chargeBackEntity.setChargeback_amount(merchantdata.getChargeback_amount());
						if (chargeBackEntity.getTran_amount().compareTo(merchantdata.getChargeback_amount()) > 0) {
							// tranAmount is greater than chargebackAmount
							chargeBackEntity.setChargeback_approval("BANK");
						} else if (chargeBackEntity.getTran_amount()
								.compareTo(merchantdata.getChargeback_amount()) < 0) {
							// tranAmount is less than chargebackAmount
							chargeBackEntity.setChargeback_approval("MERCHANT");
						} else {
							// tranAmount is equal to chargebackAmount
							chargeBackEntity.setChargeback_approval("MERCHANT");
						}
					} else {
						chargeBackEntity.setChargeback_approval("BANK");
					}

				} else {
					chargeBackEntity.setChargeback_approval("BANK");

				}

				chargebackrepo.save(chargeBackEntity);
				OTRepo.save(existingUser);
				return "Charges Inititated Successfully.";
			} else {
				return "Charges Inititated Failed.";
			}

		} catch (Exception e) {
			System.out.print("Exception------->" + e.getMessage());
			logger.debug("Update Charge Back ERROR------>" + e.getMessage());
			return e.getMessage();
		}
	}

	@GetMapping("/CustomerTransactionView")
	public OutwardTransactionMonitoringTable CustomerTransactionView(@RequestParam String message_ref) {
		return OTService.getCustomerTransactionView(message_ref);
	}

	// User All Transaction list
	@GetMapping("/TransactionList")
	public List<OutwardTransactionMonitoringTable> getAllTransactions(@RequestParam String user_id,
			@RequestParam String Merchant_id) {
		return service.getAllTransactions(user_id, Merchant_id);
	}

	// User and Device Combination Transaction list
	@GetMapping("/TransactionListForUser")
	public List<OutwardTransactionMonitoringTable> getTransForUser(@RequestParam String user_id,
			@RequestParam String Device_id, @RequestParam String Merchant_id) {
		return service.getUserTransaction(user_id, Device_id, Merchant_id);
	}

	@GetMapping("/getCustomerPaydetails")
	public String getCustomerPayDetails(@RequestParam String merchant_id, @RequestParam String device_id,
			@RequestParam String reference_number,
			@RequestHeader(value = "PSU_Device_ID", required = true) String psuDeviceID) throws Exception {
		CustomerPayResponse response = OTService.getCustomerPayDetails(merchant_id, device_id, reference_number);
		//System.out.println("Cust Response " + response);
		String jsonUserData = objectMapper.writeValueAsString(response);
		String encryptedData = encryption.encrypt(jsonUserData, psuDeviceID);
		//System.out.println("Encrypted data: " + encryptedData);
		String decryptedData = encryption.decrypt(encryptedData, psuDeviceID);
		//System.out.println("Decrypted data: " + decryptedData);
		return encryptedData;
	}


	// Service Request List
	@GetMapping("/ServiceRequestAllList")
	public List<ServiceReqEntity> getAllServiceDetails(@RequestParam String merchant_id) {
		return service.getAllservice(merchant_id);
	}

	// Service Request List
	@GetMapping("/ServiceRequestUnitList")
	public List<ServiceReqEntity> getunitServiceDetails(@RequestParam String merchant_id,
			@RequestParam String unit_id) {
		return service.getUnitservice(merchant_id, unit_id);
	}

	// Add Service Request
	@PostMapping("/AddServiceReq")
	public String postServiceData(@RequestBody EncryptionEntity EncryptedString,
			@RequestHeader(value = "PSU_Device_ID", required = true) String psuDeviceID) throws Exception {
		return service.postService(EncryptedString, psuDeviceID);
	}

	// Account Statement Merchant wise
	@GetMapping("/AccountStatementMerchantWise")
	public List<Object[]> AccountStatementMerchant(@RequestParam String merchant_id,
			@RequestParam String fromdate,
			@RequestParam String  todate) {
		return service.AccountStatementMerchantWise(merchant_id, fromdate, todate);
	}

	// Account Statement Merchant Unit wise
	@GetMapping("/AccountStatementMerchantUnitWise")
	public List<Object[]> AccountStatementMerchantUnit(@RequestParam String merchant_id,
			@RequestParam String  fromdate,
			@RequestParam String todate, @RequestParam String unit_id) {
		return service.AccountStatementMerchantUnitWise(merchant_id, fromdate, todate, unit_id);
	}

	// Account Statement User wise
	@GetMapping("/AccountStatementUserWise")
	public List<Object[]> AccountStatementUserWise(@RequestParam String merchant_id,
			@RequestParam String fromdate,
			@RequestParam String todate, @RequestParam String unit_id,
			@RequestParam String user_id) {
		return service.AccountStatementUserWise(merchant_id, fromdate, todate, unit_id, user_id);
	}

	// Account Statement User wise
	@GetMapping("/AccountStatementDeviceWise")
	public List<Object[]> AccountStatementDeviceWise(@RequestParam String merchant_id,
			@RequestParam String fromdate,
			@RequestParam String todate, @RequestParam String unit_id,
			@RequestParam String device_id) {
		return service.AccountStatementDeviceWise(merchant_id, fromdate, todate, unit_id, device_id);
	}

	@GetMapping("/FeesAndChargeForOne")
	public OutwardTransactionMonitoringTable FeesAndChargeForOne(@RequestParam String message_ref) {
		return OTService.getFeesChargeForOne(message_ref);
	}

	@GetMapping("/AlertList")
	public List<BIPS_Alert_Entity> AlertList() {
		return service.getAlertList();
	}

	@GetMapping("/NotificationList")
	public List<BIPS_Notification_Entity> NotificationList() {
		return service.getNotificationList();
	}

	@GetMapping("/AdminViewDevice")
	public BIPS_Mer_Device_Management_Entity AdminViewDevice(@RequestParam String device_id) {
		return service.getAdminViewDevice(device_id);
	}

	@Autowired
	ReferenceCodeRep referenceCodeRep;

	@GetMapping("/referenceMasterForDropdown")
	public List<String> referenceMasterForDropdown(@RequestParam String ref_type) {
		return referenceCodeRep.getReferenceList(ref_type);
	}

	@GetMapping("/TransactionListMerchantRep")
	public List<OutwardTransactionMonitoringTable> TransactionListMerchantRep(@RequestParam String user_id,
			@RequestParam String Merchant_id) {
		return service.getAllTransactionsMerchantRep(user_id, Merchant_id);
	}
	
	@GetMapping("/ChargebackForOne")
	public BIPS_Charge_Back_Entity ChargebackForOne(@RequestParam String message_ref) {
		return OTService.getChargeBackForOne(message_ref);
	}
	
	@GetMapping("/TransactionForOne")
	public OutwardTransactionMonitoringTable TransactionForOne(@RequestParam String message_ref) {
		return OTService.getTransactionForOne(message_ref);
	}
	
	//For Web --- Customer Txns List For Single Date
	@GetMapping("/CustomerTransactionListSingleDate")
	public List<OutwardTransactionMonitoringTable> CustomerTransactionListSingleDate(
	        @RequestParam String merchant_id,
	        @RequestParam String Date, 
	        @RequestParam(required = false) String unit_id) {
	    
	    System.out.println("Date: " + Date);
	    System.out.println("Unit ID: " + unit_id);
	    
	    if (Objects.nonNull(unit_id) && !unit_id.equalsIgnoreCase("null")) {
	        return outwardTransactionMonitoringTableRep.findAllTransactionsInSingleDate(merchant_id, Date, unit_id);
	    } else {
	        System.out.println("Entered here");
	        return outwardTransactionMonitoringTableRep.findAllTransactionsInSingleDate(merchant_id, Date);
	    }    
	}

		
		@GetMapping("/UserTransactionListSingleDate")
		public List<OutwardTransactionMonitoringTable> UserTransactionListSingleDate(@RequestParam String user_id,
				@RequestParam String Date) {
			System.out.println(user_id+Date);
			System.out.println(outwardTransactionMonitoringTableRep.userTransactionsInSingleDate(user_id, Date));
				return outwardTransactionMonitoringTableRep.userTransactionsInSingleDate(user_id, Date);
		}
		//For Web ---Fees & Charges List For Single Date
				@GetMapping("/FeesAndChargesListSingleDate")
				public List<OutwardTransactionMonitoringTable> FeesAndChargesListSingleDate(@RequestParam String merchant_id,
						@RequestParam String Date,@RequestParam String unit_id) {
					if(Objects.nonNull(unit_id) && !unit_id.equalsIgnoreCase("null")) {
						return outwardTransactionMonitoringTableRep.findAllTransactionsInSingleDate(merchant_id, Date,unit_id);
					}else {
						return outwardTransactionMonitoringTableRep.findAllTransactionsInSingleDate(merchant_id, Date);
					}	
				}
				
				@GetMapping("/ChargeBackListSingleDate")
				public List<BIPS_Charge_Back_Entity> chargeBackListSingleDate(
				        @RequestParam String merchant_id,
				        @RequestParam String date,  // Assuming the format here is consistent
				        @RequestParam String unit_id,
				        @RequestParam String remarks) {
				    
				    if (Objects.nonNull(unit_id) && !unit_id.equalsIgnoreCase("null")) {
				        if ("ALL".equals(remarks) && !remarks.equalsIgnoreCase("null")) {
				            return chargebackrepo.findAllUnitTansactionBetweenDates(merchant_id, unit_id, date, date);
				        } else {
				            return chargebackrepo.findAllUnitTansactionBetweenDates(merchant_id, remarks, unit_id, date,date);
				        }
				    } else {
				        if ("ALL".equals(remarks) && !remarks.equalsIgnoreCase("null")) {
				            return chargebackrepo.findAllTansactionBetweenDates(merchant_id, date, date);
				        } else {
				            return chargebackrepo.findAllTansactionBetweenDates(merchant_id, remarks, date, date);
				        }
				    }
				}
				
				
				@GetMapping("/InitiateSingleDate")
				public List<OutwardTransactionMonitoringTable> InitiateSingleDate(
				        @RequestParam String merchant_id,
				        @RequestParam String date,  // Assuming the format here is consistent
				        @RequestParam String unit_id,
				        @RequestParam String remarks) {
				    
				    if (Objects.nonNull(unit_id) && !unit_id.equalsIgnoreCase("null")) {
				       return outwardTransactionMonitoringTableRep.findAllUnitTansactionBetweenDates(merchant_id, unit_id, date, date);
				    } else {
				       return outwardTransactionMonitoringTableRep.findAllTansactionBetweenDates(merchant_id, date, date);
				    }
				}

				
	@GetMapping("/uniqueUserId")
    public String uniqueUserId(@RequestParam String merchant_id) {
		return service.getUniqueUserId(merchant_id);
	}
	
	@GetMapping("/uniqueUnitId")
    public String uniqueUnitId(@RequestParam String merchant_id) {
		return service.getUniqueUnitId(merchant_id);
	}
	
	@GetMapping("/uniqueDeviceId")
    public String uniqueDeviceId(@RequestParam String merchant_id) {
		return service.getUniqueDeviceId(merchant_id);
	}
	
	@GetMapping("/ServiceRequestId")
    public String ServiceRequestId() {
		return service.getUniqueSrId();
	}
	
	@GetMapping("/NotifiParamId")
    public String NotifiParamId() {
		return service.getUniqueNpId();
	}

	@GetMapping("/CustomerTransactionListForUser")
	public List<CustomerTransactionEntity> CustomerTransactionListForUser(@RequestParam String user_id,
			@RequestParam String merchant_id) {
		return service.getCustomerTransactionList(user_id, merchant_id);
	}
}
