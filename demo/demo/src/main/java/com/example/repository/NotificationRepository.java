package com.example.repository;

import java.util.ArrayList;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import com.example.domain.Ad;
import com.example.domain.Notification;
import com.example.domain.User;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

	
	ArrayList<Notification> findAllByUser(User u);
	
	@Modifying
    @Transactional
    void deleteByUser(User u);
}
