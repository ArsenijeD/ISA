package com.example.service;

import java.util.List;

import com.example.domain.Presentation;
import com.example.domain.Projection;


public interface PresentationService {
	
	List<Presentation> getAll();
	
	Presentation getPresentationByID(Long id);
	
	boolean registerPresentation(Presentation p);
	
	void deleteById(Long id);
	
	boolean updatePresentation(Presentation p);


}
