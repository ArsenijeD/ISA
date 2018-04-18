package com.example.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.domain.Ad;

import antlr.collections.List;

public interface AdRepository extends JpaRepository<Ad, Long>{

	ArrayList<Ad> findAllByUser(Long user_id);
	ArrayList<Ad> findAllByConfirmed(Long confirmed);
}
