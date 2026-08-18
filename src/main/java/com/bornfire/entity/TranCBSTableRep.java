package com.bornfire.entity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TranCBSTableRep extends JpaRepository<TranCBSTable, String> {

	@Query(value = "select * from BIPS_TRAN_CBS_TABLE where sequence_unique_id = ?1", nativeQuery = true)
	List<TranCBSTable> findBySeqUniqueIDCustom(String seqUniqueID);
}
