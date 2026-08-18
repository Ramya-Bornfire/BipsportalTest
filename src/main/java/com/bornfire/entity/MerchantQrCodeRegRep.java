package com.bornfire.entity;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Transactional
@Repository
public interface MerchantQrCodeRegRep extends JpaRepository<MerchantQRRegistration, String> {
	Optional<MerchantQRRegistration> findById(String directorId);
	
	@Query(value = "select * from BIPS_MERCHANT_QRCODE_REG_TABLE ", nativeQuery = true)
	List<MerchantQRRegistration> findAllCustom();
	
	@Query(value = "select * from BIPS_MERCHANT_QRCODE_REG_TABLE ", nativeQuery = true)
	List<MerchantQRRegistration> findAllData();

	@Query(value = "select * from BIPS_MERCHANT_QRCODE_REG_TABLE where merchant_acct_no= ?1", nativeQuery = true)
	MerchantQRRegistration findByIdCustom(String Id);
}
