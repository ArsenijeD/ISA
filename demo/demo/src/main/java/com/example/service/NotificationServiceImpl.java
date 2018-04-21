package com.example.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.Notification;
import com.example.domain.User;
import com.example.repository.AdRepository;
import com.example.repository.NotificationRepository;

@Service
public class NotificationServiceImpl implements NotificationService {

	

	@Autowired
	private NotificationRepository notificationRepository;

	@Override
	public boolean registerNotification(Notification n) {
		
		notificationRepository.save(n);
		return true;
	}

	@Override
	public ArrayList<Notification> findAllByUser(User u) {
		
		return notificationRepository.findAllByUser(u);
	}

	@Override
	public void deleteByUser(User u) {
		
		notificationRepository.deleteByUser(u);
		
	}

	

	
}
