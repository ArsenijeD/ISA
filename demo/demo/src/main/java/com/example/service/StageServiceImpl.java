package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.Stage;
import com.example.repository.HallRepository;
import com.example.repository.StageRepository;

@Service
public class StageServiceImpl implements StageService {

	
	@Autowired
	private StageRepository stageRepository;
	
	@Override
	public List<Stage> getAll() {
		
		return stageRepository.findAll();
	}

	@Override
	public Stage getStageByID(Long id) {
		
		return stageRepository.findById(id);
	}

	@Override
	public boolean registerStage(Stage s) {
		
		stageRepository.save(s);
		return true;
	}

	@Override
	public void save(Stage stage) {
		
		stageRepository.save(stage);
		
	}

}
