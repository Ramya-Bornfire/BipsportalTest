package com.bornfire.entity;

import java.util.Arrays;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "BIPS_MERCHANT_POSTER_TABLE")
public class PosterEntity {
	private String	merchant_id;
	private String	unit_id;
	private String	unit_name;
	@Id
	private String poster_id;
	private String	merchant_rep_id;
	private Date	poster_date;
    private byte[] file_name;
    private String	image_name;
    private String	frequency;
    private Date	from_date;
    private Date	to_date;
	public String getMerchant_id() {
		return merchant_id;
	}
	public void setMerchant_id(String merchant_id) {
		this.merchant_id = merchant_id;
	}
	public String getUnit_id() {
		return unit_id;
	}
	public void setUnit_id(String unit_id) {
		this.unit_id = unit_id;
	}
	public String getUnit_name() {
		return unit_name;
	}
	public void setUnit_name(String unit_name) {
		this.unit_name = unit_name;
	}
	public String getPoster_id() {
		return poster_id;
	}
	public void setPoster_id(String poster_id) {
		this.poster_id = poster_id;
	}
	public String getMerchant_rep_id() {
		return merchant_rep_id;
	}
	public void setMerchant_rep_id(String merchant_rep_id) {
		this.merchant_rep_id = merchant_rep_id;
	}
	public Date getPoster_date() {
		return poster_date;
	}
	public void setPoster_date(Date poster_date) {
		this.poster_date = poster_date;
	}
	public byte[] getFile_name() {
		return file_name;
	}
	public void setFile_name(byte[] file_name) {
		this.file_name = file_name;
	}
	public String getImage_name() {
		return image_name;
	}
	public void setImage_name(String image_name) {
		this.image_name = image_name;
	}
	public String getFrequency() {
		return frequency;
	}
	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}
	public Date getFrom_date() {
		return from_date;
	}
	public void setFrom_date(Date from_date) {
		this.from_date = from_date;
	}
	public Date getTo_date() {
		return to_date;
	}
	public void setTo_date(Date to_date) {
		this.to_date = to_date;
	}
	public PosterEntity(String merchant_id, String unit_id, String unit_name, String poster_id, String merchant_rep_id,
			Date poster_date, byte[] file_name, String image_name, String frequency, Date from_date, Date to_date) {
		super();
		this.merchant_id = merchant_id;
		this.unit_id = unit_id;
		this.unit_name = unit_name;
		this.poster_id = poster_id;
		this.merchant_rep_id = merchant_rep_id;
		this.poster_date = poster_date;
		this.file_name = file_name;
		this.image_name = image_name;
		this.frequency = frequency;
		this.from_date = from_date;
		this.to_date = to_date;
	}
	public PosterEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "PosterEntity [merchant_id=" + merchant_id + ", unit_id=" + unit_id + ", unit_name=" + unit_name
				+ ", poster_id=" + poster_id + ", merchant_rep_id=" + merchant_rep_id + ", poster_date=" + poster_date
				+ ", file_name=" + Arrays.toString(file_name) + ", image_name=" + image_name + ", frequency="
				+ frequency + ", from_date=" + from_date + ", to_date=" + to_date + "]";
	}
	
    

}
