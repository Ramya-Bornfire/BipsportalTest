package com.bornfire.entity;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginSessionHistoryRepo extends JpaRepository<LoginSessionHistoryEntity,String>{

	
}