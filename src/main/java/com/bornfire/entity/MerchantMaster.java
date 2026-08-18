package com.bornfire.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Table(name="MERCHANT_MASTER_TABLE")
@Entity
public class MerchantMaster {
	@Id
	private String	merchant_id;
	private String	merchant_pow_ca_no;
	private String	merchant_acc_no;
	private String	merchant_addr;
	private String	merchant_legal_id;
	private String	merchant_cont_per;
	private String	merchant_name;
	private String	merchant_cont_details;
	private String	company_name;
	private String	trading_name;
	private String	bill_acc_no;
	private String	tran_curr;
	private String	tran_amount;
	private String	remitter;
	private String	remitter_acc_no;
	private String	remitter_ref_no;
	private String	tran_type;
	private String	tran_remarks1;
	private String	merchant_corp_name;
	private String	merchant_mob_no;
	private String	merchant_type;
	private String	type_of_qr;
	private String	merchant_cat_code;
	private String	merchant_fees;
	private String	merchant_city;
	private String	merchant_posting;
	private String	amount_percentage1;
	private String	periodicity1;
	private String	merchant_terminal;
	private String	merchant_number;
	private String	outlet_no;
	private String	entry_user;
	private Date	entry_time;
	private Date	modify_time;
	private String	auth_user;
	private String	modify_user;
	private Date	auth_time;
	private Character	entity_flg;
	private Character	del_flg;
	private String	freeze_flg;
	private String	freeze_type;
	private String	add_details_req;
	private String	paritioning_type;
	private Character	modify_flg;
	private String	amount_percentage2;
	private String	periodicity2;
	private String	tran_remarks2;
	private String	amount_percentage3;
	private String	periodicity3;
	private String	tran_remarks3;
	private String	tran_remarks;
	private String	version;
	private String	modes;
	private String	purpose;
	private String	orgid;
	private String	tid;
	private String	tr;
	private String	tn;
	private String	pa;
	private String	msid;
	private String	mtid;
	private String	bam;
	private String	curr;
	private String	invoiceno;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date	invoicedate;
	private String	invoicename;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date	qrexpire;
	private String	am;
	private String	pincode;
	private String	tier;
	private String	txntype;
	private String	qrmedium;
	private String	tranasactio_crncy;
	private String	tip_or_conv_indicator;
	private String conv_fees_type;
	private String	value_conv_fees;
	private String	zip_code;
	private String	bill_number;
	private String	loyalty_number;
	private String	customer_label;
	private String	store_label;
	private String	terminal_label;
	private String	reference_label;
	private String	purpose_of_tran;
	private String	status;
	private String merchant_coun_cod;
	private String merchant_brand;
	private String merchant_location;
	private String merchant_genre;
	private String merchant_onboard;
	private String type_maucas;
	private String type_upi;
	private String	tip_or_conv_indicator_upi;
	private String conv_fees_type_upi;
	private String	value_conv_fees_upi;
	private String bank_name;
	private String bank_account_no;
	private String bank_branch;
	private String bank_code;
	private String iban;
	private String currency;
	private String merchant_card_acc_id;
	private String merchant_mode;
	private String merchant_bips_no;
	private String mer_contact_per;
	private String mer_email_addr;
	private String send_notify;
	private String mer_phone_num;
	private String fee_desc;
	private String fee_type;
	private String amount_per;
	private String fee_freq;
	private String vat_collect;
	private String del_flag;
	private String status_enable;
	private String status_disable;
	private String merchant_cat_desc;
	private String notify_mode;
	private String mer_partner;
	private String merchant_addr_2;
	private String merchant_addr_3;
	private String merchant_office_number;
	private String merchant_out_addr_2;
	private String merchant_out_addr_3;
	private String merchant_out_addr_1;
	private String settlement_frequency;
	private String settlement_day;
	private String settlement_date;
	private Date next_settlement_date;
	private String static_field;
	private String	mer_user_id_r1;
	private String	mer_user_id_r2;
	private String	mer_user_id_r3;
	private String	mer_user_id_r4;
	private String	mer_user_id_r5;
	private String mer_user_id_r6;
	private String mer_user_id_r7;
	private String mer_user_id_r8;
	private String mer_user_id_r9;
	private String mer_user_id_r10;
	private String	mer_cont_pers;
	private String	mer_cont_pers_r2;
	private String	mer_cont_pers_r3;
	private String	mer_cont_pers_r4;
	private String	mer_cont_pers_r5;
	private String mer_cont_pers_r6;
	private String mer_cont_pers_r7;
	private String mer_cont_pers_r8;
	private String mer_cont_pers_r9;
	private String mer_cont_pers_r10;
	private BigDecimal	mer_ph_no;
	private BigDecimal	mer_ph_no_r2;
	private BigDecimal	mer_ph_no_r3;
	private BigDecimal	mer_ph_no_r4;
	private BigDecimal	mer_ph_no_r5;
	private BigDecimal mer_ph_no_r6;
	private BigDecimal mer_ph_no_r7;
	private BigDecimal mer_ph_no_r8;
	private BigDecimal mer_ph_no_r9;
	private BigDecimal mer_ph_no_r10;
	private BigDecimal	mer_ofc_no;
	private BigDecimal	mer_ofc_no_r2;
	private BigDecimal	mer_ofc_no_r3;
	private BigDecimal	mer_ofc_no_r4;
	private BigDecimal	mer_ofc_no_r5;
	private BigDecimal mer_ofc_no_r6;
	private BigDecimal mer_ofc_no_r7;
	private BigDecimal mer_ofc_no_r8;
	private BigDecimal mer_ofc_no_r9;
	private BigDecimal mer_ofc_no_r10;
	private String	mer_email_addr_r1;
	private String	mer_email_addr_r2;
	private String	mer_email_addr_r3;
	private String	mer_email_addr_r4;
	private String	mer_email_addr_r5;
	private String mer_email_addr_r6;
	private String mer_email_addr_r7;
	private String mer_email_addr_r8;
	private String mer_email_addr_r9;
	private String mer_email_addr_r10;
	private String	mer_notifi_mode;
	private String	mer_notifi_mode_r2;
	private String	mer_notifi_mode_r3;
	private String	mer_notifi_mode_r4;
	private String	mer_notifi_mode_r5;
	private String mer_notifi_mode_r6;
	private String mer_notifi_mode_r7;
	private String mer_notifi_mode_r8;
	private String mer_notifi_mode_r9;
	private String mer_notifi_mode_r10;
	private String	send_notifi;
	private String	send_notifi_r2;
	private String	send_notifi_r3;
	private String	send_notifi_r4;
	private String	send_notifi_r5;
	private String send_notifi_r6;
	private String send_notifi_r7;
	private String send_notifi_r8;
	private String send_notifi_r9;
	private String send_notifi_r10;
	private String ph_countrycode_r1;
	private String ph_countrycode_r2;
	private String ph_countrycode_r3;
	private String ph_countrycode_r4;
	private String ph_countrycode_r5;
	private String ph_countrycode_r6;
	private String ph_countrycode_r7;
	private String ph_countrycode_r8;
	private String ph_countrycode_r9;
	private String ph_countrycode_r10;
	private String ofc_countrycode_r1;
	private String ofc_countrycode_r2;
	private String ofc_countrycode_r3;
	private String ofc_countrycode_r4;
	private String ofc_countrycode_r5;
	private String ofc_countrycode_r6;
	private String ofc_countrycode_r7;
	private String ofc_countrycode_r8;
	private String ofc_countrycode_r9;
	private String ofc_countrycode_r10;
	private String bank_name2;
	private String bank_account_no2;
	private String bank_branch2;
	private String bank_code2;
	private String iban2;
	private String currency2;
	private String settlement_frequency2;
	private String settlement_day2;
	private String settlement_date2;
	private BigDecimal chargeback_amount;
	private String chargeback_approval;
	@Lob
	private byte[] photo;
	private String transaction_amount;
	private String detailed_address1;
	private String detailed_address2;
	
	
	public String getDetailed_address1() {
		return detailed_address1;
	}

	public void setDetailed_address1(String detailed_address1) {
		this.detailed_address1 = detailed_address1;
	}

	public String getDetailed_address2() {
		return detailed_address2;
	}

	public void setDetailed_address2(String detailed_address2) {
		this.detailed_address2 = detailed_address2;
	}

	public String getTransaction_amount() {
		return transaction_amount;
	}

	public void setTransaction_amount(String transaction_amount) {
		this.transaction_amount = transaction_amount;
	}

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	public String getMer_user_id_r6() {
		return mer_user_id_r6;
	}

	public void setMer_user_id_r6(String mer_user_id_r6) {
		this.mer_user_id_r6 = mer_user_id_r6;
	}

	public String getMer_user_id_r7() {
		return mer_user_id_r7;
	}

	public void setMer_user_id_r7(String mer_user_id_r7) {
		this.mer_user_id_r7 = mer_user_id_r7;
	}

	public String getMer_user_id_r8() {
		return mer_user_id_r8;
	}

	public void setMer_user_id_r8(String mer_user_id_r8) {
		this.mer_user_id_r8 = mer_user_id_r8;
	}

	public String getMer_user_id_r9() {
		return mer_user_id_r9;
	}

	public void setMer_user_id_r9(String mer_user_id_r9) {
		this.mer_user_id_r9 = mer_user_id_r9;
	}

	public String getMer_user_id_r10() {
		return mer_user_id_r10;
	}

	public void setMer_user_id_r10(String mer_user_id_r10) {
		this.mer_user_id_r10 = mer_user_id_r10;
	}

	public String getMer_cont_pers_r6() {
		return mer_cont_pers_r6;
	}

	public void setMer_cont_pers_r6(String mer_cont_pers_r6) {
		this.mer_cont_pers_r6 = mer_cont_pers_r6;
	}

	public String getMer_cont_pers_r7() {
		return mer_cont_pers_r7;
	}

	public void setMer_cont_pers_r7(String mer_cont_pers_r7) {
		this.mer_cont_pers_r7 = mer_cont_pers_r7;
	}

	public String getMer_cont_pers_r8() {
		return mer_cont_pers_r8;
	}

	public void setMer_cont_pers_r8(String mer_cont_pers_r8) {
		this.mer_cont_pers_r8 = mer_cont_pers_r8;
	}

	public String getMer_cont_pers_r9() {
		return mer_cont_pers_r9;
	}

	public void setMer_cont_pers_r9(String mer_cont_pers_r9) {
		this.mer_cont_pers_r9 = mer_cont_pers_r9;
	}

	public String getMer_cont_pers_r10() {
		return mer_cont_pers_r10;
	}

	public void setMer_cont_pers_r10(String mer_cont_pers_r10) {
		this.mer_cont_pers_r10 = mer_cont_pers_r10;
	}

	public BigDecimal getMer_ph_no_r6() {
		return mer_ph_no_r6;
	}

	public void setMer_ph_no_r6(BigDecimal mer_ph_no_r6) {
		this.mer_ph_no_r6 = mer_ph_no_r6;
	}

	public BigDecimal getMer_ph_no_r7() {
		return mer_ph_no_r7;
	}

	public void setMer_ph_no_r7(BigDecimal mer_ph_no_r7) {
		this.mer_ph_no_r7 = mer_ph_no_r7;
	}

	public BigDecimal getMer_ph_no_r8() {
		return mer_ph_no_r8;
	}

	public void setMer_ph_no_r8(BigDecimal mer_ph_no_r8) {
		this.mer_ph_no_r8 = mer_ph_no_r8;
	}

	public BigDecimal getMer_ph_no_r9() {
		return mer_ph_no_r9;
	}

	public void setMer_ph_no_r9(BigDecimal mer_ph_no_r9) {
		this.mer_ph_no_r9 = mer_ph_no_r9;
	}

	public BigDecimal getMer_ph_no_r10() {
		return mer_ph_no_r10;
	}

	public void setMer_ph_no_r10(BigDecimal mer_ph_no_r10) {
		this.mer_ph_no_r10 = mer_ph_no_r10;
	}

	public BigDecimal getMer_ofc_no_r6() {
		return mer_ofc_no_r6;
	}

	public void setMer_ofc_no_r6(BigDecimal mer_ofc_no_r6) {
		this.mer_ofc_no_r6 = mer_ofc_no_r6;
	}

	public BigDecimal getMer_ofc_no_r7() {
		return mer_ofc_no_r7;
	}

	public void setMer_ofc_no_r7(BigDecimal mer_ofc_no_r7) {
		this.mer_ofc_no_r7 = mer_ofc_no_r7;
	}

	public BigDecimal getMer_ofc_no_r8() {
		return mer_ofc_no_r8;
	}

	public void setMer_ofc_no_r8(BigDecimal mer_ofc_no_r8) {
		this.mer_ofc_no_r8 = mer_ofc_no_r8;
	}

	public BigDecimal getMer_ofc_no_r9() {
		return mer_ofc_no_r9;
	}

	public void setMer_ofc_no_r9(BigDecimal mer_ofc_no_r9) {
		this.mer_ofc_no_r9 = mer_ofc_no_r9;
	}

	public BigDecimal getMer_ofc_no_r10() {
		return mer_ofc_no_r10;
	}

	public void setMer_ofc_no_r10(BigDecimal mer_ofc_no_r10) {
		this.mer_ofc_no_r10 = mer_ofc_no_r10;
	}

	public String getMer_email_addr_r6() {
		return mer_email_addr_r6;
	}

	public void setMer_email_addr_r6(String mer_email_addr_r6) {
		this.mer_email_addr_r6 = mer_email_addr_r6;
	}

	public String getMer_email_addr_r7() {
		return mer_email_addr_r7;
	}

	public void setMer_email_addr_r7(String mer_email_addr_r7) {
		this.mer_email_addr_r7 = mer_email_addr_r7;
	}

	public String getMer_email_addr_r8() {
		return mer_email_addr_r8;
	}

	public void setMer_email_addr_r8(String mer_email_addr_r8) {
		this.mer_email_addr_r8 = mer_email_addr_r8;
	}

	public String getMer_email_addr_r9() {
		return mer_email_addr_r9;
	}

	public void setMer_email_addr_r9(String mer_email_addr_r9) {
		this.mer_email_addr_r9 = mer_email_addr_r9;
	}

	public String getMer_email_addr_r10() {
		return mer_email_addr_r10;
	}

	public void setMer_email_addr_r10(String mer_email_addr_r10) {
		this.mer_email_addr_r10 = mer_email_addr_r10;
	}

	public String getMer_notifi_mode_r6() {
		return mer_notifi_mode_r6;
	}

	public void setMer_notifi_mode_r6(String mer_notifi_mode_r6) {
		this.mer_notifi_mode_r6 = mer_notifi_mode_r6;
	}

	public String getMer_notifi_mode_r7() {
		return mer_notifi_mode_r7;
	}

	public void setMer_notifi_mode_r7(String mer_notifi_mode_r7) {
		this.mer_notifi_mode_r7 = mer_notifi_mode_r7;
	}

	public String getMer_notifi_mode_r8() {
		return mer_notifi_mode_r8;
	}

	public void setMer_notifi_mode_r8(String mer_notifi_mode_r8) {
		this.mer_notifi_mode_r8 = mer_notifi_mode_r8;
	}

	public String getMer_notifi_mode_r9() {
		return mer_notifi_mode_r9;
	}

	public void setMer_notifi_mode_r9(String mer_notifi_mode_r9) {
		this.mer_notifi_mode_r9 = mer_notifi_mode_r9;
	}

	public String getMer_notifi_mode_r10() {
		return mer_notifi_mode_r10;
	}

	public void setMer_notifi_mode_r10(String mer_notifi_mode_r10) {
		this.mer_notifi_mode_r10 = mer_notifi_mode_r10;
	}

	public String getSend_notifi_r6() {
		return send_notifi_r6;
	}

	public void setSend_notifi_r6(String send_notifi_r6) {
		this.send_notifi_r6 = send_notifi_r6;
	}

	public String getSend_notifi_r7() {
		return send_notifi_r7;
	}

	public void setSend_notifi_r7(String send_notifi_r7) {
		this.send_notifi_r7 = send_notifi_r7;
	}

	public String getSend_notifi_r8() {
		return send_notifi_r8;
	}

	public void setSend_notifi_r8(String send_notifi_r8) {
		this.send_notifi_r8 = send_notifi_r8;
	}

	public String getSend_notifi_r9() {
		return send_notifi_r9;
	}

	public void setSend_notifi_r9(String send_notifi_r9) {
		this.send_notifi_r9 = send_notifi_r9;
	}

	public String getSend_notifi_r10() {
		return send_notifi_r10;
	}

	public void setSend_notifi_r10(String send_notifi_r10) {
		this.send_notifi_r10 = send_notifi_r10;
	}

	public String getPh_countrycode_r1() {
		return ph_countrycode_r1;
	}

	public void setPh_countrycode_r1(String ph_countrycode_r1) {
		this.ph_countrycode_r1 = ph_countrycode_r1;
	}

	public String getPh_countrycode_r2() {
		return ph_countrycode_r2;
	}

	public void setPh_countrycode_r2(String ph_countrycode_r2) {
		this.ph_countrycode_r2 = ph_countrycode_r2;
	}

	public String getPh_countrycode_r3() {
		return ph_countrycode_r3;
	}

	public void setPh_countrycode_r3(String ph_countrycode_r3) {
		this.ph_countrycode_r3 = ph_countrycode_r3;
	}

	public String getPh_countrycode_r4() {
		return ph_countrycode_r4;
	}

	public void setPh_countrycode_r4(String ph_countrycode_r4) {
		this.ph_countrycode_r4 = ph_countrycode_r4;
	}

	public String getPh_countrycode_r5() {
		return ph_countrycode_r5;
	}

	public void setPh_countrycode_r5(String ph_countrycode_r5) {
		this.ph_countrycode_r5 = ph_countrycode_r5;
	}

	public String getPh_countrycode_r6() {
		return ph_countrycode_r6;
	}

	public void setPh_countrycode_r6(String ph_countrycode_r6) {
		this.ph_countrycode_r6 = ph_countrycode_r6;
	}

	public String getPh_countrycode_r7() {
		return ph_countrycode_r7;
	}

	public void setPh_countrycode_r7(String ph_countrycode_r7) {
		this.ph_countrycode_r7 = ph_countrycode_r7;
	}

	public String getPh_countrycode_r8() {
		return ph_countrycode_r8;
	}

	public void setPh_countrycode_r8(String ph_countrycode_r8) {
		this.ph_countrycode_r8 = ph_countrycode_r8;
	}

	public String getPh_countrycode_r9() {
		return ph_countrycode_r9;
	}

	public void setPh_countrycode_r9(String ph_countrycode_r9) {
		this.ph_countrycode_r9 = ph_countrycode_r9;
	}

	public String getPh_countrycode_r10() {
		return ph_countrycode_r10;
	}

	public void setPh_countrycode_r10(String ph_countrycode_r10) {
		this.ph_countrycode_r10 = ph_countrycode_r10;
	}

	public String getOfc_countrycode_r1() {
		return ofc_countrycode_r1;
	}

	public void setOfc_countrycode_r1(String ofc_countrycode_r1) {
		this.ofc_countrycode_r1 = ofc_countrycode_r1;
	}

	public String getOfc_countrycode_r2() {
		return ofc_countrycode_r2;
	}

	public void setOfc_countrycode_r2(String ofc_countrycode_r2) {
		this.ofc_countrycode_r2 = ofc_countrycode_r2;
	}

	public String getOfc_countrycode_r3() {
		return ofc_countrycode_r3;
	}

	public void setOfc_countrycode_r3(String ofc_countrycode_r3) {
		this.ofc_countrycode_r3 = ofc_countrycode_r3;
	}

	public String getOfc_countrycode_r4() {
		return ofc_countrycode_r4;
	}

	public void setOfc_countrycode_r4(String ofc_countrycode_r4) {
		this.ofc_countrycode_r4 = ofc_countrycode_r4;
	}

	public String getOfc_countrycode_r5() {
		return ofc_countrycode_r5;
	}

	public void setOfc_countrycode_r5(String ofc_countrycode_r5) {
		this.ofc_countrycode_r5 = ofc_countrycode_r5;
	}

	public String getOfc_countrycode_r6() {
		return ofc_countrycode_r6;
	}

	public void setOfc_countrycode_r6(String ofc_countrycode_r6) {
		this.ofc_countrycode_r6 = ofc_countrycode_r6;
	}

	public String getOfc_countrycode_r7() {
		return ofc_countrycode_r7;
	}

	public void setOfc_countrycode_r7(String ofc_countrycode_r7) {
		this.ofc_countrycode_r7 = ofc_countrycode_r7;
	}

	public String getOfc_countrycode_r8() {
		return ofc_countrycode_r8;
	}

	public void setOfc_countrycode_r8(String ofc_countrycode_r8) {
		this.ofc_countrycode_r8 = ofc_countrycode_r8;
	}

	public String getOfc_countrycode_r9() {
		return ofc_countrycode_r9;
	}

	public void setOfc_countrycode_r9(String ofc_countrycode_r9) {
		this.ofc_countrycode_r9 = ofc_countrycode_r9;
	}

	public String getOfc_countrycode_r10() {
		return ofc_countrycode_r10;
	}

	public void setOfc_countrycode_r10(String ofc_countrycode_r10) {
		this.ofc_countrycode_r10 = ofc_countrycode_r10;
	}

	public BigDecimal getChargeback_amount() {
		return chargeback_amount;
	}

	public void setChargeback_amount(BigDecimal chargeback_amount) {
		this.chargeback_amount = chargeback_amount;
	}

	public String getChargeback_approval() {
		return chargeback_approval;
	}

	public void setChargeback_approval(String chargeback_approval) {
		this.chargeback_approval = chargeback_approval;
	}

	public String getMerchant_id() {
		return merchant_id;
	}

	public void setMerchant_id(String merchant_id) {
		this.merchant_id = merchant_id;
	}

	public String getMerchant_pow_ca_no() {
		return merchant_pow_ca_no;
	}

	public void setMerchant_pow_ca_no(String merchant_pow_ca_no) {
		this.merchant_pow_ca_no = merchant_pow_ca_no;
	}

	public String getMerchant_acc_no() {
		return merchant_acc_no;
	}

	public void setMerchant_acc_no(String merchant_acc_no) {
		this.merchant_acc_no = merchant_acc_no;
	}

	public String getMerchant_addr() {
		return merchant_addr;
	}

	public void setMerchant_addr(String merchant_addr) {
		this.merchant_addr = merchant_addr;
	}

	public String getMerchant_legal_id() {
		return merchant_legal_id;
	}

	public void setMerchant_legal_id(String merchant_legal_id) {
		this.merchant_legal_id = merchant_legal_id;
	}

	public String getMerchant_cont_per() {
		return merchant_cont_per;
	}

	public void setMerchant_cont_per(String merchant_cont_per) {
		this.merchant_cont_per = merchant_cont_per;
	}

	public String getMerchant_name() {
		return merchant_name;
	}

	public void setMerchant_name(String merchant_name) {
		this.merchant_name = merchant_name;
	}

	public String getMerchant_cont_details() {
		return merchant_cont_details;
	}

	public void setMerchant_cont_details(String merchant_cont_details) {
		this.merchant_cont_details = merchant_cont_details;
	}

	public String getCompany_name() {
		return company_name;
	}

	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}

	public String getTrading_name() {
		return trading_name;
	}

	public void setTrading_name(String trading_name) {
		this.trading_name = trading_name;
	}

	public String getBill_acc_no() {
		return bill_acc_no;
	}

	public void setBill_acc_no(String bill_acc_no) {
		this.bill_acc_no = bill_acc_no;
	}

	public String getTran_curr() {
		return tran_curr;
	}

	public void setTran_curr(String tran_curr) {
		this.tran_curr = tran_curr;
	}

	public String getTran_amount() {
		return tran_amount;
	}

	public void setTran_amount(String tran_amount) {
		this.tran_amount = tran_amount;
	}

	public String getRemitter() {
		return remitter;
	}

	public void setRemitter(String remitter) {
		this.remitter = remitter;
	}

	public String getRemitter_acc_no() {
		return remitter_acc_no;
	}

	public void setRemitter_acc_no(String remitter_acc_no) {
		this.remitter_acc_no = remitter_acc_no;
	}

	public String getRemitter_ref_no() {
		return remitter_ref_no;
	}

	public void setRemitter_ref_no(String remitter_ref_no) {
		this.remitter_ref_no = remitter_ref_no;
	}

	public String getTran_type() {
		return tran_type;
	}

	public void setTran_type(String tran_type) {
		this.tran_type = tran_type;
	}

	public String getTran_remarks1() {
		return tran_remarks1;
	}

	public void setTran_remarks1(String tran_remarks1) {
		this.tran_remarks1 = tran_remarks1;
	}

	public String getMerchant_corp_name() {
		return merchant_corp_name;
	}

	public void setMerchant_corp_name(String merchant_corp_name) {
		this.merchant_corp_name = merchant_corp_name;
	}

	public String getMerchant_mob_no() {
		return merchant_mob_no;
	}

	public void setMerchant_mob_no(String merchant_mob_no) {
		this.merchant_mob_no = merchant_mob_no;
	}

	public String getMerchant_type() {
		return merchant_type;
	}

	public void setMerchant_type(String merchant_type) {
		this.merchant_type = merchant_type;
	}

	public String getType_of_qr() {
		return type_of_qr;
	}

	public void setType_of_qr(String type_of_qr) {
		this.type_of_qr = type_of_qr;
	}

	public String getMerchant_cat_code() {
		return merchant_cat_code;
	}

	public void setMerchant_cat_code(String merchant_cat_code) {
		this.merchant_cat_code = merchant_cat_code;
	}

	public String getMerchant_fees() {
		return merchant_fees;
	}

	public void setMerchant_fees(String merchant_fees) {
		this.merchant_fees = merchant_fees;
	}

	public String getMerchant_city() {
		return merchant_city;
	}

	public void setMerchant_city(String merchant_city) {
		this.merchant_city = merchant_city;
	}

	public String getMerchant_posting() {
		return merchant_posting;
	}

	public void setMerchant_posting(String merchant_posting) {
		this.merchant_posting = merchant_posting;
	}

	public String getAmount_percentage1() {
		return amount_percentage1;
	}

	public void setAmount_percentage1(String amount_percentage1) {
		this.amount_percentage1 = amount_percentage1;
	}

	public String getPeriodicity1() {
		return periodicity1;
	}

	public void setPeriodicity1(String periodicity1) {
		this.periodicity1 = periodicity1;
	}

	public String getMerchant_terminal() {
		return merchant_terminal;
	}

	public void setMerchant_terminal(String merchant_terminal) {
		this.merchant_terminal = merchant_terminal;
	}

	public String getMerchant_number() {
		return merchant_number;
	}

	public void setMerchant_number(String merchant_number) {
		this.merchant_number = merchant_number;
	}

	public String getOutlet_no() {
		return outlet_no;
	}

	public void setOutlet_no(String outlet_no) {
		this.outlet_no = outlet_no;
	}

	public String getEntry_user() {
		return entry_user;
	}

	public void setEntry_user(String entry_user) {
		this.entry_user = entry_user;
	}

	public Date getEntry_time() {
		return entry_time;
	}

	public void setEntry_time(Date entry_time) {
		this.entry_time = entry_time;
	}

	public Date getModify_time() {
		return modify_time;
	}

	public void setModify_time(Date modify_time) {
		this.modify_time = modify_time;
	}

	public String getAuth_user() {
		return auth_user;
	}

	public void setAuth_user(String auth_user) {
		this.auth_user = auth_user;
	}

	public String getModify_user() {
		return modify_user;
	}

	public void setModify_user(String modify_user) {
		this.modify_user = modify_user;
	}

	public Date getAuth_time() {
		return auth_time;
	}

	public void setAuth_time(Date auth_time) {
		this.auth_time = auth_time;
	}

	public Character getEntity_flg() {
		return entity_flg;
	}

	public void setEntity_flg(Character entity_flg) {
		this.entity_flg = entity_flg;
	}

	public Character getDel_flg() {
		return del_flg;
	}

	public void setDel_flg(Character del_flg) {
		this.del_flg = del_flg;
	}

	public String getFreeze_flg() {
		return freeze_flg;
	}

	public void setFreeze_flg(String freeze_flg) {
		this.freeze_flg = freeze_flg;
	}

	public String getFreeze_type() {
		return freeze_type;
	}

	public void setFreeze_type(String freeze_type) {
		this.freeze_type = freeze_type;
	}

	public String getAdd_details_req() {
		return add_details_req;
	}

	public void setAdd_details_req(String add_details_req) {
		this.add_details_req = add_details_req;
	}

	public String getParitioning_type() {
		return paritioning_type;
	}

	public void setParitioning_type(String paritioning_type) {
		this.paritioning_type = paritioning_type;
	}

	public Character getModify_flg() {
		return modify_flg;
	}

	public void setModify_flg(Character modify_flg) {
		this.modify_flg = modify_flg;
	}

	public String getAmount_percentage2() {
		return amount_percentage2;
	}

	public void setAmount_percentage2(String amount_percentage2) {
		this.amount_percentage2 = amount_percentage2;
	}

	public String getPeriodicity2() {
		return periodicity2;
	}

	public void setPeriodicity2(String periodicity2) {
		this.periodicity2 = periodicity2;
	}

	public String getTran_remarks2() {
		return tran_remarks2;
	}

	public void setTran_remarks2(String tran_remarks2) {
		this.tran_remarks2 = tran_remarks2;
	}

	public String getAmount_percentage3() {
		return amount_percentage3;
	}

	public void setAmount_percentage3(String amount_percentage3) {
		this.amount_percentage3 = amount_percentage3;
	}

	public String getPeriodicity3() {
		return periodicity3;
	}

	public void setPeriodicity3(String periodicity3) {
		this.periodicity3 = periodicity3;
	}

	public String getTran_remarks3() {
		return tran_remarks3;
	}

	public void setTran_remarks3(String tran_remarks3) {
		this.tran_remarks3 = tran_remarks3;
	}

	public String getTran_remarks() {
		return tran_remarks;
	}

	public void setTran_remarks(String tran_remarks) {
		this.tran_remarks = tran_remarks;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getModes() {
		return modes;
	}

	public void setModes(String modes) {
		this.modes = modes;
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public String getOrgid() {
		return orgid;
	}

	public void setOrgid(String orgid) {
		this.orgid = orgid;
	}

	public String getTid() {
		return tid;
	}

	public void setTid(String tid) {
		this.tid = tid;
	}

	public String getTr() {
		return tr;
	}

	public void setTr(String tr) {
		this.tr = tr;
	}

	public String getTn() {
		return tn;
	}

	public void setTn(String tn) {
		this.tn = tn;
	}

	public String getPa() {
		return pa;
	}

	public void setPa(String pa) {
		this.pa = pa;
	}

	public String getMsid() {
		return msid;
	}

	public void setMsid(String msid) {
		this.msid = msid;
	}

	public String getMtid() {
		return mtid;
	}

	public void setMtid(String mtid) {
		this.mtid = mtid;
	}

	public String getBam() {
		return bam;
	}

	public void setBam(String bam) {
		this.bam = bam;
	}

	public String getCurr() {
		return curr;
	}

	public void setCurr(String curr) {
		this.curr = curr;
	}

	public String getInvoiceno() {
		return invoiceno;
	}

	public void setInvoiceno(String invoiceno) {
		this.invoiceno = invoiceno;
	}

	public Date getInvoicedate() {
		return invoicedate;
	}

	public void setInvoicedate(Date invoicedate) {
		this.invoicedate = invoicedate;
	}

	public String getInvoicename() {
		return invoicename;
	}

	public void setInvoicename(String invoicename) {
		this.invoicename = invoicename;
	}

	public Date getQrexpire() {
		return qrexpire;
	}

	public void setQrexpire(Date qrexpire) {
		this.qrexpire = qrexpire;
	}

	public String getAm() {
		return am;
	}

	public void setAm(String am) {
		this.am = am;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public String getTier() {
		return tier;
	}

	public void setTier(String tier) {
		this.tier = tier;
	}

	public String getTxntype() {
		return txntype;
	}

	public void setTxntype(String txntype) {
		this.txntype = txntype;
	}

	public String getQrmedium() {
		return qrmedium;
	}

	public void setQrmedium(String qrmedium) {
		this.qrmedium = qrmedium;
	}

	public String getTranasactio_crncy() {
		return tranasactio_crncy;
	}

	public void setTranasactio_crncy(String tranasactio_crncy) {
		this.tranasactio_crncy = tranasactio_crncy;
	}

	public String getTip_or_conv_indicator() {
		return tip_or_conv_indicator;
	}

	public void setTip_or_conv_indicator(String tip_or_conv_indicator) {
		this.tip_or_conv_indicator = tip_or_conv_indicator;
	}

	public String getConv_fees_type() {
		return conv_fees_type;
	}

	public void setConv_fees_type(String conv_fees_type) {
		this.conv_fees_type = conv_fees_type;
	}

	public String getValue_conv_fees() {
		return value_conv_fees;
	}

	public void setValue_conv_fees(String value_conv_fees) {
		this.value_conv_fees = value_conv_fees;
	}

	public String getZip_code() {
		return zip_code;
	}

	public void setZip_code(String zip_code) {
		this.zip_code = zip_code;
	}

	public String getBill_number() {
		return bill_number;
	}

	public void setBill_number(String bill_number) {
		this.bill_number = bill_number;
	}

	public String getLoyalty_number() {
		return loyalty_number;
	}

	public void setLoyalty_number(String loyalty_number) {
		this.loyalty_number = loyalty_number;
	}

	public String getCustomer_label() {
		return customer_label;
	}

	public void setCustomer_label(String customer_label) {
		this.customer_label = customer_label;
	}

	public String getStore_label() {
		return store_label;
	}

	public void setStore_label(String store_label) {
		this.store_label = store_label;
	}

	public String getTerminal_label() {
		return terminal_label;
	}

	public void setTerminal_label(String terminal_label) {
		this.terminal_label = terminal_label;
	}

	public String getReference_label() {
		return reference_label;
	}

	public void setReference_label(String reference_label) {
		this.reference_label = reference_label;
	}

	public String getPurpose_of_tran() {
		return purpose_of_tran;
	}

	public void setPurpose_of_tran(String purpose_of_tran) {
		this.purpose_of_tran = purpose_of_tran;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMerchant_coun_cod() {
		return merchant_coun_cod;
	}

	public void setMerchant_coun_cod(String merchant_coun_cod) {
		this.merchant_coun_cod = merchant_coun_cod;
	}

	public String getMerchant_brand() {
		return merchant_brand;
	}

	public void setMerchant_brand(String merchant_brand) {
		this.merchant_brand = merchant_brand;
	}

	public String getMerchant_location() {
		return merchant_location;
	}

	public void setMerchant_location(String merchant_location) {
		this.merchant_location = merchant_location;
	}

	public String getMerchant_genre() {
		return merchant_genre;
	}

	public void setMerchant_genre(String merchant_genre) {
		this.merchant_genre = merchant_genre;
	}

	public String getMerchant_onboard() {
		return merchant_onboard;
	}

	public void setMerchant_onboard(String merchant_onboard) {
		this.merchant_onboard = merchant_onboard;
	}

	public String getType_maucas() {
		return type_maucas;
	}

	public void setType_maucas(String type_maucas) {
		this.type_maucas = type_maucas;
	}

	public String getType_upi() {
		return type_upi;
	}

	public void setType_upi(String type_upi) {
		this.type_upi = type_upi;
	}

	public String getTip_or_conv_indicator_upi() {
		return tip_or_conv_indicator_upi;
	}

	public void setTip_or_conv_indicator_upi(String tip_or_conv_indicator_upi) {
		this.tip_or_conv_indicator_upi = tip_or_conv_indicator_upi;
	}

	public String getConv_fees_type_upi() {
		return conv_fees_type_upi;
	}

	public void setConv_fees_type_upi(String conv_fees_type_upi) {
		this.conv_fees_type_upi = conv_fees_type_upi;
	}

	public String getValue_conv_fees_upi() {
		return value_conv_fees_upi;
	}

	public void setValue_conv_fees_upi(String value_conv_fees_upi) {
		this.value_conv_fees_upi = value_conv_fees_upi;
	}

	public String getBank_name() {
		return bank_name;
	}

	public void setBank_name(String bank_name) {
		this.bank_name = bank_name;
	}

	public String getBank_account_no() {
		return bank_account_no;
	}

	public void setBank_account_no(String bank_account_no) {
		this.bank_account_no = bank_account_no;
	}

	public String getBank_branch() {
		return bank_branch;
	}

	public void setBank_branch(String bank_branch) {
		this.bank_branch = bank_branch;
	}

	public String getBank_code() {
		return bank_code;
	}

	public void setBank_code(String bank_code) {
		this.bank_code = bank_code;
	}

	public String getIban() {
		return iban;
	}

	public void setIban(String iban) {
		this.iban = iban;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getMerchant_card_acc_id() {
		return merchant_card_acc_id;
	}

	public void setMerchant_card_acc_id(String merchant_card_acc_id) {
		this.merchant_card_acc_id = merchant_card_acc_id;
	}

	public String getMerchant_mode() {
		return merchant_mode;
	}

	public void setMerchant_mode(String merchant_mode) {
		this.merchant_mode = merchant_mode;
	}

	public String getMerchant_bips_no() {
		return merchant_bips_no;
	}

	public void setMerchant_bips_no(String merchant_bips_no) {
		this.merchant_bips_no = merchant_bips_no;
	}

	public String getMer_contact_per() {
		return mer_contact_per;
	}

	public void setMer_contact_per(String mer_contact_per) {
		this.mer_contact_per = mer_contact_per;
	}

	public String getMer_email_addr() {
		return mer_email_addr;
	}

	public void setMer_email_addr(String mer_email_addr) {
		this.mer_email_addr = mer_email_addr;
	}

	public String getSend_notify() {
		return send_notify;
	}

	public void setSend_notify(String send_notify) {
		this.send_notify = send_notify;
	}

	public String getMer_phone_num() {
		return mer_phone_num;
	}

	public void setMer_phone_num(String mer_phone_num) {
		this.mer_phone_num = mer_phone_num;
	}

	public String getFee_desc() {
		return fee_desc;
	}

	public void setFee_desc(String fee_desc) {
		this.fee_desc = fee_desc;
	}

	public String getFee_type() {
		return fee_type;
	}

	public void setFee_type(String fee_type) {
		this.fee_type = fee_type;
	}

	public String getAmount_per() {
		return amount_per;
	}

	public void setAmount_per(String amount_per) {
		this.amount_per = amount_per;
	}

	public String getFee_freq() {
		return fee_freq;
	}

	public void setFee_freq(String fee_freq) {
		this.fee_freq = fee_freq;
	}

	public String getVat_collect() {
		return vat_collect;
	}

	public void setVat_collect(String vat_collect) {
		this.vat_collect = vat_collect;
	}

	public String getDel_flag() {
		return del_flag;
	}

	public void setDel_flag(String del_flag) {
		this.del_flag = del_flag;
	}

	public String getStatus_enable() {
		return status_enable;
	}

	public void setStatus_enable(String status_enable) {
		this.status_enable = status_enable;
	}

	public String getStatus_disable() {
		return status_disable;
	}

	public void setStatus_disable(String status_disable) {
		this.status_disable = status_disable;
	}

	public String getMerchant_cat_desc() {
		return merchant_cat_desc;
	}

	public void setMerchant_cat_desc(String merchant_cat_desc) {
		this.merchant_cat_desc = merchant_cat_desc;
	}

	public String getNotify_mode() {
		return notify_mode;
	}

	public void setNotify_mode(String notify_mode) {
		this.notify_mode = notify_mode;
	}

	public String getMer_partner() {
		return mer_partner;
	}

	public void setMer_partner(String mer_partner) {
		this.mer_partner = mer_partner;
	}

	public String getMerchant_addr_2() {
		return merchant_addr_2;
	}

	public void setMerchant_addr_2(String merchant_addr_2) {
		this.merchant_addr_2 = merchant_addr_2;
	}

	public String getMerchant_addr_3() {
		return merchant_addr_3;
	}

	public void setMerchant_addr_3(String merchant_addr_3) {
		this.merchant_addr_3 = merchant_addr_3;
	}

	public String getMerchant_office_number() {
		return merchant_office_number;
	}

	public void setMerchant_office_number(String merchant_office_number) {
		this.merchant_office_number = merchant_office_number;
	}

	public String getMerchant_out_addr_2() {
		return merchant_out_addr_2;
	}

	public void setMerchant_out_addr_2(String merchant_out_addr_2) {
		this.merchant_out_addr_2 = merchant_out_addr_2;
	}

	public String getMerchant_out_addr_3() {
		return merchant_out_addr_3;
	}

	public void setMerchant_out_addr_3(String merchant_out_addr_3) {
		this.merchant_out_addr_3 = merchant_out_addr_3;
	}

	public String getMerchant_out_addr_1() {
		return merchant_out_addr_1;
	}

	public void setMerchant_out_addr_1(String merchant_out_addr_1) {
		this.merchant_out_addr_1 = merchant_out_addr_1;
	}

	public String getSettlement_frequency() {
		return settlement_frequency;
	}

	public void setSettlement_frequency(String settlement_frequency) {
		this.settlement_frequency = settlement_frequency;
	}

	public String getSettlement_day() {
		return settlement_day;
	}

	public void setSettlement_day(String settlement_day) {
		this.settlement_day = settlement_day;
	}

	public String getSettlement_date() {
		return settlement_date;
	}

	public void setSettlement_date(String settlement_date) {
		this.settlement_date = settlement_date;
	}

	public Date getNext_settlement_date() {
		return next_settlement_date;
	}

	public void setNext_settlement_date(Date next_settlement_date) {
		this.next_settlement_date = next_settlement_date;
	}

	public String getStatic_field() {
		return static_field;
	}

	public void setStatic_field(String static_field) {
		this.static_field = static_field;
	}

	public String getMer_user_id_r1() {
		return mer_user_id_r1;
	}

	public void setMer_user_id_r1(String mer_user_id_r1) {
		this.mer_user_id_r1 = mer_user_id_r1;
	}

	public String getMer_user_id_r2() {
		return mer_user_id_r2;
	}

	public void setMer_user_id_r2(String mer_user_id_r2) {
		this.mer_user_id_r2 = mer_user_id_r2;
	}

	public String getMer_user_id_r3() {
		return mer_user_id_r3;
	}

	public void setMer_user_id_r3(String mer_user_id_r3) {
		this.mer_user_id_r3 = mer_user_id_r3;
	}

	public String getMer_user_id_r4() {
		return mer_user_id_r4;
	}

	public void setMer_user_id_r4(String mer_user_id_r4) {
		this.mer_user_id_r4 = mer_user_id_r4;
	}

	public String getMer_user_id_r5() {
		return mer_user_id_r5;
	}

	public void setMer_user_id_r5(String mer_user_id_r5) {
		this.mer_user_id_r5 = mer_user_id_r5;
	}

	public String getMer_cont_pers() {
		return mer_cont_pers;
	}

	public void setMer_cont_pers(String mer_cont_pers) {
		this.mer_cont_pers = mer_cont_pers;
	}

	public String getMer_cont_pers_r2() {
		return mer_cont_pers_r2;
	}

	public void setMer_cont_pers_r2(String mer_cont_pers_r2) {
		this.mer_cont_pers_r2 = mer_cont_pers_r2;
	}

	public String getMer_cont_pers_r3() {
		return mer_cont_pers_r3;
	}

	public void setMer_cont_pers_r3(String mer_cont_pers_r3) {
		this.mer_cont_pers_r3 = mer_cont_pers_r3;
	}

	public String getMer_cont_pers_r4() {
		return mer_cont_pers_r4;
	}

	public void setMer_cont_pers_r4(String mer_cont_pers_r4) {
		this.mer_cont_pers_r4 = mer_cont_pers_r4;
	}

	public String getMer_cont_pers_r5() {
		return mer_cont_pers_r5;
	}

	public void setMer_cont_pers_r5(String mer_cont_pers_r5) {
		this.mer_cont_pers_r5 = mer_cont_pers_r5;
	}

	public BigDecimal getMer_ph_no() {
		return mer_ph_no;
	}

	public void setMer_ph_no(BigDecimal mer_ph_no) {
		this.mer_ph_no = mer_ph_no;
	}

	public BigDecimal getMer_ph_no_r2() {
		return mer_ph_no_r2;
	}

	public void setMer_ph_no_r2(BigDecimal mer_ph_no_r2) {
		this.mer_ph_no_r2 = mer_ph_no_r2;
	}

	public BigDecimal getMer_ph_no_r3() {
		return mer_ph_no_r3;
	}

	public void setMer_ph_no_r3(BigDecimal mer_ph_no_r3) {
		this.mer_ph_no_r3 = mer_ph_no_r3;
	}

	public BigDecimal getMer_ph_no_r4() {
		return mer_ph_no_r4;
	}

	public void setMer_ph_no_r4(BigDecimal mer_ph_no_r4) {
		this.mer_ph_no_r4 = mer_ph_no_r4;
	}

	public BigDecimal getMer_ph_no_r5() {
		return mer_ph_no_r5;
	}

	public void setMer_ph_no_r5(BigDecimal mer_ph_no_r5) {
		this.mer_ph_no_r5 = mer_ph_no_r5;
	}

	public BigDecimal getMer_ofc_no() {
		return mer_ofc_no;
	}

	public void setMer_ofc_no(BigDecimal mer_ofc_no) {
		this.mer_ofc_no = mer_ofc_no;
	}

	public BigDecimal getMer_ofc_no_r2() {
		return mer_ofc_no_r2;
	}

	public void setMer_ofc_no_r2(BigDecimal mer_ofc_no_r2) {
		this.mer_ofc_no_r2 = mer_ofc_no_r2;
	}

	public BigDecimal getMer_ofc_no_r3() {
		return mer_ofc_no_r3;
	}

	public void setMer_ofc_no_r3(BigDecimal mer_ofc_no_r3) {
		this.mer_ofc_no_r3 = mer_ofc_no_r3;
	}

	public BigDecimal getMer_ofc_no_r4() {
		return mer_ofc_no_r4;
	}

	public void setMer_ofc_no_r4(BigDecimal mer_ofc_no_r4) {
		this.mer_ofc_no_r4 = mer_ofc_no_r4;
	}

	public BigDecimal getMer_ofc_no_r5() {
		return mer_ofc_no_r5;
	}

	public void setMer_ofc_no_r5(BigDecimal mer_ofc_no_r5) {
		this.mer_ofc_no_r5 = mer_ofc_no_r5;
	}

	public String getMer_email_addr_r1() {
		return mer_email_addr_r1;
	}

	public void setMer_email_addr_r1(String mer_email_addr_r1) {
		this.mer_email_addr_r1 = mer_email_addr_r1;
	}

	public String getMer_email_addr_r2() {
		return mer_email_addr_r2;
	}

	public void setMer_email_addr_r2(String mer_email_addr_r2) {
		this.mer_email_addr_r2 = mer_email_addr_r2;
	}

	public String getMer_email_addr_r3() {
		return mer_email_addr_r3;
	}

	public void setMer_email_addr_r3(String mer_email_addr_r3) {
		this.mer_email_addr_r3 = mer_email_addr_r3;
	}

	public String getMer_email_addr_r4() {
		return mer_email_addr_r4;
	}

	public void setMer_email_addr_r4(String mer_email_addr_r4) {
		this.mer_email_addr_r4 = mer_email_addr_r4;
	}

	public String getMer_email_addr_r5() {
		return mer_email_addr_r5;
	}

	public void setMer_email_addr_r5(String mer_email_addr_r5) {
		this.mer_email_addr_r5 = mer_email_addr_r5;
	}

	public String getMer_notifi_mode() {
		return mer_notifi_mode;
	}

	public void setMer_notifi_mode(String mer_notifi_mode) {
		this.mer_notifi_mode = mer_notifi_mode;
	}

	public String getMer_notifi_mode_r2() {
		return mer_notifi_mode_r2;
	}

	public void setMer_notifi_mode_r2(String mer_notifi_mode_r2) {
		this.mer_notifi_mode_r2 = mer_notifi_mode_r2;
	}

	public String getMer_notifi_mode_r3() {
		return mer_notifi_mode_r3;
	}

	public void setMer_notifi_mode_r3(String mer_notifi_mode_r3) {
		this.mer_notifi_mode_r3 = mer_notifi_mode_r3;
	}

	public String getMer_notifi_mode_r4() {
		return mer_notifi_mode_r4;
	}

	public void setMer_notifi_mode_r4(String mer_notifi_mode_r4) {
		this.mer_notifi_mode_r4 = mer_notifi_mode_r4;
	}

	public String getMer_notifi_mode_r5() {
		return mer_notifi_mode_r5;
	}

	public void setMer_notifi_mode_r5(String mer_notifi_mode_r5) {
		this.mer_notifi_mode_r5 = mer_notifi_mode_r5;
	}

	public String getSend_notifi() {
		return send_notifi;
	}

	public void setSend_notifi(String send_notifi) {
		this.send_notifi = send_notifi;
	}

	public String getSend_notifi_r2() {
		return send_notifi_r2;
	}

	public void setSend_notifi_r2(String send_notifi_r2) {
		this.send_notifi_r2 = send_notifi_r2;
	}

	public String getSend_notifi_r3() {
		return send_notifi_r3;
	}

	public void setSend_notifi_r3(String send_notifi_r3) {
		this.send_notifi_r3 = send_notifi_r3;
	}

	public String getSend_notifi_r4() {
		return send_notifi_r4;
	}

	public void setSend_notifi_r4(String send_notifi_r4) {
		this.send_notifi_r4 = send_notifi_r4;
	}

	public String getSend_notifi_r5() {
		return send_notifi_r5;
	}

	public void setSend_notifi_r5(String send_notifi_r5) {
		this.send_notifi_r5 = send_notifi_r5;
	}

	public String getBank_name2() {
		return bank_name2;
	}

	public void setBank_name2(String bank_name2) {
		this.bank_name2 = bank_name2;
	}

	public String getBank_account_no2() {
		return bank_account_no2;
	}

	public void setBank_account_no2(String bank_account_no2) {
		this.bank_account_no2 = bank_account_no2;
	}

	public String getBank_branch2() {
		return bank_branch2;
	}

	public void setBank_branch2(String bank_branch2) {
		this.bank_branch2 = bank_branch2;
	}

	public String getBank_code2() {
		return bank_code2;
	}

	public void setBank_code2(String bank_code2) {
		this.bank_code2 = bank_code2;
	}

	public String getIban2() {
		return iban2;
	}

	public void setIban2(String iban2) {
		this.iban2 = iban2;
	}

	public String getCurrency2() {
		return currency2;
	}

	public void setCurrency2(String currency2) {
		this.currency2 = currency2;
	}

	public String getSettlement_frequency2() {
		return settlement_frequency2;
	}

	public void setSettlement_frequency2(String settlement_frequency2) {
		this.settlement_frequency2 = settlement_frequency2;
	}

	public String getSettlement_day2() {
		return settlement_day2;
	}

	public void setSettlement_day2(String settlement_day2) {
		this.settlement_day2 = settlement_day2;
	}

	public String getSettlement_date2() {
		return settlement_date2;
	}

	public void setSettlement_date2(String settlement_date2) {
		this.settlement_date2 = settlement_date2;
	}
 
 
	public MerchantMaster(String merchant_id, String merchant_pow_ca_no, String merchant_acc_no, String merchant_addr,
			String merchant_legal_id, String merchant_cont_per, String merchant_name, String merchant_cont_details,
			String company_name, String trading_name, String bill_acc_no, String tran_curr, String tran_amount,
			String remitter, String remitter_acc_no, String remitter_ref_no, String tran_type, String tran_remarks1,
			String merchant_corp_name, String merchant_mob_no, String merchant_type, String type_of_qr,
			String merchant_cat_code, String merchant_fees, String merchant_city, String merchant_posting,
			String amount_percentage1, String periodicity1, String merchant_terminal, String merchant_number,
			String outlet_no, String entry_user, Date entry_time, Date modify_time, String auth_user,
			String modify_user, Date auth_time, Character entity_flg, Character del_flg, String freeze_flg,
			String freeze_type, String add_details_req, String paritioning_type, Character modify_flg,
			String amount_percentage2, String periodicity2, String tran_remarks2, String amount_percentage3,
			String periodicity3, String tran_remarks3, String tran_remarks, String version, String modes,
			String purpose, String orgid, String tid, String tr, String tn, String pa, String msid, String mtid,
			String bam, String curr, String invoiceno, Date invoicedate, String invoicename, Date qrexpire, String am,
			String pincode, String tier, String txntype, String qrmedium, String tranasactio_crncy,
			String tip_or_conv_indicator, String conv_fees_type, String value_conv_fees, String zip_code,
			String bill_number, String loyalty_number, String customer_label, String store_label, String terminal_label,
			String reference_label, String purpose_of_tran, String status, String merchant_coun_cod,
			String merchant_brand, String merchant_location, String merchant_genre, String merchant_onboard,
			String type_maucas, String type_upi, String tip_or_conv_indicator_upi, String conv_fees_type_upi,
			String value_conv_fees_upi, String bank_name, String bank_account_no, String bank_branch, String bank_code,
			String iban, String currency, String merchant_card_acc_id, String merchant_mode, String merchant_bips_no,
			String mer_contact_per, String mer_email_addr, String send_notify, String mer_phone_num, String fee_desc,
			String fee_type, String amount_per, String fee_freq, String vat_collect, String del_flag,
			String status_enable, String status_disable, String merchant_cat_desc, String notify_mode,
			String mer_partner, String merchant_addr_2, String merchant_addr_3, String merchant_office_number,
			String merchant_out_addr_2, String merchant_out_addr_3, String merchant_out_addr_1,
			String settlement_frequency, String settlement_day, String settlement_date, Date next_settlement_date,
			String static_field, String mer_user_id_r1, String mer_user_id_r2, String mer_user_id_r3,
			String mer_user_id_r4, String mer_user_id_r5, String mer_user_id_r6, String mer_user_id_r7,
			String mer_user_id_r8, String mer_user_id_r9, String mer_user_id_r10, String mer_cont_pers,
			String mer_cont_pers_r2, String mer_cont_pers_r3, String mer_cont_pers_r4, String mer_cont_pers_r5,
			String mer_cont_pers_r6, String mer_cont_pers_r7, String mer_cont_pers_r8, String mer_cont_pers_r9,
			String mer_cont_pers_r10, BigDecimal mer_ph_no, BigDecimal mer_ph_no_r2, BigDecimal mer_ph_no_r3,
			BigDecimal mer_ph_no_r4, BigDecimal mer_ph_no_r5, BigDecimal mer_ph_no_r6, BigDecimal mer_ph_no_r7,
			BigDecimal mer_ph_no_r8, BigDecimal mer_ph_no_r9, BigDecimal mer_ph_no_r10, BigDecimal mer_ofc_no,
			BigDecimal mer_ofc_no_r2, BigDecimal mer_ofc_no_r3, BigDecimal mer_ofc_no_r4, BigDecimal mer_ofc_no_r5,
			BigDecimal mer_ofc_no_r6, BigDecimal mer_ofc_no_r7, BigDecimal mer_ofc_no_r8, BigDecimal mer_ofc_no_r9,
			BigDecimal mer_ofc_no_r10, String mer_email_addr_r1, String mer_email_addr_r2, String mer_email_addr_r3,
			String mer_email_addr_r4, String mer_email_addr_r5, String mer_email_addr_r6, String mer_email_addr_r7,
			String mer_email_addr_r8, String mer_email_addr_r9, String mer_email_addr_r10, String mer_notifi_mode,
			String mer_notifi_mode_r2, String mer_notifi_mode_r3, String mer_notifi_mode_r4, String mer_notifi_mode_r5,
			String mer_notifi_mode_r6, String mer_notifi_mode_r7, String mer_notifi_mode_r8, String mer_notifi_mode_r9,
			String mer_notifi_mode_r10, String send_notifi, String send_notifi_r2, String send_notifi_r3,
			String send_notifi_r4, String send_notifi_r5, String send_notifi_r6, String send_notifi_r7,
			String send_notifi_r8, String send_notifi_r9, String send_notifi_r10, String ph_countrycode_r1,
			String ph_countrycode_r2, String ph_countrycode_r3, String ph_countrycode_r4, String ph_countrycode_r5,
			String ph_countrycode_r6, String ph_countrycode_r7, String ph_countrycode_r8, String ph_countrycode_r9,
			String ph_countrycode_r10, String ofc_countrycode_r1, String ofc_countrycode_r2, String ofc_countrycode_r3,
			String ofc_countrycode_r4, String ofc_countrycode_r5, String ofc_countrycode_r6, String ofc_countrycode_r7,
			String ofc_countrycode_r8, String ofc_countrycode_r9, String ofc_countrycode_r10, String bank_name2,
			String bank_account_no2, String bank_branch2, String bank_code2, String iban2, String currency2,
			String settlement_frequency2, String settlement_day2, String settlement_date2, BigDecimal chargeback_amount,
			String chargeback_approval, byte[] photo, String transaction_amount,String detailed_address1,String detailed_address2) {
		super();
		this.detailed_address1=detailed_address1;
		this.detailed_address2=detailed_address2;
		this.merchant_id = merchant_id;
		this.merchant_pow_ca_no = merchant_pow_ca_no;
		this.merchant_acc_no = merchant_acc_no;
		this.merchant_addr = merchant_addr;
		this.merchant_legal_id = merchant_legal_id;
		this.merchant_cont_per = merchant_cont_per;
		this.merchant_name = merchant_name;
		this.merchant_cont_details = merchant_cont_details;
		this.company_name = company_name;
		this.trading_name = trading_name;
		this.bill_acc_no = bill_acc_no;
		this.tran_curr = tran_curr;
		this.tran_amount = tran_amount;
		this.remitter = remitter;
		this.remitter_acc_no = remitter_acc_no;
		this.remitter_ref_no = remitter_ref_no;
		this.tran_type = tran_type;
		this.tran_remarks1 = tran_remarks1;
		this.merchant_corp_name = merchant_corp_name;
		this.merchant_mob_no = merchant_mob_no;
		this.merchant_type = merchant_type;
		this.type_of_qr = type_of_qr;
		this.merchant_cat_code = merchant_cat_code;
		this.merchant_fees = merchant_fees;
		this.merchant_city = merchant_city;
		this.merchant_posting = merchant_posting;
		this.amount_percentage1 = amount_percentage1;
		this.periodicity1 = periodicity1;
		this.merchant_terminal = merchant_terminal;
		this.merchant_number = merchant_number;
		this.outlet_no = outlet_no;
		this.entry_user = entry_user;
		this.entry_time = entry_time;
		this.modify_time = modify_time;
		this.auth_user = auth_user;
		this.modify_user = modify_user;
		this.auth_time = auth_time;
		this.entity_flg = entity_flg;
		this.del_flg = del_flg;
		this.freeze_flg = freeze_flg;
		this.freeze_type = freeze_type;
		this.add_details_req = add_details_req;
		this.paritioning_type = paritioning_type;
		this.modify_flg = modify_flg;
		this.amount_percentage2 = amount_percentage2;
		this.periodicity2 = periodicity2;
		this.tran_remarks2 = tran_remarks2;
		this.amount_percentage3 = amount_percentage3;
		this.periodicity3 = periodicity3;
		this.tran_remarks3 = tran_remarks3;
		this.tran_remarks = tran_remarks;
		this.version = version;
		this.modes = modes;
		this.purpose = purpose;
		this.orgid = orgid;
		this.tid = tid;
		this.tr = tr;
		this.tn = tn;
		this.pa = pa;
		this.msid = msid;
		this.mtid = mtid;
		this.bam = bam;
		this.curr = curr;
		this.invoiceno = invoiceno;
		this.invoicedate = invoicedate;
		this.invoicename = invoicename;
		this.qrexpire = qrexpire;
		this.am = am;
		this.pincode = pincode;
		this.tier = tier;
		this.txntype = txntype;
		this.qrmedium = qrmedium;
		this.tranasactio_crncy = tranasactio_crncy;
		this.tip_or_conv_indicator = tip_or_conv_indicator;
		this.conv_fees_type = conv_fees_type;
		this.value_conv_fees = value_conv_fees;
		this.zip_code = zip_code;
		this.bill_number = bill_number;
		this.loyalty_number = loyalty_number;
		this.customer_label = customer_label;
		this.store_label = store_label;
		this.terminal_label = terminal_label;
		this.reference_label = reference_label;
		this.purpose_of_tran = purpose_of_tran;
		this.status = status;
		this.merchant_coun_cod = merchant_coun_cod;
		this.merchant_brand = merchant_brand;
		this.merchant_location = merchant_location;
		this.merchant_genre = merchant_genre;
		this.merchant_onboard = merchant_onboard;
		this.type_maucas = type_maucas;
		this.type_upi = type_upi;
		this.tip_or_conv_indicator_upi = tip_or_conv_indicator_upi;
		this.conv_fees_type_upi = conv_fees_type_upi;
		this.value_conv_fees_upi = value_conv_fees_upi;
		this.bank_name = bank_name;
		this.bank_account_no = bank_account_no;
		this.bank_branch = bank_branch;
		this.bank_code = bank_code;
		this.iban = iban;
		this.currency = currency;
		this.merchant_card_acc_id = merchant_card_acc_id;
		this.merchant_mode = merchant_mode;
		this.merchant_bips_no = merchant_bips_no;
		this.mer_contact_per = mer_contact_per;
		this.mer_email_addr = mer_email_addr;
		this.send_notify = send_notify;
		this.mer_phone_num = mer_phone_num;
		this.fee_desc = fee_desc;
		this.fee_type = fee_type;
		this.amount_per = amount_per;
		this.fee_freq = fee_freq;
		this.vat_collect = vat_collect;
		this.del_flag = del_flag;
		this.status_enable = status_enable;
		this.status_disable = status_disable;
		this.merchant_cat_desc = merchant_cat_desc;
		this.notify_mode = notify_mode;
		this.mer_partner = mer_partner;
		this.merchant_addr_2 = merchant_addr_2;
		this.merchant_addr_3 = merchant_addr_3;
		this.merchant_office_number = merchant_office_number;
		this.merchant_out_addr_2 = merchant_out_addr_2;
		this.merchant_out_addr_3 = merchant_out_addr_3;
		this.merchant_out_addr_1 = merchant_out_addr_1;
		this.settlement_frequency = settlement_frequency;
		this.settlement_day = settlement_day;
		this.settlement_date = settlement_date;
		this.next_settlement_date = next_settlement_date;
		this.static_field = static_field;
		this.mer_user_id_r1 = mer_user_id_r1;
		this.mer_user_id_r2 = mer_user_id_r2;
		this.mer_user_id_r3 = mer_user_id_r3;
		this.mer_user_id_r4 = mer_user_id_r4;
		this.mer_user_id_r5 = mer_user_id_r5;
		this.mer_user_id_r6 = mer_user_id_r6;
		this.mer_user_id_r7 = mer_user_id_r7;
		this.mer_user_id_r8 = mer_user_id_r8;
		this.mer_user_id_r9 = mer_user_id_r9;
		this.mer_user_id_r10 = mer_user_id_r10;
		this.mer_cont_pers = mer_cont_pers;
		this.mer_cont_pers_r2 = mer_cont_pers_r2;
		this.mer_cont_pers_r3 = mer_cont_pers_r3;
		this.mer_cont_pers_r4 = mer_cont_pers_r4;
		this.mer_cont_pers_r5 = mer_cont_pers_r5;
		this.mer_cont_pers_r6 = mer_cont_pers_r6;
		this.mer_cont_pers_r7 = mer_cont_pers_r7;
		this.mer_cont_pers_r8 = mer_cont_pers_r8;
		this.mer_cont_pers_r9 = mer_cont_pers_r9;
		this.mer_cont_pers_r10 = mer_cont_pers_r10;
		this.mer_ph_no = mer_ph_no;
		this.mer_ph_no_r2 = mer_ph_no_r2;
		this.mer_ph_no_r3 = mer_ph_no_r3;
		this.mer_ph_no_r4 = mer_ph_no_r4;
		this.mer_ph_no_r5 = mer_ph_no_r5;
		this.mer_ph_no_r6 = mer_ph_no_r6;
		this.mer_ph_no_r7 = mer_ph_no_r7;
		this.mer_ph_no_r8 = mer_ph_no_r8;
		this.mer_ph_no_r9 = mer_ph_no_r9;
		this.mer_ph_no_r10 = mer_ph_no_r10;
		this.mer_ofc_no = mer_ofc_no;
		this.mer_ofc_no_r2 = mer_ofc_no_r2;
		this.mer_ofc_no_r3 = mer_ofc_no_r3;
		this.mer_ofc_no_r4 = mer_ofc_no_r4;
		this.mer_ofc_no_r5 = mer_ofc_no_r5;
		this.mer_ofc_no_r6 = mer_ofc_no_r6;
		this.mer_ofc_no_r7 = mer_ofc_no_r7;
		this.mer_ofc_no_r8 = mer_ofc_no_r8;
		this.mer_ofc_no_r9 = mer_ofc_no_r9;
		this.mer_ofc_no_r10 = mer_ofc_no_r10;
		this.mer_email_addr_r1 = mer_email_addr_r1;
		this.mer_email_addr_r2 = mer_email_addr_r2;
		this.mer_email_addr_r3 = mer_email_addr_r3;
		this.mer_email_addr_r4 = mer_email_addr_r4;
		this.mer_email_addr_r5 = mer_email_addr_r5;
		this.mer_email_addr_r6 = mer_email_addr_r6;
		this.mer_email_addr_r7 = mer_email_addr_r7;
		this.mer_email_addr_r8 = mer_email_addr_r8;
		this.mer_email_addr_r9 = mer_email_addr_r9;
		this.mer_email_addr_r10 = mer_email_addr_r10;
		this.mer_notifi_mode = mer_notifi_mode;
		this.mer_notifi_mode_r2 = mer_notifi_mode_r2;
		this.mer_notifi_mode_r3 = mer_notifi_mode_r3;
		this.mer_notifi_mode_r4 = mer_notifi_mode_r4;
		this.mer_notifi_mode_r5 = mer_notifi_mode_r5;
		this.mer_notifi_mode_r6 = mer_notifi_mode_r6;
		this.mer_notifi_mode_r7 = mer_notifi_mode_r7;
		this.mer_notifi_mode_r8 = mer_notifi_mode_r8;
		this.mer_notifi_mode_r9 = mer_notifi_mode_r9;
		this.mer_notifi_mode_r10 = mer_notifi_mode_r10;
		this.send_notifi = send_notifi;
		this.send_notifi_r2 = send_notifi_r2;
		this.send_notifi_r3 = send_notifi_r3;
		this.send_notifi_r4 = send_notifi_r4;
		this.send_notifi_r5 = send_notifi_r5;
		this.send_notifi_r6 = send_notifi_r6;
		this.send_notifi_r7 = send_notifi_r7;
		this.send_notifi_r8 = send_notifi_r8;
		this.send_notifi_r9 = send_notifi_r9;
		this.send_notifi_r10 = send_notifi_r10;
		this.ph_countrycode_r1 = ph_countrycode_r1;
		this.ph_countrycode_r2 = ph_countrycode_r2;
		this.ph_countrycode_r3 = ph_countrycode_r3;
		this.ph_countrycode_r4 = ph_countrycode_r4;
		this.ph_countrycode_r5 = ph_countrycode_r5;
		this.ph_countrycode_r6 = ph_countrycode_r6;
		this.ph_countrycode_r7 = ph_countrycode_r7;
		this.ph_countrycode_r8 = ph_countrycode_r8;
		this.ph_countrycode_r9 = ph_countrycode_r9;
		this.ph_countrycode_r10 = ph_countrycode_r10;
		this.ofc_countrycode_r1 = ofc_countrycode_r1;
		this.ofc_countrycode_r2 = ofc_countrycode_r2;
		this.ofc_countrycode_r3 = ofc_countrycode_r3;
		this.ofc_countrycode_r4 = ofc_countrycode_r4;
		this.ofc_countrycode_r5 = ofc_countrycode_r5;
		this.ofc_countrycode_r6 = ofc_countrycode_r6;
		this.ofc_countrycode_r7 = ofc_countrycode_r7;
		this.ofc_countrycode_r8 = ofc_countrycode_r8;
		this.ofc_countrycode_r9 = ofc_countrycode_r9;
		this.ofc_countrycode_r10 = ofc_countrycode_r10;
		this.bank_name2 = bank_name2;
		this.bank_account_no2 = bank_account_no2;
		this.bank_branch2 = bank_branch2;
		this.bank_code2 = bank_code2;
		this.iban2 = iban2;
		this.currency2 = currency2;
		this.settlement_frequency2 = settlement_frequency2;
		this.settlement_day2 = settlement_day2;
		this.settlement_date2 = settlement_date2;
		this.chargeback_amount = chargeback_amount;
		this.chargeback_approval = chargeback_approval;
		this.photo = photo;
		this.transaction_amount = transaction_amount;
	}

	public MerchantMaster() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MerchantMaster(MerchantMasterMod user) {
		this.detailed_address1=user.getDetailed_address1();
		this.detailed_address2=user.getDetailed_address2();
		this.merchant_id = user.getMerchant_id();
		this.merchant_pow_ca_no = user.getMerchant_pow_ca_no();
		this.merchant_acc_no = user.getMerchant_acc_no();
		this.merchant_addr = user.getMerchant_addr();
		this.merchant_legal_id = user.getMerchant_legal_id();
		this.merchant_cont_per = user.getMerchant_cont_per();
		this.merchant_name = user.getMerchant_name();
		this.merchant_cont_details = user.getMerchant_cont_details();
		this.company_name = user.getCompany_name();
		this.trading_name = user.getTrading_name();
		this.bill_acc_no = user.getBill_acc_no();
		this.tran_curr = user.getTran_curr();
		this.tran_amount = user.getTran_amount();
		this.remitter = user.getRemitter();
		this.remitter_acc_no = user.getRemitter_acc_no();
		this.remitter_ref_no = user.getRemitter_ref_no();
		this.tran_type = user.getTran_type();
		this.tran_remarks1 = user.getTran_remarks1();
		this.merchant_corp_name = user.getMerchant_corp_name();
		this.merchant_mob_no = user.getMerchant_mob_no();
		this.merchant_type = user.getMerchant_type();
		this.type_of_qr = user.getType_of_qr();
		this.merchant_cat_code = user.getMerchant_cat_code();
		this.merchant_fees = user.getMerchant_fees();
		this.merchant_city = user.getMerchant_city();
		this.merchant_posting = user.getMerchant_posting();
		this.amount_percentage1 = user.getAmount_percentage1();
		this.periodicity1 = user.getPeriodicity1();
		this.merchant_terminal = user.getMerchant_terminal();
		this.merchant_number = user.getMerchant_number();
		this.outlet_no = user.getOutlet_no();
		this.entry_user = user.getEntry_user();
		this.entry_time = user.getEntry_time();
		this.modify_time = user.getModify_time();
		this.auth_user = user.getAuth_user();
		this.modify_user = user.getModify_user();
		this.auth_time = user.getAuth_time();
		this.entity_flg = user.getEntity_flg();
		this.del_flg = user.getDel_flg();
		this.freeze_flg = user.getFreeze_flg();
		this.freeze_type = user.getFreeze_type();
		this.add_details_req = user.getAdd_details_req();
		this.paritioning_type = user.getParitioning_type();
		this.modify_flg = user.getModify_flg();
		this.amount_percentage2 = user.getAmount_percentage2();
		this.periodicity2 = user.getPeriodicity2();
		this.tran_remarks2 = user.getTran_remarks2();
		this.amount_percentage3 = user.getAmount_percentage3();
		this.periodicity3 = user.getPeriodicity3();
		this.tran_remarks3 = user.getTran_remarks3();
		this.tran_remarks = user.getTran_remarks();
		this.version = user.getVersion();
		this.modes = user.getModes();
		this.purpose = user.getPurpose();
		this.orgid = user.getOrgid();
		this.tid = user.getTid();
		this.tr = user.getTr();
		this.tn = user.getTn();
		this.pa = user.getPa();
		this.msid = user.getMsid();
		this.mtid = user.getMtid();
		this.bam = user.getBam();
		this.curr = user.getCurr();
		this.invoiceno = user.getInvoiceno();
		this.invoicedate = user.getInvoicedate();
		this.invoicename = user.getInvoicename();
		this.qrexpire = user.getQrexpire();
		this.am = user.getAm();
		this.pincode = user.getPincode();
		this.tier = user.getTier();
		this.txntype = user.getTxntype();
		this.qrmedium = user.getQrmedium();
		this.tranasactio_crncy = user.getTranasactio_crncy();
		this.tip_or_conv_indicator = user.getTip_or_conv_indicator();
		this.conv_fees_type = user.getConv_fees_type();
		this.value_conv_fees = user.getValue_conv_fees();
		this.zip_code = user.getZip_code();
		this.bill_number = user.getBill_number();
		this.loyalty_number = user.getLoyalty_number();
		this.customer_label = user.getCustomer_label();
		this.store_label = user.getStore_label();
		this.terminal_label = user.getTerminal_label();
		this.reference_label = user.getReference_label();
		this.purpose_of_tran = user.getPurpose_of_tran();
		this.status = user.getStatus();
		this.merchant_coun_cod = user.getMerchant_coun_cod();
		this.merchant_brand = user.getMerchant_brand();
		this.merchant_location = user.getMerchant_location();
		this.merchant_genre = user.getMerchant_genre();
		this.merchant_onboard = user.getMerchant_onboard();
		this.type_maucas = user.getType_maucas();
		this.type_upi = user.getType_upi();
		this.tip_or_conv_indicator_upi = user.getTip_or_conv_indicator_upi();
		this.conv_fees_type_upi = user.getConv_fees_type_upi();
		this.value_conv_fees_upi = user.getValue_conv_fees_upi();
		this.bank_name = user.getBank_name();
		this.bank_account_no = user.getBank_account_no();
		this.bank_branch = user.getBank_branch();
		this.bank_code = user.getBank_code();
		this.iban = user.getIban();
		this.currency = user.getCurrency();
		this.merchant_card_acc_id = user.getMerchant_card_acc_id();
		this.merchant_mode = user.getMerchant_mode();
		this.merchant_bips_no = user.getMerchant_bips_no();
		this.mer_contact_per = user.getMer_contact_per();
		this.mer_email_addr = user.getMer_email_addr();
		this.send_notify = user.getSend_notify();
		this.mer_phone_num = user.getMer_phone_num();
		this.fee_desc = user.getFee_desc();
		this.fee_type = user.getFee_type();
		this.amount_per = user.getAmount_per();
		this.fee_freq = user.getFee_freq();
		this.vat_collect = user.getVat_collect();
		this.del_flag = user.getDel_flag();
		this.status_enable = user.getStatus_enable();
		this.status_disable = user.getStatus_disable();
		this.merchant_cat_desc = user.getMerchant_cat_desc();
		this.notify_mode = user.getNotify_mode();
		this.mer_partner = user.getMer_partner();
		this.merchant_addr_2 = user.getMerchant_addr_2();
		this.merchant_addr_3 = user.getMerchant_addr_3();
		this.merchant_office_number = user.getMerchant_office_number();
		this.merchant_out_addr_2 = user.getMerchant_out_addr_2();
		this.merchant_out_addr_3 = user.getMerchant_out_addr_3();
		this.merchant_out_addr_1=user.getMerchant_out_addr_1();
		this.settlement_frequency =user.getSettlement_frequency();
		this.settlement_day= user.getSettlement_day();
		this.settlement_date=user.getSettlement_date();
		this.next_settlement_date=user.getNext_settlement_date();
		this.static_field=user.getStatic_field();
		this.mer_user_id_r1 = user.getMer_user_id_r1();
		this.mer_user_id_r2 = user.getMer_user_id_r2();
		this.mer_user_id_r3 = user.getMer_user_id_r3();
		this.mer_user_id_r4 = user.getMer_user_id_r4();
		this.mer_user_id_r5 = user.getMer_user_id_r5();
		this.mer_user_id_r6 = user.getMer_user_id_r6();
		this.mer_user_id_r7 = user.getMer_user_id_r7();
		this.mer_user_id_r8 = user.getMer_user_id_r8();
		this.mer_user_id_r9 = user.getMer_user_id_r9();
		this.mer_user_id_r10 = user.getMer_user_id_r10();
		this.mer_cont_pers = user.getMer_cont_pers();
		this.mer_cont_pers_r2 = user.getMer_cont_pers_r2();
		this.mer_cont_pers_r3 = user.getMer_cont_pers_r3();
		this.mer_cont_pers_r4 = user.getMer_cont_pers_r4();
		this.mer_cont_pers_r5 = user.getMer_cont_pers_r5();
		this.mer_cont_pers_r6 = user.getMer_cont_pers_r6();
		this.mer_cont_pers_r7 = user.getMer_cont_pers_r7();
		this.mer_cont_pers_r8 = user.getMer_cont_pers_r8();
		this.mer_cont_pers_r9 = user.getMer_cont_pers_r9();
		this.mer_cont_pers_r10 = user.getMer_cont_pers_r10();
		this.mer_ph_no = user.getMer_ph_no();
		this.mer_ph_no_r2 = user.getMer_ph_no_r2();
		this.mer_ph_no_r3 = user.getMer_ph_no_r3();
		this.mer_ph_no_r4 = user.getMer_ph_no_r4();
		this.mer_ph_no_r5 = user.getMer_ph_no_r5();
		this.mer_ph_no_r6 = user.getMer_ph_no_r6();
		this.mer_ph_no_r7 = user.getMer_ph_no_r7();
		this.mer_ph_no_r8 = user.getMer_ph_no_r8();
		this.mer_ph_no_r9 = user.getMer_ph_no_r9();
		this.mer_ph_no_r10 = user.getMer_ph_no_r10();
		this.mer_ofc_no = user.getMer_ofc_no();
		this.mer_ofc_no_r2 = user.getMer_ofc_no_r2();
		this.mer_ofc_no_r3 = user.getMer_ofc_no_r3();
		this.mer_ofc_no_r4 = user.getMer_ofc_no_r4();
		this.mer_ofc_no_r5 = user.getMer_ofc_no_r5();
		this.mer_ofc_no_r6 = user.getMer_ofc_no_r6();
		this.mer_ofc_no_r7 = user.getMer_ofc_no_r7();
		this.mer_ofc_no_r8 = user.getMer_ofc_no_r8();
		this.mer_ofc_no_r9 = user.getMer_ofc_no_r9();
		this.mer_ofc_no_r10 = user.getMer_ofc_no_r10();
		this.mer_email_addr_r1 = user.getMer_email_addr_r1();
		this.mer_email_addr_r2 = user.getMer_email_addr_r3();
		this.mer_email_addr_r3 = user.getMer_email_addr_r3();
		this.mer_email_addr_r4 = user.getMer_email_addr_r4();
		this.mer_email_addr_r5 = user.getMer_email_addr_r5();
		this.mer_email_addr_r6 = user.getMer_email_addr_r6();
		this.mer_email_addr_r7 = user.getMer_email_addr_r7();
		this.mer_email_addr_r8 = user.getMer_email_addr_r8();
		this.mer_email_addr_r9 = user.getMer_email_addr_r9();
		this.mer_email_addr_r10 = user.getMer_email_addr_r10();
		this.mer_notifi_mode = user.getMer_notifi_mode();
		this.mer_notifi_mode_r2 = user.getMer_notifi_mode_r2();
		this.mer_notifi_mode_r3 = user.getMer_notifi_mode_r3();
		this.mer_notifi_mode_r4 = user.getMer_notifi_mode_r4();
		this.mer_notifi_mode_r5 = user.getMer_notifi_mode_r5();
		this.mer_notifi_mode_r6 = user.getMer_notifi_mode_r6();
		this.mer_notifi_mode_r7 = user.getMer_notifi_mode_r7();
		this.mer_notifi_mode_r8 = user.getMer_notifi_mode_r8();
		this.mer_notifi_mode_r9 = user.getMer_notifi_mode_r9();
		this.mer_notifi_mode_r10 = user.getMer_notifi_mode_r10();
		this.send_notifi = user.getSend_notifi();
		this.send_notifi_r2 = user.getSend_notifi_r2();
		this.send_notifi_r3 = user.getSend_notifi_r3();
		this.send_notifi_r4 = user.getSend_notifi_r4();
		this.send_notifi_r5 = user.getSend_notifi_r5();
		this.send_notifi_r6 = user.getSend_notifi_r6();
		this.send_notifi_r7 = user.getSend_notifi_r7();
		this.send_notifi_r8 = user.getSend_notifi_r8();
		this.send_notifi_r9 = user.getSend_notifi_r9();
		this.send_notifi_r10 = user.getSend_notifi_r10();
		this.ph_countrycode_r1 = user.getPh_countrycode_r1();
		this.ph_countrycode_r2 = user.getPh_countrycode_r2();
		this.ph_countrycode_r3 = user.getPh_countrycode_r3();
		this.ph_countrycode_r4 = user.getPh_countrycode_r4();
		this.ph_countrycode_r5 = user.getPh_countrycode_r5();
		this.ph_countrycode_r6 = user.getPh_countrycode_r6();
		this.ph_countrycode_r7 = user.getPh_countrycode_r7();
		this.ph_countrycode_r8 = user.getPh_countrycode_r8();
		this.ph_countrycode_r9 = user.getPh_countrycode_r9();
		this.ph_countrycode_r10 = user.getPh_countrycode_r10();
		this.ofc_countrycode_r1 = user.getOfc_countrycode_r1();
		this.ofc_countrycode_r2 = user.getOfc_countrycode_r2();
		this.ofc_countrycode_r3 = user.getOfc_countrycode_r3();
		this.ofc_countrycode_r4 = user.getOfc_countrycode_r4();
		this.ofc_countrycode_r5 = user.getOfc_countrycode_r5();
		this.ofc_countrycode_r6 = user.getOfc_countrycode_r6();
		this.ofc_countrycode_r7 = user.getOfc_countrycode_r7();
		this.ofc_countrycode_r8 = user.getOfc_countrycode_r8();
		this.ofc_countrycode_r9 = user.getOfc_countrycode_r9();
		this.ofc_countrycode_r10 = user.getOfc_countrycode_r10();
		this.bank_name2 = user.getBank_name2();
		this.bank_branch2 = user.getBank_branch2();
		this.iban2 = user.getIban2();
		this.settlement_frequency2 = user.getSettlement_frequency2();
		this.settlement_day2 = user.getSettlement_day2();
		this.settlement_date2 = user.getSettlement_date2();
		this.bank_code2 = user.getBank_code2();
		this.bank_account_no2 = user.getBank_account_no2();
		this.currency2 = user.getCurrency2();
		this.chargeback_amount=user.getChargeback_amount();
		this.chargeback_approval=user.getChargeback_approval();
		this.photo=user.getPhoto();
		this.transaction_amount=user.getTransaction_amount();
	}	
}
