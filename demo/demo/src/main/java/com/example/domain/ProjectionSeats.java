package com.example.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "projection_seats")
public class ProjectionSeats implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(name = "taken", nullable = false)
    private boolean taken;
	
	@Id
	@ManyToOne
    @JoinColumn(name="projection_id", nullable=false)
    private Projection projection;
	
	@Id
	@ManyToOne
    @JoinColumn(name="seat_id", nullable=false)
    private Seat seat;

	public boolean isTaken() {
		return taken;
	}

	public void setTaken(boolean taken) {
		this.taken = taken;
	}

	public Projection getProjection() {
		return projection;
	}

	public void setProjection(Projection projection) {
		this.projection = projection;
	}

	public Seat getSeat() {
		return seat;
	}

	public void setSeat(Seat seat) {
		this.seat = seat;
	}

	public ProjectionSeats() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ProjectionSeats(boolean taken, Projection projection, Seat seat) {
		super();
		this.taken = taken;
		this.projection = projection;
		this.seat = seat;
	}
	
	
	
	

}
