package com.example.repository;

import java.util.List;

import org.springframework.data.repository.Repository;

import com.example.domain.Theater;


public interface TheaterRepository extends Repository<Theater, Long> {
	
	List<Theater> findAll();
	Theater findById(Long id);
	Theater findByName(String name);
	void save(Theater theater);

}
