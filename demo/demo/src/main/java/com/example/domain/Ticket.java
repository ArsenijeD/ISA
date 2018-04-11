package com.example.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

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
	
	@Column(name = "price", nullable = false)
    private int price;
	
	@Column(name = "discount")
	private int discount;
	
	@Column(name = "reserved")
	private boolean reserved;
	
	
	@OneToOne(mappedBy = "ticket")
	private ProjectionSeats projectionSeats;

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}


	public boolean isReserved() {
		return reserved;
	}

	public void setReserved(boolean reserved) {
		this.reserved = reserved;
	}

	public ProjectionSeats getProjectionSeats() {
		return projectionSeats;
	}

	public void setProjectionSeats(ProjectionSeats projectionSeats) {
		this.projectionSeats = projectionSeats;
	}

	public Ticket() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Ticket(int price, int discount, boolean reserved, ProjectionSeats projectionSeats) {
		super();
		this.price = price;
		this.discount = discount;
		this.reserved = reserved;
		this.projectionSeats = projectionSeats;
	}


	
	
	


}