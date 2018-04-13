package com.example.repository;

import java.util.List;

import org.springframework.data.repository.Repository;

import com.example.domain.Projection;

public interface ProjectionRepository extends Repository<Projection, Long>{
	
	List<Projection> findAll();
	Projection findById(Long id);
	void save(Projection projection);

}
