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
	
	@Column(name = "averageRating", nullable = false)
	private float averageRating;
	
	
	@Column(name = "description", nullable = false, columnDefinition="VARCHAR(50)")
    private String description;
	
	@Column(name="price", nullable = false)
	private int price;
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "performance_director", joinColumns = @JoinColumn(name = "performance_id"), inverseJoinColumns = @JoinColumn(name = "director_id"))
    private Set<Director> director;
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "performance_actors", joinColumns = @JoinColumn(name = "performance_id"), inverseJoinColumns = @JoinColumn(name = "actor_id"))
    private Set<Actor> actors;
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "performance_genres", joinColumns = @JoinColumn(name = "performance_id"), inverseJoinColumns = @JoinColumn(name = "genre_id"))
    private Set<Genre> genre;
	
	@OneToMany(mappedBy="performance",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonIgnore
    private Set<Presentation> presentations;
	
	
	 @OneToMany(mappedBy="performance",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	 @JsonIgnore
	 private Set<RatingPerformance> ratings;

	 
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


	public float getAverageRating() {
		return averageRating;
	}


	public void setAverageRating(float averageRating) {
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


	public Set<Director> getDirector() {
		return director;
	}


	public void setDirector(Set<Director> director) {
		this.director = director;
	}


	public Set<Actor> getActors() {
		return actors;
	}


	public void setActors(Set<Actor> actors) {
		this.actors = actors;
	}


	public Set<Genre> getGenre() {
		return genre;
	}


	public void setGenre(Set<Genre> genre) {
		this.genre = genre;
	}


	public Set<Presentation> getPresentations() {
		return presentations;
	}


	public void setPresentations(Set<Presentation> presentations) {
		this.presentations = presentations;
	}


	public Set<RatingPerformance> getRatings() {
		return ratings;
	}


	public void setRatings(Set<RatingPerformance> ratings) {
		this.ratings = ratings;
	}


	public Performance() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Performance(String name, int duration, float averageRating, String description, int price,
			Set<Director> director, Set<Actor> actors, Set<Genre> genre, Set<Presentation> presentations,
			Set<RatingPerformance> ratings) {
		super();
		this.name = name;
		this.duration = duration;
		this.averageRating = averageRating;
		this.description = description;
		this.price = price;
		this.director = director;
		this.actors = actors;
		this.genre = genre;
		this.presentations = presentations;
		this.ratings = ratings;
	}
	 
	 

}
