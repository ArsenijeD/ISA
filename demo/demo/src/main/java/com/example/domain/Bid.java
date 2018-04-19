package com.example.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "bid")
public class Bid {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bid_id", nullable = false, updatable = false)
    private Long id;
	
	@Column(name = "money", nullable = false)
    private Long money;
	
	@ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name="user_id")
    private User user;
	
	@ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name="ad_id")
    private Ad ad;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getMoney() {
		return money;
	}

	public void setMoney(Long money) {
		this.money = money;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Ad getAd() {
		return ad;
	}

	public void setAd(Ad ad) {
		this.ad = ad;
	}

	public Bid(Long id, Long money, User user, Ad ad) {
		super();
		this.id = id;
		this.money = money;
		this.user = user;
		this.ad = ad;
	}

	public Bid() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
