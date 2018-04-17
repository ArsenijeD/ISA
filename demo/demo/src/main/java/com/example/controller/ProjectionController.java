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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.DTO.ProjectionDTO;
import com.example.domain.Cinema;
import com.example.domain.Film;
import com.example.domain.Hall;
import com.example.domain.Projection;
import com.example.service.CinemaService;
import com.example.service.FilmService;
import com.example.service.HallService;
import com.example.service.ProjectionService;

@RestController
@CrossOrigin
@RequestMapping("/public/projections")
public class ProjectionController {
	
	@Autowired
	private ProjectionService projectionService;
	
	@Autowired 
	private HallService hallService;
	
	@Autowired 
	private CinemaService cinemaService;
	
	@Autowired 
	private FilmService filmService;
	
	
	
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
	public Cinema registerProjection(@RequestBody ProjectionDTO p) {
		
		//System.out.println("POGODJEN CONTROLLER /registerCinema");
		try {
			
			System.out.println("ID SALE: " + p.getHall_id());
//			Long hall_id = Long.valueOf(hallID);
			System.out.println("Datum: " + p.getDate());
			System.out.println("Vreme: " + p.getTime());
			System.out.println("Film_id: " + p.getFilm_id());
			
			
			Film film = filmService.getFilmByID(p.getFilm_id());
			
			Projection projection = new Projection(film, p.getDate(), p.getTime(), p.getDiscount());
				
			projectionService.registerProjection(projection);
			
			Hall hall = hallService.getHallByID(p.getHall_id());
			
			hall.getProjections().add(projection);
			
			hallService.save(hall);
			
			return cinemaService.getCinemaByID(p.getCinema_id());
			
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
			return null;
		}
		
		
	}
	
	
	@RequestMapping(
			value = "/getProjectionsByHallID/{hallID:.+}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Projection> getProjectionsByHallID(@PathVariable("hallID") Long hallID){
			
		return projectionService.getProjectionsByHallID(hallID);
		
	}
	
	
	@RequestMapping(
			value = "/deleteProjection/{cinemaID}/{hallID}/{projectionID}",
			method = RequestMethod.DELETE, 
			produces = MediaType.APPLICATION_JSON_VALUE, 
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity deleteProjection(@PathVariable("cinemaID") Long cinemaID,
			@PathVariable("hallID") Long hallID,
			@PathVariable("projectionID") Long projectionID) {

		Hall hall = new Hall();
		hall = hallService.getHallByID(hallID);
//		Long pID = (projectionID);
		Projection projection = new Projection();

		for (Projection p : hall.getProjections()) {
			if (projectionID.equals(p.getId())) {
				projection = p;
				hall.getProjections().remove(p);
				System.out.println("ID obrisane projekcije: " + projectionID);
			}
		}

		hallService.save(hall);
		projectionService.deleteById(projectionID);
		System.out.println("Obrisao projekciju " + projectionID + " iz tabele projection!");
		

		return new ResponseEntity(cinemaService.getCinemaByID(cinemaID), HttpStatus.OK);

	}
	
	
	
	@CrossOrigin(origins = "*")
	@RequestMapping(
			value = "/updateProjection",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public Cinema updateProjection(@RequestBody ProjectionDTO p) {
		
		//System.out.println("POGODJEN CONTROLLER /registerCinema");
		try {
			
			System.out.println("ID SALE: " + p.getHall_id());
//			Long hall_id = Long.valueOf(hallID);
			System.out.println("Datum: " + p.getDate());
			System.out.println("Vreme: " + p.getTime());
			System.out.println("Film_id: " + p.getFilm_id());
			
			
			Film film = filmService.getFilmByID(p.getFilm_id());
			
			Projection projection = new Projection(film, p.getDate(), p.getTime(), p.getDiscount());
			projection.setId(p.getProjection_id());
			
			// updatejtujem projekciju prvo u tabeli PROJECTION
			projectionService.updateProjection(projection);
			
			Cinema cinema = cinemaService.getCinemaByID(p.getCinema_id());
			
			//izbrisem vezu izmedju updejtovane projekcije i stare sale u kojoj je bila
			Hall old_hall = hallService.getHallByID(p.getOld_hall_id());
			for(Projection proj : old_hall.getProjections()) {
				if(proj.getId().equals(projection.getId())) {
					old_hall.getProjections().remove(proj);
				}
			}
			hallService.save(old_hall);		// upisem tu staru salu u bazu
			
			
			Hall hall = hallService.getHallByID(p.getHall_id());
			
			hall.getProjections().add(projection);	// dodam updejtovanu projekciju u novu izabranu salu
			
			hallService.save(hall);
			
			
			return cinemaService.getCinemaByID(p.getCinema_id());
			
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
			return null;
		}
		
		
	}
	
	
	
	
	
	

}
