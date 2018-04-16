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

import com.example.domain.Hall;
import com.example.domain.Projection;
import com.example.service.ProjectionService;

@RestController
@CrossOrigin
@RequestMapping("/public/projections")
public class ProjectionController {
	
	@Autowired
	private ProjectionService projectionService;
	
	
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
	public boolean registerProjection(@RequestBody Projection p) {
		
		//System.out.println("POGODJEN CONTROLLER /registerCinema");
		try {
			
			projectionService.registerProjection(p);
			
		} catch (Exception e) {
			
			e.printStackTrace();
			return false;
		}
		
		return true;
		
	}
	
	
	@RequestMapping(
			value = "/getProjectionsByHallID/{hallID:.+}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Projection> getProjectionsByHallID(@PathVariable("hallID") Long hallID){
			
		return projectionService.getProjectionsByHallID(hallID);
		
	}
	
	
	

}
