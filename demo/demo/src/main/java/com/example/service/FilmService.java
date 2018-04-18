package com.example.service;

import java.util.List;

import com.example.domain.Film;


public interface FilmService {
	
	List<Film> getAll();
	
	Film getFilmByID(Long id);
	
	boolean registerFilm(Film f);
	
	Film getFilmByName(String name);

}
