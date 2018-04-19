package com.example.service;

import java.util.List;

import com.example.domain.Stage;

public interface StageService {
	
	List<Stage> getAll();
	
	Stage getStageByID(Long id);
	
	boolean registerStage(Stage s);
	
	void save(Stage stage);
	
	void deleteById(Long id);

}
