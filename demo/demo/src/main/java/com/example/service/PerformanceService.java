package com.example.service;

import java.util.List;

import com.example.domain.Performance;

public interface PerformanceService {
	
	List<Performance> getAll();
	
	Performance getPerformanceByID(Long id);
	
	boolean registerPerformance(Performance p);
	
	Performance getPerformanceByName(String name);
	
	boolean updatePerformance(Performance p);
	
	void deleteById(Long id);

}
