package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.Presentation;
import com.example.domain.Projection;
import com.example.repository.PresentationRepository;

@Service
public class PresentationServiceImpl implements PresentationService {

	
	@Autowired
	private PresentationRepository presentationRepository;
	
	@Override
	public List<Presentation> getAll() {
		
		return presentationRepository.findAll();
	}

	@Override
	public Presentation getPresentationByID(Long id) {
		
		return presentationRepository.findById(id);
		
	}

	@Override
	public boolean registerPresentation(Presentation p) {
		
		presentationRepository.save(p);
		return true;
	}

	@Override
	public void deleteById(Long id) {
		
		presentationRepository.deleteById(id);
		
	}

	@Override
	public boolean updatePresentation(Presentation p) {
		
		Presentation presentation = presentationRepository.findOne(p.getId());
		
		presentation.setId(p.getId());
		presentation.setPerformance(p.getPerformance());
		presentation.setDate(p.getDate());
		presentation.setTime(p.getTime());
		presentation.setDiscount(p.getDiscount());
		
		presentationRepository.flush();
		
		return true;
		
	}

}
