package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.domain.MyRole;

public interface RoleRepository extends JpaRepository<MyRole, Long> {
	MyRole findOneByName(String role);
}
