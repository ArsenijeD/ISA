package com.example.test.friendRequestTesting;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.domain.User;
import com.example.service.UserServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ServiceTest {
	
	@Autowired
	private UserServiceImpl userServiceImpl;
	
	@Test
	public void testFindAll() {
		List<User> students = userServiceImpl.getAllFriends(1);
		assertThat(students).hasSize(1); 
	}
}
