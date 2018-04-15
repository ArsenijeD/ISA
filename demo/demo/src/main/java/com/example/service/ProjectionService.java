package com.example.service;

import java.util.List;

import com.example.domain.Projection;


public interface ProjectionService {
	
	List<Projection> getAll();
	
	Projection getProjectionByID(Long id);
	
	boolean registerProjection(Projection p);

}
