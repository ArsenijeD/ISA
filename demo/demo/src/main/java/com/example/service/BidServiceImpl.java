package com.example.service;

import java.util.ArrayList;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.Ad;
import com.example.domain.Bid;
import com.example.repository.AdRepository;
import com.example.repository.BidRepository;

@Service
public class BidServiceImpl implements BidService {

	@Autowired
	private BidRepository bidRepository;

	@Override
	public ArrayList<Bid> getBidsByAd(Ad ad) {
		
		return bidRepository.findAllByAd(ad);
	}

	@Override
	@Transactional
	public boolean registerBid(Bid b) {
		bidRepository.save(b);
		return true;
	}

	@Override
	public void deleteById(Long id) {
		bidRepository.deleteById(id);
		
	}
}
