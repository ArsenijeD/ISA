package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.domain.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
	}

