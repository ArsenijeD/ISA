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
    @JoinColumn(name="film_id", nullable=false)
    private Film film;
	
	
//	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @JoinColumn(name="hall_id", nullable=false)
//    private Hall hall;

	
//	@OneToMany(mappedBy="projection",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//	@JsonIgnore
//    private Set<Ticket> tickets;
	
	
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
	
	

	public Film getFilm() {
		return film;
	}

	public void setFilm(Film film) {
		this.film = film;
	}

	public Projection() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Projection(String date, String time, int discount) {
		super();
		this.date = date;
		this.time = time;
		this.film = film;
		this.discount = discount;
	}

	
	
	


	
	

	
	

	
	



	
	
	
	
	

}
