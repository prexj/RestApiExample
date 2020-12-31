package com.monarch.UM.user.service;

import java.util.List;

import com.monarch.UM.user.model.User;

public interface UserService {
	public User save(User user);

	public User findById(Long id);

	public List<User> findAll();

	public boolean delete(Long id);

	public User update(User user);

	public User findByUserName(String username);

	public User validUser(String username, String password);

	public User findByRfId(String rfid);

	public User getUserByFingerPrintId(String fpid);

}
