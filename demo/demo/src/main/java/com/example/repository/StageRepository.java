package com.example.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.Repository;

import com.example.domain.Stage;

public interface StageRepository extends JpaRepository<Stage, Long>{
	
	public List<Stage> findAll();
	public Stage findById(Long id);
	
//	public void save(Stage stage);
	
	@Modifying
    @Transactional
    void deleteById(Long id);

}
