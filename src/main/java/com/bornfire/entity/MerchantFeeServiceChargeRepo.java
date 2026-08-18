package com.bornfire.entity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MerchantFeeServiceChargeRepo extends JpaRepository<MerchantFeesServiceCharges, MerchantFeeID> {

	@Query(value = "select * from MERCHANT_FEES_SERVICE_CHARGES where MERCHANT_ID=?1", nativeQuery = true)
    List<MerchantFeesServiceCharges> merchantDetails(String merchant_id);
}
