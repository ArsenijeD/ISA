package com.example.service;

import java.util.List;


import com.example.domain.Theater;

public interface TheaterService {
	
	List<Theater> getAll();
	
	Theater getTheaterByID(Long id);
	
	boolean registerTheater(Theater t);
	
	Theater getTheaterByName(String name);
	
	boolean updateTheater(Theater t);			// promena admina pozorista
	
	boolean changeTheater(Theater t); 		// izmena pozorista


}
