package com.example.service;

import java.util.List;

import com.example.domain.Projection;
import com.example.domain.Seat;


public interface ProjectionService {
	
	List<Projection> getAll();
	
	Projection getProjectionByID(Long id);
	
	boolean registerProjection(Projection p);
	
	List<Projection> getProjectionsByHallID(Long hallID);
	
	void deleteById(Long id);
	
	boolean updateProjection(Projection p);

}
