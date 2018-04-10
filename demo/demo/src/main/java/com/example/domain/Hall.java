package com.example.domain;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "hall")
public class Hall implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hall_id", nullable = false, updatable = false)
    private Long id;
	
	
	@ManyToOne
    @JoinColumn(name="cinema_id", nullable=false)
    private Cinema cinema;
	
	
	@OneToMany(mappedBy="hall")
    private Set<Seat> seats;


	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Cinema getCinema() {
		return cinema;
	}

	public void setCinema(Cinema cinema) {
		this.cinema = cinema;
	}

	public Set<Seat> getSeats() {
		return seats;
	}

	public void setSeats(Set<Seat> seats) {
		this.seats = seats;
	}

	
	public Hall() {}

	
	public Hall(Cinema cinema, Set<Seat> seats) {
		this.cinema = cinema;
		this.seats = seats;
	}
	
	
	
	
	
	
	

}
