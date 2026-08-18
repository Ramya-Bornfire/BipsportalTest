package com.bornfire.config;

import org.springframework.stereotype.Component;

@Component
public class ErrorResponseCode {
	public String ErrorCode(String code) {

		String responseDesc = null;
		if (code.equals("CIM0")) {
			responseDesc = "CIM0:Success";
		} else if (code.equals("AC01")) {
			responseDesc = "AC01:Incorrect Account Number";
		} else if (code.equals("AC03")) {
			responseDesc = "AC03:Invalid Creditor Account Number";
		} else if (code.equals("AC04")) {
			responseDesc = "AC04:Closed Account Number";
		} else if (code.equals("AC06")) {
			responseDesc = "AC06:Blocked Account";
		} else if (code.equals("AC09")) {
			responseDesc = "AC09:Invalid Account Currency";
		}else if (code.equals("AG01")) {
			responseDesc = "AG01:Transaction Forbidden";
		}else if (code.equals("AG02")) {
			responseDesc = "AG02:Invalid Bank Operation Code";
		}else if (code.equals("AG03")) {
			responseDesc = "AG03:Transaction Not Supported";
		}else if (code.equals("AGNT")) {
			responseDesc = "AGNT:Incorrect Agent";
		}else if (code.equals("AM01")) {
			responseDesc = "AM01:Zero Amount";
		}else if (code.equals("AM02")) {
			responseDesc = "AM02:Not Allowed Amount";
		} else if (code.equals("AM04")) {
			responseDesc = "AM04:Insufficient Funds";
		}else if (code.equals("AM05")) {
			responseDesc = "AM05:Duplication";
		}else if (code.equals("AM09")) {
			responseDesc = "AM09:Wrong Amount";
		}else if (code.equals("AM11")) {
			responseDesc = "AM11:Invalid Transaction Currency";
		}else if (code.equals("AM12")) {
			responseDesc = "AM12:Invalid Amount";
		}else if (code.equals("ARDT")) {
			responseDesc = "ARDT:Already Returned Transaction";
		}else if (code.equals("CUST")) {
			responseDesc = "CUST:Requested By Customer";
		}else if (code.equals("DUPL")) {
			responseDesc = "DUPL:Duplicate Payment";
		}else if (code.equals("MS02")) {
			responseDesc = "MS02:Not Specified Reason Customer Generated";
		}else if (code.equals("MS03")) {
			responseDesc = "MS03:Not Specified Reason Agent Generated";
		}else if (code.equals("NOAS")) {
			responseDesc = "NOAS:No Answer From Customer";
		}else if (code.equals("NOOR")) {
			responseDesc = "NOOR:No Original Transaction Received";
		}else if (code.equals("RC01")) {
			responseDesc = "RC01:Bank Identifier Incorrect";
		}else if (code.equals("RR01")) {
			responseDesc = "RR01:Missing Debtor Account or Identification";
		}else if (code.equals("RR02")) {
			responseDesc = "RR02:Missing Debtor Name or Address";
		}else if (code.equals("RR03")) {
			responseDesc = "RR03:Missing Creditor Name or Address";
		}else if (code.equals("RR04")) {
			responseDesc = "RR04:Regulatory Reason";
		}else if (code.equals("RR07")) {
			responseDesc = "RR07:Remittance Information Invalid";
		}else if (code.equals("TECH")) {
			responseDesc = "TECH:Technical Problem";
		}else if (code.equals("AC13")) {
			responseDesc = "AC13:Invalid Account Type";
		}else if (code.equals("IV")) {
			responseDesc = "IV:Invalid Terminal ID";
		}  else {
			responseDesc = "TECH:Technical Problem";
		}
		return responseDesc;
	}

	public String validationError(String code) {
		String responseDesc = null;
		
		if(code.equals("BIPS1")) {
			responseDesc = "BIPS1:Missing Debtor Name";
		}else if(code.equals("BIPS2")) {
			responseDesc = "BIPS2:Missing Debtor Account Number";
		}else if(code.equals("BIPS3")) {
			responseDesc = "BIPS3:Missing Creditor Name";
		}else if(code.equals("BIPS4")) {
			responseDesc = "BIPS4:Missing Creditor Account Number";
		}else if(code.equals("BIPS5")) {
			responseDesc = "BIPS5:Missing Transaction Amount";
		}else if(code.equals("BIPS6")) {
			responseDesc = "BIPS6:Zero Amount";
		}else if(code.equals("BIPS7")) {
			responseDesc = "BIPS7:Missing Currency Code";
		}else if(code.equals("BIPS8")) {
			responseDesc = "BIPS8:Invalid Currency Code";
		}else if(code.equals("BIPS9")) {
			responseDesc = "BIPS9:Invalid Debtor Account Number";
		}else if(code.equals("BIPS10")) {
			responseDesc = "BIPS10:Invalid Bank Code";
		}else if(code.equals("BIPS11")) {
			responseDesc = "BIPS11:Invalid Purpose Code";
		}else if(code.equals("BIPS12")) {
			responseDesc = "BIPS12:Maximum Amount Not Permitted";
		}else if(code.equals("BIPS13")) {
			responseDesc = "BIPS13:Unable to Process Request your rest.Dublicate Reference ID:PID";
		}else if(code.equals("BIPS14")) {
			responseDesc = "BIPS14:Invalid Response";
		}else if(code.equals("BIPS15")) {
			responseDesc = "BIPS15:Invalid Request";
		}else if(code.equals("BIPS15-2")) {
			responseDesc = "BIPS15:Convenience Indicator Fee Type Required";
		}else if(code.equals("BIPS15-3")) {
			responseDesc = "BIPS15:Convenience Indicator Fee Type Allowed Input(Fixed,Percentage)";
		}else if(code.equals("BIPS15-4")) {
			responseDesc = "BIPS15:Convenience Indicator Fee Required";
		}else if(code.equals("BIPS15-5")) {
			responseDesc = "BIPS15:Convenience Indicator Fee should not exceed 15 characters";
		}else if(code.equals("BIPS15-6")) {
			responseDesc = "BIPS15:Convenience Indicator Fee should not exceed 5 characters";
		}else if(code.equals("BIPS15-7")) {
			responseDesc = "BIPS15:Convenience Indicator required";
		}else if(code.equals("BIPS15-8")) {
			responseDesc = "BIPS15:Invalid PayeeParticipantCode";
		}else if(code.equals("BIPS15-9")) {
			responseDesc = "BIPS15:Tip Or Convenience Indicator Allowed Input(01,02,03)";
		}else if(code.equals("BIPS16")) {
			responseDesc = "BIPS16:Invalid Document Type";
		}else if(code.equals("BIPS17")) {
			responseDesc = "BIPS17:QR Code Format Error";
		}else if(code.equals("BIPS18")) {
			responseDesc = "BIPS18:QR Code Not Detected";
		}else if(code.equals("BIPS19")) {
			responseDesc = "BIPS19:Invalid Currency Type";
		}else if(code.equals("BIPS20")) {
			responseDesc = "BIPS20:Invalid Country Code";
		}else if(code.equals("BIPS21")){
			responseDesc = "BIPS21:Invalid Remitter Bank Code";
		}else if(code.equals("BIPS23")) {
			responseDesc = "BIPS23:Invalid Beneficiary Bank Code";
		}else if(code.equals("BIPS22")){
			responseDesc = "BIPS22:Registered Remitter Account BankCode doesn't match with Remitter Bank Code";
		}else if(code.equals("BIPS24")){
			responseDesc = "BIPS24:Unable to Process Request your rest.Dublicate Request UID";
		}else if(code.equals("BIPSR1")){
			responseDesc = "BIPSR1:Fields Required";
		}else if(code.equals("BIPS25")) {
			responseDesc = "BIPS25:Unable to Process Request your rest.Dublicate Reference ID:X-REQUEST_ID";
		}else if(code.equals("BIPS26")) {
			responseDesc = "BIPS26:Invalid Scheme Name";
		}else if(code.equals("BIPS27")) {
			responseDesc = "BIPS27:Invalid Permission";
		}else if(code.equals("BIPS28")) {
			responseDesc = "BIPS28:Invalid Consent ID";
		}else if(code.equals("BIPS29")) {
			responseDesc = "BIPS29:Invalid Request";
		}else if(code.equals("BIPS30")){
			responseDesc = "BIPS30:Reversal Transaction already exist";
		}else if(code.equals("BIPS31")) {
			responseDesc = "BIPS31:UnRegistered Remitter Account Exist";
		}else if(code.equals("BIPS32")) {
			responseDesc = "BIPS32:Document ID doesn't Exist";
		}else if(code.equals("BIPS33")) {
			responseDesc = "BIPS33:Daily Limit Exceeds";
		}else if(code.equals("BIPS501")) {
			responseDesc = "BIPS501:Internal Server Problem";
		}else if(code.equals("BIPS51")) {
			responseDesc = "BIPS51:Daily Limit Exceeded";
		}else if(code.equals("BIPS52")) {
			responseDesc = "BIPS52:Weekly Limit Exceeded";
		}else if(code.equals("BIPS53")) {
			responseDesc = "BIPS53:Monthly Limit Exceeded";
		}
		else if(code.equals("BIPS34")) {
			responseDesc = "BIPS34:Tran ID doesn't Exist";
		}else if(code.equals("BIPSQ32")) {
			responseDesc = "BIPSQ32:Merchant doesn't Exist";
		}else if(code.equals("BIPSQ33")) {
			responseDesc = "BIPSQ33:Merchant Inactive";
		}else if(code.equals("BIPSQR1")) {
			responseDesc = "BIPSQR1:Request doesn't Exist";
		}else if(code.equals("BIPSQR2")){
			responseDesc = "BIPSQR2:Required Fields Missing";
		}
		else if(code.equals("BIPS55")){
			responseDesc = "ChargeBack:Same User Not Approve";
		}
		else if(code.equals("BIPSQRTran01")){
			responseDesc = "Bill Number not Found";
		}
		else if(code.equals("BIPSQRTran07")){
			responseDesc = "Duplicate Bill Number";
		}
		else if(code.equals("BIPSQRTran02")){
			responseDesc = "Store Label not Found";
		}
		else if(code.equals("BIPSQRTran03")){
			responseDesc = "Loyalty Number not Found";
		}
		else if(code.equals("BIPSQRTran04")){
			responseDesc = "Customer Label not Found";
		}
		else if(code.equals("BIPSQRTran05")){
			responseDesc = "Terminal Label not Found";
		}
		else if(code.equals("BIPSQRTran06")){
			responseDesc = "Reference Label not Found";
		}
		
		return responseDesc;
	}
	
	public String ErrorCodeRegistration(String code) {

		String responseDesc = null;

		if (code.equals("1")) {
			responseDesc = "101:Invalid request";
		} else if (code.equals("2")) {
			responseDesc = "102:No registration request for received authorization";
		} else if (code.equals("3")) {
			responseDesc = "103:Authorization request doesn’t match registration request";
		} else if(code.equals("4")){
			responseDesc = "104:Internal error";
		}else if(code.equals("5")){
			responseDesc = "202:Receiver's URL is not found";
		}else if(code.equals("6")){
			responseDesc = "302:Internal error (response from receiver)";
		}else if(code.equals("7")){
			responseDesc = "303:Invalid account";
		}else if(code.equals("8")){
			responseDesc = "304:Incorrect customer data";
		}else if(code.equals("9")){
			responseDesc = "305:Account is closed";
		}else if(code.equals("10")){
			responseDesc = "306:Account is blocked/dormant";
		}else if(code.equals("11")){
			responseDesc = "307:Authorization is expired";
		}else if(code.equals("12")){
			responseDesc = "308:Authorization is incorrect";
		}else if(code.equals("13")){
			responseDesc = "315:Invalid consent";
		}
		
		else if(code.equals("21")) {
			responseDesc="351:Insufficient Fund";
		}else if(code.equals("22")) {
			responseDesc="352:Maximum Amount Not Permitted(must be less than or equal 10000 Only)";
		}else if(code.equals("23")) {
			responseDesc="204:No account found with specified CPR/CR";
		}else if(code.equals("24")) {
			responseDesc="401:wrong credentials";
		}else if(code.equals("25")) {
			responseDesc="500:Internal Server Error";
		}else if(code.equals("31")){
			responseDesc = "400:QR CODE VALIDATION FAILED";
		}else if(code.equals("32")){
			responseDesc = "PE:Payment validity expired";
		}
		
		else {
			responseDesc = "104:Internal error";
		}
		return responseDesc;
	

}
	
	
}

