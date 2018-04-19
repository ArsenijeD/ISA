package com.example.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.domain.Ad;
import com.example.domain.Bid;


public interface BidRepository  extends JpaRepository<Bid, Long> {

	ArrayList<Bid> findAllByAd(Ad ad);
	
}
