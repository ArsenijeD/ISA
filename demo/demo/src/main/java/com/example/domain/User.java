package com.example.domain;

import java.io.Serializable;
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;



@Entity
@Table(name = "user")
public class User implements Serializable {

	
//    public User() {
//		super();
//		roles=new HashSet<MyRole>();
//		enabled=false;
//	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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

	public void setId(Long id) {
		this.id = id;
	}

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

//    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
//    private Set<MyRole> roles;
    
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name="role_id")
    private MyRole role;
    
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

	public void setId(Long id) {
		this.id = id;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

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

	public MyRole getRole() {
		return role;
	}

	public void setRole(MyRole role) {
		this.role = role;
	}

	

	
//	@Override
//    public String toString() {
//        return "User{" +
//                "id=" + id +
//                ", email='" + email +
//                ", passwordHash='" + passwordHash.substring(0, 10) +
//                ", role=" + roles +
//                '}';
//    }
}
