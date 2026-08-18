package com.bornfire.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CustomerDetailsRepository extends JpaRepository<CustomerDetailsEntity, String> {
   // Optional<CustomerDetailsEntity> findByMobileNumber(String mobileNumber);
	//@Query(value = "SELECT * FROM customer_details WHERE mobile_number = ?1", nativeQuery = true)
	//Optional<CustomerDetailsEntity> findByMobileNumber(String mobileNumber);
	@Query(value = "select mobile_number from customer_details where mobile_number=?1", nativeQuery = true)
	String findByMobileNumber(String mobileNumber);


}
