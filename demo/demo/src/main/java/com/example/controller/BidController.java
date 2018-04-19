package com.example.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
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
@RequestMapping("/public/bids")
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
}
