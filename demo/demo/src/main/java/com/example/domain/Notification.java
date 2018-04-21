package com.example.domain;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "notification")
public class Notification {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notification_id", nullable = false, updatable = false)
    private Long id;
	
	@Column(name = "message", nullable = false)
    private String message;
	

	
	@ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name="user_id")
    private User user;



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getMessage() {
		return message;
	}



	public void setMessage(String message) {
		this.message = message;
	}



	



	public User getUser() {
		return user;
	}



	public void setUser(User user) {
		this.user = user;
	}



	



	public Notification(Long id, String message, User user) {
		super();
		this.id = id;
		this.message = message;
		this.user = user;
	}



	public Notification() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
