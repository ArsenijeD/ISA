package com.example.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.Ad;
import com.example.domain.Cinema;
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

	@Override
	public ArrayList<Ad> getAdsByUser(Long id) {
		
		return adRepository.findAllByUser(id);
	}

	@Override
	public ArrayList<Ad> getAdsByConfirmed(Long confirmed) {
		return adRepository.findAllByConfirmed(confirmed);
	}

	@Override
	public boolean updateAd(Ad a) {
		
		Ad ad = adRepository.findOne(a.getId());
		ad.setConfirmed(a.getConfirmed());
		ad.setId(a.getId());
		
		
		adRepository.flush();
		return true;
	}
	
}
