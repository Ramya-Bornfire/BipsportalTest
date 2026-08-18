package com.bornfire.controller;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.HibernateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;

import com.bornfire.config.ErrorResponseCode;
import com.bornfire.config.Listener;
import com.bornfire.config.SequenceGenerator;
import com.bornfire.entity.BIPS_Charge_Back_Entity;
import com.bornfire.entity.BIPS_Charge_Back_Rep;
import com.bornfire.entity.BankAgentTable;
import com.bornfire.entity.BankAgentTableRep;
import com.bornfire.entity.CIMCustomerQRcodeRequest;
import com.bornfire.entity.CIMMerchantDirectFndRequest;
import com.bornfire.entity.CIMMerchantQRcodeRequest;
import com.bornfire.entity.CusotmerQrGenTablerep;
import com.bornfire.entity.MerchantQrGenTable;
import com.bornfire.entity.MerchantQrGenTablerep;
import com.bornfire.entity.OutwardTransactionMonitoringTable;
import com.bornfire.entity.OutwardTransactionMonitoringTableRep;
import com.bornfire.entity.SettlementAccountRep;
import com.bornfire.entity.TranCBSTable;
import com.bornfire.entity.TranCBSTableRep;
import com.bornfire.entity.TranCimCBSTable;
import com.bornfire.entity.TranCimCBSTableRep;
import com.bornfire.entity.TranIPSTableRep;
import com.bornfire.entity.TranMonitorStatus;
import com.bornfire.exception.IPSXException;

@Service
//@Transactional
public class IpsDao {
	@Autowired
	Environment env;
	@Autowired
	SequenceGenerator sequence;
	@Autowired
	SettlementAccountRep settlAccountRep;

	@Autowired
	MerchantQrGenTablerep mercantQrGenTableRep;

	@Autowired
	CimCBSservice cimCBSservice;

	@Autowired
	ErrorResponseCode errorCode;
	@Autowired
	TaskExecutor taskExecutor;

	@Autowired
	Listener listener;

	@Autowired
	TranCimCBSTableRep tranCimCBSTableRep;

	@Autowired
	BankAgentTableRep bankAgentTableRep;

	@Autowired
	OutwardTransactionMonitoringTableRep outwardTranRep;

	@Autowired
	CusotmerQrGenTablerep customerqrgentable;

	@Autowired
	TranIPSTableRep tranIPStableRep;

	@Autowired
	TranCBSTableRep tranCBSTableRep;

	@PersistenceContext
	EntityManager entityManager;

	private static final Logger logger = LoggerFactory.getLogger(IpsDao.class);

	public BankAgentTable findByBank(String bankAgent) {
		BankAgentTable tm = new BankAgentTable();
		try {
			Optional<BankAgentTable> otm = bankAgentTableRep.findByCustomBankName(bankAgent);
			if (otm.isPresent()) {
				tm = otm.get();
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return tm;
	}

	public void updateMerchantQRData(String p_id, String status, String reason) {
		Optional<MerchantQrGenTable> data = mercantQrGenTableRep.findById(p_id);
		if (data.isPresent()) {
			MerchantQrGenTable subData = data.get();
			subData.setStatus(status);
			if (status.equals("SUCCESS")) {
				byte[] decodedBytesQR = Base64.getDecoder().decode(reason);
				subData.setQr_code(decodedBytesQR);
			} else {
				subData.setReason(reason);
			}
			mercantQrGenTableRep.save(subData);
		}
	}

	public String registerCIMcbsIncomingData(String requestUUID, String channelId, String serviveReqVersion,
			String serviceReqId, Date msgDate, String tranNumber, String initChannel, String initTranNumber,
			String postToCbs, String tran_type, String isReversal, String tran_numberFromCbs, String acctNumber,
			String trAmt, String currency, String seqUniqueID, String debrAcctNumber, String debtAcctName,
			String tran_part_code, String debit_remarks, String credit_remarks, String resv_field1, String res_field2,
			Date valueDate, String settlType, String init_sub_tran_no, String error_code, String error_msg,
			String ipsMasterRefId, String debitoragent, String creditoragent) {

		String response = "0";
		try {
			TranCimCBSTable tranCimCBSTable = new TranCimCBSTable();

			BankAgentTable remitterBank = findByBank(debitoragent);
			BankAgentTable benificiaryBank = findByBank(creditoragent);

			tranCimCBSTable.setSequence_unique_id(seqUniqueID);
			tranCimCBSTable.setRequest_uuid(requestUUID);
			tranCimCBSTable.setChannel_id(channelId);
			tranCimCBSTable.setService_request_version(serviveReqVersion);
			tranCimCBSTable.setService_request_id(serviceReqId);
			tranCimCBSTable.setMessage_date_time(msgDate);

			tranCimCBSTable.setTran_no(tranNumber);
			tranCimCBSTable.setInit_channel(initChannel);
			tranCimCBSTable.setInit_tran_no(initTranNumber);
			tranCimCBSTable.setPost_to_cbs(postToCbs);
			tranCimCBSTable.setTran_type(tran_type);
			tranCimCBSTable.setIsreversal(isReversal);
			tranCimCBSTable.setTran_no_from_cbs(tran_numberFromCbs);
			tranCimCBSTable.setCustomer_name(debtAcctName);
			tranCimCBSTable.setFrom_account_no(debrAcctNumber);
			tranCimCBSTable.setTo_account_no(acctNumber);
			tranCimCBSTable.setTran_amt(new BigDecimal(trAmt));
			tranCimCBSTable.setTran_date(new Date());
			tranCimCBSTable.setTran_currency(currency);
			tranCimCBSTable.setTran_particular_code(tran_part_code);
			tranCimCBSTable.setDebit_remarks(debit_remarks);
			tranCimCBSTable.setCredit_remarks(credit_remarks);
			tranCimCBSTable.setResv_field_1(resv_field1);
			tranCimCBSTable.setResv_field_2(res_field2);
			tranCimCBSTable.setValue_date(valueDate);
			tranCimCBSTable.setSettl_acct_type(settlType);
			tranCimCBSTable.setInit_sub_tran_no(init_sub_tran_no);
			tranCimCBSTable.setError_code(error_code);
			tranCimCBSTable.setError_msg(error_msg);
			tranCimCBSTable.setIps_master_ref_id(ipsMasterRefId);
			tranCimCBSTable.setRemitterbank(env.getProperty("ipsx.remitterbank"));
			tranCimCBSTable.setRemitterbankcode(remitterBank.getBank_code());
			tranCimCBSTable.setRemitterswiftcode(remitterBank.getBank_agent());
			tranCimCBSTable.setBeneficiarybank(benificiaryBank.getBank_name());
			tranCimCBSTable.setBeneficiarybankcode(benificiaryBank.getBank_code());
			tranCimCBSTable.setBeneficiaryswiftcode(benificiaryBank.getBank_agent());
			tranCimCBSTableRep.saveAndFlush(tranCimCBSTable);
			response = "1";

		} catch (Exception e) {
			System.out.print(e.getLocalizedMessage());
			response = "0";
		}

		return response;
	}

	public String RegisterMerchantOutgoingMasterRecord(String psuDeviceID, String psuIpAddress, String sysTraceNumber,
			String bobMsgID, String seqUniqueID, String endTOEndID, String master_ref_id, String msgNetMIR,
			String instg_agt, String instd_agt, String dbtr_agt, String dbtr_agt_acc, String cdtr_agt,
			String cdtr_agt_acc, String instr_id, String svc_lvl, String lcl_instrm, String ctgy_purp,
			String tran_type_code, String remitterAcctName, String remitterAcctNumber, String bank_code,
			String remitterbank_code, String currencyCode, String benAcctName, String benAcctNumber, String reqUniqueId,
			String trAmt, String trRmks, String p_id, String req_unique_id, String channelID, String resvfield1,
			String resvfield2, String chrBearer, String RmtInfo, CIMMerchantDirectFndRequest cimMerchantRequest) {

		String status = "0";
		try {
			System.out.print("Request---->" + cimMerchantRequest);
			OutwardTransactionMonitoringTable tranManitorTable = new OutwardTransactionMonitoringTable();
			tranManitorTable.setP_id(p_id);
			tranManitorTable.setReq_unique_id(cimMerchantRequest.getReqUniqueId());
			tranManitorTable.setInit_channel_id(channelID);
			tranManitorTable.setResv_field1(resvfield1);
			tranManitorTable.setResv_field2(resvfield2);
			tranManitorTable.setTran_rmks(cimMerchantRequest.getTrRmks());
			tranManitorTable.setMsg_type(TranMonitorStatus.OUTGOING.toString());
			tranManitorTable.setTran_audit_number(sysTraceNumber);
			tranManitorTable.setSequence_unique_id(seqUniqueID);
			tranManitorTable.setCim_message_id(bobMsgID);
			tranManitorTable.setCim_account(benAcctNumber);
			tranManitorTable.setIpsx_account(remitterAcctNumber);
			tranManitorTable.setReceiver_bank(bank_code);
			tranManitorTable.setInitiator_bank(env.getProperty("ipsx.remitterbank"));
			tranManitorTable.setTran_amount(new BigDecimal(trAmt));
			tranManitorTable.setTran_date(new Date());
			tranManitorTable.setEntry_time(new Date());
			tranManitorTable.setCbs_status(TranMonitorStatus.CBS_DEBIT_INITIATED.toString());
			tranManitorTable.setTran_currency(currencyCode);
			tranManitorTable.setTran_status(TranMonitorStatus.INITIATED.toString());
			//tranManitorTable.setDevice_id(psuDeviceID);
			tranManitorTable.setDevice_ip(psuIpAddress);
			tranManitorTable.setNat_id("");
			tranManitorTable.setMaster_ref_id(master_ref_id);

			tranManitorTable.setEnd_end_id(endTOEndID);
			tranManitorTable.setCim_account_name(benAcctName);
			tranManitorTable.setIpsx_account_name(remitterAcctName);

			tranManitorTable.setTran_type_code(tran_type_code);
			tranManitorTable.setNet_mir(msgNetMIR);
			tranManitorTable.setInstg_agt(instg_agt);
			tranManitorTable.setInstd_agt(instd_agt);

			tranManitorTable.setDbtr_agt(dbtr_agt);
			tranManitorTable.setDbtr_agt_acc(dbtr_agt_acc);
			tranManitorTable.setCdtr_agt(cdtr_agt);
			tranManitorTable.setCdtr_agt_acc(cdtr_agt_acc);

			tranManitorTable.setInstr_id(instr_id);
			tranManitorTable.setSvc_lvl(svc_lvl);
			tranManitorTable.setLcl_instrm(lcl_instrm);
			tranManitorTable.setCtgy_purp(ctgy_purp);
			tranManitorTable.setChrg_br(chrBearer);
			 
			tranManitorTable.setBill_date(new Date());
			tranManitorTable.setBill_amount(new BigDecimal(trAmt));
			tranManitorTable.setRmt_info(RmtInfo);

			tranManitorTable.setGlobal_id(cimMerchantRequest.getMerchantAccount().getGlobalID());
			tranManitorTable.setPoint_init(cimMerchantRequest.getMerchantAccount().getPointOfInitiationFormat());
			tranManitorTable.setMerchant_id(cimMerchantRequest.getMerchantAccount().getMerchantID());
			tranManitorTable.setMcc(cimMerchantRequest.getMerchantAccount().getMCC());
			/*
			 * if(!String.valueOf(cimMerchantRequest.getMerchantAccount().
			 * getTipOrConvenienceIndicator()).equals("null")&&
			 * !String.valueOf(cimMerchantRequest.getMerchantAccount().
			 * getTipOrConvenienceIndicator()).equals("")) {
			 * tranManitorTable.setConv_flg("Y");
			 * //tranManitorTable.setConv_fee_type(cimMerchantRequest.getMerchantAccount().
			 * getConvenienceIndicatorFeeType());
			 * tranManitorTable.setConv_fee(cimMerchantRequest.getMerchantAccount().
			 * getConvenienceIndicatorFee()); }
			 */

			if (!String.valueOf(cimMerchantRequest.getMerchantAccount().getTipOrConvenienceIndicator()).equals("null")
					&& !String.valueOf(cimMerchantRequest.getMerchantAccount().getTipOrConvenienceIndicator())
							.equals("")) {

				if (String.valueOf(cimMerchantRequest.getMerchantAccount().getTipOrConvenienceIndicator())
						.equals("01")) {
					tranManitorTable.setTip_or_conv_indicator("01");
					tranManitorTable.setTip_amount(cimMerchantRequest.getMerchantAccount().getTipAmt());
				} else if (String.valueOf(cimMerchantRequest.getMerchantAccount().getTipOrConvenienceIndicator())
						.equals("02")) {
					tranManitorTable.setTip_or_conv_indicator("02");
					tranManitorTable
							.setConv_amount(cimMerchantRequest.getMerchantAccount().getConvenienceIndicatorFee());
				} else if (String.valueOf(cimMerchantRequest.getMerchantAccount().getTipOrConvenienceIndicator())
						.equals("03")) {
					tranManitorTable.setTip_or_conv_indicator("03");
					tranManitorTable
							.setConv_amount(cimMerchantRequest.getMerchantAccount().getConvenienceIndicatorFee());
				}

			}

			tranManitorTable.setMerchant_city(cimMerchantRequest.getMerchantAccount().getCity());
			tranManitorTable.setMerchant_cntry(cimMerchantRequest.getMerchantAccount().getCountryCode());

			if (!String.valueOf(cimMerchantRequest.getMerchantAccount().getPostalCode()).equals("null")
					&& !String.valueOf(cimMerchantRequest.getMerchantAccount().getPostalCode()).equals("")) {
				tranManitorTable.setMerchant_postal_cd(cimMerchantRequest.getMerchantAccount().getPostalCode());
			}

			if (cimMerchantRequest.getAdditionalDataInformation() != null) {

				if (!String.valueOf(cimMerchantRequest.getAdditionalDataInformation().getBillNumber()).equals("null")
						&& !String.valueOf(cimMerchantRequest.getAdditionalDataInformation().getBillNumber())
								.equals("")) {
					tranManitorTable
							.setMerchant_bill_number(cimMerchantRequest.getAdditionalDataInformation().getBillNumber());
				}
				if (!String.valueOf(cimMerchantRequest.getAdditionalDataInformation().getMobileNumber()).equals("null")
						&& !String.valueOf(cimMerchantRequest.getAdditionalDataInformation().getMobileNumber())
								.equals("")) {
					tranManitorTable
							.setMerchant_mobile(cimMerchantRequest.getAdditionalDataInformation().getMobileNumber());
				}
				if (!String.valueOf(cimMerchantRequest.getAdditionalDataInformation().getStoreLabel()).equals("null")
						&& !String.valueOf(cimMerchantRequest.getAdditionalDataInformation().getStoreLabel())
								.equals("")) {
					tranManitorTable
							.setMerchant_store_label(cimMerchantRequest.getAdditionalDataInformation().getStoreLabel());
					tranManitorTable.setUnit_id(cimMerchantRequest.getAdditionalDataInformation().getStoreLabel());
				}
				if (!String.valueOf(cimMerchantRequest.getAdditionalDataInformation().getLoyaltyNumber()).equals("null")
						&& !String.valueOf(cimMerchantRequest.getAdditionalDataInformation().getLoyaltyNumber())
								.equals("")) {
					tranManitorTable.setMerchant_loyalty_number(
							cimMerchantRequest.getAdditionalDataInformation().getLoyaltyNumber());
				}
				if (!String.valueOf(cimMerchantRequest.getAdditionalDataInformation().getDeviceID()).equals("null")
						&& !String.valueOf(cimMerchantRequest.getAdditionalDataInformation().getDeviceID())
								.equals("")) {
					tranManitorTable.setDevice_id(cimMerchantRequest.getAdditionalDataInformation().getDeviceID());
				}
				if (!String.valueOf(cimMerchantRequest.getAdditionalDataInformation().getReferenceLabel())
						.equals("null")
						&& !String.valueOf(cimMerchantRequest.getAdditionalDataInformation().getReferenceLabel())
								.equals("")) {
					tranManitorTable.setMerchant_ref_label(
							cimMerchantRequest.getAdditionalDataInformation().getReferenceLabel());
				}
				if (!String.valueOf(cimMerchantRequest.getAdditionalDataInformation().getCustomerLabel()).equals("null")
						&& !String.valueOf(cimMerchantRequest.getAdditionalDataInformation().getCustomerLabel())
								.equals("")) {
					tranManitorTable.setMerchant_customer_label(
							cimMerchantRequest.getAdditionalDataInformation().getCustomerLabel());
					tranManitorTable.setUser_id(cimMerchantRequest.getAdditionalDataInformation().getCustomerLabel());
				}
				if (!String.valueOf(cimMerchantRequest.getAdditionalDataInformation().getTerminalLabel()).equals("null")
						&& !String.valueOf(cimMerchantRequest.getAdditionalDataInformation().getTerminalLabel())
								.equals("")) {
					tranManitorTable.setMerchant_terminal_label(
							cimMerchantRequest.getAdditionalDataInformation().getTerminalLabel());
				}
				if (!String.valueOf(cimMerchantRequest.getAdditionalDataInformation().getPurposeOfTransaction())
						.equals("null")) {
					tranManitorTable.setMerchant_purp_tran(
							cimMerchantRequest.getAdditionalDataInformation().getPurposeOfTransaction());
				}
				if (!String.valueOf(cimMerchantRequest.getAdditionalDataInformation().getAddlDataRequest())
						.equals("null")) {
					tranManitorTable.setMerchant_addl_data_request(
							cimMerchantRequest.getAdditionalDataInformation().getAddlDataRequest());
				}
			}

			//// Check CutOff time after BOB settlement time
			//// if yes the value date is +1
			if (isTimeAfterCutOff()) {
				Date dt = new Date();
				Calendar c = Calendar.getInstance();
				c.setTime(dt);
				c.add(Calendar.DATE, 1);
				dt = c.getTime();
				tranManitorTable.setValue_date(dt);
			} else {
				tranManitorTable.setValue_date(new Date());
			}

			outwardTranRep.save(tranManitorTable);

			status = "1";

		} catch (Exception e) {
			System.err.println(e.getMessage());
			status = "0";
		}

		return status;
	}

	public String regCustomerQR(String p_id, String psuDeviceID, String psuIpAddress, String channelID,
			CIMCustomerQRcodeRequest qrrequest) {
		String status = "0";
		//System.out.println("status---------->" + status);
		try {
			MerchantQrGenTable merchantQrGenTable = new MerchantQrGenTable();
			merchantQrGenTable.setP_id(p_id);
			merchantQrGenTable.setPsu_device_id(psuDeviceID);
			merchantQrGenTable.setPsu_ip_address(psuIpAddress);
			merchantQrGenTable.setPsu_channel(channelID);
			merchantQrGenTable.setPayload_format_indicator(qrrequest.getPayloadFormatIndiator());
			merchantQrGenTable.setPoi_method(qrrequest.getPointOfInitiationFormat());
			merchantQrGenTable.setGlobal_unique_id(qrrequest.getPayeeAccountInformation().getGlobalID());
			merchantQrGenTable
					.setPayee_participant_code(qrrequest.getPayeeAccountInformation().getPayeeParticipantCode());
			merchantQrGenTable.setMerchant_id(qrrequest.getPayeeAccountInformation().getCustomerID());
			merchantQrGenTable.setTransaction_crncy(qrrequest.getCurrency());
			merchantQrGenTable.setCountry(qrrequest.getCountryCode());
			merchantQrGenTable.setMerchant_name(qrrequest.getCustomerName());
			merchantQrGenTable.setCity(qrrequest.getCity());
			merchantQrGenTable.setBill_number(qrrequest.getAdditionalDataInformation().getBillNumber());
			merchantQrGenTable.setMobile(qrrequest.getAdditionalDataInformation().getMobileNumber());
			merchantQrGenTable.setReference_label(qrrequest.getAdditionalDataInformation().getReferenceNumber());
			merchantQrGenTable.setCustomer_label(qrrequest.getAdditionalDataInformation().getCustomerLabel());
			merchantQrGenTable.setTerminal_label(qrrequest.getAdditionalDataInformation().getTerminalLabel());
			merchantQrGenTable.setPurpose_of_tran(qrrequest.getAdditionalDataInformation().getPurposeOfTransaction());
			merchantQrGenTable.setStore_label(qrrequest.getAdditionalDataInformation().getStoreLabel());
			merchantQrGenTable.setLoyalty_number(qrrequest.getAdditionalDataInformation().getdeviceID());
			merchantQrGenTable.setAdditional_details(qrrequest.getAdditionalDataInformation().getAddlDataRequest());
			merchantQrGenTable.setEntry_time(new Date());
			//System.out.println("MerchantQRGEnTable--------->" + merchantQrGenTable.toString());
			mercantQrGenTableRep.save(merchantQrGenTable);
			//System.out.println("Savings in db");
			status = "1";
		} catch (Exception e) {
			status = "0";
		}
		return status;
	}

	public String regMerchantQR(String p_id, String psuDeviceID, String psuIpAddress, String channelID,
			CIMMerchantQRcodeRequest qrrequest,String userId,String unitId,String terminalId) {
		String status = "0";
		//System.out.println("status---------->" + status);
		try {
			MerchantQrGenTable merchantQrGenTable = new MerchantQrGenTable();
			merchantQrGenTable.setP_id(p_id);
			merchantQrGenTable.setPsu_device_id(psuDeviceID);
			merchantQrGenTable.setPsu_ip_address(psuIpAddress);
			merchantQrGenTable.setPsu_channel(channelID);
			merchantQrGenTable.setPayload_format_indicator(qrrequest.getPayloadFormatIndiator());
			merchantQrGenTable.setPoi_method(qrrequest.getPointOfInitiationFormat());
			merchantQrGenTable.setGlobal_unique_id(qrrequest.getMerchantAcctInformation().getGlobalID());
			merchantQrGenTable
					.setPayee_participant_code(qrrequest.getMerchantAcctInformation().getPayeeParticipantCode());
			merchantQrGenTable.setMerchant_acct_no(qrrequest.getMerchantAcctInformation().getMerchantAcctNumber());
			merchantQrGenTable.setMerchant_id(qrrequest.getMerchantAcctInformation().getMerchantID());
			merchantQrGenTable.setMerchant_category_code(qrrequest.getMCC());
			merchantQrGenTable.setTransaction_crncy(qrrequest.getCurrency());
			merchantQrGenTable.setTip_or_conv_indicator(qrrequest.getTipOrConvenienceIndicator());
			merchantQrGenTable.setValue_conv_fees(qrrequest.getConvenienceIndicatorFee());
			merchantQrGenTable.setCountry(qrrequest.getCountryCode());
			merchantQrGenTable.setMerchant_name(qrrequest.getMerchantName());
			merchantQrGenTable.setCity(qrrequest.getCity());
			merchantQrGenTable.setZip_code(qrrequest.getPostalCode());
			merchantQrGenTable.setBill_number(qrrequest.getAdditionalDataInformation().getBillNumber());
			merchantQrGenTable.setMobile(qrrequest.getAdditionalDataInformation().getMobileNumber());
			merchantQrGenTable.setReference_label(qrrequest.getAdditionalDataInformation().getReferenceLabel());
			merchantQrGenTable.setCustomer_label(qrrequest.getAdditionalDataInformation().getCustomerLabel());
			merchantQrGenTable.setTerminal_label(qrrequest.getAdditionalDataInformation().getTerminalLabel());
			merchantQrGenTable.setPurpose_of_tran(qrrequest.getAdditionalDataInformation().getPurposeOfTransaction());
			merchantQrGenTable.setStore_label(qrrequest.getAdditionalDataInformation().getStoreLabel());
			merchantQrGenTable.setLoyalty_number(qrrequest.getAdditionalDataInformation().getLoyaltyNumber());
			merchantQrGenTable.setAdditional_details(qrrequest.getAdditionalDataInformation().getAddlDataRequest());
			merchantQrGenTable.setEntry_time(new Date());
			merchantQrGenTable.setUser_id(userId);
			merchantQrGenTable.setUnit_id(unitId);
			merchantQrGenTable.setTerminal_id(terminalId);
			//System.out.println("MerchantQRGEnTable--------->" + merchantQrGenTable);
			mercantQrGenTableRep.save(merchantQrGenTable);
			//System.out.println("Savings in db");
			status = "1";
		} catch (Exception e) {
			status = "0";
		}
		return status;
	}

	public String RegisterMerchantOutgoingMasterRecord(String psuDeviceID, String psuIpAddress, String sysTraceNumber,
			String bobMsgID, String seqUniqueID, String endTOEndID, String master_ref_id, String msgNetMIR,
			String instg_agt, String dbtr_agt, String dbtr_agt_acc, String instr_id, String svc_lvl, String lcl_instrm,
			String ctgy_purp, String remitterAcctName, String remitterAcctNumber, String currencyCode,
			String benAcctName, String benAcctNumber, String reqUniqueId, String trAmt, String trRmks, String p_id,
			String req_unique_id, String userid, String unit_id, String unit_name, String channelID, String resvfield1,
			String resvfield2, String RmtInfo, CIMMerchantDirectFndRequest cimMerchantRequest) {
		String status = "0";
		try {
			OutwardTransactionMonitoringTable tranManitorTable = new OutwardTransactionMonitoringTable();
			tranManitorTable.setP_id(p_id);
			tranManitorTable.setReq_unique_id(cimMerchantRequest.getReqUniqueId());
			tranManitorTable.setInit_channel_id(channelID);
			tranManitorTable.setResv_field1(resvfield1);
			tranManitorTable.setResv_field2(resvfield2);
			tranManitorTable.setTran_rmks(cimMerchantRequest.getTrRmks());
			tranManitorTable.setMsg_type(TranMonitorStatus.OUTGOING.toString());
			tranManitorTable.setTran_audit_number(sysTraceNumber);
			tranManitorTable.setSequence_unique_id(seqUniqueID);
			tranManitorTable.setCim_message_id(bobMsgID);
			tranManitorTable.setCim_account(benAcctNumber);
			tranManitorTable.setIpsx_account(remitterAcctNumber);
			tranManitorTable.setTran_amount(new BigDecimal(trAmt));
			tranManitorTable.setTran_date(new Date());
			tranManitorTable.setEntry_time(new Date());
			tranManitorTable.setCbs_status(TranMonitorStatus.CBS_DEBIT_INITIATED.toString());
			tranManitorTable.setTran_currency(currencyCode);
			tranManitorTable.setTran_status(TranMonitorStatus.SUCCESS.toString());
		//	tranManitorTable.setDevice_id(psuDeviceID);
			tranManitorTable.setDevice_ip(psuIpAddress);
			tranManitorTable.setNat_id("");
			tranManitorTable.setMaster_ref_id(master_ref_id);
			tranManitorTable.setUser_id(userid);
			tranManitorTable.setUnit_id(unit_id);
			tranManitorTable.setUnit_name(unit_name);
			tranManitorTable.setEnd_end_id(endTOEndID);
			tranManitorTable.setBill_amount(new BigDecimal(trAmt));
			tranManitorTable.setBill_date(new Date());
			tranManitorTable.setCim_account_name(benAcctName);
			tranManitorTable.setIpsx_account_name(remitterAcctName);
			tranManitorTable.setNet_mir(msgNetMIR);
			tranManitorTable.setInstg_agt(instg_agt);
			tranManitorTable.setDbtr_agt(dbtr_agt);
			tranManitorTable.setDbtr_agt_acc(dbtr_agt_acc);
			tranManitorTable.setInstr_id(instr_id);
			tranManitorTable.setSvc_lvl(svc_lvl);
			tranManitorTable.setLcl_instrm(lcl_instrm);
			tranManitorTable.setCtgy_purp(ctgy_purp);
			tranManitorTable.setRmt_info(RmtInfo);
			tranManitorTable.setGlobal_id(cimMerchantRequest.getMerchantAccount().getGlobalID());
			tranManitorTable.setPoint_init(cimMerchantRequest.getMerchantAccount().getPointOfInitiationFormat());
			tranManitorTable.setMerchant_id(cimMerchantRequest.getMerchantAccount().getMerchantID());
			tranManitorTable.setMcc(cimMerchantRequest.getMerchantAccount().getMCC());
			if (!String.valueOf(cimMerchantRequest.getMerchantAccount().getTipOrConvenienceIndicator()).equals("null")
					&& !String.valueOf(cimMerchantRequest.getMerchantAccount().getTipOrConvenienceIndicator())
							.equals("")) {

				if (String.valueOf(cimMerchantRequest.getMerchantAccount().getTipOrConvenienceIndicator())
						.equals("01")) {
					tranManitorTable.setTip_or_conv_indicator("01");
					tranManitorTable.setTip_amount(cimMerchantRequest.getMerchantAccount().getTipAmt());
				} else if (String.valueOf(cimMerchantRequest.getMerchantAccount().getTipOrConvenienceIndicator())
						.equals("02")) {
					tranManitorTable.setTip_or_conv_indicator("02");
					tranManitorTable
							.setConv_amount(cimMerchantRequest.getMerchantAccount().getConvenienceIndicatorFee());
				} else if (String.valueOf(cimMerchantRequest.getMerchantAccount().getTipOrConvenienceIndicator())
						.equals("03")) {
					tranManitorTable.setTip_or_conv_indicator("03");
					tranManitorTable
							.setConv_amount(cimMerchantRequest.getMerchantAccount().getConvenienceIndicatorFee());
				}
			}
			tranManitorTable.setMerchant_city(cimMerchantRequest.getMerchantAccount().getCity());
			tranManitorTable.setMerchant_cntry(cimMerchantRequest.getMerchantAccount().getCountryCode());
			tranManitorTable.setInitiator_bank("BOB");
			if (!String.valueOf(cimMerchantRequest.getMerchantAccount().getPostalCode()).equals("null")
					&& !String.valueOf(cimMerchantRequest.getMerchantAccount().getPostalCode()).equals("")) {
				tranManitorTable.setMerchant_postal_cd(cimMerchantRequest.getMerchantAccount().getPostalCode());
			}
			if (cimMerchantRequest.getAdditionalDataInformation() != null) {
				if (!String.valueOf(cimMerchantRequest.getAdditionalDataInformation().getBillNumber()).equals("null")
						&& !String.valueOf(cimMerchantRequest.getAdditionalDataInformation().getBillNumber())
								.equals("")) {
					tranManitorTable
							.setMerchant_bill_number(cimMerchantRequest.getAdditionalDataInformation().getBillNumber());
				}
				if (!String.valueOf(cimMerchantRequest.getAdditionalDataInformation().getMobileNumber()).equals("null")
						&& !String.valueOf(cimMerchantRequest.getAdditionalDataInformation().getMobileNumber())
								.equals("")) {
					tranManitorTable
							.setMerchant_mobile(cimMerchantRequest.getAdditionalDataInformation().getMobileNumber());
				}
				if (!String.valueOf(cimMerchantRequest.getAdditionalDataInformation().getStoreLabel()).equals("null")
						&& !String.valueOf(cimMerchantRequest.getAdditionalDataInformation().getStoreLabel())
								.equals("")) {
					tranManitorTable
							.setMerchant_store_label(cimMerchantRequest.getAdditionalDataInformation().getStoreLabel());
					tranManitorTable.setUnit_id(cimMerchantRequest.getAdditionalDataInformation().getStoreLabel());
				}
				if (!String.valueOf(cimMerchantRequest.getAdditionalDataInformation().getLoyaltyNumber()).equals("null")
						&& !String.valueOf(cimMerchantRequest.getAdditionalDataInformation().getLoyaltyNumber())
								.equals("")) {
					tranManitorTable.setMerchant_loyalty_number(
							cimMerchantRequest.getAdditionalDataInformation().getLoyaltyNumber());
				}
				if (!String.valueOf(cimMerchantRequest.getAdditionalDataInformation().getReferenceLabel())
						.equals("null")
						&& !String.valueOf(cimMerchantRequest.getAdditionalDataInformation().getReferenceLabel())
								.equals("")) {
					tranManitorTable.setMerchant_ref_label(
							cimMerchantRequest.getAdditionalDataInformation().getReferenceLabel());
				}
				if (!String.valueOf(cimMerchantRequest.getAdditionalDataInformation().getCustomerLabel()).equals("null")
						&& !String.valueOf(cimMerchantRequest.getAdditionalDataInformation().getCustomerLabel())
								.equals("")) {
					tranManitorTable.setMerchant_customer_label(
							cimMerchantRequest.getAdditionalDataInformation().getCustomerLabel());
					tranManitorTable.setUser_id(cimMerchantRequest.getAdditionalDataInformation().getCustomerLabel());
				}
				if (!String.valueOf(cimMerchantRequest.getAdditionalDataInformation().getDeviceID()).equals("null")
						&& !String.valueOf(cimMerchantRequest.getAdditionalDataInformation().getDeviceID())
								.equals("")) {
					tranManitorTable.setDevice_id(cimMerchantRequest.getAdditionalDataInformation().getDeviceID());
				}
				if (!String.valueOf(cimMerchantRequest.getAdditionalDataInformation().getTerminalLabel()).equals("null")
						&& !String.valueOf(cimMerchantRequest.getAdditionalDataInformation().getTerminalLabel())
								.equals("")) {
					tranManitorTable.setMerchant_terminal_label(
							cimMerchantRequest.getAdditionalDataInformation().getTerminalLabel());
				}
				if (!String.valueOf(cimMerchantRequest.getAdditionalDataInformation().getPurposeOfTransaction())
						.equals("null")
						&& !String.valueOf(cimMerchantRequest.getAdditionalDataInformation().getPurposeOfTransaction())
								.equals("")) {
					tranManitorTable.setMerchant_purp_tran(
							cimMerchantRequest.getAdditionalDataInformation().getPurposeOfTransaction());
				}
				if (!String.valueOf(cimMerchantRequest.getAdditionalDataInformation().getAddlDataRequest())
						.equals("null")
						&& !String.valueOf(cimMerchantRequest.getAdditionalDataInformation().getAddlDataRequest())
								.equals("")) {
					tranManitorTable.setMerchant_addl_data_request(
							cimMerchantRequest.getAdditionalDataInformation().getAddlDataRequest());
				}
			}

			//// Check CutOff time after BOB settlement time
			//// if yes the value date is +1
			if (isTimeAfterCutOff()) {
				Date dt = new Date();
				Calendar c = Calendar.getInstance();
				c.setTime(dt);
				c.add(Calendar.DATE, 1);
				dt = c.getTime();
				tranManitorTable.setValue_date(dt);
			} else {
				tranManitorTable.setValue_date(new Date());
			}
			outwardTranRep.save(tranManitorTable);
			status = "1";
		} catch (Exception e) {
			System.err.println(e.getMessage());
			status = "0";
		}
		return status;
	}

	boolean isTimeAfterCutOff() throws ParseException {
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
		dateFormat.format(date);
		if (dateFormat.parse(dateFormat.format(date)).after(dateFormat.parse(env.getProperty("bob.cutofftime")))) {
			return true;
		} else {
			return false;
		}
	}

	public boolean invalidP_ID(String pid) {
		boolean valid = false;
		try {
			List<Object[]> otm = outwardTranRep.existsByPID(pid);
			if (otm.size() > 0) {
				valid = false;
				return valid;
			} else {
				valid = true;
				return valid;
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return valid;
	}

	public boolean checkConvenienceFeeValidation(CIMMerchantDirectFndRequest mcCreditTransferRequest) {
		if (!String.valueOf(mcCreditTransferRequest.getMerchantAccount().getTipOrConvenienceIndicator()).equals("null")
				&& !String.valueOf(mcCreditTransferRequest.getMerchantAccount().getTipOrConvenienceIndicator())
						.equals("")) {
			if (mcCreditTransferRequest.getMerchantAccount().getTipOrConvenienceIndicator().equals("01")
					|| mcCreditTransferRequest.getMerchantAccount().getTipOrConvenienceIndicator().equals("02")
					|| mcCreditTransferRequest.getMerchantAccount().getTipOrConvenienceIndicator().equals("03")) {

				if (!mcCreditTransferRequest.getMerchantAccount().getTipOrConvenienceIndicator().equals("01")) {
					if (!String.valueOf(mcCreditTransferRequest.getMerchantAccount().getConvenienceIndicatorFee())
							.equals("null")
							&& !String
									.valueOf(mcCreditTransferRequest.getMerchantAccount().getConvenienceIndicatorFee())
									.equals("")) {
						if (String.valueOf(mcCreditTransferRequest.getMerchantAccount().getTipOrConvenienceIndicator())
								.equals("02")) {
							if (String
									.valueOf(mcCreditTransferRequest.getMerchantAccount().getConvenienceIndicatorFee())
									.length() <= 15) {
								return true;

							} else {
								throw new IPSXException(errorCode.validationError("BIPS15-5"));
							}
						} else {
							if (String
									.valueOf(mcCreditTransferRequest.getMerchantAccount().getConvenienceIndicatorFee())
									.length() <= 5) {
								return true;

							} else {
								throw new IPSXException(errorCode.validationError("BIPS15-6"));
							}
						}
					} else {
						throw new IPSXException(errorCode.validationError("BIPS15-4"));
					}
				} else {
					return true;
				}
			} else {
				throw new IPSXException(errorCode.validationError("BIPS15-9"));
			}
		} else {
			return true;
		}
	}

	public boolean checkConvenienceFeeValidationQR(CIMMerchantQRcodeRequest mcCreditTransferRequest) {
		if (!String.valueOf(mcCreditTransferRequest.getTipOrConvenienceIndicator()).equals("null")
				&& !String.valueOf(mcCreditTransferRequest.getTipOrConvenienceIndicator()).equals("")) {
			if (mcCreditTransferRequest.getTipOrConvenienceIndicator().equals("01")
					|| mcCreditTransferRequest.getTipOrConvenienceIndicator().equals("02")
					|| mcCreditTransferRequest.getTipOrConvenienceIndicator().equals("03")) {

				if (!mcCreditTransferRequest.getTipOrConvenienceIndicator().equals("01")) {
					if (!String.valueOf(mcCreditTransferRequest.getConvenienceIndicatorFee()).equals("null")
							&& !String.valueOf(mcCreditTransferRequest.getConvenienceIndicatorFee()).equals("")) {
						if (String.valueOf(mcCreditTransferRequest.getTipOrConvenienceIndicator()).equals("02")) {
							if (String.valueOf(mcCreditTransferRequest.getConvenienceIndicatorFee()).length() <= 15) {
								return true;

							} else {
								throw new IPSXException(errorCode.validationError("BIPS15-5"));
							}
						} else {
							if (String.valueOf(mcCreditTransferRequest.getConvenienceIndicatorFee()).length() <= 5) {
								return true;

							} else {
								throw new IPSXException(errorCode.validationError("BIPS15-6"));
							}
						}

					} else {
						throw new IPSXException(errorCode.validationError("BIPS15-4"));
					}
				} else {
					return true;
				}

			} else {
				throw new IPSXException(errorCode.validationError("BIPS15-9"));
			}
		} else {
			return true;
		}
	}

	public String getOtherBankCode(String bankAgent) {
		String tm = "";
		try {
			Optional<BankAgentTable> otm = bankAgentTableRep.findByCustomBankName(bankAgent);
			tm = otm.isPresent() ? tm = otm.get().getBank_code() : "";
			return tm;
		} catch (Exception e) {
			return tm;
		}
	}

	public String registerCIMcbsIncomingData(String requestUUID, String channelId, String serviveReqVersion,
			String serviceReqId, Date msgDate, String tranNumber, String initChannel, String initTranNumber,
			String postToCbs, String tran_type, String isReversal, String tran_numberFromCbs, String acctNumber,
			String trAmt, String currency, String seqUniqueID, String debrAcctNumber, String debtAcctName,
			String tran_part_code, String debit_remarks, String credit_remarks, String resv_field1, String res_field2,
			Date valueDate, String settlType, String init_sub_tran_no, String error_code, String error_msg,
			String ipsMasterRefId, String debitoragent) {

		String response = "0";
		try {
			TranCimCBSTable tranCimCBSTable = new TranCimCBSTable();
			BankAgentTable remitterBank = findByBank(env.getProperty("ipsx.remitterbank"));
			tranCimCBSTable.setSequence_unique_id(seqUniqueID);
			tranCimCBSTable.setRequest_uuid(requestUUID);
			tranCimCBSTable.setChannel_id(channelId);
			tranCimCBSTable.setService_request_version(serviveReqVersion);
			tranCimCBSTable.setService_request_id(serviceReqId);
			tranCimCBSTable.setMessage_date_time(msgDate);
			tranCimCBSTable.setTran_no(tranNumber);
			tranCimCBSTable.setInit_channel(initChannel);
			tranCimCBSTable.setInit_tran_no(initTranNumber);
			tranCimCBSTable.setPost_to_cbs(postToCbs);
			tranCimCBSTable.setTran_type(tran_type);
			tranCimCBSTable.setIsreversal(isReversal);
			tranCimCBSTable.setTran_no_from_cbs(tran_numberFromCbs);
			tranCimCBSTable.setCustomer_name(debtAcctName);
			tranCimCBSTable.setFrom_account_no(debrAcctNumber);
			tranCimCBSTable.setTo_account_no(acctNumber);
			tranCimCBSTable.setTran_amt(new BigDecimal(trAmt));
			tranCimCBSTable.setTran_date(new Date());
			tranCimCBSTable.setTran_currency(currency);
			tranCimCBSTable.setTran_particular_code(tran_part_code);
			tranCimCBSTable.setDebit_remarks(debit_remarks);
			tranCimCBSTable.setCredit_remarks(credit_remarks);
			tranCimCBSTable.setResv_field_1(resv_field1);
			tranCimCBSTable.setResv_field_2(res_field2);
			tranCimCBSTable.setValue_date(valueDate);
			tranCimCBSTable.setSettl_acct_type(settlType);
			tranCimCBSTable.setInit_sub_tran_no(init_sub_tran_no);
			tranCimCBSTable.setError_code(error_code);
			tranCimCBSTable.setError_msg(error_msg);
			tranCimCBSTable.setIps_master_ref_id(ipsMasterRefId);
			tranCimCBSTable.setRemitterbank(env.getProperty("ipsx.remitterbank"));
			tranCimCBSTable.setRemitterbankcode(remitterBank.getBank_code());
			tranCimCBSTable.setRemitterswiftcode(remitterBank.getBank_agent());
			tranCimCBSTableRep.saveAndFlush(tranCimCBSTable);
			response = "1";
		} catch (Exception e) {
			response = "0";
		}
		return response;
	}

	public void updateOutwardIPSXStatus(String seqUniqueID, String ipsStatus, String tranStatus) {
		try {
			Optional<OutwardTransactionMonitoringTable> otm = outwardTranRep.findById(seqUniqueID);
			//System.out.println("updateIPSXStatus seqID:" + seqUniqueID);
			if (otm.isPresent()) {
				OutwardTransactionMonitoringTable tm = otm.get();
				if (tm.getResponse_status() == null) {
					tm.setIpsx_status(ipsStatus);
					tm.setTran_status(tranStatus);
					tm.setIpsx_response_time(new Date());
				} else {
					if (tm.getResponse_status().equals(TranMonitorStatus.ACSP.toString())) {
						tm.setTran_status(TranMonitorStatus.SUCCESS.toString());
					} else {
						tm.setIpsx_status(ipsStatus);
						tm.setTran_status(tranStatus);
						tm.setIpsx_response_time(new Date());
					}
				}
				outwardTranRep.save(tm);
			} else {
				//System.out.println("updateIPSXStatus:Data not found");
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

	public void updateOutwardCBSStatus(String seqUniqueID, String cbsStatus, String tranStatus) {
		try {
			Optional<OutwardTransactionMonitoringTable> otm = outwardTranRep.findById(seqUniqueID);
			if (otm.isPresent()) {
				OutwardTransactionMonitoringTable tm = otm.get();
				tm.setCbs_status(cbsStatus);
				tm.setCbs_response_time(new Date());
				tm.setTran_status(tranStatus);
				outwardTranRep.save(tm);
			} else {
				System.out.print("updateCBSStatus:Data Not found");
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

	public void updateCIMcbsData(String requestUUID, String status, String statusCode, String message,
			String tranNoFromCBS) {
		tranCimCBSTableRep.updateCIMcbsData(requestUUID, status, statusCode, message, tranNoFromCBS,
				new SimpleDateFormat("dd-MMM-yyyy").format(new Date()));
	}

	public void updateOutwardCBSStatusError(String seqUniqueID, String cbsStatus, String error_desc,
			String tranStatus) {
		try {
			Optional<OutwardTransactionMonitoringTable> otm = outwardTranRep.findById(seqUniqueID);
			if (otm.isPresent()) {
				OutwardTransactionMonitoringTable tm = otm.get();
				tm.setCbs_status(cbsStatus);
				tm.setCbs_status_error(error_desc);
				tm.setCbs_response_time(new Date());
				tm.setTran_status(tranStatus);
				outwardTranRep.save(tm);
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}

	}

	public void updateCIMCNFData(String seqUniqueID, String requestUUID, String status, String statusError) {
		outwardTranRep.updateCIMCNFData(seqUniqueID, requestUUID, status, statusError);
	}

	public void updateTranIPSACK(String sequenceUniqueID, String msgID, String msg_sub_type, String Ack_status,
			String net_mir, String userRef) {
		try {
			tranIPStableRep.updateAckStatus1(sequenceUniqueID, msgID, msg_sub_type, Ack_status, net_mir, userRef);
		} catch (Exception e) {
			System.out.print(e.getLocalizedMessage());
			System.err.println(e.getMessage());
		}
	}

	public boolean invalidPIDQR(String pid) {
		boolean valid = false;
		try {
			List<Object[]> otm = mercantQrGenTableRep.existsByPID(pid);
			if (otm.size() > 0) {
				valid = false;
				return valid;
			} else {
				valid = true;
				return valid;
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return valid;
	}

	public boolean invaliQRdBankCode(String payeeParticipantCode) {
		boolean valid = true;
		try {

			if (payeeParticipantCode.equals(env.getProperty("ipsx.qrPartiipant"))) {

				valid = false;
			} else {
				valid = true;
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}

		return valid;
	}

	public void updateINOUTOUTWARD(String seqUniqueID, String menu) {
		try {

			Optional<OutwardTransactionMonitoringTable> otm = outwardTranRep.findById(seqUniqueID);

			if (otm.isPresent()) {
				OutwardTransactionMonitoringTable tm = otm.get();

				if (menu.equals("MC_IN")) {
					tm.setMconnectin("Y");
					tm.setMconnectindate(new Date());
				} else if (menu.equals("MC_OUT")) {
					tm.setMconnectout("Y");
					tm.setMconnectoutdate(new Date());
				} else if (menu.equals("CBS_IN")) {
					tm.setCbsin("Y");
					tm.setCbsindate(new Date());
				} else if (menu.equals("CBS_OUT")) {
					tm.setCbsout("Y");
					tm.setCbsoutdate(new Date());
				} else if (menu.equals("CBS_REVERSE_IN")) {
					tm.setCbsreversein("Y");
					tm.setCbsreverseindate(new Date());
				} else if (menu.equals("CBS_REVERSE_OUT")) {
					tm.setCbsreverseout("Y");
					tm.setCbsreverseoutdate(new Date());
				} else if (menu.equals("IPS_IN")) {
					tm.setIpsin("Y");
					tm.setIpsindate(new Date());
				} else if (menu.equals("IPS_OUT")) {
					tm.setIpsout("Y");
					tm.setIpsoutdate(new Date());
				}

				outwardTranRep.save(tm);
			} else {
				logger.info("updateCBSStatus:Data Not found");

			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}

	}

//
//	public String getMaxAmountPerDay(String acctNumber,String trAmt) {
//		String totMaxAmt=tranRep.getMaxTranAmt(acctNumber);
//		Double d2=Double.parseDouble(totMaxAmt)+Double.parseDouble(trAmt);
//		String totAmt= d2.toString();
//		// TODO Auto-generated method stub
//		return totAmt;
//	}

//	public void updateTranCBS(String sysTraceAuditNumber, String acctNumber, String trAmt, String tranType,
//			String currencyCode, String tranStatus, String tranStatusError, String seqUniqueID, String user,
//			String settl_acct, String settl_acct_type, String tran_charge_type) {
//		try {
//			TranCBSTable tranCBStable = new TranCBSTable();
//			tranCBStable.setTran_audit_number(sysTraceAuditNumber);
//			tranCBStable.setBob_account(acctNumber);
//			tranCBStable.setTran_date(new Date());
//			//System.out.println("Tran Amount"+ trAmt);
//			//System.out.println("Tran Amount BigDecimal"+ new BigDecimal(trAmt));
//			tranCBStable.setTran_amount(new BigDecimal(trAmt));
//			tranCBStable.setTran_type(tranType);
//			tranCBStable.setTran_currency(currencyCode);
//			tranCBStable.setTran_cbs_status(tranStatus);
//			tranCBStable.setTran_cbs_status_error(tranStatusError);
//			tranCBStable.setEntry_user(user);
//			tranCBStable.setEntry_time(new Date());
//			tranCBStable.setEntity_cre_flg("N");
//			tranCBStable.setSequence_unique_id(seqUniqueID);
//			tranCBStable.setSettl_acct(settl_acct);
//			tranCBStable.setSettl_acct_type(settl_acct_type);
//			tranCBStable.setTran_charge_type(tran_charge_type);
//
//			/// Assign Value Date
//			List<TranCBSTable> countTranCBSList = tranCBSTableRep.findBySeqUniqueIDCustom(seqUniqueID);
//			//// Check CBS Table already exist(Reversal)
//			//// if yes the value date is original tran date
//			if (countTranCBSList.size() > 0) {
//				for (TranCBSTable cbsTable : countTranCBSList) {
//					if (cbsTable.getTran_cbs_status().equals("SUCCESS")) {
//						DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
//						if (dateFormat.format(cbsTable.getTran_date()).equals(dateFormat.format(new Date()))) {
//							//// Check CutOff time after BOB settlement time
//							//// if yes the value date is +1
//							if (isTimeAfterCutOff()) {
//								Date dt = new Date();
//								Calendar c = Calendar.getInstance();
//								c.setTime(dt);
//								c.add(Calendar.DATE, 1);
//								dt = c.getTime();
//								tranCBStable.setValue_date(dt);
//							} else {
//								tranCBStable.setValue_date(new Date());
//							}
//						} else {
//							tranCBStable.setValue_date(cbsTable.getTran_date());
//						}
//					}
//				}
//			} else {
//				//// Check CutOff time after BOB settlement time
//				//// if yes the value date is +1
//				if (isTimeAfterCutOff()) {
//					Date dt = new Date();
//					Calendar c = Calendar.getInstance();
//					c.setTime(dt);
//					c.add(Calendar.DATE, 1);
//					dt = c.getTime();
//					tranCBStable.setValue_date(dt);
//				} else {
//					tranCBStable.setValue_date(new Date());
//				}
//			}
//			tranCBSTableRep.save(tranCBStable);
//		} catch (Exception e) {
//			System.err.println(e.getMessage());
//		}
//	}

	public void updateCBSStatusout(String seqUniqueID, String cbsStatus, String tranStatus) {
		try {
			Optional<OutwardTransactionMonitoringTable> otm = outwardTranRep.findById(seqUniqueID);

			if (otm.isPresent()) {
				OutwardTransactionMonitoringTable tm = otm.get();
				tm.setCbs_status(cbsStatus);
				tm.setCbs_response_time(new Date());
				// tm.setCbs_status_error("");
				tm.setTran_status(tranStatus);
				outwardTranRep.save(tm);
			} else {
				logger.info("updateCBSStatus:Data Not found");

			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}

	}

	public void updateCBSStatusErrorout(String seqUniqueID, String cbsStatus, String error_desc, String tranStatus) {
		try {
			Optional<OutwardTransactionMonitoringTable> otm = outwardTranRep.findById(seqUniqueID);

			if (otm.isPresent()) {
				OutwardTransactionMonitoringTable tm = otm.get();
				tm.setCbs_status(cbsStatus);
				tm.setCbs_status_error(error_desc);
				tm.setCbs_response_time(new Date());
				tm.setTran_status(tranStatus);
				outwardTranRep.save(tm);
			} else {
				logger.info("updateCBSStatus:Data Not found");

			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}

	}

	@Autowired
	BIPS_Charge_Back_Rep bips_Charge_Back_Rep;

	public void updateChargeBackTable(String seqUniqueID, String menu) {
		try {

			Optional<BIPS_Charge_Back_Entity> otm = bips_Charge_Back_Rep.findById(seqUniqueID);

			if (otm.isPresent()) {
				BIPS_Charge_Back_Entity tm = otm.get();

				if (menu.equals("MC_IN")) {
					tm.setMconnectin("Y");
					tm.setMconnectindate(new Date());
				} else if (menu.equals("MC_OUT")) {
					tm.setMconnectout("Y");
					tm.setMconnectoutdate(new Date());
				} else if (menu.equals("CBS_IN")) {
					tm.setCbsin("Y");
					tm.setCbsindate(new Date());
				} else if (menu.equals("CBS_OUT")) {
					tm.setCbsout("Y");
					tm.setCbsoutdate(new Date());
				} else if (menu.equals("CBS_REVERSE_IN")) {
					tm.setCbsreversein("Y");
					tm.setCbsreverseindate(new Date());
				} else if (menu.equals("CBS_REVERSE_OUT")) {
					tm.setCbsreverseout("Y");
					tm.setCbsreverseoutdate(new Date());
				} else if (menu.equals("IPS_IN")) {
					tm.setIpsin("Y");
					tm.setIpsindate(new Date());
				} else if (menu.equals("IPS_OUT")) {
					tm.setIpsout("Y");
					tm.setIpsoutdate(new Date());
				}
				bips_Charge_Back_Rep.save(tm);
			} else {
				logger.info("updateCBSStatus:Data Not found");
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

	public void updateTranCBS(String sysTraceAuditNumber, String acctNumber, String trAmt, String tranType,
			String currencyCode, String tranStatus, String tranStatusError, String seqUniqueID, String user,
			String settl_acct, String settl_acct_type, String tran_charge_type) throws SQLException {

		//System.out.println("1 : ==> " + sysTraceAuditNumber);
		//System.out.println("2 : ==> " + acctNumber);
		//System.out.println("3 : ==> " + trAmt);
		//System.out.println("4 : ==> " + tranType);
		//System.out.println("5 : ==> " + currencyCode);
		//System.out.println("6 : ==> " + tranStatus);
		//System.out.println("7 : ==> " + tranStatusError);
		//System.out.println("8 : ==> " + seqUniqueID);
		//System.out.println("9 : ==> " + user);
		//System.out.println("10 : ==> " + settl_acct);
		//System.out.println("11 : ==> " + settl_acct_type);
		//System.out.println("12 : ==> " + tran_charge_type);

		try {
			TranCBSTable tranCBStable = new TranCBSTable();
			tranCBStable.setTran_audit_number(sysTraceAuditNumber);
			tranCBStable.setBob_account(acctNumber);
			tranCBStable.setTran_date(new Date());

			BigDecimal transactionAmount;
			try {
				transactionAmount = new BigDecimal(trAmt);
				//System.out.println("Transaction Amount BigDecimal: " + transactionAmount);
			} catch (NumberFormatException e) {
				System.err.println("Invalid transaction amount format: " + trAmt);
				return;
			}

			tranCBStable.setTran_amount(transactionAmount);
			tranCBStable.setTran_type(tranType);
			tranCBStable.setTran_currency(currencyCode);
			tranCBStable.setTran_cbs_status(tranStatus);
			tranCBStable.setTran_cbs_status_error(tranStatusError);
			tranCBStable.setEntry_user(user);
			tranCBStable.setEntry_time(new Date());
			tranCBStable.setEntity_cre_flg("N");
			tranCBStable.setSequenceUniqueId(seqUniqueID);
			tranCBStable.setSettl_acct(settl_acct);
			tranCBStable.setSettl_acct_type(settl_acct_type);
			tranCBStable.setTran_charge_type(tran_charge_type);

			List<TranCBSTable> countTranCBSList = findBySeqUniqueIDCustom(seqUniqueID);
			//System.out.println("CBS List Size: " + countTranCBSList.size());

			if (countTranCBSList.size() > 0) {
				for (TranCBSTable cbsTable : countTranCBSList) {
					if (cbsTable.getTran_cbs_status().equals("SUCCESS")) {
						DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
						if (dateFormat.format(cbsTable.getTran_date()).equals(dateFormat.format(new Date()))) {
							if (isTimeAfterCutOff()) {
								Calendar c = Calendar.getInstance();
								c.setTime(new Date());
								c.add(Calendar.DATE, 1);
								tranCBStable.setValue_date(c.getTime());
							} else {
								tranCBStable.setValue_date(new Date());
							}
						} else {
							tranCBStable.setValue_date(cbsTable.getTran_date());
						}
					}
				}
			} else {
				if (isTimeAfterCutOff()) {
					Calendar c = Calendar.getInstance();
					c.setTime(new Date());
					c.add(Calendar.DATE, 1);
					tranCBStable.setValue_date(c.getTime());
				} else {
					tranCBStable.setValue_date(new Date());
				}
			}

			tranCBSTableRep.save(tranCBStable);
			//System.out.println("Transaction successfully saved.");
		} catch (HibernateException e) {
			System.err.println("Hibernate Error: " + e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			System.err.println("Unexpected Error: " + e.getMessage());
			e.printStackTrace();
		}
	}

	public List<TranCBSTable> findBySeqUniqueIDCustom(String seqUniqueID) {
		//System.out.println("Sys : " + seqUniqueID);
		return entityManager
				.createQuery("SELECT t FROM TranCBSTable t WHERE t.sequenceUniqueId = :seqUniqueID", TranCBSTable.class)
				.setParameter("seqUniqueID", seqUniqueID).getResultList();
	}

}
