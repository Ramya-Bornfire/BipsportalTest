package com.bornfire.entity;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginSessionRepository extends JpaRepository<LoginSessionEntity,String>{

	@Query(value="select * from login_session WHERE user_id=?1",nativeQuery=true) 
    LoginSessionEntity findByuserID(String MerchantRep);
}