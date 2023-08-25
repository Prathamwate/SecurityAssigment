package com.pratham.SecurityPratice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.pratham.SecurityPratice.Repository.UserRepository;
import com.pratham.SecurityPratice.model.User;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;

	public String saveUser(User user) {
	 user.setPassword(passwordEncoder.encode(user.getPassword()));
	 userRepository.save(user);
	 return "User Added";
	}

	public List<User> getAll() {
		return userRepository.findAll();
	}

	public User getUserById(int id) {
		User user= userRepository.findById(id).get();
		return user;
	}

	public String deleteUser(int id) {
		try {
			User user=getUserById(id);
			userRepository.deleteById(id);
			return "Deleted";
		} catch (Exception e) {
			return "User Not Found";
		}
	}
}
