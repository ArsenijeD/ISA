package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.Cinema;
import com.example.domain.RatingCinema;

import com.example.repository.RatingCinemaRepository;

@Service
public class RatingCinemaServiceImpl implements RatingCinemaService{

	@Autowired
	private RatingCinemaRepository ratingCinemaRepository;
	
	@Override
	public List<RatingCinema> getAll() {
		
		return ratingCinemaRepository.findAll();
	}

	@Override
	public RatingCinema getRatingCinemaByID(Long id) {
		
		return ratingCinemaRepository.findOneById(id);
		
	}

	@Override
	public RatingCinema getRatingCinemaByUserID(Long userID) {
		
		return ratingCinemaRepository.findByUserID(userID);
		
	}

	@Override
	public boolean registerRatingCinema(RatingCinema rc) {
		
		ratingCinemaRepository.save(rc);
		return true;
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		ratingCinemaRepository.deleteById(id);
		
	}

	@Override
	public boolean updateRatingCinema(RatingCinema rc) {
		
		RatingCinema ratingCinema = ratingCinemaRepository.findOne(rc.getId());
		ratingCinema.setId(rc.getId());
		ratingCinema.setUser(rc.getUser());
		ratingCinema.setValue(rc.getValue());
		
		ratingCinemaRepository.flush();
		
		return true;
		
	}
	
	

}
