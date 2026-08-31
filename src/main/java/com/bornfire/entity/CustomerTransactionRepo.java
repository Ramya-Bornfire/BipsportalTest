package com.bornfire.entity;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerTransactionRepo extends JpaRepository<CustomerTransactionEntity, String> {

	@Query(value="select * from customer_transaction_details WHERE customer_id=?1 AND customer_reference_label=?2",nativeQuery=true)
	CustomerTransactionEntity getDetails(String customer_id,String reference_number);
	
	@Query(value="select * from customer_transaction_details WHERE (merchant_reference_label=?1 OR customer_reference_label=?1) AND ROWNUM = 1", nativeQuery=true)
	CustomerTransactionEntity getByReferenceNumber(String reference_number);
	
	
	//insert the query+method, then close the interface
	@Query(value="select * from customer_transaction_details WHERE user_id=?1 AND merchant_id=?2 ORDER BY entry_time DESC", nativeQuery=true)
	List<CustomerTransactionEntity> getTransactionListForUser(String user_id, String merchant_id);

}

