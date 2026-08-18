package com.bornfire.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MerchantChargesandFeesModRep extends JpaRepository<MerchantChargesandFeesMod, String>{
	
	@Query(value = "select * from BIPS_MERCHANT_CHARGES_AND_FEES_MOD_TABLE where reference_number= ?1 ", nativeQuery = true)
	MerchantChargesandFeesMod findByIdReferenceNum(String Id);
	
	@Query(value = "DELETE FROM BIPS_MERCHANT_CHARGES_AND_FEES_MOD_TABLE where reference_number= ?1", nativeQuery = true)
	void deleteDetails(String Id);

}
