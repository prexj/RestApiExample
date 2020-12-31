package com.monarch.UM.user.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.monarch.UM.user.model.User;
import com.monarch.UM.user.model.UserProfile;
import com.monarch.UM.user.service.UserProfileService;
import com.monarch.UM.user.service.UserService;

@RequestMapping("/user")
@RestController
public class UserRestController {
	@Autowired
	private UserService userService;

	@Autowired
	private UserProfileService userProfileService;

	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@PostMapping
	public ResponseEntity<HashMap<String, Object>> saveUser(@RequestBody User user) {
		System.out.println("in save user");
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			User user1 = userService.findByUserName(user.getUsername());
			if (user1 == null) {
				userService.save(user);
				map.put("user", user);
				map.put("msg", "user is saved");
			} else {
				map.put("msg", "username  Already Exist! Try Again");
			}
			return new ResponseEntity<>(map, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			return null;
		}
	}

	@PostMapping("/{id}/udpate/rfid/{rfid}")
	public ResponseEntity<HashMap<String, Object>> updateRfid(@PathVariable("id") Long id,
			@PathVariable("rfid") String rfid) {
		System.out.println("in update user rfid");
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			User user1 = userService.findById(id);
			if (user1 != null) {
				user1.setRfId(rfid);
				userService.save(user1);
				map.put("msg", "RFID Updated");
			} else {
				map.put("msg", "Resource not found");
			}
			return new ResponseEntity<>(map, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			return null;
		}
	}

	@PostMapping("/{id}/udpate/finger-print/{fpid}")
	public ResponseEntity<HashMap<String, Object>> updateFpid(@PathVariable("id") Long id,
			@PathVariable("fpid") String fpid) {
		System.out.println("in update user fpid");
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			User user1 = userService.findById(id);
			if (user1 != null) {
				user1.setFingerPrintId(fpid);
				userService.save(user1);
				map.put("msg", "FPID Updated");
			} else {
				map.put("msg", "Resource not found");
			}
			return new ResponseEntity<>(map, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			return null;
		}
	}

	@PostMapping("/{id}/makeAdmin")
	public ResponseEntity<HashMap<String, Object>> makeAdmin(@PathVariable("id") Long id) {
		System.out.println("in make Admin");
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			User user = userService.findById(id);
			if (user == null) {
				map.put("msg", "user does not exist");
			} else {
				user.setAdmin(true);
				userService.save(user);
				map.put("msg", "user is changed to admin");
			}
			return new ResponseEntity<>(map, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			return null;
		}

	}

	@PostMapping("/{id}/removeAdmin")
	public ResponseEntity<HashMap<String, Object>> removeAdmin(@PathVariable("id") Long id) {
		System.out.println("in remove Admin");
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			User user = userService.findById(id);
			if (user == null) {
				map.put("msg", "user does not exist");
			} else {
				user.setAdmin(false);
				userService.save(user);
				map.put("msg", "admin is changed to user");
			}
			return new ResponseEntity<>(map, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			return null;
		}

	}

	@PostMapping("/login")
	public Map<String, Object> validUser(@RequestParam(value = "username") String username,
			@RequestParam(value = "password") String password) {
		Map<String, Object> hm = new HashMap<>();

		User user = userService.validUser(username, password);
		if (user != null) {
			hm.put("isAdmin", user.isAdmin());
			hm.put("user", user);
		}
		return hm;
	}

	@PostMapping("/change/password/{id}")
	public ResponseEntity<HashMap<String, Object>> changePassword(@PathVariable(value = "id") long id,
			@RequestParam(value = "oldPassword") String oldPassword,
			@RequestParam(value = "newPassword") String newPassword) {
		System.out.println("in change password");
		HashMap<String, Object> hm = new HashMap<>();
		try {
			User user = userService.findById(id);
			boolean matches = bCryptPasswordEncoder.matches(oldPassword, user.getPassword());

			if (matches == true) {
				user.setPassword(newPassword);
				userService.save(user);
				hm.put("success", true);
			} else {
				hm.put("success", false);
			}

			return new ResponseEntity<>(hm, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@GetMapping("/{id}")
	public User getUser(@PathVariable("id") Long id) {
		return userService.findById(id);
	}

	@GetMapping("/username")
	public ResponseEntity<HashMap<String, Object>> getUserByUserName(
			@RequestParam(value = "userName") String userName) {
		HashMap<String, Object> map = new HashMap<>();
		try {
			User user1 = userService.findByUserName(userName);
			if (user1 != null) {
				map.put("user", user1);
				// map.put("msg", "user is saved");
			} else {
				map.put("msg", "Sorry, Username does not Exist in our application! Try Again with proper username");
			}
			return new ResponseEntity<>(map, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			return null;
		}
	}

	@GetMapping("/rfid/{rfid}")
	public User getUserByRfid(@PathVariable("rfid") String rfid) {
		return userService.findByRfId(rfid);
	}

	@GetMapping("/finger-print/{fpid}")
	public User getUserByFingerPrintId(@PathVariable("fpid") String fpid) {
		return userService.getUserByFingerPrintId(fpid);
	}

	@GetMapping()
	public List<User> getAllUsers() {
		return userService.findAll();
	}

	@PutMapping("/{id}")
	public ResponseEntity<HashMap<String, Object>> UpdateUser(@PathVariable("id") Long id, @RequestBody User user) {
		User userFind = userService.findById(id);
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			if (userFind != null) {
				User afterUpdateUser = userService.update(user);
				map.put("user", afterUpdateUser);
				map.put("msg", "user is Updated");
			} else {
				map.put("msg", "Sorry, This user does not Exist in our application ! Try Again with Proper userId");
			}
			return new ResponseEntity<>(map, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			return null;
		}
	}

	@DeleteMapping("/{id}")
	public boolean deleteUser(@PathVariable("id") Long id) {
		return userService.delete(id);
	}

	@PostMapping("/{id}/profile")
	public ResponseEntity<HashMap<String, Object>> saveUserProfile(@PathVariable("id") Long id,
			@RequestBody UserProfile userProfile) {
		System.out.println("in save user");
		HashMap<String, Object> map = new HashMap<String, Object>();
		User user = userService.findById(id);
		try {
			if (user == null) {
				map.put("msg", "user does not exist");
			} else {
				userProfile.setUser(user);

				userProfileService.save(userProfile);
				map.put("userProfile", userProfile);
				map.put("msg", "userProfile is saved");

				return new ResponseEntity<>(map, HttpStatus.OK);
			}
			return new ResponseEntity<>(map, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	@PutMapping("/{id}/profile/{profileId}")
	public ResponseEntity<HashMap<String, Object>> updateUserProfile(@PathVariable("id") Long id,
			@PathVariable("profileId") Long profileId,@RequestBody UserProfile userProfile) {
		System.out.println("in save user");
		HashMap<String, Object> map = new HashMap<String, Object>();
		User user = userService.findById(id);
		try {
			if (user == null) {
				map.put("msg", "user does not exist");
			} else {
				userProfile.setUser(user);
				userProfile.setId(profileId);
				userProfileService.save(userProfile);
				map.put("userProfile", userProfile);
				map.put("msg", "userProfile is updated");

				return new ResponseEntity<>(map, HttpStatus.OK);
			}
			return new ResponseEntity<>(map, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			return null;
		}
	}
//This is change here as per requirement. The Change is in table field so that under code commented 30/12/2020 
//	@PostMapping("/{id}/profile/{profileId}")
//	public UserProfile setDefaultProfile(@PathVariable("id") Long id, @PathVariable("profileId") Long profileId) {
//		return userProfileService.setDefaultProfile(id, profileId);
//	}

//	@PostMapping("/profile/demo")
//	public UserProfile setDemoProfile(@RequestBody UserProfile userProfile) {
//		try {
//			userProfile.setAdminDemo(true);
//			userProfile.setDefaultProfile(true);
//			return userProfileService.save(userProfile);
//		} catch (Exception e) {
//			e.printStackTrace();
//			System.out.println(e.getMessage());
//			return null;
//		}
//	}

	@GetMapping("/{id}/profile/{profileId}")
	public UserProfile getUserProfile(@PathVariable("id") Long id, @PathVariable("profileId") Long profileId) {
		return userProfileService.getUserProfile(id, profileId);
	}
//This is change here as per requirement. The Change is in table field so that under code commented 30/12/2020 
//	@GetMapping("/{id}/profile/default")
//	public UserProfile getDefaultProfile(@PathVariable("id") Long id) {
//		return userProfileService.getDefaultProfile(id);
//	}

	@GetMapping("/{id}/profile")
	public List<UserProfile> getUserProfile(@PathVariable("id") Long id) {
		return userProfileService.findByuser_id(id);
	}
//This is change here as per requirement. The Change is in table field so that under code commented 30/12/2020 
//	@GetMapping("/demo-profile")
//	public UserProfile getDemoProfile() {
//		return userProfileService.getDemoProfile();
//	}

	@DeleteMapping("/{id}/profile")
	public boolean deleteUserProfile(@PathVariable("id") Long id) {
		return userProfileService.delete(id);
	}

	@DeleteMapping("/{id}/profile/{profileId}")
	public boolean deleteUserProfileById(@PathVariable("id") Long id, @PathVariable("profileId") Long profileId) {
		return userProfileService.delete(id, profileId);
	}
//This is change here as per requirement. The Change is in table field so that under code commented 30/12/2020 
//	@PutMapping("/defaultProfile")
//	public void defaultProfile(@RequestBody UserProfile userProfile) {
//		userProfileService.defaultProfile(userProfile);
//	}
}
