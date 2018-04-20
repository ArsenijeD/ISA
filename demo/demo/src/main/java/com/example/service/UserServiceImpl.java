package com.example.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.DTO.MyFriendDTOMaper;
import com.example.DTO.UserDTOFriend;
import com.example.DTO.UserUpdateDTO;
import com.example.domain.FriendRequest;
import com.example.domain.FriendRequestStatus;
import com.example.domain.MyRole;
import com.example.domain.Role;
import com.example.domain.User;
import com.example.domain.UserCreateForm;
import com.example.domain.VerificationToken;
import com.example.repository.FriendRequestRepository;
import com.example.repository.RoleRepository;
import com.example.repository.UserRepository;
import com.example.repository.VerificationTokenRepository;



@Service
public class UserServiceImpl implements UserService {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
	
	 public static final String TOKEN_INVALID = "invalidToken";
	 public static final String TOKEN_EXPIRED = "expired";
     public static final String TOKEN_VALID = "valid";
	    
    @Autowired 
    private FriendRequestRepository friendRequestRepository;
    
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired 
	private VerificationTokenRepository tokenRepository;

	@Override
	public Optional<User> getUserById(long id) {
		LOGGER.debug("Getting user={}", id);
		return Optional.ofNullable(userRepository.findOne(id));
	}

	@Override
	public Optional<User> getUserByEmail(String email) {
		LOGGER.debug("Getting user by email={}", email);
		return userRepository.findOneByEmail(email);
	}

	@Override
	public Collection<User> getAllUsers() {
		LOGGER.debug("Getting all users");
		return userRepository.findAll(new Sort("email"));
	}

	@Override
	public User create(UserCreateForm form) {
		User user = new User();
		user.setEmail(form.getEmail());
		user.setPasswordHash(new BCryptPasswordEncoder().encode(form.getPassword()));
		user.setFirstName(form.getName());
		user.setLastName(form.getLastName());
		user.setCity(form.getCity());
		user.setPhoneNumber(form.getPhoneNumber());
		MyRole role=roleRepository.findOneByName(Role.USER.toString());
		System.out.println("rola je:"+role.getName());
		user.getRoles().add(role);

		System.out.println("dodao role");
		userRepository.save(user);
		return user;
		
	}
	
	 @Override
	    public void createVerificationTokenForUser(final User user, final String token) {
	        final VerificationToken myToken = new VerificationToken(token, user);
	        tokenRepository.save(myToken);
	    }
	 
	 @Override
	    public String validateVerificationToken(String token) {
	        final VerificationToken verificationToken = tokenRepository.findByToken(token);
	        if (verificationToken == null) {
	            return TOKEN_INVALID;
	        }
	        final User user = verificationToken.getUser();
	        final Calendar cal = Calendar.getInstance();
	        if ((verificationToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
	            tokenRepository.delete(verificationToken);
	            return TOKEN_EXPIRED;
	        }

	        user.setEnabled(true);
	        // tokenRepository.delete(verificationToken);
	        userRepository.save(user);
	        return TOKEN_VALID;
	 }
	 
	    @Override
	    public User getUserByToken(final String verificationToken) {
	        final VerificationToken token = tokenRepository.findByToken(verificationToken);
	        if (token != null) {
	            return token.getUser();
	        }
	        return null;
	    }
	    
	    @Override
	    public void saveRegisteredUser(final User user) {
	        userRepository.save(user);
	    }
	    
	    @Override
	    public VerificationToken getVerificationToken(final String VerificationToken) {
	        return tokenRepository.findByToken(VerificationToken);
	    }
	    
			
		@Override
		public List<FriendRequest> findBySenderOrReceiver(Long id) {
			return friendRequestRepository.findBySenderOrReceiver(id);
		}

		@Override
		public List<User> getSearchedFriends(String firstName, String lastName) {
			ArrayList<User> allUsers=(ArrayList<User>) userRepository.findAllUsersFandL(firstName,lastName);			
			return allUsers;
		}

		@Override
		public List<UserDTOFriend> getAllFriends(long friendId) {
			ArrayList<UserDTOFriend> friends=new ArrayList<UserDTOFriend>();
			ArrayList<FriendRequest> allReq=(ArrayList<FriendRequest>) friendRequestRepository.findApprovedRequests(friendId,FriendRequestStatus.APPROVED);
			for(FriendRequest freq : allReq){
				if(freq.getReceiver().getId()==friendId) {
					friends.add(MyFriendDTOMaper.mapReceived(freq));
				}else {
					friends.add(MyFriendDTOMaper.mapSent(freq));
				}
				
			}
			return friends;
		}

		@Override
		public List<FriendRequest> getAllFriendRequestSentPending(Long senderId) {
			return friendRequestRepository.findBySenderAndStatus(senderId, FriendRequestStatus.PENDING);
		}

		@Override
		public List<FriendRequest> getALLFriendRequestReceivedPending(Long receiverId) {
			return friendRequestRepository.findByReceiverAndStatus(receiverId, FriendRequestStatus.PENDING);
		}

		@Override
		public Boolean sendRequest(Long senderId, Long receiverId) {
			//receiver and sender are same
			if(senderId.equals(receiverId)) {
				return false;
			}
			
			//cannot send request if another request is already pending,or you are already friends
			if(friendRequestRepository.findBySenderAndReceiverAndNotTwoStatus(senderId,receiverId,FriendRequestStatus.APPROVED,FriendRequestStatus.PENDING).size()!=0) {
				return false;
			}
			if(friendRequestRepository.findBySenderAndReceiverAndNotTwoStatus(senderId,receiverId,FriendRequestStatus.APPROVED,FriendRequestStatus.PENDING).size()!=0) {
				return false;
			}
			FriendRequest fr=new FriendRequest();
			fr.setReceiver(userRepository.findOne(receiverId));
			fr.setSender(userRepository.findOne(senderId));
			fr.setStatus(FriendRequestStatus.PENDING);
			
			if( friendRequestRepository.save(fr)!=null) {
				return true;
			}else {
				return false;
			}
		}

		@Override
		public Boolean approveRequest(Long frequestId, Long receiverId) {
			// TODO Auto-generated method stub
			FriendRequest fr=friendRequestRepository.findOne(frequestId);
			if(fr.getReceiver().getId().equals(receiverId)) {
				if(fr.getStatus()==FriendRequestStatus.PENDING) {
					fr.setStatus(FriendRequestStatus.APPROVED);
					friendRequestRepository.flush();
					return true;
				}else {
					return false;
				}
			}else {
				return false;
			}
		}

		//ili da se trazi preko sendera i receivera i da moze biti samo jedan zapis 
		@Override
		public Boolean rejectRequest(Long frequestId, Long receiverId) {
			// TODO Auto-generated method stub
			FriendRequest fr=friendRequestRepository.findOne(frequestId);
			if(fr.getReceiver().getId().equals(receiverId) && fr.getStatus()==FriendRequestStatus.PENDING) {
				friendRequestRepository.delete(frequestId);
				friendRequestRepository.flush();
				return true;
			}else {
			return false;
			}
		}

		@Override
		public Boolean deleteSentRequest(Long frequestId, Long senderId) {
			// TODO Auto-generated method stub
			FriendRequest fr=friendRequestRepository.findOne(frequestId);
			if(fr.getSender().getId().equals(senderId) && fr.getStatus()==FriendRequestStatus.PENDING) {
				friendRequestRepository.delete(frequestId);
				friendRequestRepository.flush();
				return true;
			}else {
			return false;
			}
		}

		@Override
		public Boolean deleteFriend(Long frequestId, Long friendUnfriender) {
			// TODO Auto-generated method stub
			FriendRequest fr=friendRequestRepository.findOne(frequestId);
			if((fr.getSender().getId().equals(friendUnfriender) || fr.getReceiver().getId().equals(friendUnfriender))&& fr.getStatus()==FriendRequestStatus.APPROVED) {
				friendRequestRepository.delete(frequestId);
				friendRequestRepository.flush();
				return true;
			}else {
			return false;
			}
		}
		
		

	
		public Set<User> getUsersByIdIn(Set<Long> ids) {
			
			return userRepository.findByIdIn(ids);
		}

		@Override
		public List<User> getAll() {
			
			return userRepository.findAll();
		}

		@Override
		public boolean updateUserRole(User u) {
			
			User user = userRepository.findOne(u.getId());
			user.getRoles().clear();
			for(MyRole role:u.getRoles()) {
				user.getRoles().add(role);
			}
			user.setId(u.getId());
			
			//userRepository.save(user);
			userRepository.flush();
			return true;
		}
		
		@Override
		public boolean updateUserInfo(UserUpdateDTO u,long userID) {			
			User user = userRepository.findOne(userID);
			user.setCity(u.getCity());
			user.setFirstName(u.getFirstName());
			user.setLastName(u.getLastName());
			user.setPhoneNumber(u.getPhoneNumber());
			userRepository.flush();
			System.out.println("userdto update servis");
			return true;
		}
		
		@Override
		public boolean updateUserInfo(User u) {			
			User user = userRepository.findOne(u.getId());
			user.setCity(u.getCity());
			user.setFirstName(u.getFirstName());
			user.setLastName(u.getLastName());
			user.setPhoneNumber(u.getPhoneNumber());
			userRepository.flush();
			System.out.println("user update servis");
			return true;
		}
		
}
