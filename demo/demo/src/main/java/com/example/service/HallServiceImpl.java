package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.Cinema;
import com.example.domain.Hall;
import com.example.repository.HallRepository;

@Service
public class HallServiceImpl implements HallService{

	
	@Autowired
	private HallRepository hallRepository;
	
	@Override
	public List<Hall> getAll() {
		
		return hallRepository.findAll();
	}

	@Override
	public Hall getHallByID(Long id) {
		
		return hallRepository.findById(id);
	}

	@Override
	public boolean registerHall(Hall h) {
		hallRepository.save(h);
		return true;
	}

	@Override
	public List<Hall> getHallsByCinemaID(Long cinemaID) {
		
		return hallRepository.findByCinemaID(cinemaID);
	}

	@Override
	public void save(Hall hall) {
		
		hallRepository.save(hall);
		
	}

	

}
