package com.example.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="FriendRequest")
public class CinemaVisit {

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;  
	
	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	private User visitor;
	
	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	private Cinema cinema;
	

}
