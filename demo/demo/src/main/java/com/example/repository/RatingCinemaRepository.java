package com.example.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.domain.RatingCinema;


public interface RatingCinemaRepository extends JpaRepository<RatingCinema, Long>{
	
	RatingCinema findOneById(Long id);
	
	@Query(value = "SELECT * FROM rating_cinema rs where rs.user_id = :user_id",
			nativeQuery=true)
	public RatingCinema findByUserID(@Param("user_id") Long user_id);
	//void save(Cinema cinema);
	List<RatingCinema> findAll();
	
	@Modifying
    @Transactional
    void deleteById(Long id);

}
