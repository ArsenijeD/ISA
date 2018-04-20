package com.example.service;

import java.util.List;

import com.example.domain.Ticket;
import com.example.domain.User;

public interface TicketService {
	
	List<Ticket> getAll();
	
	Ticket getTicketByID(Long id);
	
	boolean registerTicket(Ticket t);
	
	void save(Ticket ticket);
	
	void deleteById(Long id);
	
	boolean changeToFastReserveTicket(Ticket t);
	
	boolean reserveTicket(User u, Ticket t);
	
	List<Ticket> getUnReservedTickets(boolean reserved);
	
//	void assignUserToTicket(User u, Ticket t);
	
	

}
