package com.example.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.service.UserService;

@Controller
public class UsersController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UsersController.class);
    
    @Autowired
    private UserService userService;

    @RequestMapping("/users")
    public ModelAndView getUsersPage() {
        LOGGER.debug("Users page triggered");
        return new ModelAndView("users", "users", userService.getAllUsers());
    }


}
