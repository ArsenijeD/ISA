package com.example.controller;

import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.DTO.HallDTO;
import com.example.DTO.StageDTO;
import com.example.domain.Chair;
import com.example.domain.Cinema;
import com.example.domain.Hall;
import com.example.domain.Presentation;
import com.example.domain.Projection;
import com.example.domain.Seat;
import com.example.domain.Stage;
import com.example.domain.Theater;
import com.example.service.ChairService;
import com.example.service.StageService;
import com.example.service.TheaterService;

@RestController
@CrossOrigin
@RequestMapping("/public/stages")
public class StageController {
	
	@Autowired
	private StageService stageService;
	
	@Autowired
	private ChairService chairService;
	
	@Autowired
	private TheaterService theaterService;
	
	
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
	
	
	@CrossOrigin(origins = "*")
	@RequestMapping(
			value = "/addStage",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public Theater addStage(@RequestBody StageDTO s) {
		
		System.out.println("id cineme: " + s.getTheater_id());
		System.out.println("Redni broj sale: " + s.getNumber());
		System.out.println("Broj sedista u sali: " + s.getNumberOfChairs());
		
		try {
			
			Stage stage = new Stage();
			stage.setNumber(s.getNumber());
			stage.setChairs(new HashSet<Chair>());
			stage.setPresentations(new HashSet<Presentation>());
			
			for(int i=0; i<s.getNumberOfChairs(); i++) {
				Chair chair = new Chair();
				chair.setNumber(i+1);
				System.out.println("Redni broj sedista: " + chair.getNumber());
				if(chairService.registerChair(chair))
					System.out.println("UPISAO SEDISTE!");
				
				stage.getChairs().add(chair);
			}
			
			stageService.save(stage);
			
			Theater theater = theaterService.getTheaterByID(s.getTheater_id());
			
			theater.getStages().add(stage);
			
			theaterService.changeTheater(theater);
			
			return theater;
			
			
			
		} catch (Exception e) {
			
			e.printStackTrace();
			return null;
		}
		
		
	}
	
	
	
	@RequestMapping(
			value = "/deleteStage/{theaterID}/{stageID}",
			method = RequestMethod.DELETE,
			produces = MediaType.APPLICATION_JSON_VALUE, 
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public  Theater deleteStage(@PathVariable("theaterID") Long theaterID, @PathVariable("stageID") Long stageID){
		
		try {
			
			System.out.println("theaterID: " + theaterID);
			System.out.println("stageID: " + stageID);
			
			Stage stage = stageService.getStageByID(stageID);
			
			Theater theater = theaterService.getTheaterByID(theaterID);
			System.out.println("Pokupio theater!");
			theater.getStages().remove(stage);
			System.out.println("Izbrisao salu iz theatera!");
			
			if(theaterService.changeTheater(theater))
				System.out.println("izmenio listu sala iz trenutnog bioskopa!");
			
			System.out.println("Pokupio salu sa prosledjenim ID-em!");
			stageService.deleteById(stageID);
			System.out.println("Izbrisao salu sa prosledjenim ID-em!");
			
			
			return theater;
			
			} catch (Exception e) {
				
				e.printStackTrace();
				return null;
			}
		
			
	}
	
	

}
