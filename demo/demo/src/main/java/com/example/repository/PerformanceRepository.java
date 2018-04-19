package com.example.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.Repository;
import com.example.domain.Film;
import com.example.domain.Performance;

public interface PerformanceRepository extends JpaRepository<Performance, Long>{

	List<Performance> findAll();
	Performance findById(Long id);
	Performance findByName(String name);
//	void save(Performance performance);
	
	@Modifying
    @Transactional
    void deleteById(Long id);

	
}
