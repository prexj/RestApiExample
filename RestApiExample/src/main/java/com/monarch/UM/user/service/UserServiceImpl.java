package com.monarch.UM.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.monarch.UM.user.model.User;
import com.monarch.UM.user.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public User save(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}

	@Override
	public User update(User user) {
		User old = userRepository.findById(user.getId()).orElse(null);
		user.setAdmin(old.isAdmin());
		if (old.getPassword().equals(user.getPassword())) {
			return userRepository.save(user);
		} else {
			user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
			return userRepository.save(user);
		}
	}

	@Override
	public User findById(Long id) {
		return userRepository.findById(id).orElse(null);
	}

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public boolean delete(Long id) {
		try {
			userRepository.deleteById(id);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	@Override
	public User findByUserName(String username) {
		// TODO Auto-generated method stub
		return userRepository.findByUsername(username);
	}

	@Override
	public User validUser(String username, String password) {

		try {
			User user = userRepository.findByUsername(username);

			if (user != null) {
				boolean result = bCryptPasswordEncoder.matches(password, user.getPassword());
				if (result == true) {
					return user;
				} else {
					return null;
				}
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public User findByRfId(String rfid) {
		return userRepository.findByRfId(rfid).orElse(null);
	}

	@Override
	public User getUserByFingerPrintId(String fpid) {
		return userRepository.getUserByFingerPrintId(fpid).orElse(null);
	}

}
