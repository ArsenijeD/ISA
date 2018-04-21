package com.example.domain;

import java.io.Serializable;
import java.sql.Date;
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

@Entity
@Table(name = "ad")
public class Ad implements Serializable {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ad_id", nullable = false, updatable = false)
    private Long id;
	
	@Column(name = "name", nullable = false)
    private String name;
	
	@Column(name = "description", nullable = false)
    private String description;
	
	@Column(name = "date")
	private Date date;
	
	@Column(name = "image", nullable = false)
    private String image;
	
	@Column(name = "confirmed", nullable = false)
    private Long confirmed;
	
	@ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name="user_id")
    private User user;
	
//	@OneToMany(mappedBy="ad")
//    private Set<Bid> bids;

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

	

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
//
//	public Long getYear() {
//		return year;
//	}
//
//	public void setYear(Long year) {
//		this.year = year;
//	}
//
//	public Long getMonth() {
//		return month;
//	}
//
//	public void setMonth(Long month) {
//		this.month = month;
//	}
//
//	public Long getDay() {
//		return day;
//	}
//
//	public void setDay(Long day) {
//		this.day = day;
//	}

//	public String getDate() {
//		return date;
//	}
//
//	public void setDate(String date) {
//		this.date = date;
//	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

//	public boolean isConfirmed() {
//		return confirmed;
//	}
//
//	public void setConfirmed(boolean confirmed) {
//		this.confirmed = confirmed;
//	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	
	public Long getConfirmed() {
		return confirmed;
	}

	public void setConfirmed(Long confirmed) {
		this.confirmed = confirmed;
	}

	public Ad(Long id, String name, String description, Date date, String image, Long confirmed, User user) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.date = date;
		this.image = image;
		this.confirmed = confirmed;
		this.user = user;
	}

	
	
	public Ad() {
		super();
		// TODO Auto-generated constructor stub
	}

//public Ad(Long id, String name, String description, Long year, Long month, Long day, String image, boolean confirmed,
//		User user) {
//	super();
//	this.id = id;
//	this.name = name;
//	this.description = description;
//	this.year = year;
//	this.month = month;
//	this.day = day;
//	this.image = image;
//	this.confirmed = confirmed;
//	this.user = user;
//}

	
}
