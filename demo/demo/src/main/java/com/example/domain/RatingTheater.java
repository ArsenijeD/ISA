package com.example.domain;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "rating_theater")
public class RatingTheater implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ratingTheater_id", nullable = false, updatable = false)
    private Long id;
	
	
	@Column(name = "value", nullable = false)
    private int value;
	
	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name="user_id", nullable=false)
    private User user;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}


	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public RatingTheater() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RatingTheater(int value, User user) {
		super();
		this.value = value;
		this.user = user;
	}

		
	
	
	

}
