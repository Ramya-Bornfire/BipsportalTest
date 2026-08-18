package com.bornfire.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerTransactionRepo extends JpaRepository<CustomerTransactionEntity, String> {

	@Query(value="select * from customer_transaction_details WHERE customer_id=?1 AND customer_reference_label=?2",nativeQuery=true)
	CustomerTransactionEntity getDetails(String customer_id,String reference_number);
	
}
