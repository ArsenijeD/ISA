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

import com.example.domain.Stage;
import com.example.service.StageService;

@RestController
@CrossOrigin
@RequestMapping("/public/stages")
public class StageController {
	
	@Autowired
	private StageService stageService;
	
	@RequestMapping(
			value = "/getAll", 
			method = RequestMethod.GET, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Stage>  getStages() {
		
		System.out.println("Number of cinemas: " + stageService.getAll().size());
		
		return stageService.getAll();

	}
	
	@RequestMapping(
			value = "/getStageByID/{id}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public Stage getStageByID(@PathVariable("id") Long id){
		
		return stageService.getStageByID(id);
		
	}
	
	
	@CrossOrigin(origins = "*")
	@RequestMapping(
			value = "/registerStage",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public boolean registerStage(@RequestBody Stage s) {
		
		//System.out.println("POGODJEN CONTROLLER /registerCinema");
		try {
			
			stageService.registerStage(s);
			
		} catch (Exception e) {
			
			e.printStackTrace();
			return false;
		}
		
		return true;
		
	}
	
	

}
