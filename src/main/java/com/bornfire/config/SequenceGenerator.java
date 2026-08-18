package com.bornfire.config;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.TimeZone;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.bornfire.entity.TranCimCBSTableRep;

@Component
public class SequenceGenerator {
	private static final String CHAR_LIST = "0123456789";
	private static final int OTP = 5;
	private static final String NUM_LIST = "0123456789";
	private static final int SEQ_MSG_ID = 5;
	private static final int MSG_SEQ = 6;
	private static final int SEQ_UNIQUE_ID = 6;
	@Autowired
	TranCimCBSTableRep tranCimCBSTableRep;

	@Autowired
	Environment env;

	public String generateOTP() {

		StringBuffer randStr = new StringBuffer();
		for (int i = 0; i < OTP; i++) {
			int number = getRandomMsgNumber();
			char ch = NUM_LIST.charAt(number);
			randStr.append(ch);
		}
		return randStr.toString();
	}

	public String generateSeqUniqueID() {

		StringBuffer randStr = new StringBuffer();
		randStr.append(env.getProperty("ipsx.userS"));
		randStr.append(new SimpleDateFormat("yyMMddHHmmss").format(new Date()));

		for (int i = 0; i < SEQ_UNIQUE_ID; i++) {
			int number = getRandomMsgNumber();
			char ch = NUM_LIST.charAt(number);
			randStr.append(ch);
		}
		return randStr.toString();
	}

	public String generateMsgSequence() {

		StringBuffer randStr = new StringBuffer();
		for (int i = 0; i < MSG_SEQ; i++) {
			int number = getRandomMsgNumber();
			char ch = NUM_LIST.charAt(number);
			randStr.append(ch);
		}
		return randStr.toString();
	}

	public String generateRequestUUId() {

		StringBuffer randStr = new StringBuffer();
		randStr.append(new SimpleDateFormat("yyyyMMdd").format(new Date()));
		randStr.append("_");

		Long request_UUID = tranCimCBSTableRep.getRequestUUID();

		randStr.append(String.format("%05d", request_UUID));

		return randStr.toString();
	}

	private AtomicInteger counter = new AtomicInteger(0);

	public String generateUniqueId() {
		String formattedCounter = String.format("%03d", counter.incrementAndGet());

		String uniqueId = "PI" + formattedCounter;

		return uniqueId;
	}

	public String generateSystemTraceAuditNumber() {

		StringBuffer randStr = new StringBuffer();
		randStr.append(new SimpleDateFormat("yyyyMMdd").format(new Date()));

		Long cbs_Tran_no = tranCimCBSTableRep.getCBSTranNo();

		randStr.append(String.format("%06d", cbs_Tran_no));

		return randStr.toString();
	}

	private int getRandomNumber() {
		int randomInt = 0;
		Random randomGenerator = new Random();
		randomInt = randomGenerator.nextInt(CHAR_LIST.length());

		if (randomInt - 1 == -1) {
			return randomInt;
		} else {
			return randomInt - 1;
		}
	}

	private int getRandomMsgNumber() {
		int randomInt = 0;
		Random randomGenerator = new Random();
		randomInt = randomGenerator.nextInt(NUM_LIST.length());

		if (randomInt - 1 == -1) {
			return randomInt;
		} else {
			return randomInt - 1;
		}
	}

	public String getCustomerRandomNumber() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		String dateStr = dateFormat.format(new Date());
		dateStr = dateStr.replace("-", "");
		Random random = new Random();
		int randomDigits = 1000 + random.nextInt(9000);
		String randomNumber = dateStr + randomDigits;
		//System.out.println("Generated Number: " + randomNumber);
		return randomNumber;
	}

	@Autowired
	SequenceGenerator sequence;

	public String format() {
		TimeZone mur = TimeZone.getTimeZone("GMT+4");
		SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");
		sdf.setTimeZone(mur);
		return sdf.format(new Date()) + generateRandomNumber();
	}

	private static final int RANDOM_STRING_LENGTH = 6;

	public String generateRandomNumber() {

		StringBuffer randStr = new StringBuffer();
		for (int i = 0; i < RANDOM_STRING_LENGTH; i++) {
			int number = getRandomNumber();
			char ch = CHAR_LIST.charAt(number);
			randStr.append(ch);
		}
		return randStr.toString();
	}

}
