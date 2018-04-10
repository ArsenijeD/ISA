package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.domain.FriendRequest;
import com.example.domain.FriendRequestStatus;

public interface FriendRequestRepository extends JpaRepository<FriendRequest, Long>  {

	//svi poslati zahtevi koji cekaju odgovor
	@Query("Select fr.id,fr.status,fr.sender,fr.receiver from FriendRequest fr where fr.status=?1 and fr.sender=?2")
	List<FriendRequest> findByStatusAndSender(FriendRequestStatus status,Long sender);
	// svi primljeni zahtevi koji cekaju odgovor
	
	
	//svi prijatelji
	
	
}
