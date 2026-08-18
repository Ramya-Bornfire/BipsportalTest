package com.bornfire.entity;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IPSAuditRepo extends JpaRepository<IPSAuditTable,String> {
	@Query(value = "select * from BIPS_AUDIT_TABLE where trunc(audit_date) between ?1 and ?2  AND audit_table  in ('BIPS_USER PROFILE','BIPS_USER_PROFILE') and modi_details is not null order by entry_time desc", nativeQuery = true)
	List<IPSAuditTable> getauditListLocal(Date Fromdate, Date Todate);
}
