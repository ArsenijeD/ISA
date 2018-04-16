package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.Ad;
import com.example.repository.AdRepository;
import com.example.repository.CinemaRepository;

@Service
public class AdServiceImpl implements AdService {

	
	@Autowired
	private AdRepository adRepository;

	@Override
	public boolean registerAd(Ad a) {
		adRepository.save(a);
		return true;
	}
	
}
