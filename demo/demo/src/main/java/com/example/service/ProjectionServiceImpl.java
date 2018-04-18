package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.Projection;
import com.example.repository.HallRepository;
import com.example.repository.ProjectionRepository;

@Service
public class ProjectionServiceImpl implements ProjectionService{

	@Autowired
	private ProjectionRepository projectionRepository;
	
	@Override
	public List<Projection> getAll() {
		
		return projectionRepository.findAll();
	}

	@Override
	public Projection getProjectionByID(Long id) {

		return projectionRepository.findById(id);
	}

	@Override
	public boolean registerProjection(Projection p) {
		projectionRepository.save(p);
		return true;
	}

	@Override
	public List<Projection> getProjectionsByHallID(Long hallID) {
		
		return projectionRepository.findByHallID(hallID);
		
	}

	@Override
	public void deleteById(Long id) {
		projectionRepository.deleteById(id);
		
	}

	@Override
	public boolean updateProjection(Projection p) {
		
		Projection projection = projectionRepository.findOne(p.getId());
		
		projection.setId(p.getId());
		projection.setFilm(p.getFilm());
		projection.setDate(p.getDate());
		projection.setTime(p.getTime());
		projection.setDiscount(p.getDiscount());
		
		projectionRepository.flush();
		
		return true;
	}

}
