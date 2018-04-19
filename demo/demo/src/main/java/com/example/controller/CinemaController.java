package com.example.controller;

import java.util.List;

import org.apache.catalina.User;
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

import com.example.domain.Cinema;
import com.example.domain.Theater;
import com.example.service.CinemaService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/public/cinemas")
public class CinemaController {

	@Autowired
	private CinemaService cinemaService;
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
	
	
//	@RequestMapping(value="/registerCinema", method = RequestMethod.POST) 
//	public ResponseEntity registerCinema(@RequestBody Cinema c) {
//		System.out.println("POGODIO ME JE");
//		
//		return new ResponseEntity<>(cinemaService.registerCinema(c), HttpStatus.OK);
//	}
	
}
