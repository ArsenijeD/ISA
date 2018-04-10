package com.example.domain;

import java.io.Serializable;
import java.sql.Date;
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
@Table(name = "projection")
public class Projection implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "projection_id", nullable = false, updatable = false)
    private Long id;
	
	@Column(name = "date", nullable = false)
    private Date date;
	
	
	@ManyToOne
    @JoinColumn(name="film_id", nullable=false)
    private Film film;

	
	@OneToMany(mappedBy="projection")
    private Set<ProjectionSeats> projectionSeats;
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}



	public Film getFilm() {
		return film;
	}

	public void setFilm(Film film) {
		this.film = film;
	}


	public Set<ProjectionSeats> getProjectionSeats() {
		return projectionSeats;
	}

	public void setProjectionSeats(Set<ProjectionSeats> projectionSeats) {
		this.projectionSeats = projectionSeats;
	}

	public Projection() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	public Projection(Date date, Film film, Set<ProjectionSeats> projectionSeats) {
		super();
		this.date = date;
		this.film = film;
		this.projectionSeats = projectionSeats;
	}

	
	
	
	
	

}
