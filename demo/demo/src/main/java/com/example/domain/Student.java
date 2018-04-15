package com.example.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Student {
 
	public Student(){
		super();
	}
	
	
    public Student(long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}


	@Id
    private long id;
     
    @Column(name="name")
    private String name;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
     
    // standard constructor, getters, setters
}
