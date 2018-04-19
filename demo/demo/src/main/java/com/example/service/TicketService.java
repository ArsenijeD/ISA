package com.example.service;

import java.util.List;

import com.example.domain.Ticket;

public interface TicketService {
	
	List<Ticket> getAll();
	
	Ticket getTicketByID(Long id);
	
	boolean registerTicket(Ticket t);
	
	void save(Ticket ticket);
	
	void deleteById(Long id);
	
	boolean changeToFastReserveTicket(Ticket t);
	
	boolean reserveTicket(Ticket t);

}
