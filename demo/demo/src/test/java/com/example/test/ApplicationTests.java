package com.example.test;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.example.DemoApplication;
import com.example.config.JpaConfig;
import com.example.domain.FriendRequest;
import com.example.domain.FriendRequestStatus;
import com.example.repository.FriendRequestRepository;


	@RunWith(SpringRunner.class)
	
	@SpringBootTest
	@ContextConfiguration(
			  classes = { JpaConfig.class }, 
			  loader = AnnotationConfigContextLoader.class)
	public class ApplicationTests {
		
		@Test
		public void contextLoads() {
		}

	}

