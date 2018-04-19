package com.example.service;

import java.util.List;

import com.example.domain.Film;
import com.example.domain.Projection;


public interface FilmService {
	
	List<Film> getAll();
	
	Film getFilmByID(Long id);
	
	boolean registerFilm(Film f);
	
	Film getFilmByName(String name);
	
	boolean updateFilm(Film f);
	
	void deleteById(Long id);

}
