package com.example.domain;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
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
    private String date;
	
	
	@Column(name = "time", nullable = false)
	private String time;
	
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JsonIgnore
    @JoinColumn(name="film_id", nullable=false)
    private Film film;
	
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name="hall_id", nullable=false)
	@JsonIgnore
    private Hall hall;

	
	@OneToMany(mappedBy="projection",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JsonIgnore
    private Set<Ticket> tickets;
	
	
	@Column(name = "discount")
	private int discount;
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}



	public Film getFilm() {
		return film;
	}

	public void setFilm(Film film) {
		this.film = film;
	}


	
	public Set<Ticket> getTickets() {
		return tickets;
	}

	public void setTickets(Set<Ticket> tickets) {
		this.tickets = tickets;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	
	
	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}
	
	

	public Hall getHall() {
		return hall;
	}

	public void setHall(Hall hall) {
		this.hall = hall;
	}

	public Projection() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Projection(String date, String time, Film film, Hall hall, Set<Ticket> tickets, int discount) {
		super();
		this.date = date;
		this.time = time;
		this.film = film;
		this.hall = hall;
		this.tickets = tickets;
		this.discount = discount;
	}

	
	

	
	

	
	



	
	
	
	
	

}
