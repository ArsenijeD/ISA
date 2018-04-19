package com.example.service;

import java.util.ArrayList;

import com.example.domain.Ad;
import com.example.domain.Cinema;

import antlr.collections.List;

public interface AdService {

	
	boolean registerAd(Ad a);
	
	ArrayList<Ad> getAdsByUser(Long id);
	
	ArrayList<Ad> getAdsByConfirmed(Long confirmed);
	
	boolean updateAd(Ad a);
	
	void deleteById(Long id);
	
	boolean updateWholeAd(Ad a);
	
	Ad findOneById(Long id);
}
