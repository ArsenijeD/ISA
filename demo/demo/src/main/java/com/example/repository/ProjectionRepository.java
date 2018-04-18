package com.example.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import com.example.domain.Projection;

public interface ProjectionRepository extends JpaRepository<Projection, Long>{
	
	List<Projection> findAll();
	Projection findById(Long id);
//	void save(Projection projection);
	
	@Query(value = "SELECT p.projection_id, p.date, p.time, p.film_id, p.hall_id, p.discount FROM projection p where p.hall_id = :hallID",
			nativeQuery=true)
	public List<Projection> findByHallID(@Param("hallID") Long hallID);
	
	@Modifying
    @Transactional
    void deleteById(Long id);

}
