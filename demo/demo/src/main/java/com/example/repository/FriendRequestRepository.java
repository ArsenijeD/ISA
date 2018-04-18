package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.domain.FriendRequest;
import com.example.domain.FriendRequestStatus;

public interface FriendRequestRepository extends JpaRepository<FriendRequest, Long>  {
	
	//all approved requests for specific user-all friends
	@Query("Select fr from FriendRequest fr join fr.sender s  join fr.receiver r where fr.status=?2 and (s.id=?1 or r.id=?1) ")
	List<FriendRequest> findApprovedRequests(Long friend,FriendRequestStatus status);
	
	
	
	@Query("Select fr from FriendRequest fr join fr.sender s  where fr.status=?2 and s.id=?1  ")
	List<FriendRequest> findBySenderAndStatus(Long friend,FriendRequestStatus status);
	

	@Query("Select fr from FriendRequest fr join fr.receiver  r where fr.status=?2 and r.id=?1 ")
	List<FriendRequest> findByReceiverAndStatus(Long friend,FriendRequestStatus status);
	
	@Query("Select fr from FriendRequest fr join fr.sender s  join fr.receiver r where s.id=?1 and r.id=?2 and fr.status!=?3 and fr.status!=?4")
	List<FriendRequest> findBySenderAndReceiverAndNotTwoStatus(Long senderId,long receiverId,FriendRequestStatus status1,FriendRequestStatus status2);
	
}
