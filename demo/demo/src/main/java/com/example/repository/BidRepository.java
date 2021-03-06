package com.example.repository;

import java.util.ArrayList;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import com.example.domain.Ad;
import com.example.domain.Bid;


public interface BidRepository  extends JpaRepository<Bid, Long> {

	ArrayList<Bid> findAllByAd(Ad ad);
	
	@Modifying
    @Transactional
    void deleteById(Long id);
	
}
