package com.bornfire.controller;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import javax.xml.bind.JAXBException;
import javax.xml.datatype.DatatypeConfigurationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bornfire.config.ErrorResponseCode;
import com.bornfire.config.SequenceGenerator;
import com.bornfire.entity.CIMAddlDataFieldRequest;
import com.bornfire.entity.CIMCustomerDecodeQRFormatResponse;
import com.bornfire.entity.CIMCustomerDirectFndRequest;
import com.bornfire.entity.CIMCustomerQRcodeRequest;
import com.bornfire.entity.CIMDirectCustomerRemitterAccount;
import com.bornfire.entity.CIMDirectMerchantBenAccount;
import com.bornfire.entity.CIMMerchantDecodeQRFormatResponse;
import com.bornfire.entity.CIMMerchantDecodeQRMerchantAcctInfo;
import com.bornfire.entity.CIMMerchantDecodeQRMerchantAddlInfo;
import com.bornfire.entity.CIMMerchantDirectFndRequest;
import com.bornfire.entity.CIMMerchantQRAddlInfo;
import com.bornfire.entity.CIMMerchantQRRequestFormat;
import com.bornfire.entity.CIMMerchantQRcodeAcctInfo;
import com.bornfire.entity.CIMMerchantQRcodeRequest;
import com.bornfire.entity.CimDynamicMaucasRequest;
import com.bornfire.entity.CimMerchantResponse;
import com.bornfire.entity.CustomerTransactionEntity;
import com.bornfire.entity.CustomerTransactionRepo;
import com.bornfire.entity.DeviceManagementRepository;
import com.bornfire.entity.MCCreditTransferResponse;
import com.bornfire.entity.MerchantMaster;
import com.bornfire.entity.MerchantMasterRep;
import com.bornfire.entity.MerchantQRRegistration;
import com.bornfire.entity.MerchantQrGenTable;
import com.bornfire.entity.MerchantQrGenTablerep;
import com.bornfire.entity.OutwardTransactionMonitoringTableRep;
import com.bornfire.entity.StaticMerchantNotificationEntity;
import com.bornfire.entity.StaticMerchantNotificationRepo;
import com.bornfire.exception.IPSXException;
import com.bornfire.qrcode.core.isos.Country;
import com.bornfire.qrcode.core.isos.Currency;
import com.google.zxing.WriterException;

@RestController
@RequestMapping("/api")
public class QrController {

	@Autowired
	SequenceGenerator sequence;

	@Autowired
	MerchantMasterRep merchantmasterRep;

	@Autowired
	Environment env;

	@Autowired
	IPSConnection ipsConnection;

	@Autowired
	MerchantQrGenTablerep mercantQrGenTableRep;

	@Autowired
	CustomerTransactionRepo customerrepo;

	@Autowired
	IpsDao ipsdao;

	@Autowired
	ErrorResponseCode errorCode;

	@Autowired
	DeviceManagementRepository devicemanagement;
	@Autowired
	OutwardTransactionMonitoringTableRep Otrepo;

	// Generate Static QR Code
	@PostMapping(path = "/ws/StaticMaucas", produces = "application/json", consumes = "application/json")
	public ResponseEntity<CimMerchantResponse> genMerchantQRcode(
			@RequestHeader(value = "P-ID", required = false) String p_id,
			@RequestHeader(value = "PSU-Device-ID", required = false) String psuDeviceID,
			@RequestHeader(value = "PSU-IP-Address", required = false) String psuIpAddress,
			@RequestHeader(value = "PSU-ID", required = false) String psuID,
			@RequestHeader(value = "PSU-Channel", required = false) String channelID,
			@RequestHeader(value = "Device_ID", required = true) String deviceID,
			@RequestHeader(value = "Reference_Number", required = true) String referencenumber,
			@RequestHeader(value = "User-ID", required = true) String UserID,
			@RequestHeader(value = "Unit-ID", required = true) String unit_id,
			@RequestHeader(value = "Terminal-ID", required = true) String terminal_id,
			@Size(max = 15, message = "Merchant ID should not exceed 15 characters") @RequestHeader(value = "Merchant_ID", required = true) String acct_num,
			@RequestHeader(value = "PSU-Resv-Field2", required = false) String resvfield2)
			throws DatatypeConfigurationException, JAXBException, KeyManagementException, UnrecoverableKeyException,
			KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException, WriterException {

		MerchantMaster ms = merchantmasterRep.findByIdCustom(acct_num);
		if (Objects.isNull(ms)) {
			CimMerchantResponse merchantQRResponse = new CimMerchantResponse();
			merchantQRResponse.setBase64QR("Unable to generate QR code due to an invalid merchant ID.");
			return new ResponseEntity<>(merchantQRResponse, HttpStatus.OK);
		} else {
			if (Objects.isNull(deviceID)) {
				CimMerchantResponse merchantQRResponse = new CimMerchantResponse();
				merchantQRResponse.setBase64QR("Unable to generate QR code - Device ID Not Found.");
				return new ResponseEntity<>(merchantQRResponse, HttpStatus.OK);
			}
			String terminalid = devicemanagement.findByTerminalId(deviceID);
			if (Objects.isNull(terminalid)) {
				CimMerchantResponse merchantQRResponse = new CimMerchantResponse();
				merchantQRResponse.setBase64QR("Unable to generate QR code - Terminal ID Not Found.");
				return new ResponseEntity<>(merchantQRResponse, HttpStatus.OK);
			}
			MerchantQRRegistration merchantQRgenerator = new MerchantQRRegistration();
			String paycode = env.getProperty("ipsx.qr.payeecode");
			String globalUnique = env.getProperty("ipsx.qr.globalUnique");
			String payload = env.getProperty("ipsx.qr.payload");
			String poiMethod_static = env.getProperty("ipsx.qr.poiMethod_static");
			merchantQRgenerator.setPoi_method(poiMethod_static);
			merchantQRgenerator.setPayee_participant_code(paycode);
			merchantQRgenerator.setGlobal_unique_id(globalUnique);
			merchantQRgenerator.setPayload_format_indicator(payload);
			merchantQRgenerator.setMerchant_acct_no(ms.getMerchant_acc_no());
			merchantQRgenerator.setMerchant_id(ms.getMerchant_id());
			merchantQRgenerator.setMerchant_name(ms.getMerchant_name());
			merchantQRgenerator.setMerchant_category_code(ms.getMerchant_cat_code());
			merchantQRgenerator.setTransaction_crncy("BWP");
			merchantQRgenerator.setTip_or_conv_indicator(ms.getTip_or_conv_indicator());
			merchantQRgenerator.setConv_fees_type(ms.getConv_fees_type());
			merchantQRgenerator.setValue_conv_fees(ms.getValue_conv_fees());
			merchantQRgenerator.setCity(ms.getMerchant_city());
			merchantQRgenerator.setCountry("BW");
			merchantQRgenerator.setZip_code(ms.getPincode());
			merchantQRgenerator.setBill_number(ms.getTr());
			merchantQRgenerator.setMobile(ms.getMerchant_mob_no());
			merchantQRgenerator.setLoyalty_number(ms.getLoyalty_number());
			merchantQRgenerator.setCustomer_label(ms.getCustomer_label());
			merchantQRgenerator.setStore_label(ms.getStore_label());
			merchantQRgenerator.setTerminal_label(ms.getReference_label());
			merchantQRgenerator.setReference_label(ms.getTerminal_label());
			merchantQRgenerator.setPurpose_of_tran(ms.getPurpose_of_tran());
			merchantQRgenerator.setAdditional_details(ms.getAdd_details_req());

			merchantQRgenerator.setBill_number(ms.getTr());
			merchantQRgenerator.setCustomer_label(ms.getCustomer_label());
			CIMMerchantQRcodeRequest cimMerchantQRcodeRequest = new CIMMerchantQRcodeRequest();
			// System.out.println(merchantQRgenerator.getPayload_format_indicator().toString());
			cimMerchantQRcodeRequest
					.setPayloadFormatIndiator(merchantQRgenerator.getPayload_format_indicator().toString());
			cimMerchantQRcodeRequest.setPointOfInitiationFormat(merchantQRgenerator.getPoi_method().toString());

			CIMMerchantQRcodeAcctInfo merchantQRAcctInfo = new CIMMerchantQRcodeAcctInfo();
			merchantQRAcctInfo.setGlobalID(merchantQRgenerator.getGlobal_unique_id());
			merchantQRAcctInfo.setPayeeParticipantCode(merchantQRgenerator.getPayee_participant_code());
			merchantQRAcctInfo.setMerchantAcctNumber(merchantQRgenerator.getMerchant_acct_no());
			merchantQRAcctInfo.setMerchantID(merchantQRgenerator.getMerchant_id());
			cimMerchantQRcodeRequest.setMerchantAcctInformation(merchantQRAcctInfo);

			cimMerchantQRcodeRequest.setMCC(merchantQRgenerator.getMerchant_category_code().toString());
			cimMerchantQRcodeRequest.setCurrency(merchantQRgenerator.getTransaction_crncy().toString());

			if (!String.valueOf(merchantQRgenerator.getTransaction_amt()).equals("null")
					&& !String.valueOf(merchantQRgenerator.getTransaction_amt()).equals("")) {
				cimMerchantQRcodeRequest.setTrAmt(merchantQRgenerator.getTransaction_amt().toString());
			}

			cimMerchantQRcodeRequest.setCountryCode(merchantQRgenerator.getCountry().toString());
			cimMerchantQRcodeRequest.setMerchantName(merchantQRgenerator.getMerchant_name().toString());
			cimMerchantQRcodeRequest.setCity(merchantQRgenerator.getCity().toString());

			if (!String.valueOf(merchantQRgenerator.getZip_code()).equals("null")
					&& !String.valueOf(merchantQRgenerator.getZip_code()).equals("")) {
				cimMerchantQRcodeRequest.setPostalCode(merchantQRgenerator.getZip_code().toString());
			}

			CIMMerchantQRAddlInfo cimMercbantQRAddlInfo = new CIMMerchantQRAddlInfo();
			cimMercbantQRAddlInfo.setBillNumber(merchantQRgenerator.getBill_number());
			cimMercbantQRAddlInfo.setMobileNumber(merchantQRgenerator.getMobile());
			cimMercbantQRAddlInfo.setStoreLabel(unit_id);
			cimMercbantQRAddlInfo.setLoyaltyNumber(deviceID);
			cimMercbantQRAddlInfo.setCustomerLabel(UserID);
			cimMercbantQRAddlInfo.setTerminalLabel(terminalid);
			cimMercbantQRAddlInfo.setReferenceLabel(referencenumber);
			cimMercbantQRAddlInfo.setPurposeOfTransaction(merchantQRgenerator.getPurpose_of_tran());
			cimMercbantQRAddlInfo.setAddlDataRequest(merchantQRgenerator.getAdditional_details());

			cimMerchantQRcodeRequest.setAdditionalDataInformation(cimMercbantQRAddlInfo);
			CimMerchantResponse merchantQRResponse = ipsConnection.createMerchantQRConnection(psuDeviceID, psuIpAddress,
					psuID, cimMerchantQRcodeRequest, p_id, channelID, acct_num, resvfield2,UserID,unit_id,terminal_id);
			String QrImg;
			String imageAsBase64 = null;
			if (merchantQRResponse.getBase64QR() != null) {
				QrImg = merchantQRResponse.getBase64QR();
				imageAsBase64 = QrImg;

			} else {
				if (merchantQRResponse.getBase64QR() == null) {

					imageAsBase64 = "Something went wrong at server end";
				}
			}
			merchantQRResponse.setBase64QR(imageAsBase64);
			return new ResponseEntity<>(merchantQRResponse, HttpStatus.OK);
		}

	}

	// Generate Dynamic QR Code
	@PostMapping(path = "/ws/DynamicMaucas", produces = "application/json", consumes = "application/json")
	public ResponseEntity<CimMerchantResponse> genDynamicMerchantQRcode(
			@RequestHeader(value = "P-ID", required = false) String p_id,
			@RequestHeader(value = "PSU-Device-ID", required = false) String psuDeviceID,
			@RequestHeader(value = "Device-ID", required = true) String DeviceID,
			@RequestHeader(value = "PSU-IP-Address", required = false) String psuIpAddress,
			@RequestHeader(value = "PSU-ID", required = false) String psuID,
			@RequestHeader(value = "PSU-Channel", required = true) String channelID,
			@RequestHeader(value = "User-ID", required = true) String UserID,
			@RequestHeader(value = "Merchant_ID", required = true) String acct_num,
			@RequestHeader(value = "PSU-Resv-Field2", required = false) String resvfield2,
			@RequestHeader(value = "Unit-ID", required = false) String unit_id,
			@RequestHeader(value = "Termina-ID", required = false) String terminal_id,
			@RequestBody CimDynamicMaucasRequest cimmaudynamic)
			throws DatatypeConfigurationException, JAXBException, KeyManagementException, UnrecoverableKeyException,
			KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException, WriterException {
		//System.out.println("Service Starts generate QR Code CimDynamicMaucasRequest
		// :" + cimmaudynamic.toString());
		//System.out.println("Device iddddd---->" + DeviceID);
		MerchantMaster ms = merchantmasterRep.findByIdCustom(cimmaudynamic.getMerchant_ID());
		if (Objects.isNull(ms)) {
			CimMerchantResponse merchantQRResponse = new CimMerchantResponse();
			merchantQRResponse.setBase64QR("Unable to generate QR code due to an invalid merchant ID.");
			return new ResponseEntity<>(merchantQRResponse, HttpStatus.OK);
		} else {
			String terminalid = devicemanagement.findByTerminalId(DeviceID);
			String deviceid = devicemanagement.findBydeviceId(DeviceID);
			if (Objects.isNull(terminalid)) {
				CimMerchantResponse merchantQRResponse = new CimMerchantResponse();
				merchantQRResponse.setBase64QR("Unable to generate QR code - Terminal ID Not Found.");
				return new ResponseEntity<>(merchantQRResponse, HttpStatus.OK);
			}
			if (Objects.isNull(deviceid)) {
				System.out.print("Device id is not found");
				CimMerchantResponse merchantQRResponse = new CimMerchantResponse();
				merchantQRResponse.setBase64QR("Unable to generate QR code due to an invalid device ID.");
				return new ResponseEntity<>(merchantQRResponse, HttpStatus.OK);
			} else {
				//String Billnumberexist = mercantQrGenTableRep.findByBilNumber(cimmaudynamic.getBill_num());
				String Billnumberexist=Otrepo.findByBilNumber(cimmaudynamic.getBill_num());
				if (Billnumberexist != null && Billnumberexist.equals(cimmaudynamic.getBill_num())) {
					CimMerchantResponse merchantQRResponse = new CimMerchantResponse();
					merchantQRResponse.setBase64QR("Unable to generate QR code due to duplicate Bill Number");
					return new ResponseEntity<>(merchantQRResponse, HttpStatus.OK);
				} else {
					MerchantQRRegistration merchantQRgenerator = new MerchantQRRegistration();
					String paycode = env.getProperty("ipsx.qr.payeecode");
					String globalUnique = env.getProperty("ipsx.qr.globalUnique");
					String payload = env.getProperty("ipsx.qr.payload");
					String poiMethod_static = env.getProperty("ipsx.qr.poiMethod_dynamic");
					merchantQRgenerator.setPoi_method(poiMethod_static);
					merchantQRgenerator.setPayee_participant_code(paycode);
					merchantQRgenerator.setGlobal_unique_id(globalUnique);
					merchantQRgenerator.setPayload_format_indicator(payload);
					merchantQRgenerator.setMerchant_acct_no(ms.getMerchant_acc_no());
					merchantQRgenerator.setMerchant_id(ms.getMerchant_id());
					merchantQRgenerator.setMerchant_name(ms.getMerchant_name());
					merchantQRgenerator.setMerchant_category_code(ms.getMerchant_cat_code());
					merchantQRgenerator.setTransaction_crncy("BWP");
					merchantQRgenerator.setTip_or_conv_indicator(ms.getTip_or_conv_indicator());
					merchantQRgenerator.setConv_fees_type(ms.getConv_fees_type());
					merchantQRgenerator.setValue_conv_fees(ms.getValue_conv_fees());
					merchantQRgenerator.setCity(ms.getMerchant_city());
					merchantQRgenerator.setCountry("BW");

					merchantQRgenerator.setTransaction_amt(cimmaudynamic.getTran_amt());
					merchantQRgenerator.setZip_code(ms.getPincode());
					String billno = cimmaudynamic.getBill_num();
					if (Objects.isNull(billno)) {
						CimMerchantResponse merchantQRResponse = new CimMerchantResponse();
						merchantQRResponse.setBase64QR("No Billl Number is Found");
						return new ResponseEntity<>(merchantQRResponse, HttpStatus.OK);
					} else {
						if (cimmaudynamic.getBill_num().equals("null") && cimmaudynamic.getBill_num().equals("")) {
							merchantQRgenerator.setBill_number(ms.getBill_number());
						} else {
							merchantQRgenerator.setBill_number(cimmaudynamic.getBill_num());
						}
					}

					if (Objects.isNull(cimmaudynamic.getMob_num())) {
						merchantQRgenerator.setMobile(ms.getMerchant_cont_details());
					} else {
						merchantQRgenerator.setMobile(cimmaudynamic.getMob_num());
					}

					CIMMerchantQRcodeRequest cimMerchantQRcodeRequest = new CIMMerchantQRcodeRequest();
					// System.out.println(merchantQRgenerator.getPayload_format_indicator().toString());
					cimMerchantQRcodeRequest
							.setPayloadFormatIndiator(merchantQRgenerator.getPayload_format_indicator().toString());
					cimMerchantQRcodeRequest.setPointOfInitiationFormat(merchantQRgenerator.getPoi_method().toString());

					CIMMerchantQRcodeAcctInfo merchantQRAcctInfo = new CIMMerchantQRcodeAcctInfo();
					merchantQRAcctInfo.setGlobalID(merchantQRgenerator.getGlobal_unique_id());
					merchantQRAcctInfo.setPayeeParticipantCode(merchantQRgenerator.getPayee_participant_code());
					merchantQRAcctInfo.setMerchantAcctNumber(merchantQRgenerator.getMerchant_acct_no());
					merchantQRAcctInfo.setMerchantID(merchantQRgenerator.getMerchant_id());
					cimMerchantQRcodeRequest.setMerchantAcctInformation(merchantQRAcctInfo);

					cimMerchantQRcodeRequest.setMCC(merchantQRgenerator.getMerchant_category_code().toString());
					cimMerchantQRcodeRequest.setCurrency(merchantQRgenerator.getTransaction_crncy().toString());
					String tranamount = merchantQRgenerator.getTransaction_amt();
					if (Objects.isNull(tranamount)) {
						CimMerchantResponse merchantQRResponse = new CimMerchantResponse();
						merchantQRResponse.setBase64QR("No Transaction Amount Found");
						return new ResponseEntity<>(merchantQRResponse, HttpStatus.OK);
					} else {
						if (!merchantQRgenerator.getTransaction_amt().equals("null")
								&& !merchantQRgenerator.getTransaction_amt().equals("")) {
							cimMerchantQRcodeRequest.setTrAmt(merchantQRgenerator.getTransaction_amt());
						}
					}

					if (merchantQRgenerator.getTip_or_conv_indicator() != null) {
						if (!merchantQRgenerator.getTip_or_conv_indicator().toString().equals("")) {

							if (merchantQRgenerator.getTip_or_conv_indicator().toString().equals("01")) {
								cimMerchantQRcodeRequest.setTipOrConvenienceIndicator("01");
							} else if (merchantQRgenerator.getTip_or_conv_indicator().toString().equals("02")) {
								if (merchantQRgenerator.getConv_fees_type().equals("Fixed")) {
									cimMerchantQRcodeRequest.setTipOrConvenienceIndicator("02");
									cimMerchantQRcodeRequest
											.setConvenienceIndicatorFee(merchantQRgenerator.getValue_conv_fees());
								} else if (merchantQRgenerator.getConv_fees_type().equals("Percentage")) {
									cimMerchantQRcodeRequest.setTipOrConvenienceIndicator("03");
									cimMerchantQRcodeRequest
											.setConvenienceIndicatorFee(merchantQRgenerator.getValue_conv_fees());

								}
							}

						}
					}

					cimMerchantQRcodeRequest.setCountryCode(merchantQRgenerator.getCountry().toString());
					cimMerchantQRcodeRequest.setMerchantName(merchantQRgenerator.getMerchant_name().toString());
					cimMerchantQRcodeRequest.setCity(merchantQRgenerator.getCity().toString());

					if (!String.valueOf(merchantQRgenerator.getZip_code()).equals("null")
							&& !String.valueOf(merchantQRgenerator.getZip_code()).equals("")) {
						cimMerchantQRcodeRequest.setPostalCode(merchantQRgenerator.getZip_code().toString());
					}

					CIMMerchantQRAddlInfo cimMercbantQRAddlInfo = new CIMMerchantQRAddlInfo();
					cimMercbantQRAddlInfo.setBillNumber(merchantQRgenerator.getBill_number());
					cimMercbantQRAddlInfo.setMobileNumber(merchantQRgenerator.getMobile());
					cimMercbantQRAddlInfo.setStoreLabel(unit_id);
					cimMercbantQRAddlInfo.setLoyaltyNumber(deviceid);
					cimMercbantQRAddlInfo.setCustomerLabel(UserID);
					cimMercbantQRAddlInfo.setTerminalLabel(terminalid);
					cimMercbantQRAddlInfo.setReferenceLabel(cimmaudynamic.getRef_label());
					cimMercbantQRAddlInfo.setPurposeOfTransaction(merchantQRgenerator.getPurpose_of_tran());
					cimMercbantQRAddlInfo.setAddlDataRequest(merchantQRgenerator.getAdditional_details());

					cimMerchantQRcodeRequest.setAdditionalDataInformation(cimMercbantQRAddlInfo);

					// System.out.println("cimMerchantQRcodeRequest" +
					// cimMerchantQRcodeRequest.toString());
					CimMerchantResponse merchantQRResponse = ipsConnection.createMerchantQRConnection(psuDeviceID,
							psuIpAddress, psuID, cimMerchantQRcodeRequest, p_id, channelID, acct_num, resvfield2,UserID,unit_id,terminal_id);
					String QrImg;
					String imageAsBase64 = null;
					if (merchantQRResponse.getBase64QR() != null) {
						QrImg = merchantQRResponse.getBase64QR();
						imageAsBase64 = QrImg;

					} else {
						if (merchantQRResponse.getBase64QR() == null) {

							imageAsBase64 = "Something went wrong at server end";
						}
					}

					merchantQRResponse.setBase64QR(imageAsBase64);

					return new ResponseEntity<>(merchantQRResponse, HttpStatus.OK);
				}
			}
		}
	}

	// Scan static QR Code
	@PostMapping(path = "/ws/scanMerchantQRcode", produces = "application/json", consumes = "application/json")
	public ResponseEntity<CIMMerchantDecodeQRFormatResponse> getMerchantQRdata(
	        @RequestHeader(value = "P-ID", required = true) @NotEmpty(message = "Required") String p_id,
	        @RequestHeader(value = "PSU-Device-ID", required = true) @NotEmpty(message = "Required") String psuDeviceID,
	        @RequestHeader(value = "PSU-IP-Address", required = true) String psuIpAddress,
	        @RequestHeader(value = "PSU-ID", required = false) String psuID,
	        @RequestHeader(value = "PSU-Channel", required = true) String channelID,
	        @RequestHeader(value = "PSU-Resv-Field1", required = false) String resvfield1,
	        @RequestHeader(value = "PSU-Resv-Field2", required = false) String resvfield2,
	        @Valid @RequestBody CIMMerchantQRRequestFormat mcCreditTransferRequest)
	        throws DatatypeConfigurationException, JAXBException, KeyManagementException, UnrecoverableKeyException,
	        KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException {
	    // Call the service to get the response
	    CIMMerchantDecodeQRFormatResponse response = ipsConnection.getMerchantQRdata(
	            psuDeviceID, psuIpAddress, psuID, mcCreditTransferRequest, p_id, channelID, resvfield1, resvfield2);

	    // If the response is null, create a new empty response
	    if (response == null) {
	        response = new CIMMerchantDecodeQRFormatResponse();
	    }

	    // Null checks for top-level fields
	    response.setPayloadFormatIndiator(response.getPayloadFormatIndiator() == null ? "" : response.getPayloadFormatIndiator());
	    response.setPointOfInitiationFormat(response.getPointOfInitiationFormat() == null ? "" : response.getPointOfInitiationFormat());
	    response.setMCC(response.getMCC() == null ? "" : response.getMCC());
	    response.setCurrency(response.getCurrency() == null ? "" : response.getCurrency());
	    response.setTrAmt(response.getTrAmt() == null ? "" : response.getTrAmt());
	    response.setTipOrConvenienceIndicator(response.getTipOrConvenienceIndicator() == null ? "" : response.getTipOrConvenienceIndicator());
	    response.setConvenienceIndicatorFee(response.getConvenienceIndicatorFee() == null ? "" : response.getConvenienceIndicatorFee());
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
	    acctInfo.setPayeeParticipantCode(acctInfo.getPayeeParticipantCode() == null ? "" : acctInfo.getPayeeParticipantCode());
	    acctInfo.setMerchantAcctNumber(acctInfo.getMerchantAcctNumber() == null ? "" : acctInfo.getMerchantAcctNumber());
	    acctInfo.setMerchantID(acctInfo.getMerchantID() == null ? "" : acctInfo.getMerchantID());
	    acctInfo.setReserveField(acctInfo.getReserveField() == null ? "" : acctInfo.getReserveField());

	    // Null checks for nested objects (AdditionalDataInformation)
	    if (response.getAdditionalDataInformation() == null) {
	        response.setAdditionalDataInformation(new CIMMerchantDecodeQRMerchantAddlInfo());
	    }
	    CIMMerchantDecodeQRMerchantAddlInfo additionalData = response.getAdditionalDataInformation();
	    additionalData.setBillNumber(additionalData.getBillNumber() == null ? "" : additionalData.getBillNumber());
	    additionalData.setMobileNumber(additionalData.getMobileNumber() == null ? "" : additionalData.getMobileNumber());
	    additionalData.setStoreLabel(additionalData.getStoreLabel() == null ? "" : additionalData.getStoreLabel());
	    additionalData.setLoyaltyNumber(additionalData.getLoyaltyNumber() == null ? "" : additionalData.getLoyaltyNumber());
	    additionalData.setReferenceLabel(additionalData.getReferenceLabel() == null ? "" : additionalData.getReferenceLabel());
	    additionalData.setCustomerLabel(additionalData.getCustomerLabel() == null ? "" : additionalData.getCustomerLabel());
	    additionalData.setTerminalLabel(additionalData.getTerminalLabel() == null ? "" : additionalData.getTerminalLabel());
	    additionalData.setPurposeOfTransaction(additionalData.getPurposeOfTransaction() == null ? "" : additionalData.getPurposeOfTransaction());
	    additionalData.setAddlDataRequest(additionalData.getAddlDataRequest() == null ? "" : additionalData.getAddlDataRequest());

	    // Return the modified response
	    return new ResponseEntity<>(response, HttpStatus.OK);
	}

	// Transaction Api
	@PostMapping(path = "/webservices/directMerchantFndTransfer", produces = "application/json", consumes = "application/json")
	public ResponseEntity<MCCreditTransferResponse> directMerchantFndTransfer(
			@RequestHeader(value = "P-ID", required = true) @NotEmpty(message = "Required") String p_id,
			@RequestHeader(value = "PSU-Device-ID", required = true) @NotEmpty(message = "Required") String psuDeviceID,
			@RequestHeader(value = "PSU-IP-Address", required = true) String psuIpAddress,
			@RequestHeader(value = "PSU-ID", required = false) String psuID,
			@RequestHeader(value = "PSU-Channel", required = true) String channelID,
			@RequestHeader(value = "PSU-Resv-Field1", required = false) String resvfield1,
			@RequestHeader(value = "PSU-Resv-Field2", required = false) String resvfield2,
			@RequestHeader(value = "User-ID", required = true) String userid,
			@RequestHeader(value = "Unit-ID", required = true) String unit_id,
			@RequestHeader(value = "Unit-Name", required = true) String unit_name,
			@Valid @RequestBody CIMMerchantDirectFndRequest mcCreditTransferRequest)
			throws DatatypeConfigurationException, JAXBException, KeyManagementException, UnrecoverableKeyException,
			KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException {

		// System.out.println("Service Starts" + p_id);

		MCCreditTransferResponse response = null;

		// System.out.println("Calling Credit Transfer Connection flow Starts");

		if (ipsdao.invalidP_ID(p_id)) {

			response = ipsConnection.createMerchantFTConnection(psuDeviceID, psuIpAddress, psuID,
					mcCreditTransferRequest, p_id, userid, unit_id, unit_name, channelID, resvfield1, resvfield2);

		} else {
			String responseStatus = errorCode.validationError("BIPS13");
			throw new IPSXException(responseStatus);
		}

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	// Customer QR Code API
	@PostMapping(path = "/ws/generateCustomerQRcode", produces = "application/json", consumes = "application/json")
	public ResponseEntity<CimMerchantResponse> genMerchantQRcode(
			@RequestHeader(value = "P-ID", required = true) @NotEmpty(message = "Required") String p_id,
			@RequestHeader(value = "PSU-Device-ID", required = true) @NotEmpty(message = "Required") String psuDeviceID,
			@RequestHeader(value = "PSU-IP-Address", required = true) String psuIpAddress,
			@RequestHeader(value = "PSU-ID", required = false) String psuID,
			@RequestHeader(value = "PSU-Channel", required = true) String channelID,
			@RequestHeader(value = "PSU-Resv-Field1", required = false) String resvfield1,
			@RequestHeader(value = "PSU-Resv-Field2", required = false) String resvfield2,
			@Valid @RequestBody CIMCustomerQRcodeRequest mcCreditTransferRequest)
			throws DatatypeConfigurationException, JAXBException, KeyManagementException, UnrecoverableKeyException,
			KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException, Exception {
		// System.out.println(mcCreditTransferRequest.toString());
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
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	// Scan Customer API
	@PostMapping(path = "/ws/scanCustomerQRcode", produces = "application/json", consumes = "application/json")
	public ResponseEntity<CIMCustomerDecodeQRFormatResponse> getCustomerQRdata(
			@RequestHeader(value = "P-ID", required = true) @NotEmpty(message = "Required") String p_id,
			@RequestHeader(value = "PSU-Device-ID", required = true) @NotEmpty(message = "Required") String psuDeviceID,
			@RequestHeader(value = "PSU-IP-Address", required = true) String psuIpAddress,
			@RequestHeader(value = "PSU-ID", required = false) String psuID,
			@RequestHeader(value = "PSU-Channel", required = true) String channelID,
			@RequestHeader(value = "PSU-Resv-Field1", required = false) String resvfield1,
			@RequestHeader(value = "PSU-Resv-Field2", required = false) String resvfield2,
			@Valid @RequestBody CIMMerchantQRRequestFormat mcCreditTransferRequest)
			throws DatatypeConfigurationException, JAXBException, KeyManagementException, UnrecoverableKeyException,
			KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException {
		// System.out.println("Request------------->" + mcCreditTransferRequest);
		// System.out.println("Service Starts" + p_id);

		CIMCustomerDecodeQRFormatResponse response = ipsConnection.getCustomerQRdata(psuDeviceID, psuIpAddress, psuID,
				mcCreditTransferRequest, p_id, channelID, resvfield1, resvfield2);

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	// Transaction Api
	@PostMapping(path = "/ws/InitiateCustomerTransaction", produces = "application/json", consumes = "application/json")
	public String directCustomerFndInititate(
			@RequestHeader(value = "P-ID", required = true) @NotEmpty(message = "Required") String p_id,
			@RequestHeader(value = "PSU-Device-ID", required = true) @NotEmpty(message = "Required") String psuDeviceID,
			@RequestHeader(value = "PSU-IP-Address", required = true) String psuIpAddress,
			@RequestHeader(value = "PSU-ID", required = false) String psuID,
			@RequestHeader(value = "PSU-Channel", required = true) String channelID,
			@RequestHeader(value = "PSU-Resv-Field1", required = false) String resvfield1,
			@RequestHeader(value = "PSU-Resv-Field2", required = false) String resvfield2,
			@Valid @RequestBody CustomerTransactionEntity request)
			throws DatatypeConfigurationException, JAXBException, KeyManagementException, UnrecoverableKeyException,
			KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException {

		// System.out.println("Calling Credit Transfer Connection flow Starts");

		CustomerTransactionEntity customer = new CustomerTransactionEntity();

		customer.setPayload_format_indicator(env.getProperty("ipsx.qr.payload"));
		customer.setPoi_method(env.getProperty("ipsx.qr.poiMethod_static"));
		customer.setPayee_participant_code(env.getProperty("ipsx.qr.payeecode"));
		customer.setGlobal_unique_id(env.getProperty("ipsx.qr.globalUnique"));
		customer.setCountry("BW");

		customer.setCustomer_id(request.getCustomer_id());
		customer.setCity(request.getCity());
		customer.setCustomer_transaction_amt(request.getCustomer_transaction_amt());
		customer.setPurpose_of_tran(request.getPurpose_of_tran());

		customer.setCustomer_name(request.getCustomer_name());
		customer.setCustomer_bill_number(request.getCustomer_bill_number());
		String Billnumberexist=Otrepo.findByBilNumber(request.getCustomer_bill_number());
		if (Billnumberexist != null && Billnumberexist.equals(request.getCustomer_bill_number())) {
			return "Duplicate Bill Number";
		}
		customer.setCustomer_reference_label(request.getCustomer_reference_label());
		String Accountnumber = merchantmasterRep.getMerchantaccountnumber(request.getMerchant_id());
		customer.setMerchant_acct_no(Accountnumber);
		customer.setMerchant_id(request.getMerchant_id());
		customer.setMerchant_name(request.getMerchant_name());
		customer.setUser_id(request.getUser_id());
		customer.setMerchant_deviceid(request.getMerchant_deviceid());
		customer.setMerchant_mob_no(request.getPhone());
		String terminalid = devicemanagement.findByTerminalId(request.getMerchant_deviceid());
		customer.setMerchant_terminal_label(terminalid);
		String mcc = merchantmasterRep.getMCC(request.getMerchant_id());
		customer.setMcc(mcc);
		customer.setCurrency("BWP");
		customer.setCountry_code("BW");
		customer.setPostal_code(request.getPostal_code());
		customer.setMerchant_mob_no(request.getMerchant_mob_no());
		customer.setMerchant_reference_label(request.getCustomer_reference_label());
		customer.setMerchant_status(request.getMerchant_status());
		customer.setUnit_id(request.getUnit_id());
		MerchantQrGenTable merchanrGenEntity = mercantQrGenTableRep.getRecordByRefLable(request.getCustomer_reference_label());
		if (Objects.nonNull(merchanrGenEntity)) {
			merchanrGenEntity.setStore_label(request.getUnit_id());
			merchanrGenEntity.setCustomer_label(request.getUser_id());
			merchanrGenEntity.setLoyalty_number(request.getMerchant_deviceid());
			merchanrGenEntity.setTerminal_label(terminalid);
			merchanrGenEntity.setP_id(merchanrGenEntity.getP_id());
		mercantQrGenTableRep.save(merchanrGenEntity);
		}
		
		customer.setEntity_flg("Y");
		customer.setEntry_time(new Date());
		customerrepo.save(customer);

		return "Successfully Initiated";
	}

	@GetMapping("/ws/getMerchantTransactionDetails")
	public CIMCustomerDirectFndRequest GetUnitAllUser(@RequestParam String customer_id,
			@RequestParam String reference_number) {
		return getresponse(customer_id, reference_number);
	}

	public CIMCustomerDirectFndRequest getresponse(String customer_id, String reference_number) {
		CustomerTransactionEntity request = customerrepo.getDetails(customer_id, reference_number);
		if (Objects.nonNull(request)) {
			CIMCustomerDirectFndRequest response = new CIMCustomerDirectFndRequest();

			CIMDirectCustomerRemitterAccount remiterresponse = new CIMDirectCustomerRemitterAccount();
			remiterresponse.setCustomerID(request.getCustomer_id());
			remiterresponse.setCustomerName(request.getCustomer_name());
			remiterresponse.setReferenceNumber(request.getCustomer_reference_label());
			response.setRemitterAccount(remiterresponse);

			CIMDirectMerchantBenAccount merchantresponse = new CIMDirectMerchantBenAccount();
			merchantresponse.setGlobalID(request.getGlobal_unique_id());
			merchantresponse.setPointOfInitiationFormat(request.getPoi_method());
			merchantresponse.setMerchantName(request.getMerchant_name());
			merchantresponse.setMerchantID(request.getMerchant_id());
			merchantresponse.setMerchantAcctNumber(request.getMerchant_acct_no());
			merchantresponse.setPayeeParticipantCode(request.getPayee_participant_code());
			merchantresponse.setMCC(request.getMcc());
			merchantresponse.setCurrency(request.getCurrency());
			merchantresponse.setCity(request.getCity());
			merchantresponse.setCountryCode(request.getCountry_code());
			merchantresponse.setPostalCode(" ");
			merchantresponse.setTrAmt(request.getCustomer_transaction_amt());
			merchantresponse.setTipOrConvenienceIndicator("False");
			merchantresponse.setConvenienceIndicatorFee(" ");
			response.setMerchantAccount(merchantresponse);

			CIMAddlDataFieldRequest additionresponse = new CIMAddlDataFieldRequest();
			additionresponse.setBillNumber(request.getCustomer_bill_number());
			additionresponse.setMobileNumber(request.getMerchant_mob_no());
			additionresponse.setDeviceID(request.getMerchant_deviceid());
			additionresponse.setStoreLabel(request.getUnit_id());
			additionresponse.setLoyaltyNumber(request.getMerchant_deviceid());
			additionresponse.setTerminalLabel(request.getMerchant_terminal_label());
			additionresponse.setCustomerLabel(request.getUser_id());
			additionresponse.setReferenceLabel(request.getMerchant_reference_label());
			additionresponse.setPurposeOfTransaction(request.getPurpose_of_tran());
			response.setAdditionalDataInformation(additionresponse);
			return response;
		}
		return null;
	}

	@GetMapping("/getTranAmountLimit")
	public String getTranAmountLimit(@RequestParam String merchant_id) {
		String Amount = merchantmasterRep.getTranAmountLimit(merchant_id);
		return Amount;
	}

	@Autowired
	StaticMerchantNotificationRepo repo;

	@GetMapping("/getStaticPaydetails")
	public ResponseEntity<?> getCustomerPayDetails(@RequestParam String merchant_id, @RequestParam String device_id,
			@RequestParam String userid) throws Exception {
		//System.out.println(merchant_id + device_id + userid);
		List<StaticMerchantNotificationEntity> responses = repo.getstaticqrdetails(merchant_id, device_id, userid);

		if (responses == null || responses.isEmpty()) {
			return new ResponseEntity<>(responses, HttpStatus.OK);
		}

		// Process the records
		for (StaticMerchantNotificationEntity response : responses) {
			char notificationFlag = response.getNotification_flag();
			if (notificationFlag == 'N') {
				response.setNotification_flag('Y');
				repo.save(response);
				// repo.delete(response); // Uncomment if you want to delete
			}
		}

		return new ResponseEntity<>(responses, HttpStatus.OK);
	}
	
	@PostMapping("/ws/generateMerchantQRcode")
	public ResponseEntity<CimMerchantResponse> genMerchantQRcode(
			@RequestHeader(value = "P-ID", required = true) @NotEmpty(message = "Required") String p_id,
			@RequestHeader(value = "PSU-Device-ID", required = true) @NotEmpty(message = "Required") String psuDeviceID,
			@RequestHeader(value = "PSU-IP-Address", required = true) String psuIpAddress,
			@RequestHeader(value = "PSU-ID", required = false) String psuID,
			@RequestHeader(value = "PSU-Channel", required = true) String channelID,
			@RequestHeader(value = "PSU-Resv-Field1", required = false) String resvfield1,
			@RequestHeader(value = "PSU-Resv-Field2", required = false) String resvfield2,
			@RequestHeader(value = "User_ID", required = true) String user_id,
			@RequestHeader(value = "Unit_ID", required = true) String unit_id,
			@RequestHeader(value = "Terminal_ID", required = true) String terminal_id,
			@Valid @RequestBody CIMMerchantQRcodeRequest mcCreditTransferRequest)
			throws DatatypeConfigurationException, JAXBException, KeyManagementException, UnrecoverableKeyException,
			KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException, WriterException {

		//logger.info("Service Starts generate QR Code");
		System.out.println("QR CODE NEW");
		System.out.println(mcCreditTransferRequest.toString());

		CimMerchantResponse response = null;

		if (ipsdao.checkConvenienceFeeValidationQR(mcCreditTransferRequest)) {
			if (!ipsdao.invaliQRdBankCode(
					mcCreditTransferRequest.getMerchantAcctInformation().getPayeeParticipantCode())) {
				if (Currency.exists(mcCreditTransferRequest.getCurrency())) {
					if (Country.exists(mcCreditTransferRequest.getCountryCode())) {
						response = ipsConnection.createMerchantQRConnection(psuDeviceID, psuIpAddress, psuID,
								mcCreditTransferRequest, p_id, channelID, resvfield1, resvfield2,user_id,unit_id,terminal_id);
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

		}

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
