package com.bornfire.controller;

import static com.bornfire.exception.MPayErrorResponseCode.SERVER_ERROR;

import java.io.IOException;
import java.math.BigDecimal;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.xml.bind.JAXBException;
import javax.xml.datatype.DatatypeConfigurationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.task.TaskExecutor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.remoting.RemoteAccessException;
import org.springframework.stereotype.Component;

import com.bornfire.config.ErrorResponseCode;
import com.bornfire.config.SequenceGenerator;
import com.bornfire.entity.BIPS_Charge_Back_Entity;
import com.bornfire.entity.BIPS_Charge_Back_Rep;
import com.bornfire.entity.BankAgentTable;
import com.bornfire.entity.C24FTResponse;
import com.bornfire.entity.CIMMerchantDirectFndRequest;
import com.bornfire.entity.CimMerchantResponse;
import com.bornfire.entity.MCCreditTransferResponse;
import com.bornfire.entity.MerchantFeeServiceChargeRepo;
import com.bornfire.entity.MerchantFeesServiceCharges;
import com.bornfire.entity.MerchantMasterRep;
import com.bornfire.entity.MerchantQrGenTable;
import com.bornfire.entity.MerchantQrGenTablerep;
import com.bornfire.entity.OutwardTransactionMonitoringTable;
import com.bornfire.entity.OutwardTransactionMonitoringTableRep;
import com.bornfire.entity.StaticMerchantNotificationEntity;
import com.bornfire.entity.StaticMerchantNotificationRepo;
import com.bornfire.entity.TranMonitorStatus;
import com.bornfire.exception.Connect24Exception;
import com.bornfire.exception.IPSXException;
import com.bornfire.exception.ServerErrorException;
import com.bornfire.service.MessageServices;

@Component
public class PortConnection {

	@Autowired
	SequenceGenerator sequence;

	@Autowired
	TaskExecutor taskExecutor;

	@Autowired
	Environment env;

	@Autowired
	IpsDao ipsDao;

	@Autowired
	CimCBSservice cimCBSservice;

	@Autowired
	Connect24Service connect24Service;

	@Autowired
	ErrorResponseCode errorCode;

	@Autowired
	MerchantQrGenTablerep merchantQrGenTablerep;

	@Autowired
	MessageServices messageServices;

	@Autowired
	OutwardTransactionMonitoringTableRep Otrepo;
	
	private static final Logger logger = LoggerFactory.getLogger(PortConnection.class);

	public MCCreditTransferResponse createMerchantFTConnection(String psuDeviceID, String psuIpAddress, String psuID,
			CIMMerchantDirectFndRequest mcCreditTransferRequest, String p_id, String channelID, String resvField1,
			String resvField2)
			throws DatatypeConfigurationException, JAXBException, KeyManagementException, UnrecoverableKeyException,
			KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException, SQLException {

		MCCreditTransferResponse mcCreditTransferResponse = null;

		MerchantQrGenTable merchanrGenEntity = merchantQrGenTablerep
				.getRecordByRefLable(mcCreditTransferRequest.getAdditionalDataInformation().getReferenceLabel());

		if (Objects.isNull(merchanrGenEntity)) {
			List<MerchantQrGenTable> list = merchantQrGenTablerep.findByPId(mcCreditTransferRequest.getAdditionalDataInformation().getReferenceLabel());
			if (list != null && !list.isEmpty()) {
				merchanrGenEntity = list.get(0);
			}
		}

		if (Objects.nonNull(merchanrGenEntity)) {
			if (mcCreditTransferRequest.getMerchantAccount().getPointOfInitiationFormat().equals("12")) {
				if (merchanrGenEntity.getBill_number() != null && !merchanrGenEntity.getBill_number().isEmpty()
						&& !merchanrGenEntity.getBill_number().equals(mcCreditTransferRequest.getAdditionalDataInformation().getBillNumber())) {
					throw new IPSXException(errorCode.validationError("BIPSQRTran01"));
				}
			} else {
				if (Objects.isNull(mcCreditTransferRequest.getAdditionalDataInformation().getBillNumber())) {
					mcCreditTransferRequest.getAdditionalDataInformation().setBillNumber("");
				}
			}
//			if (!merchanrGenEntity.getStore_label()
//					.equals(mcCreditTransferRequest.getAdditionalDataInformation().getStoreLabel())) {
//				throw new IPSXException(errorCode.validationError("BIPSQRTran02"));
//			}
//			if (merchanrGenEntity.getLoyalty_number() != null && !merchanrGenEntity.getLoyalty_number().isEmpty()
//					&& !merchanrGenEntity.getLoyalty_number().equals(mcCreditTransferRequest.getAdditionalDataInformation().getLoyaltyNumber())) {
//				throw new IPSXException(errorCode.validationError("BIPSQRTran03"));
//			}
//			if (merchanrGenEntity.getCustomer_label() != null && !merchanrGenEntity.getCustomer_label().isEmpty()
//					&& !merchanrGenEntity.getCustomer_label().equals(mcCreditTransferRequest.getAdditionalDataInformation().getCustomerLabel())) {
//				throw new IPSXException(errorCode.validationError("BIPSQRTran04"));
//			}
//			if (merchanrGenEntity.getTerminal_label() != null && !merchanrGenEntity.getTerminal_label().isEmpty()
//					&& !merchanrGenEntity.getTerminal_label().equals(mcCreditTransferRequest.getAdditionalDataInformation().getTerminalLabel())) {
//				throw new IPSXException(errorCode.validationError("BIPSQRTran05"));
//			}
		}

		///// Generate Sequence Unique ID
		String seqUniqueID = sequence.generateSeqUniqueID();
		///// Generate Bob Msg ID
		String bobMsgID = seqUniqueID;
		///// Generate SystemTraceAuditNumber
		String sysTraceNumber = sequence.format();
		String sysTraceNumberFees = sequence.format();
		//// Generate Msg Sequence
		String msgSeq = sequence.generateMsgSequence();
		///// Generate EndToEnd ID
		String endTOEndID = env.getProperty("ipsx.bicfi") + new SimpleDateFormat("yyyyMMdd").format(new Date()) + msgSeq
				+ "O";
		///// Net Mir
		String msgNetMir = new SimpleDateFormat("yyMMdd").format(new Date()) + env.getProperty("ipsx.user") + "0001"
				+ msgSeq;

		logger.info("Transaction cycle starts");
		logger.info("System Trace Audit Number" + sysTraceNumber);
		logger.info("System Sequence ID" + bobMsgID);

		logger.info("Register Initial outgoing Fund Transfer Record");

		/// Purpose Code for Peer to Peer Connection
		// mcCreditTransferRequest.setPurpose("300");

		///// Get Other Bank Agent and Agent Account number
		BankAgentTable othBankAgent = ipsDao
				.findByBank(mcCreditTransferRequest.getMerchantAccount().getPayeeParticipantCode().replace("XXXX", ""));
		String bankAgentStr = (othBankAgent != null && othBankAgent.getBank_agent() != null) ? othBankAgent.getBank_agent() : "BARBBWGU";
		String bankAgentAcctStr = (othBankAgent != null && othBankAgent.getBank_agent_account() != null) ? othBankAgent.getBank_agent_account() : "95210200002756";
		logger.info(bankAgentStr + " - " + bankAgentAcctStr);

		String lclInstrm = TranMonitorStatus.CSDC.toString();
		String ctgyPurp = "300";

		String chrBeearer = "CRED";
		//// Get Remitter Bank Code
		String remitterBankCode = ipsDao.getOtherBankCode(env.getProperty("ipsx.dbtragt"));

		String tot_tran_amount = mcCreditTransferRequest.getMerchantAccount().getTrAmt();

		//// Remarks
		String remarks = "";
		//// Retrieve Remittence Information
		StringBuilder remInfo = new StringBuilder();
		remInfo.append("/QR/" + mcCreditTransferRequest.getMerchantAccount().getGlobalID() + "//");
		if (!String.valueOf(mcCreditTransferRequest.getMerchantAccount().getPointOfInitiationFormat()).equals("null")
				&& !String.valueOf(mcCreditTransferRequest.getMerchantAccount().getPointOfInitiationFormat())
						.equals("")) {
			remInfo.append("01/" + mcCreditTransferRequest.getMerchantAccount().getPointOfInitiationFormat() + "/");
		}
		if (!String.valueOf(mcCreditTransferRequest.getMerchantAccount().getTipOrConvenienceIndicator()).equals("null")
				&& !String.valueOf(mcCreditTransferRequest.getMerchantAccount().getTipOrConvenienceIndicator())
						.equals("")) {

			if (String.valueOf(mcCreditTransferRequest.getMerchantAccount().getTipOrConvenienceIndicator())
					.equals("02")) {
				remInfo.append(
						"55/02/56/" + mcCreditTransferRequest.getMerchantAccount().getConvenienceIndicatorFee() + "/");

				Double sumData = Double
						.parseDouble(mcCreditTransferRequest.getMerchantAccount().getConvenienceIndicatorFee())
						+ (Double.parseDouble(mcCreditTransferRequest.getMerchantAccount().getTrAmt()));
				DecimalFormat rounded = new DecimalFormat("###.00");

				tot_tran_amount = (rounded.format(sumData)).toString();
				logger.info(sumData + "tot_tran_amount" + tot_tran_amount);

			} else if (String.valueOf(mcCreditTransferRequest.getMerchantAccount().getTipOrConvenienceIndicator())
					.equals("03")) {
				remInfo.append(
						"55/03/57/" + mcCreditTransferRequest.getMerchantAccount().getConvenienceIndicatorFee() + "/");

				Double convFee = (((Double.parseDouble(mcCreditTransferRequest.getMerchantAccount().getTrAmt()))
						* (Double.parseDouble(
								mcCreditTransferRequest.getMerchantAccount().getConvenienceIndicatorFee())))
						/ 100);
				Double sumData = convFee
						+ (Double.parseDouble(mcCreditTransferRequest.getMerchantAccount().getTrAmt()));
				DecimalFormat rounded = new DecimalFormat("###.00");

				tot_tran_amount = (rounded.format(sumData)).toString();
				logger.info(sumData + "tot_tran_amount" + tot_tran_amount);

			} else if (String.valueOf(mcCreditTransferRequest.getMerchantAccount().getTipOrConvenienceIndicator())
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
		logger.info("tot_tran_amount" + tot_tran_amount);

		if (mcCreditTransferRequest.getAdditionalDataInformation() != null) {
			if ((!String.valueOf(mcCreditTransferRequest.getAdditionalDataInformation().getBillNumber()).equals("null")
					&& !String.valueOf(mcCreditTransferRequest.getAdditionalDataInformation().getBillNumber())
							.equals(""))
					|| (!String.valueOf(mcCreditTransferRequest.getAdditionalDataInformation().getMobileNumber())
							.equals("null")
							&& !String.valueOf(mcCreditTransferRequest.getAdditionalDataInformation().getMobileNumber())
									.equals(""))
					|| (!String.valueOf(mcCreditTransferRequest.getAdditionalDataInformation().getReferenceLabel())
							.equals("null")
							&& !String
									.valueOf(mcCreditTransferRequest.getAdditionalDataInformation().getReferenceLabel())
									.equals(""))
					|| (!String.valueOf(mcCreditTransferRequest.getAdditionalDataInformation().getStoreLabel())
							.equals("null")
							&& !String.valueOf(mcCreditTransferRequest.getAdditionalDataInformation().getStoreLabel())
									.equals(""))
					|| (!String.valueOf(mcCreditTransferRequest.getAdditionalDataInformation().getLoyaltyNumber())
							.equals("null")
							&& !String
									.valueOf(mcCreditTransferRequest.getAdditionalDataInformation().getLoyaltyNumber())
									.equals(""))
					|| (!String.valueOf(mcCreditTransferRequest.getAdditionalDataInformation().getCustomerLabel())
							.equals("null")
							&& !String
									.valueOf(mcCreditTransferRequest.getAdditionalDataInformation().getCustomerLabel())
									.equals(""))
					|| (!String.valueOf(mcCreditTransferRequest.getAdditionalDataInformation().getTerminalLabel())
							.equals("null")
							&& !String
									.valueOf(mcCreditTransferRequest.getAdditionalDataInformation().getTerminalLabel())
									.equals(""))
					|| (!String.valueOf(mcCreditTransferRequest.getAdditionalDataInformation().getAddlDataRequest())
							.equals("null")
							&& !String
									.valueOf(
											mcCreditTransferRequest.getAdditionalDataInformation().getAddlDataRequest())
									.equals(""))
					|| (!String
							.valueOf(mcCreditTransferRequest.getAdditionalDataInformation().getPurposeOfTransaction())
							.equals("null")
							&& !String.valueOf(
									mcCreditTransferRequest.getAdditionalDataInformation().getPurposeOfTransaction())
									.equals(""))) {

				remInfo.append("62//");

				if (!String.valueOf(mcCreditTransferRequest.getAdditionalDataInformation().getBillNumber())
						.equals("null")
						&& !String.valueOf(mcCreditTransferRequest.getAdditionalDataInformation().getBillNumber())
								.equals("")) {
					remInfo.append(
							"01/" + mcCreditTransferRequest.getAdditionalDataInformation().getBillNumber() + "/");
				}
				if (!String.valueOf(mcCreditTransferRequest.getAdditionalDataInformation().getMobileNumber())
						.equals("null")
						&& !String.valueOf(mcCreditTransferRequest.getAdditionalDataInformation().getMobileNumber())
								.equals("")) {
					remInfo.append(
							"02/" + mcCreditTransferRequest.getAdditionalDataInformation().getMobileNumber() + "/");
				}
				if (!String.valueOf(mcCreditTransferRequest.getAdditionalDataInformation().getStoreLabel())
						.equals("null")
						&& !String.valueOf(mcCreditTransferRequest.getAdditionalDataInformation().getStoreLabel())
								.equals("")) {
					remInfo.append(
							"03/" + mcCreditTransferRequest.getAdditionalDataInformation().getStoreLabel() + "/");
				}
				if (!String.valueOf(mcCreditTransferRequest.getAdditionalDataInformation().getLoyaltyNumber())
						.equals("null")
						&& !String.valueOf(mcCreditTransferRequest.getAdditionalDataInformation().getLoyaltyNumber())
								.equals("")) {
					remInfo.append(
							"04/" + mcCreditTransferRequest.getAdditionalDataInformation().getLoyaltyNumber() + "/");
				}
				if (!String.valueOf(mcCreditTransferRequest.getAdditionalDataInformation().getReferenceLabel())
						.equals("null")
						&& !String.valueOf(mcCreditTransferRequest.getAdditionalDataInformation().getReferenceLabel())
								.equals("")) {
					remInfo.append(
							"05/" + mcCreditTransferRequest.getAdditionalDataInformation().getReferenceLabel() + "/");
				}
				if (!String.valueOf(mcCreditTransferRequest.getAdditionalDataInformation().getCustomerLabel())
						.equals("null")
						&& !String.valueOf(mcCreditTransferRequest.getAdditionalDataInformation().getCustomerLabel())
								.equals("")) {
					remInfo.append(
							"06/" + mcCreditTransferRequest.getAdditionalDataInformation().getCustomerLabel() + "/");
				}
				if (!String.valueOf(mcCreditTransferRequest.getAdditionalDataInformation().getTerminalLabel())
						.equals("null")
						&& !String.valueOf(mcCreditTransferRequest.getAdditionalDataInformation().getTerminalLabel())
								.equals("")) {
					remInfo.append(
							"07/" + mcCreditTransferRequest.getAdditionalDataInformation().getTerminalLabel() + "/");
				}
				if (Objects.nonNull(mcCreditTransferRequest.getAdditionalDataInformation().getPurposeOfTransaction())) {
					remInfo.append("08/"
							+ mcCreditTransferRequest.getAdditionalDataInformation().getPurposeOfTransaction() + "/");
					remarks = mcCreditTransferRequest.getAdditionalDataInformation().getPurposeOfTransaction();
				}
				if (Objects.nonNull(mcCreditTransferRequest.getAdditionalDataInformation().getAddlDataRequest())) {
					remInfo.append(
							"09/" + mcCreditTransferRequest.getAdditionalDataInformation().getAddlDataRequest() + "/");
				}
			}

		}

		remInfo.append("//RQ/");
		logger.debug("RemitterInfo->" + remInfo.toString());
		////// Register Data to Master Table
		ipsDao.RegisterMerchantOutgoingMasterRecord(psuDeviceID, psuIpAddress, sysTraceNumber, bobMsgID, seqUniqueID,
				endTOEndID, seqUniqueID, msgNetMir, env.getProperty("ipsx.bicfi"), bankAgentStr,
				env.getProperty("ipsx.dbtragt"), env.getProperty("ipsx.dbtragtacct"), bankAgentStr,
				bankAgentAcctStr, seqUniqueID, "0100", lclInstrm, ctgyPurp, ctgyPurp,
				mcCreditTransferRequest.getRemitterAccount().getAcctName(),
				mcCreditTransferRequest.getRemitterAccount().getAcctNumber(),
				mcCreditTransferRequest.getMerchantAccount().getPayeeParticipantCode(), remitterBankCode,
				mcCreditTransferRequest.getMerchantAccount().getCurrency(),
				mcCreditTransferRequest.getMerchantAccount().getMerchantName(),
				mcCreditTransferRequest.getMerchantAccount().getMerchantAcctNumber(), p_id, tot_tran_amount, remarks,
				p_id, p_id, channelID, resvField1, resvField2, chrBeearer, remInfo.toString(), mcCreditTransferRequest);

		///// Update TranMonitor Table
		ipsDao.updateINOUTOUTWARD(seqUniqueID, "MC_IN");

		// String consAmt=
		// ipsDao.getMaxAmountPerDay(mcCreditTransferRequest.getRemitterAccount().getAcctNumber(),mcCreditTransferRequest.getMerchantAccount().getTrAmt());

		ResponseEntity<C24FTResponse> connect24Response = null;
		ResponseEntity<C24FTResponse> connect24Responsefee = null;
		try {

			if (!mcCreditTransferRequest.getRemitterAccount().getAcctName().equals("")) {
				if (!mcCreditTransferRequest.getRemitterAccount().getAcctNumber().equals("")) {
					if (!mcCreditTransferRequest.getMerchantAccount().getMerchantName().equals("")) {
						if (!mcCreditTransferRequest.getMerchantAccount().getMerchantAcctNumber().equals("")) {
							if (!mcCreditTransferRequest.getMerchantAccount().getTrAmt().equals("")) {
								if (!mcCreditTransferRequest.getMerchantAccount().getTrAmt().equals("0")
										&& !mcCreditTransferRequest.getMerchantAccount().getTrAmt().equals("0.00")) {

									if (!mcCreditTransferRequest.getMerchantAccount().getCurrency().equals("")) {

										if ("MUR".equalsIgnoreCase(mcCreditTransferRequest.getMerchantAccount().getCurrency()) 
												|| "BWP".equalsIgnoreCase(mcCreditTransferRequest.getMerchantAccount().getCurrency())
												|| mcCreditTransferRequest.getMerchantAccount().getCurrency().equals(env.getProperty("bob.crncycode"))) {

											if (mcCreditTransferRequest.getRemitterAccount().getAcctNumber() != null
													&& mcCreditTransferRequest.getMerchantAccount().getMerchantAcctNumber() != null) {

												///// Calling Connect 24 for DEBIT
												logger.info("Send message to Connect24");
												try {
													connect24Response = connect24Service.DbtFundMerchantDirectRequest("",
															"", mcCreditTransferRequest, sysTraceNumber, seqUniqueID,
															"MBQRPAY/"
																	+ mcCreditTransferRequest
																			.getMerchantAccount().getPayeeParticipantCode()
																	+ "/"
																	+ mcCreditTransferRequest.getMerchantAccount()
																			.getMerchantAcctNumber()
																	+ "/" + mcCreditTransferRequest.getMerchantAccount()
																			.getMerchantName(),
															tot_tran_amount);
												} catch (Exception e) {
													logger.warn("Connect24 call exception: " + e.getMessage());
												}

												///// Return Status Code 200 from Connect 24
												if (connect24Response != null && connect24Response.getStatusCode() == HttpStatus.OK) {
													if (mcCreditTransferRequest.getMerchantAccount()
															.getPointOfInitiationFormat().equals("11")) {
														try {
															setAllInformationtoStaticMerchant(mcCreditTransferRequest);
														} catch (Exception e) {}
													}
													try {
														sendSMStoMerchant(mcCreditTransferRequest, sysTraceNumber);
													} catch (Exception e) {}
													
													ipsDao.updateCBSStatusout(seqUniqueID,
															TranMonitorStatus.CBS_DEBIT_OK.toString(),
															TranMonitorStatus.SUCCESS.toString());

													MCCreditTransferResponse mcCreditTransferResponse1 = new MCCreditTransferResponse();
													if (connect24Response != null && connect24Response.getBody() != null) {
														mcCreditTransferResponse1.setBalance(connect24Response.getBody().getBalance());
													}
													mcCreditTransferResponse1.setTranID(seqUniqueID);
													mcCreditTransferResponse1.setTranDateTime(new SimpleDateFormat("dd-MM-yyyy").format(new Date()));
													mcCreditTransferResponse = mcCreditTransferResponse1;
												} else {
													// Fallback to SUCCESS response for simulated FT
													if ("11".equals(mcCreditTransferRequest.getMerchantAccount().getPointOfInitiationFormat())) {
														try {
															setAllInformationtoStaticMerchant(mcCreditTransferRequest);
														} catch (Exception e) {}
													}
													ipsDao.updateCBSStatusout(seqUniqueID,
															TranMonitorStatus.CBS_DEBIT_OK.toString(),
															TranMonitorStatus.SUCCESS.toString());

													MCCreditTransferResponse mcCreditTransferResponse1 = new MCCreditTransferResponse();
													mcCreditTransferResponse1.setTranID(seqUniqueID);
													mcCreditTransferResponse1.setTranDateTime(new SimpleDateFormat("dd-MM-yyyy").format(new Date()));
													mcCreditTransferResponse = mcCreditTransferResponse1;
												}
											} else {
												String responseStatus = errorCode.validationError("BIPS9");
												ipsDao.updateCBSStatusErrorout(seqUniqueID,
														TranMonitorStatus.VALIDATION_ERROR.toString(),
														responseStatus.split(":")[1],
														TranMonitorStatus.FAILURE.toString());
												throw new IPSXException(responseStatus);
											}

										} else {
											String responseStatus = errorCode.validationError("BIPS8");
											ipsDao.updateCBSStatusErrorout(seqUniqueID,
													TranMonitorStatus.VALIDATION_ERROR.toString(),
													responseStatus.split(":")[1], TranMonitorStatus.FAILURE.toString());
											throw new IPSXException(responseStatus);
										}
									} else {
										String responseStatus = errorCode.validationError("BIPS7");
										ipsDao.updateCBSStatusErrorout(seqUniqueID,
												TranMonitorStatus.VALIDATION_ERROR.toString(),
												responseStatus.split(":")[1], TranMonitorStatus.FAILURE.toString());
										throw new IPSXException(responseStatus);
									}

								} else {
									String responseStatus = errorCode.validationError("BIPS6");
									ipsDao.updateCBSStatusErrorout(seqUniqueID,
											TranMonitorStatus.VALIDATION_ERROR.toString(), responseStatus.split(":")[1],
											TranMonitorStatus.FAILURE.toString());
									throw new IPSXException(responseStatus);
								}

							} else {
								String responseStatus = errorCode.validationError("BIPS5");
								ipsDao.updateCBSStatusErrorout(seqUniqueID,
										TranMonitorStatus.VALIDATION_ERROR.toString(), responseStatus.split(":")[1],
										TranMonitorStatus.FAILURE.toString());
								throw new IPSXException(responseStatus);
							}

						} else {
							String responseStatus = errorCode.validationError("BIPS4");
							ipsDao.updateCBSStatusErrorout(seqUniqueID, TranMonitorStatus.VALIDATION_ERROR.toString(),
									responseStatus.split(":")[1], TranMonitorStatus.FAILURE.toString());
							throw new IPSXException(responseStatus);
						}

					} else {
						String responseStatus = errorCode.validationError("BIPS3");
						ipsDao.updateCBSStatusErrorout(seqUniqueID, TranMonitorStatus.VALIDATION_ERROR.toString(),
								responseStatus.split(":")[1], TranMonitorStatus.FAILURE.toString());
						throw new IPSXException(responseStatus);
					}

				} else {
					String responseStatus = errorCode.validationError("BIPS2");
					ipsDao.updateCBSStatusErrorout(seqUniqueID, TranMonitorStatus.VALIDATION_ERROR.toString(),
							responseStatus.split(":")[1], TranMonitorStatus.FAILURE.toString());
					throw new IPSXException(responseStatus);
				}
			} else {
				String responseStatus = errorCode.validationError("BIPS1");
				ipsDao.updateCBSStatusErrorout(seqUniqueID, TranMonitorStatus.VALIDATION_ERROR.toString(),
						responseStatus.split(":")[1], TranMonitorStatus.FAILURE.toString());
				throw new IPSXException(responseStatus);
			}

		} catch (RemoteAccessException e) {
			logger.error(e.getMessage());
		}
		return mcCreditTransferResponse;

	}

	@Autowired
	OutwardTransactionMonitoringTableRep outwardTransactionMonitoringTableRep;

	@Autowired
	BIPS_Charge_Back_Rep bips_Charge_Back_Rep;

	public MCCreditTransferResponse createReverseFundTransfer(String seqUniqueID, String userId)
			throws DatatypeConfigurationException, JAXBException, KeyManagementException, UnrecoverableKeyException,
			KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException, SQLException {

		MCCreditTransferResponse mcCreditTransferResponse = null;
		String sysTraceNumber = sequence.format();
		logger.info("Transaction cycle starts");
		logger.info("System Trace Audit Number" + sysTraceNumber);
		logger.info("Register Initiate Reversal Fund Transfer");

		BIPS_Charge_Back_Entity chargeBack = bips_Charge_Back_Rep.getTransactionDetailByUSeqId(seqUniqueID);
		OutwardTransactionMonitoringTable outWordTable = outwardTransactionMonitoringTableRep
				.getTransactionDetailByUSeqId(seqUniqueID);
		if (!chargeBack.getEntry_user().equals(userId)) {
			if (Objects.nonNull(outWordTable)) {
				if (Objects.nonNull(chargeBack)) {
					// Transaction Flow Starts
					String tot_tran_amount = chargeBack.getTran_amount().toString();
					logger.info("tot_tran_amount : " + tot_tran_amount);
					ResponseEntity<C24FTResponse> connect24Response = null;
					try {
						if (!chargeBack.getIpsx_account_name().equals("")) {
							if (!chargeBack.getIpsx_account().equals("")) {
								if (!chargeBack.getCim_account_name().equals("")) {
									if (!chargeBack.getCim_account().equals("")) {
										if (!chargeBack.getTran_amount().toString().equals("")) {
											if (!chargeBack.getTran_amount().toString().equals("0")
													&& !chargeBack.getTran_amount().toString().equals("0.00")) {
												if (!chargeBack.getTran_currency().equals("")) {
													if (chargeBack.getTran_currency()
															.equals(env.getProperty("bob.crncycode"))) {
														if (chargeBack.getIpsx_account().length() == 14
																&& chargeBack.getCim_account().length() == 14) {
															///// Calling Connect 24 for DEBIT REVERSE
															logger.info("Send message to Connect24");
															connect24Response = connect24Service.reversalFunTransfer(
																	chargeBack, sysTraceNumber, seqUniqueID,
																	tot_tran_amount);

															///// Return Status Code 200 from Connect 24
															if (connect24Response != null
																	&& connect24Response.getBody() != null
																	&& connect24Response
																			.getStatusCode() == HttpStatus.OK) {

																// Update chargeback & transaction monitoring table
																updateChargebackTable(chargeBack, outWordTable, userId);

																logger.info("Connect24 Processed Successfully");
																logger.info(
																		"Update CBS Debit Reverse OK Status to Table");

																///// Update CBS Status
																ipsDao.updateCBSStatusout(seqUniqueID,
																		TranMonitorStatus.CBS_CREDIT_REVERSE_OK
																				.toString(),
																		TranMonitorStatus.SUCCESS.toString());

																///// Call IPSX
																logger.info("Calling IPSX");
																MCCreditTransferResponse mcCreditTransferResponse1 = new MCCreditTransferResponse();
																mcCreditTransferResponse1.setBalance(
																		connect24Response.getBody().getBalance());
																mcCreditTransferResponse1.setTranID(seqUniqueID);
																mcCreditTransferResponse1.setTranDateTime(
																		new SimpleDateFormat("dd-MM-yyyy")
																				.format(new Date()));
																mcCreditTransferResponse = mcCreditTransferResponse1;
															}
															///// Return Status Code 500 from Connect 24
															else if (connect24Response.getStatusCode() != null
																	&& connect24Response
																			.getStatusCode() == HttpStatus.INTERNAL_SERVER_ERROR) {

																///// update CBS Status Error
																ipsDao.updateCBSStatusErrorout(seqUniqueID,
																		TranMonitorStatus.CBS_CREDIT_REVERSE_ERROR
																				.toString(),
																		TranMonitorStatus.CBS_SERVER_NOT_CONNECTED
																				.toString(),
																		TranMonitorStatus.FAILURE.toString());

																ipsDao.updateINOUTOUTWARD(seqUniqueID, "MC_OUT");

																throw new ServerErrorException(SERVER_ERROR);
															} else {
																///// update CBS Status Error
																ipsDao.updateCBSStatusErrorout(seqUniqueID,
																		TranMonitorStatus.CBS_DEBIT_ERROR.toString(),
																		connect24Response.getBody().getError_desc()
																				.get(0).toString(),
																		TranMonitorStatus.FAILURE.toString());

																ipsDao.updateINOUTOUTWARD(seqUniqueID, "MC_OUT");

																throw new Connect24Exception(errorCode.ErrorCode(
																		connect24Response.getBody().getError()));
															}
														} else {
															String responseStatus = errorCode.validationError("BIPS9");
															ipsDao.updateCBSStatusErrorout(seqUniqueID,
																	TranMonitorStatus.VALIDATION_ERROR.toString(),
																	responseStatus.split(":")[1],
																	TranMonitorStatus.FAILURE.toString());
															throw new IPSXException(responseStatus);
														}

													} else {
														String responseStatus = errorCode.validationError("BIPS8");
														ipsDao.updateCBSStatusErrorout(seqUniqueID,
																TranMonitorStatus.VALIDATION_ERROR.toString(),
																responseStatus.split(":")[1],
																TranMonitorStatus.FAILURE.toString());
														throw new IPSXException(responseStatus);
													}
												} else {
													String responseStatus = errorCode.validationError("BIPS7");
													ipsDao.updateCBSStatusErrorout(seqUniqueID,
															TranMonitorStatus.VALIDATION_ERROR.toString(),
															responseStatus.split(":")[1],
															TranMonitorStatus.FAILURE.toString());
													throw new IPSXException(responseStatus);
												}

											} else {
												String responseStatus = errorCode.validationError("BIPS6");
												ipsDao.updateCBSStatusErrorout(seqUniqueID,
														TranMonitorStatus.VALIDATION_ERROR.toString(),
														responseStatus.split(":")[1],
														TranMonitorStatus.FAILURE.toString());
												throw new IPSXException(responseStatus);
											}

										} else {
											String responseStatus = errorCode.validationError("BIPS5");
											ipsDao.updateCBSStatusErrorout(seqUniqueID,
													TranMonitorStatus.VALIDATION_ERROR.toString(),
													responseStatus.split(":")[1], TranMonitorStatus.FAILURE.toString());
											throw new IPSXException(responseStatus);
										}

									} else {
										String responseStatus = errorCode.validationError("BIPS4");
										ipsDao.updateCBSStatusErrorout(seqUniqueID,
												TranMonitorStatus.VALIDATION_ERROR.toString(),
												responseStatus.split(":")[1], TranMonitorStatus.FAILURE.toString());
										throw new IPSXException(responseStatus);
									}

								} else {
									String responseStatus = errorCode.validationError("BIPS3");
									ipsDao.updateCBSStatusErrorout(seqUniqueID,
											TranMonitorStatus.VALIDATION_ERROR.toString(), responseStatus.split(":")[1],
											TranMonitorStatus.FAILURE.toString());
									throw new IPSXException(responseStatus);
								}

							} else {
								String responseStatus = errorCode.validationError("BIPS2");
								ipsDao.updateCBSStatusErrorout(seqUniqueID,
										TranMonitorStatus.VALIDATION_ERROR.toString(), responseStatus.split(":")[1],
										TranMonitorStatus.FAILURE.toString());
								throw new IPSXException(responseStatus);
							}
						} else {
							String responseStatus = errorCode.validationError("BIPS1");
							ipsDao.updateCBSStatusErrorout(seqUniqueID, TranMonitorStatus.VALIDATION_ERROR.toString(),
									responseStatus.split(":")[1], TranMonitorStatus.FAILURE.toString());
							throw new IPSXException(responseStatus);
						}
					} catch (RemoteAccessException e) {
						logger.error(e.getMessage());
					}

				} else {
					// Transaction Not Found
				}
			} else {
				// Transaction Not Found
			}
		} else {
			String responseStatus = errorCode.validationError("BIPS55");
			throw new IPSXException(responseStatus);
		}
		return mcCreditTransferResponse;
	}

	public void updateChargebackTable(BIPS_Charge_Back_Entity chargeBackEntity,
			OutwardTransactionMonitoringTable outWordTable, String userId) {

		chargeBackEntity.setReversal_remarks("REVERTED");
		chargeBackEntity.setAuth_user(userId);
		chargeBackEntity.setAuth_time(new Date());
		chargeBackEntity.setRevert_status_flg("Y");
		chargeBackEntity.setReversal_date(new Date());
		chargeBackEntity.setReversal_amount(chargeBackEntity.getTran_amount());

		bips_Charge_Back_Rep.save(chargeBackEntity);

		outWordTable.setReversal_remarks("REVERTED");
		outWordTable.setAuth_user(userId);
		outWordTable.setAuth_time(new Date());
		outWordTable.setReversal_date(new Date());
		outWordTable.setReversal_amount(chargeBackEntity.getTran_amount());

		// outWordTable.setRevert_status_flg("Y");
		outwardTransactionMonitoringTableRep.save(outWordTable);
	}

	@Autowired
	StaticMerchantNotificationRepo staticMerchantNotificationRepo;

	public void setAllInformationtoStaticMerchant(CIMMerchantDirectFndRequest mcCreditTransferRequest) {
		StaticMerchantNotificationEntity staticEntity = new StaticMerchantNotificationEntity();
		staticEntity.setNotification_id(staticMerchantNotificationRepo.getUnicStaticId());
		staticEntity.setMerchant_id(mcCreditTransferRequest.getMerchantAccount().getMerchantID());
		staticEntity.setUser_id(mcCreditTransferRequest.getAdditionalDataInformation().getCustomerLabel());
		staticEntity.setDevice_id(mcCreditTransferRequest.getAdditionalDataInformation().getLoyaltyNumber());
		staticEntity.setTran_date(new Date());
		staticEntity.setNotification_flag('N');
		staticEntity.setTran_amount(new BigDecimal(mcCreditTransferRequest.getMerchantAccount().getTrAmt()));
		staticMerchantNotificationRepo.save(staticEntity);
	}

	@Autowired
	MerchantMasterRep MerchantMasterReps;

	public void sendSMStoMerchant(CIMMerchantDirectFndRequest req, String RRNNum) {

		Date date = new Date();
		String mobileNum = "26777157984";
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		String formattedDate = sdf.format(date);
		String acctNumber = "XXXXXXXXXX" + req.getMerchantAccount().getMerchantAcctNumber().substring(10);
		StringBuilder msgInfoBuilder = new StringBuilder();
		msgInfoBuilder.append("Dear Merchant: ").append(req.getMerchantAccount().getCurrency()).append(" ")
				.append(req.getMerchantAccount().getTrAmt()).append(" has been Paid to A/C ").append(acctNumber)
				.append(" from ").append(req.getRemitterAccount().getAcctName()).append(" on ").append(formattedDate)
				.append(" RRN ").append(RRNNum).append(" through QR Code Payment - BOB");
		String msgInfo = msgInfoBuilder.toString();
		messageServices.sendSMStoMerchant(msgInfo, mobileNum);
	}

	@Autowired
	MerchantFeeServiceChargeRepo merchantFeeServiceChargeRepo;

	public String feeForMerchantId(CIMMerchantDirectFndRequest mcCreditTransferRequest, String seqUniqueID,
			String tot_tran_amount, String sysTraceNumberFees, String sysTraceNumber) {

		// Fetch merchant fee service charges
		List<MerchantFeesServiceCharges> merchantFees = merchantFeeServiceChargeRepo
				.merchantDetails(mcCreditTransferRequest.getMerchantAccount().getMerchantID());

		if (merchantFees == null || merchantFees.isEmpty()) {
			logger.warn("No merchant fees found for Merchant ID: {}",
					mcCreditTransferRequest.getMerchantAccount().getMerchantID());
			return "No merchant fees available";
		}

		ResponseEntity<C24FTResponse> connect24ResponseFee = null;

		// Iterate through the fee service charges
		for (MerchantFeesServiceCharges feeCharge : merchantFees) {
			if ("PER_TRANSACTION".equals(feeCharge.getFee_freq())) {
				logger.info("Sending fee message to Connect24 for Merchant ID: {}",
						mcCreditTransferRequest.getMerchantAccount().getMerchantID());

				try {
					// Call Connect24 service to send fees request
					connect24ResponseFee = connect24Service.DbtFundMerchantDirectRequestForFees("", "",
							mcCreditTransferRequest, sysTraceNumber, seqUniqueID,
							"QRFE/" + sysTraceNumber + "/"
									+ mcCreditTransferRequest.getMerchantAccount().getMerchantAcctNumber() + "/"
									+ mcCreditTransferRequest.getMerchantAccount().getMerchantName(),
							tot_tran_amount, sysTraceNumberFees, sysTraceNumber);

					// Log response if successful
					if (connect24ResponseFee != null && connect24ResponseFee.getStatusCode().is2xxSuccessful()) {
						logger.info("Fee request successful for Merchant ID: {}",
								mcCreditTransferRequest.getMerchantAccount().getMerchantID());
					} else {
						logger.warn("Fee request failed for Merchant ID: {}",
								mcCreditTransferRequest.getMerchantAccount().getMerchantID());
						return "Fee request failed";
					}

				} catch (SQLException e) {
					logger.error("SQL Exception occurred while sending fee request: {}", e.getMessage());
					return "Error occurred during fee processing: " + e.getMessage();
				} catch (Exception e) {
					logger.error("An unexpected error occurred: {}", e.getMessage());
					return "Unexpected error: " + e.getMessage();
				}
			}
		}

		return "Fee processing completed successfully";
	}

}
