package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.DTO.RatingCinemaDTO;
import com.example.domain.Cinema;
import com.example.domain.RatingCinema;
import com.example.domain.Theater;
import com.example.domain.User;
import com.example.service.CinemaService;
import com.example.service.RatingCinemaService;
import com.example.service.UserService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/public/cinemas")
public class CinemaController {

	@Autowired
	private CinemaService cinemaService;
	
	@Autowired
	private RatingCinemaService ratingCinemaService;
	
	@Autowired
	private UserService userService;
	
	
//	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(
			value = "/getAll", 
			method = RequestMethod.GET, 
			produces = MediaType.APPLICATION_JSON_VALUE)

	public List<Cinema>  getCinemas() {
		
		System.out.println("Number of cinemas: " + cinemaService.getAll().size());
		
		return cinemaService.getAll();
		
	}
	
	@RequestMapping(
			value = "/getCinemaByID/{id}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public Cinema getCinemaByID(@PathVariable("id") Long id){
		
		return cinemaService.getCinemaByID(id);
		
	}
	
	@RequestMapping(
			value = "/getCinemaByName/{name:.+}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public Cinema getCinema(@PathVariable("name") String name){
		
		return cinemaService.getCinemaByName(name);
		
	}
	
	
	@RequestMapping(
			value = "/register",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public boolean registerCinema(@RequestBody Cinema c) {
		
		//System.out.println("POGODJEN CONTROLLER /registerCinema");
		try {
			
			cinemaService.registerCinema(c);
			
		} catch (Exception e) {
			
			e.printStackTrace();
			return false;
		}
		
		return true;
		
	}
	
	@RequestMapping(value = "/changeCinemaAdmin", method = RequestMethod.PUT)
	public @ResponseBody Boolean updateCinema(@RequestBody Cinema c){
	 
		for (com.example.domain.User u : c.getAdmins()) {
			
			System.out.println(u.getFirstName());
		}
		System.out.println("POGODJEN CONTROLLER /changeCinemaAdmin");
		try {
			
			cinemaService.updateCinema(c);
			
		} catch (Exception e) {
			
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	
	
	@RequestMapping(value = "/changeCinema", method = RequestMethod.PUT)
	public Cinema changeCinema(@RequestBody Cinema c){
	 	
		try {
			
			System.out.println("Cinema id: " + c.getId());
			System.out.println("Cinema name: " + c.getName());
			System.out.println("Cinema address: " + c.getAdress());
			System.out.println("Cinema description: " + c.getDescription());


			cinemaService.changeCinema(c);
			
			return cinemaService.getCinemaByID(c.getId());
			
		} catch (Exception e) {
			
			e.printStackTrace();
			return null;
		}
		
		
	}
	
	
	
	@RequestMapping(
			value = "/rateCinema",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public Cinema rateCinema(@RequestBody RatingCinemaDTO rc) {
		
		boolean ocenio = false;
		try {
			
			User user = userService.getOneById(rc.getUserID());
			
			Cinema cinema = cinemaService.getCinemaByID(rc.getCinemaID());
			
			RatingCinema ratingCinema = new RatingCinema();
			
			// moram da proverim da li je ovaj user vec ocenio cinemu
			for(RatingCinema ratCin : cinema.getCinemaRatings()) {
				if(user.getId().equals(ratCin.getUser().getId())) {		// vec ocenio cinemu
					ocenio = true;
					ratingCinema = ratCin;
					ratingCinema.setValue(rc.getMark());
					
					ratCin.setValue(rc.getMark());
					
					break;
				}
				
			}
			
			if(ocenio) {
				
				ratingCinemaService.updateRatingCinema(ratingCinema);		// updejtovao ga u bazi
				
				double temp = 0.0;
				for(RatingCinema rat_cin : cinema.getCinemaRatings()) {
					temp += rat_cin.getValue();
				}
				cinema.setAverageRating(temp / cinema.getCinemaRatings().size()*1.0);
				
				cinemaService.registerCinema(cinema);
				
				return cinema;
				
			} else {
				
				ratingCinema.setUser(user);
				ratingCinema.setValue(rc.getMark());		
				ratingCinemaService.registerRatingCinema(ratingCinema);			// ubacio novu u bazu
				
				cinema.getCinemaRatings().add(ratingCinema);
				
				double temp = 0.0;
				for(RatingCinema rat_cin : cinema.getCinemaRatings()) {
					temp += rat_cin.getValue();
				}
				cinema.setAverageRating(temp / cinema.getCinemaRatings().size()*1.0);
				
				cinemaService.registerCinema(cinema);

				return cinema;
			}
				
			
		} catch (Exception e) {
			
			e.printStackTrace();
			return null;
		}

		
	}
	
	
	
	
//	@RequestMapping(value="/registerCinema", method = RequestMethod.POST) 
//	public ResponseEntity registerCinema(@RequestBody Cinema c) {
//		System.out.println("POGODIO ME JE");
//		
//		return new ResponseEntity<>(cinemaService.registerCinema(c), HttpStatus.OK);
//	}
	
}
