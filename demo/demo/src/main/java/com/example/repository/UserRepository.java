package com.example.repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.domain.Cinema;
import com.example.domain.User;



public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findOneByEmail(String email);
	Set<User> findByIdIn(Set<Long> ids);
	List<User> findAll();
	User findOneById(Long id);
}
