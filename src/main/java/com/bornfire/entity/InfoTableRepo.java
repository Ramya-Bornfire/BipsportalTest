package com.bornfire.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface InfoTableRepo extends JpaRepository<InfoTableEntity,String> {

	@Query(value = "select * from  INFO_TABLE WHERE screen_id=?1", nativeQuery = true)
	InfoTableEntity findByScreenId(String screen_id);
	
}
