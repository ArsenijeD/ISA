package com.example.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.domain.Cinema;
import com.example.service.CinemaService;


@RestController
@RequestMapping("/public/cinemas")
public class CinemaController {

	@Autowired
	private CinemaService cinemaService;
	
	@RequestMapping(
			value = "/getAll", 
			method = RequestMethod.GET, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Cinema> getCinemas() {
		
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
	
	@CrossOrigin(origins = "*")
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
	
//	@RequestMapping(value="/registerCinema", method = RequestMethod.POST) 
//	public ResponseEntity registerCinema(@RequestBody Cinema c) {
//		System.out.println("POGODIO ME JE");
//		
//		return new ResponseEntity<>(cinemaService.registerCinema(c), HttpStatus.OK);
//	}
	
}
