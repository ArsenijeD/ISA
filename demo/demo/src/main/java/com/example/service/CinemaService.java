package com.example.service;

import java.util.List;

import com.example.domain.Cinema;


public interface CinemaService {

	
	List<Cinema> getAll();
	
	Cinema getCinemaByID(Long id);
	
	boolean registerCinema(Cinema c);
	
	Cinema getCinemaByName(String name);
		
	boolean updateCinema(Cinema c);			// promena admina bioskopu
	
	boolean changeCinema(Cinema c); 		// izmena bioskopa
}
