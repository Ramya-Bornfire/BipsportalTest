package com.bornfire.controller;

import static com.bornfire.exception.MPayErrorResponseCode.SERVER_ERROR_CODE;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import com.bornfire.entity.BIPS_Charge_Back_Entity;
import com.bornfire.entity.C24FTRequest;
import com.bornfire.entity.C24FTResponse;
import com.bornfire.entity.C24RequestAcount;
import com.bornfire.entity.CIMMerchantDirectFndRequest;
import com.bornfire.entity.SettlementAccountRep;
import com.bornfire.entity.TranMonitorStatus;
import com.google.gson.Gson;

@Component
public class Connect24Service {
	private static final Logger logger = LoggerFactory.getLogger(Connect24Service.class);

	@Autowired
	IpsDao ipsDao;

	@Autowired
	Environment env;

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	SettlementAccountRep settlAcctRep;

	public ResponseEntity<C24FTResponse> DbtFundMerchantDirectRequest(String senderParticipantBIC,
			String participantSOL, CIMMerchantDirectFndRequest mcCreditTransferRequest, String sysTraceAuditNumber,
			String seqUniqueID, String trRmks, String tot_tran_amount) throws SQLException {

		System.out
				.println("Loyalty Num : " + mcCreditTransferRequest.getAdditionalDataInformation().getLoyaltyNumber());

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		httpHeaders.set("Partipant_BIC", env.getProperty("bob.bankcode"));
		httpHeaders.set("Partipant_SOL", participantSOL);
		httpHeaders.set("SYS_TRACE_AUDIT_NUMBER", sysTraceAuditNumber);

		C24FTRequest c24ftRequest = new C24FTRequest();
		C24RequestAcount c24RequestAcount = new C24RequestAcount();
		c24RequestAcount.setAcctNumber(mcCreditTransferRequest.getRemitterAccount().getAcctNumber());
		c24RequestAcount.setSchmType(mcCreditTransferRequest.getRemitterAccount().getSchmType());
		c24RequestAcount.setSettlAcctNumber(mcCreditTransferRequest.getMerchantAccount().getMerchantAcctNumber());

		String merchantName = mcCreditTransferRequest.getMerchantAccount().getMerchantName();
		if (merchantName != null) {
			String merchantNameTrimmed = merchantName.trim();
			merchantName = merchantNameTrimmed.length() > 32 ? merchantNameTrimmed.substring(0, 32)
					: merchantNameTrimmed;
		}
		c24RequestAcount.setBenAcctName(merchantName);

		c24ftRequest.setAccount(c24RequestAcount);
		c24ftRequest.setCurrency_Code(mcCreditTransferRequest.getMerchantAccount().getCurrency());
		c24ftRequest.setPAN("");
		c24ftRequest.setTrAmt(tot_tran_amount);
		c24ftRequest.setTrRmks(trRmks);

		HttpEntity<C24FTRequest> entity = new HttpEntity<>(c24ftRequest, httpHeaders);
		ResponseEntity<C24FTResponse> response = null;

		ipsDao.updateINOUTOUTWARD(seqUniqueID, "CBS_IN");

		try {
			response = restTemplate.postForEntity(env.getProperty("connect24.url") + "/api/ws/dbtActfndTransfer?",
					entity, C24FTResponse.class);

			if (response != null && response.getBody() != null) {
				return handleSuccessResponse(response.getBody(), mcCreditTransferRequest, sysTraceAuditNumber,
						seqUniqueID, tot_tran_amount);
			} else {
				return handleFailureResponse(HttpStatus.INTERNAL_SERVER_ERROR, mcCreditTransferRequest,
						sysTraceAuditNumber, seqUniqueID, tot_tran_amount,
						TranMonitorStatus.CBS_SERVER_NOT_CONNECTED.toString());
			}

		} catch (HttpClientErrorException ex) {
			return handleHttpClientError(ex, mcCreditTransferRequest, sysTraceAuditNumber, seqUniqueID,
					tot_tran_amount);

		} catch (HttpServerErrorException ex) {
			return handleServerError(ex, mcCreditTransferRequest, sysTraceAuditNumber, seqUniqueID, tot_tran_amount);

		} catch (Exception ex) {
			return handleException(ex, mcCreditTransferRequest, sysTraceAuditNumber, seqUniqueID, tot_tran_amount);
		}
	}

	private ResponseEntity<C24FTResponse> handleSuccessResponse(C24FTResponse body,
			CIMMerchantDirectFndRequest mcCreditTransferRequest, String sysTraceAuditNumber, String seqUniqueID,
			String tot_tran_amount) throws SQLException {
		C24FTResponse c24ftResponse = new C24FTResponse("SUCCESS", body.getBalance(), body.getTranCurrency());

		ipsDao.updateTranCBS(sysTraceAuditNumber, mcCreditTransferRequest.getRemitterAccount().getAcctNumber(),
				tot_tran_amount, "DEBIT", mcCreditTransferRequest.getRemitterAccount().getCurrencyCode(), "SUCCESS", "",
				seqUniqueID, "SYSTEM", mcCreditTransferRequest.getMerchantAccount().getMerchantAcctNumber(),
				mcCreditTransferRequest.getMerchantAccount().getMerchantID(), env.getProperty("trchtype.tran"));

		ipsDao.updateINOUTOUTWARD(seqUniqueID, "CBS_OUT");

		return new ResponseEntity<>(c24ftResponse, HttpStatus.OK);
	}

	private ResponseEntity<C24FTResponse> handleFailureResponse(HttpStatus status,
			CIMMerchantDirectFndRequest mcCreditTransferRequest, String sysTraceAuditNumber, String seqUniqueID,
			String tot_tran_amount, String errorDesc) throws SQLException {
		C24FTResponse c24ftResponse = new C24FTResponse("FAILURE", SERVER_ERROR_CODE,
				Collections.singletonList(errorDesc));

		ipsDao.updateTranCBS(sysTraceAuditNumber, mcCreditTransferRequest.getRemitterAccount().getAcctNumber(),
				tot_tran_amount, "DEBIT", mcCreditTransferRequest.getRemitterAccount().getCurrencyCode(), "FAILURE",
				errorDesc, seqUniqueID, "SYSTEM", mcCreditTransferRequest.getMerchantAccount().getMerchantAcctNumber(),
				mcCreditTransferRequest.getMerchantAccount().getMerchantID(), env.getProperty("trchtype.tran"));

		ipsDao.updateINOUTOUTWARD(seqUniqueID, "CBS_OUT");

		return new ResponseEntity<>(c24ftResponse, status);
	}

	private ResponseEntity<C24FTResponse> handleHttpClientError(HttpClientErrorException ex,
			CIMMerchantDirectFndRequest mcCreditTransferRequest, String sysTraceAuditNumber, String seqUniqueID,
			String tot_tran_amount) throws SQLException {
		logger.info("HttpClientErrorException --------->" + ex.getStatusCode());
		logger.info(ex.getLocalizedMessage());
		logger.error(ex.getLocalizedMessage());

		if (ex.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
			return handleFailureResponse(HttpStatus.INTERNAL_SERVER_ERROR, mcCreditTransferRequest, sysTraceAuditNumber,
					seqUniqueID, tot_tran_amount, TranMonitorStatus.CBS_SERVER_NOT_CONNECTED.toString());
		} else {
			C24FTResponse c24ftResponse1 = new Gson().fromJson(ex.getResponseBodyAsString(), C24FTResponse.class);
			C24FTResponse c24ftResponse = new C24FTResponse("FAILURE", c24ftResponse1.getError(),
					c24ftResponse1.getError_desc());

			ipsDao.updateTranCBS(sysTraceAuditNumber, mcCreditTransferRequest.getRemitterAccount().getAcctNumber(),
					tot_tran_amount, "DEBIT", mcCreditTransferRequest.getRemitterAccount().getCurrencyCode(), "FAILURE",
					c24ftResponse1.getError_desc().get(0).toString(), seqUniqueID, "SYSTEM",
					mcCreditTransferRequest.getMerchantAccount().getMerchantAcctNumber(),
					mcCreditTransferRequest.getMerchantAccount().getMerchantID(), env.getProperty("trchtype.tran"));

			ipsDao.updateINOUTOUTWARD(seqUniqueID, "CBS_OUT");

			return new ResponseEntity<>(c24ftResponse, ex.getStatusCode());
		}
	}

	private ResponseEntity<C24FTResponse> handleServerError(HttpServerErrorException ex,
			CIMMerchantDirectFndRequest mcCreditTransferRequest, String sysTraceAuditNumber, String seqUniqueID,
			String tot_tran_amount) throws SQLException {
		logger.info("HttpServerErrorException --------->");
		logger.info(ex.getLocalizedMessage());
		logger.error(ex.getLocalizedMessage());

		return handleFailureResponse(HttpStatus.INTERNAL_SERVER_ERROR, mcCreditTransferRequest, sysTraceAuditNumber,
				seqUniqueID, tot_tran_amount, TranMonitorStatus.CBS_SERVER_NOT_CONNECTED.toString());
	}

	private ResponseEntity<C24FTResponse> handleException(Exception ex,
			CIMMerchantDirectFndRequest mcCreditTransferRequest, String sysTraceAuditNumber, String seqUniqueID,
			String tot_tran_amount) throws SQLException {
		logger.info("Exception --------->");
		logger.info(ex.getLocalizedMessage());
		logger.error(ex.getLocalizedMessage());

		return handleFailureResponse(HttpStatus.INTERNAL_SERVER_ERROR, mcCreditTransferRequest, sysTraceAuditNumber,
				seqUniqueID, tot_tran_amount, TranMonitorStatus.CBS_SERVER_NOT_CONNECTED.toString());
	}

	public ResponseEntity<C24FTResponse> reversalFunTransfer(BIPS_Charge_Back_Entity chargeBack,
			String sysTraceAuditNumber, String seqUniqueID, String tot_tran_amount) throws SQLException {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		httpHeaders.set("Partipant_BIC", env.getProperty("bob.bankcode"));
		httpHeaders.set("Partipant_SOL", chargeBack.getIpsx_account().substring(0, 4));
		httpHeaders.set("SYS_TRACE_AUDIT_NUMBER", sysTraceAuditNumber);

		C24FTRequest c24ftRequest = new C24FTRequest();
		C24RequestAcount c24RequestAcount = new C24RequestAcount();
		c24RequestAcount.setAcctNumber(chargeBack.getIpsx_account());
		c24RequestAcount.setSchmType(env.getProperty("bob.SchmType"));
		c24RequestAcount.setSettlAcctNumber(chargeBack.getCim_account());

		c24ftRequest.setAccount(c24RequestAcount);
		c24ftRequest.setCurrency_Code(chargeBack.getTran_currency());
		c24ftRequest.setPAN("");
		c24ftRequest.setTrAmt(tot_tran_amount);
		c24ftRequest.setTrRmks("MC/" + chargeBack.getCim_account() + "/" + chargeBack.getCim_account_name());

		HttpEntity<C24FTRequest> entity = new HttpEntity<>(c24ftRequest, httpHeaders);
		ResponseEntity<C24FTResponse> response = null;

		try {
			ipsDao.updateINOUTOUTWARD(seqUniqueID, "CBS_REVERSE_IN");
			ipsDao.updateChargeBackTable(seqUniqueID, "CBS_REVERSE_IN");
			// System.out.println("step1");
			response = restTemplate.postForEntity(
					env.getProperty("connect24.url") + "/api/ws/dbtActReversefndTransfer?", entity,
					C24FTResponse.class);
			// System.out.println("step2");
			if (response != null && response.getBody() != null && response.getStatusCode() == HttpStatus.OK) {
				C24FTResponse c24ftResponse = new C24FTResponse("SUCCESS", response.getBody().getBalance(),
						response.getBody().getTranCurrency());

				// System.out.println("step3");

				ipsDao.updateTranCBS(sysTraceAuditNumber, chargeBack.getIpsx_account(), tot_tran_amount,
						"DEBIT_REVERSE", chargeBack.getTran_currency(), "SUCCESS", "", seqUniqueID, "SYSTEM",
						chargeBack.getCim_account(), chargeBack.getMerchant_id(), env.getProperty("trchtype.tran"));

				// System.out.println("step4");

				ipsDao.updateINOUTOUTWARD(seqUniqueID, "CBS_REVERSE_OUT");
				// System.out.println("step5");
				ipsDao.updateChargeBackTable(seqUniqueID, "CBS_REVERSE_OUT");
				// System.out.println("step6");
				return new ResponseEntity<>(c24ftResponse, HttpStatus.OK);
			} else {
				// System.out.println("Failed Reversal transaction");
			}
		} catch (HttpClientErrorException ex) {
			handleClientError(ex, sysTraceAuditNumber, chargeBack, tot_tran_amount, seqUniqueID);
		} catch (HttpServerErrorException ex) {
			handleServerError(ex, sysTraceAuditNumber, chargeBack, tot_tran_amount, seqUniqueID);
		} catch (Exception ex) {
			handleException(ex, sysTraceAuditNumber, chargeBack, tot_tran_amount, seqUniqueID);
		}

		return response;
	}

	private void handleClientError(HttpClientErrorException ex, String sysTraceAuditNumber,
			BIPS_Charge_Back_Entity chargeBack, String tot_tran_amount, String seqUniqueID) throws SQLException {
		logger.info("HttpClientErrorException --------->" + ex.getStatusCode());
		logger.info(ex.getLocalizedMessage());
		logger.error(ex.getLocalizedMessage());

		C24FTResponse c24ftResponse;
		if (ex.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
			c24ftResponse = new C24FTResponse("FAILURE", SERVER_ERROR_CODE,
					Collections.singletonList(TranMonitorStatus.CBS_SERVER_NOT_CONNECTED.toString()));

			updateTablesFailure(sysTraceAuditNumber, chargeBack, tot_tran_amount, seqUniqueID,
					TranMonitorStatus.CBS_SERVER_NOT_CONNECTED.toString());

			new ResponseEntity<>(c24ftResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		} else {
			C24FTResponse c24ftResponse1 = new Gson().fromJson(ex.getResponseBodyAsString(), C24FTResponse.class);
			c24ftResponse = new C24FTResponse("FAILURE", c24ftResponse1.getError(), c24ftResponse1.getError_desc());

			updateTablesFailure(sysTraceAuditNumber, chargeBack, tot_tran_amount, seqUniqueID,
					c24ftResponse1.getError_desc().get(0).toString());

			new ResponseEntity<>(c24ftResponse, ex.getStatusCode());
		}
	}

	private void handleServerError(HttpServerErrorException ex, String sysTraceAuditNumber,
			BIPS_Charge_Back_Entity chargeBack, String tot_tran_amount, String seqUniqueID) throws SQLException {
		logger.info("HttpServerErrorException --------->");
		logger.info(ex.getLocalizedMessage());
		logger.error(ex.getLocalizedMessage());

		C24FTResponse c24ftResponse = new C24FTResponse("FAILURE", SERVER_ERROR_CODE,
				Collections.singletonList(TranMonitorStatus.CBS_SERVER_NOT_CONNECTED.toString()));

		updateTablesFailure(sysTraceAuditNumber, chargeBack, tot_tran_amount, seqUniqueID,
				TranMonitorStatus.CBS_SERVER_NOT_CONNECTED.toString());

		new ResponseEntity<>(c24ftResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	private void handleException(Exception ex, String sysTraceAuditNumber, BIPS_Charge_Back_Entity chargeBack,
			String tot_tran_amount, String seqUniqueID) throws SQLException {
		// System.out.println("Exception : Enter");
		logger.info("Exception --------->");
		logger.info(ex.getLocalizedMessage());
		logger.error(ex.getLocalizedMessage());

		C24FTResponse c24ftResponse = new C24FTResponse("FAILURE", SERVER_ERROR_CODE,
				Collections.singletonList(TranMonitorStatus.CBS_SERVER_NOT_CONNECTED.toString()));
		// System.out.println("Exception : Enter Server Error");

		updateTablesFailure(sysTraceAuditNumber, chargeBack, tot_tran_amount, seqUniqueID,
				TranMonitorStatus.CBS_SERVER_NOT_CONNECTED.toString());

		new ResponseEntity<>(c24ftResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	private void updateTablesFailure(String sysTraceAuditNumber, BIPS_Charge_Back_Entity chargeBack,
			String tot_tran_amount, String seqUniqueID, String errorDesc) throws SQLException {

		ipsDao.updateTranCBS(sysTraceAuditNumber, chargeBack.getIpsx_account(), tot_tran_amount, "DEBIT_REVERSE",
				chargeBack.getTran_currency(), "FAILURE", errorDesc, seqUniqueID, "SYSTEM", chargeBack.getCim_account(),
				chargeBack.getMerchant_id(), env.getProperty("trchtype.tran"));

		ipsDao.updateINOUTOUTWARD(seqUniqueID, "CBS_REVERSE_OUT");
		ipsDao.updateChargeBackTable(seqUniqueID, "CBS_REVERSE_OUT");
	}

	public ResponseEntity<C24FTResponse> DbtFundMerchantDirectRequestForFees(String senderParticipantBIC,
			String participantSOL, CIMMerchantDirectFndRequest mcCreditTransferRequest, String sysTraceAuditNumber,
			String seqUniqueID, String trRmks, String tot_tran_amount, String RRN, String parentRRN)
			throws SQLException {

		// System.out.println("Device Id : " +
		// mcCreditTransferRequest.getAdditionalDataInformation().getDeviceID());
		System.out.println("Loyalty Num : " + mcCreditTransferRequest.getAdditionalDataInformation().getLoyaltyNumber());

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		httpHeaders.set("Partipant_BIC", env.getProperty("bob.bankcode"));
		httpHeaders.set("Partipant_SOL", participantSOL);
		httpHeaders.set("SYS_TRACE_AUDIT_NUMBER", RRN);

		C24FTRequest c24ftRequest = new C24FTRequest();
		C24RequestAcount c24RequestAcount = new C24RequestAcount();
		c24RequestAcount.setAcctNumber(mcCreditTransferRequest.getMerchantAccount().getMerchantAcctNumber());
		c24RequestAcount.setSchmType(mcCreditTransferRequest.getRemitterAccount().getSchmType());
		c24RequestAcount.setSettlAcctNumber(env.getProperty("ips.feesaccount"));

		String merchantName = mcCreditTransferRequest.getMerchantAccount().getMerchantName();

		// System.out.println("Before Condition Merchant Name : " + merchantName);
		if (merchantName != null) {
			String merchantNameTrimmed = merchantName.trim();
			if (merchantNameTrimmed.length() > 30) {
				merchantName = merchantNameTrimmed.substring(0, 30);
			} else {
				merchantName = merchantNameTrimmed;
			}
		}
		// System.out.println("After Condition Merchant Name : " + merchantName);
		c24RequestAcount.setBenAcctName(merchantName);

		BigDecimal tramt = new BigDecimal(tot_tran_amount);
		int percentage = 2;
		BigDecimal percentageAmount = tramt.multiply(BigDecimal.valueOf(percentage / 100.0));
		c24ftRequest.setAccount(c24RequestAcount);
		c24ftRequest.setCurrency_Code(mcCreditTransferRequest.getMerchantAccount().getCurrency());
		c24ftRequest.setPAN("");
		c24ftRequest.setTrAmt(percentageAmount.toString());
		c24ftRequest.setTrRmks(trRmks);
		c24ftRequest.setParentRRN(parentRRN);

		HttpEntity<C24FTRequest> entity = new HttpEntity<>(c24ftRequest, httpHeaders);
		ResponseEntity<C24FTResponse> response = null;

		ipsDao.updateINOUTOUTWARD(seqUniqueID, "CBS_IN");

		// SettlementAccount
		// settlAcct=settlAcctRep.findById(env.getProperty("settl.payable")).get();
		try {
			// logger.info("Sending message to connect24 ");

			response = restTemplate.postForEntity(env.getProperty("connect24.url") + "/api/ws/dbtActfndTransfer?",
					entity, C24FTResponse.class);
			C24FTResponse c24ftResponse = new C24FTResponse("SUCCESS", response.getBody().getBalance(),
					response.getBody().getTranCurrency());

			//// update table
			ipsDao.updateTranCBS(RRN, mcCreditTransferRequest.getMerchantAccount().getMerchantAcctNumber(),
					percentageAmount.toString(), "DEBIT",
					mcCreditTransferRequest.getRemitterAccount().getCurrencyCode(), "SUCCESS", "", seqUniqueID + "FE",
					"SYSTEM", env.getProperty("ips.feesaccount"),
					mcCreditTransferRequest.getMerchantAccount().getMerchantID(), env.getProperty("trchtype.tran"));

			ipsDao.updateINOUTOUTWARD(seqUniqueID, "CBS_OUT");

			return new ResponseEntity<>(c24ftResponse, HttpStatus.OK);
		} catch (HttpClientErrorException ex) {

			logger.info("HttpClientErrorException --------->" + ex.getStatusCode());
			logger.info(ex.getLocalizedMessage());
			logger.error(ex.getLocalizedMessage());

			if (ex.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
				C24FTResponse c24ftResponse = new C24FTResponse("FAILURE", SERVER_ERROR_CODE,
						Collections.singletonList(TranMonitorStatus.CBS_SERVER_NOT_CONNECTED.toString()));

				///// Update Table
				ipsDao.updateTranCBS(sysTraceAuditNumber, mcCreditTransferRequest.getRemitterAccount().getAcctNumber(),
						tot_tran_amount, "DEBIT", mcCreditTransferRequest.getRemitterAccount().getCurrencyCode(),
						"FAILURE", TranMonitorStatus.CBS_SERVER_NOT_CONNECTED.toString(), seqUniqueID, "SYSTEM",
						mcCreditTransferRequest.getMerchantAccount().getMerchantAcctNumber(),
						mcCreditTransferRequest.getMerchantAccount().getMerchantID(), env.getProperty("trchtype.tran"));

				ipsDao.updateINOUTOUTWARD(seqUniqueID, "CBS_OUT");

				return new ResponseEntity<>(c24ftResponse, HttpStatus.INTERNAL_SERVER_ERROR);
			} else {
				C24FTResponse c24ftResponse1 = new Gson().fromJson(ex.getResponseBodyAsString(), C24FTResponse.class);
				C24FTResponse c24ftResponse = new C24FTResponse("FAILURE", c24ftResponse1.getError(),
						c24ftResponse1.getError_desc());

				//// update table
				ipsDao.updateTranCBS(sysTraceAuditNumber, mcCreditTransferRequest.getRemitterAccount().getAcctNumber(),
						tot_tran_amount, "DEBIT", mcCreditTransferRequest.getRemitterAccount().getCurrencyCode(),
						"FAILURE", c24ftResponse1.getError_desc().get(0).toString(), seqUniqueID, "SYSTEM",
						mcCreditTransferRequest.getMerchantAccount().getMerchantAcctNumber(),
						mcCreditTransferRequest.getMerchantAccount().getMerchantID(), env.getProperty("trchtype.tran"));

				ipsDao.updateINOUTOUTWARD(seqUniqueID, "CBS_OUT");

				return new ResponseEntity<>(c24ftResponse, ex.getStatusCode());
			}

		} catch (HttpServerErrorException ex) {
			logger.info("HttpServerErrorException --------->");
			logger.info(ex.getLocalizedMessage());
			logger.error(ex.getLocalizedMessage());

			C24FTResponse c24ftResponse = new C24FTResponse("FAILURE", SERVER_ERROR_CODE,
					Collections.singletonList(TranMonitorStatus.CBS_SERVER_NOT_CONNECTED.toString()));
			ipsDao.updateTranCBS(sysTraceAuditNumber, mcCreditTransferRequest.getRemitterAccount().getAcctNumber(),
					tot_tran_amount, "DEBIT", mcCreditTransferRequest.getRemitterAccount().getCurrencyCode(), "FAILURE",
					TranMonitorStatus.CBS_SERVER_NOT_CONNECTED.toString(), seqUniqueID, "SYSTEM",
					mcCreditTransferRequest.getMerchantAccount().getMerchantAcctNumber(),
					mcCreditTransferRequest.getMerchantAccount().getMerchantID(), env.getProperty("trchtype.tran"));

			ipsDao.updateINOUTOUTWARD(seqUniqueID, "CBS_OUT");

			return new ResponseEntity<>(c24ftResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception ex) {
			logger.info("Exception --------->");
			logger.info(ex.getLocalizedMessage());
			logger.error(ex.getLocalizedMessage());

			C24FTResponse c24ftResponse = new C24FTResponse("FAILURE", SERVER_ERROR_CODE,
					Collections.singletonList(TranMonitorStatus.CBS_SERVER_NOT_CONNECTED.toString()));
			ipsDao.updateTranCBS(sysTraceAuditNumber, mcCreditTransferRequest.getRemitterAccount().getAcctNumber(),
					tot_tran_amount, "DEBIT", mcCreditTransferRequest.getRemitterAccount().getCurrencyCode(), "FAILURE",
					TranMonitorStatus.CBS_SERVER_NOT_CONNECTED.toString(), seqUniqueID, "SYSTEM",
					mcCreditTransferRequest.getMerchantAccount().getMerchantAcctNumber(),
					mcCreditTransferRequest.getMerchantAccount().getMerchantID(), env.getProperty("trchtype.tran"));

			ipsDao.updateINOUTOUTWARD(seqUniqueID, "CBS_OUT");

			return new ResponseEntity<>(c24ftResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
}
