package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.Hall;
import com.example.domain.Seat;
import com.example.repository.SeatRepository;

@Service
public class SeatServiceImpl implements SeatService{

	
	@Autowired
	private SeatRepository seatRepository;
	
	@Override
	public List<Seat> getAll() {

		return seatRepository.findAll();
	}

	@Override
	public Seat getSeatByID(Long id) {
		
		return seatRepository.findById(id);
	}

	@Override
	public boolean registerSeat(Seat s) {
		seatRepository.save(s);
		return true;
	}

	@Override
	public List<Seat> getSeatsByHallID(Long hallID) {
		
		return seatRepository.findByHallID(hallID);
	}



}
