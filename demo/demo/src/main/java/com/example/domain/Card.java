package com.example.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "card")
public class Card implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
//	@Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "card_id", nullable = false, updatable = false)
//    private Long id;
	
	@Column(name = "price", nullable = false)
    private int price;
	
//	@Column(name = "discount")
//	private int discount;
	
	@Column(name = "reserved")
	private boolean reserved;
	
	
	@Id
	@ManyToOne
    @JoinColumn(name="presentation_id", nullable=false)
    private Presentation presentation;
	
	@Id
	@ManyToOne
    @JoinColumn(name="chair_id", nullable=false)
    private Chair chair;


//	public Long getId() {
//		return id;
//	}
//
//
//	public void setId(Long id) {
//		this.id = id;
//	}


	public int getPrice() {
		return price;
	}


	public void setPrice(int price) {
		this.price = price;
	}


//	public int getDiscount() {
//		return discount;
//	}
//
//
//	public void setDiscount(int discount) {
//		this.discount = discount;
//	}


	public boolean isReserved() {
		return reserved;
	}


	public void setReserved(boolean reserved) {
		this.reserved = reserved;
	}



	public Presentation getPresentation() {
		return presentation;
	}


	public void setPresentation(Presentation presentation) {
		this.presentation = presentation;
	}


	public Chair getChair() {
		return chair;
	}


	public void setChair(Chair chair) {
		this.chair = chair;
	}


	public Card() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Card(int price, boolean reserved) {
		super();
		this.price = price;
		this.reserved = reserved;
	}


	
	
	

}
