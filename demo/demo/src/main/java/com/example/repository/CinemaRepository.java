package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

import com.example.domain.Cinema;
import com.example.domain.User;



public interface CinemaRepository extends JpaRepository<Cinema, Long> {

	Cinema findOneById(Long id);
	Cinema findByName(String name);
	//void save(Cinema cinema);
	List<Cinema> findAll();
	
	
	
	
	
	
	
}
