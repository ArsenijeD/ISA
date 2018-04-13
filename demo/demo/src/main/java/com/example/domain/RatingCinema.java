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
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "rating_cinema")
public class RatingCinema implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ratingCinema_id", nullable = false, updatable = false)
    private Long id;
	
	@Column(name = "description", columnDefinition="VARCHAR(40)")
    private String description;
	
	@Column(name = "value", nullable = false)
    private int value;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="cinema_id", nullable=false)
	@JsonIgnore
    private Cinema cinema;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public Cinema getCinema() {
		return cinema;
	}

	public void setCinema(Cinema cinema) {
		this.cinema = cinema;
	}

	
	
	public RatingCinema() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RatingCinema(String description, int value, Cinema cinema) {
		super();
		this.description = description;
		this.value = value;
		this.cinema = cinema;
	}
	

}
