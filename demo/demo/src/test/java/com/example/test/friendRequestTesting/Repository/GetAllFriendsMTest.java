package com.example.test.friendRequestTesting.Repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.example.config.JpaConfig;
import com.example.domain.FriendRequest;
import com.example.domain.FriendRequestStatus;
import com.example.domain.User;
import com.example.repository.FriendRequestRepository;
import com.example.repository.UserRepository;
import com.example.service.TheaterService;
import com.example.service.UserService;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(
//  classes = { JpaConfig.class }, 
//  loader = AnnotationConfigContextLoader.class)
@RunWith(SpringRunner.class)
@SpringBootTest
public class GetAllFriendsMTest {

	@Autowired 
	private FriendRequestRepository friendRequestRepository;
	@Autowired 
	private UserRepository userRepository;
	
	
	//only two users which are friends sfriends
	@Test
	public void getAllFriendsTest() {
	    // given   	
		User sender = new User();
		
		sender.setId(new Long(1));
		sender.setEmail("admin@admin");
		sender.setPasswordHash("$2a$10$78Ds/FFAVY7eUCo9BhSwxOh0kisBUrc2cFef53sla/ZkpnGwDAgfy");
	        
		User receiver = new User();
		receiver.setId(new Long(2));
		receiver.setEmail("user@user");
		receiver.setPasswordHash("$2a$10$78Ds/FFAVY7eUCo9BhSwxOh0kisBUrc2cFef53sla/ZkpnGwDAgfy");

	    userRepository.save(sender);
	    userRepository.save(receiver);
	    
	    
	    FriendRequest fr=new FriendRequest();
	    fr.setId(new Long(1));
	    fr.setSender(sender);
	    fr.setReceiver(receiver);
	    fr.setStatus(FriendRequestStatus.APPROVED);
	    
	    friendRequestRepository.save(fr);

	    // when

	    List<FriendRequest> found = friendRequestRepository.findApprovedRequests(sender.getId(),FriendRequestStatus.APPROVED);
	 
	    // then
		System.out.println("eM: "+ userRepository.findOne(new Long(1)));

	    assertThat(found.size()).isEqualTo(1);
	}

}
