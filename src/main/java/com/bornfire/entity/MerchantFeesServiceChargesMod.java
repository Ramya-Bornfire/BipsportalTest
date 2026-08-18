package com.bornfire.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Table(name="MERCHANT_FEES_SERVICE_CHARGES_MOD")
@Entity
@IdClass(MerchantFeeID.class)
public class MerchantFeesServiceChargesMod {
    @Id
    private String srl_no;
    @Id
    private String merchant_id;
    private  String fee_desc;
    private  String fee_type;
    private  String amount_per;
    private  String fee_freq;
    private  String vat_collect;
    private  String del_flg;

    public String getSrl_no() {
        return srl_no;
    }

    public void setSrl_no(String srl_no) {
        this.srl_no = srl_no;
    }

    public String getMerchant_id() {
        return merchant_id;
    }

    public void setMerchant_id(String merchant_id) {
        this.merchant_id = merchant_id;
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

    public String getDel_flg() {
        return del_flg;
    }

    public void setDel_flg(String del_flg) {
        this.del_flg = del_flg;
    }

    public MerchantFeesServiceChargesMod(String srl_no,String merchant_id, String fee_desc, String fee_type, String amount_per, String fee_freq, String vat_collect, String del_flg) {
        this.srl_no = srl_no;
        this.merchant_id = merchant_id;
        this.fee_desc = fee_desc;
        this.fee_type = fee_type;
        this.amount_per = amount_per;
        this.fee_freq = fee_freq;
        this.vat_collect = vat_collect;
        this.del_flg = del_flg;
    }

    public MerchantFeesServiceChargesMod() {
        super();
    }
    
    
    public MerchantFeesServiceChargesMod( MerchantFeesServiceCharges up) {
        this.srl_no = up.getSrl_no();
        this.merchant_id = up.getMerchant_id();
        this.fee_desc = up.getFee_desc();
        this.fee_type = up.getFee_type();
        this.amount_per = up.getAmount_per();
        this.fee_freq = up.getFee_freq();
        this.vat_collect = up.getVat_collect();
        this.del_flg = up.getDel_flg();
    }
}
