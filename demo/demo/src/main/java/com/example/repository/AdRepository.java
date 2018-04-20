package com.example.repository;

import java.util.ArrayList;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import com.example.domain.Ad;
import com.example.domain.User;

import antlr.collections.List;

public interface AdRepository extends JpaRepository<Ad, Long>{

	ArrayList<Ad> findAllByUser(User u);
	ArrayList<Ad> findAllByConfirmed(Long confirmed);
	
	@Modifying
    @Transactional
    void deleteById(Long id);
	
	Ad findOneById(Long id);
}
