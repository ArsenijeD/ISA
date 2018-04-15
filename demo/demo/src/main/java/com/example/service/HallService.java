package com.example.service;

import java.util.List;

import com.example.domain.Cinema;
import com.example.domain.Hall;

public interface HallService {
	
	List<Hall> getAll();
	
	Hall getHallByID(Long id);
	
	boolean registerHall(Hall h);
	
	List<Hall> getHallsByCinemaID(Long cinemaID);
	

}
