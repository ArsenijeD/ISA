package com.example.service;

import java.util.ArrayList;

import com.example.domain.Ad;
import com.example.domain.Notification;
import com.example.domain.User;

public interface NotificationService {

	
	boolean registerNotification(Notification n);
	
	ArrayList<Notification> findAllByUser(User u);
	
	void deleteByUser(User u);
}
