package com.example.service;

import java.util.ArrayList;

import com.example.domain.Ad;
import com.example.domain.Cinema;
import com.example.domain.User;

import antlr.collections.List;

public interface AdService {

	
	boolean registerAd(Ad a);
	
	ArrayList<Ad> getAdsByUser(User u);
	
	ArrayList<Ad> getAdsByConfirmed(Long confirmed);
	
	boolean updateAd(Ad a);
	
	void deleteById(Long id);
	
	boolean updateWholeAd(Ad a);
	
	Ad findOneById(Long id);
	
	public ArrayList<Ad> findByUserNotAndConfirmed(User u, Long confirmed);
}
