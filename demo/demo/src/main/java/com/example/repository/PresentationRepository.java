package com.example.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.Repository;

import com.example.domain.Presentation;


public interface PresentationRepository  extends JpaRepository<Presentation, Long>{
	
	List<Presentation> findAll();
	Presentation findById(Long id);
//	void save(Presentation presentation);
	
	@Modifying
    @Transactional
    void deleteById(Long id);

}
