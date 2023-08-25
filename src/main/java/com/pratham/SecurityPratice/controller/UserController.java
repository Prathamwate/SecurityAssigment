package com.pratham.SecurityPratice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.pratham.SecurityPratice.JwtToken.JwtService;
import com.pratham.SecurityPratice.model.JwtRequest;
import com.pratham.SecurityPratice.model.User;
import com.pratham.SecurityPratice.service.UserService;

@RestController
@RequestMapping("user")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private JwtService jwtService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@GetMapping("/")
	public String home() {
		return "Welcome";
	}

	@PostMapping("new")
	public String saveUser(@RequestBody User user) {
		return userService.saveUser(user);
	}

	@GetMapping("allUser")
	@PreAuthorize("hasAuthority('ADMIN')")
	public List<User> getAll() {
		return userService.getAll();
	}

	@GetMapping("id/{id}")
	@PreAuthorize("hasAuthority('USER')")
	public User getUserById(@PathVariable int id) {
		return userService.getUserById(id);
	}

	@PostMapping("/authenticate")
	public String authenticateAndGetToken(@RequestBody JwtRequest jwtRequest) throws AuthenticationException {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword()));
		if (authentication.isAuthenticated()) {
			return jwtService.genrateToken(jwtRequest.getUsername());
		} else {
			throw new UsernameNotFoundException("User Not Found");
		}
	}
	
	@DeleteMapping("/delete/{id}")
	public String deleteUser(@PathVariable int id) {
		return userService.deleteUser(id);
		
	}
}
