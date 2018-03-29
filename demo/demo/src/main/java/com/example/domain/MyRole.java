package com.example.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "role")
public class MyRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id", nullable = false, updatable = false)
    private Long role_id;
    
    @Column(name = "role", nullable = false, unique = true) 
    private String name;

    
    public MyRole(){
    	super();
    }
    public MyRole(String role){
    	super();
    	this.name=role;
    	
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


}
