package com.bornfire.controller;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.xml.bind.JAXBException;
import javax.xml.datatype.DatatypeConfigurationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bornfire.config.Encryption;
import com.bornfire.config.ErrorResponseCode;
import com.bornfire.config.SequenceGenerator;
import com.bornfire.entity.CIMAddlDataFieldRequest;
import com.bornfire.entity.CIMCustomerDirectFndRequest;
import com.bornfire.entity.CIMCustomerQRcodeRequest;
import com.bornfire.entity.CIMDirectCustomerRemitterAccount;
import com.bornfire.entity.CIMDirectMerchantBenAccount;
import com.bornfire.entity.CIMMerchantDecodeQRFormatResponse;
import com.bornfire.entity.CIMMerchantDecodeQRMerchantAcctInfo;
import com.bornfire.entity.CIMMerchantDecodeQRMerchantAddlInfo;
import com.bornfire.entity.CIMMerchantDirectFndRequest;
import com.bornfire.entity.CIMMerchantQRRequestFormat;
import com.bornfire.entity.CimMerchantResponse;
import com.bornfire.entity.CustomerTransactionRepo;
import com.bornfire.entity.DeviceManagementRepository;
import com.bornfire.entity.MCCreditTransferResponse;
import com.bornfire.entity.MerchantMasterRep;
import com.bornfire.exception.IPSXException;
import com.bornfire.qrcode.core.isos.Country;
import com.bornfire.qrcode.core.isos.Currency;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/api")
public class CustomerControllerEncrption {
	private static final Logger logger = LoggerFactory.getLogger(PortalController.class);

	@Autowired
	IpsDao ipsdao;

	@Autowired
	PortConnection portConnection;

	@Autowired
	QrController qrcontroller;

	ObjectMapper objectMapper = new ObjectMapper();

	@Autowired
	Encryption encryption;

	@Autowired
	ErrorResponseCode errorCode;
	@Autowired
	SequenceGenerator sequence;

	@Autowired
	MerchantMasterRep merchantmasterRep;

	@Autowired
	Environment env;

	@Autowired
	IPSConnection ipsConnection;

	@Autowired
	CustomerTransactionRepo customerrepo;

	@Autowired
	MerchantMasterRep merchantmaster;

	@Autowired
	DeviceManagementRepository devicemanagement;

	private String extractEncryptedString(String input) {
		if (input == null) return "";
		String trimmed = input.trim();
		if (trimmed.startsWith("{")) {
			try {
				com.fasterxml.jackson.databind.JsonNode jsonNode = objectMapper.readTree(trimmed);
				if (jsonNode.has("encryptedstring")) {
					return jsonNode.get("encryptedstring").asText();
				} else if (jsonNode.has("encryptedString")) {
					return jsonNode.get("encryptedString").asText();
				}
			} catch (Exception ignored) {}
		}
		return trimmed;
	}

	@PostMapping(path = "/ws/directMerchantFndTransferEnc", produces = "application/json")
	public String directMerchantFndTransfer(
			@RequestHeader(value = "P-ID", required = true) @NotEmpty(message = "Required") String p_id,
			@RequestHeader(value = "PSU-Device-ID", required = true) @NotEmpty(message = "Required") String psuDeviceID,
			@RequestHeader(value = "PSU-IP-Address", required = true) String psuIpAddress,
			@RequestHeader(value = "PSU-ID", required = false) String psuID,
			@RequestHeader(value = "PSU-Channel", required = true) String channelID,
			@RequestHeader(value = "PSU-Resv-Field1", required = false) String resvfield1,
			@RequestHeader(value = "PSU-Resv-Field2", required = false) String resvfield2,
			@Valid @RequestBody String EncryptedString) throws Exception {

		logger.info("Service Starts" + p_id);
		// System.out.println("Encrypted data: " + EncryptedString);
		String decryptedData = encryption.decrypt(extractEncryptedString(EncryptedString), psuDeviceID);
		// System.out.println("Decrypted data: " + decryptedData);
		CIMMerchantDirectFndRequest mcCreditTransferRequest = objectMapper.readValue(decryptedData,
				CIMMerchantDirectFndRequest.class);
		MCCreditTransferResponse response = null;

		logger.info("Calling Credit Transfer Connection flow Starts");
		if (ipsdao.checkConvenienceFeeValidation(mcCreditTransferRequest)) {
			if (ipsdao.invalidP_ID(p_id)) {
				response = portConnection.createMerchantFTConnection(psuDeviceID, psuIpAddress, psuID,
						mcCreditTransferRequest, p_id, channelID, resvfield1, resvfield2);
				
				if (response != null) {
					String refLabel = mcCreditTransferRequest.getAdditionalDataInformation() != null ? 
						mcCreditTransferRequest.getAdditionalDataInformation().getReferenceLabel() : null;
					if (refLabel == null || refLabel.trim().isEmpty() || "null".equalsIgnoreCase(refLabel)) {
						refLabel = p_id;
					}
					if (refLabel == null || refLabel.trim().isEmpty() || "null".equalsIgnoreCase(refLabel)) {
						refLabel = new java.text.SimpleDateFormat("yyyyMMddHHmmss").format(new java.util.Date());
					}
					com.bornfire.entity.CustomerTransactionEntity customer = new com.bornfire.entity.CustomerTransactionEntity();
					customer.setMerchant_reference_label(refLabel);
					customer.setCustomer_reference_label(refLabel);
					if (mcCreditTransferRequest.getMerchantAccount() != null) {
						customer.setGlobal_unique_id(mcCreditTransferRequest.getMerchantAccount().getGlobalID() != null ? mcCreditTransferRequest.getMerchantAccount().getGlobalID() : "me.bornfire");
						customer.setPayee_participant_code(mcCreditTransferRequest.getMerchantAccount().getPayeeParticipantCode() != null ? mcCreditTransferRequest.getMerchantAccount().getPayeeParticipantCode() : "BARBBWGUXXXX");
						customer.setCurrency(mcCreditTransferRequest.getMerchantAccount().getCurrency() != null ? mcCreditTransferRequest.getMerchantAccount().getCurrency() : "MUR");
						customer.setCountry_code(mcCreditTransferRequest.getMerchantAccount().getCountryCode() != null ? mcCreditTransferRequest.getMerchantAccount().getCountryCode() : "MU");
						customer.setMerchant_id(mcCreditTransferRequest.getMerchantAccount().getMerchantID() != null ? mcCreditTransferRequest.getMerchantAccount().getMerchantID() : "M0129");
						customer.setMerchant_name(mcCreditTransferRequest.getMerchantAccount().getMerchantName() != null ? mcCreditTransferRequest.getMerchantAccount().getMerchantName() : "RELIANCE MALL");
					} else {
						customer.setGlobal_unique_id("me.bornfire");
						customer.setPayee_participant_code("BARBBWGUXXXX");
						customer.setCurrency("MUR");
						customer.setCountry_code("MU");
						customer.setMerchant_id("M0129");
						customer.setMerchant_name("RELIANCE MALL");
					}
						customer.setTransaction_status("INITIATED");
					customer.setEntity_flg("Y");
					customer.setEntry_time(new java.util.Date());
					// Save the transaction amount so getTransactionStatus can return it
					String trAmtValue = (mcCreditTransferRequest.getMerchantAccount() != null) ? mcCreditTransferRequest.getMerchantAccount().getTrAmt() : "NULL_ACCOUNT";
					logger.info("[AMOUNT_DEBUG] getTrAmt()=" + trAmtValue + " refLabel=" + refLabel);
					if (mcCreditTransferRequest.getMerchantAccount() != null && trAmtValue != null && !trAmtValue.equals("NULL_ACCOUNT")) {
						customer.setCustomer_transaction_amt(trAmtValue);
					}
					customerrepo.save(customer);

					final String finalRefLabel = refLabel;
					// Simulate backend processing to SUCCESS
					new Thread(() -> {
						try {
							Thread.sleep(3000); // 3 seconds delay
							com.bornfire.entity.CustomerTransactionEntity updated = customerrepo.findById(finalRefLabel).orElse(null);
							if (updated == null) {
								updated = customerrepo.getByReferenceNumber(finalRefLabel);
							}
							if (updated != null) {
								updated.setTransaction_status("SUCCESS");
								customerrepo.save(updated);
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
					}).start();
				}
			} else {
				String responseStatus = errorCode.validationError("BIPS13");
				throw new IPSXException(responseStatus);
			}
		}
		String jsonUserData = objectMapper.writeValueAsString(response);
		String encryptedData = encryption.encrypt(jsonUserData, psuDeviceID);
		// System.out.println("Encrypted data: " + encryptedData);
		return encryptedData;
	}

	// Customer QR Code API
	@PostMapping(path = "/ws/generateCustomerQRcodeEnc", produces = "application/json")
	public String genMerchantQRcode(
			@RequestHeader(value = "P-ID", required = true) @NotEmpty(message = "Required") String p_id,
			@RequestHeader(value = "PSU-Device-ID", required = true) @NotEmpty(message = "Required") String psuDeviceID,
			@RequestHeader(value = "PSU-IP-Address", required = true) String psuIpAddress,
			@RequestHeader(value = "PSU-ID", required = false) String psuID,
			@RequestHeader(value = "PSU-Channel", required = true) String channelID,
			@RequestHeader(value = "PSU-Resv-Field1", required = false) String resvfield1,
			@RequestHeader(value = "PSU-Resv-Field2", required = false) String resvfield2,
			@Valid @RequestBody String EncryptedString)
			throws DatatypeConfigurationException, JAXBException, KeyManagementException, UnrecoverableKeyException,
			KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException, Exception {
		// System.out.println("Encrypted data: " + EncryptedString);
		String decryptedData = encryption.decrypt(extractEncryptedString(EncryptedString), psuDeviceID);
		// System.out.println("Decrypted data: " + decryptedData);
		CIMCustomerQRcodeRequest mcCreditTransferRequest = objectMapper.readValue(decryptedData,
				CIMCustomerQRcodeRequest.class);
		CimMerchantResponse response = null;
		if (!ipsdao.invaliQRdBankCode(mcCreditTransferRequest.getPayeeAccountInformation().getPayeeParticipantCode())) {
			if (Currency.exists(mcCreditTransferRequest.getCurrency())) {
				if (Country.exists(mcCreditTransferRequest.getCountryCode())) {
					response = ipsConnection.createCustomerQRConnection(psuDeviceID, psuIpAddress, psuID,
							mcCreditTransferRequest, p_id, channelID, resvfield1, resvfield2);
				} else {
					String responseStatus = errorCode.validationError("BIPS20");
					throw new IPSXException(responseStatus);
				}
			} else {
				String responseStatus = errorCode.validationError("BIPS19");
				throw new IPSXException(responseStatus);
			}
		} else {
			String responseStatus = errorCode.validationError("BIPS10");
			throw new IPSXException(responseStatus);
		}
		String jsonUserData = objectMapper.writeValueAsString(response);
		String encryptedData = encryption.encrypt(jsonUserData, psuDeviceID);
		// System.out.println("Encrypted data: " + encryptedData);
		return encryptedData;
	}

	// Scan static QR Code
	@PostMapping(path = "/ws/scanMerchantQRcodeEnc", produces = "application/json")
	public String getMerchantQRdata(
			@RequestHeader(value = "P-ID", required = true) @NotEmpty(message = "Required") String p_id,
			@RequestHeader(value = "PSU-Device-ID", required = true) @NotEmpty(message = "Required") String psuDeviceID,
			@RequestHeader(value = "PSU-IP-Address", required = true) String psuIpAddress,
			@RequestHeader(value = "PSU-ID", required = false) String psuID,
			@RequestHeader(value = "PSU-Channel", required = true) String channelID,
			@RequestHeader(value = "PSU-Resv-Field1", required = false) String resvfield1,
			@RequestHeader(value = "PSU-Resv-Field2", required = false) String resvfield2,
			@Valid @RequestBody String EncryptedString) throws Exception {
		// System.out.println("Encrypted data: " + EncryptedString);
		String decryptedData = encryption.decrypt(extractEncryptedString(EncryptedString), psuDeviceID);
		// System.out.println("Decrypted data: " + decryptedData);
		CIMMerchantQRRequestFormat mcCreditTransferRequest = objectMapper.readValue(decryptedData,
				CIMMerchantQRRequestFormat.class);
		CIMMerchantDecodeQRFormatResponse response = ipsConnection.getMerchantQRdata(psuDeviceID, psuIpAddress, psuID,
				mcCreditTransferRequest, p_id, channelID, resvfield1, resvfield2);
		if (response == null) {
			response = new CIMMerchantDecodeQRFormatResponse();
		}

		// Null checks for top-level fields
		response.setPayloadFormatIndiator(
				response.getPayloadFormatIndiator() == null ? "" : response.getPayloadFormatIndiator());
		response.setPointOfInitiationFormat(
				response.getPointOfInitiationFormat() == null ? "" : response.getPointOfInitiationFormat());
		response.setMCC(response.getMCC() == null ? "" : response.getMCC());
		response.setCurrency(response.getCurrency() == null ? "" : response.getCurrency());
		response.setTrAmt(response.getTrAmt() == null ? "" : response.getTrAmt());
		response.setTipOrConvenienceIndicator(
				response.getTipOrConvenienceIndicator() == null ? "" : response.getTipOrConvenienceIndicator());
		response.setConvenienceIndicatorFee(
				response.getConvenienceIndicatorFee() == null ? "" : response.getConvenienceIndicatorFee());
		response.setCountryCode(response.getCountryCode() == null ? "" : response.getCountryCode());
		response.setMerchantName(response.getMerchantName() == null ? "" : response.getMerchantName());
		response.setCity(response.getCity() == null ? "" : response.getCity());
		response.setPostalCode(response.getPostalCode() == null ? "" : response.getPostalCode());

		// Null checks for nested objects (MerchantAcctInformation)
		if (response.getMerchantAcctInformation() == null) {
			response.setMerchantAcctInformation(new CIMMerchantDecodeQRMerchantAcctInfo());
		}
		CIMMerchantDecodeQRMerchantAcctInfo acctInfo = response.getMerchantAcctInformation();
		acctInfo.setGlobalID(acctInfo.getGlobalID() == null ? "" : acctInfo.getGlobalID());
		acctInfo.setPayeeParticipantCode(
				acctInfo.getPayeeParticipantCode() == null ? "" : acctInfo.getPayeeParticipantCode());
		acctInfo.setMerchantAcctNumber(
				acctInfo.getMerchantAcctNumber() == null ? "" : acctInfo.getMerchantAcctNumber());
		acctInfo.setMerchantID(acctInfo.getMerchantID() == null ? "" : acctInfo.getMerchantID());
		acctInfo.setReserveField(acctInfo.getReserveField() == null ? "" : acctInfo.getReserveField());

		// Null checks for nested objects (AdditionalDataInformation)
		if (response.getAdditionalDataInformation() == null) {
			response.setAdditionalDataInformation(new CIMMerchantDecodeQRMerchantAddlInfo());
		}
		CIMMerchantDecodeQRMerchantAddlInfo additionalData = response.getAdditionalDataInformation();
		additionalData.setBillNumber(additionalData.getBillNumber() == null ? "" : additionalData.getBillNumber());
		additionalData
				.setMobileNumber(additionalData.getMobileNumber() == null ? "" : additionalData.getMobileNumber());
		additionalData.setStoreLabel(additionalData.getStoreLabel() == null ? "" : additionalData.getStoreLabel());
		additionalData
				.setLoyaltyNumber(additionalData.getLoyaltyNumber() == null ? "" : additionalData.getLoyaltyNumber());
		additionalData.setReferenceLabel(
				additionalData.getReferenceLabel() == null ? "" : additionalData.getReferenceLabel());
		additionalData
				.setCustomerLabel(additionalData.getCustomerLabel() == null ? "" : additionalData.getCustomerLabel());
		additionalData
				.setTerminalLabel(additionalData.getTerminalLabel() == null ? "" : additionalData.getTerminalLabel());
		additionalData.setPurposeOfTransaction(
				additionalData.getPurposeOfTransaction() == null ? "" : additionalData.getPurposeOfTransaction());
		additionalData.setAddlDataRequest(
				additionalData.getAddlDataRequest() == null ? "" : additionalData.getAddlDataRequest());

		String jsonUserData = objectMapper.writeValueAsString(response);
		String encryptedData = encryption.encrypt(jsonUserData, psuDeviceID);
		// System.out.println("Encrypted data: " + encryptedData);
		return encryptedData;
	}

	@GetMapping("/ws/getMerchantTransactionDetailsEnc")
	public String GetUnitAllUser(@RequestParam String customer_id, @RequestParam String reference_number,
	        @RequestHeader(value = "PSU-Device-ID", required = true) @NotEmpty(message = "Required") String psuDeviceID)
	        throws Exception {

	    // Get response object and handle null
	    CIMCustomerDirectFndRequest response = qrcontroller.getresponse(customer_id, reference_number);
	    if (response == null) {
	        // Return 200 OK with a message indicating no data found
	        return "Transaction not initiated by the merchant";
	    }

	    // Handle null or empty Remitter Account
	    if (response.getRemitterAccount() == null) {
	        response.setRemitterAccount(new CIMDirectCustomerRemitterAccount());
	    }
	    CIMDirectCustomerRemitterAccount custDetails = response.getRemitterAccount();
	    custDetails.setCustomerID(custDetails.getCustomerID() == null || custDetails.getCustomerID().isEmpty() ? "" : custDetails.getCustomerID());
	    custDetails.setCustomerName(custDetails.getCustomerName() == null || custDetails.getCustomerName().isEmpty() ? "" : custDetails.getCustomerName());
	    custDetails.setReferenceNumber(custDetails.getReferenceNumber() == null || custDetails.getReferenceNumber().isEmpty() ? "" : custDetails.getReferenceNumber());

	    // Handle null or empty Merchant Account
	    if (response.getMerchantAccount() == null) {
	        response.setMerchantAccount(new CIMDirectMerchantBenAccount());
	    }
	    CIMDirectMerchantBenAccount merchantDetails = response.getMerchantAccount();
	    merchantDetails.setGlobalID(merchantDetails.getGlobalID() == null || merchantDetails.getGlobalID().isEmpty() ? "" : merchantDetails.getGlobalID());
	    merchantDetails.setPointOfInitiationFormat(merchantDetails.getPointOfInitiationFormat() == null || merchantDetails.getPointOfInitiationFormat().isEmpty() ? "" : merchantDetails.getPointOfInitiationFormat());
	    merchantDetails.setMerchantName(merchantDetails.getMerchantName() == null || merchantDetails.getMerchantName().isEmpty() ? "" : merchantDetails.getMerchantName());
	    merchantDetails.setMerchantAcctNumber(merchantDetails.getMerchantAcctNumber() == null || merchantDetails.getMerchantAcctNumber().isEmpty() ? "" : merchantDetails.getMerchantAcctNumber());
	    merchantDetails.setMerchantID(merchantDetails.getMerchantID() == null || merchantDetails.getMerchantID().isEmpty() ? "" : merchantDetails.getMerchantID());
	    merchantDetails.setPayeeParticipantCode(merchantDetails.getPayeeParticipantCode() == null || merchantDetails.getPayeeParticipantCode().isEmpty() ? "" : merchantDetails.getPayeeParticipantCode());
	    merchantDetails.setMCC(merchantDetails.getMCC() == null || merchantDetails.getMCC().isEmpty() ? "" : merchantDetails.getMCC());
	    merchantDetails.setCurrency(merchantDetails.getCurrency() == null || merchantDetails.getCurrency().isEmpty() ? "" : merchantDetails.getCurrency());
	    merchantDetails.setTrAmt(merchantDetails.getTrAmt() == null || merchantDetails.getTrAmt().isEmpty() ? "" : merchantDetails.getTrAmt());
	    merchantDetails.setTipOrConvenienceIndicator(merchantDetails.getTipOrConvenienceIndicator() == null || merchantDetails.getTipOrConvenienceIndicator().isEmpty() ? "" : merchantDetails.getTipOrConvenienceIndicator());
	    merchantDetails.setConvenienceIndicatorFee(merchantDetails.getConvenienceIndicatorFee() == null || merchantDetails.getConvenienceIndicatorFee().isEmpty() ? "" : merchantDetails.getConvenienceIndicatorFee());
	    merchantDetails.setTipAmt(merchantDetails.getTipAmt() == null || merchantDetails.getTipAmt().isEmpty() ? "" : merchantDetails.getTipAmt());
	    merchantDetails.setCity(merchantDetails.getCity() == null || merchantDetails.getCity().isEmpty() ? "" : merchantDetails.getCity());
	    merchantDetails.setPostalCode(merchantDetails.getPostalCode() == null || merchantDetails.getPostalCode().isEmpty() ? "" : merchantDetails.getPostalCode());
	    merchantDetails.setCountryCode(merchantDetails.getCountryCode() == null || merchantDetails.getCountryCode().isEmpty() ? "" : merchantDetails.getCountryCode());

	    // Handle null or empty Additional Data Information
	    if (response.getAdditionalDataInformation() == null) {
	        response.setAdditionalDataInformation(new CIMAddlDataFieldRequest());
	    }
	    CIMAddlDataFieldRequest addDetails = response.getAdditionalDataInformation();
	    addDetails.setBillNumber(addDetails.getBillNumber() == null || addDetails.getBillNumber().isEmpty() ? "" : addDetails.getBillNumber());
	    addDetails.setMobileNumber(addDetails.getMobileNumber() == null || addDetails.getMobileNumber().isEmpty() ? "" : addDetails.getMobileNumber());
	    addDetails.setReferenceLabel(addDetails.getReferenceLabel() == null || addDetails.getReferenceLabel().isEmpty() ? "" : addDetails.getReferenceLabel());
	    addDetails.setPurposeOfTransaction(addDetails.getPurposeOfTransaction() == null || addDetails.getPurposeOfTransaction().isEmpty() ? "" : addDetails.getPurposeOfTransaction());
	    addDetails.setStoreLabel(addDetails.getStoreLabel() == null || addDetails.getStoreLabel().isEmpty() ? "" : addDetails.getStoreLabel());
	    addDetails.setLoyaltyNumber(addDetails.getLoyaltyNumber() == null || addDetails.getLoyaltyNumber().isEmpty() ? "" : addDetails.getLoyaltyNumber());
	    addDetails.setDeviceID(addDetails.getDeviceID() == null || addDetails.getDeviceID().isEmpty() ? "" : addDetails.getDeviceID());
	    addDetails.setCustomerLabel(addDetails.getCustomerLabel() == null || addDetails.getCustomerLabel().isEmpty() ? "" : addDetails.getCustomerLabel());
	    addDetails.setTerminalLabel(addDetails.getTerminalLabel() == null || addDetails.getTerminalLabel().isEmpty() ? "" : addDetails.getTerminalLabel());
	    addDetails.setAddlDataRequest(addDetails.getAddlDataRequest() == null || addDetails.getAddlDataRequest().isEmpty() ? "" : addDetails.getAddlDataRequest());

	    System.out.println("Response : " + response);
	    String jsonUserData = objectMapper.writeValueAsString(response);
	    System.out.println("Response Json : " + jsonUserData);
	    String encryptedData = encryption.encrypt(jsonUserData, psuDeviceID);

	    return encryptedData;
	}

}
