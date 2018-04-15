package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.Ad;
import com.example.domain.Cinema;
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
	
}
