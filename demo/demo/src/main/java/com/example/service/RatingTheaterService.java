package com.example.service;

import java.util.List;

import com.example.domain.RatingTheater;

public interface RatingTheaterService {
	
	List<RatingTheater> getAll();
	
	RatingTheater getRatingTheaterByID(Long id);
	
	RatingTheater getRatingTheaterByUserID(Long userID);
	
	boolean registerRatingTheater(RatingTheater rt);
	
	void deleteById(Long id);
	
	boolean updateRatingTheater(RatingTheater rt);

}
