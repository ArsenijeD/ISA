package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

import com.example.domain.Stage;

public interface StageRepository extends JpaRepository<Stage, Long>{
	
	public List<Stage> findAll();
	public Stage findById(Long id);
	
//	public void save(Stage stage);

}
