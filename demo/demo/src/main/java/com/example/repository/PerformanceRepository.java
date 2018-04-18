package com.example.repository;

import java.util.List;

import org.springframework.data.repository.Repository;

import com.example.domain.Film;
import com.example.domain.Performance;

public interface PerformanceRepository extends Repository<Performance, Long>{

	List<Performance> findAll();
	Performance findById(Long id);
	Performance findByName(String name);
	void save(Performance performance);
	
}
