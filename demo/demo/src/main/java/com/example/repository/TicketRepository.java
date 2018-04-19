package com.example.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import com.example.domain.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
	
	public List<Ticket> findAll();
	public Ticket findById(Long id);
	
//	public void save(Stage stage);
	
	@Modifying
    @Transactional
    void deleteById(Long id);

}
