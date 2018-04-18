package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.CurrentUser;
import com.example.domain.User;
import com.example.service.UserService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/friend")
public class FriendController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(
			value = "/getAll", 
			method = RequestMethod.GET, 
			produces = MediaType.APPLICATION_JSON_VALUE)

	public List<User>  getCinemas(@ModelAttribute("currentUser") CurrentUser currentUser) {
		
		System.out.println("Number of cinemas: " + userService.getAllFriends(currentUser.getId()).size());
		
		return userService.getAllFriends(currentUser.getId());
		
	}
}
