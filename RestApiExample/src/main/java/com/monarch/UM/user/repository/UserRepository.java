package com.monarch.UM.user.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.monarch.UM.user.model.User;

public interface UserRepository extends JpaRepository<User,Long>,CrudRepository<User, Long>
{
		User findByUsername(String username);

		Optional<User> findByRfId(String rfid);

		Optional<User> getUserByFingerPrintId(String fpid);
		
	
	
}
