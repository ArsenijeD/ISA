package com.example.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.Ad;
import com.example.domain.Cinema;
import com.example.domain.User;
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
	public ArrayList<Ad> getAdsByUser(User u) {
		
		return adRepository.findAllByUser(u);
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

	@Override
	public void deleteById(Long id) {
		
		adRepository.deleteById(id);
		
	}

	@Override
	public boolean updateWholeAd(Ad a) {
		
		Ad ad = adRepository.findOne(a.getId());
		ad.setName(a.getName());
		ad.setDate(a.getDate());
		ad.setDescription(a.getDescription());
		ad.setImage(a.getImage());
		ad.setId(a.getId());
		
		
		adRepository.flush();
		return true;
	}

	@Override
	public Ad findOneById(Long id) {
	
		return adRepository.findOneById(id);
	}
}
