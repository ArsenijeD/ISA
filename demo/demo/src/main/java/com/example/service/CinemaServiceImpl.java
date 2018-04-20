package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.Cinema;
import com.example.repository.CinemaRepository;


@Service
public class CinemaServiceImpl implements CinemaService {

	@Autowired
	private CinemaRepository cinemaRepository;
	
	@Override
	public List<Cinema> getAll() {
		
		return cinemaRepository.findAll();
		
	}

	@Override
	public Cinema getCinemaByID(Long id) {
		
		return cinemaRepository.findOneById(id);
		
	}

	@Override
	public boolean registerCinema(Cinema c) {
		
		cinemaRepository.save(c);
		return true;
		
		
	}

	@Override
	public Cinema getCinemaByName(String name) {
		
		return cinemaRepository.findByName(name);
	}

	@Override
	public boolean updateCinema(Cinema c) {			// izmena admina bioskopa
		
		Cinema cinema = cinemaRepository.findOne(c.getId());
		cinema.setAdmins(c.getAdmins());
		cinema.setId(c.getId());
		
		
		cinemaRepository.flush();
		return true;
	}

	@Override
	public boolean changeCinema(Cinema c) {
		
		Cinema cinema = cinemaRepository.findOne(c.getId());
		cinema.setId(c.getId());
		cinema.setName(c.getName());
		cinema.setAdress(c.getAdress());
		cinema.setDescription(c.getDescription());
		
		if(c.getHalls()!=null) {
			cinema.setHalls(c.getHalls());
			System.out.println("Zamenio listu sala sa novom listom sala!");
		}
		
		cinemaRepository.flush();
		
		
		return true;
	}

}
