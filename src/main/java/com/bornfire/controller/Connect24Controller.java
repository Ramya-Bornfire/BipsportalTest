//package com.bornfire.controller;
//
//import static com.bornfire.exception.ErrorResponseCode.SERVER_ERROR;
//
//import java.io.IOException;
//import java.security.KeyManagementException;
//import java.security.KeyStoreException;
//import java.security.NoSuchAlgorithmException;
//import java.security.UnrecoverableKeyException;
//import java.security.cert.CertificateException;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.Optional;
//
//import javax.xml.bind.JAXBException;
//import javax.xml.datatype.DatatypeConfigurationException;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestHeader;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.server.ServerErrorException;
//
//import com.bornfire.exception.Connect24Exception;
//import com.bornfire.exception.IPSXException;
//import com.bornfire.service.Connect24Service;
//
//@RestController
//@RequestMapping("/api")
//public class Connect24Controller {
//
//	private static final Logger logger = LoggerFactory.getLogger(Connect24Controller.class);
//
//	@Autowired
//	Connect24Service Connect24Service;
//
//	@GetMapping(path = "/ws/getContact", produces = "application/json", consumes = "application/json")
//	public ResponseEntity<MCCreditTransferResponse> sendCreditTransferMessage(
//			@RequestHeader(value = "PSU_Device_ID", required = true) String psuDeviceID,
//			@RequestHeader(value = "PSU_IP_Address", required = false) String psuIpAddress,
//			@RequestHeader(value = "PSU_ID", required = false) String psuID,
//			@RequestHeader(value = "Participant_BIC", required = true) String senderParticipantBIC,
//			@RequestHeader(value = "Participant_SOL", required = true) String participantSOL)
//			throws DatatypeConfigurationException, JAXBException, KeyManagementException, UnrecoverableKeyException,
//			KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException {
//		
//		ResponseEntity<AccountContactResponse> connect24ResponseAccContactExist = Connect24Service
//				.getAccountContact(psuDeviceID, "", "", mcCreditTransferRequest.getFrAccount().getAcctNumber());
//		
//if (connect24ResponseAccContactExist.getStatusCode() == HttpStatus.OK) {
//			
//		} else if (connect24ResponseAccContactExist.getStatusCode() == HttpStatus.INTERNAL_SERVER_ERROR) {
//			throw new ServerErrorException(SERVER_ERROR);
//		} else {
//			throw new Connect24Exception(errorCode.ErrorCode(connect24ResponseAccContactExist.getBody().getError()));
//		}
//		logger.info("RESPONSE  -> " + response);
//		return new ResponseEntity<>(response, HttpStatus.OK);
//
//}
