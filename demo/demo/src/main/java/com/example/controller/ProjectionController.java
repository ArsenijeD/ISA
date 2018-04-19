package com.example.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.DTO.FastTicketsDTO;
import com.example.DTO.ProjectionDTO;
import com.example.domain.Cinema;
import com.example.domain.Film;
import com.example.domain.Hall;
import com.example.domain.Projection;
import com.example.domain.Seat;
import com.example.domain.Ticket;
import com.example.service.CinemaService;
import com.example.service.FilmService;
import com.example.service.HallService;
import com.example.service.ProjectionService;
import com.example.service.TicketService;


@RestController
@CrossOrigin
@RequestMapping("/public/projections")
public class ProjectionController {
	
	@Autowired
	private ProjectionService projectionService;
	
	@Autowired 
	private HallService hallService;
	
	@Autowired 
	private CinemaService cinemaService;
	
	@Autowired 
	private FilmService filmService;
	
	@Autowired 
	private TicketService ticketService;
	
	
	
	@RequestMapping(
			value = "/getAll", 
			method = RequestMethod.GET, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Projection>  gethalls() {
		
		System.out.println("Number of cinemas: " + projectionService.getAll().size());
		
		return projectionService.getAll();

	}
	
	
	@RequestMapping(
			value = "/getProjectionByID/{id}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public Projection getProjectionByID(@PathVariable("id") Long id){
		
		return projectionService.getProjectionByID(id);
		
	}
	
	
	@CrossOrigin(origins = "*")
	@RequestMapping(
			value = "/registerProjection",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public Cinema registerProjection(@RequestBody ProjectionDTO p) {
		
		//System.out.println("POGODJEN CONTROLLER /registerCinema");
		try {
			
			System.out.println("ID SALE: " + p.getHall_id());
//			Long hall_id = Long.valueOf(hallID);
			System.out.println("Datum: " + p.getDate());
			System.out.println("Vreme: " + p.getTime());
			System.out.println("Film_id: " + p.getFilm_id());
			
			
			Film film = filmService.getFilmByID(p.getFilm_id());
			
			Projection projection = new Projection(film, p.getDate(), p.getTime(), p.getDiscount());
			
			Hall hall = hallService.getHallByID(p.getHall_id());
			
			
			// moram da kreiram odma i karte za tu projekciju
			projection.setTickets(new HashSet<Ticket>());
			
			
			for(Seat s : hall.getSeats()) {
				Ticket ticket = new Ticket(); 		// ovde sam stao
				ticket.setReserved(false);
				ticket.setForFastReservation(false);
				ticket.setSeat(s);
				ticket.setPrice(film.getPrice() - (p.getDiscount()*film.getPrice())/100);
				
				if(ticketService.registerTicket(ticket))
					System.out.println("UPISAO KARTU!");
				
				projection.getTickets().add(ticket);
			}
			
					
			projectionService.registerProjection(projection);
			
			
			hall.getProjections().add(projection);
			
			hallService.save(hall);
			
			return cinemaService.getCinemaByID(p.getCinema_id());
			
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
			return null;
		}
		
		
	}
	
	
	@RequestMapping(
			value = "/getProjectionsByHallID/{hallID:.+}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Projection> getProjectionsByHallID(@PathVariable("hallID") Long hallID){
			
		return projectionService.getProjectionsByHallID(hallID);
		
	}
	
	
	@RequestMapping(
			value = "/deleteProjection/{cinemaID}/{hallID}/{projectionID}",
			method = RequestMethod.DELETE, 
			produces = MediaType.APPLICATION_JSON_VALUE, 
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public Cinema deleteProjection(@PathVariable("cinemaID") Long cinemaID,
			@PathVariable("hallID") Long hallID,
			@PathVariable("projectionID") Long projectionID) {

		try { 
			
			Hall hall = new Hall();
			hall = hallService.getHallByID(hallID);
	//		Long pID = (projectionID);
			Projection projection = new Projection();
	
			for (Projection p : hall.getProjections()) {
				if (projectionID.equals(p.getId())) {
					projection = p;
					hall.getProjections().remove(p);
					System.out.println("ID obrisane projekcije: " + projectionID);
				}
			}
	
			hallService.save(hall);
			projectionService.deleteById(projectionID);
			System.out.println("Obrisao projekciju " + projectionID + " iz tabele projection!");
			
	
			return cinemaService.getCinemaByID(cinemaID);
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
			return null;
		}

	}
	
	
	
	@CrossOrigin(origins = "*")
	@RequestMapping(
			value = "/updateProjection",
			method = RequestMethod.PUT,						// ovde menjao
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public Cinema updateProjection(@RequestBody ProjectionDTO p) {
		
		try {
			
			Cinema c1 = deleteProjection(p.getCinema_id(), p.getOld_hall_id(), p.getProjection_id());
			
			Cinema c2 = registerProjection(p);
			
			return c2;
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
			return null;
		}
	
	}
	
	
	@CrossOrigin(origins = "*")
	@RequestMapping(
			value = "/addFastTickets",
			method = RequestMethod.PUT,						// ovde menjao
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public boolean addFastTickets(@RequestBody FastTicketsDTO ft) {
		
		int potrebanBrojBrzihKarata = ft.getFast_tickets_number();
		Projection projection = projectionService.getProjectionByID(ft.getProjection_id());
		
		boolean ima_ih_dovoljno = false;
		ArrayList<Long> list_ticketsID = new ArrayList<Long>();
		
		
		// prvo proveravam hoce li biti dovoljno karata
		for(Ticket ticket : projection.getTickets()) {
			System.out.println("Da li je rezervisana: " + ticket.isReserved());
			if(!ticket.isReserved()) {
				if(!ticket.isForFastReservation()) {
					potrebanBrojBrzihKarata--;
					list_ticketsID.add(ticket.getId());
				}		
			}
			
			if(potrebanBrojBrzihKarata == 0) {
				ima_ih_dovoljno = true;
				break;
			}
			
		}
		
		
		if(ima_ih_dovoljno) {
			
			for(Long ticketID : list_ticketsID) {
				Ticket t = ticketService.getTicketByID(ticketID);
				t.setForFastReservation(true);
				ticketService.changeToFastReserveTicket(t);
			}
			
			return true;
			
			
		}
		
		return false;
		
		
		
	}
	
	
	
	
	/*
	@CrossOrigin(origins = "*")
	@RequestMapping(
			value = "/updateProjection",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public Cinema updateProjection(@RequestBody ProjectionDTO p) {
		
		//System.out.println("POGODJEN CONTROLLER /registerCinema");
		try {
			
			System.out.println("ID SALE: " + p.getHall_id());
//			Long hall_id = Long.valueOf(hallID);
			System.out.println("Datum: " + p.getDate());
			System.out.println("Vreme: " + p.getTime());
			System.out.println("Film_id: " + p.getFilm_id());
			
			
			Film film = filmService.getFilmByID(p.getFilm_id());
			
			Projection projection = new Projection(film, p.getDate(), p.getTime(), p.getDiscount());
			projection.setId(p.getProjection_id());
			
			
			//-----------ovo sam dodao sad --------------//
			projection.setTickets(new HashSet<Ticket>());
			projectionService.updateProjection(projection);
			// ------------------------------------------//
			
			Hall hall = hallService.getHallByID(p.getHall_id());
			for(Seat s : hall.getSeats()) {
				Ticket ticket = new Ticket(); 		// ovde sam stao
				ticket.setReserved(false);
				ticket.setForFastReservation(false);
				ticket.setSeat(s);
				ticket.setPrice(film.getPrice() - (p.getDiscount()*film.getPrice())/100);
				
				if(ticketService.registerTicket(ticket))
					System.out.println("UPISAO KARTU!");
				
				projection.getTickets().add(ticket);
			}
			
			// ---------------------------------------- //
			
					
			projectionService.registerProjection(projection);
			
			
			// updatejtujem projekciju prvo u tabeli PROJECTION
			projectionService.updateProjection(projection);
			
			Cinema cinema = cinemaService.getCinemaByID(p.getCinema_id());
			
			//izbrisem vezu izmedju updejtovane projekcije i stare sale u kojoj je bila
			Hall old_hall = hallService.getHallByID(p.getOld_hall_id());
			for(Projection proj : old_hall.getProjections()) {
				if(proj.getId().equals(projection.getId())) {
					old_hall.getProjections().remove(proj);
				}
			}
			hallService.save(old_hall);		// upisem tu staru salu u bazu
			
			
			hall.getProjections().add(projection);	// dodam updejtovanu projekciju u novu izabranu salu
			
			hallService.save(hall);
			
			
			return cinemaService.getCinemaByID(p.getCinema_id());
			
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
			return null;
		}
		
		
	}
	
	*/
	
	
	
	

}
