package com.example.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.Ad;
import com.example.domain.Cinema;
import com.example.domain.CurrentUser;
import com.example.domain.Hall;
import com.example.domain.Projection;
import com.example.domain.User;
import com.example.service.AdService;
import com.example.service.CinemaService;
import com.example.service.UserService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/oglasi")
public class AdController {

	
	@Autowired
	private AdService adService;
	
	@Autowired
	private UserService userService;
	
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
			value = "/getOglasForCurrentUser/{id}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ArrayList<Ad> getAdsOfCurrentUser(@PathVariable("id") Long id){
		
		User u = userService.getOneById(id);
		System.out.println(u.getFirstName());
		return adService.getAdsByUser(u);
		
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
			
			Ad ad = adService.findOneById(id);
			
			
			adService.deleteById(id);
			
		} catch (Exception e) {
			
			return false;
		}
			
		return true;
	}
	
	@RequestMapping(
			value = "/getOtherUsersOglasi/{id}/{confirmed}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ArrayList<Ad> getAdsOfCurrentUser(@PathVariable("id") Long id, @PathVariable("confirmed") Long confirmed){
		
		System.out.println("kao momak zivim");
		User u = userService.getOneById(id);
		System.out.println(u.getFirstName());
		return adService.findByUserNotAndConfirmed(u, confirmed);
		
	}
	
	
}
