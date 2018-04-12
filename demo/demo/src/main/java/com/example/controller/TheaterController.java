package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import com.example.domain.Theater;
import com.example.service.TheaterService;




@RestController
@RequestMapping("/public/theaters")
public class TheaterController {
	
	@Autowired
	private TheaterService theaterService;
	
	
	@RequestMapping(
			value = "/getAll", 
			method = RequestMethod.GET, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Theater> getTheaters() {
		
		return theaterService.getAll();

	}
	
	@RequestMapping(
			value = "/getTheaterByID/{id}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public Theater getTheaterByID(@PathVariable("id") Long id){
		
		return theaterService.getTheaterByID(id);
		
	}
	
	@RequestMapping(
			value = "/getTheaterByName/{name:.+}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public Theater getTheater(@PathVariable("name") String name){
		
		return theaterService.getTheaterByName(name);
		
	}
	
	
	@CrossOrigin(origins = "*")
	@RequestMapping(
			value = "/register",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public boolean registerTheater(@RequestBody Theater t) {
		
		//System.out.println("POGODJEN CONTROLLER /registerCinema");
		try {
			
			theaterService.registerTheater(t);
			
		} catch (Exception e) {
			
			e.printStackTrace();
			return false;
		}
		
		return true;
		
		
	}
	

}
