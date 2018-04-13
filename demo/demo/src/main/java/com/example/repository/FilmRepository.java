package com.example.repository;

import java.util.List;

import org.springframework.data.repository.Repository;

import com.example.domain.Film;

public interface FilmRepository extends Repository<Film, Long> {
	
	
	List<Film> findAll();
	Film findById(Long id);
	Film findByName(String name);
	void save(Film film);

}
