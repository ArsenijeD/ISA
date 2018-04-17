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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "presentation")
public class Presentation implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "presentation_id", nullable = false, updatable = false)
    private Long id;
	
	@Column(name = "date", nullable = false)
    private String date;
	
	
	@Column(name = "time", nullable = false)
	private String time;
	
	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name="performance_id", nullable=false)
    private Performance performance;

	
//	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @JoinColumn(name="stage_id", nullable=false)
//    private Stage stage;
	
//	@OneToMany(mappedBy="presentation",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//	@JsonIgnore
//    private Set<Card> cards;

	
	@Column(name = "discount")
	private int discount;
	

	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getDate() {
		return date;
	}


	public void setDate(String date) {
		this.date = date;
	}


	public Performance getPerformance() {
		return performance;
	}


	public void setPerformance(Performance performance) {
		this.performance = performance;
	}



	public String getTime() {
		return time;
	}


	public void setTime(String time) {
		this.time = time;
	}

	

	public int getDiscount() {
		return discount;
	}


	public void setDiscount(int discount) {
		this.discount = discount;
	}




	public Presentation() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Presentation(String date, String time, Performance performance, int discount) {
		super();
		this.date = date;
		this.time = time;
		this.performance = performance;
		this.discount = discount;
	}


	


	


	




	
	

}
