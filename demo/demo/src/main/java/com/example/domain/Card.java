package com.example.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "card")
public class Card implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "card_id", nullable = false, updatable = false)
    private Long id;
	
	@Column(name = "price", nullable = false)
    private int price;
	
	@Column(name = "discount")
	private int discount;
	
	@Column(name = "reserved")
	private boolean reserved;
	
	
	@OneToOne(mappedBy = "card")
	private PresentationChairs presentationChairs;


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public int getPrice() {
		return price;
	}


	public void setPrice(int price) {
		this.price = price;
	}


	public int getDiscount() {
		return discount;
	}


	public void setDiscount(int discount) {
		this.discount = discount;
	}


	public boolean isReserved() {
		return reserved;
	}


	public void setReserved(boolean reserved) {
		this.reserved = reserved;
	}


	public PresentationChairs getPresentationChairs() {
		return presentationChairs;
	}


	public void setPresentationChairs(PresentationChairs presentationChairs) {
		this.presentationChairs = presentationChairs;
	}


	public Card() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Card(int price, int discount, boolean reserved, PresentationChairs presentationChairs) {
		super();
		this.price = price;
		this.discount = discount;
		this.reserved = reserved;
		this.presentationChairs = presentationChairs;
	}
	
	

}
