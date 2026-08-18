package com.bornfire.entity;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name= "REF_MASTER")
public class ReferenceCodeEntity {

	
	private String	ref_type;
	private String	ref_type_desc;
	@Id
	private String	ref_id;
	private String	ref_id_desc;
	private String	module_id;
	private String	remarks;
	private String	entry_user;
	private String	modify_user;
	private String	auth_user;
	private Date	entry_time;
	private Date	modify_time;
	private Date	auth_time;
	private String	del_flg;
	private String	modify_flg;
	private String	entity_flg;
	public String getRef_type() {
		return ref_type;
	}
	public void setRef_type(String ref_type) {
		this.ref_type = ref_type;
	}
	public String getRef_type_desc() {
		return ref_type_desc;
	}
	public void setRef_type_desc(String ref_type_desc) {
		this.ref_type_desc = ref_type_desc;
	}
	public String getRef_id() {
		return ref_id;
	}
	public void setRef_id(String ref_id) {
		this.ref_id = ref_id;
	}
	public String getRef_id_desc() {
		return ref_id_desc;
	}
	public void setRef_id_desc(String ref_id_desc) {
		this.ref_id_desc = ref_id_desc;
	}
	public String getModule_id() {
		return module_id;
	}
	public void setModule_id(String module_id) {
		this.module_id = module_id;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
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
	public String getAuth_user() {
		return auth_user;
	}
	public void setAuth_user(String auth_user) {
		this.auth_user = auth_user;
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
	public Date getAuth_time() {
		return auth_time;
	}
	public void setAuth_time(Date auth_time) {
		this.auth_time = auth_time;
	}
	public String getDel_flg() {
		return del_flg;
	}
	public void setDel_flg(String del_flg) {
		this.del_flg = del_flg;
	}
	public String getModify_flg() {
		return modify_flg;
	}
	public void setModify_flg(String modify_flg) {
		this.modify_flg = modify_flg;
	}
	public String getEntity_flg() {
		return entity_flg;
	}
	public void setEntity_flg(String entity_flg) {
		this.entity_flg = entity_flg;
	}
	public ReferenceCodeEntity(String ref_type, String ref_type_desc, String ref_id, String ref_id_desc,
			String module_id, String remarks, String entry_user, String modify_user, String auth_user, Date entry_time,
			Date modify_time, Date auth_time, String del_flg, String modify_flg, String entity_flg) {
		super();
		this.ref_type = ref_type;
		this.ref_type_desc = ref_type_desc;
		this.ref_id = ref_id;
		this.ref_id_desc = ref_id_desc;
		this.module_id = module_id;
		this.remarks = remarks;
		this.entry_user = entry_user;
		this.modify_user = modify_user;
		this.auth_user = auth_user;
		this.entry_time = entry_time;
		this.modify_time = modify_time;
		this.auth_time = auth_time;
		this.del_flg = del_flg;
		this.modify_flg = modify_flg;
		this.entity_flg = entity_flg;
	}
	public ReferenceCodeEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

}

