package com.bornfire.entity;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface  AccessandRolesRepository extends JpaRepository<IPSAccessRole,String> {
	 Optional<IPSAccessRole> findById( String directorId);
	 
	 
	 @Query(value = "select * from BIPS_ACCESS_ROLE_TABLE  where ROLE_ID =?1", nativeQuery = true)
		String FindByAll(String roleId);
	 
	 @Query(value = "select * from BIPS_ACCESS_ROLE_TABLE  where ROLE_ID =?1", nativeQuery = true)
	 IPSAccessRole getRoleMenu(String roleId);

	 @Query(value = "select count(*) from BIPS_USER_PROFILE where ROLE_ID =?1 and ENTITY_FLG ='Y' and DEL_FLG <>'Y'", nativeQuery = true)
		BigDecimal userrolecount(String roleId);
	 
	 @Query(value = "select * from BIPS_ACCESS_ROLE_TABLE where DEL_FLG!='Y'", nativeQuery = true)
	 List<IPSAccessRole> rulelist();
	 
	 @Query(value = "select distinct role_desc from BIPS_ACCESS_ROLE_TABLE where role_id =?1", nativeQuery = true)
	 String rulelistCODE(String roleId);
	 
	 @Modifying
		@Query(value = "UPDATE BIPS_ACCESS_ROLE_TABLE set DEL_FLG ='Y' where ROLE_ID =?1", nativeQuery = true)
		String findByfgdg1(String roleId);
	 
	 @Query(value = "select distinct ROLE_ID from BIPS_ACCESS_ROLE_TABLE  where DEL_FLG='N'", nativeQuery = true)
		List<String> roleidtype();

	 @Query(value = "select count(*) from BIPS_ACCESS_ROLE_TABLE  where role_id=?1 and del_flg='N'", nativeQuery = true)
	String getusercount(String role_id);

	 @Query(value = "select * from BIPS_ACCESS_ROLE_TABLE  where entity_flg ='Y' and del_flg ='N' UNION ALL select * from BIPS_ACCESS_ROLE_MOD_TABLE  where entity_flg ='N'", nativeQuery = true)
	List<IPSAccessRole> finsAllData();

}
