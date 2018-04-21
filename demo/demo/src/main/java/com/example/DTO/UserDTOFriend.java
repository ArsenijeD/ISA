package com.example.DTO;

import com.example.domain.FriendRequest;
import com.example.domain.FriendRequestStatus;

public class UserDTOFriend {

	public  Long requestId;
	public Long id;
	public String firstName;
	public String lastName;
	public FriendRequestStatus friendRequestStatus;
	public String email;
	
	public Long getRequestId() {
		return requestId;
	}
	public void setRequestId(Long friendId) {
		this.requestId = friendId;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public FriendRequestStatus getFriendRequestStatus() {
		return friendRequestStatus;
	}
	public void setFriendRequestStatus(FriendRequestStatus friendRequestStatus) {
		this.friendRequestStatus = friendRequestStatus;
	}
	
	public  UserDTOFriend mapSent(FriendRequest friendRequest) {
		UserDTOFriend userDTOFriend=new UserDTOFriend();
		userDTOFriend.requestId=friendRequest.getId();
		userDTOFriend.id=friendRequest.getReceiver().getId();
		userDTOFriend.firstName=friendRequest.getReceiver().getFirstName();
		userDTOFriend.lastName=friendRequest.getReceiver().getLastName();
		userDTOFriend.friendRequestStatus=friendRequest.getStatus();
		userDTOFriend.email=friendRequest.getReceiver().getEmail();
		return userDTOFriend;
	}
	public  UserDTOFriend mapReceived(FriendRequest friendRequest) {
		UserDTOFriend userDTOFriend=new UserDTOFriend();
		userDTOFriend.requestId=friendRequest.getId();
		userDTOFriend.id=friendRequest.getSender().getId();
		userDTOFriend.firstName=friendRequest.getSender().getFirstName();
		userDTOFriend.lastName=friendRequest.getSender().getLastName();
		userDTOFriend.friendRequestStatus=friendRequest.getStatus();
		userDTOFriend.email=friendRequest.getSender().getEmail();
		return userDTOFriend;
	}
}
