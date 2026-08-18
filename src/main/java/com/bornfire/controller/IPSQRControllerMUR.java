package com.bornfire.controller;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Hashtable;

import javax.imageio.ImageIO;
import javax.xml.bind.JAXBException;
import javax.xml.datatype.DatatypeConfigurationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.bornfire.config.ErrorResponseCode;
import com.bornfire.entity.CIMMerchantQRAddlInfo;
import com.bornfire.entity.CIMMerchantQRcodeAcctInfo;
import com.bornfire.entity.CIMMerchantQRcodeRequest;
import com.bornfire.entity.CimDynamicMaucasRequest;
import com.bornfire.entity.CimMerchantResponse;
import com.bornfire.entity.MerchantMaster;
import com.bornfire.entity.MerchantMasterRep;
import com.bornfire.entity.MerchantQRRegistration;
import com.bornfire.entity.QRUrlGlobalEntity;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

@RestController
@Validated
public class IPSQRControllerMUR {

	private static final Logger logger = LoggerFactory.getLogger(IPSQRControllerMUR.class);

	@Autowired
	ErrorResponseCode errorCode;

	@Autowired
	IPSConnection ipsConnection;

	@Autowired
	IpsDao ipsDao;

	@Autowired
	MerchantMasterRep merchantmasterRep;

	@Autowired
	Environment env;

	@PostMapping(path = "/api/ws/StaticMaucasMur", produces = "application/json", consumes = "application/json")
	public ResponseEntity<CimMerchantResponse> StaticMaucasMur(
			@RequestHeader(value = "P-ID", required = false) String p_id,
			@RequestHeader(value = "PSU-Device-ID", required = false) String psuDeviceID,
			@RequestHeader(value = "PSU-IP-Address", required = false) String psuIpAddress,
			@RequestHeader(value = "PSU-ID", required = false) String psuID,
			@RequestHeader(value = "PSU-Channel", required = false) String channelID,
			@RequestHeader(value = "Merchant_ID", required = true) String acct_num,
			@RequestHeader(value = "User_ID", required = true) String user_id,
			@RequestHeader(value = "Unit_ID", required = true) String unit_id,
			@RequestHeader(value = "Terminal_ID", required = true) String terminal_id,
			@RequestHeader(value = "PSU-Resv-Field2", required = false) String resvfield2)
			throws DatatypeConfigurationException, JAXBException, KeyManagementException, UnrecoverableKeyException,
			KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException {

		logger.info("Service Starts generate QR Code");
		MerchantMaster ms = merchantmasterRep.findByIdCustom(acct_num);
		MerchantQRRegistration merchantQRgenerator = new MerchantQRRegistration();
		String paycode = env.getProperty("ipsx.qr.payeecode");
		String globalUnique = env.getProperty("ipsx.qr.globalUnique");
		String payload = env.getProperty("ipsx.qr.payload");
		String poiMethod_static = env.getProperty("ipsx.qr.poiMethod_static");
		merchantQRgenerator.setPoi_method(poiMethod_static);
		merchantQRgenerator.setPayee_participant_code(paycode);
		merchantQRgenerator.setGlobal_unique_id(globalUnique);
		merchantQRgenerator.setPayload_format_indicator(payload);
		merchantQRgenerator.setMerchant_acct_no(ms.getMerchant_id());
		merchantQRgenerator.setMerchant_id(ms.getMerchant_id());
		merchantQRgenerator.setMerchant_name(ms.getMerchant_name());
		merchantQRgenerator.setMerchant_category_code(ms.getMerchant_cat_code());
		merchantQRgenerator.setTransaction_crncy(ms.getCurr());
		merchantQRgenerator.setTip_or_conv_indicator(ms.getTip_or_conv_indicator());
		merchantQRgenerator.setConv_fees_type(ms.getConv_fees_type());
		merchantQRgenerator.setValue_conv_fees(ms.getValue_conv_fees());
		merchantQRgenerator.setCity(ms.getMerchant_city());
		merchantQRgenerator.setCountry("MU");
		merchantQRgenerator.setZip_code(ms.getPincode());
		merchantQRgenerator.setBill_number(ms.getTr());
		merchantQRgenerator.setMobile(ms.getMerchant_mob_no());
		merchantQRgenerator.setLoyalty_number(ms.getLoyalty_number());
		merchantQRgenerator.setCustomer_label(ms.getCustomer_label());
		merchantQRgenerator.setStore_label(ms.getStore_label());
		merchantQRgenerator.setTerminal_label(ms.getTerminal_label());
		merchantQRgenerator.setReference_label(ms.getReference_label());
		merchantQRgenerator.setPurpose_of_tran(ms.getPurpose_of_tran());
		merchantQRgenerator.setAdditional_details(ms.getAdd_details_req());
		merchantQRgenerator.setBill_number(ms.getTr());
		merchantQRgenerator.setCustomer_label(ms.getCustomer_label());
		CIMMerchantQRcodeRequest cimMerchantQRcodeRequest = new CIMMerchantQRcodeRequest();
		System.out.println(merchantQRgenerator.getPayload_format_indicator().toString());
		cimMerchantQRcodeRequest.setPayloadFormatIndiator(merchantQRgenerator.getPayload_format_indicator().toString());
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
		if (merchantQRgenerator.getTip_or_conv_indicator() != null) {
			if (!merchantQRgenerator.getTip_or_conv_indicator().toString().equals("")) {
				if (merchantQRgenerator.getTip_or_conv_indicator().toString().equals("01")) {
					cimMerchantQRcodeRequest.setTipOrConvenienceIndicator("01");
				} else if (merchantQRgenerator.getTip_or_conv_indicator().toString().equals("02")) {
					if (merchantQRgenerator.getConv_fees_type().equals("Fixed")) {
						cimMerchantQRcodeRequest.setTipOrConvenienceIndicator("02");
						cimMerchantQRcodeRequest.setConvenienceIndicatorFee(merchantQRgenerator.getValue_conv_fees());
					} else if (merchantQRgenerator.getConv_fees_type().equals("Percentage")) {
						cimMerchantQRcodeRequest.setTipOrConvenienceIndicator("03");
						cimMerchantQRcodeRequest.setConvenienceIndicatorFee(merchantQRgenerator.getValue_conv_fees());
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
		cimMercbantQRAddlInfo.setStoreLabel(merchantQRgenerator.getStore_label());
		cimMercbantQRAddlInfo.setLoyaltyNumber(merchantQRgenerator.getLoyalty_number());
		cimMercbantQRAddlInfo.setCustomerLabel(merchantQRgenerator.getCustomer_label());
		cimMercbantQRAddlInfo.setTerminalLabel(merchantQRgenerator.getTerminal_label());
		cimMercbantQRAddlInfo.setReferenceLabel(merchantQRgenerator.getReference_label());
		cimMercbantQRAddlInfo.setPurposeOfTransaction(merchantQRgenerator.getPurpose_of_tran());
		cimMercbantQRAddlInfo.setAddlDataRequest(merchantQRgenerator.getAdditional_details());
		cimMerchantQRcodeRequest.setAdditionalDataInformation(cimMercbantQRAddlInfo);
		CimMerchantResponse merchantQRResponse = ipsConnection.createMerchantQRConnection(psuDeviceID, psuIpAddress,
				psuID, cimMerchantQRcodeRequest, p_id, channelID, acct_num, resvfield2,user_id,unit_id,terminal_id);
		String QrImg;
		String imageAsBase64 = null;
		if (merchantQRResponse.getBase64QR() != null) {
			QrImg = merchantQRResponse.getBase64QR();
			imageAsBase64 = "data:image/png;base64," + QrImg;

		} else {
			if (merchantQRResponse.getBase64QR() == null) {
				imageAsBase64 = "Something went wrong at server end";
			}
		}
		merchantQRResponse.setBase64QR(imageAsBase64);
		return new ResponseEntity<>(merchantQRResponse, HttpStatus.OK);
	}

	@PostMapping(path = "/api/ws/DynamicMaucasMur", produces = "application/json", consumes = "application/json")
	public ResponseEntity<CimMerchantResponse> genDynamicMerchantQRcode(
			@RequestHeader(value = "P-ID", required = false) String p_id,
			@RequestHeader(value = "PSU-Device-ID", required = false) String psuDeviceID,
			@RequestHeader(value = "PSU-IP-Address", required = false) String psuIpAddress,
			@RequestHeader(value = "PSU-ID", required = false) String psuID,
			@RequestHeader(value = "PSU-Channel", required = true) String channelID,
			@RequestHeader(value = "Merchant_ID", required = true) String acct_num,
			@RequestHeader(value = "PSU-Resv-Field2", required = false) String resvfield2,
			@RequestHeader(value = "User_ID", required = true) String user_id,
			@RequestHeader(value = "Unit_ID", required = true) String unit_id,
			@RequestHeader(value = "Terminal_ID", required = true) String terminal_id,
			@RequestBody CimDynamicMaucasRequest cimmaudynamic)

			throws DatatypeConfigurationException, JAXBException, KeyManagementException, UnrecoverableKeyException,
			KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException {

		logger.info("Service Starts generate QR Code CimDynamicMaucasRequest :" + cimmaudynamic.toString());
		MerchantMaster ms = merchantmasterRep.findByIdCustom(cimmaudynamic.getMerchant_ID());
		MerchantQRRegistration merchantQRgenerator = new MerchantQRRegistration();
		String paycode = env.getProperty("ipsx.qr.payeecode");
		String globalUnique = env.getProperty("ipsx.qr.globalUnique");
		String payload = env.getProperty("ipsx.qr.payload");
		String poiMethod_static = env.getProperty("ipsx.qr.poiMethod_dynamic");
		merchantQRgenerator.setPoi_method(poiMethod_static);
		merchantQRgenerator.setPayee_participant_code(paycode);
		merchantQRgenerator.setGlobal_unique_id(globalUnique);
		merchantQRgenerator.setPayload_format_indicator(payload);
		merchantQRgenerator.setMerchant_acct_no(ms.getMerchant_id());
		merchantQRgenerator.setMerchant_id(ms.getMerchant_id());
		merchantQRgenerator.setMerchant_name(ms.getMerchant_name());
		merchantQRgenerator.setMerchant_category_code(ms.getMerchant_cat_code());
		merchantQRgenerator.setTransaction_crncy(ms.getCurr());
		merchantQRgenerator.setTip_or_conv_indicator(ms.getTip_or_conv_indicator());
		merchantQRgenerator.setConv_fees_type(ms.getConv_fees_type());
		merchantQRgenerator.setValue_conv_fees(ms.getValue_conv_fees());
		merchantQRgenerator.setCity(ms.getMerchant_city());
		merchantQRgenerator.setCountry("MU");

		merchantQRgenerator.setTransaction_amt(cimmaudynamic.getTran_amt());
		merchantQRgenerator.setZip_code(ms.getPincode());

		if (cimmaudynamic.getBill_num().equals("null") && cimmaudynamic.getBill_num().equals("")) {
			merchantQRgenerator.setBill_number(ms.getBill_number());
		} else {
			merchantQRgenerator.setBill_number(cimmaudynamic.getBill_num());
		}
		if (cimmaudynamic.getLoy_num().equals("null") && cimmaudynamic.getLoy_num().equals("")) {
			merchantQRgenerator.setLoyalty_number(ms.getLoyalty_number());
		} else {
			merchantQRgenerator.setLoyalty_number(cimmaudynamic.getLoy_num());
		}
		if (cimmaudynamic.getMob_num().equals("null") && cimmaudynamic.getMob_num().equals("")) {
			merchantQRgenerator.setMobile(ms.getMerchant_cont_details());
		} else {
			merchantQRgenerator.setMobile(cimmaudynamic.getMob_num());
		}
		if (cimmaudynamic.getCust_label().equals("null") && cimmaudynamic.getCust_label().equals("")) {
			merchantQRgenerator.setCustomer_label(ms.getCustomer_label());
		} else {
			merchantQRgenerator.setCustomer_label(cimmaudynamic.getCust_label());
		}
		if (cimmaudynamic.getSto_label().equals("null") && cimmaudynamic.getSto_label().equals("")) {
			merchantQRgenerator.setStore_label(ms.getStore_label());
		} else {
			merchantQRgenerator.setStore_label(cimmaudynamic.getSto_label());
		}
		if (cimmaudynamic.getTer_label().equals("null") && cimmaudynamic.getTer_label().equals("")) {
			merchantQRgenerator.setTerminal_label(ms.getTerminal_label());
		} else {
			merchantQRgenerator.setTerminal_label(cimmaudynamic.getTer_label());
		}
		if (cimmaudynamic.getRef_label().equals("null") && cimmaudynamic.getRef_label().equals("")) {
			merchantQRgenerator.setReference_label(ms.getReference_label());
		} else {
			merchantQRgenerator.setReference_label(cimmaudynamic.getRef_label());
		}
		if (cimmaudynamic.getPur_tran().equals("null") && cimmaudynamic.getPur_tran().equals("")) {
			merchantQRgenerator.setPurpose_of_tran(ms.getPurpose_of_tran());
		} else {
			merchantQRgenerator.setPurpose_of_tran(cimmaudynamic.getPur_tran());
		}
		if (cimmaudynamic.getAdd_det().equals("null") && cimmaudynamic.getAdd_det().equals("")) {
			merchantQRgenerator.setAdditional_details(ms.getAdd_details_req());
		} else {
			merchantQRgenerator.setAdditional_details(cimmaudynamic.getAdd_det());
		}
		if (cimmaudynamic.getCust_label().equals("null") && cimmaudynamic.getCust_label().equals("")) {
			merchantQRgenerator.setCustomer_label(ms.getCustomer_label());
		} else {
			merchantQRgenerator.setCustomer_label(cimmaudynamic.getCust_label());
		}

		CIMMerchantQRcodeRequest cimMerchantQRcodeRequest = new CIMMerchantQRcodeRequest();
		System.out.println(merchantQRgenerator.getPayload_format_indicator().toString());
		cimMerchantQRcodeRequest.setPayloadFormatIndiator(merchantQRgenerator.getPayload_format_indicator().toString());
		cimMerchantQRcodeRequest.setPointOfInitiationFormat(merchantQRgenerator.getPoi_method().toString());

		CIMMerchantQRcodeAcctInfo merchantQRAcctInfo = new CIMMerchantQRcodeAcctInfo();
		merchantQRAcctInfo.setGlobalID(merchantQRgenerator.getGlobal_unique_id());
		merchantQRAcctInfo.setPayeeParticipantCode(merchantQRgenerator.getPayee_participant_code());
		merchantQRAcctInfo.setMerchantAcctNumber(merchantQRgenerator.getMerchant_acct_no());
		merchantQRAcctInfo.setMerchantID(merchantQRgenerator.getMerchant_id());
		cimMerchantQRcodeRequest.setMerchantAcctInformation(merchantQRAcctInfo);

		cimMerchantQRcodeRequest.setMCC(merchantQRgenerator.getMerchant_category_code().toString());
		cimMerchantQRcodeRequest.setCurrency(merchantQRgenerator.getTransaction_crncy().toString());

		if (!merchantQRgenerator.getTransaction_amt().equals("null")
				&& !merchantQRgenerator.getTransaction_amt().equals("")) {
			cimMerchantQRcodeRequest.setTrAmt(merchantQRgenerator.getTransaction_amt());
		}

		if (merchantQRgenerator.getTip_or_conv_indicator() != null) {
			if (!merchantQRgenerator.getTip_or_conv_indicator().toString().equals("")) {

				if (merchantQRgenerator.getTip_or_conv_indicator().toString().equals("01")) {
					cimMerchantQRcodeRequest.setTipOrConvenienceIndicator("01");
				} else if (merchantQRgenerator.getTip_or_conv_indicator().toString().equals("02")) {
					if (merchantQRgenerator.getConv_fees_type().equals("Fixed")) {
						cimMerchantQRcodeRequest.setTipOrConvenienceIndicator("02");
						cimMerchantQRcodeRequest.setConvenienceIndicatorFee(merchantQRgenerator.getValue_conv_fees());
					} else if (merchantQRgenerator.getConv_fees_type().equals("Percentage")) {
						cimMerchantQRcodeRequest.setTipOrConvenienceIndicator("03");
						cimMerchantQRcodeRequest.setConvenienceIndicatorFee(merchantQRgenerator.getValue_conv_fees());

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
		cimMercbantQRAddlInfo.setStoreLabel(merchantQRgenerator.getStore_label());
		cimMercbantQRAddlInfo.setLoyaltyNumber(merchantQRgenerator.getLoyalty_number());
		cimMercbantQRAddlInfo.setCustomerLabel(merchantQRgenerator.getCustomer_label());
		cimMercbantQRAddlInfo.setTerminalLabel(merchantQRgenerator.getTerminal_label());
		cimMercbantQRAddlInfo.setReferenceLabel(merchantQRgenerator.getReference_label());
		cimMercbantQRAddlInfo.setPurposeOfTransaction(merchantQRgenerator.getPurpose_of_tran());
		cimMercbantQRAddlInfo.setAddlDataRequest(merchantQRgenerator.getAdditional_details());

		cimMerchantQRcodeRequest.setAdditionalDataInformation(cimMercbantQRAddlInfo);

		logger.info("cimMerchantQRcodeRequest" + cimMerchantQRcodeRequest.toString());
		CimMerchantResponse merchantQRResponse = ipsConnection.createMerchantQRConnection(psuDeviceID, psuIpAddress,
				psuID, cimMerchantQRcodeRequest, p_id, channelID, acct_num, resvfield2,user_id,unit_id,terminal_id);
		String QrImg;
		String imageAsBase64 = null;

		if (merchantQRResponse.getBase64QR() != null) {
			QrImg = merchantQRResponse.getBase64QR();
			imageAsBase64 = "data:image/png;base64," + QrImg;

		} else {
			if (merchantQRResponse.getBase64QR() == null) {

				imageAsBase64 = "Something went wrong at server end";
			}
		}

		merchantQRResponse.setBase64QR(imageAsBase64);

		return new ResponseEntity<>(merchantQRResponse, HttpStatus.OK);
	}

	@PostMapping(path = "/api/ws/StaticUpi", produces = "application/json", consumes = "application/json")
	public ResponseEntity<CimMerchantResponse> genMerchantUPIQRcode(
			@RequestHeader(value = "P-ID", required = false) String p_id,
			@RequestHeader(value = "PSU-Device-ID", required = false) String psuDeviceID,
			@RequestHeader(value = "PSU-IP-Address", required = false) String psuIpAddress,
			@RequestHeader(value = "PSU-ID", required = false) String psuID,
			@RequestHeader(value = "PSU-Channel", required = true) String channelID,
			@RequestHeader(value = "Merchant_ID", required = true) String acct_num,
			@RequestHeader(value = "PSU-Resv-Field2", required = false) String resvfield2,
			@RequestHeader(value = "User_ID", required = true) String user_id,
			@RequestHeader(value = "Unit_ID", required = true) String unit_id,
			@RequestHeader(value = "Terminal_ID", required = true) String terminal_id)
			throws DatatypeConfigurationException, JAXBException, KeyManagementException, UnrecoverableKeyException,
			KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException, WriterException {

		logger.info("Service Starts generate QR Code");
		MerchantMaster ms = merchantmasterRep.findByIdCustom(acct_num);

		QRUrlGlobalEntity merchantQRgenerator = new QRUrlGlobalEntity();
		merchantQRgenerator.setVers(ms.getVersion());
		merchantQRgenerator.setModes(ms.getModes());
		merchantQRgenerator.setPurpose(ms.getPurpose());
		merchantQRgenerator.setOrgid(ms.getOrgid());
		merchantQRgenerator.setTid(ms.getTid());
		merchantQRgenerator.setTr(ms.getTr());
		merchantQRgenerator.setTn(ms.getTn());
		merchantQRgenerator.setPa(ms.getPa());
		merchantQRgenerator.setPn(ms.getMerchant_name());
		merchantQRgenerator.setMc(new BigDecimal(ms.getMerchant_cat_code()));
		merchantQRgenerator.setMid(ms.getMerchant_id());
		merchantQRgenerator.setMsid(ms.getMsid());
		merchantQRgenerator.setMtid(ms.getMtid());
		merchantQRgenerator.setTid(ms.getTid());
		merchantQRgenerator.setCcs("MU");
		merchantQRgenerator.setMtype(ms.getMerchant_type());
		merchantQRgenerator.setMgr("OFFLINE");
		merchantQRgenerator.setMerchant_onboarding("BANK");
		merchantQRgenerator.setMerchant_location(ms.getMerchant_city());
		merchantQRgenerator.setBrand("TEST");
		merchantQRgenerator.setTipsorconv(ms.getTip_or_conv_indicator());
		merchantQRgenerator.setTips_value(ms.getTip_or_conv_indicator());
		merchantQRgenerator.setCov_fee_type(ms.getConv_fees_type());
		merchantQRgenerator.setVal_con_fee(ms.getValue_conv_fees());
		if (ms.getTran_amount() != null) {
			merchantQRgenerator.setBam(new BigDecimal(ms.getTran_amount()));
			merchantQRgenerator.setAm(new BigDecimal(ms.getTran_amount()));
		}
		merchantQRgenerator.setCu(ms.getCurr());
		merchantQRgenerator.setInvoiceno(ms.getInvoiceno());
		merchantQRgenerator.setInvoicedate(ms.getInvoicedate());
		merchantQRgenerator.setQrexpire(ms.getQrexpire());
		merchantQRgenerator.setQrmedium(ms.getQrmedium());

		String qrcode = createQrcode(merchantQRgenerator, "");

		Hashtable<EncodeHintType, ErrorCorrectionLevel> hintMap = new Hashtable<>();
		hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
		
		
		QRCodeWriter qrCodeWriter = new QRCodeWriter();
		BitMatrix byteMatrix = qrCodeWriter.encode(qrcode, BarcodeFormat.QR_CODE, 1500, 2000, hintMap);
		// Make the BufferedImage that are to hold the QRCode
		int matrixWidth = byteMatrix.getWidth();
		int matrixHeight = byteMatrix.getHeight();
		BufferedImage image = new BufferedImage(matrixWidth, matrixHeight, BufferedImage.TYPE_INT_RGB);
		image.createGraphics();

		Graphics2D graphics = (Graphics2D) image.getGraphics();
		graphics.setColor(Color.WHITE);
		graphics.fillRect(0, 0, matrixWidth, matrixHeight);
		graphics.setColor(Color.BLACK);

		BufferedImage logoimage = ImageIO.read(this.getClass().getResourceAsStream("/static/Image/NPCI_UPI_QR.png"));

		logoimage.createGraphics();
		Graphics2D graphi = (Graphics2D) logoimage.getGraphics();
		graphi.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC, 1f));

		ByteArrayOutputStream output = new ByteArrayOutputStream();

		for (int i = 0; i < matrixWidth; i++) {
			for (int j = 0; j < matrixHeight; j++) {
				if (byteMatrix.get(i, j)) {
					graphics.fillRect(i, j, 1, 1);
				}
			}
		}
		graphi.drawImage(image, 650, 1100, matrixWidth + 300, matrixHeight + 200, null);
		graphi.setFont(new Font("Lucida Calligraphy", Font.BOLD, 250));
		graphi.setColor(Color.BLACK);
		FontMetrics fm = graphi.getFontMetrics();
		String[] displayTextQR = { merchantQRgenerator.getPn(), "" };
		//Integer width = 350;
		Integer height = 6500;
		int startingYposition = height + 5;
		for (String displayText : displayTextQR) {
			graphi.drawString(displayText, (image.getWidth()) - (fm.stringWidth(displayText) / 2), startingYposition);
			// graphi.drawString(displayText, 500,6500);
			startingYposition += 20;
		}

		//BufferedImage BOB_LOGO = ImageIO.read(this.getClass().getResourceAsStream("/static/Image/CIM_logo.png"));
		//graphi.drawImage(BOB_LOGO, 1250, 1905, 500, 500, null);

		ImageIO.write(logoimage, "png", output);
		String QrImg = Base64.getEncoder().encodeToString(output.toByteArray());

		String imageAsBase64 = null;
		imageAsBase64 = "data:image/png;base64," + QrImg;

		logger.info("imageAsBase64 :" + imageAsBase64);
		CimMerchantResponse str = new CimMerchantResponse();
		str.setBase64QR(imageAsBase64);

		return new ResponseEntity<>(str, HttpStatus.OK);
	}

	public String createQrcode(QRUrlGlobalEntity qrcodeen, String userid) {
		
		String initenturl = "";
		initenturl += "upiGlobal://pay?ver=" + qrcodeen.getVers() + "&mode=" + qrcodeen.getModes() + "&purpose="+ qrcodeen.getPurpose();
		if (qrcodeen.getOrgid() != null) {
			initenturl += "&orgid=" + qrcodeen.getOrgid();
		}
		if (qrcodeen.getTr() != null) {
			initenturl += "&tr=" + qrcodeen.getTr();
		}
		if (qrcodeen.getTn() != null) {
			initenturl += "&tn=" + qrcodeen.getTn();
		}
		if (qrcodeen.getCategorys() != null) {
			initenturl += "&category=" + qrcodeen.getCategorys();
		}
		if (qrcodeen.getUrls() != null) {
			initenturl += "&url=" + qrcodeen.getUrls();
		}
		if (qrcodeen.getPa() != null) {
			initenturl += "&pa=" + qrcodeen.getPa();
		}
		if (qrcodeen.getPn() != null) {
			initenturl += "&pn=" + qrcodeen.getPn();
		}
		if (qrcodeen.getMc() != null) {
			initenturl += "&mc=" + qrcodeen.getMc();
		}
		if (qrcodeen.getMid() != null) {
			initenturl += "&mid=" + qrcodeen.getMid();
		}
		if (qrcodeen.getMsid() != null) {
			initenturl += "&msid=" + qrcodeen.getMsid();
		}
		if (qrcodeen.getMtid() != null) {
			initenturl += "&mtid=" + qrcodeen.getMtid();
		}
		if (qrcodeen.getMtype() != null) {
			initenturl += "&mType=" + qrcodeen.getMtype();
		}
		if (qrcodeen.getMgr() != null) {
			initenturl += "&mGr=" + qrcodeen.getMgr();
		}
		if (qrcodeen.getMerchant_onboarding() != null) {
			initenturl += "&mOnboarding=" + qrcodeen.getMerchant_onboarding();
		}
		if (qrcodeen.getMerchant_location() != null) {
			initenturl += "&mLoc=" + qrcodeen.getMerchant_location();
		}
		if (qrcodeen.getBrand() != null) {
			initenturl += "&brand=" + qrcodeen.getBrand();
		}
		if (qrcodeen.getTipsorconv().equals("01")) {
			initenturl += "&enTips=Y";
		}
		if (qrcodeen.getTipsorconv().equals("02")) {
			if (qrcodeen.getCov_fee_type().equals("Fixed")) {
				initenturl += "&split=CONFEE:" + qrcodeen.getVal_con_fee();
			} else if (qrcodeen.getCov_fee_type().equals("Percentage")) {
				initenturl += "&split=CONPCT:" + qrcodeen.getVal_con_fee();
			}
		}
		if (!qrcodeen.getModes().equals("15")) {
			initenturl += "&am=" + qrcodeen.getAm();
		}
		if (!qrcodeen.getCcs().equals(null)) {
			initenturl += "&cc=" + qrcodeen.getCcs();
		}
		if (qrcodeen.getCurr() != null) {
			initenturl += "&cu=" + qrcodeen.getCurr();
		}
		if (qrcodeen.getQrmedium() != null) {
			initenturl += "&qrMedium=" + qrcodeen.getQrmedium();
		}
		if (qrcodeen.getInvoiceno() != null) {
			initenturl += "&invoiceNo=" + qrcodeen.getInvoiceno();
		}
		if (qrcodeen.getInvoicedate() != null) {
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss+04:00");
			initenturl += "&invoiceDate=" + dateFormat.format(qrcodeen.getInvoicedate());
		}
		if (qrcodeen.getQrexpire() != null) {
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss+04:00");
			initenturl += "&QRexpire=" + dateFormat.format(qrcodeen.getQrexpire());
		}
		String without = initenturl.replace(" ", "%20");
		return without;
	}

}
