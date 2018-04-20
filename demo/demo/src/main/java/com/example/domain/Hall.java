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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
	
	@Column(name = "hall_number", nullable = false, updatable = false)
	private int number;
	
	
//	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @JoinColumn(name="cinema_id", nullable=false)
//	@JsonIgnore
//    private Cinema cinema;
	
	
//	@OneToMany(mappedBy="hall", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//	@JsonIgnore
//  private Set<Seat> seats;
	
	
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "hall_projections", joinColumns = @JoinColumn(name = "hall_id"), inverseJoinColumns = @JoinColumn(name = "projection_id"))
    private Set<Projection> projections;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "hall_seats", joinColumns = @JoinColumn(name = "hall_id"), inverseJoinColumns = @JoinColumn(name = "seat_id"))
    private Set<Seat> seats;


	
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

	public Set<Projection> getProjections() {
		return projections;
	}

	public void setProjections(Set<Projection> projections) {
		this.projections = projections;
	}

	
	
	public Set<Seat> getSeats() {
		return seats;
	}

	public void setSeats(Set<Seat> seats) {
		this.seats = seats;
	}

	
	public Hall() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Hall(int number, Set<Projection> projections, Set<Seat> seats) {
		super();
		this.number = number;
		this.projections = projections;
		this.seats = seats;
	}
	

	

	
	

	
	
	
	

	
	
	
	
	
	
	
	

}
