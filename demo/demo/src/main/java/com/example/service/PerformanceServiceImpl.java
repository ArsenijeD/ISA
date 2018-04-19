package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.Film;
import com.example.domain.Performance;
import com.example.repository.PerformanceRepository;

@Service
public class PerformanceServiceImpl implements PerformanceService{

	@Autowired
	private PerformanceRepository performanceRepository;
	
	
	@Override
	public List<Performance> getAll() {
		
		return performanceRepository.findAll();
	}

	@Override
	public Performance getPerformanceByID(Long id) {
		// TODO Auto-generated method stub
		return performanceRepository.findById(id);
	}

	@Override
	public boolean registerPerformance(Performance p) {
		
		performanceRepository.save(p);
		
		return true;
	
	}

	@Override
	public Performance getPerformanceByName(String name) {
		// TODO Auto-generated method stub
		return performanceRepository.findByName(name);
	}
	
	

	@Override
	public boolean updatePerformance(Performance p) {
		Performance performance = performanceRepository.findOne(p.getId());
		
		performance.setId(p.getId());
		performance.setName(p.getName());
		performance.setDescription(p.getDescription());
		performance.setDuration(p.getDuration());
		performance.setAverageRating(p.getAverageRating());
		performance.setGenre(p.getGenre());
		performance.setPrice(p.getPrice());
		
		performanceRepository.flush();
		
		return true;
	}

	@Override
	public void deleteById(Long id) {
		
		performanceRepository.deleteById(id);
		
	}

}
