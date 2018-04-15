package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
