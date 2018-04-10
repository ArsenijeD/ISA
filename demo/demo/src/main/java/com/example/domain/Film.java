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


@Entity
@Table(name = "film")
public class Film implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "film_id", nullable = false, updatable = false)
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
    @JoinTable(name = "film_director", joinColumns = @JoinColumn(name = "film_id"), inverseJoinColumns = @JoinColumn(name = "director_id"))
    private Set<Director> director;
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "film_actors", joinColumns = @JoinColumn(name = "film_id"), inverseJoinColumns = @JoinColumn(name = "actor_id"))
    private Set<Actor> actors;
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "film_genres", joinColumns = @JoinColumn(name = "film_id"), inverseJoinColumns = @JoinColumn(name = "genre_id"))
    private Set<Genre> genre;
	
	@OneToMany(mappedBy="film")
    private Set<Projection> projections;
	
	
	 @OneToMany(mappedBy="film")
	 private Set<RatingFilm> ratings;
	
	
	// POSTER IMAGE treba dodati !!!!
	
	
	public Film() {}
	

	public Film(String name, int duration, float averageRating, String description, int price, Set<Director> director,
			Set<Actor> actors, Set<Genre> genre, Set<Projection> projections, Set<RatingFilm> ratings) {
		super();
		this.name = name;
		this.duration = duration;
		this.averageRating = averageRating;
		this.description = description;
		this.price = price;
		this.director = director;
		this.actors = actors;
		this.genre = genre;
		this.projections = projections;
		this.ratings = ratings;
	}


	
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

	public Set<Genre> getGenre() {
		return genre;
	}

	public void setGenre(Set<Genre> genre) {
		this.genre = genre;
	}

	public Set<Projection> getProjections() {
		return projections;
	}

	public void setProjections(Set<Projection> projections) {
		this.projections = projections;
	}


	public Set<RatingFilm> getRatings() {
		return ratings;
	}


	public void setRatings(Set<RatingFilm> ratings) {
		this.ratings = ratings;
	}

	
	
	
	


}
