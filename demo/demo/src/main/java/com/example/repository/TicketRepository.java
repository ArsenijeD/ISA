package com.example.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.domain.FriendRequest;
import com.example.domain.FriendRequestStatus;
import com.example.domain.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
	
	public List<Ticket> findAll();
	public Ticket findById(Long id);
	
//	public void save(Stage stage);
	
	@Modifying
    @Transactional
    void deleteById(Long id);
	
	@Query(value = "SELECT * FROM ticket t where t.reserved = :reserved", nativeQuery=true)
	public List<Ticket> getUnReservedTickets(@Param("reserved") boolean reserved);
	
	@Query(value = "SELECT t FROM Ticket t join t.user s where s.id = :userId and reserved=true")
	public List<Ticket> getTicketByUser(@Param("userId") Long userId);
	
//	
//	@Query("Select fr from FriendRequest fr join fr.sender s  where fr.status=?2 and s.id=?1  ")
//	List<FriendRequest> findBySenderAndStatus(Long friend,FriendRequestStatus status);
	
	
}
