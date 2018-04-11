package com.example.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "rating_theater")
public class RatingTheater implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ratingTheater_id", nullable = false, updatable = false)
    private Long id;
	
	@Column(name = "description", columnDefinition="VARCHAR(40)")
    private String description;
	
	@Column(name = "value", nullable = false)
    private int value;
	
	@ManyToOne
    @JoinColumn(name="theater_id", nullable=false)
    private Theater theater;

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

	public Theater getTheater() {
		return theater;
	}

	public void setTheater(Theater theater) {
		this.theater = theater;
	}

	public RatingTheater() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RatingTheater(String description, int value, Theater theater) {
		super();
		this.description = description;
		this.value = value;
		this.theater = theater;
	}
	
	
	

}
