package com.bornfire.entity;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReferenceCodeRep extends CrudRepository<ReferenceCodeEntity,String> {
	
	@Query(value = "select * from ref_master ORDER BY ref_type", nativeQuery = true)
	List<ReferenceCodeEntity> getreflist();
	
	@Query(value = "select * from ref_master where ref_id =?1", nativeQuery = true)
	ReferenceCodeEntity getrefview(String ref_id);
	
	@Query(value = "SELECT * from ref_master WHERE ref_type='MM01' and del_flg='N' ", nativeQuery = true)
	List<ReferenceCodeEntity> getmerchantlist();
	
	@Query(value = "SELECT ref_id_desc from ref_master WHERE ref_type=?1 and del_flg='N' ", nativeQuery = true)
	List<String> getReferenceList(String ref_type);

}
