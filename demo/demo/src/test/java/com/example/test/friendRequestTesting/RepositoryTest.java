package com.example.test.friendRequestTesting;


import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;

import com.example.DemoApplication;
import com.example.config.JpaConfig;
import com.example.domain.FriendRequest;
import com.example.domain.FriendRequestStatus;
import com.example.domain.User;
import com.example.repository.FriendRequestRepository;

//@RunWith(SpringRunner.class)
//@SpringBootTest
//@ContextConfiguration(classes = DemoApplication.class)
@DataJpaTest // ovo krene da popunjava neku h2 bazu i pokrene mi data.sql u kojem je sintaxa za MySql pa imam konflikte
//@ActiveProfiles("test")
//@TestPropertySource(locations="classpath:application.properties")
@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(
//  classes = { JpaConfig.class }, 
//  loader = AnnotationConfigContextLoader.class)
//@Transactional
public class RepositoryTest {
	@Autowired
	private TestEntityManager entityManager;
	
	@Autowired 
	private FriendRequestRepository friendRequestRepository;
	
	@Test
	public void findApprovedRequestReceived() {
	    // given   	

		User sender = new User();
		
		sender.setId(new Long(1));
		sender.setEmail("admin@admin");
		sender.setPasswordHash("$2a$10$78Ds/FFAVY7eUCo9BhSwxOh0kisBUrc2cFef53sla/ZkpnGwDAgfy");
	    entityManager.persist(sender);
	    entityManager.flush();
	    
	    
		User receiver = new User();
		sender.setId(new Long(2));
		sender.setEmail("user@user");
		sender.setPasswordHash("$2a$10$78Ds/FFAVY7eUCo9BhSwxOh0kisBUrc2cFef53sla/ZkpnGwDAgfy");
	    entityManager.persist(receiver);
	    entityManager.flush();
	    
	    entityManager.persist(receiver);
	    entityManager.flush();
	    
	    
	    FriendRequest fr=new FriendRequest();
	    fr.setId(new Long(1));
	    fr.setSender(sender);
	    fr.setReceiver(receiver);
	    fr.setStatus(FriendRequestStatus.APPROVED);
	    
	    // when
	    List<FriendRequest> found = friendRequestRepository.findByStatusAndSender(FriendRequestStatus.APPROVED, sender.getId());
	 
	    // then
	    assertThat(found.size()).isEqualTo(1);
	}
}
