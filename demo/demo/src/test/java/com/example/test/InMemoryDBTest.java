package com.example.test;

import static org.junit.Assert.assertEquals;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;

import com.example.config.JpaConfig;
import com.example.domain.Student;
import com.example.repository.StudentRepository;

@RunWith(SpringJUnit4ClassRunner.class)
	@ContextConfiguration(
	  classes = { JpaConfig.class }, 
	  loader = AnnotationConfigContextLoader.class)
	@Transactional
	//@ActiveProfiles("test")
	public class InMemoryDBTest {
	     
	    @Resource
	    private StudentRepository studentRepository;
	     
	    @Test
	    public void givenStudent_whenSave_thenGetOk() {
	        Student student = new Student(1, "john");
	        studentRepository.save(student);
	         
	        Student student2 = studentRepository.findOne(new Long(1));
	        assertEquals("john", student2.getName());
	    }
	}
	
