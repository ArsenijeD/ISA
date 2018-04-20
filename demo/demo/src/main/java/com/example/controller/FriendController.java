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

import com.example.DTO.MyFriendDTOMaper;
import com.example.DTO.UserDTOFriend;
import com.example.domain.CurrentUser;
import com.example.domain.FriendRequest;
import com.example.domain.FriendRequestStatus;
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
			value = "/search/{firstName}/{lastName}", 
			method = RequestMethod.GET, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public List<UserDTOFriend>  searchUsers(@ModelAttribute("currentUser") CurrentUser currentUser,@PathVariable String firstName,@PathVariable String lastName) {		
		
		List<FriendRequest> userFriendsStats=userService.findBySenderOrReceiver(new Long(currentUser.getUser().getId()));
		List<User> users=userService.getSearchedFriends(firstName, lastName);
		List<UserDTOFriend> list= users.stream().map(post -> modelMapper.map(post,UserDTOFriend.class)).collect(Collectors.toList());
		UserDTOFriend currentDeleteDTO=null;
		Boolean deleteCurrent=false;
		for(UserDTOFriend user:list) {
			Boolean notFriends=true;
			for(FriendRequest friendRequest:userFriendsStats) {
				if(friendRequest.getReceiver().getId()==user.getId()||friendRequest.getSender().getId()==user.getId()) {
						user.setFriendRequestStatus(friendRequest.getStatus());
						user.setRequestId(friendRequest.getId());
						notFriends=false;
						if(user.getId()==currentUser.getId()) {
							deleteCurrent=true;
							currentDeleteDTO=user;
						}
						break;					
				}						
			}
			if(notFriends) {
				user.setFriendRequestStatus(FriendRequestStatus.NOTFRIENDS);
			}
		}
		if(deleteCurrent) {
			list.remove(currentDeleteDTO);
		}
		return list;
	}
	
	@RequestMapping(
			value = "/getAll", 
			method = RequestMethod.GET, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public List<UserDTOFriend>  getFriends(@ModelAttribute("currentUser") CurrentUser currentUser) {		
		System.out.println("Number of friends: " + userService.getAllFriends(currentUser.getId()).size());		
		return userService.getAllFriends(currentUser.getId());
	}
	
	@RequestMapping(
			value = "/getAllPendingReqSent", 
			method = RequestMethod.GET, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public List<UserDTOFriend>  getPendingReqSent(@ModelAttribute("currentUser") CurrentUser currentUser) {		
		System.out.println("Number of friend req sent: " + userService.getAllFriendRequestSentPending(currentUser.getId()).size());
		
		List<FriendRequest> friendRequests=userService.getAllFriendRequestSentPending(currentUser.getId());
		return friendRequests.stream().map(post -> MyFriendDTOMaper.mapSent(post)).collect(Collectors.toList());
	}

	
	@RequestMapping(
			value = "/getAllPendingReqReceived", 
			method = RequestMethod.GET, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public List<UserDTOFriend>  getPendingReqReceived(@ModelAttribute("currentUser") CurrentUser currentUser) {		
		System.out.println("Number of PendingReqReceived: " + userService.getALLFriendRequestReceivedPending(currentUser.getId()).size());
		
		List<FriendRequest> friendRequests=userService.getALLFriendRequestReceivedPending(currentUser.getId());
		return friendRequests.stream().map(post -> MyFriendDTOMaper.mapReceived(post)).collect(Collectors.toList());
	}
	
	@RequestMapping(
			value = "/sendRequest/{receiverId}", 
			method = RequestMethod.GET, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public Boolean sendRequest(@ModelAttribute("currentUser") CurrentUser currentUser,@PathVariable Long receiverId) {
		//false vrati ako iz nekog razloga ne moze da ga doda
		System.out.println("send request sent*************************");
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
			value = "/deleteSentRequest/{requestId}", 
			method = RequestMethod.GET, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public Boolean deleteSentRequest(@ModelAttribute("currentUser") CurrentUser currentUser,@PathVariable Long requestId) {
		//false vrati ako iz nekog razloga ne moze da ga odbije
		return userService.deleteSentRequest(requestId,currentUser.getId());
	}
	
	@RequestMapping(
			value = "/unfriend/{requestId}", 
			method = RequestMethod.GET, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public Boolean deleteFriend(@ModelAttribute("currentUser") CurrentUser currentUser,@PathVariable Long requestId) {
		//false vrati ako iz nekog razloga ne moze da ga obrise
		return userService.deleteFriend(requestId,currentUser.getId());
	}
}
