package com.example.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.domain.FriendRequest;
import com.example.domain.FriendRequestStatus;
import com.example.domain.MyRole;
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
		MyRole role=roleRepository.findOneByName(form.getRole().toString());
		System.out.println("sad ce rola");
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
		public List<User> getAllFriends(long id) {
			ArrayList<User> friends=new ArrayList<User>();
			ArrayList<FriendRequest> allReq=(ArrayList<FriendRequest>) friendRequestRepository.findByStatusAndSender(FriendRequestStatus.APPROVED, id);
			for(FriendRequest freq : allReq){
				friends.add(freq.getSender());
			}
			return friends;
		}
}
