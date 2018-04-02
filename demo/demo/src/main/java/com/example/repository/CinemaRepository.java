package com.example.repository;

import java.util.List;

import org.springframework.data.repository.Repository;

import com.example.domain.Cinema;



public interface CinemaRepository extends Repository<Cinema, Long> {

	List<Cinema> findAll();
	Cinema findById(Long id);
	Cinema findByName(String name);
	void save(Cinema cinema);
	
	
}
