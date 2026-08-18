package com.bornfire.entity;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
@Transactional
public interface TranIPSTableRep extends JpaRepository<TranIPSTable, TranIPSTableID> {

	@Query(value = "select * from BIPS_TRAN_IPS_TABLE where msg_id=?1", nativeQuery = true)
	List<TranIPSTable> findByIdCustom(String string);

	@Query(value = "select * from BIPS_TRAN_IPS_TABLE where sequence_unique_id=?1 and msg_id=?2 and msg_sub_type=?3", nativeQuery = true)
	List<TranIPSTable> updateAckStatus(String sequenceUniqueID, String msgID, String msg_sub_type);

	@Query(value = "select * from BIPS_TRAN_IPS_TABLE where sequence_unique_id=?1 and msg_code='pacs.002.001.10' and msg_sub_type='I'", nativeQuery = true)
	List<TranIPSTable> getCustomResult(String seqUniqueID008);
	
	@Query(value = "select * from BIPS_TRAN_IPS_TABLE where ((sequence_unique_id=?2 and msg_code='pain.002.001.10' and RESPONSE_STATUS='RJCT')or (sequence_unique_id=?1 and msg_code='pacs.002.001.10')) and msg_sub_type='I' and RESPONSE_STATUS='RJCT'", nativeQuery = true)
	List<TranIPSTable> getCustomResult1(String seqUniqueID008,String painSeqID);

	@Query(value = "select * from BIPS_TRAN_IPS_TABLE where sequence_unique_id=?1 and msg_code='pain.002.001.10' and msg_sub_type='I'", nativeQuery = true)
	List<TranIPSTable> getCustomResultPain002(String seqUniqueID);

	@Modifying
	@Query(value = "update BIPS_TRAN_IPS_TABLE set ack_status=?4,net_mir=?5,user_ref=?6 where sequence_unique_id=?1 and msg_id=?2 and msg_sub_type=?3", nativeQuery = true)
	void updateAckStatus1(String sequenceUniqueID, String msgID, String msg_sub_type, String ack_status, String net_mir,
			String userRef);


}

