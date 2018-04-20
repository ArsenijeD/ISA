package com.example.service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.example.DTO.UserDTOFriend;
import com.example.DTO.UserUpdateDTO;
import com.example.domain.FriendRequest;
import com.example.domain.User;
import com.example.domain.UserCreateForm;
import com.example.domain.VerificationToken;


public interface UserService {

	Optional<User> getUserById(long id);

	Optional<User> getUserByEmail(String email);

	User getOneById(Long id);
	
	Collection<User> getAllUsers();

	boolean updateUserRole(User u);
	
	boolean updateUserInfo(UserUpdateDTO u,long userID);
	boolean updateUserInfo(User u);

	List<User> getAll();
	
	Set<User> getUsersByIdIn(Set<Long> ids);
	
	User create(UserCreateForm form);
	
	void createVerificationTokenForUser(final User user, final String token);
	
	String validateVerificationToken(String token);
	
    public User getUserByToken(final String verificationToken);
    
    void saveRegisteredUser(User user);
    
    public VerificationToken getVerificationToken(final String VerificationToken);
    
    //////friend request
    public List<User> getSearchedFriends(String firstName,String lastName);
    
    public List<UserDTOFriend> getAllFriends(long friendId);

    public List<FriendRequest> getAllFriendRequestSentPending(Long senderId);
    
    public List<FriendRequest> getALLFriendRequestReceivedPending(Long receiverId);
    
    public Boolean sendRequest(Long senderId,Long receiverId);
    
    public Boolean approveRequest(Long frequestId,Long receiverId);
    
    public Boolean rejectRequest(Long frequestId,Long receiverId);
    
    public Boolean deleteSentRequest(Long frequestId,Long senderId);
    
    public Boolean deleteFriend(Long frequestId,Long friendUnfrinder);

    public  List<FriendRequest> findBySenderOrReceiver(Long id);
    
}
