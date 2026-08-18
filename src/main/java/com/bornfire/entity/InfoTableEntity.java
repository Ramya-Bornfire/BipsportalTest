package com.bornfire.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="INFO_TABLE")
public class InfoTableEntity {

	private String	desc_data1;
	private String	desc_data10;
	private String	desc_data2;
	private String	desc_data3;
	private String	desc_data4;
	private String	desc_data5;
	private String	desc_data6;
	private String	desc_data7;
	private String	desc_data8;
	private String	desc_data9;
	private String	functionality;
	@Id
	private String	screen_id;
	private String	title;
	public String getDesc_data1() {
		return desc_data1;
	}
	public void setDesc_data1(String desc_data1) {
		this.desc_data1 = desc_data1;
	}
	public String getDesc_data10() {
		return desc_data10;
	}
	public void setDesc_data10(String desc_data10) {
		this.desc_data10 = desc_data10;
	}
	public String getDesc_data2() {
		return desc_data2;
	}
	public void setDesc_data2(String desc_data2) {
		this.desc_data2 = desc_data2;
	}
	public String getDesc_data3() {
		return desc_data3;
	}
	public void setDesc_data3(String desc_data3) {
		this.desc_data3 = desc_data3;
	}
	public String getDesc_data4() {
		return desc_data4;
	}
	public void setDesc_data4(String desc_data4) {
		this.desc_data4 = desc_data4;
	}
	public String getDesc_data5() {
		return desc_data5;
	}
	public void setDesc_data5(String desc_data5) {
		this.desc_data5 = desc_data5;
	}
	public String getDesc_data6() {
		return desc_data6;
	}
	public void setDesc_data6(String desc_data6) {
		this.desc_data6 = desc_data6;
	}
	public String getDesc_data7() {
		return desc_data7;
	}
	public void setDesc_data7(String desc_data7) {
		this.desc_data7 = desc_data7;
	}
	public String getDesc_data8() {
		return desc_data8;
	}
	public void setDesc_data8(String desc_data8) {
		this.desc_data8 = desc_data8;
	}
	public String getDesc_data9() {
		return desc_data9;
	}
	public void setDesc_data9(String desc_data9) {
		this.desc_data9 = desc_data9;
	}
	public String getFunctionality() {
		return functionality;
	}
	public void setFunctionality(String functionality) {
		this.functionality = functionality;
	}
	public String getScreen_id() {
		return screen_id;
	}
	public void setScreen_id(String screen_id) {
		this.screen_id = screen_id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public InfoTableEntity(String desc_data1, String desc_data10, String desc_data2, String desc_data3,
			String desc_data4, String desc_data5, String desc_data6, String desc_data7, String desc_data8,
			String desc_data9, String functionality, String screen_id, String title) {
		super();
		this.desc_data1 = desc_data1;
		this.desc_data10 = desc_data10;
		this.desc_data2 = desc_data2;
		this.desc_data3 = desc_data3;
		this.desc_data4 = desc_data4;
		this.desc_data5 = desc_data5;
		this.desc_data6 = desc_data6;
		this.desc_data7 = desc_data7;
		this.desc_data8 = desc_data8;
		this.desc_data9 = desc_data9;
		this.functionality = functionality;
		this.screen_id = screen_id;
		this.title = title;
	}
	public InfoTableEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "InfoTableEntity [desc_data1=" + desc_data1 + ", desc_data10=" + desc_data10 + ", desc_data2="
				+ desc_data2 + ", desc_data3=" + desc_data3 + ", desc_data4=" + desc_data4 + ", desc_data5="
				+ desc_data5 + ", desc_data6=" + desc_data6 + ", desc_data7=" + desc_data7 + ", desc_data8="
				+ desc_data8 + ", desc_data9=" + desc_data9 + ", functionality=" + functionality + ", screen_id="
				+ screen_id + ", title=" + title + "]";
	}
	
	

}
