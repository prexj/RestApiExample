package com.monarch.UM.user.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.monarch.UM.user.model.User;
import com.monarch.UM.user.model.UserProfile;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {

	List<UserProfile> findByuser_id(Long id);

	@Modifying
	@Query("delete from UserProfile where id = :id")
	@Transactional
	void deleteUserProfile(@Param("id") Long id);
	//This is change here as per requirement. The Change is in table field so that under code commented 30/12/2020 
	//UserProfile findByAdminDemoTrue();
	//This is change here as per requirement. The Change is in table field so that under code commented 30/12/2020 
	//UserProfile findByUserAndDefaultProfileTrue(User user);

	UserProfile findByIdAndUser(Long profileId, User user);
}
