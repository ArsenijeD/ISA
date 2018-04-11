package com.example.test.friendRequestTesting;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.DemoApplication;
import com.example.domain.User;
import com.example.service.UserServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
//@DataJpaTest
@ContextConfiguration(classes = DemoApplication.class)
public class ServiceTest {
	
	@Autowired
	private UserServiceImpl userServiceImpl;
	
	@Test
	public void testFindAll() {
		List<User> students = userServiceImpl.getAllFriends(new Long(1));
		assertThat(students).hasSize(1); 
	//	Optional<User> user= userServiceImpl.getUserById(new Long(1));
//		assertThat(user.get().getId()).isEqualByComparingTo(new Long(1));
	}
}
