package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.Ticket;
import com.example.domain.User;
import com.example.repository.StageRepository;
import com.example.repository.TicketRepository;


@Service
public class TicketServiceImpl implements TicketService{

	
	@Autowired
	private TicketRepository ticketRepository;
	
	@Override
	public List<Ticket> getAll() {
		
		return ticketRepository.findAll();
	}

	@Override
	public Ticket getTicketByID(Long id) {
	
		return ticketRepository.findById(id);
	}

	@Override
	public boolean registerTicket(Ticket t) {
		
		ticketRepository.save(t);
		return true;
	}

	@Override
	public void save(Ticket ticket) {
		
		ticketRepository.save(ticket);
		
	}

	@Override
	public void deleteById(Long id) {
		
		ticketRepository.deleteById(id);
		
	}

	@Override
	public boolean changeToFastReserveTicket(Ticket t) {		// kreiranje karata za brzu rezervaciju
		
		Ticket ticket = ticketRepository.findOne(t.getId());
		ticket.setId(t.getId());
		ticket.setForFastReservation(t.isForFastReservation());
		
		ticketRepository.flush();
		
		return true;
	}

	@Override
	public boolean reserveTicket(User u, Ticket t) {
		
		Ticket ticket = ticketRepository.findOne(t.getId());
		ticket.setId(t.getId());
		ticket.setReserved(t.isReserved());
		ticket.setUser(u);
		
		ticketRepository.flush();
		
		return true;
	}

	@Override
	public List<Ticket> getUnReservedTickets(boolean reserved) {
		
		return ticketRepository.getUnReservedTickets(reserved);
	}

//	@Override
//	public void assignUserToTicket(User u, Ticket t) {
//		
//		Ticket ticket = ticketRepository.findOne(t.getId());
//		ticket.setId(t.getId());
//		ticket.setUser(u);
//		
//		ticketRepository.flush();
//		
//	}

}
