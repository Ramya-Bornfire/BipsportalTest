package com.bornfire.entity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepository extends JpaRepository<LoginEntity,String>{
	@Query(value = "select * from  bips_password_management", nativeQuery = true)
	List<LoginEntity> findByAll();
	
	@Query(value="select * from  bips_password_management where merchant_user_id=?1",nativeQuery=true)
	LoginEntity findByMerchantUserId(String merchantUserId);
	
	@Query(value="select * from bips_password_management WHERE merchant_rep_id=?1",nativeQuery=true) 
    LoginEntity findByMerchantRepID(String MerchantRep);
	
	@Query(value = "select  password FROM BIPS_PASSWORD_MANAGEMENT where merchant_user_id=?1", nativeQuery = true)
	String getPassword(String userid);
	
	

	@Query(value = "select * from BIPS_PASSWORD_MANAGEMENT where merchant_user_id=?1", nativeQuery = true)
	LoginEntity getpasslst(String merchant_user_id);

	@Query(value = "select * from BIPS_PASSWORD_MANAGEMENT where merchant_user_id=?1 ", nativeQuery = true)
	List<Object[]> getpasslsts(String MERCHANT_USER_ID);
	
	@Query(value = "select * from BIPS_PASSWORD_MANAGEMENT where merchant_user_id=?1 AND merchant_rep_id=?2 ", nativeQuery = true)
	List<LoginEntity> getmerrep(String MERCHANT_USER_ID ,String merchant_rep_id);
	
	@Query(value = "select * from BIPS_PASSWORD_MANAGEMENT where merchant_user_id=?1 AND unit_id=?2 ", nativeQuery = true)
	List<LoginEntity> getmerunit(String MERCHANT_USER_ID ,String merUnit);
	@Query(value = "select * from BIPS_PASSWORD_MANAGEMENT where merchant_user_id=?1", nativeQuery = true)
	List<LoginEntity> getmerunitpasswrd(String MERCHANT_USER_ID );
	
	
	
	@Query(value = "select * from BIPS_PASSWORD_MANAGEMENT where merchant_user_id=?1 ", nativeQuery = true)
	List<LoginEntity> getmersecondif(String MERCHANT_USER_ID );

	@Query(value = "select * from BIPS_PASSWORD_MANAGEMENT where merchant_user_id=?1 and unit_id=?2 ", nativeQuery = true)
	List<LoginEntity> getPassmerId(String merchant_user_id, String unit_id);

	@Query(value = "select * from BIPS_PASSWORD_MANAGEMENT where merchant_user_id=?1 ", nativeQuery = true)
	List<LoginEntity> getPassmer(String merchant_user_id);

	@Query(value = "select * from BIPS_PASSWORD_MANAGEMENT where merchant_rep_id=?1", nativeQuery = true)
	LoginEntity getRepId(String merchant_rep_id);
	
	@Query(value = "SELECT * FROM BIPS_PASSWORD_MANAGEMENT WHERE merchant_user_id = ?1", nativeQuery = true)
    List<LoginEntity> getPassList(String merchant_user_id);
    
    @Query(value = "select merchant_user_id from BIPS_PASSWORD_MANAGEMENT where merchant_rep_id=?1", nativeQuery = true)
	String getRepQR(String merchant_rep_id);

	 @Query(value="SELECT * FROM BIPS_PASSWORD_MANAGEMENT WHERE MERCHANT_REP_ID=?1 ", nativeQuery = true)
	 LoginEntity getrole(String userid);
	 
	 
	 @Query(value="SELECT * FROM BIPS_PASSWORD_MANAGEMENT WHERE unit_id=?1 ", nativeQuery = true)
	 List< LoginEntity> getunit(String unitid);

}