package com.bornfire.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

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

import com.bornfire.config.Listener;
import com.bornfire.config.SequenceGenerator;
import com.bornfire.entity.CimCBSrequest;
import com.bornfire.entity.CimCBSrequestData;
import com.bornfire.entity.CimCBSrequestHeader;
import com.bornfire.entity.CimCBSresponse;
import com.bornfire.entity.CimUpdatePaymentStatusRequest;
import com.bornfire.entity.TranCimCBSTable;
import com.bornfire.entity.TranCimCBSTableRep;

@Component
public class CimCBSservice {
	private static final Logger logger = LoggerFactory.getLogger(CimCBSservice.class);

	@Autowired
	Listener listener;

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	Environment env;

	@Autowired
	TranCimCBSTableRep tranCimCBSTableRep;

	@Autowired
	IpsDao ipsDao;

	@Autowired
	SequenceGenerator sequence;

	public ResponseEntity<CimCBSresponse> cdtFundRequest(String requestUUID) {
		logger.info("Inside CBS REQUEST");

		HttpHeaders httpHeaders = new HttpHeaders();

		httpHeaders.setContentType(MediaType.APPLICATION_JSON);

		TranCimCBSTable data = tranCimCBSTableRep.findById(requestUUID).get();

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

		if (data.getInit_channel().contains("Jmeter") || data.getInit_channel().contains("Postman")) {
			cimCBSrequestData.setBranchId("TESTING");
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

		logger.debug("cimCBSrequest1 :" + cimCBSrequest.toString());
		if (!String.valueOf(data.getStatus()).equals("null") && !String.valueOf(data.getStatus()).equals("")
				&& String.valueOf(data.getStatus()).equals("ACSP")) {
			cimCBSrequestData.setIpsxTranStatus(Boolean.TRUE);
			logger.debug("cimCBSrequest Inside TRUE");
		} else {
			cimCBSrequestData.setIpsxTranStatus(Boolean.FALSE);
			logger.debug("cimCBSrequest Inside FALSE");
		}
		// cimCBSrequestData.setIpsxTranStatus((data.getStatus()==null)?Boolean.FALSE:((data.getStatus()=="ACSP")?Boolean.TRUE:Boolean.FALSE));
		cimCBSrequest.setData(cimCBSrequestData);

		logger.debug("cimCBSrequest :" + cimCBSrequest.toString());

		HttpEntity<CimCBSrequest> entity = new HttpEntity<>(cimCBSrequest, httpHeaders);

		ResponseEntity<CimCBSresponse> response = null;

		try {
			logger.info("Sending message to connect24 credit using restTemplate");

			response = restTemplate.postForEntity(
					env.getProperty("cimESB.url") + "appname=" + env.getProperty("cimESB.appname") + "&prgname="
							+ env.getProperty("cimESB.prgname") + "&arguments=" + env.getProperty("cimESB.arguments"),
					entity, CimCBSresponse.class);

			return new ResponseEntity<>(response.getBody(), HttpStatus.OK);

		} catch (HttpClientErrorException ex) {
			logger.debug("HttpClient" + ex.getStatusCode());
			logger.debug("Exception" + ex.getLocalizedMessage());
			CimCBSresponse cbsResponse = new CimCBSresponse();
			return new ResponseEntity<>(cbsResponse, HttpStatus.BAD_REQUEST);
		} catch (HttpServerErrorException ex) {
			logger.debug("HttpServert" + ex.getStatusCode());
			logger.debug("Exception" + ex.getLocalizedMessage());
			CimCBSresponse cbsResponse = new CimCBSresponse();
			return new ResponseEntity<>(cbsResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception ex) {
			logger.debug("Ex Exception" + ex.getLocalizedMessage());
			CimCBSresponse cbsResponse = new CimCBSresponse();
			return new ResponseEntity<>(cbsResponse, HttpStatus.BAD_REQUEST);
		}

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

		logger.debug(cimCBSrequest.toString());

		HttpEntity<CimCBSrequest> entity = new HttpEntity<>(cimCBSrequest, httpHeaders);

		///// Call REST API
		ResponseEntity<CimCBSresponse> response = null;
		try {
			logger.info("Sending message to ESB Cable for Debit the Customer Amount");
			response = restTemplate.postForEntity(
					env.getProperty("cimESB.url") + "appname=" + env.getProperty("cimESB.appname") + "&prgname="
							+ env.getProperty("cimESB.prgname") + "&arguments=" + env.getProperty("cimESB.arguments"),
					entity, CimCBSresponse.class);
			return new ResponseEntity<>(response.getBody(), HttpStatus.OK);
		} catch (HttpClientErrorException ex) {
			logger.debug("HttpClient" + ex.getStatusCode());
			logger.debug("Exception" + ex.getLocalizedMessage());
			logger.info("HTTP Client Error:" + ex.getLocalizedMessage() + "-" + ex.getStatusCode());
			CimCBSresponse cbsResponse = new CimCBSresponse();
			return new ResponseEntity<>(cbsResponse, HttpStatus.BAD_REQUEST);
		} catch (HttpServerErrorException ex) {
			CimCBSresponse cbsResponse = new CimCBSresponse();
			return new ResponseEntity<>(cbsResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception ex) {
			logger.info("HTTP Ex Error:" + ex.getLocalizedMessage());
			CimCBSresponse cbsResponse = new CimCBSresponse();
			return new ResponseEntity<>(cbsResponse, HttpStatus.BAD_REQUEST);
		}

	}

	public ResponseEntity<CimCBSresponse> cbsResponseSuccess(String requestUUID) {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);

		TranCimCBSTable data = tranCimCBSTableRep.findById(requestUUID).get();

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
		cimCBSrequestData.setInitatorTransactionNo(data.getInit_tran_no());
		cimCBSrequestData.setPostToCBS(Boolean.FALSE);
		cimCBSrequestData.setTransactionType("");
		cimCBSrequestData.setIsReversal("");
		cimCBSrequestData.setTransactionNoFromCBS("");
		cimCBSrequestData.setCustomerName(data.getCustomer_name());
		cimCBSrequestData.setFromAccountNo(data.getFrom_account_no());
		cimCBSrequestData.setToAccountNo(data.getTo_account_no());
		cimCBSrequestData.setTransactionAmount(new BigDecimal(data.getTran_amt().toString()));
		cimCBSrequestData.setTransactionDate(new SimpleDateFormat("yyyy-MM-dd").format(data.getTran_date()));
		cimCBSrequestData.setTransactionCurrency(data.getTran_currency());
		cimCBSrequestData.setTransactionParticularCode(data.getTran_particular_code());
		cimCBSrequestData.setCreditRemarks("");
		cimCBSrequestData.setDebitRemarks("");
		cimCBSrequestData.setReservedField1(data.getResv_field_1());
		cimCBSrequestData.setReservedField2(data.getResv_field_2());

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

		logger.debug(cimCBSrequest.toString());
		HttpEntity<CimCBSrequest> entity = new HttpEntity<>(cimCBSrequest, httpHeaders);

		ResponseEntity<CimCBSresponse> response = null;
		try {
			logger.info("Sending message to CBS credit using restTemplate Success");
			response = restTemplate.postForEntity(
					env.getProperty("cimESB.url") + "appname=" + env.getProperty("cimESB.appname") + "&prgname="
							+ env.getProperty("cimESB.prgname") + "&arguments=" + env.getProperty("cimESB.arguments"),
					entity, CimCBSresponse.class);

			return new ResponseEntity<>(response.getBody(), HttpStatus.OK);

		} catch (HttpClientErrorException ex) {
			logger.debug("HttpClient" + ex.getStatusCode());
			logger.debug("Exception" + ex.getLocalizedMessage());
			CimCBSresponse cbsResponse = new CimCBSresponse();
			return new ResponseEntity<>(cbsResponse, HttpStatus.BAD_REQUEST);
		} catch (HttpServerErrorException ex) {
			CimCBSresponse cbsResponse = new CimCBSresponse();
			return new ResponseEntity<>(cbsResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception ex) {
			CimCBSresponse cbsResponse = new CimCBSresponse();
			return new ResponseEntity<>(cbsResponse, HttpStatus.BAD_REQUEST);
		}

	}

	public ResponseEntity<CimCBSresponse> cbsResponseFailure(String requestUUID) {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		logger.info("inside to send reversal msg inside API");
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
		logger.info("inside to send reversal msg inside API after Header");
		CimCBSrequestData cimCBSrequestData = new CimCBSrequestData();
		cimCBSrequestData.setTransactionNo(data.getTran_no());
		cimCBSrequestData.setInitiatingChannel(data.getInit_channel());
		cimCBSrequestData.setInitatorTransactionNo((data.getInit_tran_no() == null) ? "" : data.getInit_tran_no());
		if (data.getPost_to_cbs().equals("True")) {
			cimCBSrequestData.setPostToCBS(Boolean.TRUE);
		} else {
			cimCBSrequestData.setPostToCBS(Boolean.FALSE);
		}

		if (data.getInit_channel().contains("Jmeter") || data.getInit_channel().contains("Postman")) {
			cimCBSrequestData.setBranchId("TESTING");
		}

		cimCBSrequestData.setTransactionType(data.getTran_type());
		cimCBSrequestData.setIsReversal(data.getIsreversal());
		cimCBSrequestData
				.setTransactionNoFromCBS((data.getTran_no_from_cbs() == null) ? "" : data.getTran_no_from_cbs());
		cimCBSrequestData.setCustomerName(data.getCustomer_name());
		cimCBSrequestData.setFromAccountNo(data.getFrom_account_no());
		cimCBSrequestData.setToAccountNo(data.getTo_account_no());

		cimCBSrequestData.setTransactionAmount(new BigDecimal(data.getTran_amt().toString()));
		// cimCBSrequestData.setTransactionDate(new
		// SimpleDateFormat("dd-MM-yyyy").format(data.getTran_date()));
		cimCBSrequestData.setTransactionDate(new SimpleDateFormat("yyyy-MM-dd").format(data.getTran_date()));

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
		logger.info("inside to send reversal msg inside API before Status");
		cimCBSrequestData.setIpsxTranStatus(Boolean.FALSE);
		cimCBSrequestData.setTransactionNoToCBS(data.getTransactionnotocbs());

		logger.info("inside to send reversal msg inside API after status");
		cimCBSrequest.setData(cimCBSrequestData);

		logger.info(cimCBSrequest.toString());
		logger.info(listener.generateJsonFormat(cimCBSrequest.toString()));
		///////////////////////////////////////////////////

		HttpEntity<CimCBSrequest> entity = new HttpEntity<>(cimCBSrequest, httpHeaders);

		///// Call REST API
		ResponseEntity<CimCBSresponse> response = null;
		try {
			logger.info("Sending Failure message to ESB Cable ");
			response = restTemplate.postForEntity(
					env.getProperty("cimESB.url") + "appname=" + env.getProperty("cimESB.appname") + "&prgname="
							+ env.getProperty("cimESB.prgname") + "&arguments=" + env.getProperty("cimESB.arguments"),
					entity, CimCBSresponse.class);
			return new ResponseEntity<>(response.getBody(), HttpStatus.OK);
		} catch (HttpClientErrorException ex) {
			logger.debug("HttpClient" + ex.getStatusCode());
			logger.debug("Exception" + ex.getLocalizedMessage());
			CimCBSresponse cbsResponse = new CimCBSresponse();
			return new ResponseEntity<>(cbsResponse, HttpStatus.BAD_REQUEST);
		} catch (HttpServerErrorException ex) {
			logger.debug("HttpServerErrorException" + ex.getStatusCode());
			logger.debug("Exception" + ex.getLocalizedMessage());
			CimCBSresponse cbsResponse = new CimCBSresponse();
			return new ResponseEntity<>(cbsResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception ex) {
			logger.debug("Exception" + ex.getLocalizedMessage());
			CimCBSresponse cbsResponse = new CimCBSresponse();
			return new ResponseEntity<>(cbsResponse, HttpStatus.BAD_REQUEST);
		}

	}

	public ResponseEntity<CimCBSresponse> updateStatusMobile(String transactionNo, String tranId, Date transactionDate,
	        String referenceId, String toAccountNumber, BigDecimal transactionAmount, String isSuccess,
	        String statusCode, String message, String receipt_number) {

	    HttpHeaders httpHeaders = new HttpHeaders();
	    httpHeaders.setContentType(MediaType.APPLICATION_JSON);
	    CimUpdatePaymentStatusRequest CimCBSrequestData = new CimUpdatePaymentStatusRequest();
	    CimCBSrequestData.setTransactionNo(transactionNo);
	    CimCBSrequestData.setTranId(tranId);
	    CimCBSrequestData.setTransactionDate(new SimpleDateFormat("yyyy-MM-dd").format(transactionDate));
	    CimCBSrequestData.setReferenceId(referenceId);
	    CimCBSrequestData.setToAccountNumber(toAccountNumber);
	    CimCBSrequestData.setTransactionAmount(transactionAmount);
	    CimCBSrequestData.setIsSuccess("ACSP".equals(isSuccess));
	    CimCBSrequestData.setStatusCode(statusCode);
	    CimCBSrequestData.setMessage(message);
	    CimCBSrequestData.setReceiptNumber(receipt_number);

	    HttpEntity<CimUpdatePaymentStatusRequest> entity = new HttpEntity<>(CimCBSrequestData, httpHeaders);

	    ResponseEntity<CimCBSresponse> response = null;

	    try {
	        logger.info("Sending message to updateStatusMobile using restTemplate");
	        logger.info("updateStatusMobile Request: " + CimCBSrequestData.toString());

	        response = restTemplate.postForEntity(env.getProperty("cimUpdatePayment.url"), entity, CimCBSresponse.class);
	        logger.info("updateStatusMobile Response: " + response);

	        if (response != null && response.getBody() != null) {
	            return new ResponseEntity<>(response.getBody(), HttpStatus.OK);
	        } else {
	            CimCBSresponse cbsResponse = new CimCBSresponse();
	            return new ResponseEntity<>(cbsResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    } catch (HttpClientErrorException ex) {
	        logger.debug("HttpClientErrorException: " + ex.getStatusCode());
	        logger.debug("Exception: " + ex.getLocalizedMessage());
	        CimCBSresponse cbsResponse = new CimCBSresponse();
	        return new ResponseEntity<>(cbsResponse, HttpStatus.BAD_REQUEST);
	    } catch (HttpServerErrorException ex) {
	        logger.debug("HttpServerErrorException: " + ex.getStatusCode());
	        logger.debug("Exception: " + ex.getLocalizedMessage());
	        CimCBSresponse cbsResponse = new CimCBSresponse();
	        return new ResponseEntity<>(cbsResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	    } catch (Exception ex) {
	        logger.debug("Exception: " + ex.getLocalizedMessage());
	        CimCBSresponse cbsResponse = new CimCBSresponse();
	        return new ResponseEntity<>(cbsResponse, HttpStatus.BAD_REQUEST);
	    }
	}

}
