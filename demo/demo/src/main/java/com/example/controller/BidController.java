package com.example.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.Ad;
import com.example.domain.Bid;
import com.example.domain.User;
import com.example.service.AdService;
import com.example.service.BidService;
import com.example.service.UserService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/bids")
public class BidController {

	@Autowired
	private BidService bidService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AdService adService;
	
	@RequestMapping(
			value = "/getBidsForSelectedOglas/{id}",
			method = RequestMethod.GET,
			
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ArrayList<Bid> getBidsForSelectedOglas(@PathVariable("id") Long id){
		
		Ad ad = adService.findOneById(id);
		return bidService.getBidsByAd(ad);
		
	}
	
	@RequestMapping(
			value = "/register",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public boolean registerAd(@RequestBody Bid b) {
		
		
		System.out.println("POGODJEN CONTROLLER /bids/register");
		try {
			
			bidService.registerBid(b);
			
		} catch (Exception e) {
			
			e.printStackTrace();
			return false;
		}
		
		return true;
		
	}
	
	@RequestMapping(
			value = "/deleteBid/{id}",
			method = RequestMethod.DELETE, 
			produces = MediaType.APPLICATION_JSON_VALUE, 
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public boolean deleteBid(@PathVariable("id") Long id) {

		System.out.println("pogodio /deleteBid");
		try {
			
			bidService.deleteById(id);
			
		} catch (Exception e) {
			
			return false;
		}
			
		return true;
	}
}
