package com.bornfire.entity;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BIPS_PasswordManagement_Repo extends JpaRepository<BIPS_Password_Management_Entity, String> {

	@Query(value = "select  password FROM BIPS_PASSWORD_MANAGEMENT where merchant_user_id=?1", nativeQuery = true)
	String getPassword(String userid);

	@Query(value = "select * from BIPS_PASSWORD_MANAGEMENT where merchant_user_id=?1", nativeQuery = true)
	BIPS_Password_Management_Entity getpasslst(String merchant_user_id);

	@Query(value = "select * from BIPS_PASSWORD_MANAGEMENT where merchant_user_id=?1 ", nativeQuery = true)
	List<Object[]> getpasslsts(String MERCHANT_USER_ID);

	@Query(value = "select * from BIPS_PASSWORD_MANAGEMENT where merchant_user_id=?1 AND merchant_rep_id=?2 ", nativeQuery = true)
	List<BIPS_Password_Management_Entity> getmerrep(String MERCHANT_USER_ID, String merchant_rep_id);

	@Query(value = "select * from BIPS_PASSWORD_MANAGEMENT where merchant_user_id=?1 AND unit_id=?2 ", nativeQuery = true)
	List<BIPS_Password_Management_Entity> getmerunit(String MERCHANT_USER_ID, String merUnit);

	@Query(value = "select * from BIPS_PASSWORD_MANAGEMENT where merchant_user_id=?1 ", nativeQuery = true)
	List<BIPS_Password_Management_Entity> getmersecondif(String MERCHANT_USER_ID);

	@Query(value = "select * from BIPS_PASSWORD_MANAGEMENT where merchant_user_id=?1 and unit_id=?2 ", nativeQuery = true)
	List<BIPS_Password_Management_Entity> getPassmerId(String merchant_user_id, String unit_id);

	@Query(value = "select * from BIPS_PASSWORD_MANAGEMENT where merchant_user_id=?1 ", nativeQuery = true)
	List<BIPS_Password_Management_Entity> getPassmer(String merchant_user_id);

	@Query(value = "select * from BIPS_PASSWORD_MANAGEMENT where merchant_rep_id=?1", nativeQuery = true)
	BIPS_Password_Management_Entity getRepId(String merchant_rep_id);

	@Query(value = "SELECT * FROM BIPS_PASSWORD_MANAGEMENT WHERE merchant_user_id = ?1", nativeQuery = true)
	List<BIPS_Password_Management_Entity> getPassList(String merchant_user_id);

	@Query(value = "select merchant_user_id from BIPS_PASSWORD_MANAGEMENT where merchant_rep_id=?1", nativeQuery = true)
	String getRepQR(String merchant_rep_id);

	@Query(value = "SELECT * FROM BIPS_PASSWORD_MANAGEMENT WHERE MERCHANT_REP_ID=?1 ", nativeQuery = true)
	BIPS_Password_Management_Entity getrole(String userid);

	@Query(value = "SELECT * FROM BIPS_PASSWORD_MANAGEMENT WHERE unit_id=?1 ", nativeQuery = true)
	List<BIPS_Password_Management_Entity> getunit(String unitid);

	@Modifying
	@Transactional
	@Query(value = "UPDATE BIPS_PASSWORD_MANAGEMENT a SET a.no_of_attmp = NVL(a.no_of_attmp, 0) + 1, a.user_locked_flg = DECODE(NVL(a.no_of_attmp, 0) + 1, :attempts, 'Y', 'N'), a.login_status = DECODE(NVL(a.no_of_attmp, 0) + 1, :attempts, 'Y', 'N') WHERE a.merchant_rep_id = :merchant_rep_id", nativeQuery = true)
	void updateLoginLockedFlg1(@Param("merchant_rep_id") String merchantRepId, @Param("attempts") int attempts);

}
