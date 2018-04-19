package com.example.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.Ad;
import com.example.domain.Cinema;
import com.example.domain.Hall;
import com.example.domain.Projection;
import com.example.service.AdService;
import com.example.service.CinemaService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/public/oglasi")
public class AdController {

	
	@Autowired
	private AdService adService;
	
	@RequestMapping(
			value = "/register",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public boolean registerAd(@RequestBody Ad a) {
		
		
		System.out.println("POGODJEN CONTROLLER /public/oglasi/register");
		try {
			
			adService.registerAd(a);
			
		} catch (Exception e) {
			
			e.printStackTrace();
			return false;
		}
		
		return true;
		
	}
	
	@RequestMapping(
			value = "/getOglasForCurrentUser",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ArrayList<Ad> getAdsOfCurrentUser(){
		
		return adService.getAdsByUser(null);
		
	}
	
	@RequestMapping(
			value = "/getOglasByConfirmed/{confirmed}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ArrayList<Ad> getAdsByConfirmed(@PathVariable("confirmed") Long confirmed){
		
		System.out.println("pogodjen.");
		return adService.getAdsByConfirmed(confirmed);
		
	}
	
	@RequestMapping(value = "/changeOglasStatus", method = RequestMethod.PUT)
	public @ResponseBody Boolean changeOglasStatus(@RequestBody Ad ad){
	 
		
		System.out.println("POGODJEN CONTROLLER /changeOglasStatus");
		try {
			
			adService.updateAd(ad);
			
		} catch (Exception e) {
			
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	@RequestMapping(value = "/updateOglas", method = RequestMethod.PUT)
	public @ResponseBody Boolean updateOglas(@RequestBody Ad ad){
	 
	
		
		System.out.println("POGODJEN CONTROLLER /updateOglas");
		try {
			
			adService.updateWholeAd(ad);
			
		} catch (Exception e) {
			
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	
	@RequestMapping(
			value = "/deleteOglas/{id}",
			method = RequestMethod.DELETE, 
			produces = MediaType.APPLICATION_JSON_VALUE, 
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public boolean deleteOglas(@PathVariable("id") Long id) {

		System.out.println("pogodio /deleteOglas");
		try {
			
			adService.deleteById(id);
			
		} catch (Exception e) {
			
			return false;
		}
			
		return true;
	}
	
	
}
