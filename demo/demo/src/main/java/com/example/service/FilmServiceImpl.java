package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.Film;
import com.example.repository.FilmRepository;

@Service
public class FilmServiceImpl implements FilmService {
	
	@Autowired
	private FilmRepository filmRepository;

	@Override
	public List<Film> getAll() {
		
		return filmRepository.findAll();
	}

	@Override
	public Film getFilmByID(Long id) {
		
		return filmRepository.findById(id);
	}

	@Override
	public boolean registerFilm(Film f) {
		filmRepository.save(f);
		return true;
	}

	@Override
	public Film getFilmByName(String name) {
		
		return filmRepository.findByName(name);
	}

	@Override
	public boolean updateFilm(Film f) {
		
		Film film = filmRepository.findOne(f.getId());
		
		film.setId(f.getId());
		film.setName(f.getName());
		film.setDescription(f.getDescription());
		film.setDuration(f.getDuration());
		film.setAverageRating(f.getAverageRating());
		film.setGenre(f.getGenre());
		film.setPrice(f.getPrice());
		
		filmRepository.flush();
		
		return true;
		
		
	}

	@Override
	public void deleteById(Long id) {
		
		filmRepository.deleteById(id);
		
	}

}
