package com.example.DTO;

import com.example.domain.FriendRequest;

public   class MyFriendDTOMaper {
	public static  UserDTOFriend mapSent(FriendRequest friendRequest) {
		UserDTOFriend userDTOFriend=new UserDTOFriend();
		userDTOFriend.requestId=friendRequest.getId();
		userDTOFriend.id=friendRequest.getReceiver().getId();
		userDTOFriend.firstName=friendRequest.getReceiver().getFirstName();
		userDTOFriend.lastName=friendRequest.getReceiver().getLastName();
		userDTOFriend.friendRequestStatus=friendRequest.getStatus();
		userDTOFriend.email=friendRequest.getReceiver().getEmail();
		return userDTOFriend;
	}
	public static UserDTOFriend mapReceived(FriendRequest friendRequest) {
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
