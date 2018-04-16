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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "stage")
public class Stage implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stage_id", nullable = false, updatable = false)
    private Long id;
	
	@Column(name = "stage_number", nullable = false, updatable = false)
	private int number;
	
	
//	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @JoinColumn(name="theater_id", nullable=false)
//    private Theater theater;
	
	
//	@OneToMany(mappedBy="stage", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private Set<Chair> chairs;
	
//	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    @JoinTable(name = "stage_presentation", joinColumns = @JoinColumn(name = "stage_id"), inverseJoinColumns = @JoinColumn(name = "presentation_id"))
//    private Set<Projection> projections;

	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "stage_presentations", joinColumns = @JoinColumn(name = "stage_id"), inverseJoinColumns = @JoinColumn(name = "presentation_id"))
    private Set<Presentation> presentations;

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	

	public Set<Presentation> getPresentations() {
		return presentations;
	}

	public void setPresentations(Set<Presentation> presentations) {
		this.presentations = presentations;
	}

	public Stage() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Stage(int number, Set<Presentation> presentations) {
		super();
		this.number = number;
		this.presentations = presentations;
	}


	
	

	

	
	
	
	

}
