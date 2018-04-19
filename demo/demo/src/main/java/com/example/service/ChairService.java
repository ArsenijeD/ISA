package com.example.service;

import java.util.List;

import com.example.domain.Chair;


public interface ChairService {
	
	List<Chair> getAll();
	
	Chair getChairByID(Long id);
	
	boolean registerChair(Chair c);

}
