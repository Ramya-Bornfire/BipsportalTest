package com.bornfire.entity;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MerchantFeesRep extends CrudRepository<MerchantFees,String>{
	
	@Query(value = "select * from BIPS_MERCHANT_FEES_TABLE where NVL(DEL_FLG,1) <> 'Y'", nativeQuery = true)
	List<MerchantFees> customFindByAll();
	
	@Query(value = "select * from BIPS_MERCHANT_FEES_TABLE where account_no=?1", nativeQuery = true)
	List<MerchantFees> customFindById(String accountNO);
	
}

	