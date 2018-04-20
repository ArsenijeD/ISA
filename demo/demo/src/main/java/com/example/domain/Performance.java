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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "performance")
public class Performance implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "performance_id", nullable = false, updatable = false)
    private Long id;
	
	@Column(name = "name", nullable = false, unique = true, columnDefinition="VARCHAR(40)")
    private String name;
	
	@Column(name = "duration", nullable = false)
    private int duration;
	
	@Column(name = "averageRating", nullable = true)
	private Float averageRating;
	
	
	@Column(name = "description", nullable = false, columnDefinition="VARCHAR(50)")
    private String description;
	
	@Column(name="price", nullable = false)
	private int price;
	
	
	@Column(name = "genre", nullable = false, columnDefinition="VARCHAR(50)")
    private String genre;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "film_ratingPerformance", joinColumns = @JoinColumn(name = "performance_id"), inverseJoinColumns = @JoinColumn(name = "ratingPerformance_id"))
    private Set<RatingPerformance> performanceRatings;
	


	 
	// POSTER IMAGE treba dodati !!!!

	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getDuration() {
		return duration;
	}


	public void setDuration(int duration) {
		this.duration = duration;
	}


	public Float getAverageRating() {
		return averageRating;
	}


	public void setAverageRating(Float averageRating) {
		this.averageRating = averageRating;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public int getPrice() {
		return price;
	}


	public void setPrice(int price) {
		this.price = price;
	}



	public String getGenre() {
		return genre;
	}


	public void setGenre(String genre) {
		this.genre = genre;
	}



	public Set<RatingPerformance> getPerformanceRatings() {
		return performanceRatings;
	}


	public void setPerformanceRatings(Set<RatingPerformance> performanceRatings) {
		this.performanceRatings = performanceRatings;
	}


	public Performance() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Performance(String name, int duration, float averageRating, String description, int price, String genre) {
		super();
		this.name = name;
		this.duration = duration;
		this.averageRating = averageRating;
		this.description = description;
		this.price = price;
		this.genre = genre;
	}


	 
	 

}
