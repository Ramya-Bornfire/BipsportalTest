package com.bornfire.entity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BIPS_Alert_Repo extends JpaRepository<BIPS_Alert_Entity, String>{

	@Query(value = "SELECT DISTINCT * FROM bips_alert", nativeQuery = true)
	List<BIPS_Alert_Entity> getlst();
	
}
