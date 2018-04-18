package com.example.test.friendRequestTesting.Repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.example.config.JpaConfig;
import com.example.config.ServiceConfig;
import com.example.domain.FriendRequest;
import com.example.domain.FriendRequestStatus;
import com.example.domain.User;
import com.example.repository.FriendRequestRepository;
import com.example.repository.UserRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		  classes = { JpaConfig.class,ServiceConfig.class}, 
		  loader = AnnotationConfigContextLoader.class)
public class FindByReceiverAndStatusTest {
		
	User sender;
	User receiver;

	User sender2;
	User receiver2;
	
	FriendRequest frPending;
	
	FriendRequest frApproved;

	@Autowired
    private FriendRequestRepository friendRequestRepository;

	
	@Autowired
	private UserRepository userRepository;
	
	@Before
	public void setup() {
		sender = new User();
		
		sender.setId(new Long(1));
		sender.setEmail("admin@admin");
		sender.setPasswordHash("$2a$10$78Ds/FFAVY7eUCo9BhSwxOh0kisBUrc2cFef53sla/ZkpnGwDAgfy");
	        
		receiver = new User();
		receiver.setId(new Long(2));
		receiver.setEmail("user@user");
		receiver.setPasswordHash("$2a$10$78Ds/FFAVY7eUCo9BhSwxOh0kisBUrc2cFef53sla/ZkpnGwDAgfy");
		
		sender2 = new User();
		sender2.setId(new Long(3));
		sender2.setEmail("user3@user3");
		sender2.setPasswordHash("$2a$10$78Ds/FFAVY7eUCo9BhSwxOh0kisBUrc2cFef53sla/ZkpnGwDAgfy");
		
		receiver2 = new User();
		receiver2.setId(new Long(4));
		receiver2.setEmail("user4@user4");
		receiver2.setPasswordHash("$2a$10$78Ds/FFAVY7eUCo9BhSwxOh0kisBUrc2cFef53sla/ZkpnGwDAgfy");
		
		
		
		userRepository.save(receiver);
		userRepository.save(sender);
		userRepository.save(receiver2);
		userRepository.save(sender2);

		
		frApproved =new FriendRequest();

		frApproved.setId(new Long(1));
		frApproved.setReceiver(receiver);
		frApproved.setSender(sender);
		frApproved.setStatus(FriendRequestStatus.APPROVED);
		
		frPending =new FriendRequest();
		frPending.setId(new Long(2));
		frPending.setReceiver(receiver);
		frPending.setSender(sender2);
		frPending.setStatus(FriendRequestStatus.PENDING);
		
		friendRequestRepository.save(frApproved);
		friendRequestRepository.save(frPending);		
	}
	//findByReceiverAndStatus
	@Test
	public void getAllFriendRequestReceivedPending() {
		List<FriendRequest> frList=friendRequestRepository.findByReceiverAndStatus(receiver.getId(), FriendRequestStatus.PENDING);
		assertThat(frList).hasSize(1);
		assertThat(frList.get(0).getId()).isEqualByComparingTo(frPending.getId());
	}
	@Test
	public void getAllFriendRequestSentPending() {
		List<FriendRequest> frList=friendRequestRepository.findBySenderAndStatus(sender2.getId(), FriendRequestStatus.PENDING);
		assertThat(frList).hasSize(1);
		assertThat(frList.get(0).getId()).isEqualByComparingTo(frPending.getId());
	}
	
}
