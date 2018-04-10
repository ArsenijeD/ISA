package com.example.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "director")
public class Director implements Serializable{	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "director_id", nullable = false, updatable = false)
    private Long id;
	
	
	@Column(name = "firstname", nullable = false, columnDefinition="VARCHAR(40)")
    private String firstname;
	
	@Column(name = "surname", nullable = false, columnDefinition="VARCHAR(40)")
    private String surname;

	
	
	public Director() {}
	
	public Director(String firstname, String surname) {
		super();
		this.firstname = firstname;
		this.surname = surname;
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
	
	
	
	
}
