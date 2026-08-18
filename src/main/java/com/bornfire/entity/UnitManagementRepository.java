package com.bornfire.entity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UnitManagementRepository extends JpaRepository<UnitManagementEntity,String>{
	
	@Query(value = "select * from  BIPS_MERCHANT_UNIT_MANAGEMENT WHERE unit_id=?1 and del_flg='N'", nativeQuery = true)
	List<UnitManagementEntity> findByAll(String merchant);
	
	@Query(value="select * from BIPS_MERCHANT_UNIT_MANAGEMENT WHERE unit_id=?1 and del_flg='N'",nativeQuery=true)
	UnitManagementEntity findByUnitId(String unitid);
	
	@Query(value = "select * from BIPS_MERCHANT_UNIT_MANAGEMENT where unit_id=?1 and del_flg='N' ", nativeQuery = true)
	List<UnitManagementEntity> merctopas(String a);

	@Query(value = "select * from BIPS_MERCHANT_UNIT_MANAGEMENT where unit_id=?1 and del_flg='N'", nativeQuery = true)
	UnitManagementEntity getUnitId(String unit_id);

	@Query(value = "select * from BIPS_MERCHANT_UNIT_MANAGEMENT where merchant_user_id=?1 and del_flg='N' ", nativeQuery = true)
	List<UnitManagementEntity> getUnitlist(String merchant_user_id);
	
	@Query(value = "select * from BIPS_MERCHANT_UNIT_MANAGEMENT where merchant_user_id=?1 and del_flg='N' order by unit_id desc", nativeQuery = true)
	List<UnitManagementEntity> getUniqueUnitlist(String merchant_user_id);

	@Query(value = "select * from BIPS_MERCHANT_UNIT_MANAGEMENT where  merchant_user_id=?1  and del_flg='N'", nativeQuery = true)
	UnitManagementEntity findByIdCustoms(String a);
	
	@Query(value = "select * from BIPS_MERCHANT_UNIT_MANAGEMENT where merchant_user_id=?1 AND unit_id=?2 and del_flg='N'", nativeQuery = true)
	List<UnitManagementEntity> getUnitId(String merchant_user_id,String unit_id );
	
	@Query(value = "select * from BIPS_Unit_Mangement_Entity where merchant_user_id=?1 AND merchant_rep_id=?2  and del_flg='N'", nativeQuery = true)
	List<UnitManagementEntity> getmerrep(String MERCHANT_USER_ID ,String merchant_rep_id);
	
	@Query(value = "select DISTINCT unit_id from BIPS_MERCHANT_UNIT_MANAGEMENT where merchant_user_id =?1 and del_flg='N'", nativeQuery = true)
	List<String> getpartUnitId(String merchant_id1);
	
	@Query(value = "select UNIT_NAME,UNIT_TYPE from BIPS_MERCHANT_UNIT_MANAGEMENT where UNIT_ID =?1 and del_flg='N'", nativeQuery = true)
	Object[] getUnitDetail(String unitId);
	

}
