

package com.bornfire.entity;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface MerchantChargesAndFeesRepositry extends JpaRepository<MerchantChargesAndFees, String> {
	
	 Optional<MerchantChargesAndFees> findById(String directorId);


	@Query(value = "select * from BIPS_MERCHANT_CHARGES_AND_FEES_TABLE where entity_flg='Y' and del_flg='N' union all select * from BIPS_MERCHANT_CHARGES_AND_FEES_MOD_TABLE where del_flg='N'", nativeQuery = true)
	List<MerchantChargesAndFees> findAllCustom();
	
	
	@Query(value = "select * from BIPS_MERCHANT_CHARGES_AND_FEES_TABLE where reference_number= ?1 ", nativeQuery = true)
	MerchantChargesAndFees findByIdReferenceNum(String Id);

	@Query(value = "select * from BIPS_MERCHANT_CHARGES_AND_FEES_TABLE where del_flg='N' and description = ?1", nativeQuery = true)
	MerchantChargesAndFees findByFeeDesc(String desc);
	
	@Query(value = "select * from BIPS_MERCHANT_CHARGES_AND_FEES_TABLE where criteria='VAT'", nativeQuery = true)
	List<MerchantChargesAndFees> findAllVatfeesdata();
	
}
