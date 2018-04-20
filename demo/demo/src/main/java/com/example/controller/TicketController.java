package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.DTO.ReservationTicketDTO;
import com.example.domain.Projection;
import com.example.domain.Ticket;
import com.example.domain.User;
import com.example.service.ProjectionService;
import com.example.service.TicketService;
import com.example.service.UserService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/public/cinemas")
public class TicketController {
	
	@Autowired
	private TicketService ticketService;
	
	@Autowired
	private ProjectionService projectionService;
	
	@Autowired
	private UserService userService;
	
	
	
	
	@CrossOrigin(origins = "*")
	@RequestMapping(
			value = "/fastReserveTicket",
			method = RequestMethod.PUT,						
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public boolean fastReserveTicket(@RequestBody ReservationTicketDTO rt) {
		
		try {
				User user = userService.getOneById(rt.getUser_id());
				Projection projection = projectionService.getProjectionByID(rt.getProjection_id());
				
				boolean slobodna = false;
				for(Ticket ticket : projection.getTickets()) {
					if(ticket.isForFastReservation() && !ticket.isReserved()) {
						ticket.setReserved(true);
						ticketService.reserveTicket(user, ticket);
						
						return true;
					}
				}
							
				return false;
		
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		
		
		
	}

}
