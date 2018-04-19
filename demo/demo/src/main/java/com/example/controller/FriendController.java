package com.example.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.DTO.UserDTOFriend;
import com.example.domain.CurrentUser;
import com.example.domain.User;
import com.example.service.UserService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/friend")
public class FriendController {
	
	@Autowired
	private UserService userService;
	
	private ModelMapper modelMapper=new ModelMapper();
	@RequestMapping(
			value = "/getAll", 
			method = RequestMethod.GET, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public List<UserDTOFriend>  getFriends(@ModelAttribute("currentUser") CurrentUser currentUser) {		
		System.out.println("Number of cinemas: " + userService.getAllFriends(currentUser.getId()).size());
		
		List<User> userFriends=userService.getAllFriends(currentUser.getId());
		return userFriends.stream().map(post -> modelMapper.map(post,UserDTOFriend.class)).collect(Collectors.toList());
	}
	
	@RequestMapping(
			value = "/getAllPendingReqSend", 
			method = RequestMethod.GET, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public List<UserDTOFriend>  getPendingReqSent(@ModelAttribute("currentUser") CurrentUser currentUser) {		
		System.out.println("Number of friends: " + userService.getAllFriendRequestSentPending(currentUser.getId()).size());
		
		List<User> userFriends=userService.getAllFriends(currentUser.getId());
		return userFriends.stream().map(post -> modelMapper.map(post,UserDTOFriend.class)).collect(Collectors.toList());
	}
	
	@RequestMapping(
			value = "/getAllPendingReqReceived", 
			method = RequestMethod.GET, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public List<UserDTOFriend>  getPendingReqReceived(@ModelAttribute("currentUser") CurrentUser currentUser) {		
		System.out.println("Number of PendingReqReceived: " + userService.getALLFriendRequestReceivedPending(currentUser.getId()).size());
		
		List<User> userFriends=userService.getAllFriends(currentUser.getId());
		return userFriends.stream().map(post -> modelMapper.map(post,UserDTOFriend.class)).collect(Collectors.toList());
	}
	
	@RequestMapping(
			value = "/sendRequest/{receiverId}", 
			method = RequestMethod.GET, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public Boolean sendRequest(@ModelAttribute("currentUser") CurrentUser currentUser,@PathVariable Long receiverId) {
		//false vrati ako iz nekog razloga ne moze da ga doda
		return userService.sendRequest(currentUser.getId(),receiverId);
	}
	
	
	@RequestMapping(
			value = "/approveRequest/{requestId}", 
			method = RequestMethod.GET, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public Boolean approveRequest(@ModelAttribute("currentUser") CurrentUser currentUser,@PathVariable Long requestId) {
		//false vrati ako iz nekog razloga ne moze da ga doda
		return userService.approveRequest(requestId,currentUser.getId());
	}
	
	@RequestMapping(
			value = "/rejectRequest/{requestId}", 
			method = RequestMethod.GET, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public Boolean rejectRequest(@ModelAttribute("currentUser") CurrentUser currentUser,@PathVariable Long requestId) {
		//false vrati ako iz nekog razloga ne moze da ga odbije
		return userService.rejectRequest(requestId,currentUser.getId());
	}
	
	@RequestMapping(
			value = "/deleteFriend/{requestId}", 
			method = RequestMethod.GET, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public Boolean deleteFriend(@ModelAttribute("currentUser") CurrentUser currentUser,@PathVariable Long requestId) {
		//false vrati ako iz nekog razloga ne moze da ga obrise
		return userService.deleteFriend(requestId,currentUser.getId());
	}
}
