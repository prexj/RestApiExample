package com.monarch.UM.jwt.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.monarch.UM.jwt.TokenProvider;
import com.monarch.UM.jwt.models.AuthToken;
import com.monarch.UM.user.model.LoginUser;
import com.monarch.UM.user.model.User;
import com.monarch.UM.user.service.UserService;

@RestController
@RequestMapping("/token")
public class AuthenticationRestController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private TokenProvider jwtTokenUtil;

	@Autowired
	UserService userService;
	
	
	@PostMapping("/generate-token")
	public ResponseEntity<HashMap<Object, Object>> register(@RequestBody LoginUser loginUser)
			throws AuthenticationException {
		try {
			System.out.println("mfmsdfksfksdfkdfk");
			HashMap<Object, Object> hm = new HashMap<>();

			final Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(loginUser.getUsername(), loginUser.getPassword()));
			SecurityContextHolder.getContext().setAuthentication(authentication);

			final String token = jwtTokenUtil.generateToken(authentication);

			User user = userService.findByUserName(loginUser.getUsername());

			hm.put("token", new AuthToken(token));
			hm.put("user", user);
			hm.put("isAdmin", user.isAdmin());
			
			return ResponseEntity.ok(hm);
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
	
	
}
