package com.example.service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import com.example.domain.User;
import com.example.domain.UserCreateForm;
import com.example.domain.VerificationToken;


public interface UserService {

	Optional<User> getUserById(long id);

	Optional<User> getUserByEmail(String email);

	Collection<User> getAllUsers();

	User create(UserCreateForm form);
	
	void createVerificationTokenForUser(final User user, final String token);
	
	String validateVerificationToken(String token);
	
    public User getUserByToken(final String verificationToken);
    
    void saveRegisteredUser(User user);
    
    public VerificationToken getVerificationToken(final String VerificationToken);
    
    public List<User> getAllFriends(long id);


}
