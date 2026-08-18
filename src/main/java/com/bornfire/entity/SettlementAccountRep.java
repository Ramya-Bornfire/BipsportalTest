package com.bornfire.entity;

import java.math.BigDecimal;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;



@Transactional
@Repository
public interface SettlementAccountRep extends JpaRepository<SettlementAccount, String> {

	@Modifying
	@Query(value = "update BIPS_SETTL_ACCTS set not_bal=?1  where acc_code='06'", nativeQuery = true)
	void updateNotBalData(String acc_code);
	
	@Query(value = "select * from BIPS_SETTL_ACCTS  where acc_code='06'", nativeQuery = true)
	SettlementAccount getNotBalData();
	
	@Modifying
	@Query(value = "update BIPS_SETTL_ACCTS set not_bal=not_bal-?2,acct_bal=acct_bal-?2  where acc_code=?1", nativeQuery = true)
	void updateGLNotBalReceivable(String acc_code,BigDecimal amount);
	
	@Modifying
	@Query(value = "update BIPS_SETTL_ACCTS set not_bal=not_bal+?2,acct_bal=acct_bal+?2  where acc_code=?1", nativeQuery = true)
	void updateGLNotBalSettlement(String acc_code,BigDecimal amount);

	@Query(value = "select account_number from BIPS_SETTL_ACCTS where acc_code=?1", nativeQuery = true)
	String findByAccountNuber(String acc);
	
	
}
