package com.example.domain;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

public class UserCreateForm {

    @NotEmpty
    private String email = "";

    @NotEmpty
    private String password = "";

   // @NotEmpty
    private String passwordRepeated = "";
/*
    @NotNull
    private Role role = Role.USER;
*/
    
    @NotEmpty
    private String lastName = "";

    @NotEmpty
    private String name = "";

    @NotEmpty
    private String city = "";
    
    @NotEmpty
    private String phoneNumber = "";
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordRepeated() {
        return passwordRepeated;
    }

    public void setPasswordRepeated(String passwordRepeated) {
        this.passwordRepeated = passwordRepeated;
    }
/*
    public Role getRole() {
        return role;
    }
*/
    /*
    public void setRole(Role role) {
        this.role = role;
    }
*/
    @Override
    public String toString() {
        return "UserCreateForm{" +
                "email='" + email + '\'' +
                ", password=***" + '\'' +
                ", passwordRepeated=***" + '\'' +
                //", role=" + role +
                '}';
    }

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

}
