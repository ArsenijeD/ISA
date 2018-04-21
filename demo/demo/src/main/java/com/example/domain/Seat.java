package com.example.domain;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "seat")
public class Seat implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seat_id", nullable = false, updatable = false)
    private Long id;
	
	
	@Column(name = "seat_number", nullable = false, updatable = false)
	private int number;
	
//	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    @JoinColumn(name="hall_id", nullable=false)
//    private Hall hall;

	
//	@OneToMany(mappedBy="seat",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//	@JsonIgnore
//    private Set<Ticket> tickets;
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	

	public Seat() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Seat(int number) {
		super();
		this.number = number;

	}

	
	


	
	
	

}
