package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import com.example.domain.Seat;

public interface SeatRepository extends JpaRepository<Seat, Long>{
	
	public List<Seat> findAll();
	public Seat findById(Long id);
//	public void save(Seat seat);
	
	
	@Query(value = "SELECT * FROM seat s where s.hall_id = :hallID", nativeQuery=true)
	public List<Seat> findByHallID(@Param("hallID") Long hallID);

}
