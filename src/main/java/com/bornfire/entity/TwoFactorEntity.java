package com.bornfire.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TWOFA")
public class TwoFactorEntity {
			@Id
			private String user_id;
		    private String username;
		    private String password_hash;
		    private String two_fa_enabled;
		    private String preferred_method;
		    private String phone_number;
		    private String email;
		    private String auth_app_secret;
		    private String security_answer_1;
		    private String security_answer_2;
		    private String security_answer_3;
		    private String security_answer_4;
		    private String security_answer_5;
		    private String security_answer_6;
		    private String security_answer_7;
		    private String security_answer_8;
		    private String security_answer_9;
		    private String security_answer_10;
			public String getUser_id() {
				return user_id;
			}
			public void setUser_id(String user_id) {
				this.user_id = user_id;
			}
			public String getUsername() {
				return username;
			}
			public void setUsername(String username) {
				this.username = username;
			}
			public String getPassword_hash() {
				return password_hash;
			}
			public void setPassword_hash(String password_hash) {
				this.password_hash = password_hash;
			}
			public String getTwo_fa_enabled() {
				return two_fa_enabled;
			}
			public void setTwo_fa_enabled(String two_fa_enabled) {
				this.two_fa_enabled = two_fa_enabled;
			}
			public String getPreferred_method() {
				return preferred_method;
			}
			public void setPreferred_method(String preferred_method) {
				this.preferred_method = preferred_method;
			}
			public String getPhone_number() {
				return phone_number;
			}
			public void setPhone_number(String phone_number) {
				this.phone_number = phone_number;
			}
			public String getEmail() {
				return email;
			}
			public void setEmail(String email) {
				this.email = email;
			}
			public String getAuth_app_secret() {
				return auth_app_secret;
			}
			public void setAuth_app_secret(String auth_app_secret) {
				this.auth_app_secret = auth_app_secret;
			}
			public String getSecurity_answer_1() {
				return security_answer_1;
			}
			public void setSecurity_answer_1(String security_answer_1) {
				this.security_answer_1 = security_answer_1;
			}
			public String getSecurity_answer_2() {
				return security_answer_2;
			}
			public void setSecurity_answer_2(String security_answer_2) {
				this.security_answer_2 = security_answer_2;
			}
			public String getSecurity_answer_3() {
				return security_answer_3;
			}
			public void setSecurity_answer_3(String security_answer_3) {
				this.security_answer_3 = security_answer_3;
			}
			public String getSecurity_answer_4() {
				return security_answer_4;
			}
			public void setSecurity_answer_4(String security_answer_4) {
				this.security_answer_4 = security_answer_4;
			}
			public String getSecurity_answer_5() {
				return security_answer_5;
			}
			public void setSecurity_answer_5(String security_answer_5) {
				this.security_answer_5 = security_answer_5;
			}
			public String getSecurity_answer_6() {
				return security_answer_6;
			}
			public void setSecurity_answer_6(String security_answer_6) {
				this.security_answer_6 = security_answer_6;
			}
			public String getSecurity_answer_7() {
				return security_answer_7;
			}
			public void setSecurity_answer_7(String security_answer_7) {
				this.security_answer_7 = security_answer_7;
			}
			public String getSecurity_answer_8() {
				return security_answer_8;
			}
			public void setSecurity_answer_8(String security_answer_8) {
				this.security_answer_8 = security_answer_8;
			}
			public String getSecurity_answer_9() {
				return security_answer_9;
			}
			public void setSecurity_answer_9(String security_answer_9) {
				this.security_answer_9 = security_answer_9;
			}
			public String getSecurity_answer_10() {
				return security_answer_10;
			}
			public void setSecurity_answer_10(String security_answer_10) {
				this.security_answer_10 = security_answer_10;
			}
			public TwoFactorEntity(String user_id, String username, String password_hash, String two_fa_enabled,
					String preferred_method, String phone_number, String email, String auth_app_secret,
					String security_answer_1, String security_answer_2, String security_answer_3,
					String security_answer_4, String security_answer_5, String security_answer_6,
					String security_answer_7, String security_answer_8, String security_answer_9,
					String security_answer_10) {
				super();
				this.user_id = user_id;
				this.username = username;
				this.password_hash = password_hash;
				this.two_fa_enabled = two_fa_enabled;
				this.preferred_method = preferred_method;
				this.phone_number = phone_number;
				this.email = email;
				this.auth_app_secret = auth_app_secret;
				this.security_answer_1 = security_answer_1;
				this.security_answer_2 = security_answer_2;
				this.security_answer_3 = security_answer_3;
				this.security_answer_4 = security_answer_4;
				this.security_answer_5 = security_answer_5;
				this.security_answer_6 = security_answer_6;
				this.security_answer_7 = security_answer_7;
				this.security_answer_8 = security_answer_8;
				this.security_answer_9 = security_answer_9;
				this.security_answer_10 = security_answer_10;
			}
			public TwoFactorEntity() {
				super();
				// TODO Auto-generated constructor stub
			}
	
}
