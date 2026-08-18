package com.bornfire.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "BIPS_QR_UPI_GLOBAL")
public class QRUrlGlobalEntity {

	private String vers;
	private String modes;
	private String purpose;
	private String orgid;
	private String tid;
	private String tr;
	private String tn;
	@Id
	private String pa;
	private String pn;
	private BigDecimal mc;
	private String mid;
	private String msid;
	private String mtid;
	private String ccs;
	private BigDecimal bam;
	private String curr;
	private String qrmedium;
	private String invoiceno;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date invoicedate;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date qrexpire;
	private String signs;
	private String categorys;
	private String urls;
	private BigDecimal am;
	private String cu;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date qrts;

	private String splits;
	private String pincode;
	private String tiers;
	private String txntype;
	private String consent;
	private String querys;
	private String base64;
	private Character verify_flg;
	private Character entity_flg;
	private Character del_flg;
	private Character modify_flg;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date entry_date;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date modify_date;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date verify_date;
	private String entry_user;
	private String modify_user;
	private String verify_user;
	private byte[] qrcode;
	private String mtype;
	private String mgr;
	private String merchant_onboarding;
	private String merchant_location;
	private String brand;
	private String entips;
	private String tipsorconv;
	private String cov_fee_type;
	private String val_con_fee;
	private String tips_value;

	public String getVers() {
		return vers;
	}

	public void setVers(String vers) {
		this.vers = vers;
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

	public String getPn() {
		return pn;
	}

	public void setPn(String pn) {
		this.pn = pn;
	}

	public BigDecimal getMc() {
		return mc;
	}

	public void setMc(BigDecimal mc) {
		this.mc = mc;
	}

	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
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

	public String getCcs() {
		return ccs;
	}

	public void setCcs(String ccs) {
		this.ccs = ccs;
	}

	public BigDecimal getBam() {
		return bam;
	}

	public void setBam(BigDecimal bam) {
		this.bam = bam;
	}

	public String getCurr() {
		return curr;
	}

	public void setCurr(String curr) {
		this.curr = curr;
	}

	public String getQrmedium() {
		return qrmedium;
	}

	public void setQrmedium(String qrmedium) {
		this.qrmedium = qrmedium;
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

	public Date getQrexpire() {
		return qrexpire;
	}

	public void setQrexpire(Date qrexpire) {
		this.qrexpire = qrexpire;
	}

	public String getSigns() {
		return signs;
	}

	public void setSigns(String signs) {
		this.signs = signs;
	}

	public String getCategorys() {
		return categorys;
	}

	public void setCategorys(String categorys) {
		this.categorys = categorys;
	}

	public String getUrls() {
		return urls;
	}

	public void setUrls(String urls) {
		this.urls = urls;
	}

	public BigDecimal getAm() {
		return am;
	}

	public void setAm(BigDecimal am) {
		this.am = am;
	}

	public String getCu() {
		return cu;
	}

	public void setCu(String cu) {
		this.cu = cu;
	}

	public Date getQrts() {
		return qrts;
	}

	public void setQrts(Date qrts) {
		this.qrts = qrts;
	}

	public String getSplits() {
		return splits;
	}

	public void setSplits(String splits) {
		this.splits = splits;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public String getTiers() {
		return tiers;
	}

	public void setTiers(String tiers) {
		this.tiers = tiers;
	}

	public String getTxntype() {
		return txntype;
	}

	public void setTxntype(String txntype) {
		this.txntype = txntype;
	}

	public String getConsent() {
		return consent;
	}

	public void setConsent(String consent) {
		this.consent = consent;
	}

	public String getQuerys() {
		return querys;
	}

	public void setQuerys(String querys) {
		this.querys = querys;
	}

	public String getBase64() {
		return base64;
	}

	public void setBase64(String base64) {
		this.base64 = base64;
	}

	public Character getVerify_flg() {
		return verify_flg;
	}

	public void setVerify_flg(Character verify_flg) {
		this.verify_flg = verify_flg;
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

	public Character getModify_flg() {
		return modify_flg;
	}

	public void setModify_flg(Character modify_flg) {
		this.modify_flg = modify_flg;
	}

	public Date getEntry_date() {
		return entry_date;
	}

	public void setEntry_date(Date entry_date) {
		this.entry_date = entry_date;
	}

	public Date getModify_date() {
		return modify_date;
	}

	public void setModify_date(Date modify_date) {
		this.modify_date = modify_date;
	}

	public Date getVerify_date() {
		return verify_date;
	}

	public void setVerify_date(Date verify_date) {
		this.verify_date = verify_date;
	}

	public String getEntry_user() {
		return entry_user;
	}

	public void setEntry_user(String entry_user) {
		this.entry_user = entry_user;
	}

	public String getModify_user() {
		return modify_user;
	}

	public void setModify_user(String modify_user) {
		this.modify_user = modify_user;
	}

	public String getVerify_user() {
		return verify_user;
	}

	public void setVerify_user(String verify_user) {
		this.verify_user = verify_user;
	}

	public byte[] getQrcode() {
		return qrcode;
	}

	public void setQrcode(byte[] qrcode) {
		this.qrcode = qrcode;
	}

	public String getMtype() {
		return mtype;
	}

	public void setMtype(String mtype) {
		this.mtype = mtype;
	}

	public String getMgr() {
		return mgr;
	}

	public void setMgr(String mgr) {
		this.mgr = mgr;
	}

	public String getMerchant_onboarding() {
		return merchant_onboarding;
	}

	public void setMerchant_onboarding(String merchant_onboarding) {
		this.merchant_onboarding = merchant_onboarding;
	}

	public String getMerchant_location() {
		return merchant_location;
	}

	public void setMerchant_location(String merchant_location) {
		this.merchant_location = merchant_location;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getEntips() {
		return entips;
	}

	public void setEntips(String entips) {
		this.entips = entips;
	}

	public String getTipsorconv() {
		return tipsorconv;
	}

	public void setTipsorconv(String tipsorconv) {
		this.tipsorconv = tipsorconv;
	}

	public String getCov_fee_type() {
		return cov_fee_type;
	}

	public void setCov_fee_type(String cov_fee_type) {
		this.cov_fee_type = cov_fee_type;
	}

	public String getVal_con_fee() {
		return val_con_fee;
	}

	public void setVal_con_fee(String val_con_fee) {
		this.val_con_fee = val_con_fee;
	}

	public String getTips_value() {
		return tips_value;
	}

	public void setTips_value(String tips_value) {
		this.tips_value = tips_value;
	}

	public QRUrlGlobalEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public QRUrlGlobalEntity(String vers, String modes, String purpose, String orgid, String tid, String tr, String tn,
			String pa, String pn, BigDecimal mc, String mid, String msid, String mtid, String ccs, BigDecimal bam,
			String curr, String qrmedium, String invoiceno, Date invoicedate, Date qrexpire, String signs,
			String categorys, String urls, BigDecimal am, String cu, Date qrts, String splits, String pincode,
			String tiers, String txntype, String consent, String querys, String base64, Character verify_flg,
			Character entity_flg, Character del_flg, Character modify_flg, Date entry_date, Date modify_date,
			Date verify_date, String entry_user, String modify_user, String verify_user, byte[] qrcode, String mtype,
			String mgr, String merchant_onboarding, String merchant_location, String brand, String entips,
			String tipsorconv, String cov_fee_type, String val_con_fee, String tips_value) {
		super();
		this.vers = vers;
		this.modes = modes;
		this.purpose = purpose;
		this.orgid = orgid;
		this.tid = tid;
		this.tr = tr;
		this.tn = tn;
		this.pa = pa;
		this.pn = pn;
		this.mc = mc;
		this.mid = mid;
		this.msid = msid;
		this.mtid = mtid;
		this.ccs = ccs;
		this.bam = bam;
		this.curr = curr;
		this.qrmedium = qrmedium;
		this.invoiceno = invoiceno;
		this.invoicedate = invoicedate;
		this.qrexpire = qrexpire;
		this.signs = signs;
		this.categorys = categorys;
		this.urls = urls;
		this.am = am;
		this.cu = cu;
		this.qrts = qrts;
		this.splits = splits;
		this.pincode = pincode;
		this.tiers = tiers;
		this.txntype = txntype;
		this.consent = consent;
		this.querys = querys;
		this.base64 = base64;
		this.verify_flg = verify_flg;
		this.entity_flg = entity_flg;
		this.del_flg = del_flg;
		this.modify_flg = modify_flg;
		this.entry_date = entry_date;
		this.modify_date = modify_date;
		this.verify_date = verify_date;
		this.entry_user = entry_user;
		this.modify_user = modify_user;
		this.verify_user = verify_user;
		this.qrcode = qrcode;
		this.mtype = mtype;
		this.mgr = mgr;
		this.merchant_onboarding = merchant_onboarding;
		this.merchant_location = merchant_location;
		this.brand = brand;
		this.entips = entips;
		this.tipsorconv = tipsorconv;
		this.cov_fee_type = cov_fee_type;
		this.val_con_fee = val_con_fee;
		this.tips_value = tips_value;
	}

}
