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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "ticket")
public class Ticket implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ticket_id", nullable = false, updatable = false)
    private Long id;
	
	@Column(name = "price")
    private int price;
	

	@Column(name = "reserved", nullable = false)
	private boolean reserved;
	
	
	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name="seat_id", nullable=false)
    private Seat seat;
	
	@Column(name = "forFastReservation", nullable = false)
	private boolean forFastReservation;
	

//	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    @JoinColumn(name="projection_id", nullable=false)
//    private Projection projection;

	
//	public Long getId() {
//		return id;
//	}
//
//	public void setId(Long id) {
//		this.id = id;
//	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}


	public boolean isReserved() {
		return reserved;
	}

	public void setReserved(boolean reserved) {
		this.reserved = reserved;
	}

	
	public Seat getSeat() {
		return seat;
	}

	public void setSeat(Seat seat) {
		this.seat = seat;
	}
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean isForFastReservation() {
		return forFastReservation;
	}

	public void setForFastReservation(boolean forFastReservation) {
		this.forFastReservation = forFastReservation;
	}

	
	public Ticket() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Ticket(int price, boolean reserved, Seat seat, boolean forFastReservation) {
		super();
		this.price = price;
		this.reserved = reserved;
		this.seat = seat;
		this.forFastReservation = forFastReservation;
	}
	
	



	
	



	
	
	


}
