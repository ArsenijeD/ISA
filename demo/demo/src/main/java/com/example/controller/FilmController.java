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
import com.example.service.FilmService;

@RestController
@CrossOrigin
@RequestMapping("/public/films")
public class FilmController {
	
	@Autowired
	private FilmService filmService;
	
	
	@RequestMapping(
			value = "/getAll", 
			method = RequestMethod.GET, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Film>  getFilms() {
		
		System.out.println("Number of cinemas: " + filmService.getAll().size());
		
		return filmService.getAll();

	}
	
	
	@RequestMapping(
			value = "/getFilmByID/{id}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public Film getFilmByID(@PathVariable("id") Long id){
		
		return filmService.getFilmByID(id);
		
	}
	
	
	@RequestMapping(
			value = "/getFilmByName/{name:.+}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public Film getFilm(@PathVariable("name") String name){
		
		return filmService.getFilmByName(name);
		
	}
	
	
	@CrossOrigin(origins = "*")
	@RequestMapping(
			value = "/registerFilm",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public boolean registerFilm(@RequestBody Film f) {
		
		//System.out.println("POGODJEN CONTROLLER /registerCinema");
		try {
			
			filmService.registerFilm(f);
			
		} catch (Exception e) {
			
			e.printStackTrace();
			return false;
		}
		
		return true;
		
	}
	
	
	@CrossOrigin(origins = "*")
	@RequestMapping(
			value = "/updateFilm",
			method = RequestMethod.PUT,						// ovde menjao
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public boolean updateFilm(@RequestBody Film f) {
		
		//System.out.println("POGODJEN CONTROLLER /registerCinema");
		try {
			
			filmService.updateFilm(f);
			return true;
			
		} catch (Exception e) {
			
			e.printStackTrace();
			return false;
		}
		
		
		
	}
	
//	@CrossOrigin(origins = "*")
//	@RequestMapping(
//			value = "/deleteFilm",
//			method = RequestMethod.POST,
//			produces = MediaType.APPLICATION_JSON_VALUE,
//			consumes = MediaType.APPLICATION_JSON_VALUE)
//	public boolean deleteFilm(@RequestBody Film f) {
//		
//	try {
//			
//			filmService.deleteById(f.getId());
//			
//			return true;
//			
//		} catch (Exception e) {
//			
////			e.printStackTrace();
//			return false;
//		}
//		
//		
//	}
	
	
	@CrossOrigin(origins = "*")
	@RequestMapping(
			value = "/deleteFilm/{filmID:.+}",
			method = RequestMethod.DELETE,
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public boolean deleteFilm(@PathVariable("filmID") Long filmID) {
		
	try {
			
			filmService.deleteById(filmID);
			
			return true;
			
		} catch (Exception e) {
			
//			e.printStackTrace();
			return false;
		}
		
		
	}
	
	
	
	

}
