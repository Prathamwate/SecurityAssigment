package com.pratham.SecurityPratice.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pratham.SecurityPratice.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{

	Optional<User> findByName(String username);

}
