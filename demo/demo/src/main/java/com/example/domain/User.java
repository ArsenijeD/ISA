package com.example.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;



@Entity
@Table(name = "user")
public class User implements Serializable {
	
	
    public User() {
		super();
		roles=new HashSet<MyRole>();
		enabled=false;
		
	}




    public Set<Bid> getBids() {
		return bids;
	}




	public void setBids(Set<Bid> bids) {
		this.bids = bids;
	}




	
	public Set<MyRole> getRoles(){
    	return roles;
    }
    
	public void setRoles(Set<MyRole> roles) {
		this.roles = roles;
	}

    
//   @OneToMany(mappedBy="user",fetch=FetchType.EAGER)
//   private Set<Ad> ads;
   
	
//	public Set<Ad> getAds() {
//		return ads;
//	}
	//
//	public void setAds(Set<Ad> ads) {
//		this.ads = ads;
//	}
	



	/**
	 * 
	 */

	


	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "email", nullable = false)
    private String email;

//nullable = false, unique = true
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "password_hash", nullable = false)
    private String passwordHash;

  

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<MyRole> roles;
    
    

    @Column(name = "enabled", nullable = true)
    private boolean enabled;

    
    @OneToMany(mappedBy="user", fetch = FetchType.EAGER)
    private Set<Bid> bids;
    
    
    
    public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String first_name) {
		this.firstName = first_name;
	}

	public String getLast_name() {
		return lastName;
	}



	public void setLast_name(String last_name) {
		this.lastName = last_name;
	}


    //,nullable = false, unique = true
    @Column(name = "first_name")
    private String firstName;
    

    @Column(name="city",nullable=true)
    private String city;
    
    @Column(name="phone_number",nullable=true)
    private String phoneNumber;
    



	public Long getId() {
        return id;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }



	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}





	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public void setId(Long id) {
		this.id = id;
	}
	@Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email +
                ", passwordHash='" + passwordHash.substring(0, 10) +
                ", role=" + roles +
                '}';
    }


}
