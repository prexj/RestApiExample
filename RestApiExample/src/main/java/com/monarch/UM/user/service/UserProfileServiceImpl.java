package com.monarch.UM.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.monarch.UM.user.model.User;
import com.monarch.UM.user.model.UserProfile;
import com.monarch.UM.user.repository.UserProfileRepository;
import com.monarch.UM.user.repository.UserRepository;

@Service
public class UserProfileServiceImpl implements UserProfileService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	private UserProfileRepository userProfileRepository;

	@Override
	public UserProfile save(UserProfile userProfile) {
		return userProfileRepository.save(userProfile);
	}

	@Override
	public List<UserProfile> findAll() {
		// TODO Auto-generated method stub
		return userProfileRepository.findAll();
	}

	@Override
	public boolean delete(Long id) {
		// TODO Auto-generated method stub
		try {
			User user = userRepository.findById(id).orElse(null);
			List<UserProfile> profiles = user.getUserProfile();
			for (UserProfile up : profiles) {
				userProfileRepository.deleteUserProfile(up.getId());
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<UserProfile> findByuser_id(Long id) {
		// TODO Auto-generated method stub
		return userProfileRepository.findByuser_id(id);
	}

	@Override
	public UserProfile update(UserProfile userProfile) {
		// TODO Auto-generated method stub
		return userProfileRepository.save(userProfile);
	}
//This is change here as per requirement. The Change is in table field so that under code commented 30/12/2020 
//	@Override
//	public void defaultProfile(UserProfile userProfile) {
//		// TODO Auto-generated method stub
//		List<UserProfile> profiles = userProfileRepository.findAll();
//		for (UserProfile up : profiles) {
//			userProfile.setUser(up.getUser());
//			userProfile.setId(up.getId());
//			userProfileRepository.save(userProfile);
//		}
//	}
//This is change here as per requirement. The Change is in table field so that under code commented 30/12/2020 
//	@Override
//	public UserProfile setDefaultProfile(Long id, Long profileId) {
//		User user = userRepository.findById(id).orElse(null);
//		UserProfile profile = userProfileRepository.findByIdAndUser(profileId,user);//.orElse(null)
//		if (null != profile) {
//			profile.setDefaultProfile(true);
//			userProfileRepository.save(profile);
//			return profile;
//		} else {
//			return null;
//		}
//
//	}
//This is change here as per requirement. The Change is in table field so that under code commented 30/12/2020 
//	@Override
//	public UserProfile getDemoProfile() {
//		return userProfileRepository.findByAdminDemoTrue();
//	}

	@Override
	public UserProfile getUserProfile(Long id, Long profileId) {
		return userProfileRepository.findById(profileId).orElse(null);
	}
//This is change here as per requirement. The Change is in table field so that under code commented 30/12/2020 
//	@Override
//	public UserProfile getDefaultProfile(Long id) {
//		User user = userRepository.findById(id).orElse(null);
//		if (null != user) {
//			return userProfileRepository.findByUserAndDefaultProfileTrue(user);
//		} else {
//			return null;
//		}
//	}

	@Override
	public boolean delete(Long id, Long profileId) {
		try {
			User user = userRepository.findById(id).orElse(null);
			List<UserProfile> profiles = user.getUserProfile();
			for (UserProfile up : profiles) {
				if (profileId == up.getId()) {
					userProfileRepository.deleteUserProfile(up.getId());
					return true;
				}
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
