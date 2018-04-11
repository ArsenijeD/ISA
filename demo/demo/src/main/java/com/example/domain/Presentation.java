package com.example.domain;

import java.io.Serializable;
import java.sql.Date;
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
    private Date date;
	
	
	@ManyToOne
    @JoinColumn(name="performance_id", nullable=false)
    private Performance performance;

	
	@OneToMany(mappedBy="presentation")
    private Set<PresentationChairs> presentationChairs;


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}


	public Performance getPerformance() {
		return performance;
	}


	public void setPerformance(Performance performance) {
		this.performance = performance;
	}


	public Set<PresentationChairs> getPresentationChairs() {
		return presentationChairs;
	}


	public void setPresentationChairs(Set<PresentationChairs> presentationChairs) {
		this.presentationChairs = presentationChairs;
	}


	public Presentation() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Presentation(Date date, Performance performance, Set<PresentationChairs> presentationChairs) {
		super();
		this.date = date;
		this.performance = performance;
		this.presentationChairs = presentationChairs;
	}



	
	

}
