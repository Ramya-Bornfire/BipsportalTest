package com.bornfire.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;

import javax.imageio.ImageIO;
import javax.xml.bind.JAXBException;
import javax.xml.datatype.DatatypeConfigurationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.core.task.TaskExecutor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import com.bornfire.config.ErrorResponseCode;
import com.bornfire.config.Listener;
import com.bornfire.config.SequenceGenerator;
import com.bornfire.entity.CIMCustomerDecodeQRFormatResponse;
import com.bornfire.entity.CIMCustomerDecodeQRMerchantAcctInfo;
import com.bornfire.entity.CIMCustomerQRcodeRequest;
import com.bornfire.entity.CIMMerchantDecodeQRCustomerAddlInfo;
import com.bornfire.entity.CIMMerchantDecodeQRFormatResponse;
import com.bornfire.entity.CIMMerchantDecodeQRMerchantAcctInfo;
import com.bornfire.entity.CIMMerchantDecodeQRMerchantAddlInfo;
import com.bornfire.entity.CIMMerchantDirectFndRequest;
import com.bornfire.entity.CIMMerchantQRRequestFormat;
import com.bornfire.entity.CIMMerchantQRcodeRequest;
import com.bornfire.entity.CimCBSrequest;
import com.bornfire.entity.CimCBSrequestData;
import com.bornfire.entity.CimCBSrequestHeader;
import com.bornfire.entity.CimCBSresponse;
import com.bornfire.entity.CimMerchantResponse;
import com.bornfire.entity.EncodeQRFormatResponse;
import com.bornfire.entity.MCCreditTransferResponse;
import com.bornfire.entity.TranCimCBSTable;
import com.bornfire.entity.TranCimCBSTableRep;
import com.bornfire.entity.TranMonitorStatus;
import com.bornfire.exception.IPSXException;
import com.bornfire.qrcode.core.isos.Currency;
import com.bornfire.qrcode.decoder.mpm.DecoderMpm;
import com.bornfire.qrcode.model.mpm.AdditionalDataField;
import com.bornfire.qrcode.model.mpm.AdditionalDataFieldTemplate;
import com.bornfire.qrcode.model.mpm.MerchantAccountInformationReservedAdditional;
import com.bornfire.qrcode.model.mpm.MerchantAccountInformationTemplate;
import com.bornfire.qrcode.model.mpm.MerchantPresentedMode;
import com.bornfire.qrcode.validators.Crc16Validate;
import com.bornfire.qrcode.validators.MerchantPresentedModeValidate;
import com.bornfire.valid.context.ValidationResult;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

@Component
public class IPSConnection {
	@Autowired
	IpsDao ipsDao;

	@Autowired
	SequenceGenerator sequence;

	@Autowired
	Environment env;

	@Autowired
	Listener listener;

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	TranCimCBSTableRep tranCimCBSTableRep;

	@Autowired
	ErrorResponseCode errorCode;

	@Autowired
	@Qualifier("taskExecutor")
	TaskExecutor taskExecutor;

	String qrCodeImage;

	private EncodeQRFormatResponse encodeQRCodeFormat(CIMMerchantQRcodeRequest qrReuest) {

		EncodeQRFormatResponse response = new EncodeQRFormatResponse();

		final MerchantPresentedMode merchantPresentMode = new MerchantPresentedMode();

		/// Pay load Format Indicator(00)
		merchantPresentMode.setPayloadFormatIndicator(qrReuest.getPayloadFormatIndiator());
		// Point Of Initiation Method(01)
		if (!String.valueOf(qrReuest.getPointOfInitiationFormat()).equals("null")
				&& !String.valueOf(qrReuest.getPointOfInitiationFormat()).equals("")) {
			merchantPresentMode.setPointOfInitiationMethod(qrReuest.getPointOfInitiationFormat());
		}

		/// Merchant Account Information(26)
		final MerchantAccountInformationReservedAdditional merchantAccountInformationValue = new MerchantAccountInformationReservedAdditional();
		merchantAccountInformationValue
				.setGloballyUniqueIdentifier(qrReuest.getMerchantAcctInformation().getGlobalID());
		merchantAccountInformationValue
				.setPayeeParticipantCode(qrReuest.getMerchantAcctInformation().getPayeeParticipantCode());
		merchantAccountInformationValue
				.setMerchantAccountNumber(qrReuest.getMerchantAcctInformation().getMerchantAcctNumber());
		merchantAccountInformationValue.setMerchantID(qrReuest.getMerchantAcctInformation().getMerchantID());
		final MerchantAccountInformationTemplate merchanAccountInformationReservedAdditional = new MerchantAccountInformationTemplate(
				"26", merchantAccountInformationValue);
		merchantPresentMode.addMerchantAccountInformation(merchanAccountInformationReservedAdditional);

		//// Merchant Category Code(52)
		merchantPresentMode.setMerchantCategoryCode(qrReuest.getMCC());

		/// Merchant Transaction Currency
		merchantPresentMode.setTransactionCurrency(Currency.entryOf(qrReuest.getCurrency()).getNumber());

		//// Transaction Amount(optional)(54)
		if (!String.valueOf(qrReuest.getTrAmt()).equals("null") && !String.valueOf(qrReuest.getTrAmt()).equals("")) {
			merchantPresentMode.setTransactionAmount(qrReuest.getTrAmt());
		}

		if (!String.valueOf(qrReuest.getTipOrConvenienceIndicator()).equals("null")
				&& !String.valueOf(qrReuest.getTipOrConvenienceIndicator()).equals("")) {

			if (String.valueOf(qrReuest.getTipOrConvenienceIndicator()).equals("01")) {

				//// Tip or Convenience Indicator(55)
				merchantPresentMode.setTipOrConvenienceIndicator("01");

			} else if (String.valueOf(qrReuest.getTipOrConvenienceIndicator()).equals("02")) {

				//// Tip or Convenience Indicator(55)
				merchantPresentMode.setTipOrConvenienceIndicator("02");

				/// Convenience Indicator Fee Fixed(56)
				merchantPresentMode.setValueOfConvenienceFeeFixed(qrReuest.getConvenienceIndicatorFee());

			} else if (String.valueOf(qrReuest.getTipOrConvenienceIndicator()).equals("03")) {

				//// Tip or Convenience Indicator(55)
				merchantPresentMode.setTipOrConvenienceIndicator("03");

				//// Convenience Indicator Fee Percentage(57)
				merchantPresentMode.setValueOfConvenienceFeePercentage(qrReuest.getConvenienceIndicatorFee());
			}
		}

		//// Country Code (58)
		merchantPresentMode.setCountryCode(qrReuest.getCountryCode());

		//// Merchant Name(59)
		merchantPresentMode.setMerchantName(qrReuest.getMerchantName());

		//// Merchant City(60)
		merchantPresentMode.setMerchantCity(qrReuest.getCity());

		//// Postal Code (61)
		if (!String.valueOf(qrReuest.getPostalCode()).equals("null")
				&& !String.valueOf(qrReuest.getPostalCode()).equals("")) {
			merchantPresentMode.setPostalCode(qrReuest.getPostalCode());
		}

		if (qrReuest.getAdditionalDataInformation() != null) {

			if ((!String.valueOf(qrReuest.getAdditionalDataInformation().getBillNumber()).equals("null")
					&& !String.valueOf(qrReuest.getAdditionalDataInformation().getBillNumber()).equals(""))
					|| (!String.valueOf(qrReuest.getAdditionalDataInformation().getMobileNumber()).equals("null")
							&& !String.valueOf(qrReuest.getAdditionalDataInformation().getMobileNumber()).equals(""))
					|| (!String.valueOf(qrReuest.getAdditionalDataInformation().getStoreLabel()).equals("null")
							&& !String.valueOf(qrReuest.getAdditionalDataInformation().getStoreLabel()).equals(""))
					|| (!String.valueOf(qrReuest.getAdditionalDataInformation().getLoyaltyNumber()).equals("null")
							&& !String.valueOf(qrReuest.getAdditionalDataInformation().getLoyaltyNumber()).equals(""))
					|| (!String.valueOf(qrReuest.getAdditionalDataInformation().getCustomerLabel()).equals("null")
							&& !String.valueOf(qrReuest.getAdditionalDataInformation().getCustomerLabel()).equals(""))
					|| (!String.valueOf(qrReuest.getAdditionalDataInformation().getTerminalLabel()).equals("null")
							&& !String.valueOf(qrReuest.getAdditionalDataInformation().getTerminalLabel()).equals(""))
					|| (!String.valueOf(qrReuest.getAdditionalDataInformation().getAddlDataRequest()).equals("null")
							&& !String.valueOf(qrReuest.getAdditionalDataInformation().getAddlDataRequest()).equals(""))
					|| (!String.valueOf(qrReuest.getAdditionalDataInformation().getReferenceLabel()).equals("null")
							&& !String.valueOf(qrReuest.getAdditionalDataInformation().getReferenceLabel()).equals(""))
					|| (!String.valueOf(qrReuest.getAdditionalDataInformation().getPurposeOfTransaction())
							.equals("null")
							&& !String.valueOf(qrReuest.getAdditionalDataInformation().getPurposeOfTransaction())
									.equals(""))) {

				//// Additional Data Information(62)
				final AdditionalDataField additionalDataFieldValue = new AdditionalDataField();

				if (!String.valueOf(qrReuest.getAdditionalDataInformation().getBillNumber()).equals("null")
						&& !String.valueOf(qrReuest.getAdditionalDataInformation().getBillNumber()).equals("")) {
					additionalDataFieldValue.setBillNumber(qrReuest.getAdditionalDataInformation().getBillNumber());
				}
				if (!String.valueOf(qrReuest.getAdditionalDataInformation().getMobileNumber()).equals("null")
						&& !String.valueOf(qrReuest.getAdditionalDataInformation().getMobileNumber()).equals("")) {
					additionalDataFieldValue.setMobileNumber(qrReuest.getAdditionalDataInformation().getMobileNumber());
				}
				if (!String.valueOf(qrReuest.getAdditionalDataInformation().getReferenceLabel()).equals("null")
						&& !String.valueOf(qrReuest.getAdditionalDataInformation().getReferenceLabel()).equals("")) {
					additionalDataFieldValue
							.setReferenceLabel(qrReuest.getAdditionalDataInformation().getReferenceLabel());

				}

				if (!String.valueOf(qrReuest.getAdditionalDataInformation().getPurposeOfTransaction()).equals("null")
						&& !String.valueOf(qrReuest.getAdditionalDataInformation().getPurposeOfTransaction())
								.equals("")) {
					additionalDataFieldValue
							.setPurposeTransaction(qrReuest.getAdditionalDataInformation().getPurposeOfTransaction());

				}

				if (!String.valueOf(qrReuest.getAdditionalDataInformation().getStoreLabel()).equals("null")
						&& !String.valueOf(qrReuest.getAdditionalDataInformation().getStoreLabel()).equals("")) {
					additionalDataFieldValue.setStoreLabel(qrReuest.getAdditionalDataInformation().getStoreLabel());

				}

				if (!String.valueOf(qrReuest.getAdditionalDataInformation().getLoyaltyNumber()).equals("null")
						&& !String.valueOf(qrReuest.getAdditionalDataInformation().getLoyaltyNumber()).equals("")) {
					additionalDataFieldValue
							.setLoyaltyNumber(qrReuest.getAdditionalDataInformation().getLoyaltyNumber());

				}

				if (!String.valueOf(qrReuest.getAdditionalDataInformation().getCustomerLabel()).equals("null")
						&& !String.valueOf(qrReuest.getAdditionalDataInformation().getCustomerLabel()).equals("")) {
					additionalDataFieldValue
							.setCustomerLabel(qrReuest.getAdditionalDataInformation().getCustomerLabel());

				}

				if (!String.valueOf(qrReuest.getAdditionalDataInformation().getTerminalLabel()).equals("null")
						&& !String.valueOf(qrReuest.getAdditionalDataInformation().getTerminalLabel()).equals("")) {
					// System.out.println(qrReuest.getAdditionalDataInformation().getTerminalLabel());
					additionalDataFieldValue
							.setTerminalLabel(qrReuest.getAdditionalDataInformation().getTerminalLabel());

				}

				if (!String.valueOf(qrReuest.getAdditionalDataInformation().getAddlDataRequest()).equals("null")
						&& !String.valueOf(qrReuest.getAdditionalDataInformation().getAddlDataRequest()).equals("")) {
					additionalDataFieldValue.setAdditionalConsumerDataRequest(
							qrReuest.getAdditionalDataInformation().getAddlDataRequest());

				}

				final AdditionalDataFieldTemplate additionalDataField = new AdditionalDataFieldTemplate();
				additionalDataField.setValue(additionalDataFieldValue);

				merchantPresentMode.setAdditionalDataField(additionalDataField);

			}

		}

		final ValidationResult validationResult = MerchantPresentedModeValidate.validate(merchantPresentMode);
		// System.out.println("Errorrrr----------->" +
		// validationResult.getErrors().toString());

		if (validationResult.isValid()) {
			response.setSuccess(true);
			response.setQrMsg(merchantPresentMode.toString());
		} else {
			response.setSuccess(false);
			List<String> desc = new ArrayList<>();
			Collection<com.bornfire.valid.context.Error> error = validationResult.getErrors();
			for (com.bornfire.valid.context.Error elem : error) {
				desc.add(elem.getMessage());
				break;
			}
			response.setError_desc(desc);
		}
		// System.out.println("Response----------->" + merchantPresentMode.toString());

		return response;

	}

	public CIMMerchantDecodeQRFormatResponse getMerchantQRdata(String psuDeviceID, String psuIpAddress, String psuID,
			CIMMerchantQRRequestFormat cimQRFormatData, String p_id, String channelID, String resvfield1,
			String resvfield2) {
		// System.out.println("cimQRFormatData--------------->" + cimQRFormatData);
		final ValidationResult validationResult = Crc16Validate.validate(cimQRFormatData.getQrCode());
		String crccheck = env.getProperty("ipsx.crccheck");
		Boolean validationResultb = null;
		if (crccheck.equals("true")) {
			validationResultb = validationResult.isValid();
		} else {
			validationResultb = true;
		}

		if (validationResultb.equals(true)) {
			final MerchantPresentedMode merchantPresentedMode = DecoderMpm.decode(cimQRFormatData.getQrCode(),
					MerchantPresentedMode.class);

			CIMMerchantDecodeQRFormatResponse response = new CIMMerchantDecodeQRFormatResponse();

			response.setPayloadFormatIndiator(merchantPresentedMode.getPayloadFormatIndicator().getValue());

			if (!String.valueOf(merchantPresentedMode.getPointOfInitiationMethod()).equals("null")
					&& !String.valueOf(merchantPresentedMode.getPointOfInitiationMethod()).equals("")) {
				response.setPointOfInitiationFormat(merchantPresentedMode.getPointOfInitiationMethod().getValue());
			}

			Map<String, MerchantAccountInformationTemplate> merTemplate = merchantPresentedMode
					.getMerchantAccountInformation();

			CIMMerchantDecodeQRMerchantAcctInfo acctInfo = new CIMMerchantDecodeQRMerchantAcctInfo();

			for (final Entry<String, MerchantAccountInformationTemplate> entry : merTemplate.entrySet()) {
				// System.out.println(entry.getValue().getValue());
				MerchantAccountInformationReservedAdditional ss = (MerchantAccountInformationReservedAdditional) entry
						.getValue().getValue();

				if (!String.valueOf(ss.getGloballyUniqueIdentifier()).equals("null")
						&& !String.valueOf(ss.getGloballyUniqueIdentifier()).equals("")) {
					acctInfo.setGlobalID(ss.getGloballyUniqueIdentifier().getValue());
				}

				if (!String.valueOf(ss.getPayeeParticipantCode()).equals("null")
						&& !String.valueOf(ss.getPayeeParticipantCode()).equals("")) {
					acctInfo.setPayeeParticipantCode(ss.getPayeeParticipantCode().getValue());
				}

				if (!String.valueOf(ss.getMerchantAccountNumber()).equals("null")
						&& !String.valueOf(ss.getMerchantAccountNumber()).equals("")) {
					acctInfo.setMerchantAcctNumber(ss.getMerchantAccountNumber().getValue());
				}

				if (!String.valueOf(ss.getMerchantID()).equals("null")
						&& !String.valueOf(ss.getMerchantID()).equals("")) {

					if (!String.valueOf(merchantPresentedMode.getMerchantCategoryCode()).equals("null")
							&& !String.valueOf(merchantPresentedMode.getMerchantCategoryCode()).equals("")) {
						acctInfo.setMerchantID(ss.getMerchantID().getValue());
					}

					acctInfo.setReserveField(ss.getMerchantID().getValue());
				}

				response.setMerchantAcctInformation(acctInfo);

			}

			if (!String.valueOf(merchantPresentedMode.getMerchantCategoryCode()).equals("null")
					&& !String.valueOf(merchantPresentedMode.getMerchantCategoryCode()).equals("")) {
				response.setMCC(merchantPresentedMode.getMerchantCategoryCode().getValue());
			}

			if (!String.valueOf(merchantPresentedMode.getTransactionCurrency()).equals("null")
					&& !String.valueOf(merchantPresentedMode.getTransactionCurrency()).equals("")) {

				response.setCurrency(
						Currency.entryOf1(merchantPresentedMode.getTransactionCurrency().getValue()).getCode());
			}

			// //System.out.println(merchantPresentedMode.getTransactionAmount().getValue());
			if (!String.valueOf(merchantPresentedMode.getTransactionAmount()).equals("null")
					&& !String.valueOf(merchantPresentedMode.getTransactionAmount()).equals("")) {
				response.setTrAmt(merchantPresentedMode.getTransactionAmount().getValue());
			}

			if (!String.valueOf(merchantPresentedMode.getTipOrConvenienceIndicator()).equals("null")
					&& !String.valueOf(merchantPresentedMode.getTipOrConvenienceIndicator()).equals("")) {
				if (merchantPresentedMode.getTipOrConvenienceIndicator().getValue().equals("01")) {
					response.setTipOrConvenienceIndicator("01");
				} else if (merchantPresentedMode.getTipOrConvenienceIndicator().getValue().equals("02")) {
					response.setTipOrConvenienceIndicator("02");
					response.setConvenienceIndicatorFee(
							merchantPresentedMode.getValueOfConvenienceFeeFixed().getValue());
				} else if (merchantPresentedMode.getTipOrConvenienceIndicator().getValue().equals("03")) {
					response.setTipOrConvenienceIndicator("03");
					response.setConvenienceIndicatorFee(
							merchantPresentedMode.getValueOfConvenienceFeePercentage().getValue());
				} else {
					// response.setConvenienceIndicator(false);
				}
			}

			response.setCountryCode(merchantPresentedMode.getCountryCode().getValue());
			response.setMerchantName(merchantPresentedMode.getMerchantName().getValue());
			// response.setCity(merchantPresentedMode.getMerchantCity().getValue());
			if (!String.valueOf(merchantPresentedMode.getMerchantCity()).equals("null")
					&& !String.valueOf(merchantPresentedMode.getMerchantCity()).equals("")) {
				response.setCity(merchantPresentedMode.getMerchantCity().getValue());
			}
			if (!String.valueOf(merchantPresentedMode.getPostalCode()).equals("null")
					&& !String.valueOf(merchantPresentedMode.getPostalCode()).equals("")) {
				response.setPostalCode(merchantPresentedMode.getPostalCode().getValue());
			}

			if (merchantPresentedMode.getAdditionalDataField() != null) {
				CIMMerchantDecodeQRMerchantAddlInfo addInfo = new CIMMerchantDecodeQRMerchantAddlInfo();
				AdditionalDataFieldTemplate merAddTemplate = merchantPresentedMode.getAdditionalDataField();

				if (!String.valueOf(merAddTemplate.getValue().getBillNumber()).equals("null")
						&& !String.valueOf(merAddTemplate.getValue().getBillNumber()).equals("")) {
					addInfo.setBillNumber(merAddTemplate.getValue().getBillNumber().getValue());
				}
				if (!String.valueOf(merAddTemplate.getValue().getMobileNumber()).equals("null")
						&& !String.valueOf(merAddTemplate.getValue().getMobileNumber()).equals("")) {
					addInfo.setMobileNumber(merAddTemplate.getValue().getMobileNumber().getValue());
				}
				if (!String.valueOf(merAddTemplate.getValue().getStoreLabel()).equals("null")
						&& !String.valueOf(merAddTemplate.getValue().getStoreLabel()).equals("")) {
					addInfo.setStoreLabel(merAddTemplate.getValue().getStoreLabel().getValue());
				}
				if (!String.valueOf(merAddTemplate.getValue().getLoyaltyNumber()).equals("null")
						&& !String.valueOf(merAddTemplate.getValue().getLoyaltyNumber()).equals("")) {
					addInfo.setLoyaltyNumber(merAddTemplate.getValue().getLoyaltyNumber().getValue());
				}
				if (!String.valueOf(merAddTemplate.getValue().getReferenceLabel()).equals("null")
						&& !String.valueOf(merAddTemplate.getValue().getReferenceLabel()).equals("")) {
					addInfo.setReferenceLabel(merAddTemplate.getValue().getReferenceLabel().getValue());
				}
				if (!String.valueOf(merAddTemplate.getValue().getCustomerLabel()).equals("null")
						&& !String.valueOf(merAddTemplate.getValue().getCustomerLabel()).equals("")) {
					addInfo.setCustomerLabel(merAddTemplate.getValue().getCustomerLabel().getValue());
				}
				if (!String.valueOf(merAddTemplate.getValue().getTerminalLabel()).equals("null")
						&& !String.valueOf(merAddTemplate.getValue().getTerminalLabel()).equals("")) {
					addInfo.setTerminalLabel(merAddTemplate.getValue().getTerminalLabel().getValue());
				}
				if (!String.valueOf(merAddTemplate.getValue().getPurposeTransaction()).equals("null")
						&& !String.valueOf(merAddTemplate.getValue().getPurposeTransaction()).equals("")) {
					addInfo.setPurposeOfTransaction(merAddTemplate.getValue().getPurposeTransaction().getValue());
				}
				if (!String.valueOf(merAddTemplate.getValue().getAdditionalConsumerDataRequest()).equals("null")
						&& !String.valueOf(merAddTemplate.getValue().getAdditionalConsumerDataRequest()).equals("")) {
					addInfo.setAddlDataRequest(merAddTemplate.getValue().getAdditionalConsumerDataRequest().getValue());
				}

				response.setAdditionalDataInformation(addInfo);
			}

			return response;

		} else {
			// System.out.println(validationResult.getErrors().toString());
			throw new IPSXException(errorCode.validationError("BIPS18"));
		}

	}

	public MCCreditTransferResponse createMerchantFTConnection(String psuDeviceID, String psuIpAddress, String psuID,
			CIMMerchantDirectFndRequest mcCreditTransferRequest, String p_id, String userid, String unit_id,
			String unit_name, String channelID, String resvField1, String resvField2)
			throws DatatypeConfigurationException, JAXBException, KeyManagementException, UnrecoverableKeyException,
			KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException {

		MCCreditTransferResponse mcCreditTransferResponse = null;

		///// Generate Sequence Unique ID
		String seqUniqueID = sequence.generateSeqUniqueID();
		///// Generate Bob Msg ID
		String cimMsgID = seqUniqueID;
		///// Generate SystemTraceAuditNumber or CBS Tran Number
		String sysTraceNumber = sequence.generateSystemTraceAuditNumber();
		//// Generate Msg Sequence
		String msgSeq = sequence.generateMsgSequence();
		///// Generate EndToEnd ID
		String endTOEndID = env.getProperty("ipsx.bicfi") + new SimpleDateFormat("yyyyMMdd").format(new Date())
				+ msgSeq;
		///// Net Mir
		String msgNetMir = new SimpleDateFormat("yyMMdd").format(new Date()) + env.getProperty("ipsx.user") + "0001"
				+ msgSeq;

		// System.out.println("Transaction cycle starts");
		// System.out.println("System Trace Audit Number" + sysTraceNumber);
		// System.out.println("System Sequence ID" + cimMsgID);

		// System.out.println("Register Initial outgoing Fund Transfer Record");

		/// Purpose Code for Peer to Peer Connection

		///// Starting Background Service
		taskExecutor.execute(new Runnable() {
			@Override
			public void run() {

				String lclInstrm = TranMonitorStatus.CSDC.toString();
				String ctgyPurp = "300";
				String tot_tran_amount = mcCreditTransferRequest.getMerchantAccount().getTrAmt();

				//// Remarks
				String remarks = "";
				//// Retrieve Remittence Information
				StringBuilder remInfo = new StringBuilder();
				remInfo.append("/QR/" + mcCreditTransferRequest.getMerchantAccount().getGlobalID() + "//");
				if (!String.valueOf(mcCreditTransferRequest.getMerchantAccount().getPointOfInitiationFormat())
						.equals("null")
						&& !String.valueOf(mcCreditTransferRequest.getMerchantAccount().getPointOfInitiationFormat())
								.equals("")) {
					remInfo.append(
							"01/" + mcCreditTransferRequest.getMerchantAccount().getPointOfInitiationFormat() + "/");
				}
				if (!String.valueOf(mcCreditTransferRequest.getMerchantAccount().getTipOrConvenienceIndicator())
						.equals("null")
						&& !String.valueOf(mcCreditTransferRequest.getMerchantAccount().getTipOrConvenienceIndicator())
								.equals("")) {

					if (String.valueOf(mcCreditTransferRequest.getMerchantAccount().getTipOrConvenienceIndicator())
							.equals("02")) {
						remInfo.append("55/02/56/"
								+ mcCreditTransferRequest.getMerchantAccount().getConvenienceIndicatorFee() + "/");

						Double sumData = Double
								.parseDouble(mcCreditTransferRequest.getMerchantAccount().getConvenienceIndicatorFee())
								+ (Double.parseDouble(mcCreditTransferRequest.getMerchantAccount().getTrAmt()));
						tot_tran_amount = sumData.toString();
					} else if (String
							.valueOf(mcCreditTransferRequest.getMerchantAccount().getTipOrConvenienceIndicator())
							.equals("03")) {
						remInfo.append("55/03/57/"
								+ mcCreditTransferRequest.getMerchantAccount().getConvenienceIndicatorFee() + "/");

						Double convFee = (((Double.parseDouble(mcCreditTransferRequest.getMerchantAccount().getTrAmt()))
								* (Double.parseDouble(
										mcCreditTransferRequest.getMerchantAccount().getConvenienceIndicatorFee())))
								/ 100);
						Double sumData = convFee
								+ (Double.parseDouble(mcCreditTransferRequest.getMerchantAccount().getTrAmt()));
						tot_tran_amount = sumData.toString();

					} else if (String
							.valueOf(mcCreditTransferRequest.getMerchantAccount().getTipOrConvenienceIndicator())
							.equals("01")) {
						// remInfo.append("55/01/57/"+mcCreditTransferRequest.getMerchantAccount().getConvenienceIndicatorFee()+"/");
						remInfo.append("55/01/");

						// Double
						// convFee=(((Double.parseDouble(mcCreditTransferRequest.getMerchantAccount().getTrAmt()))*(Double.parseDouble(mcCreditTransferRequest.getMerchantAccount().getConvenienceIndicatorFee())))/100);
						// Double
						// sumData=convFee+(Double.parseDouble(mcCreditTransferRequest.getMerchantAccount().getTrAmt()));
						// tot_tran_amount=sumData.toString();

					}
				}

				if (mcCreditTransferRequest.getAdditionalDataInformation() != null) {
					if ((!String.valueOf(mcCreditTransferRequest.getAdditionalDataInformation().getBillNumber())
							.equals("null")
							&& !String.valueOf(mcCreditTransferRequest.getAdditionalDataInformation().getBillNumber())
									.equals(""))
							|| (!String
									.valueOf(mcCreditTransferRequest.getAdditionalDataInformation().getMobileNumber())
									.equals("null")
									&& !String.valueOf(
											mcCreditTransferRequest.getAdditionalDataInformation().getMobileNumber())
											.equals(""))
							|| (!String
									.valueOf(mcCreditTransferRequest.getAdditionalDataInformation().getReferenceLabel())
									.equals("null")
									&& !String.valueOf(
											mcCreditTransferRequest.getAdditionalDataInformation().getReferenceLabel())
											.equals(""))
							|| (!String.valueOf(mcCreditTransferRequest.getAdditionalDataInformation().getStoreLabel())
									.equals("null")
									&& !String.valueOf(
											mcCreditTransferRequest.getAdditionalDataInformation().getStoreLabel())
											.equals(""))
							|| (!String.valueOf(mcCreditTransferRequest.getAdditionalDataInformation().getDeviceID())
									.equals("null")
									&& !String.valueOf(
											mcCreditTransferRequest.getAdditionalDataInformation().getDeviceID())
											.equals(""))
							|| (!String
									.valueOf(mcCreditTransferRequest.getAdditionalDataInformation().getCustomerLabel())
									.equals("null")
									&& !String.valueOf(
											mcCreditTransferRequest.getAdditionalDataInformation().getCustomerLabel())
											.equals(""))
							|| (!String
									.valueOf(mcCreditTransferRequest.getAdditionalDataInformation().getTerminalLabel())
									.equals("null")
									&& !String.valueOf(
											mcCreditTransferRequest.getAdditionalDataInformation().getTerminalLabel())
											.equals(""))
							|| (!String
									.valueOf(
											mcCreditTransferRequest.getAdditionalDataInformation().getAddlDataRequest())
									.equals("null")
									&& !String.valueOf(
											mcCreditTransferRequest.getAdditionalDataInformation().getAddlDataRequest())
											.equals(""))
							|| (!String.valueOf(
									mcCreditTransferRequest.getAdditionalDataInformation().getPurposeOfTransaction())
									.equals("null")
									&& !String.valueOf(mcCreditTransferRequest.getAdditionalDataInformation()
											.getPurposeOfTransaction()).equals(""))) {

						remInfo.append("62//");

						if (!String.valueOf(mcCreditTransferRequest.getAdditionalDataInformation().getBillNumber())
								.equals("null")
								&& !String
										.valueOf(mcCreditTransferRequest.getAdditionalDataInformation().getBillNumber())
										.equals("")) {
							remInfo.append("01/"
									+ mcCreditTransferRequest.getAdditionalDataInformation().getBillNumber() + "/");
						}
						if (!String.valueOf(mcCreditTransferRequest.getAdditionalDataInformation().getMobileNumber())
								.equals("null")
								&& !String.valueOf(
										mcCreditTransferRequest.getAdditionalDataInformation().getMobileNumber())
										.equals("")) {
							remInfo.append("02/"
									+ mcCreditTransferRequest.getAdditionalDataInformation().getMobileNumber() + "/");
						}
						if (!String.valueOf(mcCreditTransferRequest.getAdditionalDataInformation().getStoreLabel())
								.equals("null")
								&& !String
										.valueOf(mcCreditTransferRequest.getAdditionalDataInformation().getStoreLabel())
										.equals("")) {
							remInfo.append("03/"
									+ mcCreditTransferRequest.getAdditionalDataInformation().getStoreLabel() + "/");
						}
						if (!String.valueOf(mcCreditTransferRequest.getAdditionalDataInformation().getDeviceID())
								.equals("null")
								&& !String.valueOf(mcCreditTransferRequest.getAdditionalDataInformation().getDeviceID())
										.equals("")) {
							remInfo.append(
									"04/" + mcCreditTransferRequest.getAdditionalDataInformation().getDeviceID() + "/");
						}
						if (!String.valueOf(mcCreditTransferRequest.getAdditionalDataInformation().getReferenceLabel())
								.equals("null")
								&& !String.valueOf(
										mcCreditTransferRequest.getAdditionalDataInformation().getReferenceLabel())
										.equals("")) {
							remInfo.append("05/"
									+ mcCreditTransferRequest.getAdditionalDataInformation().getReferenceLabel() + "/");
						}
						if (!String.valueOf(mcCreditTransferRequest.getAdditionalDataInformation().getCustomerLabel())
								.equals("null")
								&& !String.valueOf(
										mcCreditTransferRequest.getAdditionalDataInformation().getCustomerLabel())
										.equals("")) {
							remInfo.append("06/"
									+ mcCreditTransferRequest.getAdditionalDataInformation().getCustomerLabel() + "/");
						}
						if (!String.valueOf(mcCreditTransferRequest.getAdditionalDataInformation().getTerminalLabel())
								.equals("null")
								&& !String.valueOf(
										mcCreditTransferRequest.getAdditionalDataInformation().getTerminalLabel())
										.equals("")) {
							remInfo.append("07/"
									+ mcCreditTransferRequest.getAdditionalDataInformation().getTerminalLabel() + "/");
						}
						if (Objects.nonNull(
								mcCreditTransferRequest.getAdditionalDataInformation().getPurposeOfTransaction())) {
							remInfo.append("08/"
									+ mcCreditTransferRequest.getAdditionalDataInformation().getPurposeOfTransaction()
									+ "/");
							remarks = mcCreditTransferRequest.getAdditionalDataInformation().getPurposeOfTransaction();
						}
						if (Objects
								.nonNull(mcCreditTransferRequest.getAdditionalDataInformation().getAddlDataRequest())) {
							remInfo.append(
									"09/" + mcCreditTransferRequest.getAdditionalDataInformation().getAddlDataRequest()
											+ "/");
						}
					}

				}

				remInfo.append("//RQ/");
				// System.out.println("RemitterInfo->" + remInfo.toString());
				////// Register Data to Master Table

				ipsDao.RegisterMerchantOutgoingMasterRecord(psuDeviceID, psuIpAddress, sysTraceNumber, cimMsgID,
						seqUniqueID, endTOEndID, seqUniqueID, msgNetMir, env.getProperty("ipsx.bicfi"),
						env.getProperty("ipsx.dbtragt"), env.getProperty("ipsx.dbtragtacct"), seqUniqueID, "0100",
						lclInstrm, ctgyPurp, mcCreditTransferRequest.getRemitterAccount().getAcctName(),
						mcCreditTransferRequest.getRemitterAccount().getAcctNumber(),
						mcCreditTransferRequest.getMerchantAccount().getCurrency(),
						mcCreditTransferRequest.getMerchantAccount().getMerchantName(),
						mcCreditTransferRequest.getMerchantAccount().getMerchantAcctNumber(), p_id, tot_tran_amount,
						remarks, p_id, p_id, userid, unit_id, unit_name, channelID, resvField1, resvField2,
						remInfo.toString(), mcCreditTransferRequest);

				//// Generate RequestUUID
				String requestUUID = sequence.generateRequestUUId();

				/********** ESB *********/
				//// Store ESB Registration Data
				//// Register ESB Data
				ipsDao.registerCIMcbsIncomingData(requestUUID, env.getProperty("cimCBS.channelID"),
						env.getProperty("cimCBS.servicereqversion"), env.getProperty("cimCBS.servicereqID"), new Date(),
						sysTraceNumber, env.getProperty("cimCBS.outDBChannel"), "", "True", "DR", "N", "",
						mcCreditTransferRequest.getMerchantAccount().getMerchantAcctNumber(), tot_tran_amount,
						mcCreditTransferRequest.getMerchantAccount().getCurrency(), seqUniqueID,
						mcCreditTransferRequest.getRemitterAccount().getAcctNumber(),
						mcCreditTransferRequest.getRemitterAccount().getAcctName(), "NRT", remarks, "", "", "",
						new Date(), "PAYABLE", "", "", "", "", env.getProperty("ipsx.dbtragt"));

				///// Call ESB Connection
				ResponseEntity<CimCBSresponse> connect24Response = dbtFundRequest(requestUUID);

				// System.out.println("CBS Data:" + connect24Response.toString());
				if (connect24Response != null && connect24Response.getBody() != null
						&& connect24Response.getStatusCode() == HttpStatus.OK) {

					if (!connect24Response.toString().equals("<200 OK OK,[]>")) {
						// System.out.println(seqUniqueID + ": success" +
						// connect24Response.getBody().getStatus().getIsSuccess() + ":"+
						// connect24Response.getBody().getStatus().getMessage());

						if (connect24Response.getBody().getStatus().getIsSuccess()) {

							//// Update ESB Data
							ipsDao.updateCIMcbsData(requestUUID, TranMonitorStatus.SUCCESS.toString(),
									connect24Response.getBody().getStatus().getStatusCode(),
									connect24Response.getBody().getStatus().getMessage(),
									connect24Response.getBody().getData().getTransactionNoFromCBS());

							//// Update CBS Status to Tran Table
							ipsDao.updateOutwardCBSStatus(seqUniqueID, TranMonitorStatus.CBS_DEBIT_OK.toString(),
									TranMonitorStatus.IN_PROGRESS.toString());

						} else {

							//// Update ESB Data
							ipsDao.updateCIMcbsData(requestUUID, TranMonitorStatus.FAILURE.toString(),
									connect24Response.getBody().getStatus().getStatusCode(),
									connect24Response.getBody().getStatus().getMessage(),
									connect24Response.getBody().getData().getTransactionNoFromCBS());

							//// Update ESB Data Error
							ipsDao.updateOutwardCBSStatusError(seqUniqueID,
									TranMonitorStatus.CBS_DEBIT_ERROR.toString(),
									connect24Response.getBody().getStatus().getMessage(),
									TranMonitorStatus.FAILURE.toString());
						}
					} else {
						ipsDao.updateCIMcbsData(requestUUID, TranMonitorStatus.FAILURE.toString(),
								String.valueOf(connect24Response.getStatusCodeValue()), "No Response return from CBS",
								"");

						ipsDao.updateOutwardCBSStatusError(seqUniqueID, TranMonitorStatus.CBS_DEBIT_ERROR.toString(),
								String.valueOf(connect24Response.getStatusCodeValue()) + ":No Response return from CBS",
								TranMonitorStatus.FAILURE.toString());

					}

				} else {
					//// Update ESB Data to CIM Table
					ipsDao.updateCIMcbsData(requestUUID, TranMonitorStatus.FAILURE.toString(),
							String.valueOf(connect24Response.getStatusCodeValue()), "Internal Server Error", "");

					//// Update ESB Data to Outward Table
					ipsDao.updateOutwardCBSStatusError(seqUniqueID, TranMonitorStatus.CBS_DEBIT_ERROR.toString(),
							String.valueOf(connect24Response.getStatusCodeValue()) + ":Internal Server Error",
							TranMonitorStatus.FAILURE.toString());

				}

			}
		});

		///// Return Sequence ID to CIM
		mcCreditTransferResponse = new MCCreditTransferResponse(seqUniqueID,
				new SimpleDateFormat("YYYY-MM-dd HH:mm:ss ").format(new Date()));
		return mcCreditTransferResponse;

	}

	public ResponseEntity<CimCBSresponse> dbtFundRequest(String requestUUID) {

		//// Request Headers
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);

		//// Get Data from Table
		TranCimCBSTable data = tranCimCBSTableRep.findById(requestUUID).get();

		///////////////////// Request Body Creation/////////////////////////////
		CimCBSrequest cimCBSrequest = new CimCBSrequest();

		CimCBSrequestHeader cimCBSrequestHeader = new CimCBSrequestHeader();
		cimCBSrequestHeader.setRequestUUId(data.getRequest_uuid());
		cimCBSrequestHeader.setChannelId(data.getChannel_id());
		cimCBSrequestHeader.setServiceRequestVersion(data.getService_request_version());
		cimCBSrequestHeader.setServiceRequestId(data.getService_request_id());
		cimCBSrequestHeader
				.setMessageDateTime(listener.convertDateToGreDate(data.getMessage_date_time(), "2").toString());
		cimCBSrequestHeader.setCountryCode(env.getProperty("cimCBS.countryCode"));
		cimCBSrequest.setHeader(cimCBSrequestHeader);

		CimCBSrequestData cimCBSrequestData = new CimCBSrequestData();
		cimCBSrequestData.setTransactionNo(data.getTran_no());
		cimCBSrequestData.setInitiatingChannel(data.getInit_channel());
		cimCBSrequestData.setInitatorTransactionNo((data.getInit_tran_no() == null) ? "" : data.getInit_tran_no());
		if (data.getPost_to_cbs().equals("True")) {
			cimCBSrequestData.setPostToCBS(Boolean.TRUE);
		} else {
			cimCBSrequestData.setPostToCBS(Boolean.FALSE);
		}
		cimCBSrequestData.setTransactionType(data.getTran_type());
		cimCBSrequestData.setIsReversal(data.getIsreversal());
		cimCBSrequestData
				.setTransactionNoFromCBS((data.getTran_no_from_cbs() == null) ? "" : data.getTran_no_from_cbs());
		cimCBSrequestData.setCustomerName(data.getCustomer_name());
		cimCBSrequestData.setFromAccountNo(data.getFrom_account_no());
		cimCBSrequestData.setToAccountNo(data.getTo_account_no());

		cimCBSrequestData.setTransactionAmount(new BigDecimal(data.getTran_amt().toString()));
		cimCBSrequestData.setTransactionDate(new SimpleDateFormat("yyyy-MM-dd").format(data.getTran_date()));
		// cimCBSrequestData.setTransactionDate(listener.convertDateToGreDate(data.getTran_date(),"2"));

		cimCBSrequestData.setTransactionCurrency(data.getTran_currency());
		cimCBSrequestData.setTransactionParticularCode(data.getTran_particular_code());
		cimCBSrequestData.setCreditRemarks((data.getCredit_remarks() == null) ? "" : data.getCredit_remarks());
		cimCBSrequestData.setDebitRemarks((data.getDebit_remarks() == null) ? "" : data.getDebit_remarks());
		cimCBSrequestData.setReservedField1((data.getResv_field_1() == null) ? "" : data.getResv_field_1());
		cimCBSrequestData.setReservedField2((data.getResv_field_2() == null) ? "" : data.getResv_field_2());

		cimCBSrequestData
				.setInitatorSubTransactionNo((data.getInit_sub_tran_no() == null) ? "" : data.getInit_sub_tran_no());
		cimCBSrequestData.setErrorCode((data.getError_code() == null) ? "" : data.getError_code());
		cimCBSrequestData.setErrorMessage((data.getError_msg() == null) ? "" : data.getError_msg());
		cimCBSrequestData.setIpsMasterRefId((data.getIps_master_ref_id() == null) ? "" : data.getIps_master_ref_id());
		cimCBSrequestData.setRemitterBank(data.getRemitterbank());
		cimCBSrequestData.setRemitterBankCode(data.getRemitterbankcode());
		cimCBSrequestData.setRemitterSwiftCode(data.getRemitterswiftcode());
		cimCBSrequestData.setBeneficiaryBank(data.getBeneficiarybank());
		cimCBSrequestData.setBeneficiaryBankCode(data.getBeneficiarybankcode());
		cimCBSrequestData.setBeneficiarySwiftCode(data.getBeneficiaryswiftcode());
		cimCBSrequest.setData(cimCBSrequestData);

		// System.out.println(cimCBSrequest.toString());
		///////////////////////////////////////////////////

		HttpEntity<CimCBSrequest> entity = new HttpEntity<>(cimCBSrequest, httpHeaders);

		///// Call REST API
		ResponseEntity<CimCBSresponse> response = null;
		try {
			// System.out.println("Sending message to ESB Cable for Debit the Customer
			// Amount");
			response = restTemplate.postForEntity(
					env.getProperty("cimESB.url") + "appname=" + env.getProperty("cimESB.appname") + "&prgname="
							+ env.getProperty("cimESB.prgname") + "&arguments=" + env.getProperty("cimESB.arguments"),
					entity, CimCBSresponse.class);
			return new ResponseEntity<>(response.getBody(), HttpStatus.OK);
		} catch (HttpClientErrorException ex) {
			// System.out.println("HttpClient" + ex.getStatusCode());
			// System.out.println("Exception" + ex.getLocalizedMessage());
			// System.out.println("HTTP Client Error:" + ex.getLocalizedMessage() + "-" +
			// ex.getStatusCode());
			CimCBSresponse cbsResponse = new CimCBSresponse();
			return new ResponseEntity<>(cbsResponse, HttpStatus.BAD_REQUEST);
		} catch (HttpServerErrorException ex) {
			CimCBSresponse cbsResponse = new CimCBSresponse();
			return new ResponseEntity<>(cbsResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception ex) {
			// System.out.println("HTTP Ex Error:" + ex.getLocalizedMessage());
			CimCBSresponse cbsResponse = new CimCBSresponse();
			return new ResponseEntity<>(cbsResponse, HttpStatus.BAD_REQUEST);
		}

	}

	public CimMerchantResponse createCustomerQRConnection(String psuDeviceID, String psuIpAddress, String psuID,
			CIMCustomerQRcodeRequest qrrequest, String p_id, String channelID, String resvField1, String resvField2)
			throws DatatypeConfigurationException, JAXBException, KeyManagementException, UnrecoverableKeyException,
			KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException, WriterException {

		CimMerchantResponse response = new CimMerchantResponse();
		// System.out.println("MerchantResponse-------->" + qrrequest);

		String status = ipsDao.regCustomerQR(p_id, psuDeviceID, psuIpAddress, channelID, qrrequest);
		// System.out.println("status---------->" + status);
		if (status.equals("1")) {
			System.out.print("comingg");
			EncodeQRFormatResponse encodeQRresponse = encodeCustomerQRCodeFormat(qrrequest);

			if (encodeQRresponse.isSuccess()) {
				System.out.print("Encoded Successfully");
				String[] displayText = { qrrequest.getCustomerName(), "" };
				String[] titletextDesc = { "Scan here to pay" };
				// System.out.println("encodeQRresponse.getQrMsg()---------->" +
				// encodeQRresponse.getQrMsg());
				String qrImageCode = generateQRCode(encodeQRresponse.getQrMsg(), displayText, titletextDesc, 350, 350);
				response.setBase64QR(qrImageCode);
				System.out.print("qrImageCode Successfully------------------>" + qrImageCode);
				ipsDao.updateMerchantQRData(p_id, "SUCCESS", qrImageCode);
				return response;
			} else {
				// System.out.println("QR Code Error:" +
				// encodeQRresponse.getError_desc().get(0).toString());
				ipsDao.updateMerchantQRData(p_id, "FAILURE", encodeQRresponse.getError_desc().get(0).toString());
				throw new IPSXException("BIPS17:" + encodeQRresponse.getError_desc().get(0));
			}
		} else {
			throw new IPSXException("BIPS500:Internel Error");
		}
	}

	private EncodeQRFormatResponse encodeCustomerQRCodeFormat(CIMCustomerQRcodeRequest qrReuest) {
		// System.out.println("qrReuest------------>"+qrReuest);
		EncodeQRFormatResponse response = new EncodeQRFormatResponse();

		final MerchantPresentedMode merchantPresentMode = new MerchantPresentedMode();

		/// Payload Format Indicator(00)
		merchantPresentMode.setPayloadFormatIndicator(qrReuest.getPayloadFormatIndiator());
		// Point Of Initiation Method(01)
		if (!String.valueOf(qrReuest.getPointOfInitiationFormat()).equals("null")
				&& !String.valueOf(qrReuest.getPointOfInitiationFormat()).equals("")) {
			merchantPresentMode.setPointOfInitiationMethod(qrReuest.getPointOfInitiationFormat());
		}

		final MerchantAccountInformationReservedAdditional merchantAccountInformationValue = new MerchantAccountInformationReservedAdditional();
		merchantAccountInformationValue
				.setGloballyUniqueIdentifier(qrReuest.getPayeeAccountInformation().getGlobalID());
		merchantAccountInformationValue
				.setPayeeParticipantCode(qrReuest.getPayeeAccountInformation().getPayeeParticipantCode());
		merchantAccountInformationValue.setMerchantID(qrReuest.getPayeeAccountInformation().getCustomerID());
		final MerchantAccountInformationTemplate merchanAccountInformationReservedAdditional = new MerchantAccountInformationTemplate(
				"26", merchantAccountInformationValue);
		// merchantAccountInformationValue.addPaymentNetworkSpecific(paymentNetworkSpecific);
		merchantPresentMode.addMerchantAccountInformation(merchanAccountInformationReservedAdditional);

		merchantPresentMode.setTransactionCurrency(Currency.entryOf(qrReuest.getCurrency()).getNumber());

		//// Country Code (58)
		merchantPresentMode.setCountryCode(qrReuest.getCountryCode());

		//// Merchant Name(59)
		merchantPresentMode.setMerchantName(qrReuest.getCustomerName());

		//// Merchant City(60)
		merchantPresentMode.setMerchantCity(qrReuest.getCity());
		if (qrReuest.getAdditionalDataInformation() != null) {
			if ((!String.valueOf(qrReuest.getAdditionalDataInformation().getBillNumber()).equals("null")
					&& !String.valueOf(qrReuest.getAdditionalDataInformation().getBillNumber()).equals(""))
					|| (!String.valueOf(qrReuest.getAdditionalDataInformation().getMobileNumber()).equals("null")
							&& !String.valueOf(qrReuest.getAdditionalDataInformation().getMobileNumber()).equals(""))
					|| (!String.valueOf(qrReuest.getAdditionalDataInformation().getStoreLabel()).equals("null")
							&& !String.valueOf(qrReuest.getAdditionalDataInformation().getStoreLabel()).equals(""))
					|| (!String.valueOf(qrReuest.getAdditionalDataInformation().getReferenceNumber()).equals("null")
							&& !String.valueOf(qrReuest.getAdditionalDataInformation().getReferenceNumber()).equals(""))
					|| (!String.valueOf(qrReuest.getAdditionalDataInformation().getCustomerLabel()).equals("null")
							&& !String.valueOf(qrReuest.getAdditionalDataInformation().getCustomerLabel()).equals(""))
					|| (!String.valueOf(qrReuest.getAdditionalDataInformation().getTerminalLabel()).equals("null")
							&& !String.valueOf(qrReuest.getAdditionalDataInformation().getTerminalLabel()).equals(""))
					|| (!String.valueOf(qrReuest.getAdditionalDataInformation().getAddlDataRequest()).equals("null")
							&& !String.valueOf(qrReuest.getAdditionalDataInformation().getAddlDataRequest()).equals(""))
					|| (!String.valueOf(qrReuest.getAdditionalDataInformation().getdeviceID()).equals("null")
							&& !String.valueOf(qrReuest.getAdditionalDataInformation().getdeviceID()).equals(""))
					|| (!String.valueOf(qrReuest.getAdditionalDataInformation().getPurposeOfTransaction())
							.equals("null")
							&& !String.valueOf(qrReuest.getAdditionalDataInformation().getPurposeOfTransaction())
									.equals(""))) {

				//// Additional Data Information(62)
				final AdditionalDataField additionalDataFieldValue = new AdditionalDataField();

				if (!String.valueOf(qrReuest.getAdditionalDataInformation().getBillNumber()).equals("null")
						&& !String.valueOf(qrReuest.getAdditionalDataInformation().getBillNumber()).equals("")) {
					additionalDataFieldValue.setBillNumber(qrReuest.getAdditionalDataInformation().getBillNumber());
				}
				if (!String.valueOf(qrReuest.getAdditionalDataInformation().getMobileNumber()).equals("null")
						&& !String.valueOf(qrReuest.getAdditionalDataInformation().getMobileNumber()).equals("")) {
					additionalDataFieldValue.setMobileNumber(qrReuest.getAdditionalDataInformation().getMobileNumber());
				}
				if (!String.valueOf(qrReuest.getAdditionalDataInformation().getdeviceID()).equals("null")
						&& !String.valueOf(qrReuest.getAdditionalDataInformation().getdeviceID()).equals("")) {
					additionalDataFieldValue.setLoyaltyNumber(qrReuest.getAdditionalDataInformation().getdeviceID());

				}

				if (!String.valueOf(qrReuest.getAdditionalDataInformation().getPurposeOfTransaction()).equals("null")
						&& !String.valueOf(qrReuest.getAdditionalDataInformation().getPurposeOfTransaction())
								.equals("")) {
					additionalDataFieldValue
							.setPurposeTransaction(qrReuest.getAdditionalDataInformation().getPurposeOfTransaction());

				}

				if (!String.valueOf(qrReuest.getAdditionalDataInformation().getStoreLabel()).equals("null")
						&& !String.valueOf(qrReuest.getAdditionalDataInformation().getStoreLabel()).equals("")) {
					additionalDataFieldValue.setStoreLabel(qrReuest.getAdditionalDataInformation().getStoreLabel());

				}

				if (!String.valueOf(qrReuest.getAdditionalDataInformation().getReferenceNumber()).equals("null")
						&& !String.valueOf(qrReuest.getAdditionalDataInformation().getReferenceNumber()).equals("")) {

					additionalDataFieldValue
							.setReferenceLabel(qrReuest.getAdditionalDataInformation().getReferenceNumber());

				}

				if (!String.valueOf(qrReuest.getAdditionalDataInformation().getCustomerLabel()).equals("null")
						&& !String.valueOf(qrReuest.getAdditionalDataInformation().getCustomerLabel()).equals("")) {
					additionalDataFieldValue
							.setCustomerLabel(qrReuest.getAdditionalDataInformation().getCustomerLabel());

				}

				if (!String.valueOf(qrReuest.getAdditionalDataInformation().getTerminalLabel()).equals("null")
						&& !String.valueOf(qrReuest.getAdditionalDataInformation().getTerminalLabel()).equals("")) {
					// System.out.println(qrReuest.getAdditionalDataInformation().getTerminalLabel());
					additionalDataFieldValue
							.setTerminalLabel(qrReuest.getAdditionalDataInformation().getTerminalLabel());

				}

				if (!String.valueOf(qrReuest.getAdditionalDataInformation().getAddlDataRequest()).equals("null")
						&& !String.valueOf(qrReuest.getAdditionalDataInformation().getAddlDataRequest()).equals("")) {
					additionalDataFieldValue.setAdditionalConsumerDataRequest(
							qrReuest.getAdditionalDataInformation().getAddlDataRequest());

				}

				final AdditionalDataFieldTemplate additionalDataField = new AdditionalDataFieldTemplate();
				additionalDataField.setValue(additionalDataFieldValue);

				merchantPresentMode.setAdditionalDataField(additionalDataField);
				// System.out.println("fffffffff");
			}

		}

		response.setSuccess(true);
		response.setQrMsg(merchantPresentMode.toString());

		// System.out.println("Response----------->" + merchantPresentMode.toString());

		return response;

	}

	public CIMCustomerDecodeQRFormatResponse getCustomerQRdata(String psuDeviceID, String psuIpAddress, String psuID,
			CIMMerchantQRRequestFormat cimQRFormatData, String p_id, String channelID, String resvfield1,
			String resvfield2) {
		// System.out.println("cimQRFormatData--------------->" + cimQRFormatData);
		final ValidationResult validationResult = Crc16Validate.validate(cimQRFormatData.getQrCode());
		String crccheck = env.getProperty("ipsx.crccheck");
		Boolean validationResultb = null;
		if (crccheck.equals("true")) {
			validationResultb = validationResult.isValid();
		} else {
			validationResultb = true;
		}

		if (validationResultb.equals(true)) {
			final MerchantPresentedMode merchantPresentedMode = DecoderMpm.decode(cimQRFormatData.getQrCode(),
					MerchantPresentedMode.class);

			CIMCustomerDecodeQRFormatResponse response = new CIMCustomerDecodeQRFormatResponse();

			response.setPayloadFormatIndiator(merchantPresentedMode.getPayloadFormatIndicator().getValue());

			if (!String.valueOf(merchantPresentedMode.getPointOfInitiationMethod()).equals("null")
					&& !String.valueOf(merchantPresentedMode.getPointOfInitiationMethod()).equals("")) {
				response.setPointOfInitiationFormat(merchantPresentedMode.getPointOfInitiationMethod().getValue());
			}

			Map<String, MerchantAccountInformationTemplate> merTemplate = merchantPresentedMode
					.getMerchantAccountInformation();

			CIMCustomerDecodeQRMerchantAcctInfo acctInfo = new CIMCustomerDecodeQRMerchantAcctInfo();

			for (final Entry<String, MerchantAccountInformationTemplate> entry : merTemplate.entrySet()) {
				// System.out.println(entry.getValue().getValue());
				MerchantAccountInformationReservedAdditional ss = (MerchantAccountInformationReservedAdditional) entry
						.getValue().getValue();

				if (!String.valueOf(ss.getGloballyUniqueIdentifier()).equals("null")
						&& !String.valueOf(ss.getGloballyUniqueIdentifier()).equals("")) {
					acctInfo.setGlobalID(ss.getGloballyUniqueIdentifier().getValue());
				}

				if (!String.valueOf(ss.getPayeeParticipantCode()).equals("null")
						&& !String.valueOf(ss.getPayeeParticipantCode()).equals("")) {
					acctInfo.setPayeeParticipantCode(ss.getPayeeParticipantCode().getValue());
				}

				if (!String.valueOf(ss.getMerchantID()).equals("null")
						&& !String.valueOf(ss.getMerchantID()).equals("")) {
					acctInfo.setCustomerID(ss.getMerchantID().getValue());
				}

				response.setPayeeAccountInformation(acctInfo);

			}

			if (!String.valueOf(merchantPresentedMode.getTransactionCurrency()).equals("null")
					&& !String.valueOf(merchantPresentedMode.getTransactionCurrency()).equals("")) {

				response.setCurrency(
						Currency.entryOf1(merchantPresentedMode.getTransactionCurrency().getValue()).getCode());
			}

			// //System.out.println(merchantPresentedMode.getTransactionAmount().getValue());
			if (!String.valueOf(merchantPresentedMode.getTransactionAmount()).equals("null")
					&& !String.valueOf(merchantPresentedMode.getTransactionAmount()).equals("")) {
				response.setTrAmt(merchantPresentedMode.getTransactionAmount().getValue());
			}

			response.setCountryCode(merchantPresentedMode.getCountryCode().getValue());
			response.setCustomerName(merchantPresentedMode.getMerchantName().getValue());
			// response.setCity(merchantPresentedMode.getMerchantCity().getValue());
			if (!String.valueOf(merchantPresentedMode.getMerchantCity()).equals("null")
					&& !String.valueOf(merchantPresentedMode.getMerchantCity()).equals("")) {
				response.setCity(merchantPresentedMode.getMerchantCity().getValue());
			}
			if (!String.valueOf(merchantPresentedMode.getPostalCode()).equals("null")
					&& !String.valueOf(merchantPresentedMode.getPostalCode()).equals("")) {
				response.setPostalCode(merchantPresentedMode.getPostalCode().getValue());
			}

			if (merchantPresentedMode.getAdditionalDataField() != null) {
				CIMMerchantDecodeQRCustomerAddlInfo addInfo = new CIMMerchantDecodeQRCustomerAddlInfo();
				AdditionalDataFieldTemplate merAddTemplate = merchantPresentedMode.getAdditionalDataField();

				if (!String.valueOf(merAddTemplate.getValue().getBillNumber()).equals("null")
						&& !String.valueOf(merAddTemplate.getValue().getBillNumber()).equals("")) {
					addInfo.setBillNumber(merAddTemplate.getValue().getBillNumber().getValue());
				}
				if (!String.valueOf(merAddTemplate.getValue().getMobileNumber()).equals("null")
						&& !String.valueOf(merAddTemplate.getValue().getMobileNumber()).equals("")) {
					addInfo.setMobileNumber(merAddTemplate.getValue().getMobileNumber().getValue());
				}
				if (!String.valueOf(merAddTemplate.getValue().getStoreLabel()).equals("null")
						&& !String.valueOf(merAddTemplate.getValue().getStoreLabel()).equals("")) {
					addInfo.setStoreLabel(merAddTemplate.getValue().getStoreLabel().getValue());
				}
				if (!String.valueOf(merAddTemplate.getValue().getLoyaltyNumber()).equals("null")
						&& !String.valueOf(merAddTemplate.getValue().getLoyaltyNumber()).equals("")) {
					addInfo.setDeviceID(merAddTemplate.getValue().getLoyaltyNumber().getValue());
				}
				if (!String.valueOf(merAddTemplate.getValue().getReferenceLabel()).equals("null")
						&& !String.valueOf(merAddTemplate.getValue().getReferenceLabel()).equals("")) {
					addInfo.setReferenceNumber(merAddTemplate.getValue().getReferenceLabel().getValue());
				}
				if (!String.valueOf(merAddTemplate.getValue().getCustomerLabel()).equals("null")
						&& !String.valueOf(merAddTemplate.getValue().getCustomerLabel()).equals("")) {
					addInfo.setCustomerLabel(merAddTemplate.getValue().getCustomerLabel().getValue());
				}
				if (!String.valueOf(merAddTemplate.getValue().getTerminalLabel()).equals("null")
						&& !String.valueOf(merAddTemplate.getValue().getTerminalLabel()).equals("")) {
					addInfo.setTerminalLabel(merAddTemplate.getValue().getTerminalLabel().getValue());
				}
				if (!String.valueOf(merAddTemplate.getValue().getPurposeTransaction()).equals("null")
						&& !String.valueOf(merAddTemplate.getValue().getPurposeTransaction()).equals("")) {
					addInfo.setPurposeOfTransaction(merAddTemplate.getValue().getPurposeTransaction().getValue());
				}
				if (!String.valueOf(merAddTemplate.getValue().getAdditionalConsumerDataRequest()).equals("null")
						&& !String.valueOf(merAddTemplate.getValue().getAdditionalConsumerDataRequest()).equals("")) {
					addInfo.setAddlDataRequest(merAddTemplate.getValue().getAdditionalConsumerDataRequest().getValue());
				}

				response.setAdditionalDataInformation(addInfo);
			}

			return response;

		} else {
			// System.out.println(validationResult.getErrors().toString());
			throw new IPSXException(errorCode.validationError("BIPS18"));
		}

	}

	// For MUR Static QR

	public CimMerchantResponse createMerchantQRConnection(String psuDeviceID, String psuIpAddress, String psuID,
			CIMMerchantQRcodeRequest qrrequest, String p_id, String channelID, String resvField1, String resvField2,String userId,String unitId,String terminalId)
			throws DatatypeConfigurationException, JAXBException, KeyManagementException, UnrecoverableKeyException,
			KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException {

		CimMerchantResponse response = new CimMerchantResponse();

		String status = ipsDao.regMerchantQR(p_id, psuDeviceID, psuIpAddress, channelID, qrrequest,userId,unitId,terminalId);
		if (status.equals("1")) {

			EncodeQRFormatResponse encodeQRresponse = encodeQRCodeFormat(qrrequest);

			if (encodeQRresponse.isSuccess()) {
				String[] displayText = { qrrequest.getMerchantName(), "" };
				String[] titletextDesc = { "Scan here to pay" };
				String qrImageCode = generateQRCode(encodeQRresponse.getQrMsg(), displayText, titletextDesc, 390, 335);
				response.setBase64QR(qrImageCode);

				ipsDao.updateMerchantQRData(p_id, "SUCCESS", qrImageCode);
				return response;
			} else {
				// String responseStatus = errorCode.validationError("BIPS17");

				System.out.println("QR Code Error:" + encodeQRresponse.getError_desc().get(0).toString());
				ipsDao.updateMerchantQRData(p_id, "FAILURE", encodeQRresponse.getError_desc().get(0).toString());
				throw new IPSXException("BIPS17:" + encodeQRresponse.getError_desc().get(0));
			}
			/*
			 * }else { throw new IPSXException("BIPS17:Merchant Details Not Found"); }
			 */

		} else {
			throw new IPSXException("BIPS500:Internel Error");
		}

	}

	private String generateQRCode(String qrMsg, String[] displayTextQR, String[] titleText, int qrWidth, int qrHeight) {
		String encodedQRImage = "0";
		try {
			// 1. Generate QR Code Image
			QRCodeWriter qrCodeWriter = new QRCodeWriter();
			
			BitMatrix bitMatrix = qrCodeWriter.encode(qrMsg, BarcodeFormat.QR_CODE, qrWidth, qrHeight);
			bitMatrix = removeWhiteBorder(bitMatrix);
			BufferedImage qrImage = MatrixToImageWriter.toBufferedImage(bitMatrix);
			
			// 2. Load and resize base image
			BufferedImage originalBaseImage = ImageIO
					.read(this.getClass().getResourceAsStream("/static/Image/QR_MUR_M.png"));

			int finalWidth = 900;
			int finalHeight = 1100;

			// Resize background image
			Image scaledBaseImage = originalBaseImage.getScaledInstance(finalWidth, finalHeight, Image.SCALE_SMOOTH);
			BufferedImage resizedBaseImage = new BufferedImage(finalWidth, finalHeight, BufferedImage.TYPE_INT_ARGB);
			Graphics2D g = resizedBaseImage.createGraphics();
			g.drawImage(scaledBaseImage, 0, 0, null);

			// Enable anti-aliasing for better quality
			g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
			g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

			// 3. Draw QR Code in center (adjust position as needed)
			int qrX = 255;
			int qrY = 365; // adjust for your layout
			
			Image scaledQR = qrImage.getScaledInstance(qrWidth, qrHeight, Image.SCALE_SMOOTH);
			g.drawImage(scaledQR, qrX, qrY, null);

			// 5. Draw merchant/customer name below QR
			if (displayTextQR != null && displayTextQR.length > 0) {
				g.setFont(new Font("Arial", Font.BOLD, 24));
				g.setColor(Color.BLACK);
				FontMetrics fm = g.getFontMetrics();
				int y = qrY + qrHeight + 55;
				for (String line : displayTextQR) {
					int x = (finalWidth - fm.stringWidth(line)) / 2;
					g.drawString(line, x, y);
					y += 30;
				}
			}
			g.dispose();
			// 6. Encode image to Base64
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			ImageIO.write(resizedBaseImage, "PNG", outputStream);
			encodedQRImage = Base64.getEncoder().encodeToString(outputStream.toByteArray());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return encodedQRImage;
	}
	
	private BitMatrix removeWhiteBorder(BitMatrix matrix) {
	    int[] enclosingRectangle = matrix.getEnclosingRectangle();
	    int left = enclosingRectangle[0];
	    int top = enclosingRectangle[1];
	    int width = enclosingRectangle[2];
	    int height = enclosingRectangle[3];

	    BitMatrix trimmedMatrix = new BitMatrix(width, height);
	    trimmedMatrix.clear();
	    for (int x = 0; x < width; x++) {
	        for (int y = 0; y < height; y++) {
	            if (matrix.get(x + left, y + top)) {
	                trimmedMatrix.set(x, y);
	            }
	        }
	    }
	    return trimmedMatrix;
	}

}
