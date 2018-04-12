package com.example.domain;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "chair")
public class Chair implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chair_id", nullable = false, updatable = false)
    private Long id;
	
	
	@ManyToOne
    @JoinColumn(name="stage_id", nullable=false)
    private Stage stage;

	
	@OneToMany(mappedBy="chair")
    private Set<Card> cards;


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Stage getStage() {
		return stage;
	}


	public void setStage(Stage stage) {
		this.stage = stage;
	}



	public Set<Card> getCards() {
		return cards;
	}


	public void setCards(Set<Card> cards) {
		this.cards = cards;
	}


	public Chair() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Chair(Stage stage, Set<Card> cards) {
		super();
		this.stage = stage;
		this.cards = cards;
	}


	
	

}
