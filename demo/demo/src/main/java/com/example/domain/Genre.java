package com.example.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "genre")
public class Genre implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "genre_id", nullable = false, updatable = false)
    private Long id;
	
	@Column(name = "name", nullable = false)
    private GenreEnum name;

	
	
	public Genre() {}
	

	public Genre(GenreEnum name) {
		super();
		this.name = name;
	}



	public GenreEnum getName() {
		return name;
	}

	public void setName(GenreEnum name) {
		this.name = name;
	}
	
	
	

}
