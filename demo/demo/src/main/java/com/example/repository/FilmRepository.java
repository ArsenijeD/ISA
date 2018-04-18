package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

import com.example.domain.Film;

public interface FilmRepository extends JpaRepository<Film, Long> {
	
	
	List<Film> findAll();
	Film findById(Long id);
	Film findByName(String name);
//	void save(Film film);

}
