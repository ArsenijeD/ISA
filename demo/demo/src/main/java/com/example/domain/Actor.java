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
import javax.persistence.Table;

@Entity
@Table(name = "actor")
public class Actor implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "actor_id", nullable = false, updatable = false)
    private Long id;
	
	
	@Column(name = "firstname", nullable = false, columnDefinition="VARCHAR(40)")
    private String firstname;
	
	@Column(name = "surname", nullable = false, columnDefinition="VARCHAR(40)")
    private String surname;

	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "film_actors", joinColumns = @JoinColumn(name = "film_id"), inverseJoinColumns = @JoinColumn(name = "actor_id"))
    private Set<Film> films;
	
	
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "performance_actors", joinColumns = @JoinColumn(name = "performance_id"), inverseJoinColumns = @JoinColumn(name = "actor_id"))
    private Set<Performance> performance;
	
	public Actor() {}
	


	public Actor(String firstname, String surname, Set<Film> films, Set<Performance> performance) {
		super();
		this.firstname = firstname;
		this.surname = surname;
		this.films = films;
		this.performance = performance;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}



	public Set<Film> getFilms() {
		return films;
	}



	public void setFilms(Set<Film> films) {
		this.films = films;
	}



	public Set<Performance> getPerformance() {
		return performance;
	}



	public void setPerformance(Set<Performance> performance) {
		this.performance = performance;
	}
	
	
	
		
	

}
