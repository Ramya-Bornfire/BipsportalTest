package com.bornfire.controller;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.sql.SQLException;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.xml.bind.JAXBException;
import javax.xml.datatype.DatatypeConfigurationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.bornfire.entity.CIMMerchantDirectFndRequest;
import com.bornfire.entity.LoginSecurity;
import com.bornfire.entity.LoginSecurityRepository;
import com.bornfire.entity.MCCreditTransferResponse;
import com.bornfire.exception.CustomException;
import com.bornfire.exception.ErrorResponse;
import com.bornfire.exception.IPSXException;

@RestController
@RequestMapping("/api")
public class PortalController {
	private static final Logger logger = LoggerFactory.getLogger(PortalController.class);

	@Autowired
	IpsDao ipsdao;

	@Autowired
	PortConnection portConnection;

	@Autowired
	ErrorResponseCode errorCode;

	@PostMapping(path = "/ws/directMerchantFndTransfer", produces = "application/json", consumes = "application/json")
	public ResponseEntity<MCCreditTransferResponse> directMerchantFndTransfer(
			@RequestHeader(value = "P-ID", required = true) @NotEmpty(message = "Required") String p_id,
			@RequestHeader(value = "PSU-Device-ID", required = true) @NotEmpty(message = "Required") String psuDeviceID,
			@RequestHeader(value = "PSU-IP-Address", required = true) String psuIpAddress,
			@RequestHeader(value = "PSU-ID", required = false) String psuID,
			@RequestHeader(value = "PSU-Channel", required = true) String channelID,
			@RequestHeader(value = "PSU-Resv-Field1", required = false) String resvfield1,
			@RequestHeader(value = "PSU-Resv-Field2", required = false) String resvfield2,
			@Valid @RequestBody CIMMerchantDirectFndRequest mcCreditTransferRequest)
			throws DatatypeConfigurationException, JAXBException, KeyManagementException, UnrecoverableKeyException,
			KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException, SQLException {

		logger.info("Service Starts" + p_id);

		MCCreditTransferResponse response = null;
		System.out.print("Request---->" + mcCreditTransferRequest);
		logger.info("Calling Credit Transfer Connection flow Starts");
		if (ipsdao.checkConvenienceFeeValidation(mcCreditTransferRequest)) {
			if (ipsdao.invalidP_ID(p_id)) {
				response = portConnection.createMerchantFTConnection(psuDeviceID, psuIpAddress, psuID,
						mcCreditTransferRequest, p_id, channelID, resvfield1, resvfield2);
			} else {
				String responseStatus = errorCode.validationError("BIPS13");
				throw new IPSXException(responseStatus);
			}
		}

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping("/ws/revertMerchantFndTransfer")
	public ResponseEntity<?> revertMerchantFndTransfer(@RequestParam String seqUniqueID, @RequestParam String userid)
			throws DatatypeConfigurationException, JAXBException, KeyManagementException, UnrecoverableKeyException,
			KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException {

		try {
			MCCreditTransferResponse response = portConnection.createReverseFundTransfer(seqUniqueID, userid);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (CustomException e) {
			return new ResponseEntity<>(new ErrorResponse(e.getMessage(), "Custom error details"), e.getStatus());
		} catch (Exception e) {
			return new ResponseEntity<>(new ErrorResponse("An unexpected error occurred", e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Autowired
	IpsDao ipsDao;

	@PostMapping("/ws/testcbs")
	public void testCBSTable(@RequestParam String sysTraceAuditNumber, @RequestParam String acctNumber,
			@RequestParam String trAmt, @RequestParam String tranType, @RequestParam String currencyCode,
			@RequestParam String tranStatus, @RequestParam String tranStatusError, @RequestParam String seqUniqueID,
			@RequestParam String user, @RequestParam String settl_acct, @RequestParam String settl_acct_type,
			@RequestParam String tran_charge_type) {

		//System.out.println("TESTING");

		try {
			ipsDao.updateTranCBS(sysTraceAuditNumber, acctNumber, trAmt, tranType, currencyCode, tranStatus,
					tranStatusError, seqUniqueID, user, settl_acct, settl_acct_type, tran_charge_type);
		} catch (Exception e) {
			System.err.println("Unexpected Error: " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	@Autowired
	LoginSecurityRepository loginSecurityRepository;
	
	@GetMapping("/LoginSecurityData")
	public LoginSecurity LoginSecurityData() {
		LoginSecurity loginSecurity = new LoginSecurity();

		List<LoginSecurity> loginSecurityList = loginSecurityRepository.findAll();

		if (loginSecurityList.size() > 0) {
			loginSecurity = loginSecurityRepository.findAll().get(0);
		}
		return loginSecurity;
	}
	
}
