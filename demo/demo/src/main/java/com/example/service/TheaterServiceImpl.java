package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.Cinema;
import com.example.domain.Theater;

import com.example.repository.TheaterRepository;


@Service
public class TheaterServiceImpl implements TheaterService{

	
	@Autowired
	private TheaterRepository theaterRepository;
	
	@Override
	public List<Theater> getAll() {
		
		return theaterRepository.findAll();
	}

	@Override
	public Theater getTheaterByID(Long id) {
		
		return theaterRepository.findById(id);
	}

	@Override
	public boolean registerTheater(Theater t) {
		
		theaterRepository.save(t);
		return true;
	}

	@Override
	public Theater getTheaterByName(String name) {
		
		return theaterRepository.findByName(name);
	}
	
	

	@Override
	public boolean updateTheater(Theater t) {			// izmena admina pozorista
		
		Theater theater = theaterRepository.findOne(t.getId());
		theater.setAdmins(t.getAdmins());
		theater.setId(t.getId());
		
		
		theaterRepository.flush();
		return true;
		
	}

	@Override
	public boolean changeTheater(Theater t) {
		
		Theater theater = theaterRepository.findOne(t.getId());
		theater.setId(t.getId());
		theater.setName(t.getName());
		theater.setAdress(t.getAdress());
		theater.setDescription(t.getDescription());
		
		if(t.getStages()!=null) {
			theater.setStages(t.getStages());
			System.out.println("Zamenio listu sala sa novom listom sala!");
		}
		
		theaterRepository.flush();
		
		
		return true;
		
	}

}
