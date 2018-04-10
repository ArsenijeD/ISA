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
	
	
	@ManyToOne
    @JoinColumn(name="hall_id", nullable=false)
    private Hall hall;

	
	@OneToMany(mappedBy="seat")
    private Set<ProjectionSeats> projectionSeats;
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Hall getHall() {
		return hall;
	}

	public void setHall(Hall hall) {
		this.hall = hall;
	}
	

	public Set<ProjectionSeats> getProjectionSeats() {
		return projectionSeats;
	}

	public void setProjectionSeats(Set<ProjectionSeats> projectionSeats) {
		this.projectionSeats = projectionSeats;
	}

	
	public Seat() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Seat(Hall hall, Set<ProjectionSeats> projectionSeats) {
		super();
		this.hall = hall;
		this.projectionSeats = projectionSeats;
	}

	
	
	

}
