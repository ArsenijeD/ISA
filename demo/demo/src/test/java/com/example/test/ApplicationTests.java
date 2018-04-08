package com.example.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.DemoApplication;


	@RunWith(SpringRunner.class)
	
	@SpringBootTest
	@ContextConfiguration(classes = DemoApplication.class)
	public class ApplicationTests {

		
		@Test
		public void contextLoads() {
		}
	}

