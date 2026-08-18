package com.bornfire.entity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PosterRepository extends JpaRepository<PosterEntity, String> {

	@Query(value = "select * from  BIPS_MERCHANT_POSTER_TABLE WHERE merchant_id=?", nativeQuery = true)
	List<PosterEntity> findByAll(String merchant);
}

