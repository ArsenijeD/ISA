package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.RatingCinema;
import com.example.domain.RatingTheater;
import com.example.repository.RatingCinemaRepository;
import com.example.repository.RatingTheaterRepository;

@Service
public class RatingTheaterServiceImpl implements RatingTheaterService{

	@Autowired
	private RatingTheaterRepository ratingTheaterRepository;
	
	
	@Override
	public List<RatingTheater> getAll() {
		// TODO Auto-generated method stub
		return ratingTheaterRepository.findAll();
	}

	@Override
	public RatingTheater getRatingTheaterByID(Long id) {
		// TODO Auto-generated method stub
		return ratingTheaterRepository.findOneById(id);
	}

	@Override
	public RatingTheater getRatingTheaterByUserID(Long userID) {
		// TODO Auto-generated method stub
		return ratingTheaterRepository.findByUserID(userID);
	}

	@Override
	public boolean registerRatingTheater(RatingTheater rt) {
		// TODO Auto-generated method stub
		ratingTheaterRepository.save(rt);
		return true;
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		ratingTheaterRepository.deleteById(id);
		
	}

	@Override
	public boolean updateRatingTheater(RatingTheater rt) {
		
		RatingTheater ratingTheater = ratingTheaterRepository.findOne(rt.getId());
		ratingTheater.setId(rt.getId());
		ratingTheater.setUser(rt.getUser());
		ratingTheater.setValue(rt.getValue());
		
		ratingTheaterRepository.flush();
		
		return true;
	}

}
