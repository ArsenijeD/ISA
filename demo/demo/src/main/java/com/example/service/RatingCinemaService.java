package com.example.service;

import java.util.List;

import com.example.domain.RatingCinema;

public interface RatingCinemaService {
	
	List<RatingCinema> getAll();
	
	RatingCinema getRatingCinemaByID(Long id);
	
	RatingCinema getRatingCinemaByUserID(Long userID);
	
	boolean registerRatingCinema(RatingCinema rc);
	
	void deleteById(Long id);
	
	boolean updateRatingCinema(RatingCinema rc);

}
