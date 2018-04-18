package com.example.controller;

import java.util.List;

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

import com.example.DTO.PresentationDTO;
import com.example.domain.Film;
import com.example.domain.Hall;
import com.example.domain.Performance;
import com.example.domain.Presentation;
import com.example.domain.Projection;
import com.example.domain.Stage;
import com.example.domain.Theater;
import com.example.service.FilmService;
import com.example.service.PerformanceService;
import com.example.service.PresentationService;
import com.example.service.StageService;
import com.example.service.TheaterService;

@RestController
@CrossOrigin
@RequestMapping("/public/presentations")
public class PresentationController {
	
	@Autowired
	private PresentationService presentationService;
	
	@Autowired 
	private StageService stageService;
	
	@Autowired 
	private TheaterService theaterService;
	
	@Autowired 
	private PerformanceService performanceService;
	
	
	@RequestMapping(
			value = "/getAll", 
			method = RequestMethod.GET, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Presentation>  gethalls() {
		
		System.out.println("Number of cinemas: " + presentationService.getAll().size());
		
		return presentationService.getAll();

	}

	
	@RequestMapping(
			value = "/getPresentationByID/{id}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public Presentation getPresentationByID(@PathVariable("id") Long id){
		
		return presentationService.getPresentationByID(id);
		
	}
	
	

	@CrossOrigin(origins = "*")
	@RequestMapping(
			value = "/registerPresentation",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public Theater registerPresentation(@RequestBody PresentationDTO p) {
		
		//System.out.println("POGODJEN CONTROLLER /registerCinema");
		try {
			
			System.out.println("ID SALE: " + p.getStage_id());
//			Long hall_id = Long.valueOf(hallID);
			System.out.println("Datum: " + p.getDate());
			System.out.println("Vreme: " + p.getTime());
			System.out.println("Predstava_id: " + p.getPerformance_id());
			
			
			Performance performance = performanceService.getPerformanceByID(p.getPerformance_id());
			
			
			Presentation presentation = new Presentation(p.getDate(), p.getTime(), performance, p.getDiscount());
				
			presentationService.registerPresentation(presentation);
			
			Stage stage = stageService.getStageByID(p.getStage_id());
			
			stage.getPresentations().add(presentation);
			
			stageService.save(stage);
			
			return theaterService.getTheaterByID(p.getTheater_id());
			
			
		} catch (Exception e) {
			
			e.printStackTrace();
			return null;
		}
		
		
	}
	
	
	@RequestMapping(
			value = "/deletePresentation/{theaterID}/{stageID}/{presentationID}",
			method = RequestMethod.DELETE, 
			produces = MediaType.APPLICATION_JSON_VALUE, 
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity deleteProjection(@PathVariable("theaterID") Long theaterID,
			@PathVariable("stageID") Long stageID,
			@PathVariable("presentationID") Long presentationID) {

		Stage stage = new Stage();
		stage = stageService.getStageByID(stageID);
//		Long pID = (projectionID);
		Presentation presentation = new Presentation();

		for (Presentation p : stage.getPresentations()) {
			if (presentationID.equals(p.getId())) {
				presentation = p;
				stage.getPresentations().remove(p);
				System.out.println("ID obrisane projekcije: " + presentationID);
			}
		}

		stageService.save(stage);
		presentationService.deleteById(presentationID);
		System.out.println("Obrisao prezentacije " + presentationID + " iz tabele presentation!");
		

		return new ResponseEntity(theaterService.getTheaterByID(theaterID), HttpStatus.OK);

	}
	
	
	@CrossOrigin(origins = "*")
	@RequestMapping(
			value = "/updatePresentation",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public Theater updatePresentation(@RequestBody PresentationDTO p) {
		
		//System.out.println("POGODJEN CONTROLLER /registerCinema");
		try {
			
			System.out.println("ID SALE: " + p.getStage_id());
//			Long hall_id = Long.valueOf(hallID);
			System.out.println("Datum: " + p.getDate());
			System.out.println("Vreme: " + p.getTime());
			System.out.println("Predstava_id: " + p.getPerformance_id());
			
			
			Performance performance = performanceService.getPerformanceByID(p.getPerformance_id());
			
			Presentation presentation = new Presentation(p.getDate(), p.getTime(), performance, p.getDiscount());
			presentation.setId(p.getPresentation_id());
				
			presentationService.updatePresentation(presentation);
			
			Theater theater = theaterService.getTheaterByID(p.getTheater_id());
			
			
			//izbrisem vezu izmedju updejtovane projekcije i stare sale u kojoj je bila
			Stage old_stage = stageService.getStageByID(p.getOld_stage_id());
			for(Presentation proj : old_stage.getPresentations()) {
				if(proj.getId().equals(presentation.getId())) {
					old_stage.getPresentations().remove(proj);
				}
			}
			stageService.save(old_stage);		// upisem tu staru salu u bazu
			
			
			Stage stage = stageService.getStageByID(p.getStage_id());
			
			stage.getPresentations().add(presentation);
			
			stageService.save(stage);
			
			return theaterService.getTheaterByID(p.getTheater_id());
			
			
		} catch (Exception e) {
			
			e.printStackTrace();
			return null;
		}
		
		
	}
	
	
	
}
