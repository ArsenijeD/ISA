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

import com.example.domain.Cinema;
import com.example.domain.Hall;
import com.example.service.HallService;

@RestController
@CrossOrigin
@RequestMapping("/public/halls")
public class HallController {
	
	@Autowired
	private HallService hallService;
	
	
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
	
	
	@RequestMapping(
			value = "/getHallsByCinemaID/{cinemaID:.+}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Hall> getHallsByCinemaID(@PathVariable("cinemaID") Long cinemaID){
		
		return hallService.getHallsByCinemaID(cinemaID);
		
	}

}
