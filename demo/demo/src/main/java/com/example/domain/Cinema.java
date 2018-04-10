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
@Table(name = "cinema")
public class Cinema implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cinema_id", nullable = false, updatable = false)
    private Long id;
	
	@Column(name = "name", nullable = false, unique = true, columnDefinition="VARCHAR(40)")
    private String name;
	
	@Column(name = "opis", nullable = false, unique = true, columnDefinition="VARCHAR(50)")
    private String opis;
	
	@Column(name = "adress", nullable = false, unique = true, columnDefinition="VARCHAR(40)")
    private String adress;
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "user_cinema", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "cinema_id"))
    private Set<User> admins;

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

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public Cinema(String name, String opis, String adress, Set<User> admins) {
		super();
		this.name = name;
		this.opis = opis;
		this.adress = adress;
		this.admins = admins;
	}

	public Cinema() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	

}
