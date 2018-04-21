package com.example.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.DTO.HallDTO;
import com.example.domain.Cinema;
import com.example.domain.Hall;
import com.example.domain.Projection;
import com.example.domain.Seat;
import com.example.service.CinemaService;
import com.example.service.HallService;
import com.example.service.SeatService;


@RestController
@CrossOrigin
@RequestMapping("/public/halls")
public class HallController {
	
	@Autowired
	private HallService hallService;
	
	@Autowired
	private SeatService seatService;
	
	@Autowired
	private CinemaService cinemaService;
	
	
	@RequestMapping(
			value = "/getAll", 
			method = RequestMethod.GET, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Hall>  gethalls() {
		
		System.out.println("Number of cinemas: " + hallService.getAll().size());
		
		return hallService.getAll();

	}
	
	@RequestMapping(
			value = "/getHallByID/{id}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public Hall getHallByID(@PathVariable("id") Long id){
		
		return hallService.getHallByID(id);
		
	}
	
	
	@CrossOrigin(origins = "*")
	@RequestMapping(
			value = "/registerHall",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public boolean registerHall(@RequestBody Hall h) {
		
		//System.out.println("POGODJEN CONTROLLER /registerCinema");
		try {
			
			hallService.registerHall(h);
			
		} catch (Exception e) {
			
			e.printStackTrace();
			return false;
		}
		
		return true;
		
	}
	
	
	@CrossOrigin(origins = "*")
	@RequestMapping(
			value = "/addHall",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public Cinema addHall(@RequestBody HallDTO h) {
		
		System.out.println("id cineme: " + h.getCinema_id());
		System.out.println("Redni broj sale: " + h.getNumber());
		System.out.println("Broj sedista u sali: " + h.getNumberOfSeats());
		
		try {
			
			Hall hall = new Hall();
			hall.setNumber(h.getNumber());
			hall.setSeats(new HashSet<Seat>());
			hall.setProjections(new HashSet<Projection>());
			
			for(int i=0; i<h.getNumberOfSeats(); i++) {
				Seat seat = new Seat();
				seat.setNumber(i+1);
				System.out.println("Redni broj sedista: " + seat.getNumber());
				if(seatService.registerSeat(seat))
					System.out.println("UPISAO SEDISTE!");
				
				hall.getSeats().add(seat);
			}
			
			hallService.save(hall);
			
			Cinema cinema = cinemaService.getCinemaByID(h.getCinema_id());
			
			cinema.getHalls().add(hall);
			
			cinemaService.changeCinema(cinema);
			
			return cinema;
			
			
			
		} catch (Exception e) {
			
			e.printStackTrace();
			return null;
		}
		
		
	}
	
	
	
	@RequestMapping(
			value = "/deleteHall/{cinemaID}/{hallID}",
			method = RequestMethod.DELETE,
			produces = MediaType.APPLICATION_JSON_VALUE, 
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public  Cinema deleteHall(@PathVariable("cinemaID") Long cinemaID, @PathVariable("hallID") Long hallID){
		
		try {
			
			System.out.println("cinemaID: " + cinemaID);
			System.out.println("hallID: " + hallID);
			
			Hall hall = hallService.getHallByID(hallID);
			
			Cinema cinema = cinemaService.getCinemaByID(cinemaID);
			System.out.println("Pokupio cinema!");
			cinema.getHalls().remove(hall);
			System.out.println("Izbrisao salu iz cinema!");
			
			if(cinemaService.changeCinema(cinema))
				System.out.println("izmenio listu sala iz trenutnog bioskopa!");
			
			System.out.println("Pokupio salu sa prosledjenim ID-em!");
			hallService.deleteById(hallID);
			System.out.println("Izbrisao salu sa prosledjenim ID-em!");
			
			
			return cinema;
			
			} catch (Exception e) {
				
				e.printStackTrace();
				return null;
			}
		
			
	}
	
	
	
	
	@RequestMapping(
			value = "/getHallsByCinemaID/{cinemaID:.+}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Hall> getHallsByCinemaID(@PathVariable("cinemaID") Long cinemaID){
		
		return hallService.getHallsByCinemaID(cinemaID);
		
	}

}
