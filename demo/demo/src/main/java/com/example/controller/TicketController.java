package com.example.controller;


import static org.mockito.Matchers.anyList;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import com.example.repository.CinemaRepository;
import com.example.service.CinemaService;
import com.example.service.ProjectionService;
import com.example.service.TicketService;
import com.example.service.UserService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/public/tickets")
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
				
				
				// -------------------- provera za datum ---------------------------//
				System.out.println("Datum pre izmene: " + projection.getDate());		
				String[] splittedDate = projection.getDate().split("-");
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
		            if(date.before(today)){
		                return false;
		            }
		           
		        } catch (ParseException e) {
		            e.printStackTrace();
		        }
				
				// ----------------------------------------- provera za datum ------------------- //
				
				
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
	@CrossOrigin(origins = "*")
	@RequestMapping(
			value = "/reserveTicket",
			method = RequestMethod.PUT,						
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public boolean reserveTicket(@RequestBody ReservationTicketDTO rt) {
		
		User user = userService.getOneById(rt.getUser_id());
		Ticket ticket = ticketService.getTicketByID(rt.getTicket_id());

		Projection projection = projectionService.getProjectionByID(rt.getProjection_id());

		try {
			
			// -------------------- provera za datum ---------------------------//
			System.out.println("Datum pre izmene: " + projection.getDate());		
			String[] splittedDate = projection.getDate().split("-");
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
	            if(date.before(today)){
	                return false;
	            }
	           
	        } catch (ParseException e) {
	            e.printStackTrace();
	        }
			
			// ----------------------------------------- provera za datum ------------------- //

					if(!ticket.isForFastReservation() && !ticket.isReserved()) {
						ticket.setReserved(true);
						ticketService.reserveTicket(user, ticket);					
						return true;
					}
			
							
				return false;
		
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
	}
	

}
