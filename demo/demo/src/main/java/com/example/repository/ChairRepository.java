package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.domain.Chair;


public interface ChairRepository extends JpaRepository<Chair, Long>{
	
	public List<Chair> findAll();
	public Chair findById(Long id);
//	public void save(Seat seat);

}
