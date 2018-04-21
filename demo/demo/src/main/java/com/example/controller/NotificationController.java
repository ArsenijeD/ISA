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
import com.example.domain.Notification;
import com.example.domain.User;
import com.example.service.AdService;
import com.example.service.NotificationService;
import com.example.service.UserService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/notifications")
public class NotificationController {

	
	@Autowired
	private NotificationService notificationService;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(
			value = "/register",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public boolean registerAd(@RequestBody Notification n) {
		
		
		System.out.println("POGODJEN CONTROLLER /notifications/register");
		try {
			
			notificationService.registerNotification(n);
			
		} catch (Exception e) {
			
			e.printStackTrace();
			return false;
		}
		
		return true;
		
	}
	
	
	@RequestMapping(
			value = "/getNotificationsForCurrentUser/{id}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ArrayList<Notification> getNotificationsForCurrentUser(@PathVariable("id") Long id){
		
		User u = userService.getOneById(id);
		System.out.println(u.getFirstName());
		return notificationService.findAllByUser(u);
		
	}
	
	@RequestMapping(
			value = "/deleteNotification/{id}",
			method = RequestMethod.DELETE, 
			produces = MediaType.APPLICATION_JSON_VALUE, 
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public boolean deleteOglas(@PathVariable("id") Long id) {

		System.out.println("pogodio /deleteNotification/{id}");
		try {
			
			User u = userService.getOneById(id);
			notificationService.deleteByUser(u);
			
		} catch (Exception e) {
			
			return false;
		}
			
		return true;
	}
}
