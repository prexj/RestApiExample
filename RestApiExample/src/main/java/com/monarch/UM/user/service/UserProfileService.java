package com.monarch.UM.user.service;

import java.util.List;

import com.monarch.UM.user.model.UserProfile;

public interface UserProfileService {
	public UserProfile save(UserProfile userProfile);

	public List<UserProfile> findByuser_id(Long id);

	public List<UserProfile> findAll();

	public boolean delete(Long id);

	public UserProfile update(UserProfile userProfile);
//This is change here as per requirement. The Change is in table field so that under code commented 30/12/2020 
//	public void defaultProfile(UserProfile userProfile);
//This is change here as per requirement. The Change is in table field so that under code commented 30/12/2020 
//	public UserProfile setDefaultProfile(Long id, Long profileId);
//This is change here as per requirement. The Change is in table field so that under code commented 30/12/2020 
//	public UserProfile getDemoProfile();
	
	public UserProfile getUserProfile(Long id, Long profileId);
//This is change here as per requirement. The Change is in table field so that under code commented 30/12/2020 
//	public UserProfile getDefaultProfile(Long id);

	public boolean delete(Long id, Long profileId);

}
