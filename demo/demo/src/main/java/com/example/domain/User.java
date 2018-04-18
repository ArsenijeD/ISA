package com.example.domain;

import java.io.Serializable;
import java.util.ArrayList;
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
		roles=new ArrayList<MyRole>();
		enabled=false;
		
	}


	public ArrayList<MyRole> getRoles() {
		return roles;
	}

	public void setRoles(ArrayList<MyRole> roles) {
		this.roles = roles;
	}

	public Set<Ad> getAds() {
		return ads;
	}

	public void setAds(Set<Ad> ads) {
		this.ads = ads;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getLastName() {
		return lastName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	


	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "email", nullable = false)
    private String email;
    //,nullable = false, unique = true
    @Column(name = "first_name", nullable = false)
    private String first_name;
    
//nullable = false, unique = true
    @Column(name = "last_name", nullable = false)
    private String last_name;

    @Column(name = "password_hash", nullable = false)
    private String passwordHash;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private ArrayList<MyRole> roles;
    

    @Column(name = "enabled", nullable = true)
    private boolean enabled;
     
    @OneToMany(mappedBy="user")
    private Set<Ad> ads;
    
    public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}



	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}


    //,nullable = false, unique = true
    @Column(name = "first_name")
    private String firstName;
    
//nullable = false, unique = true
    @Column(name = "last_name")
    private String lastName;

    @Column(name="city",nullable=true)
    private String city;
    
    @Column(name="phone_number",nullable=true)
    private String phoneNumber;
    



	public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
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

//    public Set<MyRole> getRoles() {
//		return roles;
//	}
//
//	public void setRoles(Set<MyRole> roles) {
//		this.roles = roles;
//	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}


	public String getFirstName() {
		return firstName;
	}

	public void setName(String firstName) {
		this.firstName = firstName;
	}

	public String getName() {
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
