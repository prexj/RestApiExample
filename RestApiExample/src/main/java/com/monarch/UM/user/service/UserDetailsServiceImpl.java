package com.monarch.UM.user.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.monarch.UM.user.model.User;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

	@Autowired
	private UserService userService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userService.findByUserName(username);
		if (user == null)
			throw new UsernameNotFoundException(username);

		authorities.add(new SimpleGrantedAuthority("user"));
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				true, true, true, true,
				authorities);
	}

}
