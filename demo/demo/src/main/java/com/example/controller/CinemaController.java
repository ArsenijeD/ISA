package com.example.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.Cinema;
import com.example.service.CinemaService;


@Controller
@RestController
@RequestMapping("/cinemas")
public class CinemaController {

	@Autowired
	private CinemaService cinemaService;

	@RequestMapping(
			value = "/getAll", 
			method = RequestMethod.GET, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Cinema> getCinemas() {
		List<Cinema> toRet = new ArrayList<>();
		 toRet = cinemaService.getAll();
		System.out.println(toRet.size()+"   "+toRet.get(0).getName());
		return toRet;

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
	
	@RequestMapping(
			value = "/registerCinema",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public boolean registerCinema(Cinema c) {
		
		return cinemaService.registerCinema(c);
		
	}
	
}
