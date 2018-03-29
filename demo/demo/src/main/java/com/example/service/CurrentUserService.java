package com.example.service;

import com.example.domain.CurrentUser;

public interface CurrentUserService {

	boolean canAccessUser(CurrentUser currentUser, Long userId);

}
