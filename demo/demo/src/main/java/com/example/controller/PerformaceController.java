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

import com.example.domain.Film;
import com.example.domain.Performance;
import com.example.service.PerformanceService;

@RestController
@CrossOrigin
@RequestMapping("/public/performances")
public class PerformaceController {
	
	@Autowired
	private PerformanceService performaceService;
	
	
	@RequestMapping(
			value = "/getAll", 
			method = RequestMethod.GET, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Performance>  getPerformances() {
		
		System.out.println("Number of cinemas: " + performaceService.getAll().size());
		
		return performaceService.getAll();

	}
	
	
	@RequestMapping(
			value = "/getPerformanceByID/{id}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public Performance getPerformanceByID(@PathVariable("id") Long id){
		
		return performaceService.getPerformanceByID(id);
		
	}
	
	
	@RequestMapping(
			value = "/getPerformanceByName/{name:.+}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public Performance getPerformance(@PathVariable("name") String name){
		
		return performaceService.getPerformanceByName(name);
		
	}
	
	@CrossOrigin(origins = "*")
	@RequestMapping(
			value = "/registerPerformance",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public boolean registerPerformance(@RequestBody Performance p) {
		
		//System.out.println("POGODJEN CONTROLLER /registerCinema");
		try {
			
			performaceService.registerPerformance(p);
			
		} catch (Exception e) {
			
			e.printStackTrace();
			return false;
		}
		
		return true;
		
	}
	
		

}
