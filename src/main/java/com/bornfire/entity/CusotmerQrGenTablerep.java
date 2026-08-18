package com.bornfire.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CusotmerQrGenTablerep extends JpaRepository<CustomerQRRequest, String> {

	
}
