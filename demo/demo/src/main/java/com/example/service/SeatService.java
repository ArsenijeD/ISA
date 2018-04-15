package com.example.service;

import java.util.List;

import com.example.domain.Hall;
import com.example.domain.Seat;

public interface SeatService {
	
	List<Seat> getAll();
	
	Seat getSeatByID(Long id);
	
	boolean registerSeat(Seat s);
	
	List<Seat> getSeatsByHallID(Long hallID);

}
