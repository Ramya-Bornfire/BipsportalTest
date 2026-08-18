package com.bornfire.entity;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserProfileRep extends CrudRepository<UserProfile, String> {

	public Optional<UserProfile> findByusername(String userName);

	@Query(value = "select * from BIPS_USER_PROFILE where USER_ID= ?1", nativeQuery = true)
	UserProfile findByIdCustom(String Id);

	@Query(value = "select * from BIPS_USER_PROFILE where NVL(DEL_FLG,1) <> 'Y'", nativeQuery = true)
	List<UserProfile> findByAll(String Id);

	@Query(value = "select count(*) from BIPS_USER_PROFILE where del_flg='N'  and user_id=?1 ", nativeQuery = true)
	String getusercount(String custId);

	@Query(value = "select * from BIPS_USER_PROFILE where USER_ID =?1 and del_flg='N' ", nativeQuery = true)
	UserProfile getEmployeedetails(String userid);

	@Query(value = "SELECT * FROM BIPS_USER_PROFILE WHERE USER_ID=?1 ", nativeQuery = true)
	UserProfile getroles(String userid);

	@Query(value = "SELECT * FROM BIPS_USER_PROFILE WHERE USER_ID=?1 AND del_flg='N' UNION ALL SELECT * FROM BIPS_USER_PROFILE_MOD_TABLE WHERE USER_ID=?1 AND del_flg='N'", nativeQuery = true)
	List<UserProfile> getBlobImg(String userid);

}
