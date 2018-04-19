package com.example.test.friendRequestTesting.Service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.example.config.JpaConfig;
import com.example.config.ServiceConfig;
import com.example.domain.FriendRequest;
import com.example.domain.FriendRequestStatus;
import com.example.domain.User;
import com.example.repository.FriendRequestRepository;
import com.example.service.UserService;


//ovo treba da se ubaci u test profile
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		  classes = { JpaConfig.class,ServiceConfig.class}, 
		  loader = AnnotationConfigContextLoader.class)
public class GetAllFriendRequestSentPendingMTest {
/*	@TestConfiguration
	static class AccountServiceTestContextConfiguration {
		@Bean
		@Primary
		public UserService userService() {
			return new UserServiceImpl();
		}
		@Bean
		@Primary
		public FriendRequestRepository friendRequestRepository() {
			return Mockito.mock(FriendRequestRepository.class);
		}
	}
*/
	@Autowired
	UserService userService;
    @MockBean
    private FriendRequestRepository friendRequestRepository;
    

	
	@Before
	public void setup() {
		User sender = new User();
		
		sender.setId(new Long(1));
		sender.setEmail("admin@admin");
		sender.setPasswordHash("$2a$10$78Ds/FFAVY7eUCo9BhSwxOh0kisBUrc2cFef53sla/ZkpnGwDAgfy");
	        
		User receiver = new User();
		receiver.setId(new Long(2));
		receiver.setEmail("user@user");
		receiver.setPasswordHash("$2a$10$78Ds/FFAVY7eUCo9BhSwxOh0kisBUrc2cFef53sla/ZkpnGwDAgfy");
		
		FriendRequest friendRequest =new FriendRequest();

		friendRequest.setId(new Long(1));
		friendRequest.setReceiver(receiver);
		friendRequest.setSender(sender);
		
		List<FriendRequest> friendRequestList=new ArrayList<FriendRequest>();
		friendRequestList.add(friendRequest);
		Mockito.when(friendRequestRepository.findBySenderAndStatus(new Long(1), FriendRequestStatus.PENDING)).thenReturn(friendRequestList);
		}
		
	@Test
	public void GetAllFriendRequestSentPending() {
		List<FriendRequest> friendRequestList=userService.getAllFriendRequestSentPending(new Long(1));
		assertThat(friendRequestList).hasSize(1);
	}
	
	@After
	public void verify() {
	Mockito.verify(friendRequestRepository, VerificationModeFactory.times(1)).findBySenderAndStatus(Mockito.anyLong(),Mockito.any(FriendRequestStatus.class));
	// This is allowed here: using container injected mocks
	Mockito.reset(friendRequestRepository);
	}
}
