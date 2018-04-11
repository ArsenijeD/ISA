package com.example.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "presentation_chairs")
public class PresentationChairs implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Column(name = "taken", nullable = false)
    private boolean taken;
	
	@Id
	@ManyToOne
    @JoinColumn(name="presentation_id", nullable=false)
    private Presentation presentation;
	
	@Id
	@ManyToOne
    @JoinColumn(name="chair_id", nullable=false)
    private Chair chair;

	@OneToOne
	@JoinColumn(name = "card_id")
	private Card card;

	public boolean isTaken() {
		return taken;
	}

	public void setTaken(boolean taken) {
		this.taken = taken;
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

	public Card getCard() {
		return card;
	}

	public void setCard(Card card) {
		this.card = card;
	}

	public PresentationChairs() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PresentationChairs(boolean taken, Card card) {
		super();
		this.taken = taken;
		this.card = card;
	}
	
	

}
