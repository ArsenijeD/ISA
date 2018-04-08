package com.example.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="FriendRequest")
public class FriendRequest {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;    
	
	@Column(name = "status", nullable = false)
	private FriendRequestStatus status=FriendRequestStatus.PENDING;
	
	@ManyToOne
	private User sender;
	@ManyToOne
	private User receiver;
	public FriendRequestStatus getStatus() {
		return status;
	}
	public void setStatus(FriendRequestStatus status) {
		this.status = status;
	}
	public User getSender() {
		return sender;
	}
	public void setSender(User sender) {
		this.sender = sender;
	}
	public User getReceiver() {
		return receiver;
	}
	public void setReceiver(User receiver) {
		this.receiver = receiver;
	}
}
