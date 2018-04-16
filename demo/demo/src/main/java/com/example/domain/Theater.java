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
@Table(name = "theater")
public class Theater implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "theater_id", nullable = false, updatable = false)
    private Long id;
	
	@Column(name = "name", nullable = false, unique = true, columnDefinition="VARCHAR(40)")
    private String name;
	
	@Column(name = "description", nullable = false, columnDefinition="VARCHAR(50)")
    private String description;
	
	@Column(name = "adress", nullable = false, unique = true, columnDefinition="VARCHAR(100)")
    private String adress;
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "user_theater", joinColumns = @JoinColumn(name = "theater_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> admins;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "theater_stages", joinColumns = @JoinColumn(name = "theater_id"), inverseJoinColumns = @JoinColumn(name = "stage_id"))
    private Set<Stage> stages;
	

//	@OneToMany(mappedBy="theater",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//	@JsonIgnore
//	private Set<RatingTheater> ratings;

	 
	 
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public Set<User> getAdmins() {
		return admins;
	}

	public void setAdmins(Set<User> admins) {
		this.admins = admins;
	}


	
	
	public Set<Stage> getStages() {
		return stages;
	}

	public void setStages(Set<Stage> stages) {
		this.stages = stages;
	}

	public Theater() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Theater(String name, String description, String adress, Set<User> admins, Set<Stage> stages) {
		super();
		this.name = name;
		this.description = description;
		this.adress = adress;
		this.admins = admins;
		this.stages = stages;
	}


	
	 
	 
	
	

}
