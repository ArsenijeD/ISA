package com.example.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.Ticket;
import com.example.domain.User;
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
	@Transactional
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

	@Override
	public List<Ticket> getTicketByUser(Long userId) {
		return ticketRepository.getTicketByUser(userId);
	}

	@Override
	public Date convertDateFromString(String dateString) {
		// TODO Auto-generated method stub
		System.out.println("Datum pre izmene: " + dateString);		
		String[] splittedDate = dateString.split("-");
		if(splittedDate[0].length()==1) {
			splittedDate[0] = "0"+splittedDate[0];
		}
		if(splittedDate[1].length()==1) {
			splittedDate[1] = "0"+splittedDate[1];
		}
		String newDate = splittedDate[0] + "-" + splittedDate[1] + "-" + splittedDate[2];
		System.out.println("Datum posle izmene: " + newDate);
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		Date today = new Date();
		

		try {
            Date date = formatter.parse(newDate);
            return date;
           
        } catch (ParseException e) {
            e.printStackTrace();
        }
		return new Date();
	}

	@Override
	public boolean cancleTicket(Long id) {
		// TODO Auto-generated method stub
		Ticket ticket = getTicketByID(id);
		ticket.setReserved(false);
		ticketRepository.flush();
		return true;
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
